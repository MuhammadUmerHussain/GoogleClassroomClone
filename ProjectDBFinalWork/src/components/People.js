import React, { useEffect, useState } from 'react'
import axios from "axios";
import './people.css';
import SendIcon from '@mui/icons-material/Send';
 import { useGlobalContext } from '../context';
import MoreVertIcon from '@mui/icons-material/MoreVert';
import { Button, IconButton, Menu, MenuItem } from '@mui/material';
import NavBarClass from './NavBarClass';
import dataa from './StudentData';
import { useParams } from 'react-router-dom';
const People = () => {
const [data,setdata]=useState([])
const [teachers,setteachers]=useState("");
const [isLoading,setLoading]=useState(false);
const [isopen,setisopen]=useState(false)
 const [date,setDate]=useState(false)
const[isteacher,setisteacher]=useState(false)
const [stdname,setstdname]=useState("")
const Session = localStorage.getItem("user");
const {id}=useParams();
//console.log(id);
 const handleclose=()=>{
      setisopen(false)
    }
    const handleEdit=()=>{
      setisopen(false)
    }
     const handleDelete=()=>{
      //console.log(stdname);
      RemoveStudent()
      setisopen(false)
    }

    const IsteacherOfclassAPI = () => {
    axios
      .post("http://localhost:8086/IsTeacherOfaClass", {
        class_id: id,
        TeacherUsername: Session,
      })

      .then((result) => {
        setisteacher(result.data);
      })
    }

    const GetAllStudentAPI = () => {
    axios
      .post("http://localhost:8086/AllStudents", {
		"class_id": id
	})

      .then((result) => {
        // console.log(result.data);
         setdata(result.data)
        
      })

      .catch((err) => console.log(err));
  };

  const GetteacherofClass=()=>{
    axios
      .post("http://localhost:8086/GetTeacherUsernameFromClassId", {
		"class_id": id
	})

      .then((result) => {
         //console.log(result.data);
         setteachers(result.data)
        
      })

      .catch((err) => console.log(err));
  };

  const RemoveStudent=()=>{
    axios
      .delete("http://localhost:8086/RemoveStudentFromClass", {
		
    data:{  "teacherUsername": Session,
		"class_id": id,
		 "stdUsername":stdname}
	})

      .then((result) => {
         //console.log(stdname);
         
         setDate(!date)
        
      })

      .catch((err) => 
      {
        console.log(err)
      } );
  };
    
// const fetchdata = async () => {
//     setLoading(true)
//     try {
//       const newArr=dataa.filter((c)=>{
//       return c.type=='Teacher'

//     })
//     setteachers(newArr);
//     setLoading(false)
      
//     } catch (error) {
//       setLoading(false)
//       console.log(error)
//     }
//   }
   useEffect(()=>{
//     setdata(dataa)
//     fetchdata();
    
    GetAllStudentAPI();
    GetteacherofClass();
    IsteacherOfclassAPI();

    


   },[date])
  //console.log(teachers);

  
  return (
    <div>
     
    {
        
    }
    
        <div className="section">
      <div className="teachers">
    <h1> Teachers</h1>
   <div className="t"><span> <i class="fa-solid fa-user"></i> {teachers} </span></div>
    
     
    </div>
    <div className="students">
        <h1>Classmates</h1>
           {data?.map((curr)=>{
            
      return( <div className="s"><span><i className="fa-solid fa-user " ></i> <div style={{display:"inline"}}> {curr.f_name} {curr.l_name} </div>
      {isteacher && <div style={{display:"inline"}}><Button
        
        onClick={()=>{setstdname(curr.username);
          setisopen(true);
        
        }}
      >
        <MoreVertIcon sx={{color:'#ac0303'}} />
      </Button>
      <Menu
        id="demo-positioned-menu"
        aria-labelledby="demo-positioned-button"
        sx={{mt:'400px',ml:"450px"}  }
        open={isopen}
        onClose={handleclose}
        anchorOrigin={{
          vertical: 'top',
          horizontal: 'left',
        }}
        transformOrigin={{
          vertical: 'top',
          horizontal: 'left',
        }}
      >
        
        <MenuItem onClick={handleDelete}>Remove</MenuItem>
        
      </Menu> </div>} </span></div>)
    })}
        
    </div>
     
   </div>
    </div>
  )
}

export default People