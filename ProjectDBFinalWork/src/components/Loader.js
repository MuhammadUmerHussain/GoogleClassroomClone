import { Box, CircularProgress } from '@mui/material'

import React from 'react'

const Loader = () => {
  return (
    <div>
      <Box sx={{ display: 'flex',width:"100%",height:"100vh" ,justifyContent: 'center',alignItems:'center'}}>
      <CircularProgress sx={{  }}/>
    </Box>
    </div>
  )
}

export default Loader
