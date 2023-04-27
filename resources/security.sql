
-- create role and user --
CREATE ROLE ais_tpocc_view WITH
    NOSUPERUSER
    NOCREATEDB
    NOCREATEROLE
    INHERIT
    NOLOGIN;
CREATE ROLE ais_tpocc_write WITH
    NOSUPERUSER
    NOCREATEDB
    NOCREATEROLE
    INHERIT
    NOLOGIN;
-- Роль доступна только через set role. Напрямую пользователям не назначается
CREATE ROLE ais_tpocc_owner WITH
    NOSUPERUSER
    NOCREATEDB
    NOCREATEROLE
    INHERIT
    NOLOGIN;

GRANT SELECT ON ALL TABLES IN SCHEMA public TO ais_tpocc_view;
GRANT INSERT ON ALL TABLES IN SCHEMA public TO ais_tpocc_write;
GRANT SELECT ON ALL TABLES IN SCHEMA public TO ais_tpocc_write;
GRANT UPDATE ON ALL TABLES IN SCHEMA public TO ais_tpocc_write;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO ais_tpocc_owner;

CREATE ROLE "BelenovA" WITH
    LOGIN
    NOSUPERUSER
    NOCREATEDB
    NOCREATEROLE
    INHERIT
    NOREPLICATION
    CONNECTION LIMIT -1
    PASSWORD '12345';

GRANT ais_tpocc_view, ais_tpocc_write TO "BelenovA";
-- GRANT CREATE ON ALL TABLES IN SCHEMA public TO "BelenovA";

-- encryption --
create extension if not exists pgcrypto; -- this will install the module if not installed
INSERT INTO agents (name) values
    (pgp_sym_encrypt('Johny Smith', 'longsecretencryptionkey')),
    (pgp_sym_encrypt('Bob Marley', 'longsecretencryptionkey'));
SELECT pgp_sym_decrypt(name::bytea, 'longsecretencryptionkey') FROM users WHERE pgp_sym_decrypt(name::bytea, 'longsecretencryptionkey') ILIKE 'johny%'; -- querying for agents whose name start with johny