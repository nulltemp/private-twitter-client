drop table public.system_users;
drop sequence public.system_users_id_seq;

create sequence public.system_users_id_seq
    increment 1
    start 1
    minvalue 1
    maxvalue 9223372036854775807
    cache 1;

create TABLE public.system_users
(
    id integer NOT NULL DEFAULT nextval('system_users_id_seq'::regclass),
    email character varying(256) COLLATE pg_catalog."default" NOT NULL,
    password character(60) COLLATE pg_catalog."default" NOT NULL,
    token character(64) COLLATE pg_catalog."default",
    created_at timestamp with time zone NOT NULL,
    updated_at timestamp with time zone NOT NULL,
    deleted_at timestamp with time zone,
    CONSTRAINT system_users_pkey PRIMARY KEY (id),
    CONSTRAINT system_users_email_deleted_at_key UNIQUE (email, deleted_at),
    CONSTRAINT system_users_token_key UNIQUE (token)
);

insert into public.system_users(email, password, created_at, updated_at)
    values('${admin.email}', crypt('${admin.password}', gen_salt('bf', 10)), now(), now());