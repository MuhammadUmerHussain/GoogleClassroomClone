import React, { useEffect } from 'react'
import People from './People'
import NavBarClass from './NavBarClass'
import { useGlobalContext } from '../context'
import { Outlet, useNavigate } from 'react-router-dom';
import ClassWork from './ClassWork';
import Stream from './Stream';
const ClassStructure = () => {
    const {navbarb,setnavbarb,classid,clickpeople,isclasswork}=useGlobalContext();
    const nav=useNavigate();
    console.log(classid)

    // useEffect(()=>{
    // <ClassStructure/>
    // },[isclasswork,clickpeople])
  return (
    <div>
   
   
    <NavBarClass/>
    <Outlet />
    
   
      
    </div>
  )
}

export default ClassStructure
