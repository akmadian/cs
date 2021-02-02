SELECT SUM(capacity) as capacity
FROM FLIGHTS
WHERE origin_city = "Seattle WA" OR origin_city = "San Fancisco CA"
    AND dest_city = "San Francisco CA" OR dest_city = "Seattle WA"
    AND day_of_month = 10
    AND month_id = 7;
