create table user_token (
	id SERIAL PRIMARY KEY,
	token varchar,
	token_expire_time TIMESTAMP
);

CREATE TABLE IF NOT EXISTS public.role
(
    id SERIAL PRIMARY KEY,
    name varchar,
    created_by bigint,
    updated_by bigint,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    is_active boolean DEFAULT true,
    is_deleted boolean DEFAULT false
    );

CREATE EXTENSION IF NOT EXISTS pgcrypto;


CREATE TABLE "user"(
    id SERIAL ,
    first_name character varying COLLATE pg_catalog."default",
    username character varying COLLATE pg_catalog."default" primary key,
    last_name character varying COLLATE pg_catalog."default",
    password character varying COLLATE pg_catalog."default",
    role_id bigint, FOREIGN KEY (role_id) REFERENCES role(id),
    created_by bigint, 
    updated_by bigint,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    is_active boolean DEFAULT true,
    is_deleted boolean DEFAULT false
);

CREATE TABLE IF NOT EXISTS public.organization
(
    id SERIAL PRIMARY KEY,
    name varchar,
    registration_number varchar,
    state varchar,
    city varchar,
    address varchar,
    user_id varchar references "user"(username)
    created_by bigint,
    updated_by bigint,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    is_active boolean DEFAULT true,
    is_deleted boolean DEFAULT false
);

CREATE TABLE contributor(
    id SERIAL PRIMARY KEY AUTOINCREMENT,
    first_name varchar,
    last_name varchar,
    phone_number varchar,
    avatar varchar,
    user_id varchar references "user"(username),
    created_by bigint,
    updated_by bigint,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    is_active boolean DEFAULT true,
    is_deleted boolean DEFAULT false
);

CREATE TABLE otp(
    id SERIAL PRIMARY KEY,
    otp varchar,
    username varchar,
    is_verified boolean DEFAULT false
);

CREATE TABLE out_bound_email(
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    is_processed BOOLEAN,
    to_address VARCHAR,
    retry_attempts int,
    body VARCHAR,
    subject VARCHAR
);

CREATE TABLE organization_request (
    id SERIAL PRIMARY KEY,
    name varchar,
    description varchar,
    tag varchar,
    type varchar,
    raised_date timestamp,
    organization_id bigint, FOREIGN KEY (organization_id) REFERENCES organization(id),
    created_by bigint,
    updated_by bigint,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    is_active boolean DEFAULT true,
    is_deleted boolean DEFAULT false
);

CREATE TABLE product(
    id SERIAL PRIMARY KEY,
    name varchar,
    unit varchar,
    quantity bigint,
    is_order_completed boolean,
    order_platform varchar,
    contributed_by bigint,
    contributed_date timestamp,
    is_acknowledged boolean,
    link varchar,
    order_id varchar,
    request_id bigint, FOREIGN KEY (request_id) REFERENCES organization_request(id),
    created_by bigint,
    updated_by bigint,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    is_active boolean DEFAULT true,
    is_deleted boolean DEFAULT false
);



