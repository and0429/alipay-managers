drop table if exists t_distributor;
create table if not exists t_distributor(
	d_id varchar(40) primary key,
	d_name varchar(20),
	d_addr varchar(50),
	d_manager varchar(5),
	d_tel varchar(20),
	d_parent_id varchar(40),
	d_haschild int
);

insert into t_distributor (d_id, d_name, d_haschild) values ('0', '总代理', 1);