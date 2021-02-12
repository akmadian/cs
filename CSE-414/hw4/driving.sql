CREATE TABLE InsuranceCo (
  name varchar(20) PRIMARY KEY,
  phone INT
);

CREATE TABLE Person (
  ssn INT PRIMARY KEY,
  name varchar(20)
);

CREATE TABLE Driver (
  ssn INT PRIMARY KEY REFERENCES Person(ssn),
  driverID INT
);

CREATE TABLE NonProfessionalDriver (
  ssn INT PRIMARY KEY REFERENCES Driver(ssn)
);

CREATE TABLE ProfessionalDriver (
  ssn INT PRIMARY KEY REFERENCES Driver(ssn)
  medicalHistory varchar(200)
);

CREATE TABLE Vehicle (
  licensePlace varchar(20) PRIMARY KEY,
  year INT,
  insuranceco_name varchar(20) FOREIGN KEY REFERENCES InsuranceCo(name),
  ssn INT FOREIGN KEY REFERENCES Person(ssn),
  maxLiability REAL
);

CREATE TABLE Car (
  licensePlace varchar(20) PRIMARY KEY REFERENCES InsuranceCo(name),
  make varchar(10)
);

CREATE TABLE Truck (
  licensePlace varchar(20) PRIMARY KEY REFERENCES Vehicle(licensePlace),
  capacity varchar(10),
  ssn INT FOREIGN KEY REFERENCES ProfessionalDriver(ssn)
);

CREATE TABLE Drives (
  licensePlace varchar(20) REFERENCES Car(licensePlace),
  ssn INT REFERENCES NonProfessionalDriver(ssn),
  PRIMARY KEY(ssn, licensePlace)
);

-- 2b
-- Since insures is a many to 1 relationship,
-- so I can use a foreign key in "vehicle" to save a table.

-- 2c
-- Drives represents a many to many relationship which means
-- a non professional driver can drive any number of cars, and a car
-- can be driven by any number of non professional drivers. Operates
-- is a many to 1 relationship, which means that a truck can be operated
-- by at most 1 professional driver, and one professional driver
-- can drive any number of trucks.
