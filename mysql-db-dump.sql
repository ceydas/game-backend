CREATE TABLE if not exists example_table (
    id INT PRIMARY KEY,
    name VARCHAR(255)
);

INSERT INTO `example_table` (`id`, `name`) VALUES (1, 'example-1'), (2, 'example-2');
