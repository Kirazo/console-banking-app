create sequence id_sequence
start 100
increment 1

create sequence transId_sequence
start 1000
increment 1

alter sequence id_sequence
restart with 100

alter sequence transId_sequence
restart with 1000


create table customer(
custId SERIAL primary key not null,
custfName varchar(20) not null,
custlName varchar(20) not null,
custPw varchar(20) not null,
balance integer,
custApproved varchar(20)
)

create table employee(
empId SERIAL primary key not null,
empfName varchar(20) not null,
emplName varchar(20) not null,
empPw varchar(20) not null
)

create table transactions(
transId SERIAL primary key not null,
senderId integer not null,
receiverId integer not null,
amount integer not null,
date varchar(20)
)


select * from customer

select * from employee

select * from transactions

drop table customer 

drop table employee 

drop table transactions 

SELECT last_value FROM id_sequence

SELECT last_value FROM transId_sequence