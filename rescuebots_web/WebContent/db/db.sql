-- Table: public.tracker

-- DROP TABLE public.tracker;

--UPDATE public.tracker t
--   SET geom=ST_SetSRID(ST_MakePoint(cast(t.longitude as double precision), cast(t.latitude as double precision)), 4326);

--delete from public.tracker;
--select * from public.tracker
--SELECT latitude, longitude,servertime from tracker order by servertime desc limit 1 
CREATE SEQUENCE public.tracker_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE public.tracker
(
  accuracy character varying,
  altitude character varying,
  bearing character varying,
  latitude character varying,
  longitude character varying,
  provider character varying,
  speed character varying,
  "time" character varying,
  id integer NOT NULL DEFAULT nextval('tracker_id_seq'::regclass),
  found character varying,
  servertime timestamp without time zone DEFAULT now(),
  robot_id character varying,
  
  angle character varying,
  direction character varying,
  distance character varying,
  diference character varying,
  index character varying,
  lastmessage character varying,
  foundsucess character varying,
  
  geom geometry(Point,4326),
  CONSTRAINT tracker_pkey1 PRIMARY KEY (id)
)
CREATE SEQUENCE public.route_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE TABLE public.route
(
  latitude character varying,
  longitude character varying,
  id integer NOT NULL DEFAULT nextval('route_id_seq'::regclass),
  found character varying,
  servertime timestamp without time zone DEFAULT now(),
  robot_id character varying,
  geom geometry(Point,4326),
  CONSTRAINT route_pkey PRIMARY KEY (id)
)

CREATE TABLE public.robot
(
  id character varying,
  servertime timestamp without time zone DEFAULT now(),
  CONSTRAINT tracker_pkey PRIMARY KEY (id)
)
ALTER TABLE public.route ADD CONSTRAINT route_robot_id_fk FOREIGN KEY (robot_id) REFERENCES robot (id) MATCH FULL;
ALTER TABLE public.tracker ADD CONSTRAINT tracker_robot_id_fk FOREIGN KEY (robot_id) REFERENCES robot (id) MATCH FULL;



