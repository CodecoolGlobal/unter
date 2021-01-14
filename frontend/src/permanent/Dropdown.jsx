import React from "react";
import Menu from "@material-ui/core/Menu";
import MenuItem from "@material-ui/core/MenuItem";
import "./Dropdown.scss";
import Login from "./Login";
import ExpandMoreIcon from "@material-ui/icons/ExpandMore";
import { Avatar } from "@material-ui/core";
import Registration from "./Registration";

export default function Dropdown() {
  const [anchorEl, setAnchorEl] = React.useState(null);
  let content;

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const logOut = () => {
    localStorage.clear();
    setAnchorEl(null);
  };

  if (localStorage.getItem("id") !== null) {
    content = (
      <div className="dropdown">
        <button type="button" className="avatar__button" onClick={handleClick}>
          <ExpandMoreIcon />
          <Avatar />
        </button>
        <Menu
          id="simple-menu"
          anchorEl={anchorEl}
          style={{ top: "5vh", width: "10vw", borderRadius: "16px" }}
          keepMounted
          open={Boolean(anchorEl)}
          onClose={handleClose}
        >
          <MenuItem onClick={handleClose}>My account</MenuItem>
          <MenuItem onClick={logOut}>Log out</MenuItem>
        </Menu>
      </div>
    );
  } else {
    content = (
      <div className="dropdown">
        <button type="button" className="avatar__button" onClick={handleClick}>
          <ExpandMoreIcon />
          <Avatar />
        </button>
        <Menu
          id="simple-menu"
          anchorEl={anchorEl}
          style={{ top: "5vh", width: "10vw", borderRadius: "16px" }}
          keepMounted
          open={Boolean(anchorEl)}
          onClose={handleClose}
        >
          <MenuItem onClick={handleClose}>
            <Login />
          </MenuItem>
          <MenuItem onClick={handleClose}>My account</MenuItem>
          <MenuItem onClick={handleClose}>
            <Registration />
          </MenuItem>
        </Menu>
      </div>
    );
  }

  return content;
}
