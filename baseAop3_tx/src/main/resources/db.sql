

create table accounts
(
    accountid int primary key auto_increment,
    balance    numeric(10, 2) check ( balance > 0 )
);



create table oprecord
(
    id         int primary key auto_increment,
    accountid  int references accounts (accountid),
    opmoney    numeric(10, 2),
    optime     datetime,
    optype     enum ( 'deposite', 'withdraw', 'transfer') not null,  -- 存钱， 取钱， 转账
    transferid int
);


insert into accounts(balance)
values (100);
insert into accounts(balance)
values (1);

update accounts
set balance =balance + 100
where accountid = 1;
insert into oprecord(accountid, opmoney, optime, optype, transferid)
values (1, 100, now(), 'deposite', null);

select *
from accounts;
select * from oprecord;
