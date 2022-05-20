DROP TABLE IF EXISTS public.observation;
CREATE TABLE public.observation(
    id bigint NOT NULL UNIQUE GENERATED ALWAYS AS IDENTITY,
    observations character varying(255),
    consultation_id bigint,
    CONSTRAINT observation_pkey PRIMARY KEY (id),
    CONSTRAINT fkxmskdoeifnsklsri7bjst73hy FOREIGN KEY (consultation_id)
        REFERENCES public.consultation (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);