import React, { useEffect, useState } from "react";
import axios from "axios";
import TextField from "@mui/material/TextField";

import authHeader from "../../service/auth-header";
import NavigationBar from "../../components/NavigationBar/NavigationBar";
import ContentBox from "../../components/ContentBox/ContentBox";
import SearchList from "../../components/SearchList/SearchList";

import "./SearchPage.css";

const SearchPage = () => {
  const [searchInput, setSearchInput] = useState("");
  const [allMovies, setAllMovies] = useState();

  let inputHandler = (e) => {
    var lowerCase = e.target.value.toLowerCase();
    setSearchInput(lowerCase);
    console.log(allMovies)
  };

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/v1/movie/search`, {
        headers: authHeader(),
      })
      .then((response) => {
        setAllMovies(response.data);
      })
      .catch((error) => {
        console.error("Error fetching :", error);
      });
  }, []);

  return (
    <>
      <NavigationBar />
      <ContentBox>
        <div className="searchBar">
          <TextField
            id="outlined-basic"
            onChange={inputHandler}
            variant="outlined"
            fullWidth
            label="Search"
          />
        </div>
        <div className="searchContent">
          <SearchList input={searchInput} />
        </div>
      </ContentBox>
    </>
  );
};

export default SearchPage;
