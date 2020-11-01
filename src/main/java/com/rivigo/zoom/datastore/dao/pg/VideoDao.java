package com.rivigo.zoom.datastore.dao.pg;

import com.rivigo.zoom.datastore.model.Video;
import java.util.List;
import java.util.Optional;
import org.jdbi.v3.sqlobject.config.RegisterFieldMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterFieldMapper(Video.class)
public interface VideoDao extends AbstractDao<Video> {

  @SqlUpdate(
      ""
          + "INSERT INTO videos ( "
          + "title, "
          + "description, "
          + "published_at, "
          + "youtube_video_id, "
          + "youtube_channel_id, "
          + "channel_name, "
          + "thumb_nails_metadata ) "
          + "VALUES ( "
          + ":title, "
          + ":description, "
          + ":publishedAt, "
          + ":youtubeVideoId, "
          + ":youtubeChannelId, "
          + ":channelName, "
          + ":thumbNailsMetadata )")
  @GetGeneratedKeys("id")
  Long add(@BindBean Video video);

  @SqlUpdate(
      "UPDATE videos "
          + "SET "
          + "title = :title, "
          + "description = :description, "
          + "published_at = :publishedAt, "
          + "youtube_video_id = :youtubeVideoId, "
          + "youtube_channel_id = :youtubeChannelId, "
          + "channel_name = :channelName, "
          + "thumb_nails_metadata = :thumbNailsMetadata "
          + "WHERE id = :id ")
  @GetGeneratedKeys("id")
  Long update(@BindBean Video video);

  @SqlQuery(
      "select id, "
          + "title, "
          + "description, "
          + "published_at, "
          + "youtube_video_id, "
          + "youtube_channel_id, "
          + "channel_name, "
          + "thumb_nails_metadata "
          + "from videos "
          + "where "
          + "id = :id ")
  Optional<Video> findById(@Bind("id") Long id);

  @SqlQuery(
      "select id, "
          + "title, "
          + "description, "
          + "published_at, "
          + "youtube_video_id, "
          + "youtube_channel_id, "
          + "channel_name, "
          + "thumb_nails_metadata "
          + "from videos "
          + "order by published_at desc "
          + "offset :offset limit :pageSize ")
  /**
   * @param offset is non null
   * @param pageSize is non null
   */
  List<Video> getByPageNumberAndPageSize(
      @Bind("offset") Integer offset, @Bind("pageSize") Integer pageSize);

  @SqlQuery(
      "select "
          + "title, "
          + "description, "
          + "published_at, "
          + "youtube_video_id, "
          + "youtube_channel_id, "
          + "channel_name, "
          + "thumb_nails_metadata "
          + "from videos "
          + "where title like :query% "
          + "limit :pageSize ")
  /** @param query is non null */
  List<Video> getVideosByTitleOrDescription(
      @Bind("query") String query, @Bind("pageSize") Integer pageSize);
}
