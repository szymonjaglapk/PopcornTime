import React from "react";
import axios from "axios";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { Button } from "@mui/base/Button";

import "./MoviePage.css";

import authHeader from "../../service/auth-header";
import NavigationBar from "../../components/NavigationBar/NavigationBar";
import ContentBox from "../../components/ContentBox/ContentBox";

const MoviePage = () => {
  const [movieStatus, setMovieStatus] = useState({});
  const [movieInformation, setMovieInformation] = useState([]);
  const { id } = useParams();

  const fetchMovieStatus = async (movieId) => {
    axios
      .get(`http://localhost:8080/api/v1/usermovie/${movieId}`, {
        headers: authHeader(),
      })
      .then((response) => {
        setMovieStatus(response.data);
      });
  };

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/v1/movie/movieinfo/${id}`, {
        headers: authHeader(),
      })
      .then((response) => {
        setMovieInformation(response.data);
      })
      .catch((error) => {
        console.error("Error fetching :", error);
      });
  }, [id]);

  useEffect(() => {
    const getStatus = async () => {
      await fetchMovieStatus(id);
    };

    getStatus();
  }, [id]);

  const handleAddToWatch = () => {
    if (movieStatus.watch) {
      handleDeleteWatchMovie();
    } else {
      handleAddWatchMovie();
    }
  };

  const handleAddWatchMovie = () => {
    axios
      .put(
        `http://localhost:8080/api/v1/usermovie/add/${id}`,
        {},
        {
          headers: authHeader(),
        }
      )
      .then(() => {
        setMovieStatus((prevStatus) => ({
          ...prevStatus,
          watch: true,
          towatch: false,
        }));
      })
      .catch((error) => {
        console.error("Error fetching :", error);
      });
  };

  const handleDeleteWatchMovie = () => {
    axios
      .put(
        `http://localhost:8080/api/v1/usermovie/delete/${id}`,
        {},
        {
          headers: authHeader(),
        }
      )
      .then(() => {
        setMovieStatus((prevStatus) => ({
          ...prevStatus,
          watch: false,
          liked: false,
        }));
      })
      .catch((error) => {
        console.error("Error fetching :", error);
      });
  };

  const handleAddToToWatch = () => {
    if (movieStatus.toWatch) {
      handleDeleteToToWatch();
    } else {
      handleAddToToWatchMovie();
    }
  };

  const handleAddToToWatchMovie = () => {
    axios
      .put(
        `http://localhost:8080/api/v1/usermovie/addToWatch/${id}`,
        {},
        {
          headers: authHeader(),
        }
      )
      .then(() => {
        setMovieStatus((prevStatus) => ({ ...prevStatus, toWatch: true }));
      })
      .catch((error) => {
        console.error("Error fetching :", error);
      });
  };

  const handleDeleteToToWatch = () => {
    axios
      .put(
        `http://localhost:8080/api/v1/usermovie/deleteToWatch/${id}`,
        {},
        {
          headers: authHeader(),
        }
      )
      .then(() => {
        setMovieStatus((prevStatus) => ({ ...prevStatus, toWatch: false }));
      })
      .catch((error) => {
        console.error("Error fetching :", error);
      });
  };

  const handleLike = () => {
    if (movieStatus.liked) {
      handleUnlike();
    } else {
      handleLikeMovie();
    }
  };

  const handleLikeMovie = () => {
    axios
      .put(
        `http://localhost:8080/api/v1/usermovie/like/${id}`,
        {},
        {
          headers: authHeader(),
        }
      )
      .then(() => {
        setMovieStatus((prevStatus) => ({ ...prevStatus, liked: true }));
      })
      .catch((error) => {
        console.error("Error liking:", error);
      });
  };

  const handleUnlike = () => {
    axios
      .put(
        `http://localhost:8080/api/v1/usermovie/unlike/${id}`,
        {},
        {
          headers: authHeader(),
        }
      )
      .then(() => {
        setMovieStatus((prevStatus) => ({ ...prevStatus, liked: false }));
      })
      .catch((error) => {
        console.error("Error liking:", error);
      });
  };

  return (
    <>
      <NavigationBar />
      <ContentBox>
        <div className="content_movie_top">
          <img alt="Movie cover" src={movieInformation.photo} />

          <div className="content_description">
            <div className="content_description_icons">
              <Button
                onClick={handleAddToWatch}
                className={`content_description_element ${
                  movieStatus.watch ? "watch" : ""
                }`}
              >
                <img alt="" src="../../../img/movie_watch.svg" />
                <p>
                  {movieStatus.watch ? "You watched that movie" : "Add to watched movies"}
                </p>
              </Button>

              {!movieStatus.watch && (
                <Button
                  onClick={handleAddToToWatch}
                  className={`content_description_element ${
                    movieStatus.toWatch ? "watch" : ""
                  }`}
                >
                  <img alt="" src="../../../img/movie_add_to_watch.svg" />
                  <p>
                    {movieStatus.toWatch
                      ? "Remove from to-watch list"
                      : "Add to to-watch list"}
                  </p>
                </Button>
              )}

              {movieStatus.watch && (
                <Button
                  onClick={handleLike}
                  className={`content_description_element ${
                    movieStatus.liked ? "watch" : ""
                  }`}
                >
                  <img alt="" src="../../../img/movie_rating.svg" />
                  <p>
                    {movieStatus.liked ? "You like this movie" : "Like this movie"}
                  </p>
                </Button>
              )}
            </div>

            <div className="content_description_names">
              <div className="content_description_names_element">
                Title: {movieInformation.title}
              </div>
              <div className="content_description_names_element">
                Director: {movieInformation.director}
              </div>
              <div className="content_description_names_element">
                Release year: {movieInformation.release_year}
              </div>
              <div className="content_description_names_element">
                Rating: {movieInformation.rating}
              </div>
            </div>
          </div>
        </div>
        <div className="content_movie_bot">
          <h1>Description</h1>
          <h3>{movieInformation.description}</h3>
        </div>
      </ContentBox>
    </>
  );
};

export default MoviePage;
