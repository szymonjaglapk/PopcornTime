import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/api/v1/user/";

const isAdmin = async () => {
  return await axios
    .get(API_URL + "admin", { headers: authHeader() })
    .then((response) => {
      return response.data;
    })
    .catch((error) => {
      console.error("Erroch checking if user is admin:", error);
      throw error;
    });
};

const UserService = {
  isAdmin,
};

export default UserService;
