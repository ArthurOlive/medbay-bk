DROP SEQUENCE IF EXISTS public.auth_seq;
CREATE SEQUENCE public.auth_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

DROP TABLE IF EXISTS public.auth;
CREATE TABLE public.auth (
    id bigint primary key,
    username varchar(255) unique,
    password varchar(255),
    created_at timestamp without time zone
)