SELECT DISTINCT c.name AS carrier
FROM carriers AS c
WHERE c.cid IN (
  SELECT f.carrier_id
  FROM flights as f
  WHERE f.origin_city = "Seattle WA"
    AND f.dest_city   = "San Francisco CA"
);
