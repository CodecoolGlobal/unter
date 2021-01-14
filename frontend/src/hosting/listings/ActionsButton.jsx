import React from "react";
import Button from "@material-ui/core/Button";
import Menu from "@material-ui/core/Menu";
import MenuItem from "@material-ui/core/MenuItem";
import PopupState, { bindTrigger, bindMenu } from "material-ui-popup-state";

function ActionsButton() {
  const handleEdit = (close) => {
    //TODO
    close();
  };

  const handleDeactivate = (close) => {
    //TODO
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
