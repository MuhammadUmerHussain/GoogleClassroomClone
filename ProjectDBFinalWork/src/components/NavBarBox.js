import React, { useState } from "react";
import { Link } from "react-router-dom";
import { useGlobalContext } from "../context";
import "./home.css";
import Abc from "./Abc";
import axios from "axios";
import { Button, Modal, styled, TextField } from "@mui/material";
import { Box, Typography } from "@mui/material";
import { Style } from "@mui/icons-material";
import { ButtonUnstyled } from "@mui/base";
import { useEffect } from "react";

const StyleModal = styled(Modal)({
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
});
const NavBarBox = ({setdate,date}) => {
  const [open, setopen] = useState(false);
  const [openCreateClass, SetOpenCreateClass] = useState(false);
  const { setissidebaropen, issidebaropen} = useGlobalContext();
  const [joinclasscode, setjoinclass] = useState("");
  const [createClassname, SetcreateClassname] = useState("");
  const [createCode, SetcreateCode] = useState("");
  const [createDes, SetcreateDes] = useState("");
  const [createClassTitle, SetcreateClassTitle] = useState("");
  const Session=localStorage.getItem('user')
  const handleCreateClass = () => {

    /* console.log(createClassname,createCode,createDes,createClassTitle);*/
    CreateClassAPI(createClassname,createClassTitle,createCode,createDes);
        SetOpenCreateClass(false);
  };

  const CreateClassAPI = (Classname,title,code,des) => {
    axios
      .post("http://localhost:8086/CreateClassroom", {
        "TeacherUsername": Session,
		 "name": Classname,
		 "title":title,
	   "code": code,
		 "unique_code" : "",
		 "descript":des
      })

      .then((result) => {
        setdate(!date);
        
        console.log(result.data);
      })
      .catch((err) => console.log(err));
  };

  
  const JoinClassAPI = () => {
    axios
      .post("http://localhost:8086/JoinClassroom", {
		"stdUsername": Session,
		 "unique_class_code":joinclasscode
	})

      .then((result) => {
        
        setdate(!date)
        console.log(date+"ss");
      })
      .catch((err) => console.log(err));
  };
  const handleJoinClass = () => {
    JoinClassAPI();
    
    setopen(false);
  };

  const [classcode, setclasscode] = useState("");
  return (
    <div>
      <nav className="navbar ">
        <div className="container-fluid">
          <ul className="navbar nav">
            <button
              type="button hamburger"
              className="navbar-toggle nav-item hamburger"
              data-toggle="collapse"
              data-target="#myNavbar"
            >
              <Abc />
            </button>
            <li className="nav-item">
              <a className="z-index-1 nav-link text-left" href="#">
                My ClassRoom
              </a>
            </li>
            {
              <li
                className="nav-item1"
                onClick={(e) => {
                  setopen(true);
                }}
              >
                <i className="fa-sharp fa-solid fa-plus plus-btn "></i>
              </li>
            }
            <StyleModal
              open={open}
              onClose={(e) => {
                setopen(false);
              }}
              aria-labelledby="modal-modal-title"
              aria-describedby="modal-modal-description"
            >
              <Box
                width={400}
                height={200}
                bgcolor="white"
                p={3}
                borderRadius={5}
                border="none"
              >
                <Typography variant="h6" color="gray" textAlign="center">
                  {" "}
                  Join Class
                </Typography>
                <TextField
                  id="outlined-basic"
                  placeholder="Enter Class Code"
                  variant="outlined"
                  sx={{ width: "100%", marginTop: "23px" }}
                  onChange={(e) => {
                    setjoinclass(e.target.value);
                  }}
                />
                <Button sx={{ marginTop: "10px" }} onClick={handleJoinClass}>
                  {" "}
                  Submit
                </Button>
              </Box>
            </StyleModal>
            {
              <li
                className="nav-item1"
                onClick={(e) => {
                  SetOpenCreateClass(true);
                }}
              >
                <Button variant="contained" color="primary" sx={{bgcolor:"#75c9b7"}}>
                  Create Class
                </Button>
              </li>
            }
            <StyleModal
              open={openCreateClass}
              onClose={(e) => {
                SetOpenCreateClass(false);
              }}
              aria-labelledby="modal-modal-title"
              aria-describedby="modal-modal-description"
            >
              <Box
                width={400}
                height={600}
                bgcolor="white"
                p={3}
                borderRadius={5}
                border="none"
              >
                <Typography variant="h6" color="gray" textAlign="center">
                  {" "}
                  Create Class
                </Typography>
                <TextField
                  id="outlined-basic"
                  placeholder="Class Name"
                  variant="outlined"
                  sx={{ width: "100%", marginTop: "23px" }}
                  onChange={(e) => {
                    SetcreateClassname(e.target.value);
                  }}
                />
                <TextField
                  id="outlined-basic"
                  placeholder="Title"
                  variant="outlined"
                  sx={{ width: "100%", marginTop: "23px" }}
                  onChange={(e) => {
                    SetcreateClassTitle(e.target.value);
                  }}
                />
                <TextField
                  id="outlined-basic"
                  placeholder="Code"
                  variant="outlined"
                  sx={{ width: "100%", marginTop: "23px" }}
                  onChange={(e) => {
                    SetcreateCode(e.target.value);
                  }}
                />
                <TextField
                  id="outlined-basic"
                  placeholder="Create Class Description"
                  variant="outlined"
                  sx={{ width: "100%", marginTop: "23px" }}
                  onChange={(e) => {
                    SetcreateDes(e.target.value);
                  }}
                />

                <Button sx={{ marginTop: "10px" }} onClick={handleCreateClass}>
                  {" "}
                  Submit
                </Button>
              </Box>
            </StyleModal>
            {/* <li className="nav-item"><button type="button" className="nav-link btn btn-danger id-btn" >ID</button></li> */}
          </ul>
        </div>
      </nav>
      {/* <div className="options">
        <a href="#">
          <i className="fa-solid fa-list t"></i> To-do
        </a>
        <a href="#">
          <i className="fa-regular fa-calendar c"></i> Calender
        </a>
      </div> */}
    </div>
  );
};

export default NavBarBox;
