--
-- PostgreSQL database dump
--

\restrict 1FyHU8cfR6zgUo8iaM1uv5wyJRc5V5qeUjjHbghzaf6YQSYsl5OxQjBcJITte4n

-- Dumped from database version 17.11 (Homebrew)
-- Dumped by pg_dump version 18.6

-- Started on 2026-10-19 20:19:16 EEST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 16419)
-- Name: apartment; Type: TABLE; Schema: public; Owner: joonassavolainen
--

CREATE TABLE public.apartment (
    id bigint NOT NULL,
    address character varying(255) NOT NULL,
    area double precision NOT NULL,
    city character varying(255) NOT NULL,
    floor integer NOT NULL,
    rooms integer NOT NULL,
    type character varying(255) NOT NULL,
    year_built integer NOT NULL,
    zip_code character varying(255) NOT NULL,
    seller_id bigint NOT NULL,
    price integer,
    CONSTRAINT apartment_floor_check CHECK ((floor >= '-1'::integer)),
    CONSTRAINT apartment_rooms_check CHECK ((rooms >= 1)),
    CONSTRAINT apartment_year_built_check CHECK ((year_built >= 1800))
);



--
-- TOC entry 221 (class 1259 OID 16450)
-- Name: apartment_seq; Type: SEQUENCE; Schema: public; Owner: joonassavolainen
--

CREATE SEQUENCE public.apartment_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.apartment_seq OWNER TO joonassavolainen;

--
-- TOC entry 218 (class 1259 OID 16429)
-- Name: apartment_user; Type: TABLE; Schema: public; Owner: joonassavolainen
--

CREATE TABLE public.apartment_user (
    id bigint NOT NULL,
    password character varying(255),
    role character varying(255),
    username character varying(255)
);



--
-- TOC entry 222 (class 1259 OID 16451)
-- Name: apartment_user_seq; Type: SEQUENCE; Schema: public; Owner: joonassavolainen
--

CREATE SEQUENCE public.apartment_user_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.apartment_user_seq OWNER TO joonassavolainen;

--
-- TOC entry 219 (class 1259 OID 16436)
-- Name: favorite; Type: TABLE; Schema: public; Owner: joonassavolainen
--

CREATE TABLE public.favorite (
    id bigint NOT NULL,
    apartment_id bigint,
    user_id bigint
);



--
-- TOC entry 223 (class 1259 OID 16452)
-- Name: favorite_seq; Type: SEQUENCE; Schema: public; Owner: joonassavolainen
--

CREATE SEQUENCE public.favorite_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.favorite_seq OWNER TO joonassavolainen;

--
-- TOC entry 220 (class 1259 OID 16441)
-- Name: seller; Type: TABLE; Schema: public; Owner: joonassavolainen
--

CREATE TABLE public.seller (
    id bigint NOT NULL,
    email character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    phone_number character varying(255)
);



--
-- TOC entry 224 (class 1259 OID 16453)
-- Name: seller_seq; Type: SEQUENCE; Schema: public; Owner: joonassavolainen
--

CREATE SEQUENCE public.seller_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seller_seq OWNER TO joonassavolainen;

--
-- TOC entry 3699 (class 2606 OID 16428)
-- Name: apartment apartment_pkey; Type: CONSTRAINT; Schema: public; Owner: joonassavolainen
--

ALTER TABLE ONLY public.apartment
    ADD CONSTRAINT apartment_pkey PRIMARY KEY (id);


--
-- TOC entry 3701 (class 2606 OID 16435)
-- Name: apartment_user apartment_user_pkey; Type: CONSTRAINT; Schema: public; Owner: joonassavolainen
--

ALTER TABLE ONLY public.apartment_user
    ADD CONSTRAINT apartment_user_pkey PRIMARY KEY (id);


--
-- TOC entry 3705 (class 2606 OID 16440)
-- Name: favorite favorite_pkey; Type: CONSTRAINT; Schema: public; Owner: joonassavolainen
--

ALTER TABLE ONLY public.favorite
    ADD CONSTRAINT favorite_pkey PRIMARY KEY (id);


--
-- TOC entry 3707 (class 2606 OID 16447)
-- Name: seller seller_pkey; Type: CONSTRAINT; Schema: public; Owner: joonassavolainen
--

ALTER TABLE ONLY public.seller
    ADD CONSTRAINT seller_pkey PRIMARY KEY (id);


--
-- TOC entry 3703 (class 2606 OID 16449)
-- Name: apartment_user uk7vqu35cuynmstfswipsbsydqx; Type: CONSTRAINT; Schema: public; Owner: joonassavolainen
--

ALTER TABLE ONLY public.apartment_user
    ADD CONSTRAINT uk7vqu35cuynmstfswipsbsydqx UNIQUE (username);


--
-- TOC entry 3709 (class 2606 OID 16464)
-- Name: favorite fk31h8hkkfl8j644ek54mi8bacf; Type: FK CONSTRAINT; Schema: public; Owner: joonassavolainen
--

ALTER TABLE ONLY public.favorite
    ADD CONSTRAINT fk31h8hkkfl8j644ek54mi8bacf FOREIGN KEY (user_id) REFERENCES public.apartment_user(id);


--
-- TOC entry 3710 (class 2606 OID 16459)
-- Name: favorite fkdexa7qhmkdl95thmtr6vl0qaf; Type: FK CONSTRAINT; Schema: public; Owner: joonassavolainen
--

ALTER TABLE ONLY public.favorite
    ADD CONSTRAINT fkdexa7qhmkdl95thmtr6vl0qaf FOREIGN KEY (apartment_id) REFERENCES public.apartment(id);


--
-- TOC entry 3708 (class 2606 OID 16454)
-- Name: apartment fkli0ak4wegs057t8c25r8qlqb0; Type: FK CONSTRAINT; Schema: public; Owner: joonassavolainen
--

ALTER TABLE ONLY public.apartment
    ADD CONSTRAINT fkli0ak4wegs057t8c25r8qlqb0 FOREIGN KEY (seller_id) REFERENCES public.seller(id);


-- Completed on 2026-10-19 20:19:17 EEST

--
-- PostgreSQL database dump complete
--

\unrestrict 1FyHU8cfR6zgUo8iaM1uv5wyJRc5V5qeUjjHbghzaf6YQSYsl5OxQjBcJITte4n

