import React from "react";
import { Routes, Route } from "react-router-dom";

import PrivateRoutes from "./utils/PrivateRoutes";
import LoggedRoutes from "./utils/LoggedRoutes";
import AdminRoutes from "./utils/AdminRoutes";

import HomePage from "./pages/HomePage/HomePage";
import LoginPage from "./pages/LoginPage/LoginPage";
import RegisterPage from "./pages/RegisterPage/RegisterPage";
import AdminPage from "./pages/AdminPage/AdminPage"
import ProfilePage from "./pages/ProfilePage/ProfilePage"
import MoviePage from "./pages/MoviePage/MoviePage";
import CategoriesPage from "./pages/CategoriesPage/CategoriesPage"
import CategoryPage from "./pages/CategoryPage/CategoryPage";

import "./App.css";

function App() {
  return (
    <Routes>
      <Route element={<PrivateRoutes />}>
        <Route element={<HomePage />} path="/homepage" />
        <Route element={<ProfilePage />} path="/profile" />
        <Route element={<MoviePage />} path="/movie/:id" />
        <Route element={<CategoriesPage />} path="/categories" />
        <Route element={<CategoryPage />} path="/category/:categoryId" />

      </Route>
      <Route element={<LoggedRoutes />}>
        <Route element={<LoginPage />} path="/" />
        <Route element={<LoginPage />} path="/login" />
        <Route element={<RegisterPage />} path="/register" />
      </Route>
      <Route element={<AdminRoutes />}>
        <Route element={<AdminPage />} path="/admin" />
      </Route>
    </Routes>
  );
}

export default App;
