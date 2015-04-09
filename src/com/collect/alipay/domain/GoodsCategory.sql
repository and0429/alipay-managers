drop table if exists t_goods_category;
create table if not exists t_goods_category(
	gc_id varchar(40) primary key,
	gc_name varchar(10),
	gc_cust_id varchar(40)
);