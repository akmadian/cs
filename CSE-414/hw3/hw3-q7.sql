SELECT DISTINCT c.name AS carrier
FROM flights AS f, carriers AS c
WHERE f.origin_city = "Seattle WA"
  AND f.dest_city   = "San Francisco CA"
  AND c.cid = f.carrier_id;
