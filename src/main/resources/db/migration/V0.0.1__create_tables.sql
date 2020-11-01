CREATE TABLE videos (
    "id" serial NOT NULL,
    "description" character varying(10485759),
    "title" character varying(65535) NOT NULL,
    "youtube_video_id" character varying(65535) NOT NULL,
    "published_at" bigint NOT NULL,
    "youtube_channel_id" character varying(65535),
    "channel_name" character varying(65535),
    "thumb_nails_metadata" character varying(10485759),
    CONSTRAINT "video_id" PRIMARY KEY ("id"),
    CONSTRAINT "youtubeVideoId" UNIQUE ("youtube_video_id")
);

CREATE TABLE schedules (
    "id" serial NOT NULL,
    "last_successful_run_rfc_time" character varying(255) NOT NULL,
    "name" character varying(2047) NOT NULL,
    CONSTRAINT "schedule_id" PRIMARY KEY ("id"),
    CONSTRAINT "schedule_name" UNIQUE ("name")
);

INSERT INTO "schedules"
("name","last_successful_run_rfc_time")
VALUES ('youtube_video_schedule','2020-11-01T07:18:21+00:0');