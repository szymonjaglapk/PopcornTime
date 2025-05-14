import React from "react";
import { Routes, Route } from "react-router-dom";

import HomePage from "./pages/HomePage/HomePage";

import "./App.css";

function App() {
  return (
    <Routes>
        <Route element={<HomePage />} path="/" />
        
    </Routes>
  );
}

export default App;
