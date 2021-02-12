-- Part 4.1
CREATE TABLE frumble (
  name varchar(256),
  discount int,
  month varchar(10),
  price int
);

-- Part 4.2
-- name -> price
SELECT * FROM frumble A, frumble B
WHERE A.name = B.name AND A.price != B.price;

-- month -> discount
SELECT * FROM frumble A, frumble B
WHERE A.month = B.month AND A.discount != B.discount;

-- name -> price, month AND month -> discount implies that
-- name, month -> price, discount

-- name, discount -> price, month
SELECT * FROM frumble A, frumble B
WHERE A.name = B.name and A.discount = B.discount
  AND A.price != B.price AND A.month != B.month;

-- month, price -> discount, name
SELECT * FROM frumble A, frumble B
WHERE A.month = B.month AND A.price = B.price
  AND A.discount != B.discount AND A.name != B.name;

-- FDs:
-- name           -> price
-- month          -> discount
-- name, month    -> price, discount
-- name, discount -> price, month
-- month, price   -> discount, name

-- Part 4.3
-- S(name, price, discount, month)
-- {name}+  = {name, price}
-- {name}+ != {name}
-- {name}+ != {name, price, discount, month}

-- We can decompose S(name, price, discount, month) as S1(name, price) and S2(name, discount, month)
-- For S2(name, discount, month)
-- {month}+  = {month, discount}
-- {month}+ != {month}
-- {month}+ != {name, discount, month}

-- We can then decompose S2(name, discount, month) as S2A(month, discount) and S2B(month, name)

-- Complete BCNF is:
-- S1(name, price)
-- S2A(month, discount)
-- S2B(month, name)

CREATE TABLE namePriceMap (
  name varchar(256) PRIMARY KEY,
  price int
);

CREATE TABLE monthDiscountMap (
  dicount int,
  month varchar(10)  PRIMARY KEY
);

CREATE TABLE monthNameMap (
  name varchar(256),
  month varchar(10),
  FOREIGN KEY(name) REFERENCES namePriceMap(name),
  FOREIGN KEY(month) REFERENCES monthDiscountMap(month)
);

-- Part 4.4
INSERT INTO namePriceMap
SELECT DISTINCT name, Price FROM frumble;

-- 36 rows
SELECT COUNT(*) FROM namePriceMap;

INSERT INTO monthDiscountMap
SELECT DISTINCT discount, month FROM frumble;

-- 12 rows
SELECT COUNT(*) FROM monthDiscountMap;

INSERT INTO monthNameMap
SELECT DISTINCT name, month FROM frumble;

-- 426 rows
SELECT COUNT(*) FROM monthNameMap;

-- 426 rows
SELECT A.name, A.price, B.month, B.discount
FROM namePriceMap A, monthDiscountMap B, monthNameMap C
WHERE A.name = C.name AND B.month = C.month
