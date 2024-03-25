-- *************** DATA INSERTION *****************
-- Optimal insertion order: MEMBER, MOVIE, REVIEW
-- *************************************************
USE RENTVIEWREVIEWSERVICE;
INSERT INTO MEMBER (email_address, hashed_password, first_name, last_name, date_of_birth, member_type, last_four_digits, card_type, expiration_date) VALUES
    ('safhossain338@gmail.com', '98ab44a8', 'Safwan', 'Hossain', '2002-10-22', 'manager', NULL, NULL, NULL),
    ('s2hossain@torontomu.ca', '15d0cbde', 'admin', 'admin', '2000-10-21', 'manager', NULL, NULL, NULL),
    ('alice@example.com', '2f0e8570', 'Alice', 'Wonder', '1990-05-15', 'user', '1234', 'Visa', '2025-12-31'),
    ('BobJohn@example.com', '531142f', 'Bob', 'John', '1985-01-30', 'user', '5678', 'MasterCard', '2024-11-30'),
    ('CBrown@example.com', 'dcbca3ba', 'Chris', 'Brown', '1989-05-05', 'user', '9012', 'Amex', '2023-09-15'),
    ('D1Wilson@example.com', 'f0772152', 'Derek', 'Wilson', '1992-08-24', 'user', NULL, NULL, NULL),
    ('ethan69@example.com', '97319cf0', 'Ethan', 'Smith', '2000-03-12', 'user', NULL, NULL, NULL);

INSERT INTO MOVIE (movie_name, release_year, rental_cost, movie_image_path, is_movie_featured) VALUES
    ('Lord of the Rings Fellowship of the Ring', 2001, 3.99, '/resources/movie_posters/lord_of_the_rings_fellowship_of_the_ring.jpg', TRUE),
    ('Harry Potter', 2001, 2.99, '/resources/movie_posters/harry_potter.jpg', TRUE),
    ('Little Women', 2019, 3.49, '/resources/movie_posters/little_women.jpg', TRUE),
    ('Home Alone', 1990, 1.99, '/resources/movie_posters/home_alone.jpg', FALSE),
    ('Finding Nemo', 2003, 2.49, '/resources/movie_posters/finding_nemo.jpg', FALSE),
    ('Forrest Gump', 1994, 2.99, '/resources/movie_posters/forrest_gump.jpg', FALSE),
    ('Toy Story', 1995, 2.99, '/resources/movie_posters/toy_story.jpg', FALSE),
    ('The Matrix', 1999, 3.99, '/resources/movie_posters/the_matrix.jpg', FALSE),
    ('Inception', 2010, 3.99, '/resources/movie_posters/inception.jpg', FALSE),
    ('The Lion King', 1994, 2.99, '/resources/movie_posters/the_lion_king.jpg', FALSE),
    ('Oppenheimer', 2023, 4.99, '/resources/movie_posters/oppenheimer.jpg', TRUE);

INSERT INTO REVIEW (review_description, rating, member_ID, movie_ID) VALUES
    ('Great movie!', 5, 3, 1),
    ('Loved it!', 4, 4, 2),
    ('Very entertaining', 4, 5, 3),
    ('A classic', 5, 6, 4),
    ('Heartwarming story', 5, 7, 5);