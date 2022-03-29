DROP SEQUENCE IF EXISTS public.privilege_seq;
CREATE SEQUENCE public.privilege_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

DROP TABLE IF EXISTS public.privilege;
CREATE TABLE public.privilege (
    id bigint primary key,
    name varchar(255) unique,
    created_at timestamp without time zone
);