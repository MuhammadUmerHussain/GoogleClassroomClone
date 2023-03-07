import { Avatar, Button, LinearProgress } from '@mui/material'
import React from 'react'
import { useState,useEffect } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import axios from "axios";
import './ViewAllAssignment.css'
import Loader from './Loader';
const ViewAllAssignment = () => {
  const [progress, setProgress]=useState(false)
  const [assdata,setassdata]=useState([])
  const{id}=useParams()
  const nav=useNavigate()
  const {id2}=useParams()
  const ViewAllAssignmentApi = () => {

    setProgress(true);
    axios
      .post("http://localhost:8086/ViewAllAssignment", {
		"class_id": id
	})
      .then((result) =>{ 

        console.log(result.data)
        setassdata(result.data);
        setProgress(false);

      } )
      .catch((err) => console.log(err));
  };
   useEffect(()=>{

      ViewAllAssignmentApi();


     },[])
     if(progress)
     {
      return <Loader/>
     }
  return (
    <div className="container1">

            <div className='avatar'>
            <Avatar src="/broken-image.jpg" sx={{height:"100px",width:"100px"}} />
          </div>
            <div className="name">
              <h1 className='heading'>{id2} </h1>
            </div>
            {/* <div className="line">
            <h1>Line</h1>
            </div> */}

             {
              assdata?.map((curr)=>
              {
                return  <div className='c2'> <h3 className='heading'> <i class="fa-solid fa-file-arrow-up"></i>  {curr.a_title}</h3> <Button variant="contained" sx={{bgcolor:"#75c9b7",float: "right",ml:"250px" }} onClick={()=>{nav(`/class/${id}/Submission/${id2}/${curr.a_id}`)}}>Display</Button> 
                </div> 
        })} 
              

    </div>
  )
}

export default ViewAllAssignment