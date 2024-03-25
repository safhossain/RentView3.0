-- *************** DATA INSERTION *****************
-- Optimal insertion order: DIRECTOR, GENRE, MOVIE, MOVIE_DIRECTOR, MOVIE_GENRE
-- *************************************************
USE SEARCHSERVICE_DB;

INSERT INTO DIRECTOR (first_name, last_name)
    VALUES
    ('Peter', 'Jackson'),
    ('Chris', 'Columbus'),
    ('Greta', 'Gerwig'),
    ('John', 'Hughes'),
    ('Andrew', 'Stanton'),
    ('Robert', 'Zemeckis'),
    ('John', 'Lasseter'),
    ('Lana', 'Wachowski'),
    ('Christopher', 'Nolan'),
    ('Roger', 'Allers'),
    ('Lilly', 'Wachowski');

INSERT INTO GENRE (genre_type)
    VALUES
    ('Fantasy'),
    ('Adventure'),
    ('Drama'),
    ('Comedy'),
    ('Animation'),
    ('Romance'),
    ('Family');

INSERT INTO MOVIE (movie_name, release_year, rental_cost, movie_image_path, is_movie_featured)
    VALUES
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

INSERT INTO MOVIE_DIRECTOR (movie_ID, director_ID)
    VALUES
    (1, 1), -- Lord of the Rings Fellowship of the Ring by Peter Jackson
    (2, 2), -- Harry Potter by Chris Columbus
    (3, 3), -- Little Women by Greta Gerwig
    (4, 4), -- Home Alone by John Hughes
    (5, 5), -- Finding Nemo by Andrew Stanton
    (6, 6), -- Forrest Gump by Robert Zemeckis
    (7, 7), -- Toy Story by John Lasseter
    (8, 8), -- The Matrix by Lana Wachowski
    (8, 11), -- The Matrix ALSO by Lilly Wachowski
    (9, 9), -- Inception by Christopher Nolan
    (10, 10), -- The Lion King by Roger Allers
    (11, 9); -- Oppenheimer by Christopher Nolan

-- Associating movies with multiple genres
INSERT INTO MOVIE_GENRE (movie_ID, genre_ID)
    VALUES
    (1, 1), -- Lord of the Rings Fellowship of the Ring is Fantasy
    (1, 2), -- Lord of the Rings Fellowship of the Ring is also Adventure
    (2, 2), -- Harry Potter is Adventure
    (2, 7), -- Harry Potter is also Family
    (3, 3),
    (3, 6),
    (4, 4),
    (4, 7),
    (5, 5),
    (5, 7),
    (6, 6),
    (6, 4), 
    (7, 5),
    (7, 7),
    (8, 1),
    (8, 2),
    (9, 2),
    (9, 3),
    (10, 5),
    (10, 7);