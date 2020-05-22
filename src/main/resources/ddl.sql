CREATE DATABASE users;

CREATE SEQUENCE public.users_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;

ALTER SEQUENCE public.users_seq OWNER TO postgres;

CREATE TABLE public.users(
	code bigint NOT NULL DEFAULT nextval('public.users_seq'::regclass),
	username varchar NOT NULL,
	password varchar NOT NULL,
	enabled boolean NOT NULL,
	attempt integer NOT NULL,
	name varchar NOT NULL,
	first_surname varchar NOT NULL,
	second_surname varchar,
	email varchar NOT NULL,
	birth_date date NOT NULL,
	registration_date timestamp NOT NULL,
	termination_date timestamp,
	role_code bigint NOT NULL,
	CONSTRAINT pk_users PRIMARY KEY (code)

);

ALTER TABLE public.users OWNER TO postgres;

CREATE SEQUENCE public.roles_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;

ALTER SEQUENCE public.roles_seq OWNER TO postgres;

CREATE TABLE public.roles(
	code bigint NOT NULL DEFAULT nextval('public.roles_seq'::regclass),
	name varchar NOT NULL,
	CONSTRAINT pk_roles PRIMARY KEY (code),
	CONSTRAINT unique_roles_name UNIQUE (name)

);

ALTER TABLE public.roles OWNER TO postgres;

ALTER TABLE public.users ADD CONSTRAINT fk_users_roles FOREIGN KEY (role_code)
REFERENCES public.roles (code) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;