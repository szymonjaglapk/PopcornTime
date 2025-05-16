import React from "react";
import { Button } from "@mui/base/Button";
import { useState, useEffect } from "react";
import axios from "axios";

import NavigationBar from "../../components/NavigationBar/NavigationBar";
import ContentBox from "../../components/ContentBox/ContentBox";
import authHeader from "../../service/auth-header";
import MovieListElement from "../../components/MovieListElement/MovieListElement";

import "./ProfilePage.css";

const ProfilePage = () => {
  const [currentComponent, setCurrentComponent] = useState("rb");
  const [userWatchMovies, setUserWatchMovies] = useState([]);
  const [userInfo, setUserInfo] = useState();

  const showWatchMovies = () => setCurrentComponent("rb");
  const showProfileInfo = () => setCurrentComponent("pi");

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/v1/movie/userwatchlist", {
        headers: authHeader(),
      })
      .then((response) => {
        setUserWatchMovies(response.data);
      })
      .catch((error) => {
        console.error("Error fetching movies:", error);
      });
  }, []);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/v1/user/info", {
        headers: authHeader(),
      })
      .then((response) => {
        setUserInfo(response.data);
      })
      .catch((error) => {
        console.error("Error fetching info:", error);
      });
  }, []);

  return (
    <>
      <NavigationBar />
      <ContentBox>
        <div className="profile-options">
          <Button onClick={showWatchMovies} className="profile-options-button">
            User watch movies
          </Button>
          <Button onClick={showProfileInfo} className="profile-options-button">
            User info
          </Button>
        </div>
        <div className="profile-content">
          {currentComponent === "rb" &&
            userWatchMovies &&
            userWatchMovies.map((movie) => <MovieListElement movie={movie} />)}

          {currentComponent === "pi" && (
            <div className="profile-info-container">
              <div className="profile-info-block">Name: {userInfo.name}</div>
              <div className="profile-info-block">Surname: {userInfo.surname}</div>
              <div className="profile-info-block">Email: {userInfo.email}</div>
              <div className="profile-info-block">Total watched movies: 5</div>
              <div className="profile-info-block">
                Total movies marked to watch: 5
              </div>
            </div>
          )}
        </div>
      </ContentBox>
    </>
  );
};

export default ProfilePage;
