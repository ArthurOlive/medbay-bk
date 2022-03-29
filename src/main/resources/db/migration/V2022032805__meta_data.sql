DROP SEQUENCE IF EXISTS public.meta_data_seq;
CREATE SEQUENCE public.meta_data_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

DROP TABLE IF EXISTS public.meta_data;
CREATE TABLE public.meta_data (
    id bigint primary key,
    key varchar(255),
    value varchar(255),
    profile_id bigint,
    created_at timestamp without time zone,
    CONSTRAINT fk_profile FOREIGN KEY (profile_id)
        REFERENCES public.profile (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION 
);