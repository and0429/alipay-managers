drop table if exists t_cust;
create table if not exists t_cust(
	c_id varchar(40) primary key,
	c_name varchar(20),
	c_addr varchar(50),
	c_manager varchar(5),
	c_tel varchar(20),
	c_distributor_id varchar(40)
);