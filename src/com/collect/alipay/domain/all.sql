use alipay;

drop table if exists t_cust;
create table if not exists t_cust(
	c_id varchar(40) primary key,
	c_name varchar(20),
	c_addr varchar(50),
	c_manager varchar(5),
	c_tel varchar(20),
	c_distributor_id varchar(40),
	c_alipay_username varchar(50),
	c_alipay_password varchar(50)
);


drop table if exists t_distributor;
create table if not exists t_distributor(
	d_id varchar(40) primary key,
	d_name varchar(20),
	d_addr varchar(50),
	d_manager varchar(5),
	d_tel varchar(20),
	d_parent_id varchar(40),
	d_haschild int,
	d_deduct float
);

insert into t_distributor (d_id, d_name, d_haschild) values ('0', '总代理', 0);

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

insert into t_loginer(l_id, l_username, l_password, l_role, l_custOrDistributorId, l_status) 
values ('0', 'admin', '96e79218965eb72c92a549dd5a330112', 1, '0', 0);

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

-- 商户月账单
drop table if exists t_pay_month_cust;
create table if not exists t_pay_month_cust(
	pmc_id varchar(40) primary key,
	pmc_month varchar(15),
	pmc_cust_id varchar(40),
	pmc_total_money float,
	pmc_deduct float,
	pmc_deduct_money float
);

-- 分销商月账单
drop table if exists t_pay_month_distributor;
create table if not exists t_pay_month_distributor(
	pmd_id varchar(40) primary key,
	pmd_month varchar(15),
	pmd_distributor_id varchar(40),
	pmd_total_money float,
	pmd_deduct float,
	pmd_deduct_money float
);


