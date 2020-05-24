CREATE TABLE "user"  (
id BIGINT NOT NULL,
userid VARCHAR(32) NOT NULL PRIMARY KEY,
tenant_id VARCHAR(20),
name VARCHAR(32),
email VARCHAR(32),
time_created TIMESTAMP,
time_updated TIMESTAMP,
version INT
);

CREATE SEQUENCE user_id_seq;


CREATE TABLE "tenant"  (
id BIGINT NOT NULL,
tenant_id VARCHAR(20) NOT NULL PRIMARY KEY,
organization_name VARCHAR(30),
realm VARCHAR(30),
tenant_config VARCHAR(200),
locked BOOLEAN,
time_created TIMESTAMP,
time_updated TIMESTAMP,
version INT
);

CREATE SEQUENCE tenant_id_seq;


CREATE TABLE "bid"  (
bid_id BIGINT NOT NULL PRIMARY KEY,
tenant_id VARCHAR(20),
bidname VARCHAR(32),
bidwinner VARCHAR(32),
bidstatus VARCHAR(10),
time_created TIMESTAMP,
time_updated TIMESTAMP,
version INT
);

create sequence bid_id_seq;


-- TODO proper sequencing to be added later