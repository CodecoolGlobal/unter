import React from 'react'
import './AccommodationPage.scss'
import { StarBorder } from "@material-ui/icons";
import AccommodationCalendar from './AccommodationCalendar'
import GoogleMapReact from "google-map-react";
import { Button } from '@material-ui/core';



function AccomodationPage() {
    const defaultProps = {
        center: { lat: 59.95, lng: 30.33 },
        zoom: 11,
    };

    return (
        <div className="accommodation__page">
        <div className="accommodation__pageContainer">
            <div className="title">
                    <h2>Stay at this spacious Edwardian House</h2>
            </div>
            <div className="rating">
                    <StarBorder className="searchResult__star" />
                        <p>
                            <strong>{4.7}</strong>
                        </p>
                        <p>·</p>
                        <p>
                            Berlin, Németország
                        </p>
            </div>
            <div className="gallery">
                <div className="mainPicture">
                    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ_wbPYTxQPMcBh7SPzLFActXnP3uhifeVT_g&usqp=CAU" alt=""/>
                </div>
                <div className="secondaryPicture">
                    <div className="firstBlock">
                    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ_wbPYTxQPMcBh7SPzLFActXnP3uhifeVT_g&usqp=CAU" alt=""/>
                    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ_wbPYTxQPMcBh7SPzLFActXnP3uhifeVT_g&usqp=CAU" alt=""/>
                    </div>
                    <div className="secondBlock">
                    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ_wbPYTxQPMcBh7SPzLFActXnP3uhifeVT_g&usqp=CAU" alt=""/>
                    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ_wbPYTxQPMcBh7SPzLFActXnP3uhifeVT_g&usqp=CAU" alt=""/>
                    </div>
                </div>
            </div>
            <div className="scroll__container">
                <div className="accommodation__side">
                    <div className="host__info">
                        <h3> Host által által kiadott Teljes lakás</h3>
                        <div className="apartman__info">
                        <h5>3 vendég</h5>
                        <h5>·</h5>
                        <h5>Stúdiólakás</h5>
                        <h5>·</h5>
                        <h5> 3 ágy</h5>
                        <h5>·</h5>
                        <h5>1 füdőszoba</h5>
                        </div>
                    </div>
                    <div className="accommodation__description">
                    <p>
                    Das Einzimmer-Studio-Appartement Kalckreuthstraße 7 ist eine kleine, helle Wohnung mit eigenen Bad und Küche im 3.OG mit Aufzug. Vom Appartement aus schaut man auf die kleine ruhige Anliegerstraße mit einigen Antiquitätengeschäften und Galerien.
                    ACHTUNG: Wegen COVID-19 sind touristische Übernachtungen in Berlin bis zum 22. Dezember 2020 verboten!
                    Allerdings sollen touristische Übernachtungen über die…
                    </p>
                    </div>
                    <div className="sleepingPossibilities">
                        <h3>
                            Sleeping possibilities
                        </h3>
                        <div>

                        </div>
                    </div>
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
                        defaultCenter={defaultProps.center}
                        defaultZoom={defaultProps.zoom}
            ></GoogleMapReact>
            <div className="location__description">
                <h3>Berlin, Németország</h3>
                <p>Berlin, Németország
Vom Appartement aus schaut man auf die kleine ruhige Anliegerstraße mit einigen Antiquitätengeschäften. Sie liegt zwischen den U-Bahn-Stationen Wittenberg- und Nollendorfplatz.
Es sind nur 80 Meter bis zu…</p>
            </div>
            </div>
        </div>
        </div>
    )
}

export default AccomodationPage
