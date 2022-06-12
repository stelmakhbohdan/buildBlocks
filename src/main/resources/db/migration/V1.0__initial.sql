CREATE TABLE persons(
                        id bigserial primary key,
                        USER_NAME varchar(50) not null unique,
                        FIRST_NAME varchar(50) not null ,
                        LAST_NAME varchar(50) not null ,
                        EMAIL varchar(50) not null ,
                        ROLE varchar(50) not null ,
                        SSN varchar(50) not null unique
);

