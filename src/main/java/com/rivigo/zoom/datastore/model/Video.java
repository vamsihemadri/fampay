package com.rivigo.zoom.datastore.model;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Video extends AbstractModel {

  @NotBlank private String title;

  private String description;

  // in epoch
  private Long publishedAt;

  // the video id according to youtube api.
  @NotBlank private String youtubeVideoId;

  private String thumbNailsMetadata;
}
