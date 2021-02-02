SELECT DISTINCT flight_num
FROM FLIGHTS INNER JOIN CARRIERS ON flights.carrier_id = carriers.cid
WHERE origin_city = "Seattle WA"
    AND dest_city = "Boston MA"
    AND day_of_week_id = 1
    AND carriers.name = "Alaska Airlines Inc.";