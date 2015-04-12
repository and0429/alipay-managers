use alipay;

drop table if exists t_cust;
create table if not exists t_cust(
	c_id varchar(40) primary key,
	c_name varchar(20),
	c_addr varchar(50),
	c_manager varchar(5),
	c_tel varchar(20),
	c_distributor_id varchar(40)
);


drop table if exists t_deduct;
create table if not exists t_deduct(
	de_id varchar(40) primary key,
	de_val float
);

insert into t_deduct(de_id, de_val) values ('2aac3a695f254c6d97ad513e624cfc55', 0.03);

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

drop table if exists t_goods_category;
create table if not exists t_goods_category(
	gc_id varchar(40) primary key,
	gc_name varchar(10),
	gc_cust_id varchar(40)
);

drop table if exists t_loginer;
create table if not exists t_loginer(
	l_id varchar(40) primary key,
	l_username varchar(20) not null,
	l_password varchar(40) not null,
	l_role int,
	l_custOrDistributorId varchar(40),
	l_status int
);

insert into t_loginer(l_id, l_username, l_password, l_role, l_custOrDistributorId) 
values ('0', 'admin', '96e79218965eb72c92a549dd5a330112', 1, '0');

-- 支付详情
drop table if exists t_pay;
create table if not exists t_pay(
	p_id varchar(40) primary key,
	p_category varchar(40),
	p_amount float,
	p_pay_date timestamp default CURRENT_TIMESTAMP,
	p_loginer varchar(20),
	p_pay_Type int,
	p_buyer varchar(30),
	p_trade_no varchar(70),
	p_cust_id varchar(40),
	p_status varchar(20),
	p_refund_total float,
	p_refundTime varchar(30)
);


drop table if exists t_pay_month_loginer;
create table if not exists t_pay_month_loginer(
	pmc_id varchar(40) primary key,
	pmc_month varchar(15),
	pmc_loginer_id varchar(40),
	pmc_total_money float
);
