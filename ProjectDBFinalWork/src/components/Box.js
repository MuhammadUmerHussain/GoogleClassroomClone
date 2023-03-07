import React from 'react'
import "./home.css"
import NavBarBox from './NavBarBox'
import ColorGen from './ColorGen'
import { useEffect } from 'react'

const Box = ({class_name,course_title,course_Code}) => {
   const { color, generateColor } 
            = ColorGen();
      
            useEffect(()=>{
              generateColor();
            },[])
           
  return (
    <div>
    

<div class="classes">
        <div class="header " id="one" style={{ 
      backgroundColor: "#" + color 
    }}>
            <span class="dot"><i class="fa-solid fa-ellipsis-vertical"></i></span>
          <h3>{class_name}</h3>
          <h6>{course_title}</h6>
          <p>{course_Code}</p>
        </div>
    </div>
    </div>
  )
}

export default Box
