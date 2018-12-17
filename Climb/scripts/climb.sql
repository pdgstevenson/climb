-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.8.2
-- PostgreSQL version: 9.5
-- Project Site: pgmodeler.com.br
-- Model Author: ---

-- object: climber | type: ROLE --
-- DROP ROLE IF EXISTS climber;
CREATE ROLE climber WITH 
	CREATEDB;
-- ddl-end --


-- Database creation must be done outside an multicommand file.
-- These commands were put in this file only for convenience.
-- -- object: new_database | type: DATABASE --
-- -- DROP DATABASE IF EXISTS new_database;
-- CREATE DATABASE new_database
-- ;
-- -- ddl-end --
-- 

-- object: public.centre | type: TABLE --
-- DROP TABLE IF EXISTS public.centre CASCADE;
CREATE TABLE public.centre(
	centre_id serial NOT NULL,
	name varchar(64) NOT NULL,
	CONSTRAINT centre_pk PRIMARY KEY (centre_id),
	CONSTRAINT centre_name_unq UNIQUE (name)

);
-- ddl-end --
ALTER TABLE public.centre OWNER TO climber;
-- ddl-end --

-- object: public.room | type: TABLE --
-- DROP TABLE IF EXISTS public.room CASCADE;
CREATE TABLE public.room(
	room_id serial NOT NULL,
	centre_id integer NOT NULL,
	name varchar(64) NOT NULL,
	CONSTRAINT room_fk PRIMARY KEY (room_id),
	CONSTRAINT name_unq_cons UNIQUE (name)

);
-- ddl-end --
ALTER TABLE public.room OWNER TO climber;
-- ddl-end --

-- object: public.wall | type: TABLE --
-- DROP TABLE IF EXISTS public.wall CASCADE;
CREATE TABLE public.wall(
	wall_id serial NOT NULL,
	room_id integer NOT NULL,
	num smallint NOT NULL,
	orientation varchar(32) NOT NULL,
	name varchar(32),
	description varchar(512),
	width_base integer,
	width_top integer,
	height_left integer,
	height_right integer,
	z_left integer,
	z_right integer,
	CONSTRAINT wall_pk PRIMARY KEY (wall_id)

);
-- ddl-end --
ALTER TABLE public.wall OWNER TO climber;
-- ddl-end --

-- object: public.mark | type: TABLE --
-- DROP TABLE IF EXISTS public.mark CASCADE;
CREATE TABLE public.mark(
	mark_id serial NOT NULL,
	wall_id integer NOT NULL,
	x integer NOT NULL,
	y integer NOT NULL,
	z integer NOT NULL,
	CONSTRAINT mark_pk PRIMARY KEY (mark_id)

);
-- ddl-end --
ALTER TABLE public.mark OWNER TO climber;
-- ddl-end --

-- object: public.route | type: TABLE --
-- DROP TABLE IF EXISTS public.route CASCADE;
CREATE TABLE public.route(
	route_id serial NOT NULL,
	mark_id integer NOT NULL,
	route_grade_id smallint NOT NULL,
	length integer NOT NULL,
	setter_id integer NOT NULL,
	colour char(7) NOT NULL,
	installation_date date NOT NULL,
	active boolean NOT NULL,
	natural_feature_rule_id smallint NOT NULL,
	CONSTRAINT route_pk PRIMARY KEY (route_id)

);
-- ddl-end --
ALTER TABLE public.route OWNER TO climber;
-- ddl-end --

-- object: public.hold | type: TABLE --
-- DROP TABLE IF EXISTS public.hold CASCADE;
CREATE TABLE public.hold(
	hold_id serial NOT NULL,
	colour char(7) NOT NULL,
	hold_type_id smallint NOT NULL,
	width smallint,
	height smallint,
	depth smallint,
	CONSTRAINT hold_pk PRIMARY KEY (hold_id)

);
-- ddl-end --
ALTER TABLE public.hold OWNER TO climber;
-- ddl-end --

-- object: public.move | type: TABLE --
-- DROP TABLE IF EXISTS public.move CASCADE;
CREATE TABLE public.move(
	move_id serial NOT NULL,
	route_ascent_id integer NOT NULL,
	route_hold_id integer,
	natural_feature_id integer,
	smear_id smallint,
	code char(2) NOT NULL,
	"order" smallint NOT NULL,
	style_id smallint NOT NULL,

);
-- ddl-end --
ALTER TABLE public.move OWNER TO climber;
-- ddl-end --

-- object: public.hold_type | type: TABLE --
-- DROP TABLE IF EXISTS public.hold_type CASCADE;
CREATE TABLE public.hold_type(
	hold_type_id smallint NOT NULL,
	type varchar(32) NOT NULL,
	description varchar(512),
	CONSTRAINT hold_type_pk PRIMARY KEY (hold_type_id)

);
-- ddl-end --
ALTER TABLE public.hold_type OWNER TO climber;
-- ddl-end --

-- object: public.route_ascent | type: TABLE --
-- DROP TABLE IF EXISTS public.route_ascent CASCADE;
CREATE TABLE public.route_ascent(
	route_ascent_id serial NOT NULL,
	route_id integer NOT NULL,
	climber_id integer NOT NULL,
	CONSTRAINT climb_algorithm_pk PRIMARY KEY (route_ascent_id)

);
-- ddl-end --
ALTER TABLE public.route_ascent OWNER TO climber;
-- ddl-end --

-- object: public.climber | type: TABLE --
-- DROP TABLE IF EXISTS public.climber CASCADE;
CREATE TABLE public.climber(
	climber_id integer NOT NULL,
	height integer NOT NULL,
	ability smallint NOT NULL,
	CONSTRAINT climber_pk PRIMARY KEY (climber_id)

);
-- ddl-end --
ALTER TABLE public.climber OWNER TO climber;
-- ddl-end --

-- object: public.route_grade | type: TABLE --
-- DROP TABLE IF EXISTS public.route_grade CASCADE;
CREATE TABLE public.route_grade(
	route_grade_id smallint NOT NULL,
	sport_grade varchar(3) NOT NULL,
	CONSTRAINT grade_pk PRIMARY KEY (route_grade_id)

);
-- ddl-end --
ALTER TABLE public.route_grade OWNER TO climber;
-- ddl-end --

-- object: public.natural_feature | type: TABLE --
-- DROP TABLE IF EXISTS public.natural_feature CASCADE;
CREATE TABLE public.natural_feature(
	natural_feature_id serial NOT NULL,
	wall_id integer NOT NULL,
	x integer NOT NULL,
	y integer NOT NULL,
	z integer NOT NULL,
	CONSTRAINT natural_pk PRIMARY KEY (natural_feature_id)

);
-- ddl-end --
ALTER TABLE public.natural_feature OWNER TO climber;
-- ddl-end --

-- object: public.screw_thread | type: TABLE --
-- DROP TABLE IF EXISTS public.screw_thread CASCADE;
CREATE TABLE public.screw_thread(
	screw_thread_id serial NOT NULL,
	wall_id integer NOT NULL,
	x integer NOT NULL,
	y integer NOT NULL,
	z integer NOT NULL,
	CONSTRAINT thread_pk PRIMARY KEY (screw_thread_id)

);
-- ddl-end --
ALTER TABLE public.screw_thread OWNER TO climber;
-- ddl-end --

-- object: public.route_hold | type: TABLE --
-- DROP TABLE IF EXISTS public.route_hold CASCADE;
CREATE TABLE public.route_hold(
	route_hold_id serial NOT NULL,
	screw_thread_id integer NOT NULL,
	hold_id integer NOT NULL,
	route_id integer NOT NULL,
	intended_use char NOT NULL,
	orientation smallint NOT NULL,
	CONSTRAINT wall_hold_pk PRIMARY KEY (route_hold_id)

);
-- ddl-end --
ALTER TABLE public.route_hold OWNER TO climber;
-- ddl-end --

-- object: public.smear | type: TABLE --
-- DROP TABLE IF EXISTS public.smear CASCADE;
CREATE TABLE public.smear(
	smear_id smallint NOT NULL,
	type varchar(32) NOT NULL,
	description varchar(256) NOT NULL,

);
-- ddl-end --
ALTER TABLE public.smear OWNER TO climber;
-- ddl-end --

-- object: public.style | type: TABLE --
-- DROP TABLE IF EXISTS public.style CASCADE;
CREATE TABLE public.style(
	style_id smallint NOT NULL,
	type varchar(16) NOT NULL,
	CONSTRAINT style_pk PRIMARY KEY (style_id)

);
-- ddl-end --
ALTER TABLE public.style OWNER TO climber;
-- ddl-end --

-- object: public.natural_feature_rule | type: TABLE --
-- DROP TABLE IF EXISTS public.natural_feature_rule CASCADE;
CREATE TABLE public.natural_feature_rule(
	natural_feature_rule_id smallint NOT NULL,
	type varchar(16) NOT NULL,
	CONSTRAINT natural_feature_rule_pk PRIMARY KEY (natural_feature_rule_id)

);
-- ddl-end --
ALTER TABLE public.natural_feature_rule OWNER TO climber;
-- ddl-end --

-- object: room_centre_fk | type: CONSTRAINT --
-- ALTER TABLE public.room DROP CONSTRAINT IF EXISTS room_centre_fk CASCADE;
ALTER TABLE public.room ADD CONSTRAINT room_centre_fk FOREIGN KEY (centre_id)
REFERENCES public.centre (centre_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: wall_room_fk | type: CONSTRAINT --
-- ALTER TABLE public.wall DROP CONSTRAINT IF EXISTS wall_room_fk CASCADE;
ALTER TABLE public.wall ADD CONSTRAINT wall_room_fk FOREIGN KEY (room_id)
REFERENCES public.room (room_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: mark_wall_fk | type: CONSTRAINT --
-- ALTER TABLE public.mark DROP CONSTRAINT IF EXISTS mark_wall_fk CASCADE;
ALTER TABLE public.mark ADD CONSTRAINT mark_wall_fk FOREIGN KEY (wall_id)
REFERENCES public.wall (wall_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: route_mark_fk | type: CONSTRAINT --
-- ALTER TABLE public.route DROP CONSTRAINT IF EXISTS route_mark_fk CASCADE;
ALTER TABLE public.route ADD CONSTRAINT route_mark_fk FOREIGN KEY (mark_id)
REFERENCES public.mark (mark_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: route_grade_fk | type: CONSTRAINT --
-- ALTER TABLE public.route DROP CONSTRAINT IF EXISTS route_grade_fk CASCADE;
ALTER TABLE public.route ADD CONSTRAINT route_grade_fk FOREIGN KEY (route_grade_id)
REFERENCES public.route_grade (route_grade_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: route_setter_fk | type: CONSTRAINT --
-- ALTER TABLE public.route DROP CONSTRAINT IF EXISTS route_setter_fk CASCADE;
ALTER TABLE public.route ADD CONSTRAINT route_setter_fk FOREIGN KEY (setter_id)
REFERENCES public.climber (climber_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: route_rule_fk | type: CONSTRAINT --
-- ALTER TABLE public.route DROP CONSTRAINT IF EXISTS route_rule_fk CASCADE;
ALTER TABLE public.route ADD CONSTRAINT route_rule_fk FOREIGN KEY (natural_feature_rule_id)
REFERENCES public.natural_feature_rule (natural_feature_rule_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: hold_type_fk | type: CONSTRAINT --
-- ALTER TABLE public.hold DROP CONSTRAINT IF EXISTS hold_type_fk CASCADE;
ALTER TABLE public.hold ADD CONSTRAINT hold_type_fk FOREIGN KEY (hold_type_id)
REFERENCES public.hold_type (hold_type_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: move_algorith_fk | type: CONSTRAINT --
-- ALTER TABLE public.move DROP CONSTRAINT IF EXISTS move_algorith_fk CASCADE;
ALTER TABLE public.move ADD CONSTRAINT move_algorith_fk FOREIGN KEY (route_ascent_id)
REFERENCES public.route_ascent (route_ascent_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: move_route_hold_fk | type: CONSTRAINT --
-- ALTER TABLE public.move DROP CONSTRAINT IF EXISTS move_route_hold_fk CASCADE;
ALTER TABLE public.move ADD CONSTRAINT move_route_hold_fk FOREIGN KEY (route_hold_id)
REFERENCES public.route_hold (route_hold_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: move_natural_fk | type: CONSTRAINT --
-- ALTER TABLE public.move DROP CONSTRAINT IF EXISTS move_natural_fk CASCADE;
ALTER TABLE public.move ADD CONSTRAINT move_natural_fk FOREIGN KEY (natural_feature_id)
REFERENCES public.natural_feature (natural_feature_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: move_smear_fk | type: CONSTRAINT --
-- ALTER TABLE public.move DROP CONSTRAINT IF EXISTS move_smear_fk CASCADE;
ALTER TABLE public.move ADD CONSTRAINT move_smear_fk FOREIGN KEY (smear_id)
REFERENCES public.smear (smear_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: move_style | type: CONSTRAINT --
-- ALTER TABLE public.move DROP CONSTRAINT IF EXISTS move_style CASCADE;
ALTER TABLE public.move ADD CONSTRAINT move_style FOREIGN KEY (style_id)
REFERENCES public.style (style_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: climb_algorithm | type: CONSTRAINT --
-- ALTER TABLE public.route_ascent DROP CONSTRAINT IF EXISTS climb_algorithm CASCADE;
ALTER TABLE public.route_ascent ADD CONSTRAINT climb_algorithm FOREIGN KEY (route_id)
REFERENCES public.route (route_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: climbed_by | type: CONSTRAINT --
-- ALTER TABLE public.route_ascent DROP CONSTRAINT IF EXISTS climbed_by CASCADE;
ALTER TABLE public.route_ascent ADD CONSTRAINT climbed_by FOREIGN KEY (climber_id)
REFERENCES public.climber (climber_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: climber_grade_fk | type: CONSTRAINT --
-- ALTER TABLE public.climber DROP CONSTRAINT IF EXISTS climber_grade_fk CASCADE;
ALTER TABLE public.climber ADD CONSTRAINT climber_grade_fk FOREIGN KEY (ability)
REFERENCES public.route_grade (route_grade_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: natural_wall_fk | type: CONSTRAINT --
-- ALTER TABLE public.natural_feature DROP CONSTRAINT IF EXISTS natural_wall_fk CASCADE;
ALTER TABLE public.natural_feature ADD CONSTRAINT natural_wall_fk FOREIGN KEY (wall_id)
REFERENCES public.wall (wall_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: thread_wall_fk | type: CONSTRAINT --
-- ALTER TABLE public.screw_thread DROP CONSTRAINT IF EXISTS thread_wall_fk CASCADE;
ALTER TABLE public.screw_thread ADD CONSTRAINT thread_wall_fk FOREIGN KEY (wall_id)
REFERENCES public.wall (wall_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: wall_hold_hold_fk | type: CONSTRAINT --
-- ALTER TABLE public.route_hold DROP CONSTRAINT IF EXISTS wall_hold_hold_fk CASCADE;
ALTER TABLE public.route_hold ADD CONSTRAINT wall_hold_hold_fk FOREIGN KEY (hold_id)
REFERENCES public.hold (hold_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: wall_hold_thread_fk | type: CONSTRAINT --
-- ALTER TABLE public.route_hold DROP CONSTRAINT IF EXISTS wall_hold_thread_fk CASCADE;
ALTER TABLE public.route_hold ADD CONSTRAINT wall_hold_thread_fk FOREIGN KEY (screw_thread_id)
REFERENCES public.screw_thread (screw_thread_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: route_hold_route_fk | type: CONSTRAINT --
-- ALTER TABLE public.route_hold DROP CONSTRAINT IF EXISTS route_hold_route_fk CASCADE;
ALTER TABLE public.route_hold ADD CONSTRAINT route_hold_route_fk FOREIGN KEY (route_id)
REFERENCES public.route (route_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --


