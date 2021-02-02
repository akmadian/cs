SELECT
    DISTINCT carriers.name as name
FROM FLIGHTS INNER JOIN CARRIERS ON flights.carrier_id = carriers.cid
GROUP BY month_id, day_of_month, carrier_id
HAVING COUNT(*) > 1000;