CREATE TABLE movies (
                        movie_id     SERIAL PRIMARY KEY,
                        title        VARCHAR(255) NOT NULL,
                        description  TEXT,
                        director     VARCHAR(255),
                        release_year INTEGER,
                        photo       varchar(255),
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
                            type_id INTEGER NOT NULL REFERENCES types,
                            user_id INTEGER NOT NULL REFERENCES users,
                            PRIMARY KEY (type_id, user_id)
);
