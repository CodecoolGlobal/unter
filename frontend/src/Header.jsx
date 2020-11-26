import React, { useState } from "react";
import "./Header.css";
import SearchIcon from "@material-ui/icons/Search";
import LanguageIcon from "@material-ui/icons/Language";
import ExpandMoreIcon from "@material-ui/icons/ExpandMore";
import { Avatar, Button } from "@material-ui/core";
import SearchBar from "./SearchBar";

import { Link } from "react-router-dom";

function Header() {
    const [open, setOpen] = useState(false);

    const handleOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };
    return (
        <div className="header">
            <Link to="/">
                <img
                    className="header__icon"
                    src="https://i.pinimg.com/originals/3c/bf/be/3cbfbe148597341fa56f2f87ade90956.png"
                    alt=""
                />
            </Link>
            {!open ? (
                <div className="header__center">
                    <Button onClick={handleOpen}>
                        <p className="header__search">Start your search</p>
                        {/* <input type="text" placeholder="Start your search" /> */}
                        <div className="search__logo">
                            <SearchIcon className="header__searchIcon" />
                        </div>
                    </Button>
                </div>
            ) : (
                <SearchBar />
            )}

            <div className="header__right">
                <p>Become a host</p>
                <LanguageIcon />
                <ExpandMoreIcon />
                <Avatar />
            </div>
        </div>
    );
}

export default Header;
