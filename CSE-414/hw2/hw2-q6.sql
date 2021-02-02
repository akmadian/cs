SELECT
    MAX(flights.price) as price,
    carriers.name as carrier
FROM FLIGHTS 
    INNER JOIN CARRIERS ON FLIGHTS.carrier_id = CARRIERS.cid
WHERE flights.origin_city = "Seattle WA"
    AND flights.dest_city = "New York NY"
GROUP BY flights.carrier_id;