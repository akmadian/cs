CREATE TABLE FLIGHTS(
    fid             INT,
    month_id        INT       CHECK(month_id > 0 AND month_id < 13),
    day_of_month    INT       CHECK(day_of_month > 0 AND day_of_month < 32),
    day_of_week_id  INT       CHECK(day_of_week_id > 0 AND day_of_week_id < 8),
    carrier_id      varchar(7), 
    flight_num      INT,
    origin_city     varchar(34), 
    origin_state    varchar(47), 
    dest_city       varchar(34), 
    dest_state      varchar(46), 
    departure_delay INT, -- in mins
    taxi_out        INT, -- in mins
    arrival_delay   INT, -- in mins
    canceled        INT, -- 1 means canceled
    actual_time     INT, -- in mins
    distance        INT, -- in miles
    capacity        INT, 
    price           INT, -- in $
    PRIMARY KEY(fid),
    FOREIGN KEY(carrier_id) REFERENCES CARRIERS(cid),
    FOREIGN KEY(month_id)   REFERENCES MONTHS(mid),
    FOREIGN KEY(day_of_week_id) REFERENCES WEEKDAYS(did)
);

CREATE TABLE CARRIERS (cid varchar(7),
    name varchar(83),
    PRIMARY KEY(cid)
);

CREATE TABLE MONTHS (mid int,
    month varchar(9),
    PRIMARY KEY(mid)
);

CREATE TABLE WEEKDAYS (did int,
    day_of_week varchar(9),
    PRIMARY KEY(did)
);
