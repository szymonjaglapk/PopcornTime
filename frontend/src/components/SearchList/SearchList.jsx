import React, { useEffect, useState } from "react";
import axios from "axios";
import authHeader from "../../service/auth-header";

import "./SearchList.css";

import MovieListElement from "../MovieListElement/MovieListElement";

const SearchList = (props) => {
  const [allMovies, setAllMovies] = useState([]);
  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/v1/movie/search`, {
        headers: authHeader(),
      })
      .then((response) => {
        setAllMovies(response.data);
      })
      .catch((error) => {
        console.error("Error fetching :", error);
      });
  }, []);

  const filteredData = allMovies.filter((movie) => {
    if (props.input === "") {
      return movie;
    } else {
      return (
        movie.title.toLowerCase().includes(props.input) ||
        movie.author.toLowerCase().includes(props.input)
      );
    }
  });
  return (
    <>
      {filteredData.map((movie) => (
        <MovieListElement movie={movie} />
      ))}
    </>
  );
};

export default SearchList;
