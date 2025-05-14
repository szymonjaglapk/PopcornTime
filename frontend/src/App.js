import React from "react";
import { Routes, Route } from "react-router-dom";

import PrivateRoutes from "./utils/PrivateRoutes";
import LoggedRoutes from "./utils/LoggedRoutes";

import HomePage from "./pages/HomePage/HomePage";
import LoginPage from "./pages/LoginPage/LoginPage";
import RegisterPage from "./pages/RegisterPage/RegisterPage";

import "./App.css";

function App() {
  return (
    <Routes>
      <Route element={<PrivateRoutes />}>
        <Route element={<HomePage />} path="/homepage" />
      </Route>
      <Route element={<LoggedRoutes />}>
        <Route element={<LoginPage />} path="/" />
        <Route element={<LoginPage />} path="/login" />
        <Route element={<RegisterPage />} path="/register" />
      </Route>
    </Routes>
  );
}

export default App;
