# MultiTenancy
This is a Sample Multi Tenancy Project
This repository contains the sources for the blogpost about Spring Boot multi-tenancy.

# Table Script
CREATE TABLE USER_TBL (
    UUID NUMERIC(38) NOT NULL,
    FIRST_NAME VARCHAR2(50),
    LAST_NAME VARCHAR2(50),
    TENANT_ID VARCHAR2(50),
    CONSTRAINT UUID_PK PRIMARY KEY (UUID)
);

CREATE SEQUENCE UUID_SEQ START WITH 1 INCREMENT BY 1 MAXVALUE 99999999 MINVALUE 1 CACHE 20;

CREATE OR REPLACE TRIGGER UUID_SEQ_TR
 BEFORE INSERT ON USER_TBL FOR EACH ROW
 WHEN (NEW.UUID IS NULL)
BEGIN
 SELECT UUID_SEQ.NEXTVAL INTO :NEW.UUID FROM DUAL;
END;
/

# Change DataBase Settings in tenant properties
name=<TENANT_ID>
datasource.jdbcUrl=jdbc:oracle:thin:@localhost:1521:xe
datasource.username=SPLEAR2
datasource.password=SPLEAR2
datasource.driverClassName: oracle.jdbc.OracleDriver

# Start Application
run the Spring boot application from the Eclipse or run as 
java -jar /build/libs/MultiTenancy-0.0.1-SNAPSHOT.jar

# 
Happy Learning :)
