import { useState, useEffect } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";

import "./CategoryPage.css";

import authHeader from "../../service/auth-header";
import NavigationBar from "../../components/NavigationBar/NavigationBar";
import ContentBox from "../../components/ContentBox/ContentBox";
import MovieListElement from "../../components/MovieListElement/MovieListElement";

const CategoryPage = () => {
  const [MoviesOfCategory, setMoviesOfCategory] = useState([]);
  const [categoryName, setcategoryName] = useState([]);
  const { categoryId } = useParams();

  useEffect(() => {
    axios
      .get(
        `http://localhost:8080/api/v1/categories/categoryMovies/${categoryId}`,
        {
          headers: authHeader(),
        }
      )
      .then((response) => {
        setMoviesOfCategory(response.data);
      })
      .catch((error) => {
        console.error("Error fetching :", error);
      });
  }, [categoryId]);

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/v1/categories/name/${categoryId}`, {
        headers: authHeader(),
      })
      .then((response) => {
        setcategoryName(response.data);
      })
      .catch((error) => {
        console.error("Error fetching :", error);
      });
  }, [categoryId]);

  return (
    <>
      <NavigationBar />
      <ContentBox>
        <div className="categoryName">{categoryName} movies:</div>
        <div className="categoryMovies">
          {MoviesOfCategory.map((movie) => (
            <MovieListElement movie={movie} />
          ))}
        </div>
      </ContentBox>
    </>
  );
};

export default CategoryPage;
