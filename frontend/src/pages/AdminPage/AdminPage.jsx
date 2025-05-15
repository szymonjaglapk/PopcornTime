import React from "react";
import NavigationBar from "../../components/NavigationBar/NavigationBar";
import ContentBox from "../../components/ContentBox/ContentBox";

import "./AdminPage.css";

const AdminPage = () => {
    
  return (
    <>
      <NavigationBar />
      <ContentBox>
        <p>Admin</p>
      </ContentBox>
    </>
  );
};

export default AdminPage;
