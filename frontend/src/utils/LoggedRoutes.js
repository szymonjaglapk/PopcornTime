import { Navigate, Outlet } from "react-router-dom";
import AuthService from "../service/auth.service";

const LoggedRoutes = () => {
  let auth = AuthService.getCurrentUser();
  return !auth ? <Outlet /> : <Navigate to="/homepage" replace />;
};
export default LoggedRoutes;
