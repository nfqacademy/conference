create table demo (
	id INT PRIMARY KEY,
    data VARCHAR(100)
);

INSERT INTO demo (id, data) VALUES (1, 'Hello world');
INSERT INTO demo (id, data) VALUES (2, 'Hello world2');

COMMIT;