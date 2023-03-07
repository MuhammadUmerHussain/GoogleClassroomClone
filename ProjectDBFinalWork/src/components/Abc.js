import React, { useState } from 'react'
import { useGlobalContext } from '../context'

import MenuIcon from '@mui/icons-material/Menu';
import SidebarData from './SidebarData'
import { Link } from 'react-router-dom';
import Box from '@mui/material/Box';
import Drawer from '@mui/material/Drawer';
import Button from '@mui/material/Button';
import List from '@mui/material/List';
import Divider from '@mui/material/Divider';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import InboxIcon from '@mui/icons-material/MoveToInbox';
import './link.css'
import MailIcon from '@mui/icons-material/Mail';
import classdata from './classdata'
import axios from "axios";
import { useParams } from "react-router-dom";
import { useEffect } from 'react';
const Abc = () => {
  const{issidebaropen}=useGlobalContext();
  const home="Home";
  const [state, setState] = React.useState({
    top: false,
    left: false,
    bottom: false,
    right: false,
  });
  const { id } = useParams();
  const Session = localStorage.getItem("user");
   const [date,setdate]=useState(false);

    const [teacherdata,setteacherdata]=useState("")
    const [stddata,setstddata]=useState("")
     const FetchClassOfStudent =  () => {
      //setload(true);
     axios
      .post("http://localhost:8086/AllClassroomsOfStudent", {
        //class_id: id,
        
        "username":Session,
      })

      .then((result) => {
        console.log(Session)
        //console.log(result.data)

        setstddata(result.data);
        //setload(false);
      })

      .catch((err) => console.log(err));
  };
   const FetchClassOfTeacher =  () => {
    //setload(true);
     axios
      .post("http://localhost:8086/AllClassroomsOfTeacher", {
        //class_id: id,

        "username":Session,
      })

      .then((result) => {
       // console.log(result.data)
console.log(Session)
        setteacherdata(result.data);
        //setload(false);
      })

      .catch((err) => console.log(err));
  };

  useEffect(()=>{
    FetchClassOfStudent();
    FetchClassOfTeacher();
    // console.log(date+"1");
  },[date])

  const [data,setdata]=useState(classdata)

  const toggleDrawer = (anchor, open) => (event) => {
    if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
      return;
    }

    setState({ ...state, [anchor]: open });
  };

  const list = (anchor) => (
    <Box
      sx={{ width: anchor === 'top' || anchor === 'bottom' ? 'auto' : 250 }}
      role="presentation"
      onClick={toggleDrawer(anchor, false)}
      onKeyDown={toggleDrawer(anchor, false)}
    >
      <List>
       <ListItem  disablePadding >
           <Link to={'/Home'} className='linkss'> <ListItemButton >
              <ListItemIcon>
                 <InboxIcon /> 
              </ListItemIcon>
              <ListItemText primary={home}  />
            </ListItemButton></Link>
          </ListItem>
        { 
          
          stddata.length>0 && stddata?.map((text, index) => (
          <ListItem key={text} disablePadding >
           <Link to={`/class/${text.class_id}`} className='linkss'> <ListItemButton >
              <ListItemIcon>
                {index % 2 === 0 ? <InboxIcon /> : <MailIcon />}
              </ListItemIcon>
              <ListItemText primary={text.class_name} />
            </ListItemButton></Link>
          </ListItem>
        ))}

       
        {
          teacherdata.length>0  && teacherdata?.map((text, index) => (
          <ListItem key={text} disablePadding >
           <Link to={`/class/${text.class_id}`} className='linkss'> <ListItemButton >
              <ListItemIcon>
                {index % 2 === 0 ? <InboxIcon /> : <MailIcon />}
              </ListItemIcon>
              <ListItemText primary={text.class_name} />
            </ListItemButton></Link>
          </ListItem>
        ))}
      </List>
      <Divider />
      {
    
      }
    </Box>

    
  );

  
  return (
    
    <div>
      {['Classes'].map((anchor) => (
        <React.Fragment key={anchor}>
          <Button  onClick={toggleDrawer(anchor, true)}><MenuIcon sx={{color:'white'}}/></Button>
          <Drawer
            anchor={anchor}
            open={state[anchor]}
            onClose={toggleDrawer(anchor, false)}
          >
            {list(anchor)}
          </Drawer>
        </React.Fragment>
      ))}
    </div>
  );
  
}

export default Abc