SELECT DISTINCT f4.dest_city AS city
FROM flights AS f4
WHERE f4.dest_city NOT IN (
  -- Cities that CAN be reached by 1 stop, just copied from the last question
  SELECT DISTINCT f3.dest_city
  FROM flights AS f2, flights AS f3
  WHERE f2.origin_city  = "Seattle WA"
    AND f2.dest_city   != "Seattle WA"
    AND f3.origin_city != "Seattle WA"
    AND f3.dest_city   != "Seattle WA"
    AND f2.dest_city    = f3.origin_city
    AND f3.dest_city NOT IN (
      SELECT DISTINCT f1.dest_city
      FROM flights AS f1
      WHERE f1.origin_city = "Seattle WA"
    )
)
AND f4.dest_city NOT IN (
  -- Cities that can be reached directly
  SELECT DISTINCT f1.dest_city
  FROM flights as f1
  WHERE f1.origin_city = "Seattle WA"
);
