import React from "react";
import { Link } from "react-router-dom";

import "./MovieListElement.css";

const MovieListElement = ({ movie }) => {
  return (
    <Link to={`/movie/${movie.movie_id}`} className="movie-list-element-container">
      <div className="movie-list-element-cover">
        <img alt="Movie cover" src={movie.photo} />
      </div>
      <div className="movie-list-element-info">
        <h1>{movie.title}</h1>
        <h2>{movie.director}</h2>
        <div>
          <p>{movie.rating}</p>
          <img alt="" className="" src="../../../img/movie_rating_filled.svg" />
        </div>
      </div>
    </Link>
  );
};

export default MovieListElement;
