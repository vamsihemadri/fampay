package com.rivigo.zoom.datastore.model;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Schedule extends AbstractModel {

  @NotBlank private String name;

  @NonNull private String lastSuccessfulRunRFCTime;
}
