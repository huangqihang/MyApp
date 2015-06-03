drop database if exists jdbctest;
create database jdbctest;

drop table if exists t_user;
create table t_user(
	id int unsigned primary key auto_increment,
	name varchar(100) not null,
	age int not null,
	sex char(1) not null COMMENT "男 1  女0 其它 2", 
	photo varchar(50) COMMENT "photo path on disk",
	address varchar(500),
	birthday date COMMENT "yyyy-MM-dd",
	reg_date datetime COMMENT "yyyy-MM-dd HH:mm:ss"
);

insert into t_user(name, age, sex, photo, address, birthday, reg_date)
values
('张三0', 20, '1', 'c:\user\phtot\zs0.jsp', '北京市', '2000-1-1', '2000-1-1  19:23:01'),
('张三1', 21, '1', 'c:\user\phtot\zs1.jsp', '北京市', '2000-1-1', '2000-1-1  19:23:01'),
('张三2', 22, '0', 'c:\user\phtot\zs2.jsp', '北京市', '2000-1-1', '2000-1-1  19:23:01'),
('张三3', 23, '0', 'c:\user\phtot\zs3.jsp', '北京市', '2000-1-1', '2000-1-1  19:23:01'),
('张三4', 24, '2', 'c:\user\phtot\zs4.jsp', '北京市', '2000-1-1', '2000-1-1  19:23:01');