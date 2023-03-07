import React, { useEffect,useState } from 'react'
import { Link, useParams } from 'react-router-dom'
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
import MenuIcon from '@mui/icons-material/Menu';
import Container from '@mui/material/Container';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import Tooltip from '@mui/material/Tooltip';
import MenuItem from '@mui/material/MenuItem';
import AdbIcon from '@mui/icons-material/Adb';
import Abc from './Abc';
import axios from 'axios';
import People from './People';
import { useGlobalContext } from '../context';
import { TryRounded } from '@mui/icons-material';
import ClassWork from './ClassWork';

import './people.css'


const styles = {
  textDecoration: 'none',
  '&:hover': {
    textDecoration: 'underline',
}}
const pages = ['Stream', 'Classwork', 'People','Submission'];
const settings = ['Profile', 'Account', 'Dashboard', 'Logout'];

const NavBarClass = () => {
 const [anchorElNav, setAnchorElNav] = React.useState(null);
 const {clickpeple,setclickpeople,setclasswork,isclasswork}=useGlobalContext();
  const [anchorElUser, setAnchorElUser] = React.useState(null);

  const [classname,setclassname]=useState([
	{
		class_id: "",
		class_name: "",
		course_title:"" ,
		description:"" ,
		unique_class_code:"" ,
		date_created: "",
		course_Code: ""
	}
])
  const {id:classid} = useParams()
  const [isteacher,setisteacher]=useState(false);
  const Session = localStorage.getItem("user");
  const links=[{name:"Stream",link:`/class/${classid}`},{name:"ClassWork",link:`/class/${classid}/classwork`}, {name:"People",link:`/class/${classid}/people`}]



  const ClassNameApi = () => {
    axios
      .post("http://localhost:8086/GetClassData", {
		"class_id": classid
	})

      .then((result) => {
        
        // console.log(result.data);
        const temp=result.data;
        //console.log(class_name);
        setclassname(temp);
        console.log(classname)       
      })
      .catch((err) => console.log(err));
  };

  const sub={name:"Submission",
  link:`/class/${classid}/Submission`
}
  const handleOpenNavMenu = (event) => {
    setAnchorElNav(event.currentTarget);
  };
  const handleOpenUserMenu = (event) => {
    setAnchorElUser(event.currentTarget);
  };
  const[st,setst]=useState({name:"",link:""})
  const fetch=()=>{

  }
  const IsteacherOfclassAPI = () => {
    axios
      .post("http://localhost:8086/IsTeacherOfaClass", {
        class_id: classid,
        TeacherUsername: Session,
      })

      .then((result) => {
        setisteacher(result.data);
      })

      .catch((err) => console.log(err));
  };
  const handleCloseNavMenu = (ind) => {
    if(ind=='2')
    {
      setclasswork(false)
        setclickpeople(true);
        
    }
    if(ind=='1')
    {

      setclickpeople(false)
      setclasswork(true)
      console.log(isclasswork)
    }
    setAnchorElNav(null);
  };
useEffect(()=>{
IsteacherOfclassAPI()
ClassNameApi()
},[])

  const handleCloseUserMenu = () => {
    setAnchorElUser(null);
  };

  return (
    <AppBar position="static" sx={{ bgcolor: "#75c9b7;" }} >
      <Container maxWidth="xl">
        <Toolbar disableGutters >
          
          <Abc/>
          <Link to={links[0].link} className="linkclass">  <Typography
            variant="h6"
            noWrap
            component="a"
            
            sx={{
              mr: 2,
              display: { xs: 'none', md: 'flex' },
              
              fontWeight: 700,
              letterSpacing: '.1rem',
              color: "white",
              textDecoration: 'none',
              '&:hover': {
    color: '#247579',
    
}
            }}
          >
            {classname[0].class_name}
          </Typography></Link>

          <Box sx={{ flexGrow: 1, display: { xs: 'flex', md: 'none' } }}>
            <IconButton
              size="large"
              aria-label="account of current user"
              aria-controls="menu-appbar"
              aria-haspopup="true"
              onClick={handleOpenNavMenu}
              color="inherit"
            >
              
            </IconButton>
            
          </Box>
          <AdbIcon sx={{ display: { xs: 'flex', md: 'none' }, mr: 1 }} />
          <Typography
            variant="h5"
            noWrap
            component="a"
            href=""
            sx={{
              mr: 2,
              display: { xs: 'flex', md: 'none' },
              flexGrow: 1,
              fontFamily: 'monospace',
              fontWeight: 700,
              letterSpacing: '.3rem',
              color: 'inherit',
              textDecoration: 'none',
            }}
          >
            ClassNames 
          </Typography>
        
          <Box sx={{ flexGrow: 1, display: { md: 'flex' } }}>
            {links.map((curr,ind) => (
              <Button
                key={curr}
                
                sx={{ my: 2, color: 'white', display: 'block' }}
              >
              
              
               <Link to={curr.link} className='lin'> {curr.name}</Link> 
              </Button>
            ))}
            {
              isteacher &&  <Button
                
                
                sx={{ my: 2, color: 'white', display: 'block' }}
              >
              
              
               <Link to={sub.link} className='lin'> {sub.name}</Link> 
              </Button>
            }
          </Box>
            
         {//remove user menu
         }
        </Toolbar>
      </Container>
    </AppBar>
    
  );

}

export default NavBarClass