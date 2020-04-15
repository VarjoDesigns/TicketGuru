-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.1
-- PostgreSQL version: 10.0
-- Project Site: pgmodeler.io
-- Model Author: ---


-- Database creation must be done outside a multicommand file.
-- These commands were put in this file only as a convenience.
-- -- object: d9havdvk30dam9 | type: DATABASE --
-- -- DROP DATABASE IF EXISTS d9havdvk30dam9;
-- CREATE DATABASE d9havdvk30dam9;
-- -- ddl-end --
-- 

-- object: public.user_groups | type: TABLE --
-- DROP TABLE IF EXISTS public.user_groups CASCADE;
CREATE TABLE public.user_groups(
	user_group_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
	name varchar(100) NOT NULL,
	created timestamp NOT NULL DEFAULT NOW(),
	invalid timestamp,
	CONSTRAINT user_groups_pk PRIMARY KEY (user_group_id)

);
-- ddl-end --

-- object: public.users | type: TABLE --
-- DROP TABLE IF EXISTS public.users CASCADE;
CREATE TABLE public.users(
	user_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
	password varchar(250) NOT NULL,
	name varchar(100),
	username varchar(50) NOT NULL,
	user_group_id bigint NOT NULL,
	active bool NOT NULL,
	created timestamp NOT NULL DEFAULT NOW(),
	invalid timestamp,
	CONSTRAINT users_pk PRIMARY KEY (user_id)

);
-- ddl-end --

-- object: public.postcodes | type: TABLE --
-- DROP TABLE IF EXISTS public.postcodes CASCADE;
CREATE TABLE public.postcodes(
	postcode_id varchar(20) NOT NULL,
	city varchar(100) NOT NULL,
	country varchar(100) NOT NULL,
	created timestamp NOT NULL DEFAULT NOW(),
	invalid timestamp,
	CONSTRAINT postcodes_pk PRIMARY KEY (postcode_id)

);
-- ddl-end --

-- object: public.event_types | type: TABLE --
-- DROP TABLE IF EXISTS public.event_types CASCADE;
CREATE TABLE public.event_types(
	event_type_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
	name varchar(100) NOT NULL,
	info varchar(500),
	created timestamp NOT NULL DEFAULT NOW(),
	invalid timestamp,
	CONSTRAINT event_types_pk PRIMARY KEY (event_type_id)

);
-- ddl-end --

-- object: public.age_limits | type: TABLE --
-- DROP TABLE IF EXISTS public.age_limits CASCADE;
CREATE TABLE public.age_limits(
	age_limit_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
	name varchar(50) NOT NULL,
	info varchar(500),
	created timestamp NOT NULL DEFAULT NOW(),
	invalid timestamp,
	CONSTRAINT age_limits_pk PRIMARY KEY (age_limit_id)

);
-- ddl-end --

-- object: public.ticket_statuses | type: TABLE --
-- DROP TABLE IF EXISTS public.ticket_statuses CASCADE;
CREATE TABLE public.ticket_statuses(
	ticket_status_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
	name varchar(50) NOT NULL,
	created timestamp NOT NULL DEFAULT NOW(),
	invalid timestamp,
	CONSTRAINT ticket_statuses_pk PRIMARY KEY (ticket_status_id)

);
-- ddl-end --

-- object: public.ticket_types | type: TABLE --
-- DROP TABLE IF EXISTS public.ticket_types CASCADE;
CREATE TABLE public.ticket_types(
	ticket_type_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
	name varchar(100) NOT NULL,
	created timestamp NOT NULL DEFAULT NOW(),
	invalid timestamp,
	CONSTRAINT ticket_types_pk PRIMARY KEY (ticket_type_id)

);
-- ddl-end --

-- object: public.event_organizers | type: TABLE --
-- DROP TABLE IF EXISTS public.event_organizers CASCADE;
CREATE TABLE public.event_organizers(
	event_organizer_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
	name varchar(100) NOT NULL,
	street_address varchar(150),
	postcode_id varchar(20) NOT NULL,
	tel varchar(25),
	email varchar(150),
	www varchar(250),
	contact_person varchar(250),
	created timestamp NOT NULL DEFAULT NOW(),
	invalid timestamp,
	CONSTRAINT event_organizers_pk PRIMARY KEY (event_organizer_id)

);
-- ddl-end --

-- object: public.venues | type: TABLE --
-- DROP TABLE IF EXISTS public.venues CASCADE;
CREATE TABLE public.venues(
	venue_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
	name varchar(100) NOT NULL,
	street_address varchar(150) NOT NULL,
	postcode_id varchar(25) NOT NULL,
	tel varchar(25) NOT NULL,
	email varchar(150) NOT NULL,
	www varchar(250),
	contact_person varchar(250) NOT NULL,
	created timestamp NOT NULL DEFAULT NOW(),
	invalid timestamp,
	CONSTRAINT venues_pk PRIMARY KEY (venue_id)

);
-- ddl-end --

-- object: public.events | type: TABLE --
-- DROP TABLE IF EXISTS public.events CASCADE;
CREATE TABLE public.events(
	event_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
	name varchar(250) NOT NULL,
	event_type_id bigint NOT NULL,
	datetime timestamp NOT NULL,
	event_organizer_id bigint NOT NULL,
	venue_id bigint NOT NULL,
	ticket_capacity bigint NOT NULL,
	age_limit_id bigint NOT NULL,
	info varchar(500),
	created timestamp NOT NULL DEFAULT NOW(),
	invalid timestamp,
	CONSTRAINT events_pk PRIMARY KEY (event_id)

);
-- ddl-end --
ALTER TABLE public.events OWNER TO postgres;
-- ddl-end --

-- object: public.event_tickets | type: TABLE --
-- DROP TABLE IF EXISTS public.event_tickets CASCADE;
CREATE TABLE public.event_tickets(
	event_ticket_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
	event_id bigint NOT NULL,
	ticket_type_id bigint NOT NULL,
	ticket_count bigint,
	price decimal(5),
	created timestamp NOT NULL DEFAULT NOW(),
	invalid timestamp,
	CONSTRAINT event_tickets_pk PRIMARY KEY (event_ticket_id)

);
-- ddl-end --

-- object: public.tickets | type: TABLE --
-- DROP TABLE IF EXISTS public.tickets CASCADE;
CREATE TABLE public.tickets(
	ticket_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
	event_ticket_id bigint NOT NULL,
	ticket_status_id bigint NOT NULL,
	checksum varchar(100) NOT NULL,
	sale_row_id bigint NOT NULL,
	created timestamp NOT NULL DEFAULT NOW(),
	invalid timestamp,
	CONSTRAINT tickets_pk PRIMARY KEY (ticket_id)

);
-- ddl-end --

-- object: public.sale_events | type: TABLE --
-- DROP TABLE IF EXISTS public.sale_events CASCADE;
CREATE TABLE public.sale_events(
	sale_event_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
	user_id bigint NOT NULL,
	created timestamp NOT NULL DEFAULT NOW(),
	invalid timestamp,
	CONSTRAINT sale_events_pk PRIMARY KEY (sale_event_id)

);
-- ddl-end --

-- object: public.sale_rows | type: TABLE --
-- DROP TABLE IF EXISTS public.sale_rows CASCADE;
CREATE TABLE public.sale_rows(
	sale_row_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
	sale_event_id bigint NOT NULL,
	discount smallint,
	created timestamp NOT NULL DEFAULT NOW(),
	invalid timestamp,
	CONSTRAINT sale_rows_pk PRIMARY KEY (sale_row_id)

);
-- ddl-end --

-- object: fk_user_group_id | type: CONSTRAINT --
-- ALTER TABLE public.users DROP CONSTRAINT IF EXISTS fk_user_group_id CASCADE;
ALTER TABLE public.users ADD CONSTRAINT fk_user_group_id FOREIGN KEY (user_group_id)
REFERENCES public.user_groups (user_group_id) MATCH FULL
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: "fk_postcode_ID" | type: CONSTRAINT --
-- ALTER TABLE public.event_organizers DROP CONSTRAINT IF EXISTS "fk_postcode_ID" CASCADE;
ALTER TABLE public.event_organizers ADD CONSTRAINT "fk_postcode_ID" FOREIGN KEY (postcode_id)
REFERENCES public.postcodes (postcode_id) MATCH FULL
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_postcode_id | type: CONSTRAINT --
-- ALTER TABLE public.venues DROP CONSTRAINT IF EXISTS fk_postcode_id CASCADE;
ALTER TABLE public.venues ADD CONSTRAINT fk_postcode_id FOREIGN KEY (postcode_id)
REFERENCES public.postcodes (postcode_id) MATCH FULL
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_event_type_id | type: CONSTRAINT --
-- ALTER TABLE public.events DROP CONSTRAINT IF EXISTS fk_event_type_id CASCADE;
ALTER TABLE public.events ADD CONSTRAINT fk_event_type_id FOREIGN KEY (event_type_id)
REFERENCES public.event_types (event_type_id) MATCH FULL
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_event_organizer_id | type: CONSTRAINT --
-- ALTER TABLE public.events DROP CONSTRAINT IF EXISTS fk_event_organizer_id CASCADE;
ALTER TABLE public.events ADD CONSTRAINT fk_event_organizer_id FOREIGN KEY (event_organizer_id)
REFERENCES public.event_organizers (event_organizer_id) MATCH FULL
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_venue_id | type: CONSTRAINT --
-- ALTER TABLE public.events DROP CONSTRAINT IF EXISTS fk_venue_id CASCADE;
ALTER TABLE public.events ADD CONSTRAINT fk_venue_id FOREIGN KEY (venue_id)
REFERENCES public.venues (venue_id) MATCH FULL
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_age_limit_id | type: CONSTRAINT --
-- ALTER TABLE public.events DROP CONSTRAINT IF EXISTS fk_age_limit_id CASCADE;
ALTER TABLE public.events ADD CONSTRAINT fk_age_limit_id FOREIGN KEY (age_limit_id)
REFERENCES public.age_limits (age_limit_id) MATCH FULL
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_event_id | type: CONSTRAINT --
-- ALTER TABLE public.event_tickets DROP CONSTRAINT IF EXISTS fk_event_id CASCADE;
ALTER TABLE public.event_tickets ADD CONSTRAINT fk_event_id FOREIGN KEY (event_id)
REFERENCES public.events (event_id) MATCH FULL
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_ticket_type_id | type: CONSTRAINT --
-- ALTER TABLE public.event_tickets DROP CONSTRAINT IF EXISTS fk_ticket_type_id CASCADE;
ALTER TABLE public.event_tickets ADD CONSTRAINT fk_ticket_type_id FOREIGN KEY (ticket_type_id)
REFERENCES public.ticket_types (ticket_type_id) MATCH FULL
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_event_ticket_id | type: CONSTRAINT --
-- ALTER TABLE public.tickets DROP CONSTRAINT IF EXISTS fk_event_ticket_id CASCADE;
ALTER TABLE public.tickets ADD CONSTRAINT fk_event_ticket_id FOREIGN KEY (event_ticket_id)
REFERENCES public.event_tickets (event_ticket_id) MATCH FULL
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_ticket_status_id | type: CONSTRAINT --
-- ALTER TABLE public.tickets DROP CONSTRAINT IF EXISTS fk_ticket_status_id CASCADE;
ALTER TABLE public.tickets ADD CONSTRAINT fk_ticket_status_id FOREIGN KEY (ticket_status_id)
REFERENCES public.ticket_statuses (ticket_status_id) MATCH FULL
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_sale_row_id | type: CONSTRAINT --
-- ALTER TABLE public.tickets DROP CONSTRAINT IF EXISTS fk_sale_row_id CASCADE;
ALTER TABLE public.tickets ADD CONSTRAINT fk_sale_row_id FOREIGN KEY (sale_row_id)
REFERENCES public.sale_rows (sale_row_id) MATCH FULL
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_user_id | type: CONSTRAINT --
-- ALTER TABLE public.sale_events DROP CONSTRAINT IF EXISTS fk_user_id CASCADE;
ALTER TABLE public.sale_events ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id)
REFERENCES public.users (user_id) MATCH FULL
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_sale_event_id | type: CONSTRAINT --
-- ALTER TABLE public.sale_rows DROP CONSTRAINT IF EXISTS fk_sale_event_id CASCADE;
ALTER TABLE public.sale_rows ADD CONSTRAINT fk_sale_event_id FOREIGN KEY (sale_event_id)
REFERENCES public.sale_events (sale_event_id) MATCH FULL
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --


