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
	name varchar NOT NULL,
	first_surname varchar NOT NULL,
	second_surname varchar,
	email varchar NOT NULL,
	password varchar NOT NULL,
	birth_date date NOT NULL,
	registration_date timestamp NOT NULL,
	termination_date timestamp,
	CONSTRAINT users_pk PRIMARY KEY (code)
);

ALTER TABLE public.users OWNER TO postgres;