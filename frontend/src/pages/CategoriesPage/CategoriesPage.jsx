import React, { useState, useEffect } from "react";
import axios from "axios";

import "./CategoriesPage.css";

import CategoryLink from "../../components/CategoryLink/CategoryLink";
import ContentBox from "../../components/ContentBox/ContentBox";
import authHeader from "../../service/auth-header";
import NavigationBar from "../../components/NavigationBar/NavigationBar";

const CategoriesPage = () => {
  const [movieCategories, setMovieCategories] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/v1/categories/all", {
        headers: authHeader(),
      })
      .then((response) => {
        setMovieCategories(response.data);
      })
      .catch((error) => {
        console.error("Error fetching categories:", error);
      });
  }, []);

  return (
    <>
      <NavigationBar />
      <ContentBox>
        <div className="content_categories_title">Categories</div>
        <div className="content_categories_blocks">
          <ul>
            {movieCategories.map((category) => (
              <CategoryLink category={category} />
            ))}
          </ul>
        </div>
      </ContentBox>
    </>
  );
};

export default CategoriesPage;
