import React, { useEffect, useState } from 'react'
import NavBarClass from './NavBarClass'
import './people.css'
import data from './assignmentData'
import { useGlobalContext } from '../context'
import { useNavigate, useParams } from 'react-router-dom'
import { Box } from '@mui/system'
import axios from "axios";
import { Button, Modal,styled, TextField, Typography } from '@mui/material';
import { ContentCutOutlined } from '@mui/icons-material'

import { Link } from 'react-router-dom'
import Loader from './Loader'

const StyleModal=styled(Modal)({
  display:"flex",
  alignItems:"center",
  justifyContent:"center"
})

const ClassWork = () => {
  
  const [load,setload]=useState(false)
  const[deleteopen,setdeleteopen]=useState(false);
  const [editasstotalmarks,seteditasstotalmarks]=useState("")
  const [editassduedate,seteditassduedate]=useState("");
  const [editassdes,seteditassdes]=useState("")
  const[editass,seteditass]=useState(false)
  const routeParams = useParams();
  const [date,setdate]=useState(false);
  const [assdata,setassdata]=useState(data);
  const[editasstitle,seteditasstitle]=useState("")
  const Session=localStorage.getItem('user')
  const [asstitle,setasstitle]=useState("");
  const [asstotalmark,setasstotalmark]=useState(100);
  const [assduedate,setassduedate]=useState("");
  const [assdes,setassdes]=useState("");
  const[isteacher,setisteacher]=useState(false)
  const [assignmentId,setassignmentId]=useState("")
  const [editassid,seteditassid]=useState("")
  const{id}=useParams()
  const handleCreateAssign=()=>{
    setopen(false)
    CreateAssignmentAPI();
    //console.log(Session);

    
    
  }
  const handleEditPost=()=>{

    EditAssAPI();
    seteditass(false);
    
  }
  
  const EditAssAPI = () => {
    axios
      .put("http://localhost:8086/editAssignment", {
		assignment_id : editassid ,
		teacherUsername: Session,
		class_id: id,
		title: "(edited)" + editasstitle,
		totalMarks:editasstotalmarks,
		due_date:"2023/6/6",
		descript: "(edited)"+editassdes
	})

      .then((result) => {
        console.log(result.data);
        setdate(!date);
      })
      .catch((err) => {
        console.log(err);
        setdate(!date);
      });
    }
  const IsteacherOfclassAPI =  () => {

    setload(true);
     axios
      .post("http://localhost:8086/IsTeacherOfaClass",{
		"class_id": id,
		"TeacherUsername": Session
	})

      .then((result) => {


        setisteacher(result.data)
        setload(false);
        
      })

      .catch((err) => console.log(err));
  };
 
  
  const handleDelete=()=>{
    
DeleteAssignment();
    setdeleteopen(false);
    setdate(!date);
  }
  const DeleteAssignment = () => {
    axios
      .delete("http://localhost:8086/deleteAssignment", {
        data: {
         assignment_id : assignmentId ,
		teacherUsername: Session
        },
      })
      .then((result) => {
        console.log(result.data);
        console.log(assignmentId);
        
      })
      .catch((err) => {
        console.log(assignmentId);
        console.log(err);
      });
  };

  const ViewAllAssignmentApi = () => {
    setload(true);
    axios
      .post("http://localhost:8086/ViewAllAssignment", {
		"class_id": id
	})
      .then((result) =>{ 

       // console.log(result.data)
        setassdata(result.data);
        setload(false);

      } )
      .catch((err) =>{

       console.log(err)
      setload(false);}
);
  };
  const CreateAssignmentAPI = () => {
    
    axios
      .post("http://localhost:8086/createAssignment", {
		"teacherUsername": Session,
		"class_id": id,
		"title": asstitle,
		"totalMarks":asstotalmark,
		"due_date":"2023/12/15",
		"descript": assdes
	})
      .then((result) => 
      {
        
        console.log(result.data)
        setdate(!date)
        
      })
      .catch((err) => console.log(err));
  };

     const nav=useNavigate();
  const [open,setopen]=useState(false)
     
     const NavToAssign=(courseid)=>{

        

       nav(`/class/${id}/classwork/${courseid} `);

        

     }

    //  useEffect(()=>{
    //   handleDelete()
    //   console.log("useEffect");
    //  },[assignmentId])

     useEffect(()=>{
      //console.log("use")
      ViewAllAssignmentApi();
      IsteacherOfclassAPI();

     },[date])
 if (load) {
    return <Loader />;
  }
     

  return (
    
    <div>


     
     <div className="section">
     <div class="teachers"><h1>ClassWork
     { isteacher && <div>  <i className="fa-sharp fa-solid fa-plus plus-btn " onClick={(e)=>{setopen(true)}}></i>
       <StyleModal
  open={open}
  onClose={(e)=>{setopen(false)}}
  aria-labelledby="modal-modal-title"
  aria-describedby="modal-modal-description"
>
  <Box width={400} height={500} bgcolor="white" p={3} borderRadius={5}  border="none">
    <Typography variant="h6" color="gray" textAlign="center"> Create Assignment</Typography>
    <TextField id="outlined-basic" placeholder="Enter Assignment Title" variant="outlined" sx={{width:"100%",marginTop:"23px"}} onChange={(e)=>{
    setasstitle(e.target.value)}} />
    
    <TextField id="outlined-basic" placeholder="Enter Assignment Total Marks" variant="outlined" sx={{width:"100%",marginTop:"23px"}} onChange={(e)=>{
    setasstotalmark(e.target.value)}} />
    
    <TextField id="outlined-basic" placeholder="Enter Assignment Description" variant="outlined" sx={{width:"100%",marginTop:"23px"}} onChange={(e)=>{
    setassdes(e.target.value)}} />
    
    <TextField id="outlined-basic" placeholder="Enter Due Date" variant="outlined" sx={{width:"100%",marginTop:"23px"}} onChange={(e)=>{
    setassduedate(e.target.value)}} />
    <Button sx={{marginTop:"10px"}} onClick={handleCreateAssign}> Submit</Button>
  </Box>
</StyleModal></div>}</h1></div></div>
       <div class="classwork">
       
       {/* {
        assdata.map((curr)=>{
          return <button className='bttnn' state={{from:"w"}} onClick={()=>{NavToAssign(curr.a_id)}} > <div class="c1"> <i class="fa-solid fa-file-arrow-up"></i>{curr.a_title} </div></button> 
        })
       } */}
       {

       assdata?.map((curr)=>{
        

          return <div className="buttons">  <Link to= {`/class/${id}/classwork/${curr.a_id}`} className='bttnn' state={{from:{...curr}}} onClick={()=>{NavToAssign(curr.a_id)}} > <div class="c1"> <i class="fa-solid fa-file-arrow-up"></i>{curr.a_title} </div></Link>{isteacher && <div><Button variant="contained" sx={{bgcolor:"#75c9b7",float: "right",display:"inline",height:"40px" }} onClick={()=>{seteditassid(curr.a_id);seteditass(true);}}>Edit</Button>
          <StyleModal
              open={editass}
              onClose={(e) => {
                seteditass(false);
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
                  Edit Assignment
                </Typography>
                <TextField
                  id="outlined-basic"
                  placeholder="Title"
                  variant="outlined"
                  sx={{ width: "100%", marginTop: "23px" }}
                  onChange={(e) => {
                    seteditasstitle(e.target.value);
                  }}
                />
                <TextField
                  id="outlined-basic"
                  placeholder="Total Marks"
                  variant="outlined"
                  sx={{ width: "100%", marginTop: "23px" }}
                  onChange={(e) => {
                    seteditasstotalmarks(e.target.value);
                  }}
                />
                <TextField
                  id="outlined-basic"
                  placeholder="Due-Date"
                  variant="outlined"
                  sx={{ width: "100%", marginTop: "23px" }}
                  onChange={(e) => {
                    editassduedate(e.target.value);
                  }}
                />
                <TextField
                  id="outlined-basic"
                  placeholder="Assignment Description"
                  variant="outlined"
                  sx={{ width: "100%", marginTop: "23px" }}
                  onChange={(e) => {
                    editassdes(e.target.value);
                  }}
                />

                <Button sx={{ marginTop: "10px" }} onClick={handleEditPost}>
                  {" "}
                  Submit
                </Button>
                </Box>
            </StyleModal>
          
          <Button variant="contained" sx={{bgcolor:"#75c9b7",float: "right",display:"inline",height:"40px" }} onClick={()=>{ setassignmentId(curr.a_id);setdeleteopen(true)}}>Delete </Button>
           <StyleModal
              open={deleteopen}
              onClose={(e) => {
                setdeleteopen(false);
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
                <Typography variant="h4" color="red" textAlign="center" sx={{mb:"10px"}}>
                  {" "}
                  Warning
                </Typography>
              <Typography variant="h6" color="gray" textAlign="center" sx={{display:"block",mb:"10px"}}>
                  {" "}
                  Do You Want To Delete Assignment? 
                </Typography>
                <Box sx={{desplay:"flex",flexDirection:"row",gap:"20px"}}>
                <Button sx={{ mt:"20px" }} onClick={()=>{setdeleteopen(false)}}>
                  {" "}
                  No
                </Button>
                <Button sx={{float:"right",mt:"20px",mr:"5px" }} onClick={handleDelete}>
                  {" "}
                  Sure
                </Button>
                </Box>
              </Box>
            </StyleModal></div>} </div>
        })}
         
       
     </div>
    </div>
  )
}

export default ClassWork
