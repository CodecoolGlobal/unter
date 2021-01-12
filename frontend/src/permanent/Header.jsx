import React, { useContext } from "react";
import "./Header.scss";
import SearchIcon from "@material-ui/icons/Search";
import LanguageIcon from "@material-ui/icons/Language";
import { Avatar, Button } from "@material-ui/core";
import SearchBar from "./SearchBar";
import { HeaderContext } from "../context/HeaderCloseContext";
import Login from "./Login";

import { Link, useHistory } from "react-router-dom";
import Dropdown from "./Dropdown";

function Header() {
  const history = useHistory();
  const [show, setShow] = useContext(HeaderContext);

  return (
    <div className="header">
      <div className="headerLogo">
        <Link to="/">
          <img
            className="header__icon"
            src="https://i.pinimg.com/originals/3c/bf/be/3cbfbe148597341fa56f2f87ade90956.png"
            alt=""
          />
        </Link>
      </div>
      {!show ? (
        <div className="header__center">
          <Button onClick={() => setShow(!show)} disableRipple={true}>
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
        <button
          className="host__button"
          type="button"
          onClick={() => {
            history.push("/become-a-host/location");
          }}
        >
          Become a host
        </button>
        <LanguageIcon />
        <Dropdown />
      </div>
    </div>
  );
}

export default Header;
