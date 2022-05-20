DROP SEQUENCE IF EXISTS public.consultation_seq;
CREATE SEQUENCE public.consultation_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

DROP TABLE IF EXISTS public.consultation;
CREATE TABLE public.consultation(
    id bigint NOT NULL,
    created_at timestamp without time zone,
    doctor_id bigint NOT NULL,
    patient_id bigint NOT NULL,
    consultation_date timestamp without time zone,
    consultation_return_id bigint,
    status int,
    scheduled timestamp without time zone,
    CONSTRAINT consultation_pkey PRIMARY KEY (id),
    CONSTRAINT fkridmkdiwcnlkowsj5efef76vf FOREIGN KEY (doctor_id)
        REFERENCES public.doctor (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk9gravmkelkm7k8l7s6frd22vd FOREIGN KEY (patient_id)
    REFERENCES public.user_system (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk3cmshetkcpl3g4u1s6ccs67cm FOREIGN KEY (consultation_return_id)
    REFERENCES public.consultation (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);