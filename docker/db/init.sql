CREATE TABLE movies (
    movie_id     SERIAL PRIMARY KEY,
    title        VARCHAR(255) NOT NULL,
    description  TEXT,
    director     VARCHAR(255),
    release_year INTEGER,
    photo        VARCHAR(255),
    rating       INTEGER
);

CREATE TABLE users (
    user_id  SERIAL PRIMARY KEY,
    email    VARCHAR(255) NOT NULL UNIQUE,
    name     VARCHAR(255) NOT NULL,
    surname  VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE user_watched_movies (
    uwm_id     SERIAL PRIMARY KEY,
    user_id    INTEGER REFERENCES users,
    movie_id   INTEGER REFERENCES movies,
    liked      BOOLEAN,
    watched_at TIMESTAMP(6)
);

CREATE TABLE user_watchlist (
    user_id  INTEGER NOT NULL REFERENCES users,
    movie_id INTEGER NOT NULL REFERENCES movies,
    PRIMARY KEY (user_id, movie_id)
);

CREATE TABLE user_types (
    type_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL REFERENCES users,
    PRIMARY KEY (type_id, user_id)
);


INSERT INTO movies (title, description, director, release_year, photo, rating) VALUES
('The Matrix', 'A computer hacker learns about the true nature of his reality and his role in the war against its controllers.', 'Lana Wachowski, Lilly Wachowski', 1999, 'https://static-00.iconduck.com/assets.00/film-camera-icon-512x512-8kayx6sk.png', 9),
('Inception', 'A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea.', 'Christopher Nolan', 2010, 'https://static-00.iconduck.com/assets.00/film-camera-icon-512x512-8kayx6sk.png', 8),
('Pulp Fiction', 'The lives of two mob hitmen, a boxer, a gangster and his wife intertwine in four tales of violence and redemption.', 'Quentin Tarantino', 1994, 'https://static-00.iconduck.com/assets.00/film-camera-icon-512x512-8kayx6sk.png', 9),
('The Shawshank Redemption', 'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.', 'Frank Darabont', 1994, 'https://static-00.iconduck.com/assets.00/film-camera-icon-512x512-8kayx6sk.png', 10),
('Interstellar', 'A team of explorers travel through a wormhole in space in an attempt to ensure humanity''s survival.', 'Christopher Nolan', 2014, 'https://static-00.iconduck.com/assets.00/film-camera-icon-512x512-8kayx6sk.png', 9);
