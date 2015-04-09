drop table if exists t_loginer;
create table if not exists t_loginer(
	l_id varchar(40) primary key,
	l_username varchar(20) not null,
	l_password varchar(40) not null,
	l_role int,
	l_custOrDistributorId varchar(40)
);

insert into t_loginer(l_id, l_username, l_password, l_role, l_custOrDistributorId) 
values ('0', 'admin', '96e79218965eb72c92a549dd5a330112', 1, '0');