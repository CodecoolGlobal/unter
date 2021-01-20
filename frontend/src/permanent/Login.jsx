import React ,{useState} from 'react';
import Modal from '@material-ui/core/Modal';
import './Login.scss';
import CloseIcon from '@material-ui/icons/Close';
import Axios from 'axios';
import Registration from './Registration';
import {Link} from 'react-router-dom'


export default function SimpleModal() {
  const [open, setOpen] = useState(false);
  const [emailEffect,setEmailEffect] = useState(false);
  const [pswEffect,setPswEffect] = useState(false);
  const [email,setEmail] = useState();
  const [password,setPsw] = useState();
  const [user,setUser]=useState();


  const handleSubmit = async e => {
    e.preventDefault();
    const user = { email, password };
    console.log(user);
    var response;
    // send the username and password to the server
    try{
       response = 
      await Axios.post(
      "http://localhost:8762/auth",     
      user
    );
    setUser(response.data)
    console.log(user)
    // let localUser = JSON.parse(response.data)
    localStorage.setItem('user',JSON.stringify(response.data))
    // console.log(localUser)
    handleClose()
  }
    catch(error){
      alert("Your email or password is incorrect "+error)
    }
    // set the state of the user
   
  };
  function handleTextChange(target) {
      switch(target.id){
        case "email":
            target.value=== ''? setEmailEffect(false): setEmailEffect(true);
            setEmail(target.value)
            break;
        case "psw":
            target.value=== ''? setPswEffect(false): setPswEffect(true);
            setPsw(target.value)
            break;
      }


  }
  // const register =() =>{
  //   return (
  //     <Registration/>
  //   )
  // }

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const body = (
    <div className="login__modal">
        <div className="login__header">
            <CloseIcon onClick={handleClose}/>
            <h2 className="title">Login</h2>
        </div>
        <div className="login__body">
            <div className="float-label">
            <input type="email" id="email"  onChange={(e) => handleTextChange(e.target)}/>
            <label className={ emailEffect ? "Active" : ""} htmlFor="email">Email</label>
            </div>
            <div className="float-label">
            <input type="password" id="psw"  onChange={(e) => handleTextChange(e.target)}/>
            <label  className={ pswEffect ? "Active" : ""} htmlFor="psw">Password</label>
            </div>
        </div>
        <button className="login__button" onClick={handleSubmit}>Login</button>
        <h4>Forgot your password?</h4>
        <h4>Don't have profile? Register!</h4>
    </div>
  );
  
  return (
    <div className="login" >
      <p onClick={handleOpen}>Login</p>
      {/* <MenuItem onClick={handleOpen}>Login</MenuItem> */}
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="simple-modal-title"
        aria-describedby="simple-modal-description"
      >
        {body}
      </Modal>
    </div>
  );
}
