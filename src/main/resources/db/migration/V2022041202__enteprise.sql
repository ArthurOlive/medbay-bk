DROP SEQUENCE IF EXISTS public.enteprise_seq;
CREATE SEQUENCE public.enteprise_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.enteprise
(
    id bigint NOT NULL,
    cnpj character varying(255),
    created_at timestamp without time zone,
    name character varying(255),
    address_id bigint,
    CONSTRAINT enteprise_pkey PRIMARY KEY (id),
    CONSTRAINT fkotywmosrwfhdkcvu1ayof44ln FOREIGN KEY (address_id)
        REFERENCES public.address (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.enteprise_doctors
(
    enteprise_id bigint NOT NULL,
    doctors_id bigint NOT NULL,
    CONSTRAINT fk4vasx9gltcl4gttb6hnsn08h6 FOREIGN KEY (doctors_id)
        REFERENCES public.doctor (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkpbnepj12boqq672wxfnhefa8w FOREIGN KEY (enteprise_id)
        REFERENCES public.enteprise (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.enteprise_employees
(
    enteprise_id bigint NOT NULL,
    employees_id bigint NOT NULL,
    CONSTRAINT fk7i590xhkack1s1av5ipr83wmn FOREIGN KEY (enteprise_id)
        REFERENCES public.enteprise (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk7lio8fyds0hllnsptnfopecoc FOREIGN KEY (employees_id)
        REFERENCES public.user_system (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.enteprise_patients
(
    enteprise_id bigint NOT NULL,
    patients_id bigint NOT NULL,
    CONSTRAINT fk7cludgjfeiim9x2p3s3axo40y FOREIGN KEY (patients_id)
        REFERENCES public.user_system (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkar9n1e77i2jpp6agbhhdgx3px FOREIGN KEY (enteprise_id)
        REFERENCES public.enteprise (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);