CREATE TABLE PUBLIC.rooms
(
    room_id INT PRIMARY KEY AUTO_INCREMENT,
    room_name VARCHAR(50),
    capacity INTEGER,
    keywords VARCHAR(150),
    price INTEGER
);