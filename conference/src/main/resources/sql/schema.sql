CREATE TABLE demo (
	id IDENTITY PRIMARY KEY,
    data VARCHAR(100)
);

CREATE TABLE conference (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    startDate DATETIME NOT NULL,
    endDate DATETIME NOT NULL
);

CREATE INDEX startDate ON conference (startDate);
CREATE INDEX endDate ON conference (endDate);
