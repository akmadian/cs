SELECT DISTINCT origin_city
FROM flights
GROUP BY origin_city
HAVING MAX(actual_time)<180
ORDER BY origin_city;
