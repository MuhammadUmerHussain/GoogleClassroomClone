import React from 'react'
import { Avatar, LinearProgress } from '@mui/material'
import { Button, Modal, styled, TextField } from "@mui/material";
import { Box, Typography } from "@mui/material";
import FileSaver from 'file-saver';
import { useState,useEffect } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import axios from "axios";
import './ViewAllAssignment.css';
import fileDownload from 'js-file-download'
import Loader from './Loader';
const StyleModal = styled(Modal)({
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
});
const DisplayAassignment = () => {
  const [progress, setProgress]=useState(false)
  
  const [issubmit,setissubmit]=useState(false);
  const Session=localStorage.getItem('user')
  const{id}=useParams()
  const nav=useNavigate()
  const {id2}=useParams()
  const[marks,setmarks]=useState("");
  const[open,setopen]=useState(false);
  const {id3}=useParams()


  const handleAssignGrade=()=>{

    AssignGradeApi()
    setopen(false)
  }


  useEffect(()=>{
  IsSubmissionAvailable();
  },[])
  const AssignGradeApi = () => {

    
    axios
      .post("http://localhost:8086/AssignGrade",
	{
		"assignment_id": id3,
		"std_username": id2,
		"teacherUsername": Session,
		"MarksObt": marks
	})
      .then((result) =>{ 

        console.log(result.data)
        
        

      } )
      .catch((err) => console.log(err));
  };



   const IsSubmissionAvailable = () => {
    axios
      .post("http://localhost:8086/isSubmissionAvaliable",  {
		"stdUsername": id2,
		"assignmentId" : id3
	}
)

      .then((result) => {
        
        setissubmit(result.data);
         console.log(result.data)
      })

      .catch((err) => console.log(err));
  };

const DownloadSubmission = () => {
      axios
        .get('http://localhost:8086/download', {
          params: {"stdUsername": id2,"assignmentId":id3}
        })
          .then((result) => console.log(result.data))
          .catch((error) => console.log( error.response.data.message) );
      
    };
// const DownloadSubmission2 = () => {
//       axios
//         .get(`http://localhost:8086/download/${id2}/${id3}`)
//           .then((result) => {
//             const d=result.headers
//             console.log(result.data)
            
//           }
          
//           )
//           .catch((error) => console.log( error.response.data.message) );
//       
//     };


const DownloadSubmission2 = () => {
  axios
    .get(`http://localhost:8086/download/${id2}/${id3}`, {
    responseType: 'blob',
  })


      .then((result) => {
        // const d=result.headers
        console.log(result.headers)
        // fileDownload(result.data, result.headers)
        FileSaver.saveAs(
    `http://localhost:8086/download/${id2}/${id3}`,
    "fileNameYouWishCustomerToDownLoadAs.anyType");
      }
      
      )
      .catch((error) => console.log( error.response.data.message) );
      
    };





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
              
                 <div className='c2'> 
                 {issubmit&&<Button variant="contained" sx={{bgcolor:"#75c9b7",float: "left",ml:"20px" }} onClick={()=>{
                  DownloadSubmission2()
                 }} >Download</Button>}
                   <Button variant="contained" sx={{bgcolor:"#75c9b7",float: "right",ml:"700px" }} onClick={()=>{setopen(true)}}>Assign Grade</Button>
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
                  Assign Grade
                </Typography>
                <TextField
                  id="outlined-basic"
                  placeholder="Enter Marks Obtained"
                  variant="outlined"
                  sx={{ width: "100%", marginTop: "23px" }}
                  onChange={(e) => {
                    setmarks(e.target.value);
                  }}
                />
                <Button sx={{ marginTop: "10px" }} onClick={handleAssignGrade}>
                  {" "}
                  Submit
                </Button>
              </Box>
            </StyleModal> 
                </div> 
        }
              

    </div>
  )
}

export default DisplayAassignment