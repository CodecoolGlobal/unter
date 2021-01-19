import React, { useContext } from "react";
import Button from "@material-ui/core/Button";
import Menu from "@material-ui/core/Menu";
import MenuItem from "@material-ui/core/MenuItem";
import PopupState, { bindTrigger, bindMenu } from "material-ui-popup-state";
import axios from "axios";
import { NewAccommodationContext } from "../../context/NewAccommodationContext";
import { useHistory } from "react-router-dom";

function ActionsButton(props) {
  const history = useHistory();
  const [accommodation, setAccommodation] = useContext(NewAccommodationContext);

  const handleEdit = (close) => {
    axios
      .get(
        `http://localhost:8762/acc/accommodation-id/${props.accommodationId}`
      )
      .then((response) => {
        let accommodationDTO = response.data;
        delete accommodationDTO["rooms"];
        setAccommodation(accommodationDTO);
        history.push("/become-a-host/location");
      });
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
