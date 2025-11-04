-- DATABASE: online_ticket_booking_system
-- =====================================
DROP TABLE IF EXISTS refunds, payments, booking_details, bookings, shows, reviews, 
screens, theaters, movies, users CASCADE;

CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(15),
    role VARCHAR(20) CHECK (role IN ('ADMIN', 'THEATER_OWNER', 'CUSTOMER')) NOT NULL,
    status VARCHAR(20) DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE theaters (
    theater_id SERIAL PRIMARY KEY,
    owner_id INT REFERENCES users(user_id) ON DELETE CASCADE,
    theater_name VARCHAR(100) NOT NULL,
    city VARCHAR(50), 
    contact_no VARCHAR(15),
    total_screens INT DEFAULT 1,
    status VARCHAR(20) DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE screens (
    screen_id SERIAL PRIMARY KEY,
    theater_id INT REFERENCES theaters(theater_id) ON DELETE CASCADE,
    screen_name VARCHAR(50),
    total_seats INT,
    seat_layout JSONB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE movies (
    movie_id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    genre VARCHAR(50),
    language VARCHAR(50),
    duration INT,
    movie_cast TEXT, 
    director VARCHAR(100),
    release_date DATE,
    rating DECIMAL(2,1) CHECK (rating >= 0 AND rating <= 10),
    description TEXT,
    
    status VARCHAR(20) DEFAULT 'ACTIVE'
);

CREATE TABLE shows (
    show_id SERIAL PRIMARY KEY,
    movie_id INT REFERENCES movies(movie_id) ON DELETE CASCADE,
    screen_id INT REFERENCES screens(screen_id) ON DELETE CASCADE,
    show_date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    ticket_price DECIMAL(10,2) NOT NULL,
    available_seats INT,
    status VARCHAR(20) DEFAULT 'AVAILABLE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE bookings (
    booking_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id) ON DELETE CASCADE,
    show_id INT REFERENCES shows(show_id) ON DELETE CASCADE,
    total_amount DECIMAL(10,2) NOT NULL,
    payment_status VARCHAR(20) DEFAULT 'PENDING',
    booking_status VARCHAR(20) DEFAULT 'CONFIRMED',
    booked_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE booking_details (
    detail_id SERIAL PRIMARY KEY,
    booking_id INT REFERENCES bookings(booking_id) ON DELETE CASCADE,
    price DECIMAL(10,2) NOT NULL,
    seat_no VARCHAR(10) NOT NULL,
    seat_type VARCHAR(20) CHECK (seat_type IN ('SILVER','GOLD','VIP'))
);

CREATE TABLE payments (
    payment_id SERIAL PRIMARY KEY,
    booking_id INT REFERENCES bookings(booking_id) ON DELETE CASCADE,
    payment_mode VARCHAR(20) CHECK (payment_mode IN ('UPI','CARD','NETBANKING','WALLET')),
    transaction_id VARCHAR(100) UNIQUE,
    amount DECIMAL(10,2) NOT NULL,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    payment_status VARCHAR(20) DEFAULT 'SUCCESS'
);


CREATE TABLE refunds (
    refund_id SERIAL PRIMARY KEY,
    payment_id INT REFERENCES payments(payment_id) ON DELETE CASCADE,
    refund_amount DECIMAL(10,2),
    refund_reason TEXT,
    refund_status VARCHAR(20) DEFAULT 'PENDING',
    refund_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE reviews (
    review_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id) ON DELETE CASCADE,
    movie_id INT REFERENCES movies(movie_id) ON DELETE CASCADE,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comment TEXT,
    review_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 



CREATE INDEX idx_movie_title ON movies(title);
CREATE INDEX idx_show_date ON shows(show_date);
CREATE INDEX idx_booking_user ON bookings(user_id);
CREATE INDEX idx_theater_city ON theaters(city);