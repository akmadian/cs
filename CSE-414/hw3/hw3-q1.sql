SELECT distinct longest_flight.origin_city as origin_city,
all_flights.dest_city as dest_city,
longest_flight.max_time as time
FROM
(
  SELECT f.origin_city, MAX(actual_time) as max_time
  FROM flights as f
  GROUP BY f.origin_city
) longest_flight
JOIN
(
  SELECT f2.origin_city AS origin, f2.dest_city, f2.actual_time
  FROM flights as f2
) all_flights
ON longest_flight.origin_city=all_flights.origin and longest_flight.max_time=all_flights.actual_time
ORDER BY origin_city, dest_city;

-- PREVIOUS TRIES
/*WITH longestflight AS (
  SELECT origin_city as origin, dest_city as dest, MAX(actual_time) AS time
  FROM flights
  GROUP BY origin, dest
)
SELECT DISTINCT f.origin_city, f.dest_city, actual_time, COUNT(*)
FROM flights AS f, longestflight AS lf
WHERE f.origin_city=lf.origin
  AND f.dest_city=lf.dest
  AND f.actual_time=lf.time
  AND f.actual_time>180
  AND f.canceled=0
ORDER BY origin_city ASC, dest_city ASC;
*/
/*
select f.origin_city, f.dest_city, f.actual_time, MAX(lf.actual_time)
from flights as f, flights as lf
where f.fid = lf.fid AND f.canceled=0 AND f.actual_time>180
group by lf.fid, f.origin_city, lf.actual_time
having f.fid=lf.fid AND f.actual_time=lf.actual_time;
*/

/*with longest as (
  select fid, origin_city, dest_city, actual_time, canceled
  from flights
  where
)
select f.origin_city,
  f.dest_city,
  f.actual_time,
  mc
from flights as f,
  meets_criteria as mc
where f.fid = mc.fid;*/

/*
select distinct fid, origin_city, dest_city, actual_time
from flights
where actual_time>180 and canceled=0
order by origin_city asc, dest_city asc;*/
