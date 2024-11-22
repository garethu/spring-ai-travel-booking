-- Create the table
CREATE TABLE bookings (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    travel_date DATE,
    travel_from VARCHAR(50),
    travel_to VARCHAR(50),
    status CHAR(1),
    booking_class VARCHAR(20)
);

-- Insert the data
INSERT INTO bookings (id, first_name, last_name, travel_date, travel_from, travel_to, status, booking_class)
VALUES
    (101, 'John', 'Doe', '2024-11-22', 'Amsterdam', 'Utrecht', 'Y', 'BUSINESS'),
    (102, 'Jane', 'Smith', '2024-11-24', 'Rotterdam', 'Utrecht', 'Y', 'BUSINESS'),
    (103, 'Michael', 'Johnson', '2024-11-26', 'Arnhem', 'Amsterdam', 'Y', 'BUSINESS'),
    (104, 'Sarah', 'Williams', '2024-11-28', 'Utrecht', 'Amersfoort', 'Y', 'PREMIUM ECONOMY'),
    (105, 'Robert', 'Taylor', '2024-11-30', 'Zevenaar', 'Groningen', 'Y', 'ECONOMY');
