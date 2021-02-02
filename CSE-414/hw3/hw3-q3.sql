SELECT y.origin as origin_city,
  CAST(x.num AS float)/y.total as percentage
FROM
(
  SELECT f1.origin_city, COUNT(*) as num
  FROM flights as f1
  WHERE ISNULL(f1.actual_time, 181) < 180
  GROUP BY f1.origin_city
) x
RIGHT OUTER JOIN
(
  SELECT f2.origin_city as orig, ISNULL(COUNT(*), 0) as total
  FROM flights as f2
  GROUP BY f2.origin_city
) y
ON x.origin_city=y.origin
ORDER BY percentage;
