DROP SEQUENCE IF EXISTS public.doctor_seq;
CREATE SEQUENCE public.doctor_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.doctor
(
    id bigint NOT NULL,
    created_at timestamp without time zone,
    crm character varying(255),
    user_id bigint,
    CONSTRAINT doctor_pkey PRIMARY KEY (id),
    CONSTRAINT fk8wmyu7q9fp17ffcdfwibfjsv4 FOREIGN KEY (user_id)
        REFERENCES public.user_system (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)