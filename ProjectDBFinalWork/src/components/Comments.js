import React, { useState } from 'react'
import "./Style.css";
import axios from 'axios';
import { useEffect } from 'react';
 
const Comments = ({post_id,date,setDate}) => {

  
    const [commentsdata,setcommetsdata]=useState([{comment_id:"",
		post_id:"",
		comment_time:"",
		comment_text:"" ,
		comment_by_std_username:"",
		comment_by_teacher_username:"" }])
    const handleViewComment = () => {
      console.log("poxtid"+post_id)
    axios
      .post("http://localhost:8086/AllCommentsOnPost", {
        "post_id": post_id,
      })

      .then((result) => {
        console.log( result.data);
       
        const newarr=result.data;
        console.log(newarr);
        
        setcommetsdata(newarr);
        console.log(commentsdata)
        //  result.data.map((curr)=>{
        //      const [{comment_id ,
		//  post_id ,
		//  comment_time ,
		//  comment_text, 
		//  comment_by_std_username,
		//  comment_by_teacher_username }] = curr;

        //  setcommetsdata([])
        //  })
        //  console.log(commentsdata)
    })
    //     setcommetsdata({commentid:comment_id,
	// 	postid:post_id,
	// 	commenttime:comment_time,
	// 	comment_text:comment_text ,
	// 	commentbystdusername:comment_by_std_username,
	// 	commentbyteacherusername:comment_by_teacher_username })

    //     console.log( commentsdata);
    //   })

      .catch((err) => console.log(err));
  };

  useEffect(()=>{
handleViewComment(post_id)
  },[date])
  return (
    <div>
    {
        commentsdata?.map((curr)=>{
            return(
                <div>
                <h6 class="co">
                          <i class="fa-solid fa-user"></i>{curr.comment_by_std_username}
                          {curr.comment_by_teacher_username}
                          <span class="time">{curr.comment_time}</span>
                        </h6>
                        <p>{curr.comment_text}</p> </div>
         ) })
    }
                        
                      

    </div>
  )
}

export default Comments