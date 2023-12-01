-- V1__create_table_users.sql

CREATE TABLE users
(
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    role character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY (email),
    CONSTRAINT users_role_check CHECK (role::text = ANY (ARRAY['ADMIN'::character varying, 'DEFAULT'::character varying]::text[]))
    );
