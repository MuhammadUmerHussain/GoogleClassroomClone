import React, { useRef, useState } from 'react'
import "./Login.css"
import axios from "axios";
import { FcGoogle } from 'react-icons/fc';
import data from './data';
import { useGlobalContext } from '../context';
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';
import { Alert } from '@mui/material';

const Login = () => {

    

    const[validdata,setvaliddata]=useState(data);


    const [name,setname]=useState('');
    const [password,setpassword]=useState('');
    
   
    
    const nav=useNavigate();
    
const LoginAPI=  () => {
     axios
      .post("http://localhost:8086/ValidateUser",{
		"username": name,
		"password": password
	})

      .then((result) => {

        console.log(result.data)

        if(result.data)
        {
            //SetSession({name:name,password:password});

                    
                   localStorage.setItem('user', name);
                   //(true);
                    nav('/home')
        }
        else
        {
             alert("Wrong Password Please Enter Again");
           
        }
        
        
      })

      .catch((err) => console.log(err));
  };

  const handleSubmit=()=>{
    LoginAPI();
    //validpassword();
  }

    const validpassword=(e)=>{
        // e.preventDefault();
        
        console.log("u")
        let count=0;
        const check=validdata.map((curr)=>{
            if(curr.name===name)
            {
                if(curr.password===password)
                {
                    //setperson({firstName:curr.name,lastName:curr.password})
                  // SetSession({name:name,password:password});

                    
                   localStorage.setItem('user', name);
                   //setloginstate(true);
                    nav('/home')
                    
                }
            }
           else{

           }
        })
        

    }

  return (
    <main >
    
    <div className="container">
       <div className="item1">
<div className="form1" >
 <h1>Login</h1>
 <label  >Email:</label>
 <input type="text" placeholder="abc@gmail.com"  onChange={(e)=>{
    setname(e.target.value)
 }}/>
 
<label >Password:</label>
<input type="password" placeholder="*********"  onChange={(e)=>{
    setpassword(e.target.value)
}}/>
<button className="l" onClick={handleSubmit}>Login</button>
{/* <a href="">Forgot your password?</a>
<button className="g" ><FcGoogle/> Continue With Google</button> */}
<Link to='/signup' className='lia'>  <h4>Dont have account?</h4> start here</Link>
</div>
 
       </div> 
        <div className="item2">
        
             {/*   
             <Alert severity="error">This is an error alert — check it out!</Alert>*/}
               <h1>{name}</h1>
             
            <h1>{password}</h1>

        </div>
    </div>
        
    </main>
  )
}

export default Login