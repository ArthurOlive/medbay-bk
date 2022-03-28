DROP TABLE IF EXISTS public.role_privileges;
CREATE TABLE public.role_privileges (
    role_id bigint,
    privileges_id bigint,
    CONSTRAINT fk_role_id FOREIGN KEY (role_id)
        REFERENCES public.role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_privilege_id FOREIGN KEY (privileges_id)
        REFERENCES public.privilege (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION  
);