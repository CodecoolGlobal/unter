import React,{useState} from 'react'
import Modal from '@material-ui/core/Modal';
import './Registration.scss';
import CloseIcon from '@material-ui/icons/Close';
import Axios from 'axios';

function Registration() {
    const [open, setOpen] = useState(false);
  const [firstNameEffect,setFirstNameEffect] = useState(false);
  const [lastNameEffect,setLastNameEffect] = useState(false);
  const [birthDateEffect,setBirthDateEffect] = useState(true);
  const [emailEffect,setEmailEffect] = useState(false);
  const [pswEffect,setPswEffect] = useState(false);
  const [firstName,setFirstName] = useState();
  const [lastName,setLastName] = useState();
  const [birthDate,setBirthDate] = useState();
  const [email,setEmail] = useState();
  const [password,setPsw] = useState();
  const [user,setUser] = useState();
  const [firstNameStyle,setFirstNameStyle] = useState({border: "1px solid grey",
  borderRadius: "5px"})
  const [style,setStyle] =useState({border: "1px solid grey",
  borderRadius: "5px"});
  const [emailStyle,setEmailStyle] =useState({border: "1px solid grey",
  borderRadius: "5px"});


  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };
  const handleSubmit = async e => {
    e.preventDefault();
    const user = { email, password,firstName,lastName,birthDate };
    // send the username and password to the server
    console.log(JSON.stringify(user)+"CICAAAAA")
    const response = await Axios.post(
      "http://localhost:8762/reg",
      user
    );
    // set the state of the user
    setUser(response.data)
    // store the user in localStorage
    localStorage.setItem('user', response.data)
    handleClose();
    console.log(response.data)
  };
  
  function handleTextChange(target) {
      switch(target.id){
        case "firstName":
            target.value=== ''? setFirstNameEffect(false): setFirstNameEffect(true);
            setFirstName(target.value);
            setFirstNameStyle({border: "1px solid grey",
            borderRadius: "5px"})
            if(target.value === ''){
                setFirstNameStyle({border: "1px solid red",
                    borderRadius: "5px"})
            }
            break;
        case "lastName":
            target.value=== ''? setLastNameEffect(false): setLastNameEffect(true);
            setLastName(target.value)
            setStyle({border: "1px solid grey",
            borderRadius: "5px"})
            if(target.value === ''){
                setStyle({border: "1px solid red",
                    borderRadius: "5px"})
            }
            break;
        // case "birthDate":
        //     target.value=== ''? setBirthDateEffect(false): setBirthDateEffect(true);
        //     setBirthDate(target.value)
        //     break;
        case "email":
            let re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            target.value=== ''? setEmailEffect(false): setEmailEffect(true);
            setEmail(target.value)
            if( re.test(target.value)){
                setEmailStyle({border: "1px solid grey",
                borderRadius: "5px"});

            }
            else{
                setEmailStyle ({border: "1px solid red",
                borderRadius: "5px"});
            }
            break;
        case "psw":
            target.value=== ''? setPswEffect(false): setPswEffect(true);
            setPsw(target.value)
            break;
      }
     

  }
    const body = (
        <div className="register__modal">
            <div className="register__header">
                <CloseIcon onClick={handleClose}/>
                <h2 className="title">Registration</h2>
            </div>
            <div className="register__body">
                <div className="nameArea">
                    <div className="label">
                        <input style = {firstNameStyle} type="text" id="firstName"  onChange={(e) => handleTextChange(e.target)}/>
                        <label  className={ firstNameEffect ? "Active" : ""} htmlFor="firstName">First name</label>
                    </div>
                    <div className="label">
                        <input style={style} type="email" id="lastName"  onChange={(e) => handleTextChange(e.target)}/>
                        <label className={ lastNameEffect ? "Active" : ""} htmlFor="lastName">Last Name</label>
                    </div>
                </div>
                    <h5>Make sure it matches the name on your government ID.
</h5>
                <div className="birthArea">
                    <div className="label">
                        <input type="date" id="birthDate"  onChange={(e) => handleTextChange(e.target)}/>
                        <label className={ birthDateEffect ? "Active" : ""} htmlFor="birthDate">Bith date</label>
                    </div>
                </div>
                <h5>To sign up, you need to be at least 18. Your birthday won’t be shared with other people who use Airbnb.</h5>
                <div className="emailArea">
                    <div className="label">
                        <input style={emailStyle} type="email" id="email"  onChange={(e) => handleTextChange(e.target)}/>
                        <label className={ emailEffect ? "Active" : ""} htmlFor="email">Email</label>
                    </div>
                </div>
                <h5>We'll email you trip confirmations and receipts.
</h5>
                <div className="pswArea">
                    <div className="label">
                        <input type="password" id="psw"  onChange={(e) => handleTextChange(e.target)}/>
                        <label  className={ pswEffect ? "Active" : ""} htmlFor="psw">Password</label>
                    </div>
                </div>
                <h5>By selecting Agree and continue below, I agree to Airbnb’s Terms of Service, Payments Terms of Service, Privacy Policy, and Nondiscrimination Policy.</h5>
            </div>
            <button className="register__button" onClick={handleSubmit}>Register</button>
            <h6>Airbnb will send you members-only deals, inspiration, marketing emails, and push notifications. You can opt out of receiving these at any time in your account settings or directly from the marketing notification.

            </h6>
        </div>
      );


    return (
    <div className="registration__container">
        <p onClick={handleOpen}>Register</p>
        <Modal
            open={open}
            onClose={handleClose}
            aria-labelledby="simple-modal-title"
            aria-describedby="simple-modal-description"
        >
        {body}
        </Modal>
    </div>
    )
}

export default Registration
