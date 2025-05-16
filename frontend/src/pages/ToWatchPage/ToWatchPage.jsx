import React, { useState, useEffect } from "react";
import axios from "axios";

import "./ToWatchPage.css"

import authHeader from "../../service/auth-header";
import NavigationBar from "../../components/NavigationBar/NavigationBar";
import ContentBox from "../../components/ContentBox/ContentBox";
import MovieListElement from "../../components/MovieListElement/MovieListElement";

const ToWatchPage = () => {
  const [toWatchMovies, setToWatchMovies] = useState();

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/v1/user/towatch`, {
        headers: authHeader(),
      })
      .then((response) => {
        setToWatchMovies(response.data);
      })
      .catch((error) => {
        console.error("Error fetching :", error);
      });
  });

  return (
    <>
      <NavigationBar />
      <ContentBox>
        <div className="categoryName">Movies I want to watch:</div>
        <div className="categoryMovies">
          {toWatchMovies &&
            toWatchMovies.map((movie) => <MovieListElement movie={movie} />)}
        </div>
      </ContentBox>
    </>
  );
};

export default ToWatchPage;
