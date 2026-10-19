--
-- PostgreSQL database dump
--

\restrict SakAHL3PEri2FnKyK7gOWrcRkCa5pVYbJM0DSP4YFqIa1M27cZpjLrrNTWYtNwR

-- Dumped from database version 17.11 (Homebrew)
-- Dumped by pg_dump version 18.6

-- Started on 2026-10-19 20:34:15 EEST

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

--
-- TOC entry 3856 (class 0 OID 16441)
-- Dependencies: 220
-- Data for Name: seller; Type: TABLE DATA; Schema: public; Owner: joonassavolainen
--

INSERT INTO public.seller VALUES (52, 'mikko.mallikas@esimerkki.com', 'Mikko', 'Mallikas', '+358401112233');
INSERT INTO public.seller VALUES (53, 'erkki.esimerkki@malli.com', 'Erkki', 'Esimerkki', '+358502223344');


--
-- TOC entry 3853 (class 0 OID 16419)
-- Dependencies: 217
-- Data for Name: apartment; Type: TABLE DATA; Schema: public; Owner: joonassavolainen
--

INSERT INTO public.apartment VALUES (52, 'Prinssintie 4 B', 51.5, 'Helsinki', 4, 2, 'Kerrostalo', 1956, '00820', 52, 199900);
INSERT INTO public.apartment VALUES (53, 'Sirkkalankatu 4', 73.5, 'Turku', 6, 3, 'Kerrostalo', 1968, '20520', 53, 278000);
INSERT INTO public.apartment VALUES (54, 'Aspinniemenkatu 3 A 19', 45, 'Tampere', 5, 2, 'Kerrostalo', 2026, '33180', 52, 321200);
INSERT INTO public.apartment VALUES (405, 'Testikatu 12', 30, 'Helsinki', 3, 33, 'Kerrostalo', 2020, '00100', 52, 390000);


--
-- TOC entry 3855 (class 0 OID 16436)
-- Dependencies: 219
-- Data for Name: favorite; Type: TABLE DATA; Schema: public; Owner: joonassavolainen
--



--
-- TOC entry 3866 (class 0 OID 0)
-- Dependencies: 221
-- Name: apartment_seq; Type: SEQUENCE SET; Schema: public; Owner: joonassavolainen
--

SELECT pg_catalog.setval('public.apartment_seq', 451, true);


--
-- TOC entry 3867 (class 0 OID 0)
-- Dependencies: 222
-- Name: apartment_user_seq; Type: SEQUENCE SET; Schema: public; Owner: joonassavolainen
--

SELECT pg_catalog.setval('public.apartment_user_seq', 51, true);


--
-- TOC entry 3868 (class 0 OID 0)
-- Dependencies: 223
-- Name: favorite_seq; Type: SEQUENCE SET; Schema: public; Owner: joonassavolainen
--

SELECT pg_catalog.setval('public.favorite_seq', 351, true);


--
-- TOC entry 3869 (class 0 OID 0)
-- Dependencies: 224
-- Name: seller_seq; Type: SEQUENCE SET; Schema: public; Owner: joonassavolainen
--

SELECT pg_catalog.setval('public.seller_seq', 101, true);


-- Completed on 2026-10-19 20:34:15 EEST

--
-- PostgreSQL database dump complete
--

\unrestrict SakAHL3PEri2FnKyK7gOWrcRkCa5pVYbJM0DSP4YFqIa1M27cZpjLrrNTWYtNwR

