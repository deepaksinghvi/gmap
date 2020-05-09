CREATE TABLE USER  (
id BIGINT NOT NULL,
tenant_id VARCHAR(20),
userid VARCHAR(32) NOT NULL PRIMARY KEY,
name VARCHAR(32),
email VARCHAR(10),
time_created TIMESTAMP,
time_updated TIMESTAMP,
version INT
);

create sequence user_seq start with 1 increment by 100;

CREATE TABLE TENANT  (
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

create sequence tenant_seq start with 1 increment by 1;