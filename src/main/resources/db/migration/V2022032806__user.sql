DROP SEQUENCE IF EXISTS public.user_seq;
CREATE SEQUENCE public.user_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

DROP TABLE IF EXISTS public.user;
CREATE TABLE public.user (
    id bigint primary key,
    auth_id bigint,
    profile_id bigint,
    role_id bigint,
    created_at timestamp without time zone,
    CONSTRAINT fk_auth_id FOREIGN KEY (auth_id)
        REFERENCES public.auth (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_profile_id FOREIGN KEY (profile_id)
        REFERENCES public.profile (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_role_id FOREIGN KEY (role_id)
        REFERENCES public.role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION 
);