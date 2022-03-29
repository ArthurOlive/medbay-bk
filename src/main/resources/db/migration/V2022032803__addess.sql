DROP SEQUENCE IF EXISTS public.address_seq;
CREATE SEQUENCE public.address_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

DROP TABLE IF EXISTS public.address;
CREATE TABLE public.address (
    id bigint primary key,
    cep varchar(255),
    country varchar(255),
    uf varchar(10),
    city varchar(255),
    street varchar(255),
    number varchar(255),
    complement varchar(255),
    created_at timestamp without time zone
);