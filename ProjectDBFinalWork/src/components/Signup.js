

import React, { useState } from "react";
import axios from "axios";
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import { Link, useNavigate } from "react-router-dom";
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { Alert } from "@mui/material";
function Copyright(props) {
  return (
    <Typography variant="body2" color="text.secondary" align="center" {...props}>
      {'Copyright © '}
      <Link color="inherit" href="https://mui.com/">
        Your Website
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}




const Signup = () => {
  const nav=useNavigate();
const [fname,setfname]=useState("");
  const [lname,setlname]=useState("");
  const [uname,setuname]=useState("");
  const [password,setpassword]=useState("");
  const [email,setemail]=useState("");
  const [phone,setphone]=useState("");
  const [gender,setgender]=useState("");
  const[age,setage]=useState("")
const theme = createTheme();
  
const SignApi =  () => {
     axios
      .post("http://localhost:8086/RegisterUser", {
		"username": uname,
		"first_name": fname,
		"last_name": lname,
		"user_password": password,
		"email" : email,
		"phone_no" : phone, 
		"gender": gender,
	 	"age": age
	}
)

      .then((result) => {


        console.log(result.data);

        if(result)
        {
          // alert("Register Sucessfull!")
         <Alert severity="error">This is an error alert — check it out!</Alert>
          nav("/");

        }
        else
        {
          alert("Register UnSucessfull!")
        }
      })

      .catch((err) => console.log(err));
  };
    const handleSubmit = () => {
      console.log(fname);
      console.log(lname);
      console.log(uname);
      console.log(email);
      console.log(phone);
      console.log(age);
      console.log(gender);
      console.log(password);
    SignApi();
    
  };
  return (
    <div><ThemeProvider theme={theme}>
      <Grid container component="main" sx={{ height: '100vh' }}>
        <CssBaseline />
        <Grid
          item
          xs={false}
          sm={4}
          md={7}
          sx={{
            backgroundImage: 'url(https://source.unsplash.com/random)',
            backgroundRepeat: 'no-repeat',
            backgroundColor: (t) =>
              t.palette.mode === 'light' ? t.palette.grey[50] : t.palette.grey[900],
            backgroundSize: 'cover',
            backgroundPosition: 'center',
          }}
        />
        <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
          <Box
            sx={{
              my: 8,
              mx: 4,
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
            }}
          >
            <Avatar sx={{ m: 1, bgcolor: '#75c9b7' }}>
              <LockOutlinedIcon />
            </Avatar>
            <Typography component="h1" variant="h5">
              Sign in
            </Typography>
            <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 1 }}>
              
              <TextField
                margin="normal"
                required
                fullWidth
                id="fname"
                label="First Name"
                name="fname"
                onChange={(e)=>{
                  setfname(e.target.value)
                }}  
                autoFocus
              />
              <TextField
                margin="normal"
                required
                fullWidth
                id="lname"
                label="Last Name"
                name="lname"
                onChange={(e)=>{
                  setlname(e.target.value)
                }}
                autoFocus
              />
              <TextField
                margin="normal"
                required
                fullWidth
                id="Uname"
                label="User Name"
                name="Uname"
                onChange={(e)=>{
                  setuname(e.target.value)
                }}
                autoFocus
              />
              <TextField
                margin="normal"
                required
                fullWidth
                id="UID"
                label="Email"
                name="UID"
                onChange={(e)=>{
                  setemail(e.target.value)
                }}
                autoFocus
              />
               <TextField
                margin="normal"
                required
                fullWidth
                id="PNO"
                label="Phone No"
                name="PNO"
                onChange={(e)=>{
                  setphone(e.target.value)
                }}
                autoFocus
              />
              <TextField
                margin="normal"
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                onChange={(e)=>{
                  setpassword(e.target.value)
                }}
                autoComplete="current-password"
              />
              <TextField
                margin="normal"
                required
                fullWidth
                name="password"
                label="Age"
                
                id="password"
                onChange={(e)=>{
                  setage(e.target.value)
                }}
                autoComplete="current-password"
              />
              <TextField
                margin="normal"
                required
                fullWidth
                
                label="Gender"
                
                onChange={(e)=>{
                  setgender(e.target.value)
                }}
                autoComplete="current-password"
              />
              <FormControlLabel
                control={<Checkbox value="remember" color="primary" />}
                label="Remember me"
              />
              <Button
                
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2,background:"#75c9b7" }}
                onClick={handleSubmit}
              >
                Sign In
              </Button>
              <Grid container>
                <Grid item xs>
                  <Link to="/" variant="body2">
                    Forgot password?
                  </Link>
                </Grid>
                <Grid item>
                  <Link href="#" variant="body2">
                    {"Already Have Account? Login"}
                  </Link>
                </Grid>
              </Grid>
              <Copyright sx={{ mt: 5 }} />
            </Box>
          </Box>
        </Grid>
      </Grid>
    </ThemeProvider></div>
  )
}

export default Signup