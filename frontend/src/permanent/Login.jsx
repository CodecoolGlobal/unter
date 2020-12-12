import React ,{useState} from 'react';
import Modal from '@material-ui/core/Modal';
import { Avatar } from '@material-ui/core';
import ExpandMoreIcon from "@material-ui/icons/ExpandMore";
import './Login.scss';
import CloseIcon from '@material-ui/icons/Close';

export default function SimpleModal() {
  const [open, setOpen] = useState(false);
  const [emailEffect,setEmailEffect] = useState(false);
  const [pswEffect,setPswEffect] = useState(false);
  const [isActive, setIsActive] = useState(false);
  function handleTextChange(target) {
      switch(target.id){
        case "email":
            target.value=== ''? setEmailEffect(false): setEmailEffect(true);
            break;
        case "psw":
            target.value=== ''? setPswEffect(false): setPswEffect(true);
            break;
      }


  }

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
            <input type="psw" id="psw"  onChange={(e) => handleTextChange(e.target)}/>
            <label  className={ pswEffect ? "Active" : ""} htmlFor="psw">Password</label>
            </div>
        </div>
        <button className="login__button">Login</button>
    </div>
  );

  return (
    <div className="login">
      <button type="button" className="avatar__button" onClick={handleOpen}>
        <ExpandMoreIcon />
        <Avatar/>
      </button>
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
