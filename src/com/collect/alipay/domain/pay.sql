drop table if exists t_pay;
create table if not exists t_pay(
	p_id varchar(40) primary key,
	p_category varchar(40),
	p_amount float,
	p_pay_date timestamp,
	p_loginer varchar(20),
	p_pay_Type int
);