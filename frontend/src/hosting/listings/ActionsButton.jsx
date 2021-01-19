import React, { useState, useEffect } from "react";
import Button from "@material-ui/core/Button";
import Menu from "@material-ui/core/Menu";
import MenuItem from "@material-ui/core/MenuItem";
import PopupState, { bindTrigger, bindMenu } from "material-ui-popup-state";
import axios from "axios";

function ActionsButton(props) {
  const handleEdit = (close) => {
    //TODO
    close();
  };

  const handleDeactivate = (close) => {
    axios
      .delete(
        `http://localhost:8762/acc/accommodation-id/${props.accommodationId}`
      )
      .then((response) => {
        props.setRequestDate(new Date());
      });
    close();
  };

  return (
    <div>
      <PopupState variant="popover" popupId="demo-popup-menu">
        {(popupState) => (
          <React.Fragment>
            <Button variant="text" {...bindTrigger(popupState)}>
              <i className="fas fa-ellipsis-h" />
            </Button>
            <Menu {...bindMenu(popupState)}>
              <MenuItem onClick={() => handleEdit(popupState.close)}>
                Edit
              </MenuItem>
              <MenuItem onClick={() => handleDeactivate(popupState.close)}>
                Deactivate
              </MenuItem>
            </Menu>
          </React.Fragment>
        )}
      </PopupState>
    </div>
  );
}

export default ActionsButton;
