drop table if exists orders;
create table orders (
    id varchar,
    order_id varchar,
    email_address varchar,
    sku varchar,
    quantity int,
    price numeric,
    item_name varchar,
    return_status varchar,
    primary key (id)
);

drop table if exists returns;
create table returns (
    id varchar,
    order_id varchar,
    email_address varchar,
    refund_amount numeric,
    status varchar,
    primary key (id)
);