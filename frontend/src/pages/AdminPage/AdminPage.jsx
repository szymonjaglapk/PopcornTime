import React from "react";
import Form from "react-validation/build/form";
import { Button } from "@mui/base/Button";
import { useState } from "react";
import Input from "react-validation/build/input";

import MovieService from "../../service/movie.service";
import NavigationBar from "../../components/NavigationBar/NavigationBar";
import ContentBox from "../../components/ContentBox/ContentBox";


import "./AdminPage.css";

const AdminPage = () => {
  const [currentComponent, setCurrentComponent] = useState("ab");

  const [title, setTitle] = useState("");
  const [director, setDirector] = useState("");
  const [description, setDescription] = useState("");
  const [photo, setPhoto] = useState("");
  const [release_year, setRelease_year] = useState("");
  const [message, setMessage] = useState("");

  const showAddMovie = () => setCurrentComponent("ab");

  const onChangeTitle = (e) => {
    const title = e.target.value;
    setTitle(title);
  };

  const onChangeDirector = (e) => {
    const director = e.target.value;
    setDirector(director);
  };

  const onChangeDescription = (e) => {
    const description = e.target.value;
    setDescription(description);
  };

  const onChangePhoto = (e) => {
    const photo = e.target.value;
    setPhoto(photo);
  };

  const onChangeRelease_year = (e) => {
    const release_year = e.target.value;
    setRelease_year(release_year);
  };

  const handleAdd = (e) => {
    e.preventDefault();
    setMessage("");
    let p = photo
    if (p === ""){
      p = "https://static-00.iconduck.com/assets.00/film-camera-icon-512x512-8kayx6sk.png"
    }
    MovieService.addMovie(title, director, description, p, release_year)
      .then((response) => {
        setTitle("");
        setDirector("");
        setDescription("");
        setPhoto("");
        setRelease_year("");
        setMessage("");
        setMessage(`Adding sucessful.\n`);
        // setTimeout(() => {
        //   navigate("/homepage");
        // }, 2000);
        
      })
      .catch((error) => {
        const resMessage =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();
        setMessage(`Update failed. Details: Error: ${resMessage}`);
      });
  };

  return (
    <>
      <NavigationBar />
      <ContentBox>
        <div className="profile-options">
          <Button onClick={showAddMovie} className="profile-options-button">
            Add movie
          </Button>
          <Button className="profile-options-button">TODO</Button>
        </div>
        <div className="profile-content">
          {/* {currentComponent === "rb" &&
            userWatchMovies &&
            userWatchMovies.map((movie) => <MovieListElement movie={movie} />)} */}

          {currentComponent === "ab" && (
            <div className="profile-admin-container">
              <Form onSubmit={handleAdd}>
                <Input
                  className="profile-admin-block"
                  type="text"
                  name="title"
                  value={title}
                  onChange={onChangeTitle}
                  placeholder="Title"
                />
                <Input
                  className="profile-admin-block"
                  type="text"
                  name="director"
                  value={director}
                  onChange={onChangeDirector}
                  placeholder="Director"
                />
                <Input
                  className="profile-admin-block"
                  type="text"
                  name="release_year"
                  value={release_year}
                  onChange={onChangeRelease_year}
                  placeholder="release_year"
                />
                <Input
                  className="profile-admin-block"
                  type="text"
                  name="photo"
                  value={photo}
                  onChange={onChangePhoto}
                  placeholder="Photo"
                />
                <Input
                  className="profile-admin-block"
                  type="text"
                  name="description"
                  value={description}
                  onChange={onChangeDescription}
                  placeholder="Description"
                />
                <button className="profile-admin-block">ADD MOVIE</button>
                {message && <div className="message">{message}</div>}
              </Form>
            </div>
          )}
        </div>
      </ContentBox>
    </>
  );
};

export default AdminPage;
