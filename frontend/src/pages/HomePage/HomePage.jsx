import React from "react";

import AuthService from "../../service/auth.service";

import "./HomePage.css";

const HomePage = () => {
  
  const logOut = () => {
    AuthService.logout();
  };
  return (
    <>
      <h1>Hello </h1>
      <a href="/" id="logoutButton" className="menu_element" onClick={logOut}>
        LOGOUT
      </a>
    </>
  );
};

export default HomePage;
