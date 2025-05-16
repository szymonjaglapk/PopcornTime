import axios from "axios";

const API_URL = "http://localhost:8080/api/v1/auth/";

const register = (name, surname, email, password) => {
  return axios.post(API_URL + "register", {
    name,
    surname,
    email,
    password,
  });
};

const login = (email, password) => {
  return axios
    .post(API_URL + "authenticate", {
      email,
      password,
    })
    .then((response) => {
      if (response.data.token) {
        localStorage.setItem("user", JSON.stringify(response.data));
      }

      return response.data;
    });
};

const logout = async () => {
  try {
    localStorage.removeItem("user");
    localStorage.clear();
    await axios.post("http://localhost:8080/api/v1/auth/logout");
  } catch (error) {
    console.error("Error:", error);
  }
};

const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("user"));
};

const AuthService = {
  register,
  login,
  logout,
  getCurrentUser,
};

export default AuthService;
