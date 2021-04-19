# MultiTenancy
This is a Sample Multi Tenancy Project </br>
This repository contains the sources for the blogpost about Spring Boot multi-tenancy.</br>

# Table Script
CREATE TABLE USER_TBL (</br>
    UUID NUMERIC(38) NOT NULL,</br>
    FIRST_NAME VARCHAR2(50),</br>
    LAST_NAME VARCHAR2(50),</br>
    TENANT_ID VARCHAR2(50),</br>
    CONSTRAINT UUID_PK PRIMARY KEY (UUID)</br>
);</br>

CREATE SEQUENCE UUID_SEQ START WITH 1 INCREMENT BY 1 MAXVALUE 99999999 MINVALUE 1 CACHE 20;</br>

CREATE OR REPLACE TRIGGER UUID_SEQ_TR</br>
 BEFORE INSERT ON USER_TBL FOR EACH ROW</br>
 WHEN (NEW.UUID IS NULL)</br>
BEGIN</br>
 SELECT UUID_SEQ.NEXTVAL INTO :NEW.UUID FROM DUAL;</br>
END;</br>
/</br>

# Change DataBase Settings in tenant properties</br>
name=<TENANT_ID></br>
datasource.jdbcUrl=jdbc:oracle:thin:@localhost:1521:xe</br>
datasource.username=SPLEAR2</br>
datasource.password=SPLEAR2</br>
datasource.driverClassName: oracle.jdbc.OracleDriver</br>

# Start Application</br>
run the Spring boot application from the Eclipse or run as </br>
java -jar /build/libs/MultiTenancy-0.0.1-SNAPSHOT.jar</br>

# </br>
Happy Learning :)</br>
