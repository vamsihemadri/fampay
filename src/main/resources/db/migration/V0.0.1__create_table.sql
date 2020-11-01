CREATE TABLE micro_market (
    "id" serial NOT NULL,
    "code" character varying(255) NOT NULL,
    "name" character varying(255) NOT NULL,
    "pin_codes" integer[] NOT NULL,
    "created_at" bigint NOT NULL,
    "last_updated_at" bigint NOT NULL,
    "created_by_id" integer NOT NULL,
    "last_updated_by_id" integer NOT NULL,
    "is_active" boolean NOT NULL,
    CONSTRAINT "micro_market_id" PRIMARY KEY ("id"),
    CONSTRAINT "micro_market_code" UNIQUE ("code")
);