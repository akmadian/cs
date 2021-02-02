SELECT * 
FROM FLIGHTS
WHERE origin_city = "Seattle WA"
    AND dest_city = "Boston MA"
    AND month_id = 7
    AND day_of_month = 15;