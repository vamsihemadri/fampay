package com.rivigo.zoom.datastore.dao.pg;

import com.rivigo.zoom.datastore.model.AbstractModel;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.PersistenceException;
import org.jdbi.v3.sqlobject.transaction.Transactional;

public interface AbstractDao<T extends AbstractModel> extends Transactional<AbstractDao<T>> {

  Long add(T t);

  Long update(T t);

  default T save(T t) {

    Long id = (t.getId() == null) ? this.add(t) : this.update(t);

    return findById(id)
        .orElseThrow(
            () ->
                new PersistenceException(
                    "Cannot fetch entity with id "
                        + t.getId()
                        + ". Please refresh the page and try again."));
  }

  default List<T> saveAll(Collection<T> tCollection) {
    return tCollection.stream().map(this::save).collect(Collectors.toList());
  }

  Optional<T> findById(Long id);
}
