import { PhotoCamera } from '@mui/icons-material'
import { Button, IconButton } from '@mui/material'
import React from 'react'
import { useState } from 'react'

const UploadButton = ({setupload,setgetfiles}) => {

  const [files,setfiles]=useState("")
  const[dia,setdia]=useState(false)
  const handleFiles=(e)=>{
    console.log("a")
    //setfiles(e.target.files);
    setgetfiles(e.target.files[0]);
    
    setupload(false)
    setdia(true)
  }

  if(dia)
  {
    return <h1>Umer</h1>
  }
  return (
    <div>
    {}
        <Button variant="contained" component="label" sx={{mt:"80px"}}>
  Upload
  <input hidden  multiple accept=".txt,.docx,.pdf,.jpeg,.png "  type="file" onChange={handleFiles} />
</Button>

    </div>
  )
}

export default UploadButton
