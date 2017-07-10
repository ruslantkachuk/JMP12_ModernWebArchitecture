drop table account if exists;

create table account (ID bigint identity primary key, NUMBER varchar(9),
                        NAME varchar(50) not null, BALANCE NUMERIC (8,2), unique(NUMBER));
                        
ALTER TABLE account ALTER COLUMN BALANCE SET DEFAULT 0.0;
