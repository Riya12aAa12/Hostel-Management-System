create database hostelmanagementsystem;
show databases;
use hostelmanagementsystem;
create table login(username varchar(25), password varchar(25));
insert into login values('admin', '12345');
insert into login values('komal', '123456');

select * from login;

CREATE TABLE room_allocation (
  room_number VARCHAR(100) NOT NULL,
  bed_number INT,
  student_id INT,
  room_type VARCHAR(50),
  name VARCHAR(100) NOT NULL,
  marks INT,
  branch VARCHAR(50),
  year VARCHAR(10)
);
select * from room_allocation
Drop TABLE room_allocation;

