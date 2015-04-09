drop table if exists t_pay_month_loginer;
create table if not exists t_pay_month_loginer(
	pmc_id varchar(40) primary key,
	pmc_month varchar(15),
	pmc_loginer_id varchar(40),
	pmc_total_money float
);