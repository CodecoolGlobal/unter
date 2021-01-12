import React, { useState } from 'react'
import  './UserPage.scss'
import LockIcon from '@material-ui/icons/Lock';
import RecentActorsIcon from '@material-ui/icons/RecentActors';
import { Button } from '@material-ui/core';





function UserPage() {
    const [editEffect,setEditEffect] = useState(false);
    const [name,setName] = useState();
    const [nameEffect,setNameEffect] = useState(false);
    const [genderEffect,setGenderEffect] = useState(false);
    const [birthDateEffect,setBirthDateEffect] = useState(false);
    const [emailEffect,setEmailEffect] = useState(false);
    const [phoneEffect,setPhoneEffect] = useState(false);
    const [addressEffect,setAddressEffect] = useState(false);

    const editProfile = (e) =>{
        switch(e.target.id){
            case "name":
                setNameEffect(!nameEffect)
                break;
            case "gender":
                setGenderEffect(!genderEffect)
                break;
            case "birthDate":                
                setBirthDateEffect(!birthDateEffect)
                break;
            case "email":
                setEmailEffect(!emailEffect)
                break;
            case "phone":
                setPhoneEffect(!phoneEffect)
                break;
            case "address":
                setAddressEffect(!addressEffect)
                break;
        }
    }
    return (
        <div className="userPageContainer">
            <div className="pageTitle">
                <h1>Personal info</h1>
            </div>
            <div className="userPage">
          
            <div className="personalData">
                <div className="settings">
                    <div className="field">
                        <div className="name">
                        <h2>Legal name</h2>
                        {nameEffect?(
                        <div className="editName">
                            <p>
                                This is the name on your travel document, which could be a license or a passport.
                            </p>
                            <div className="nameInput">
                            <label className="firstName">
                                    First Name
                                    <input type="text">
                                    </input>
                                </label>
                                <label className="lastName">
                                    Last Name
                                    <input type="text">
                                    </input>
                                </label>
                            </div>
                            <Button>Save</Button>
                        </div>
                        ):(<p>Mészáros Lőrinc</p>) }
                        </div>
                    <div className={nameEffect? "editNone" : "edit"}>
                    <h2 id="name" onClick={(e) => editProfile(e)}> {nameEffect? "Cancel":"Edit"}</h2>  
                    </div>
                    </div>
                    <div className="field">
                        <div className="gender">
                        <h2>Gender</h2>
                        {genderEffect?(
                        <div className="editName">
                            <div className="nameInput">
                            <label className="firstName">
                                    <input type="text">
                                    </input>
                                </label>
                            </div>
                            <Button>Save</Button>
                        </div>
                        ):(<p>troll</p>) }                        </div>
                    <div className="edit">
                    <h2 id="gender" onClick={(e) => editProfile(e)}> {genderEffect? "Cancel":"Edit"}</h2>  
                    </div>
                    </div>
                    <div className="field">
                        <div className="birthDate">
                        <h2>Date of birth</h2>
                        {birthDateEffect?(
                        <div className="editName">
                            <div className="nameInput">
                            <label className="firstName">
                                    <input type="date">
                                    </input>
                                </label>
                            </div>
                            <Button>Save</Button>
                        </div>
                        ):(<p>1960.12.01</p>) }
                        </div>
                    <div className="edit">
                    <h2  id="birthDate" onClick={(e) => editProfile(e)}> {birthDateEffect? "Cancel":"Edit"}</h2>  
                    </div>
                    </div>
                    <div className="field">
                        <div className="email">
                        <h2>Email adress</h2>
                        {emailEffect?(
                        <div className="editName">
                            <p>
                            Use an address you’ll always have access to.

                            </p>
                            <div className="nameInput">
                            <label className="firstName">
                                    Email
                                    <input type="email">
                                    </input>
                                </label>
                            </div>
                            <Button>Save</Button>
                        </div>
                        ):(<p>lopok@pénzt.com</p>) }                        </div>
                    <div className="edit">
                    <h2 id="email" onClick={(e) => editProfile(e)}>{emailEffect? "Cancel":"Edit"}</h2>  
                    </div>
                    </div>
                    <div className="field">
                        <div className="phone">
                        <h2>Phone number</h2>
                        {phoneEffect?(
                        <div className="editName">
                           
                            <div className="nameInput">
                            <label className="firstName">
                                    <input type="email">
                                    </input>
                                </label>
                            </div>
                            <Button>Save</Button>
                        </div>
                        ):(<p>+36151234567</p>) }
                        </div>
                    <div className="edit">
                    <h2 id="phone" onClick={(e) => editProfile(e)}>{phoneEffect? "Cancel":"Edit"}</h2>  
                    </div>
                    </div>
                    <div className="field">
                        <div className="email">
                        <h2>Address</h2>
                        {addressEffect?(
                        <div className="editName">
                          
                            <div className="nameInput">
                            <label className="firstName">
                                
                                    <input type="email">
                                    </input>
                                </label>
                            </div>
                            <Button>Save</Button>
                        </div>
                        ):(<p>felcsút</p>) }
                        </div>
                    <div className="edit">
                    <h2 id="address" onClick={(e) => editProfile(e)}>{addressEffect? "Cancel":"Edit"}</h2>  
                    </div>
                    </div>
                    <div className="field">
                        <div className="email">
                        <h2>Emergency</h2>
                        <p>valami@valami.com</p>
                        </div>
                    <div className="edit">
                    <h2 >Edit</h2>  
                    </div>
                    </div>
                </div>
            </div>
            <div className="helpInfo">
                <div className="profileCard">
                    <div className="first">
                        <LockIcon/>
                        <h2>Which details can be edited?</h2>
                        <p>Details Airbnb uses to verify your identity can’t be changed. Contact info and some personal details can be edited, but we may ask you verify your identity the next time you book or create a listing.</p>
                    </div>
                    <div className="second">
                        <RecentActorsIcon/>
                        <h2>What info is shared with others?</h2>
                        <p>Airbnb only releases contact information for hosts and guests after a reservation is confirmed.</p>
                    </div>
                </div>
            </div>
        </div>
        </div>
        
    )
}

export default UserPage
