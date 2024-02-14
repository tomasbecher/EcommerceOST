import {Navbar, ProfileTabs} from '../';
import React  from "react";
import "./UserPage.css";

export const UserProfile = () =>{
  return (
    <>
      <Navbar/>
       <h1 style={{textAlign:"center", margin:"2%"}}>User profile</h1>
      <ProfileTabs></ProfileTabs>
    </>
  );
}



