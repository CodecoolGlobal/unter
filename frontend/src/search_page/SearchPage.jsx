import React, { useEffect, useState, useContext } from "react";
import "./SearchPage.scss";
import { Button } from "@material-ui/core";
import SearchResult from "./SearchResult";
import GoogleMapReact from "google-map-react";
import MediaQuery from "react-responsive";
import queryString from "query-string";
import { useHistory } from "react-router-dom";
import Axios from "axios";
import { HeaderContext } from "../context/HeaderCloseContext";



function SearchPage() {
    const [city, setCity] = useState();
    const history = useHistory();
    const [isLoading, setIsLoading] = useState(true);
    const [show, setShow] = useContext(HeaderContext);
    const [accommodations, setAccommodations] = useState();
    const [center,setCenter]=useState([]);
    const zoom = 13;
    
    const handleAccommodationPage = (id) =>{
        history.push(`/accomodation/${id}`)
    }



    useEffect(() => {
        setIsLoading(true);
        let parsed = queryString.parse(window.location.search);
        Axios.get(`http://localhost:8762/acc/search?latitude=${parsed.lat}&longitude=${parsed.lng}&radius=1`)
        .then(function (response) {
            // handle success
                setAccommodations(response.data.accommodationDTO);
            })
            .catch(function (error) {
                // handle error
                console.log(error);
            })
            .then(function () {
                // always executed
            });
            console.log(center)
            setCity(parsed.city);

    }, [window.location.href]);

    useEffect(() => {
        let parsed = queryString.parse(window.location.search);
        console.log(parsed)

        setCenter([{lat:Number(parsed.lat)},{lng:Number(parsed.lng)}])
        setIsLoading(false);

    }, [window.location.href])
    
    
    if(isLoading){
        return<div>Loading</div>
    }
    else{
        let newCenter = {lat:center[0]["lat"],lng:center[1]["lng"]}
        console.log(JSON.stringify(newCenter)+"CENTER")
        return (
            <div className={show ? "blurry" : ""} onClick={() => show ? setShow(!show) : setShow(show)}>
                <div className="searchPage">
                    <div className="searchResults">
                        <div className="searchPage__info">
                            <p>62 stays 26 august to 30 august 2 guest</p>
                            <h1>Stays in {city}</h1>
                            <Button variant="outlined">Cancellation Flexibility</Button>
                            <Button variant="outlined">Type of place</Button>
                            <Button variant="outlined">Price</Button>
                            <Button variant="outlined">Rooms and beds</Button>
                            <Button variant="outlined">More filters</Button>
                        </div>
    
                        {accommodations != null
                            ? accommodations.map((actual) => {
                                return (<SearchResult
                                    key={actual.id}
                                    img={actual.pictures}
                                    location="Private room in center of London"
                                    title={actual.accommodationName}
                                    description={actual.description}
                                    star={4.73}
                                    price="£30 / night"
                                    total="£117 total"
                                    id={actual.id}
                                />)
                            })
                            : <div>Loading...</div>
                        }
    
    
                    </div>
                    <MediaQuery minDeviceWidth={1224}>
                        <div className="map">
                            <GoogleMapReact
                                bootstrapURLKeys={{
                                    key: "",
                                }}
                                center={newCenter}
                                defaultZoom={zoom}
                            ></GoogleMapReact>
                        </div>
                    </MediaQuery>
                </div>
            </div>
        );
            }
}

export default SearchPage;
