CREATE TABLE orders(
                        orderid bigserial primary key,
                        orderdescription varchar(50) ,
                        user_id BIGINT references persons(id)
);

