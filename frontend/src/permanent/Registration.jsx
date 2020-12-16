import React,{useState} from 'react'
import Modal from '@material-ui/core/Modal';
import './Registration.scss';
import CloseIcon from '@material-ui/icons/Close';

function Registration() {
    const [open, setOpen] = useState(false);
  const [firstNameEffect,setFirstNameEffect] = useState(false);
  const [lastNameEffect,setLastNameEffect] = useState(false);
  const [birthDateEffect,setBirthDateEffect] = useState(false);
  const [emailEffect,setEmailEffect] = useState(false);
  const [pswEffect,setPswEffect] = useState(false);
  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  function handleTextChange(target) {
      switch(target.id){
        case "firstName":
            target.value=== ''? setFirstNameEffect(false): setFirstNameEffect(true);
            break;
        case "lastName":
            target.value=== ''? setLastNameEffect(false): setLastNameEffect(true);
            break;
        case "birthDate":
            target.value=== ''? setBirthDateEffect(false): setBirthDateEffect(true);
            break;
        case "email":
            target.value=== ''? setEmailEffect(false): setEmailEffect(true);
            break;
        case "psw":
            target.value=== ''? setPswEffect(false): setPswEffect(true);
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
                        <input type="text" id="firstName"  onChange={(e) => handleTextChange(e.target)}/>
                        <label className={ firstNameEffect ? "Active" : ""} htmlFor="firstName">First name</label>
                    </div>
                    <div className="label">
                        <input type="email" id="lastName"  onChange={(e) => handleTextChange(e.target)}/>
                        <label className={ lastNameEffect ? "Active" : ""} htmlFor="lastName">Last Name</label>
                    </div>
                </div>
                    <h5>Gondoskodj róla, hogy egyezzen a hivatalos személyazonosító okmányodon szereplő névvel.
</h5>
                <div className="birthArea">
                    <div className="label">
                        <input type="email" id="birthDate"  onChange={(e) => handleTextChange(e.target)}/>
                        <label className={ birthDateEffect ? "Active" : ""} htmlFor="birthDate">Bith date</label>
                    </div>
                </div>
                <h5>A regisztrációhoz legalább 18 évesnek kell lenned. A születésnapodat nem osztjuk meg az Airbnb más felhasználóival.</h5>
                <div className="emailArea">
                    <div className="label">
                        <input type="email" id="email"  onChange={(e) => handleTextChange(e.target)}/>
                        <label className={ emailEffect ? "Active" : ""} htmlFor="email">Email</label>
                    </div>
                </div>
                <h5>Küldünk majd egy e-mailt az utazás visszaigazolásaival és nyugtáival.
</h5>
                <div className="pswArea">
                    <div className="label">
                        <input type="psw" id="psw"  onChange={(e) => handleTextChange(e.target)}/>
                        <label  className={ pswEffect ? "Active" : ""} htmlFor="psw">Password</label>
                    </div>
                </div>
                <h5>A lenti Elfogadás és folytatás gombra kattintva elfogadom az Airbnb által meghatározott Általános Szerződési Feltételek, Fizetésekre vonatkozó szerződési feltételek, Adatvédelmi Szabályzat és Diszkriminációellenes szabályzat rendelkezéseit.</h5>
            </div>
            <button className="register__button">Register</button>
            <h6>Az Airbnb csak tagoknak szóló ajánlatokat, inspirációt, marketing-e-maileket és push-értesítéseket fog küldeni neked. A fiókbeállításoknál vagy közvetlenül a marketingértesítésben is bármikor leállíthatod az ilyen típusú üzenetek fogadását.

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
