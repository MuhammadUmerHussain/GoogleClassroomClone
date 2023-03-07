import { PhotoCamera } from '@mui/icons-material'
import { Button, IconButton } from '@mui/material'
import React from 'react'

const upload = () => {
  return (
    <div>
        <Button variant="contained" component="label">
  Upload
  <input hidden accept="image/*" multiple type="file" />
</Button>
<IconButton color="primary" aria-label="upload picture" component="label">
  <input hidden accept="image/*" type="file" />
  <PhotoCamera />
</IconButton>
    </div>
  )
}

export default upload
