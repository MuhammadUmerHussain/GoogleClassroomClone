import React from 'react'
import axios from 'axios'
import "./Style.css";
import { useState } from 'react';
const CommentOnPost = ({post_id,date,setDate}) => {
    const[commentText,setcommentText]=useState("");
    const Session=localStorage.getItem("user")
console.log(date+"date head")
    const handleComment=()=>{
         CreateCommentAPI()
       // console.log("comment on post")
    }
 const CreateCommentAPI = () => {

    
    
    
    axios
      .post("http://localhost:8086/CommentOnPost",{
		"post_id": post_id,
		"by_student": Session,
		"comment_text": commentText
	})

      .then((result) => {
        setDate(!date);
        console.log(date+"axios")
         console.log(result.data)
      })

      .catch((err) => console.log(err));
  };

  return (
    <div><div class="comments">
                    <input type="text" placeholder="Add class comments" onChange={(e)=>{
    setcommentText(e.target.value)}}/>
                    <i class="fa-solid fa-circle-arrow-right bg-secondary text-white" onClick={handleComment}></i>
                  </div></div>
  )
}

export default CommentOnPost