-- Role: library_user
-- DROP ROLE library_user;

CREATE ROLE library_user WITH
  LOGIN
  NOSUPERUSER
  INHERIT
  CREATEDB
  NOCREATEROLE
  NOREPLICATION;

COMMENT ON ROLE library_user IS 'User responsible for managing library application database.';

-- Database: librarydb

-- DROP DATABASE librarydb;

CREATE DATABASE librarydb
    WITH 
    OWNER = library_user
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_India.1252'
    LC_CTYPE = 'English_India.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE librarydb
    IS 'Database to manage library app schemas';
	
CREATE SCHEMA LIBRARY_SCHEMA;

CREATE TABLE library_schema.USERS
(
    USER_ID serial NOT NULL,
    FIRST_NAME character varying(20) NOT NULL,
    LAST_NAME character varying(20),
    MOBILE_NO character varying(10),
    EMAIL_ID character varying(100),
    STATUS character varying(1) NOT NULL DEFAULT 'A',
    CREATION_DATE timestamp without time zone,
    CREATED_BY character varying(40),
    MODIFICATION_DATE timestamp without time zone,
    MODIFIED_BY character varying(40),
    PRIMARY KEY (USER_ID),
    CONSTRAINT MOBILE_EMAIL_CONSTRAINT CHECK (NOT (MOBILE_NO IS NULL AND EMAIL_ID IS NULL)),
    CONSTRAINT STATUS_TYPE CHECK (STATUS IN ('A','D'))
);

ALTER TABLE library_schema.USERS
    OWNER to library_user;

COMMENT ON TABLE library_schema.USERS
    IS 'It stores user information';
	
	CREATE TABLE library_schema.USERS_CREDENTIALS
(
    USER_ID INTEGER NOT NULL,
    USERNAME character varying(100) NOT NULL UNIQUE,
    PASSWORD character varying(100) NOT NULL,
    PASSWORD_EXPIRY_DATE timestamp without time zone,
    LAST_LOGIN_TIME timestamp without time zone,
    STATUS character varying(1) NOT NULL DEFAULT 'A',
    CREATION_DATE timestamp without time zone,
    CREATED_BY character varying(40),
    MODIFICATION_DATE timestamp without time zone,
    MODIFIED_BY character varying(40),
    CONSTRAINT USER_CREDENTIALS_FK FOREIGN KEY (USER_ID)
        REFERENCES library_schema.USERS (USER_ID) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT STATUS_TYPE CHECK (STATUS IN ('A','D'))
);

ALTER TABLE library_schema.USERS_CREDENTIALS
    OWNER to library_user;
	
	CREATE TABLE library_schema.ROLES
(
    ROLE_ID serial NOT NULL,
    NAME character varying(20) NOT NULL,
	STATUS character varying(1) NOT NULL DEFAULT 'A',
    CREATION_DATE timestamp without time zone,
    CREATED_BY character varying(40),
    MODIFICATION_DATE timestamp without time zone,
    MODIFIED_BY character varying(40),
    PRIMARY KEY (ROLE_ID),
	CONSTRAINT STATUS_TYPE CHECK (STATUS IN ('A','D'))
);

ALTER TABLE library_schema.ROLES
    OWNER to library_user;
	
	CREATE TABLE library_schema.USER_ROLE_MAPPING
(
    USER_ID integer NOT NULL,
    ROLE_ID integer NOT NULL,
    CONSTRAINT USER_FK FOREIGN KEY (USER_ID)
        REFERENCES library_schema.USERS (USER_ID) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT ROLE_FK FOREIGN KEY (ROLE_ID)
        REFERENCES library_schema.ROLES (ROLE_ID) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE library_schema.USER_ROLE_MAPPING
    OWNER to library_user;
	
	CREATE TABLE library_schema.BOOKS_INVENTORY
(
    ISBN character varying(20) NOT NULL,
    BOOK_NAME character varying(20) NOT NULL,
    AUTHOR_NAME character varying(20) NOT NULL,
    EDITION character varying(3),
    COUNT INTEGER DEFAULT 0,
    STATUS character varying(1) NOT NULL DEFAULT 'A',
    CREATION_DATE timestamp without time zone,
    CREATED_BY character varying(40),
    MODIFICATION_DATE timestamp without time zone,
    MODIFIED_BY character varying(40),
    PRIMARY KEY (ISBN),
    CONSTRAINT COUNT_BOUNDATION CHECK (COUNT > -1),
    CONSTRAINT STATUS_TYPE CHECK (STATUS IN ('A','D'))
);

ALTER TABLE library_schema.BOOKS_INVENTORY
    OWNER to library_user;

COMMENT ON TABLE library_schema.BOOKS_INVENTORY
    IS 'It stores book information';
	
	CREATE TABLE library_schema.ORDERS
(
    ORDER_ID SERIAL NOT NULL,
    USER_ID INTEGER NOT NULL,
    BOOK_ID character varying(20) NOT NULL,
    ORDER_STATUS character varying(10) NOT NULL DEFAULT 'WITH_USER',
    EXPECTED_RETURN_DATE timestamp without time zone,
	ACTUAL_RETURN_DATE timestamp without time zone,
    STATUS character varying(1) NOT NULL DEFAULT 'A',
    CREATION_DATE timestamp without time zone,
    CREATED_BY character varying(40),
    MODIFICATION_DATE timestamp without time zone,
    MODIFIED_BY character varying(40),
	 CONSTRAINT USER_ORDER_FK FOREIGN KEY (USER_ID)
        REFERENCES library_schema.USERS (USER_ID) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
	CONSTRAINT BOOK_ORDER_FK FOREIGN KEY (BOOK_ID)
        REFERENCES library_schema.BOOKS_INVENTORY (ISBN) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    PRIMARY KEY (ORDER_ID),
    CONSTRAINT STATUS_TYPE CHECK (STATUS IN ('A','D')),
	CONSTRAINT ORDER_STATUS_TYPE CHECK (ORDER_STATUS IN ('WITH_USER','RETURNED'))
);

ALTER TABLE library_schema.ORDERS
    OWNER to library_user;
	
	CREATE TABLE library_schema.DEFAULT_RETURN_TIME
(
    DAYS INTEGER NOT NULL DEFAULT 7
);

ALTER TABLE library_schema.DEFAULT_RETURN_TIME
    OWNER to library_user;
	
