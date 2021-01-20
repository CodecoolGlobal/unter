import React, { useEffect, useState } from 'react'
import  './UserPage.scss'
import LockIcon from '@material-ui/icons/Lock';
import RecentActorsIcon from '@material-ui/icons/RecentActors';
import { Button } from '@material-ui/core';
import Axios from 'axios';





function UserPage() {
    const [editEffect,setEditEffect] = useState(false);
    const [firstName,setFirstName] = useState();
    const [lastName,setLastName] = useState();
    const [nameEffect,setNameEffect] = useState(false);
    const [genderEffect,setGenderEffect] = useState(false);
    const [birthDateEffect,setBirthDateEffect] = useState(false);
    const [emailEffect,setEmailEffect] = useState(false);
    const [phoneEffect,setPhoneEffect] = useState(false);
    const [addressEffect,setAddressEffect] = useState(false);
    const [gender,setGender]= useState();
    const [birthDate,setBirthDate]= useState();
    const [email,setEmail]= useState();
    const [phone,setPhone]= useState();
    const [zipCode,setZipCode]= useState();
    const [houseNumber,setHouseNumber]= useState();
    const [city,setCity]= useState();
    const [street,setStreet]= useState();
    const [user,setUser] = useState(JSON.parse(localStorage.getItem('user')) );
   



    const [address,setAdress]= useState();

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
    const saveChanges = (changeValue)=>{
      switch (changeValue) {
        case "name":
            Axios.post(`http://localhost:8762/user/save-profile-data/${user.Id}`,{'fullName':firstName+lastName})
            setNameEffect(!nameEffect);
            break;
        case "email":
            Axios.post(`http://localhost:8762/user/save-profile-data/${user.Id}`,{'email':email})
            setEmailEffect(!emailEffect);
            break;
        case"birthDate":
            Axios.post(`http://localhost:8762/user/save-profile-data/${user.Id}`,{'birthDate':birthDate})
            setBirthDateEffect(!birthDateEffect);
            break;
        case "phone":
            Axios.post(`http://localhost:8762/user/save-profile-data/${user.Id}`,{'phoneNumber':phone})
            setPhoneEffect(!phoneEffect);
            break;
        case "gender":
            Axios.post(`http://localhost:8762/user/save-profile-data/${user.Id}`,{'gender':gender})
            setGenderEffect(!genderEffect);
            break;
        case "address":
            Axios.post(`http://localhost:8762/user/save-profile-data/${user.Id}`,{'address':address})
            setAddressEffect(!addressEffect);
            break;
          default:
              break;
      }
            
    }

    useEffect(() => {
        console.log(user)
        Axios.get(`http://localhost:8762/user/get-user-data/${user.Id}`) 
        .then(async function (response){
            console.log(response)
            await setFirstName(response.data.firstName)
            await setBirthDate(response.data.birthDate)
            await setEmail(response.data.email)
            await setLastName(response.data.lastName)
        })  
    }, [])
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
                                    <input type="text" placeholder={firstName} onChange={(e)=>{
                                        setFirstName(e.target.value)
                                    }}>
                                    </input>
                                </label>
                                <label className="lastName">
                                    Last Name
                                    <input type="text" placeholder={lastName} onChange={(e)=>{
                                        setLastName(e.target.value)
                                    }}>
                                        
                                    </input>
                                </label>
                            </div>
                            <Button  onClick={(e) => {saveChanges("name")}}>Save</Button>
                        </div>
                        ):(<p>{firstName} {lastName}</p>) }
                        </div>
                    <div className={nameEffect? "editNone" : "edit"}>
                    <h2 id="name" onClick={(e) => editProfile(e)}> {nameEffect? "Cancel":"Edit"}</h2>  
                    </div>
                    </div>
                    <div className="field">
                        <div className="gender">
                        <h2>Gender</h2>
                        {genderEffect?(
                        <div className="editGender">
                            <div className="genderInput">
                            <label className="genderLabel">
                                    <input type="text" onChange={(e)=>{
                                        setGender(e.target.value)
                                    }}>
                                    </input>
                                </label>
                            </div>
                            <Button id="gender" onClick={(e)=>saveChanges("gender")}>Save</Button>
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
                        <div className="editBirthDate">
                            <div className="birthDateInput">
                            <label className="firstName">
                                    <input type="date" placeholder={birthDate} onChange={(e)=>{
                                        setBirthDate(e.target.value)
                                    }}>
                                        
                                    </input>
                                </label>
                            </div>
                            <Button id= "birthDate" onClick={(e)=>saveChanges("birthDate")}>Save</Button>
                        </div>
                        ):(<p>{birthDate}</p>) }
                        </div>
                    <div className="edit">
                    <h2  id="birthDate" onClick={(e) => editProfile(e)}> {birthDateEffect? "Cancel":"Edit"}</h2>  
                    </div>
                    </div>
                    <div className="field">
                        <div className="email">
                        <h2>Email adress</h2>
                        {emailEffect?(
                        <div className="editEmail">
                            <p>
                            Use an address you’ll always have access to.

                            </p>
                            <div className="emailInput">
                            <label className="emailLabel">
                                    Email
                                    <input type="email" placeholder={email} onChange={(e)=>{
                                        setEmail(e.target.value)
                                    }}>
                                        
                                    </input>
                                </label>
                            </div>
                            <Button id="email" onClick={(e)=>saveChanges("email")}>Save</Button>
                        </div>
                        ):(<p>{email}</p>) }                        </div>
                    <div className="edit">
                    <h2 id="email" onClick={(e) => editProfile(e)}>{emailEffect? "Cancel":"Edit"}</h2>  
                    </div>
                    </div>
                    <div className="field">
                        <div className="phone">
                        <h2>Phone number</h2>
                        {phoneEffect?(
                        <div className="editPhone">
                           
                            <div className="phoneInput">
                            <label className="phoneLabel">
                                    <input type="text" onChange={(e)=>{
                                        setPhone(e.target.value)
                                    }} >
                                    </input>
                                </label>
                            </div>
                            <Button id="phone" onClick={(e)=>saveChanges("phone")}>Save</Button>
                        </div>
                        ):(<p>+36151234567</p>) }
                        </div>
                    <div className="edit">
                    <h2 id="phone" onClick={(e) => editProfile(e)}>{phoneEffect? "Cancel":"Edit"}</h2>  
                    </div>
                    </div>
                    <div className="field">
                        <div className="address">
                        <h2>Address</h2>
                        {addressEffect?(
                        <div className="editAddress">
                            <form className="addressBody">
                            <div className="addressInputFirst">
                            <label className="addressLabel"> 
                                    <input placeholder="ZIP Code" type="text"
                                    onChange={(e)=>{
                                        setZipCode(e.target.value)
                                    }}>
                                    </input>
                                </label>
                                <label className="addressLabel"> 
                                    <input placeholder="City" type="text" onChange={(e)=>{
                                        setCity(e.target.value)
                                    }}>
                                    </input>
                                </label>
                            </div>
                            <div className="addressInputSecond">
                            <label className="addressLabel"> 
                                    <input placeholder="Street" type="text" onChange={(e)=>{
                                        setStreet(e.target.value)
                                    }}>
                                    </input>
                                </label>
                                <label className="addressLabel"> 
                                    <input placeholder="House number" type="text" onChange={(e)=>{
                                        setHouseNumber(e.target.value)
                                    }}>
                                    </input>
                                </label>
                            </div>

                            <Button id="address" onClick={(e)=>saveChanges("adress")}>Save</Button>
                            </form>
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
