create database if not exists banking_system;
create user 'BSUser'@'%' identified by 'BSPass';
grant all on banking_system.* to 'BSUser'@'%';


