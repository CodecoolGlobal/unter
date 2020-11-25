import React,{useState} from "react";
import "./Header.css";
import SearchIcon from "@material-ui/icons/Search";
import LanguageIcon from "@material-ui/icons/Language";
import ExpandMoreIcon from "@material-ui/icons/ExpandMore";
import { Avatar, Button } from "@material-ui/core";
import Modal from '@material-ui/core/Modal';
import Search from './Search'


import {Link} from "react-router-dom"

function Header() {
    const [open, setOpen] = React.useState(false);
    const [showSearch,setShowSearch] = useState(false);
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
            {!open?
            <div className="header__center">
                   <Button onClick={handleOpen}>
                    <p className="header__search">Start your search</p>
                {/* <input type="text" placeholder="Start your search" /> */}
                <div className="search__logo">
                <SearchIcon className="header__searchIcon" />
                </div>
                </Button> 
                </div>: 
        <div className="new__headerCenter">    
            <div className="header__title">
                <p>Places to stay</p>
            </div>
            <div className="header__buttons">
                <div className="header__button">    
                <p>Location</p>
                <input placeholder="Where are you going?" type="text"></input>
                </div>
                <div className="header__button"onClick={() => setShowSearch(!showSearch)} variant='outlined' >
                <p>Date picker</p>
                </div>
                {showSearch && <Search/>} 
                <div className="header__button">    
                    <p>Guests</p>
                    <input type="text" placeholder="Add guests"></input>
                    <div className="header__buttonSearch">
                    <SearchIcon className="header__searchIcon" />
                    </div>
                </div>
                
                </div>
        </div>  
} 
             
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
