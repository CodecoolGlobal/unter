import React from "react";
import Button from "@material-ui/core/Button";
import Menu from "@material-ui/core/Menu";
import MenuItem from "@material-ui/core/MenuItem";
import PopupState, { bindTrigger, bindMenu } from "material-ui-popup-state";
import axios from "axios";
import ReviewEditor from "./ReviewEditor";

function ActionsButton(props) {
  const [open, setOpen] = React.useState(false);

  const handleEdit = (close) => {
    close();
    setOpen(true);
  };

  const handleDelete = (close) => {
    axios
      .delete(`http://localhost:8762/review/review-id/${props.review.id}`)
      .then((response) => {
        window.location.reload();
      });
    close();
  };

  return (
    <div>
      <PopupState variant="popover" popupId="demo-popup-menu">
        {(popupState) => (
          <React.Fragment>
            <Button
              variant="text"
              {...bindTrigger(popupState)}
              style={{ minWidth: "5px" }}
            >
              <i className="fas fa-ellipsis-v" style={{ color: "grey" }} />
            </Button>
            <Menu {...bindMenu(popupState)}>
              <MenuItem onClick={() => handleEdit(popupState.close)}>
                Edit
              </MenuItem>
              <MenuItem onClick={() => handleDelete(popupState.close)}>
                Delete
              </MenuItem>
            </Menu>
          </React.Fragment>
        )}
      </PopupState>

      {open && (
        <ReviewEditor
          open={open}
          setOpen={setOpen}
          accommodationId={props.review.accommodationId}
          review={props.review}
        />
      )}
    </div>
  );
}

export default ActionsButton;
