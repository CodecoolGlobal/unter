import React, { useState ,useContext    } from "react";
import Search from "./Search";
import "./Header.scss";
import SearchIcon from "@material-ui/icons/Search";
import axios from "axios";
import { Link, useHistory } from "react-router-dom";
import { HeaderContext } from "../context/HeaderCloseContext";
import queryString from "query-string";


function SearchBar() {
    const [city, setCity] = useState("");
    const [showSearch, setShowSearch] = useState(false);
    const history = useHistory();
    const [show, setShow] = useContext(HeaderContext);
    // const [searchURL,setSearchURL] = useState();


    const getCoordinates = async () => {
        const response = await axios.get(
            `http://open.mapquestapi.com/geocoding/v1/address?key=AGLLGoolFA9orBIVXAHGMfGAtq0pvT6e&location=${city}`
        );
        if(response.data.results[0].locations[0].latLng.lat === queryString.parse(window.location.search.lat)&&
        response.data.results[0].locations[0].latLng.lng === queryString.parse(window.location.search.lng)){
        setShow(false);
        }
        else{

            let latitude = response.data.results[0].locations[0].latLng.lat;
            let longitude = response.data.results[0].locations[0].latLng.lng;
            setShow(false);
            // setSearchURL=`/search?city=${city}&lat=${latitude}&lng=${longitude}`
            history.push(`/search?city=${city}&lat=${latitude}&lng=${longitude}`);
        }
    }
        const onEnter = async (e) => {
            if(e.key ==='Enter'){

                const response = await axios.get(
                    `http://open.mapquestapi.com/geocoding/v1/address?key=AGLLGoolFA9orBIVXAHGMfGAtq0pvT6e&location=${city}`
                );
                if(response.data.results[0].locations[0].latLng.lat === queryString.parse(window.location.search.lat)&&
                response.data.results[0].locations[0].latLng.lng === queryString.parse(window.location.search.lng)){
                setShow(false);
                }
                else{
        
                    let latitude = response.data.results[0].locations[0].latLng.lat;
                    let longitude = response.data.results[0].locations[0].latLng.lng;
                    setShow(false);
                    // setSearchURL=`/search?city=${city}&lat=${latitude}&lng=${longitude}`
                    history.push(`/search?city=${city}&lat=${latitude}&lng=${longitude}`);
                }
            }
    };

    return (
        <div className="new__headerCenter">
            <div className="header__title">
                <p>Places to stay</p>
            </div>
            <div className="header__buttons">
                <div className="location">
                    <label className="location__input">
                        Location
                        <input
                            onChange={(e) => setCity(e.target.value)}
                            placeholder="Where are you going?"
                            onKeyDown={onEnter}
                            type="text"
                        ></input>
                    </label>
                </div>
                <div
                    className="datePicker"
                    onClick={() => setShowSearch(!showSearch)}
                    variant="outlined"
                >
                    <label className="date__label">Date picker</label>
                </div>
                {showSearch && <Search />}
                <div className="guests">
                    <div className="guests__input">
                        <label>Guests</label>
                        <input type="text"  placeholder="Add guests"></input>
                    </div>
                    <div className="header__buttonSearch">
                        <SearchIcon
                            onClick={getCoordinates}
                            className="header__searchIcon"
                            />
                    </div>
                </div>
            </div>
        </div>
    );
}

export default SearchBar;
