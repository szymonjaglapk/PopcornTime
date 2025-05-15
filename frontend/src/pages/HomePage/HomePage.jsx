import React, { useState, useEffect } from "react";
import axios from "axios";

import NavigationBar from "../../components/NavigationBar/NavigationBar";
import ContentBox from "../../components/ContentBox/ContentBox";
import HomePageRecommendedMovie from "../../components/HomePageRecommendedMovie/HomePageRecommendedMovie";

import "./HomePage.css";

import authHeader from "../../service/auth-header";

const HomePage = () => {
  const [topThreeRatedMovies, setTopThreeRatedMovies] = useState([]);
  const [recommendedMovies, setRecommendedMovies] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/v1/movie/bestrating", {
        headers: authHeader(),
      })
      .then((response) => {
        setTopThreeRatedMovies(response.data);
      })
      .catch((error) => {
        console.error("Error fetching movies:", error);
      });
  }, []);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/v1/movie/random", {
        headers: authHeader(),
      })
      .then((response) => {
        setRecommendedMovies(response.data);
      })
      .catch((error) => {
        console.error("Error fetching movies:", error);
      });
  }, []);

  return (
    <>
    <div className="homepage">
      <NavigationBar />
      <ContentBox>
        <div className="content_main_top_title">Recommendations:</div>
        <div className="content_main_top">
          {recommendedMovies.map((movie) => (
            <HomePageRecommendedMovie movie={movie} />
          ))}
        </div>
        <div className="content_main_top_title">Best rated movies:</div>
        <div className="content_main_bot">
          {topThreeRatedMovies.map((movie) => (
            <HomePageRecommendedMovie movie={movie} />
          ))}
        </div>
      </ContentBox>
      </div>
    </>
  );
};

export default HomePage;
