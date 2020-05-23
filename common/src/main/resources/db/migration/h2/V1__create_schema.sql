CREATE TABLE "USER"  (
id BIGINT NOT NULL,
userid VARCHAR(32) NOT NULL PRIMARY KEY,
tenant_id VARCHAR(20),
name VARCHAR(32),
email VARCHAR(10),
time_created TIMESTAMP,
time_updated TIMESTAMP,
version INT
);

create sequence user_id_seq start with 1 increment by 1;

CREATE TABLE "TENANT"  (
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

create sequence tenant_id_seq start with 1 increment by 1;