CREATE SCHEMA samplesf;

-- ............................................................................

GRANT USAGE ON SCHEMA samplesf to samplesf;

-- ............................................................................

SET search_path TO samplesf;

CREATE TABLE principal
(
  id 							bigserial NOT NULL,
  username 						character varying(128) NOT NULL unique ,
  password 						character varying(128),
  first_name 					character varying(128),
  last_name 					character varying(128),
  createdby_id                  bigint,
  created_date                  timestamp without time zone,
  updatedby_id                  bigint,
  updated_date                  timestamp without time zone,
  CONSTRAINT principal_pkey PRIMARY KEY (id )
);

GRANT ALL PRIVILEGES ON principal to samplesf;
GRANT ALL PRIVILEGES ON principal_id_seq to samplesf;

CREATE TABLE socialdentity
(
  id 							bigserial NOT NULL,
  user_id 						bigint NOT NULL,
  token 						character varying(256),
  token_date 					timestamp without time zone,
  service 						character varying(256),
  createdby_id                  bigint,
  created_date                  timestamp without time zone,
  updatedby_id                  bigint,
  updated_date                  timestamp without time zone,
  CONSTRAINT socialdentity_pkey PRIMARY KEY (id )
);

GRANT ALL PRIVILEGES ON socialdentity to samplesf;
GRANT ALL PRIVILEGES ON socialdentity_id_seq to samplesf;