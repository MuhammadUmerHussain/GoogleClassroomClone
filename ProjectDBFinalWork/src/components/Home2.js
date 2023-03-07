import React, { useState } from 'react'
import {useGlobalContext} from '../context'
import NavBarBox from './NavBarBox'
import Abc from './Abc'
import axios from "axios";
import Box from './Box'
import classdata from './classdata'
import { Link } from 'react-router-dom'
import './Box.css'
import { useNavigate } from 'react-router-dom';
import { useEffect } from 'react';
import Loader from './Loader';

const Home2 = () => {
    const[load,setload]=useState(false)
    const [stddata,setstddata]=useState([]);
    const[teacherdata,setteacherdata]=useState([])
     const{navbarb,setnavbarb,setclassid,classid}=useGlobalContext();
     const stringifiedPerson = localStorage.getItem('user');
     const [Session,SetSession]=useState(stringifiedPerson)
     const nav=useNavigate();
     const handleClick=(courseid)=>{

        setclassid(courseid);
        nav(`/class/${courseid}`);

        

     }
     const [date,setdate]=useState(false);

     const FetchClassOfStudent =  () => {
      setload(true);
     axios
      .post("http://localhost:8086/AllClassroomsOfStudent", {
        //class_id: id,
        
        "username":Session,
      })

      .then((result) => {
        console.log(Session)
        //console.log(result.data)

        setstddata(result.data);
        setload(false);
      })

      .catch((err) => console.log(err));
  };
   const FetchClassOfTeacher =  () => {
    setload(true);
     axios
      .post("http://localhost:8086/AllClassroomsOfTeacher", {
        //class_id: id,

        "username":Session,
      })

      .then((result) => {
       // console.log(result.data)
console.log(Session)
        setteacherdata(result.data);
        setload(false);
      })

      .catch((err) => console.log(err));
  };

  useEffect(()=>{
    setload(true);
    FetchClassOfStudent();
    FetchClassOfTeacher();
    setload(false)
    // console.log(date+"1");
  },[date])

  if(load)
  {
   return <Loader/> 
  }
  return (
    
    <div>
    
    <NavBarBox setdate={setdate} date={date}/>
    
    <div class="main1">
    {
        stddata.map((curr)=>{
            
            {/* return<Link to={`/class/${curr.id}`}> <Box key={curr.id} {...curr} /></Link> */}
            { return <button className='bttn' onClick={()=>{handleClick(curr.class_id)}}><Box key={curr.class_id} {...curr} /></button> }
        })

        


    }
    {
        teacherdata.map((curr)=>
        {
            
            {/* return<Link to={`/class/${curr.id}`}> <Box key={curr.id} {...curr} /></Link> */}
            { return <button className='bttn' onClick={()=>{handleClick(curr.class_id)}}><Box key={curr.class_id} {...curr} /></button> }
        })
    }
    
        </div>
    </div>
  )
}

export default Home2