drop table if exists t_deduct;
create table if not exists t_deduct(
	de_id varchar(40) primary key,
	de_val float
);

insert into t_deduct(de_id, de_val) values ('2aac3a695f254c6d97ad513e624cfc55', 0.03);