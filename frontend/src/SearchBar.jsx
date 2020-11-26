import React, { useState } from "react";
import Search from "./Search";
import "./Header.css";
import SearchIcon from "@material-ui/icons/Search";

function SearchBar() {
    const [showSearch, setShowSearch] = useState(false);
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
                            placeholder="Where are you going?"
                            type="text"
                        ></input>
                    </label>
                </div>
                <div
                    className="datePicker"
                    onClick={() => setShowSearch(!showSearch)}
                    variant="outlined"
                >
                    <label>Date picker</label>
                </div>
                {showSearch && <Search />}
                <div className="guests">
                    <div className="guests__input">
                        <label>Guests</label>
                        <input type="text" placeholder="Add guests"></input>
                    </div>
                    <div className="header__buttonSearch">
                        <SearchIcon className="header__searchIcon" />
                    </div>
                </div>
            </div>
        </div>
    );
}

export default SearchBar;
