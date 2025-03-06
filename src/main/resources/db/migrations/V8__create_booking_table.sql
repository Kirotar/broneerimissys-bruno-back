CREATE TABLE PUBLIC.booking
(
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    room_id    INTEGER,
    user_id    INTEGER,
    is_booked  BOOLEAN,
    is_paid    BOOLEAN,
    check_in   DATETIME,
    check_out  DATETIME,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (room_id) REFERENCES rooms (room_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);