SELECT SUM(departure_delay) AS delay, name
FROM FLIGHTS CROSS JOIN CARRIERS
    ON FLIGHTS.carrier_id = CARRIERS.cid
GROUP BY carrier_id;