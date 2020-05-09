CREATE TABLE BID  (
bid_id BIGINT NOT NULL PRIMARY KEY,
tenant_id VARCHAR(20),
bidstatus VARCHAR(10),
time_created TIMESTAMP,
time_updated TIMESTAMP,
version INT
);

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

create sequence bid_seq start with 1 increment by 100;
create sequence tenant_seq start with 1 increment by 1;