import React,{useEffect,useState} from 'react'
import './AccommodationPage.scss'
import { StarBorder } from "@material-ui/icons";
import AccommodationCalendar from './AccommodationCalendar'
import GoogleMapReact from "google-map-react";
import { Button } from '@material-ui/core';
import Axios from 'axios';



function AccomodationPage() {
    const [accommodation,setAccommodation] = useState();
    const [isLoading,setIsLoading] = useState(true);
    const [center,setCenter] = useState([])
    const defaultProps = {
        center: { lat: 59.95, lng: 30.33 },
        zoom: 11,
    };


    useEffect(() => {
        Axios.get(`http://localhost:8762/acc/accommodation-id/${window.location.href.substring(window.location.href.lastIndexOf('/') + 1)}`)
        .then(async function (response) {
            console.log(JSON.stringify(response.data) +"plsplaslpasls")
            setAccommodation(response.data)
            await setCenter([{lat:Number(response.data.coordinate.latitude)},{lng:Number(response.data.coordinate.longitude)}])
            setIsLoading(false)
        })
        .catch(function (error) {
            // handle error
            console.log(error);
        })
        .then(function () {
            // always executed
        });
        
    }, [])
    if(isLoading){return(
        <div>Loading</div>)
    }
    else{
        let newCenter = {lat:center[0]["lat"],lng:center[1]["lng"]}
    return (
        <div className="accommodation__page">
        <div className="accommodation__pageContainer">
            <div className="title">
                    <h2>{accommodation.name}</h2>
            </div>
            <div className="rating">
                    <StarBorder className="searchResult__star" />
                        <p>
                            <strong>{4.7}</strong>
                        </p>
                        <p>·</p>
                        <p>
                            {accommodation.address.city}, {accommodation.address.country}
                        </p>
            </div>
            <div className="gallery">
                <div className="mainPicture">
                    <img src={accommodation.pictures[0]} alt=""/>
                </div>
                <div className="secondaryPicture">
                    <div className="firstBlock">
                    <img src={accommodation.pictures[1]} alt=""/>
                    <img src={accommodation.pictures[2]} alt=""/>
                    </div>
                    <div className="secondBlock">
                    <img src={accommodation.pictures[3]} alt=""/>
                    <img src={accommodation.pictures[4]} alt=""/>
                    </div>
                </div>
            </div>
            <div className="scroll__container">
                <div className="accommodation__side">
                    <div className="host__info">
                        <h3>{accommodation.name}</h3>
                        <div className="apartman__info">
                        <h5>{accommodation.maxNumberOfGuest} Guest</h5>
                        <h5>·</h5>
                        <h5>Apartment</h5>
                        <h5>·</h5>
                        <h5> 3 bed</h5>
                        <h5>·</h5>
                        <h5>1 bathroom</h5>
                        </div>
                    </div>
                    <div className="accommodation__description">
                    <p>
                   {accommodation.description}
                    </p>
                    </div>
                    {/* <div className="sleepingPossibilities">
                        <h3>
                            Sleeping possibilities
                        </h3>
                        <div>

                        </div>
                    </div> */}
                    <div className="calendar__container">
                        <AccommodationCalendar/>
                    </div>
                </div>  
                <div className="payment__side">
                    <div className="paymentCard">
                        <div className="card__title">
                            <h3>Add dates for prices</h3>
                            <StarBorder className="searchResult__star" />
                        </div>
                        <div className="card__input">
                            <div className="chooseDate">
                                <label className="startDate">
                                    Check in
                                <input placeholder="Add date" type="text" className="startDate__input" ></input>
                                </label>
                                <label className="endDate">
                                Check out
                                <input placeholder="Add date" type="text" className="endDate__input"></input>
                                </label>
                            </div>
                            <div className="pickGuests">
                            <label className="guest__label">
                                Guests
                                <input type="text" className="guest__input"></input>
                                </label>
                            </div>
                        </div>
                        <div className="cardFooter">
                        <Button className="rentButton">Rent</Button>
                        <h5>Enter dates and number of guests to see the total price per night.</h5>    
                        </div>
                    </div>
                </div>
            </div>
            <div className="accomodation__map">
            <GoogleMapReact className="map"
                        bootstrapURLKeys={{
                            key: "",
                        }}
                        center={newCenter}
                        defaultZoom={defaultProps.zoom}
            ></GoogleMapReact>
            <div className="location__description">
                <h3>{accommodation.city}</h3>
                <p>{accommodation.description}</p>
            </div>
            </div>
        </div>
        </div>
    )
                    }
}

export default AccomodationPage
