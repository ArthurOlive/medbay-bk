DROP SEQUENCE IF EXISTS public.role_seq;
CREATE SEQUENCE public.role_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

DROP TABLE IF EXISTS public.role;
CREATE TABLE public.role (
    id bigint primary key,
    name varchar(255) unique
);