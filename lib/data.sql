-- ============================================
-- FIXED SAMPLE DATA: ONLINE TICKET BOOKING SYSTEM (Tamil Movies)
-- ============================================

-- USERS
INSERT INTO users (full_name, email, password, phone, role)
VALUES
('Super Admin', 'superadmin@ticketz.in', 'super@123', '9999900001', 'ADMIN'),
('Kavya', 'kavya.owner@gmail.com', 'kavya@123', '9999900002', 'THEATER_OWNER'),
('Ramya', 'ramya.owner@gmail.com', 'ramya@123', '9999900003', 'THEATER_OWNER'),
('Sowmiya', 'sowmiya.user@gmail.com', 'sowmiya@123', '9999900004', 'CUSTOMER'),
('Nivi', 'nivi.user@gmail.com', 'nivi@123', '9999900005', 'CUSTOMER'),
('Nanda', 'nanda.user@gmail.com', 'nanda@123', '9999900006', 'CUSTOMER'),
('Nalin', 'nalin.user@gmail.com', 'nalin@123', '9999900007', 'CUSTOMER');

-- THEATERS
INSERT INTO theaters (owner_id, theater_name, city, contact_no, total_screens,total_seat)
VALUES
(2, 'Radiance Cinemas', 'Rajapalayam',  '04563-223344', 3,300),
(2, 'Meenakshi Cinemas', 'Rajapalayam', '04563-556677', 2,250),
(3, 'Revathi Theatre', 'Srivilliputhur', '04562-998877', 2,100);

-- SCREENS
INSERT INTO screens (theater_id, screen_name, total_seats, seat_layout)
VALUES
(1, 'Radiance Screen 1', 120, '{"rows":10,"cols":12,"types":{"SILVER":6,"GOLD":4,"VIP":2}}'),
(1, 'Radiance Screen 2', 100, '{"rows":10,"cols":10,"types":{"SILVER":5,"GOLD":4,"VIP":1}}'),
(2, 'Meenakshi Screen 1', 90, '{"rows":9,"cols":10,"types":{"SILVER":5,"GOLD":3,"VIP":1}}'),
(3, 'Revathi Screen 1', 110, '{"rows":10,"cols":11,"types":{"SILVER":6,"GOLD":4,"VIP":1}}'),
(3, 'Revathi Screen 2', 80, '{"rows":8,"cols":10,"types":{"SILVER":4,"GOLD":4,"VIP":2}}');

-- MOVIES
INSERT INTO movies (title, genre, language, duration, movie_cast, director, release_date, rating, description)
VALUES
('Dude', 'Action/Thriller', 'Tamil', 155, 'Harish Kalyan, Tanya Ravichandran, VTV Ganesh', 'Kavin Kumar', '2025-09-10', 8.1, 'Stylish action drama about friendship and betrayal.'),
('Bison', 'Adventure/Action', 'Tamil', 165, 'Arya, Aishwarya Rajesh, Samuthirakani', 'R. Ravi Kumar', '2025-10-05', 8.4, 'A wildlife officer protects the Western Ghats from poachers.'),
('Aanan Pavam Pollathathu', 'Comedy/Drama', 'Tamil', 140, 'Santhanam, Yogi Babu, Rachita Mahalakshmi', 'Raja Baskar', '2025-08-25', 7.6, 'A hilarious tale of two brothers in a funny village mystery.');

-- SHOWS
INSERT INTO shows (movie_id, screen_id, show_date, start_time, end_time, ticket_price, available_seats)
VALUES
(1, 1, '2025-11-03', '10:15:00', '12:45:00', 180.00, 120),
(1, 2, '2025-11-03', '18:30:00', '21:00:00', 220.00, 100),
(2, 3, '2025-11-03', '14:30:00', '17:00:00', 200.00, 90),
(2, 4, '2025-11-03', '19:00:00', '21:30:00', 250.00, 110),
(3, 5, '2025-11-04', '10:00:00', '12:20:00', 150.00, 80),
(3, 4, '2025-11-04', '13:00:00', '15:30:00', 180.00, 110);

-- BOOKINGS
INSERT INTO bookings (user_id, show_id, total_amount, payment_status, booking_status)
VALUES
(4, 1, 540.00, 'SUCCESS', 'CONFIRMED'),
(5, 2, 440.00, 'SUCCESS', 'CONFIRMED'),
(6, 3, 400.00, 'PENDING', 'CONFIRMED'),
(7, 5, 300.00, 'SUCCESS', 'CONFIRMED');

-- BOOKING DETAILS
INSERT INTO booking_details (booking_id, price, seat_no, seat_type)
VALUES
(1, 180.00, 'A5', 'GOLD'),
(1, 180.00, 'A6', 'GOLD'),
(1, 180.00, 'A7', 'GOLD'),
(2, 220.00, 'B2', 'SILVER'),
(2, 220.00, 'B3', 'SILVER'),
(3, 200.00, 'C1', 'GOLD'),
(3, 200.00, 'C2', 'GOLD'),
(4, 150.00, 'D4', 'SILVER'),
(4, 150.00, 'D5', 'SILVER');

-- PAYMENTS
INSERT INTO payments (booking_id, payment_mode, transaction_id, amount)
VALUES
(1, 'UPI', 'TXN5001UPI', 540.00),
(2, 'CARD', 'TXN5002CARD', 440.00),
(3, 'WALLET', 'TXN5003WALLET', 400.00),
(4, 'UPI', 'TXN5004UPI', 300.00);

-- REFUNDS
INSERT INTO refunds (payment_id, refund_amount, refund_reason, refund_status)
VALUES
(3, 400.00, 'Show postponed', 'SUCCESS');

-- REVIEWS
INSERT INTO reviews (user_id, movie_id, rating, comment)
VALUES
(4, 1, 5, 'Dude was thrilling and stylish! Harish Kalyan nailed it.'),
(5, 2, 4, 'Bison - adventure packed and emotional! Arya superb!'),
(6, 3, 4, 'Aanan Pavam Pollathathu is a full comedy treat!'),
(7, 1, 5, 'Loved the background score in Dude!');
