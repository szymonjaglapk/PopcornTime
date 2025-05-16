import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/api/v1/movie/";

const register = (name, surname, email, password) => {
  return axios.post(API_URL + "register", {
    name,
    surname,
    email,
    password,
  });
};

const addMovie = (title, director, description, photo, release_year) => {
  return axios
    .post(
      API_URL + `create`,
      {
        title,
        director,
        description,
        photo,
        release_year,
      },
      { headers: authHeader() }
    )
    .then((response) => {
      return response.data;
    })
    .catch((error) => {
      console.error("Error:", error);
      throw error;
    });
};

const MovieService = {
  addMovie,
};

export default MovieService;
