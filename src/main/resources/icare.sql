--
-- PostgreSQL database dump
--

-- Dumped from database version 14.10 (Ubuntu 14.10-0ubuntu0.22.04.1)
-- Dumped by pg_dump version 14.10 (Ubuntu 14.10-0ubuntu0.22.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: pgcrypto; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA public;


--
-- Name: EXTENSION pgcrypto; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pgcrypto IS 'cryptographic functions';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: contributor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.contributor (
    id integer NOT NULL,
    first_name character varying,
    last_name character varying,
    phone_number character varying,
    avatar character varying,
    user_id bigint,
    created_by bigint,
    updated_by bigint,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    is_active boolean DEFAULT true,
    is_deleted boolean DEFAULT false,
    email character varying
);


ALTER TABLE public.contributor OWNER TO postgres;

--
-- Name: contributor_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.contributor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contributor_id_seq OWNER TO postgres;

--
-- Name: contributor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.contributor_id_seq OWNED BY public.contributor.id;


--
-- Name: organization; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.organization (
    id integer NOT NULL,
    name character varying,
    registration_number character varying,
    state character varying,
    city character varying,
    address character varying,
    user_id bigint,
    created_by bigint,
    updated_by bigint,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    is_active boolean DEFAULT true,
    is_deleted boolean DEFAULT false,
    ngo_id character varying,
    email character varying,
    phone_number character varying
);


ALTER TABLE public.organization OWNER TO postgres;

--
-- Name: organization_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.organization_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.organization_id_seq OWNER TO postgres;

--
-- Name: organization_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.organization_id_seq OWNED BY public.organization.id;


--
-- Name: organization_request; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.organization_request (
    id integer NOT NULL,
    name character varying,
    description character varying,
    tag character varying,
    type character varying,
    raised_date timestamp without time zone,
    organization_id bigint,
    created_by bigint,
    updated_by bigint,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    is_active boolean DEFAULT true,
    is_deleted boolean DEFAULT false
);


ALTER TABLE public.organization_request OWNER TO postgres;

--
-- Name: organization_request_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.organization_request_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.organization_request_id_seq OWNER TO postgres;

--
-- Name: organization_request_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.organization_request_id_seq OWNED BY public.organization_request.id;


--
-- Name: otp; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.otp (
    id integer NOT NULL,
    otp character varying,
    username character varying,
    is_verified boolean DEFAULT false
);


ALTER TABLE public.otp OWNER TO postgres;

--
-- Name: otp_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.otp_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.otp_id_seq OWNER TO postgres;

--
-- Name: otp_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.otp_id_seq OWNED BY public.otp.id;


--
-- Name: out_bound_email; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.out_bound_email (
    id integer NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    is_processed boolean,
    to_address character varying,
    retry_attempts integer,
    body character varying,
    subject character varying
);


ALTER TABLE public.out_bound_email OWNER TO postgres;

--
-- Name: out_bound_email_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.out_bound_email_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.out_bound_email_id_seq OWNER TO postgres;

--
-- Name: out_bound_email_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.out_bound_email_id_seq OWNED BY public.out_bound_email.id;


--
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id integer NOT NULL,
    name character varying,
    unit character varying,
    quantity bigint,
    is_order_completed boolean,
    order_platform character varying,
    contributed_by bigint,
    contributed_date timestamp without time zone,
    is_acknowledged boolean,
    link character varying,
    order_id character varying,
    category character varying,
    request_id bigint,
    created_by bigint,
    updated_by bigint,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    is_active boolean DEFAULT true,
    is_deleted boolean DEFAULT false
);


ALTER TABLE public.product OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_id_seq OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    id integer NOT NULL,
    name character varying,
    created_by bigint,
    updated_by bigint,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    is_active boolean DEFAULT true,
    is_deleted boolean DEFAULT false
);


ALTER TABLE public.role OWNER TO postgres;

--
-- Name: role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_id_seq OWNER TO postgres;

--
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;


--
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    id integer NOT NULL,
    username character varying,
    password character varying,
    role_id bigint,
    created_by bigint,
    updated_by bigint,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    is_active boolean DEFAULT true,
    is_deleted boolean DEFAULT false
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_id_seq OWNER TO postgres;

--
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_id_seq OWNED BY public."user".id;


--
-- Name: user_token; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_token (
    id integer NOT NULL,
    token character varying,
    token_expire_time timestamp without time zone
);


ALTER TABLE public.user_token OWNER TO postgres;

--
-- Name: user_token_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_token_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_token_id_seq OWNER TO postgres;

--
-- Name: user_token_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_token_id_seq OWNED BY public.user_token.id;


--
-- Name: contributor id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contributor ALTER COLUMN id SET DEFAULT nextval('public.contributor_id_seq'::regclass);


--
-- Name: organization id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organization ALTER COLUMN id SET DEFAULT nextval('public.organization_id_seq'::regclass);


--
-- Name: organization_request id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organization_request ALTER COLUMN id SET DEFAULT nextval('public.organization_request_id_seq'::regclass);


--
-- Name: otp id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.otp ALTER COLUMN id SET DEFAULT nextval('public.otp_id_seq'::regclass);


--
-- Name: out_bound_email id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.out_bound_email ALTER COLUMN id SET DEFAULT nextval('public.out_bound_email_id_seq'::regclass);


--
-- Name: product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- Name: role id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);


--
-- Name: user id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user" ALTER COLUMN id SET DEFAULT nextval('public.user_id_seq'::regclass);


--
-- Name: user_token id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_token ALTER COLUMN id SET DEFAULT nextval('public.user_token_id_seq'::regclass);


--
-- Data for Name: contributor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.contributor (id, first_name, last_name, phone_number, avatar, user_id, created_by, updated_by, created_at, updated_at, is_active, is_deleted, email) FROM stdin;
13	Ragul	Venkatesan	\N	https://lh3.googleusercontent.com/a/ACg8ocJNVtYkq0Avhn1HZjVmX66Jsb_7IrJj9d3FnEZ7SVbKuVw=s96-c	21	\N	\N	2023-12-20 09:42:32.197+00	2023-12-20 09:42:32.197+00	t	f	ragul.venkatesan@ideas2it.com
14	Divya	S	\N	https://lh3.googleusercontent.com/a/ACg8ocLx2Lgub2rKnSQmWli7Sm5WzFE6uW_cywOYkoADCwG1=s96-c	22	\N	\N	2023-12-20 09:46:08.231+00	2023-12-20 09:46:08.231+00	t	f	divya.s@ideas2it.com
15	Maria Antony 	Praveen Raja	\N	https://lh3.googleusercontent.com/a/ACg8ocKo9HhRJEigrkzOtb0BMcRyNt9quYVg35e7R_BQlU8lmcc=s96-c	23	\N	\N	2023-12-20 09:47:34.817+00	2023-12-20 09:47:34.817+00	t	f	mariaantony.praveenraja@ideas2it.com
\.


--
-- Data for Name: organization; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.organization (id, name, registration_number, state, city, address, user_id, created_by, updated_by, created_at, updated_at, is_active, is_deleted, ngo_id, email, phone_number) FROM stdin;
6	THE CHILDREN FOUNDATION	126	TAMIL NADU	CHENNAI	NO.5, GANDHI SALAI LANE, PAZHAVANTHANGAL, ALANDUR, CHENNAI-600114.	20	\N	\N	2023-12-20 08:37:54.87+00	2023-12-20 08:37:54.87+00	t	f	TN/2022/0303643	niraimathi.sankarasubramaniyan@ideas2it.com	\N
7	PANRUTI ONDRUPATTA ILAIGNER KAZHAKAM	100/2014	TAMIL NADU	PANRUTI	25,KAMARAJ NAGAR	24	\N	\N	2023-12-20 10:35:08.021+00	2023-12-20 10:35:08.021+00	t	f	TN/2017/0157202	shobana.chidambaram@ideas2it.com	\N
\.


--
-- Data for Name: organization_request; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.organization_request (id, name, description, tag, type, raised_date, organization_id, created_by, updated_by, created_at, updated_at, is_active, is_deleted) FROM stdin;
12	Brighten Their World: Stationery Drive for Children	Stationery for the Underprivileged children - pencils notebooks	High	Children Home	2023-12-20 14:13:34.405	6	\N	\N	2023-12-20 08:43:34.408+00	2023-12-20 08:43:34.408+00	t	f
13	Books and Beyond: Help Us Enrich the Lives of Young Minds	Books for the Young minds - Notebooks	Medium	Children Home	2023-12-20 14:14:47.7	6	\N	\N	2023-12-20 08:44:47.702+00	2023-12-20 08:44:47.702+00	t	f
14	Essentials for Elders: Your Contribution Makes a Difference	Essentials for the Elderly - Wheat, Rice	High	Oldage Home	2023-12-20 14:16:21.119	6	\N	\N	2023-12-20 08:46:21.122+00	2023-12-20 08:46:21.122+00	t	f
15	Caring for the Elderly: Support Needed for Senior Well-being	Daily needs for the Elderly - Soap, Salt, Rice	Low	Oldage Home	2023-12-20 14:17:40.962	6	\N	\N	2023-12-20 08:47:40.965+00	2023-12-20 08:47:40.965+00	t	f
16	School Supplies Drive: Building Futures for Little Ones	Requesting for School supplies - Pen, Pencil, Notebooks,Eraser, Scale	High	Children Home	2023-12-20 14:19:43.351	6	\N	\N	2023-12-20 08:49:43.355+00	2023-12-20 08:49:43.355+00	t	f
17	Michaung flood Relief	Request for the Essentials 	High	Specially Abled	2023-12-20 14:22:45.551	6	\N	\N	2023-12-20 08:52:45.553+00	2023-12-20 08:52:45.553+00	t	f
18	Essentials for Elderly	Request for Essentials - Rice, Wheat, Sugar	Medium	Oldage Home	2023-12-20 16:07:20.056	7	\N	\N	2023-12-20 10:37:20.06+00	2023-12-20 10:37:20.06+00	t	f
\.


--
-- Data for Name: otp; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.otp (id, otp, username, is_verified) FROM stdin;
1	7405	mariapraveen.g@gmail.com	f
3	4081	ragul.venkatesan@ideas2it.com	t
4	8280	nashan2791@gmail.com	t
5	4314	nandhakumar.karthikeyan@ideas2it.com	t
7	6109	karthick.murugesan@ideas2it.com	t
8	4104	nithinan@ideas2it.com	f
6	8333	niraimathi.sankarasubramaniyan@ideas2it.com	t
2	1384	shobana.chidambaram@ideas2it.com	t
\.


--
-- Data for Name: out_bound_email; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.out_bound_email (id, created_at, updated_at, is_processed, to_address, retry_attempts, body, subject) FROM stdin;
16	2023-12-20 14:07:14.886	2023-12-20 14:07:14.886	f	niraimathi.sankarasubramaniyan@ideas2it.com	0	OTP for your registration is 8333	ICare-OTP Validation
17	2023-12-20 16:04:19.659	2023-12-20 16:04:19.659	f	shobana.chidambaram@ideas2it.com	0	OTP for your registration is 1384	ICare-OTP Validation
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product (id, name, unit, quantity, is_order_completed, order_platform, contributed_by, contributed_date, is_acknowledged, link, order_id, category, request_id, created_by, updated_by, created_at, updated_at, is_active, is_deleted) FROM stdin;
36	Pencil	Qty	10	f	\N	\N	\N	f	https://www.bigbasket.com/pd/40010702/apsara-platinum-extra-dark-pencils-10-pcs/?nc=as	\N	Stationery	16	\N	\N	2023-12-20 08:49:43.356+00	2023-12-20 08:49:43.356+00	t	f
37	Blue Pen	Qty	10	f	\N	\N	\N	f	https://www.bigbasket.com/pd/40126495/reynolds-ball-pen-brite-blue-5-pcs/?nc=as	\N	Stationery	16	\N	\N	2023-12-20 08:49:43.358+00	2023-12-20 08:49:43.358+00	t	f
38	NoteBook	Qty	20	f	\N	\N	\N	f	https://www.bigbasket.com/pd/40125826/classmate-notebook-ruled-long-single-line-172-pages/?nc=as	\N	Stationery	16	\N	\N	2023-12-20 08:49:43.36+00	2023-12-20 08:49:43.36+00	t	f
39	Eraser	Qty	15	f	\N	\N	\N	f	https://www.bigbasket.com/pd/20004358/apsara-non-dust-eraser-non-toxic-dust-free-20-pcs/?nc=as	\N	Stationery	16	\N	\N	2023-12-20 08:49:43.361+00	2023-12-20 08:49:43.361+00	t	f
40	Rice	Kg	5	f	\N	\N	\N	f	https://www.bigbasket.com/pd/10000415/bb-royal-rice-raw-sona-masoori-5-kg-bag/?nc=as	\N	Grocery	17	\N	\N	2023-12-20 08:52:45.554+00	2023-12-20 08:52:45.554+00	t	f
41	Wheat	Kg	5	f	\N	\N	\N	f	https://www.bigbasket.com/pd/30006887/aashirvaad-atta-whole-wheat-1-kg-pouch/?nc=as	\N	Grocery	17	\N	\N	2023-12-20 08:52:45.556+00	2023-12-20 08:52:45.556+00	t	f
42	Sugar	Kg	4	f	\N	\N	\N	f	https://www.bigbasket.com/pd/40004538/parrys-sugar-white-label-1-kg-pouch/?nc=as	\N	Grocery	17	\N	\N	2023-12-20 08:52:45.557+00	2023-12-20 08:52:45.557+00	t	f
43	Rice	Kg	2	f	\N	\N	\N	f	https://www.bigbasket.com/pd/10000415/bb-royal-rice-raw-sona-masoori-5-kg-bag/?nc=as	\N	Grocery	18	\N	\N	2023-12-20 10:37:20.062+00	2023-12-20 10:37:20.062+00	t	f
44	Wheat	Kg	1	f	\N	\N	\N	f	https://www.bigbasket.com/pd/30006887/aashirvaad-atta-whole-wheat-1-kg-pouch/?nc=as	\N	Grocery	18	\N	\N	2023-12-20 10:37:20.065+00	2023-12-20 10:37:20.065+00	t	f
45	Sugar	Kg	1	t	\N	14	2023-12-20 16:10:29.707	t	https://www.bigbasket.com/pd/40004538/parrys-sugar-white-label-1-kg-pouch/?nc=as	98765	Grocery	18	\N	\N	2023-12-20 10:37:20.066+00	2023-12-20 10:41:11.937+00	t	f
28	Pencil	Qty	20	f	\N	\N	\N	f	https://www.bigbasket.com/pd/40010702/apsara-platinum-extra-dark-pencils-10-pcs/?nc=as	\N	Stationery	12	\N	\N	2023-12-20 08:43:34.409+00	2023-12-20 08:43:34.409+00	t	f
29	NoteBook	Qty	20	f	\N	\N	\N	f	https://www.bigbasket.com/pd/40125826/classmate-notebook-ruled-long-single-line-172-pages/?nc=as	\N	Stationery	12	\N	\N	2023-12-20 08:43:34.41+00	2023-12-20 08:43:34.41+00	t	f
30	NoteBook	Qty	30	f	\N	\N	\N	f	https://www.bigbasket.com/pd/40125826/classmate-notebook-ruled-long-single-line-172-pages/?nc=as	\N	Stationery	13	\N	\N	2023-12-20 08:44:47.703+00	2023-12-20 08:44:47.703+00	t	f
31	Wheat	Kg	5	f	\N	\N	\N	f	https://www.bigbasket.com/pd/30006887/aashirvaad-atta-whole-wheat-1-kg-pouch/?nc=as	\N	Grocery	14	\N	\N	2023-12-20 08:46:21.124+00	2023-12-20 08:46:21.124+00	t	f
32	Rice	Kg	5	f	\N	\N	\N	f	https://www.bigbasket.com/pd/10000415/bb-royal-rice-raw-sona-masoori-5-kg-bag/?nc=as	\N	Grocery	14	\N	\N	2023-12-20 08:46:21.125+00	2023-12-20 08:46:21.125+00	t	f
33	Soap	Qty	10	f	\N	\N	\N	f	https://www.bigbasket.com/pd/40227231/cinthol-bath-soap-999-germ-protection-deodorant-complexion-original-100-g/?nc=as	\N	Grocery	15	\N	\N	2023-12-20 08:47:40.965+00	2023-12-20 08:47:40.965+00	t	f
34	Salt	Kg	3	f	\N	\N	\N	f	https://www.bigbasket.com/pd/241600/tata-salt-iodized-1-kg-pouch/?nc=as	\N	Grocery	15	\N	\N	2023-12-20 08:47:40.966+00	2023-12-20 08:47:40.966+00	t	f
35	Rice	Kg	5	f	\N	\N	\N	f	https://www.bigbasket.com/pd/10000415/bb-royal-rice-raw-sona-masoori-5-kg-bag/?nc=as	\N	Grocery	15	\N	\N	2023-12-20 08:47:40.967+00	2023-12-20 08:47:40.967+00	t	f
\.


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.role (id, name, created_by, updated_by, created_at, updated_at, is_active, is_deleted) FROM stdin;
1	CONTRIBUTOR	\N	\N	2023-12-19 12:58:47.072612+00	2023-12-19 12:58:47.072612+00	t	f
2	ORG-USER	\N	\N	2023-12-19 12:58:47.072612+00	2023-12-19 12:58:47.072612+00	t	f
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."user" (id, username, password, role_id, created_by, updated_by, created_at, updated_at, is_active, is_deleted) FROM stdin;
20	ic-3643	\\xc30d04070302eb6e33c1b8f93a0e69d2b10176fce04eb21df1d0763da5995b3c8f88020f42ec13e56b8b1e786a1518ec55b226fd6824eba1bab2914a1b4fcc2e1b53a3c9b7164fd7a3a3fb5f8264c5718cb417458f524a7642cbeb05c16a51407ebd99ee84408508d9b12a0fc81ec9e35c104a3a4f924252f8af51a57f1dda4af328389157ea7ee71207c41c64f45cf12a8c9acd06ce5afc4df6af720e6985093325d39c89e8ff0644e4351799aee9be8ecd03a043725703e439d489086d47907ff7	2	\N	\N	2023-12-20 08:37:54.868+00	2023-12-20 08:37:54.868+00	t	f
21	ragul.venkatesan@ideas2it.com	\N	1	\N	\N	2023-12-20 09:42:32.188+00	2023-12-20 09:42:32.188+00	t	f
22	divya.s@ideas2it.com	\N	1	\N	\N	2023-12-20 09:46:08.22+00	2023-12-20 09:46:08.22+00	t	f
23	mariaantony.praveenraja@ideas2it.com	\N	1	\N	\N	2023-12-20 09:47:34.808+00	2023-12-20 09:47:34.808+00	t	f
24	ic-7202	\\xc30d04070302577b6157c277e62d73d2b101929f7329014fe8dbdd36a0d36e31e6961d1617e62b63e0a87fa4055ac1326336d235c3fd8ea0fbbc59a1ea0466a4809928c0be2aa4e9a11a0ba98584028d8f00b0d5c29a3ca4c72049780d46a51bb80d3757a8a15bb10019702c7c02ac2a59b0c0d5a3272c56e74adee6c31d608d6e243dffbdeccc82def7869af119b017687571acf25b8bcc428ac21ff8f18e6532bbb65669ee703379ebfee9cab858c8405dbc334d149426b99747fd7367e92e2a6d	2	\N	\N	2023-12-20 10:35:08.018+00	2023-12-20 10:35:08.018+00	t	f
\.


--
-- Data for Name: user_token; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_token (id, token, token_expire_time) FROM stdin;
98	eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.GqyCJy5NEro0ZVyypGCS5VIrcZg-cyGu9GqPfj2lT8a4A1WQqZLS00B2BT3bByc4dIv44n5t3gUA1DlnuO1GFJeGN5rv3mpo7NnEe7JtVq1csauBqpKD6DMw2OpteGC2FR8BSHOj_fvKmPWU6Q3eRVY108Aaoq-zZEJ0JLw8DxRdwqlzkJO9roWzqbDpiKkpkVYRLuhJkHRVWxQ0i2fYkavGvBLBC4U04BdAYRbA5Wu6xqLUagdck3aI7pAOYUp6AYJSHzdIpkGYHrBsPUvemu4mZYTxcbuvNNqGKGqkX4w9oZmyg0VONyx0SG1iCIWykOR8Jt7QWWZmo1X10tyIxw.DaJdUhKYHSd6__M_.4jAKN0KKId3DgZ3gIq-11eg-6R_485R4Boa3HSMCUi_V4IRdTlgLDONm6ygXNyblXd2W2Aa-k71cDaFkSphGwtRTQwje-m_VftyQHY61liGhz-lLR5iCwHTcVIIdJPHr6JsMrkHSvThAYaKzOhj55E7JwQ_zvaz5b6Pixy2NSLq9c4HWsxGunAWTm0KkuAZTaFQbsA.FL9eAFmo5IJscI8XxVutHQ	2023-12-20 17:16:08.349
97	eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.ObfFrss4CqOWiztDf-cNQ6-gS80-HYeLOfxTFd_mvhvMpodfHA50Qdw3cBbUOSy2UDmawzTS8pLnwWn9D7pF2sqo_8auJPBeo2_PaE2oT_tY5y_vrtaLKI8OwV1pVsrmNYpy1LPps0TU59I85_O947_nZzHW53Q5Y9NoO3rghaB9QZ2m0xqitz-QHIQvRW6_hnIgcyvlN7z08kL4-hiOW3L3efr29e4Tr0KJ-d--NyxUZnqdKqARdoi6qjAhUGo0qDtVf3-yipNbiVzfazft1tyaib9nF-rkqTLSJ17Ub7U3rD0hlyYf3EoX54pSKVqaPjKFMFx0i8bRYBb-wQkbNg.ko0NPwtaxyU3bx_A.98RIRL3cBqqODrM8pVbhyVZMAYhG9nBrPwXZjX_oaDegYdTC5PUOfIEOp117yos3UHTMFSYOazycq50tGiH5xamkVRyCohZwTJ7elAmj3vV3RWCd7rZSCtZQKiQ-O5RNmf6_caJKNya5kWu6JJYBA4YFtt6jLXLc3cLk345VBtbIlnmNBNilOwDyOKjdOBH4gzrs-g.umf7yzqjKV0avT6QM_m6ew	2023-12-20 16:16:24.398
94	eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.CIIL-eoNFBxUYQkIRRbPyhZNmSloLhcSQPECoBkSLxofddCRBrcNR12y5Ki_6xYearomyGdwZEyFwSD5KYz0cRl78mo43nMEoHXczpj67uaBCGzjfGEZ1aerrDs91jAf2Yoyplyo_U1WLZg4Rv8Oz7G6RlfbNlz5Cc8AmzE1Fb23X3vnBbk_2ozGTxhuzD9DrRR7lhFsYm2EjBf9F8gqVm8gvw3FoEc_aB--vv-wmdxzzQlGAZN6HAI3DFaZ7xgE-gm2kygwThgsW0v8SkMwc0sekgiL1oBBhDtsn10TisQjx3JPGmPI6ga8DJ-LKDSioqFC4VYezsIqhy8mxajqHA.geYxqg9v5dWXPW3j.Ndo3yjBJ4LlRVbbtyWxML7GOHBvdwaoG4ntehHFP__hl_9oF8A4jfPuxIZWMgb784w1waIP_60jAXu5U-iAOid0Y_dMdDEDUMR2Y5HJ2bQoPCNNQf5JQytCx98GkFzt1O3I-aGQLjE-f23LblmrmmcuyhbSptKlniM88Q6ufSkHhRVoy.GSQBcDv6lT8wjTNbpwxCDw	2023-12-20 15:22:48.437
96	eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.s-ga5aHfzpGjAbtbz-eWWqE8T5bmwtocArsSQN8v4zX4s5XMp3GQy1nyoTMFFn809cJdXQn7_sWPvg_R19HV-G_47f8Hun6Zu8ImA2kB3J8bv94RPSPZT6D3hxtuSmV6K4A4jdzFsJpG7WpUj48YNjFTGuN2ZB9PxX4zuC2ZvT0dlYKwEIRUYDp0V_vxuqvW0vqJQujRMJtZ5KJaDSgkHta4fka2EB-oQqO8zpO5r9j_o4s6AQWzfNCmkdbsT_NS3bRBsCKfwsUzFmiFpqqvueJ9W9Ady9vWl1PIKalhdnyrelNP2gYaygMABa6orrk1vmbgbokU2l4MPEaMzyliFQ.8c47r1nUUC359DzM.IMCCIxL4cTo2K34K8JZY1gx4i3S4jfuexjgX7wc7AagAxuv6zwRpRVCaX9ebkpu9Lnrkk54ppUV0R9ufg_Jp6MjOY8B1Rn_JdAKCPtSo-hU1cGD5zVA2ICi0kLQTpTpuqzBAf4BIvCVYhA5z8bm3qhccAyKu1P6OKEarNIUx_TgDVs7ENUWCipmpqxzQr3DbFpzKkmDsR4CcOK2_xg.jPuy99VvgZqDJH5Z6qb35g	2023-12-20 17:12:32.378
100	eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.HjdC9OomRllIEWlsHRy0nYRPbTJgx7QfouDtp10Vrcoc46BPi7Ir_B3_pQbqrVomWU6bHyPsdqqh42BwW0CRO3d4sxryxxPEhlntOJyhf3rJwX8sLn7i9djqb1-wLTQwQQTw4gvtcpNdliZs5ljrjFAv3_S_qJrO1R9i4BaXpI3p0JgKRfQyom8X9rr6O7Rhyh5cgjjpEWMVudc-EPOk6k1M0yV2GcXfGh4GcZkPNxJ8QHoVoB9yl2-ubqIu2YQ6sISqF72mxqPHMS0oFRSImCeTrJBp-C_VLqExXyo58n4eYSxZ3TGNgqX38xCdrHpfP4lXeKrvcyuIkw9HJQ7slQ.aohZ70NW1iW3mEE3.6ZLQxUTjyWwbni75_t7fc1NPt7uWr2o38PnJ_QR09Mvv97mWVm9FA33XsOxGKV9n67JuYyUQRzpe3gjCBD_s5HCLHd1qYm-xVL6sLArRn_K_o3YYFW0AT33GGBOEl2x4-x3BQ9HEav4-hlZVDveN-V_dKpkAdcDlN1QHajBWV120_LE3eqqMyXuejyw0sgxsVhkW6w.ozGHxec4J3H50tVzDImjsg	2023-12-20 17:16:36.864
99	eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.cMXYWI8azV06JgdHqgj3QFpamJrjsE1tUZUnXJtpjYVpDfrrmbKftxmsGwxGe6J4Nckbx0-9gJwRITyOekM22wlBjqEQ1QJERE7H4aM9b1TmSzbmWVazCDnIy7NVHcJwGosjHlkkcGCVlytBcmAlCVo2q-x90y7d1p50CzmP28-2jCcbzu3yv6lNMruLZZGQWfULFoVttAUW3kqOFM5gY5_jkRFQ7rSvFTRxRDJ1S1nmdAFQku7PiZ2IGDgX1gEmiyLg3qnRXdz_MiK1xCVlbMRwRV1jn5zh5b8DozeQ3RFm7RQ_xYpWBJmvZF2YO-uZnNcUtO4UmBUa6EJeiV9DaA.CeeNmxRwqf3cPMdz.W78yoFYHIGrXANy-Uab8-jVERCtKB0jg7T8aAaN8SZ6Abj9cVPGOD3fYOL8MHXhBRxi0x0sdFejzlZqmskZ-uHYds4Y7tDCWr7J0YaXT4CAvhTEgYa6M-U8dcf8j5oUNVma2fHAbOADAfVynMwlhi-LKtzGlV9Rj0D7Q4kl628-LCwkPcHlK_i3SRZoxqbqYY7WmuQ.UCR0Xy8mN9xiQ9lagTD0Fg	2023-12-20 16:16:36.907
95	eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.ZFGYN9izlyBUk3K96JB6gAaOjD-Tn6gHCH3xhcNxMogaKUj6Cco2Zwq0A6E_D5KzbV3qm4Je6rg3qg2KC89-qJVd17IMUK8vNVkpDAO3IGXWBwDH-xLXrcLPkZ5WCQ8acNh6ISnNKYQw4mQvr4zV5n2Ily8j_Q7l4vcyBFQB_BjoFzv6daGxbTHco5TC20DSyRLrNodWLXxhdCwFLhj7XoinmVG-S5G44fGf6SpqgWRfL9ul_D2HBMHxsk0XryLby88lKJZPPSeTl-111j9eFxs-H62YiODd7JrwjT7ksaasQ5uqsW7noP2_iRBV_Lg8sMAcaViM1scDfhGu8eog6A.bLg7ouiyWCYuf0Zp.bKwyy44ht6cm3TTDoiQGD5QZ8iIjpHjkr204lWlHZnzWXj9z_q0fep_D_5VLstWB5ipgrGaUyEEq1Yp7XwOIsWBjnJSLaXxzhjjet1xKIcHA2sBS_0g6cF8VncdF1oPGmGz3wncaNw95Eag14qSvETi6Ky1Orp-fhC76cXTFjZmPLZ9ymeQAY2LQpJbQpWn3gVbKRCVWEFU_KjnnpA.cUi4cwWnReMiHzrN_nOImA	2023-12-20 16:21:33.135
102	eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.uxMbNKnxTXZsWWgag11E-NvQnOZgp9sht8JBVsjSC3cGQuAC1sxoAtEjsk-SrUFTqZab3oP-0IRTXrrgFhTdU0abXMToB1iKQPtfCxPdDjFkxdut4ByqQMFeoA00QtYSBxFcraWIjtyQC_fglZnWDh-lSOYzWGRhfxPgY7uejBccXuoOxLlhS2tQbDixMr7E3guY8ZqoI8vnFvZxgtVCM4fuRlr6sATxlmklx_QI5KxFUMYezq8FgneHf3FPoTlNraK470f71EwWIYlIwqygcEu-DVmYR3UReY5OSBdMVzUgqmBQ70f1DvVoKWzVySrqX1PFMPc4lz78AVeRlt8h-Q._rIKUHCKedeSLpq2.xYw37DXVnR46vRuMgVC812srZmzIS_pNXTWJP9ZGwOVVtluE0s_UfBgR1U4DeN_jAiKeuNpYmQFuq4y2kaL_R8Ck1MRNJSc9a445Ft1WqSWbH9MaqDZVbFauY4A36fHhSNrbe_BAiMZE8lrr1OPlR9O0wDmCScrRP-UOOzRK55A6vtlyaO2A1Rw5K-SBNO7LHaH7YcTKPLJRwazRqsNiY_W9-E8.zRMUCrn7tPSgtHOMvBqitg	2023-12-20 17:17:34.917
101	eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.Uss4QRBrr0TcxOnllcEpcF27j4DPRNRCJj9Ybx1gA3gDyC2sca8SAd5WFPO2-z1lCAfMfANMcbXupq38sByb0NFHzQTpt7kkMmt2Voe4KkP4kGEUX-TtFqqWzhQ4q1hdcLyQhOpbirYc5lNRJ3kdobdRkHSFgpMVCAzhh9-6c9gAwlPc06DYdLDTspO03xGJrvrmA-I2nFGdgi15OuIyJafRshbE33nhFWFkySGJgb0NKpMw0t0rENadR4ylfgGXik7vAyMvtpg7NWOaixJ2gxy-z9S8FG1TJ5JvaJACDFMtZ-mh_W2PH5Odn0GlM8HMUi30_4mTpzfcBP73sd33kA.AQJr9cOkKxkaZyVu.4rAu7ZG-Q68I2VG3YWlggoaHzbN95ycpbXDir8ev5IC6fSt6WYBQNoNsWEZ0kMxFMNap0C0TVkZRq9Wm6ayU4Q6ihWS9n6YkPDWCLFR2xprgqV1QhgjH1HwhHs8bDnnwGzS3sBf7uXqdUu26Ccc0n-tz_4EyEcTxE4jxWIU1AxBYLfwSK3sSafGo8mS_n09L87kAt1U_azcSH2j8Uqc5nP3og1Y.6HJw8ygtRHi57Y55JXDYgA	2023-12-20 16:17:34.952
104	eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.YCe4VYMpqE1F6sx8A25sXjXaJXrfHtZlLEtKwHKRKp924gE7KY7lRIUh5k-FRYfZZTH-85yFnztnYdtd5j1M1Ah9elI3Ks9VRfmD5RZI8gHh1lfL0KGHJxAAK5X3EX8V2gym4VMckbdJryECUQKB45S1OVpJRHYw6DIk6DRBd0MQmzfv05s68AKlUubNdFGcw_KU3-do8NsG8uSTQayTK0Ed6RqHIPLlH0ZcDCVwoULVnF409NTZzVgAEw6VejHtgl0CVmQkLvF4z2uQlYOn8F3Bfb697AkJsO_GTYGFIgkk8Aubnip-MGWp1HwRuCIp5gpW9zt1HrjU6sL1SM2VuA.dCPln1TOLse44WWo.IBusRQEf8BnRhZ1Brsj2M2KlAmRfjS6si0_uW7DWmg1lrjdBWrLxbmmt7UDT7Fan00Q4e75smjPpR05VsGl6ZQFZXFIWZtqT7OiEERFrngU6BjFR0LDweshTkeoVsm3UcqVPI3bx9LtnSDYbOn9MTdAOkfDZD7Z1ZxayuZ-8PRupCepsTVzDVdjhoW0zW_wQEKyW9A.281paSooH0LXiQgElO9Qzw	2023-12-20 17:17:45.486
107	eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.Dr6bbXijhOCCHsGvU9wUGJm2qnckhAgX1sV215yhUhamUYbu67JWFYdkSdHebuFEoc-jLI1oVBeA_ZNQrRQWirkPPVC8bx9zo9ZWWVvoo9uzRfnmEnaOX2oFD3HSc9M_xUx9Y1eDaB-g7mBBOivXpMW3cfr14azS5uSo0X9ir2u5yKEfEvEZb61pnXuGjpQy3qNa8bj9NTNmdnB4k_pIUtLyK1uWRXnkoMvpByNjQJnQRymO1vdrd6-LQQHu0gprdY-0ehjbV9wWVY959QWjY38obHKqFqpN6x6_4Q79vz9jc9yvcEqH_jJJl5ZpW8KyBLO2kGAhCbMWhWdEq8HCdA.jUsKr882gIl0CVrJ.5O6S5Ae_NFHtCEuvq84CZbZHXJL8hLgJIWy2rD9ILGo2zHz8epuWKf6E39_Bi0djdKax4aKXUtulzdAHpfxC2kwnTPqmmwWn9d6WpN4dnaOR_8PO3AzNtewHgRk1wyMWLSamBmDt7fVaoq3QLkUiUAeSgkU-_zYwBCvJpjkCrpxjEHNFrJN4QVeHTGCzMxaxsKcB_Q.Y0wKo0IlxB-BTo8C4MtEqw	2023-12-20 16:18:57.197
103	eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.L3UzGirEsByZWrvDN19d8LHkk19iCvy5tJoqxbmQdJndczB8qsIT1lYnMYafG-0F_rCVRx56awu_lcouyFZk4MdDUvFi_pv6R01l7ixNV7u8vc16FosjR0AiM8NMdGjvx1RsPpaJHR_8jCK_WJy3mQ1Y77VXNZJx5RPRHu3AHfuBT7hYl6ffX-huMyzWKx1qEcD7zUCndAvr6Iebw8IHYMlZdr6pOLHFP2bBJCw5L_4SlgtPWQhNwnjfkkw09rLONVB96ZSlAD5wFQF7hMMxrqI-F01iYuY_IlFqpmBn07YM67CpNBIKq_i_TaUBgbGu9CVK1aiAu0otf-Yj6_w5hw.8BFdG9Q7Yzjt7X4V.hwjFy_KCpcvlkQv-DlIIK1s-BNCdDMS2ao916eJLMNy-8bgi9TgRIQr3hnDNYVSJsuDqWAtnFOL2rZAwo76XopjZSaTZeFVDwJcKZUSQE2h3cJpemrEP8ZM5qnhvYN_MnbBj8tN6S54sOmNGoJ2vy3WEOMZ26JweuPpmU9pJRTDJqbsp2vbN-VTAAmrw_YOn03WYzw.FB-cxudsDUN_4vEP7XkDhQ	2023-12-20 16:18:19.516
106	eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.mFTjVoqhxHXVSxEazGD4uQCuqKd6l_1CEiETiPpOENAvE7Sd8oM1izk8_4DQ-mWd7MN_jL3pikPYMg8wP8O1oKMvqboF76IZG2Vs3Hk-DAfD4-lMViOlBDD6ecMSI_1bK4Mo29x0HoFqDk-f3WUyNunnaI6NJK0ERXH9iQBZpvRzJyHqtkO-18oRvXN19y6gAaoUZCsDJezEKsAKUHiA7pVgjkPdQz7-u9OMirDy7d04wruusdbx0VW3F0oBDOL5iHNwgt4b7gY1lNZkrdUB6Sw4ftQT-BNGLiOPbdz6YSjl5KsJP7nbQs7DhVvKmpUsTRwDLdBMdDTXyKaATcF4YA.J1BsvzRygdMD0GEn.s94NrgoGiXqe8TxcKBZTwiCPRPNrEd5GTT9SwHDpruphWrMikblYY52dufe09legVWsBgIeNEL2y0y0TTyF5HPmMr-sgkLiGG8UHGoiSdWELpPYctVnC2c0VgULwLeF3TW6T6H0SVFOCSzNUiEGf4DKIM6mOvjTrRsQ7d6enA3N_hhuv-nXOOEDcHx7t8gCVM3bo6Q.sHIaqhv6evsoXKrPt4Ysvw	2023-12-20 17:18:34.941
105	eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.N4g6-aJtp3uKlpinndkDqVO2zzfeQhdYnYR_sUj6oGjHdZAWy6C1VphgwzLFQFqtC5aaGR58IeYrWfao_W9yWuC43Vju096p0e-4ND8UjOFbGQSwTwQBKpwQCGuuPFzdPNnfRGCKHlUDJorstFTtWtWi7s9rSC0sSrfXOujWnLPHGbJaUP8T4UFjut0N3olIq8i9Kzpz9r_W_E_QCEW5K4kQpnAw4h5E4fwO3VzTarg-CUUqvCZJtoYs1mFmABWLvyOrxN-VGtufhLvNExEUflb3zZElW7LA-rfwYQqCFKqpm5HhztkyaQVsVhhlA9PxXRbkHH9VZWin2mb-tgFfzw.6at-N6blafJnPZb1.4-SYURLidr2_26sqxxJgpK4L9v2rEIIQxa2TAapCB1t8K9rAHNN3pbStksuTDprTDOp6CeX6yneliZ4gFCdIbG-BJWkG-R_0Pa4vIgPJ3_XUZpS7jxKSC6I548CKH9G3rQOq1dUBukLXjScoBmrq5OMEdGflw_Xsgqglpm4TQ3QR1AXkWGdqeWL4vTgg8_APZuns2g.k0GrePz092nc3JVdFWWLjw	2023-12-20 16:18:35
108	eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.TlLloYeaPV5l_LlJSONFEzlgyLrcvu0grx1nymvDxlW5YcioNTXDHmOhADEJ3GO7QNquuS5CCxXpvCYAzjdWm0wYEcd1jTNL6GolnwiEA7oSS_07O44IqYD0nDnPNXD3hm-PzmcPEZulL2Gt171kMh0p5WznO3kUMzI6Q3KIgtcYxTWKSXC2SSNQMNYFAts8aIExtNeI28wNaZJ-UNVm0T3HQCu-N3NNORD2_nVT7OTL8iNpCyHTpJzrNPkzGXmgk8wFmQ14WuTXoARUG5LcRcGisMSgtzzLOio8Ukz56GVRsOpYxXq5RqkOEBFzBI7qLk0HIFgcMMdN7ZiaY76J-Q.NlHiocCBSS5sLItK.xHxWd1vQviKJtqdaHm3vkZ7FO2uyIqzlzwelEtWmO_r611-K1NvMlnl6BzIPTYHKuQ8sLdb8QBJY4uva4NPmWCd-mXsVJlaDwrfLQxVIoXv0bXS2U-OwXoxL0BE502ZurMX3kk4Qz8pTBoyqM6_SP7-yfQ0tYdjh78tN05wIh4L6KEoIria1J0kRjefFEUYdSIRZMQ.nhJSya1AToflR10PaprKOw	2023-12-20 17:18:57.156
110	eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.pibtHBBCRnCLYU0K8drVaeYeny_qt39dpodnT2yN1ksXaMWXpZeGHeGS6bBDN-ppik3RYg5YqjZZB-gEi4hBjD2FfrkF2itEq9O8RjSyq1km1x6JzJh8jMITsh3pBU4HHaixzOqtYXy0uVAuiQhtaAMReDat1j1ZTTQFwabbCNXzQqM4q5eth1hxwneCKftlZzqidY1z3mzNIhS297mduPS5LvmdgPSJx4nPIw5VyfKyct-xGvHGN4FZtzimPGQT3A7TUb0hgnP0KGfs7g-lyGwRtx_yu1kKlMTCgqVaecA5h-pqbbIoyeU0rvJsX_Ow4a8VWo-_o_IGwvyAs06fhQ.oAetkXChOlDAep6H.yCN1Z0uNiZilPuN6ZJ0wJ5tIBF7AB23t2WcsIOyREHisIgeTMzwZ0BmDt6-avsjl9yVtn7s6kxqdiHz3R1d5yWDLEPBgTkhcQa801pdJVJbNC0nBv-YLjd7JdoY1vmVJwvUvrhCPqE22ONtqjRlsnnqZ0RHwDTKiHctzBvBtlvirsYTxaw1SL8Rjy634RMmYJe8CezcK-pMWWfPlTQ.b8Emw5LY6gIz_j7eV5CZlQ	2023-12-20 17:23:34.963
109	eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.LQR-4Yl1yCesnji5_yWZn3yPXmlE1PxSzq63tgUIcCQK-ZgTQf8V5uvMCXq3PRhnbmb0xvojUILONfF8xZ-OW-R-7oj5cXoAWm2W93w3aH_jyrMM2y4YTILXfoePRTKvD9K6kKFlrRcpRJ6MAWVtwAIgLJv8savKaX5Cp5A-j-2uRFAQ6l6ZnTs5aKoZ9VIAHhK0_T0FakYVWsCRtHv1IplagMtKZQ6taIFRTbLhL9m2yyk4q6tuVh6BEijtxOCy3eGK1lhoCpNKlKR3QIe0CiFU93A2TbBUzSYSFMKPBOmBE82RpN_hZJnTOQ80s9pGwPo6NSXaJvYkKeJ0cULULg.JpZs4r4mkglgn-cv.iECdcy4r0IeywqwraiipSno_-jyfGn3VKFzR4VNitzgZLXf1_dk_lHrRjaZxaxSCxasoBpz5VzWgz9m5oXsyDOO8GrurWsXh5k2Fiao-l7vNvx8zgPgx0L9dQC8l9kI8znlKQZpsluNfG3yGUHxbO5eQCwgyXs-bvOM8LYWIIwWE-CSqw28-hrfnntyj3vU_ESAEFRHnKqFk5ddUgA.S-2uaGK2ltm37EJeHTzuug	2023-12-20 16:23:35.46
112	eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.n8OMXkfvR2nWMrMAb9nUEoGEOugpLLxQUuIdAdGujLNqpDjVhsMfoOMC8ExGB8qVcCVtw3ehtxTxtP8ldbbFxdZULPsiL3zr-FV-PBDZD1y5JjicEft_W3M_6k8toZZ9EfHa38EBLs42-AWiYIzSbAMCUKLbdMWRW9IwV7RKaU9tKcM1tQCh4Be1xwBAVNV0rLVP1wsx7aYFY7-IRv_iQxZ9gI9seMJH_zhWoLBrvyBA0qUr3R8LRZezy1oPfXhscSN_0IigsTN1rvwNMns28-wYh8INtD6f36-Ip385WbE4uNzD1CL4gPMFvWpR8SC7YkwZlMHkmdiN60UprIiAEg.5P7sjq61_YrvgaAW.I5JzPQRaU2MvBbda-0I4jKrjaKjgL3NLlXoMNxY-rAk5n5pmNlkCtUtolUYfqsvbKQ8_-4UAVbx4nmEaMUS0YBMyTQGOoXLq-PKxPRUnnVVt7JG-WO3EZPXxJK5IRrKLu7Kpk4wd2PoWxusjqLcUDjY2YAjJuHnDDw-RCFW8BwHT0TOGiY0wGaD2WOhU-EUHb5TagA.KiCm8JLzE8sEbb_2FUf7fQ	2023-12-20 17:55:19.032
111	eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.Bex3VgeJGtYBqo10H7jOUhuc9E5rwAil7IlbpYdueihsGZCTJmlNHgte09Smm5ahI5lG16Er8n7zO4q4F87hZZ1fINtNGaegFp-mlayH_TkdZm-54A_U8WoQQiYgpSn8z71dEh4N9v3p_jhEhbRzCXNp-4oZobRbBLVQ75_AzqdFQzt8Jr6NAAXreIdBxoXGnKQPtJCV1cMVJRyPqQ9s_U3eVLewIJ7FQFpqCpOMZHt20QnKqEpZYHsJPknDw1Sx3O9ZkVKEfXMwPPiMJn5yLJidtQ8JK4osVBMwMShoy453YJMuEHf0FAv29CaJefMahMtnRl4wHU7aqhK31ahvdg.8RDhPdMURkCBhgFR.eaS3odrfcF7kagc5bS6emKRoamNJrwW6okBpy04355JPERCEDpGBYmaK8UMXOTjSAZQoyLBLPp5hQKDmilayiACk5oxIwz_ANixwFkwFdKcQl6GxzQJTbxV1--eSkF_4AtpHzQqPjRy9BN6-1lVm7AJriZtuoNKNZ3PMnv5pySbUYRfSSEWKZqQQmCAFNrstXEB6Wg.8l_vaTkcRUswUFZF7NSfhA	2023-12-20 16:55:19.078
114	eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.WkO7SU0R4XMF1URuL75Ri0IYUZ_hpz2rWrLCQhDqi-iwHCH_LJ5ZrmUEAEeZFU2xLXDPkRldKSs-ine2bQXW7URNY1fNqAu4MA0ybttuc-0daEJ0GRsRgJ5b6jwg-MNJwMErp3D7ysggRsmqkMrqM0vJv0yBDcPva6YEbZ5eHyvzlxWn3rH63nSG9S3SYlKE7weEphfH9eEz3oA_KKArlUWx1YJGQGlTtMnTGbHIJJasdUNSFonw2eQfvIpSMzg4bU0bljWBj4jylzm_IXGYRUue2aLt2rfaqpKYIQpOPv8nGWTQQdMh2k0GS-or-9nEen-uDh3Lc5NH3xInmSxB4Q.BDvoMZe1pUBCZzXp.ESHr8LYTyKlPEdsvPLDhFImvAzdD2qBkb6UjoPjt8gKnZ_hBmTsiSgqEkL9SB8TJ8ksVYgqSO-Eu9wvPhfy1QEwYGVvOdefe7yvebi4p8k--h_cYY7MT6ZvLMsWGfcThSWbdwSDgXEFGDus0vCPgCqpJx7mDyv1yW7_WhDeTwM76JleRSpDOXVF83o0XarQSw3fV3w.w4J_PQ0qXjcsgQ8U_mMYug	2023-12-20 17:11:34.879
115	eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.oX0Xp4giym0owH_6q24CmDIPKAiDGnU8kGkvjYVCOlmeCAMtNKUmHEW6QHsKIlm73aj5Rp8pBd9fUJG_Q0_aiwBN7QKVDsj2woxXeLsxUnCH5MMMTG0ux2AS_I29N14NAb6XR2CJ2QA04CbZtgYH4Anf3crJYCeSJ6ziGW3tXergDyUGf8eh4HIPzgpYQdm7HfHxESEG8PHq6gLH0p5K6aqF7E3RyukvF4vNcEy3w5IqFdmj7Q5qZ13IfavwbkcZjQfOpVEMJsY9GKSZ550fjRPBZGdJW7SCmw47U24wEGk2rk05hnDUt6ykosop4HMC8_BhkcE96UGcIRqnCDet7A.BwN0p0KoNvV-Ibhn.A3j_JfiYr4EC8p4OE4Xt9meAkpFCh9OJUW9QsCczn2bhFXMu4a_Bo0N1Nj72KH-oGrJSe7Fl77DI4nWknLas44V7OzaAWOPti24UcRvP5JkhfIugWvs4C0RNuTpk2rJUBhdF_mzqCWXnw--JJlkZyW2GTJWGYD8y_64hDsp9VzLT4ufGlSa2Z5H1DYkWOtbi6kxTIw.4xP03YWLqbcH66sE65zH9A	2023-12-20 18:09:06.311
113	eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.GbBDLBey-EEVfnQS0jtVgu7razNz_SPX3X0O57M6OA1m4lDDgr-O9pDrW0TnXvbXQwdv848ecxTqskzAhgihKjEaFhAbIRpG-87yE0uq2msIfMA3aRy1k5dWaJ6u9dtg8KEeNCQC0EBZyyXWgvXW8q-BcmbjbOCURjVFS_3bKHcnlzm7lkBdz6gvaIp0vVXJpFU_vSYyflvOz0CBhN_fEdZJcgmfDny-kprkwXmyhlu9YKodR4pOVdD62tTXxoubOKx60AMi-2Uwk3Ui_jYoCzmt9TblPfHJQY6o1ZV3KMFWt_bRj-vg3na_7fY0W6UrbfbGmga4ysg5y9q-CaRPUw.7KunuwfKGOPhLQOU.O-VC3EIyrcEnFWn_n4npZ8aDPuDJCnveqbmWlhisCOVe1v3E2sTJg17aMHu9h_VpnBKlG97EyqLQOFWUkYOuh1xnm06jTU5vkLsQPDpC_pTAOt8NMTJu-OyQ8cygDVJrU4BnR1zLL1QLaSUFumknEQlcHNhRV4-OtKwmc0QRxhuubWGE.WE0wrNaY7VN4qS8ZENP5lQ	2023-12-20 17:11:19.07
\.


--
-- Name: contributor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.contributor_id_seq', 15, true);


--
-- Name: organization_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.organization_id_seq', 7, true);


--
-- Name: organization_request_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.organization_request_id_seq', 18, true);


--
-- Name: otp_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.otp_id_seq', 8, true);


--
-- Name: out_bound_email_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.out_bound_email_id_seq', 17, true);


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_id_seq', 45, true);


--
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.role_id_seq', 2, true);


--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_id_seq', 24, true);


--
-- Name: user_token_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_token_id_seq', 115, true);


--
-- Name: contributor contributor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contributor
    ADD CONSTRAINT contributor_pkey PRIMARY KEY (id);


--
-- Name: organization organization_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organization
    ADD CONSTRAINT organization_pkey PRIMARY KEY (id);


--
-- Name: organization_request organization_request_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organization_request
    ADD CONSTRAINT organization_request_pkey PRIMARY KEY (id);


--
-- Name: otp otp_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.otp
    ADD CONSTRAINT otp_pkey PRIMARY KEY (id);


--
-- Name: out_bound_email out_bound_email_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.out_bound_email
    ADD CONSTRAINT out_bound_email_pkey PRIMARY KEY (id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- Name: user_token user_token_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_token
    ADD CONSTRAINT user_token_pkey PRIMARY KEY (id);


--
-- Name: contributor contributor_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contributor
    ADD CONSTRAINT contributor_user_id_fkey FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- Name: organization_request organization_request_organization_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organization_request
    ADD CONSTRAINT organization_request_organization_id_fkey FOREIGN KEY (organization_id) REFERENCES public.organization(id);


--
-- Name: organization organization_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organization
    ADD CONSTRAINT organization_user_id_fkey FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- Name: product product_request_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_request_id_fkey FOREIGN KEY (request_id) REFERENCES public.organization_request(id);


--
-- Name: user user_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.role(id);


--
-- PostgreSQL database dump complete
--

