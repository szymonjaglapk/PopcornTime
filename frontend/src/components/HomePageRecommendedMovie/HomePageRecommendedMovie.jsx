import React from "react";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";

import "./HomePageRecommendedMovie.css";

const HomePageRecommendedMovie = ({ movie }) => {
  return (
    <Link to={`/movie/${movie.movie_id}`} className="recommended-movie-container">
      <div className="movie-cover-container">
        <img alt="Movie cover" src={movie.photo} />
      </div>
      <div className="movie-info-conatiner">
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

HomePageRecommendedMovie.propTypes = {
  movue: PropTypes.shape({
    movie_id: PropTypes.number.isRequired,
    title: PropTypes.string.isRequired,
    director: PropTypes.string.isRequired,
    photo: PropTypes.string.isRequired,
    description: PropTypes.string.isRequired,
    release_year: PropTypes.string.isRequired,
    rating: PropTypes.number.isRequired,
  }),
};



export default HomePageRecommendedMovie;
