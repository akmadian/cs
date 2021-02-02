SELECT 
    weekdays.day_of_week AS day_of_week, 
    AVG(arrival_delay) AS delay
FROM FLIGHTS INNER JOIN WEEKDAYS ON flights.day_of_week_id = weekdays.did
GROUP BY flights.day_of_week_id
ORDER BY AVG(flights.arrival_delay) DESC
LIMIT 1;