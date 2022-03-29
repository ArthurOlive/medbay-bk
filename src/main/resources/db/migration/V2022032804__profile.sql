DROP SEQUENCE IF EXISTS public.profile_seq;
CREATE SEQUENCE public.profile_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

DROP TABLE IF EXISTS public.profile;
CREATE TABLE public.profile (
    id bigint primary key,
    name varchar(255),
    birth date,
    document varchar(255) unique,
    address_id bigint,
    gender int,
    created_at timestamp without time zone,
    CONSTRAINT fk_address_id FOREIGN KEY (address_id)
        REFERENCES public.address (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION 
);