import { Outlet, Navigate } from "react-router-dom";
import AuthService from "../service/auth.service";

const PrivateRoutes = () => {
  //   let user = {AuthService.getCurrentUser() : false};

  let auth = AuthService.getCurrentUser();
  return auth ? <Outlet /> : <Navigate to="/login" />;

  // const token = localStorage.getItem("token");
  // let auth = { token: false };
  // return auth ? <Outlet /> : <Navigate to="/login" />;
  // return currentUser ? <Outlet /> : <Navigate to="/login" />;
};

export default PrivateRoutes;
