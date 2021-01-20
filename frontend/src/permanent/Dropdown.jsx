import React,{useState} from 'react';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import './Dropdown.scss';
import Login from './Login';
import ExpandMoreIcon from "@material-ui/icons/ExpandMore";
import { Avatar } from '@material-ui/core';
import Registration from './Registration';
import { Link, useHistory } from "react-router-dom";
import { db } from "../Firebase";
import Axios from 'axios';



export default function Dropdown() {
  const [anchorEl, setAnchorEl] = React.useState(null);
  const history = useHistory();
  const [img,setImg] = useState();


  let content;

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };
  const myAcc=()=>{
    history.push("/profile");
    handleClose();
  }
  const logOut =() =>{
    localStorage.clear()
    Axios.get("http://localhost:8762/auth/logout",{withCredentials: true})
    setAnchorEl(null);
  }

  
  if(localStorage.getItem('user')!== null){
    db.child('images/IMG_0954-nologo.jpg').getDownloadURL().then((url=>{
      setImg(url);
    }))
    console.log(img)
    content=(
    <div className="dropdown">
    <button type="button" className="avatar__button" onClick={handleClick}>
      <ExpandMoreIcon/>
      <img className="profilePicture" src={img} alt=""/>  
    </button>
    <Menu
      id="simple-menu"
      anchorEl={anchorEl}
      style={{top:"5vh", width:"10vw",borderRadius:"16px"}}
      keepMounted
      open={Boolean(anchorEl)}
      onClose={handleClose}
    >
      <MenuItem onClick={myAcc}>My account</MenuItem>
      <MenuItem onClick={logOut}>Log out</MenuItem>
    </Menu>
  </div>)
  }
  else{
    db.child('images/IMG_0954-nologo.jpg').getDownloadURL().then((url=>{
      setImg(url);
    }))
    console.log(img);
    content=(
      <div className="dropdown">
      <button type="button" className="avatar__button" onClick={handleClick}>
        <ExpandMoreIcon/>
        <Avatar/> 
    </button>
      <Menu
        id="simple-menu"
        anchorEl={anchorEl}
        style={{top:"5vh", width:"10vw",borderRadius:"16px"}}
        keepMounted
        open={Boolean(anchorEl)}
        onClose={handleClose}
      >
        <MenuItem onClick={handleClose}><Login/></MenuItem>
        <MenuItem onClick={handleClose}><Registration/></MenuItem>
      </Menu>
    </div>
    )
  }

  return content; 

}