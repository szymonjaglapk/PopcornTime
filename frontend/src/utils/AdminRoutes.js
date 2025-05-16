import React, { useState, useEffect } from "react";
import { Navigate, Outlet } from "react-router-dom";
import UserService from "../service/user.service";

const AdminRoutes = () => {
  const [isAdmin, setIsAdmin] = useState(null);

  useEffect(() => {
    const checkAdmin = async () => {
      try {
        const adminStatus = await UserService.isAdmin();
        setIsAdmin(adminStatus);
      } catch (error) {
        console.error("Error checking if user is admin:", error);
        setIsAdmin(false);
      }
    };

    checkAdmin();
  }, []);

  if (isAdmin === null) {
    return <div>Loading...</div>;
  }

  return isAdmin ? <Outlet /> : <Navigate to="/homepage" />;
};

export default AdminRoutes;
