create or replace view  t_pay_month_cust as 
(
    select 
    sum(pmc_total_money) as pc_total_money,
    l_custOrDistributorId as pc_custid,
    pmc_id as pc_id,
    pmc_month as pc_month
    from 
    t_pay_month_loginer 
    left join t_loginer 
    on pmc_loginer_id = l_id 
    group by l_custOrDistributorId
);