SELECT
    carriers.name,
    100.0 * COUNT(flights.canceled) / 
        (SELECT COUNT(flights.canceled) FROM FLIGHTS WHERE flights.canceled = 1) AS percentage
FROM FLIGHTS LEFT JOIN CARRIERS ON flights.carrier_id = carriers.cid
WHERE flights.origin_city = "Seattle WA"
GROUP BY flights.carrier_id
HAVING 1.0 * COUNT(flights.canceled) /
    (SELECT COUNT(flights.canceled) FROM FLIGHTS WHERE flights.canceled = 1) > 0.05
ORDER BY
    percentage ASC;
