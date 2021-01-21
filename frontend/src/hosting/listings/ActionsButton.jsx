import React, { useState } from "react";
import Button from "@material-ui/core/Button";
import Menu from "@material-ui/core/Menu";
import MenuItem from "@material-ui/core/MenuItem";
import PopupState, { bindTrigger, bindMenu } from "material-ui-popup-state";
import axios from "axios";
import { makeStyles } from "@material-ui/core/styles";
import Modal from "@material-ui/core/Modal";
import Backdrop from "@material-ui/core/Backdrop";
import Fade from "@material-ui/core/Fade";

function ActionsButton(props) {
  //const history = useHistory();
  //const [accommodation, setAccommodation] = useContext(NewAccommodationContext);

  const useStyles = makeStyles((theme) => ({
    modal: {
      display: "flex",
      alignItems: "center",
      justifyContent: "center",
    },
    paper: {
      backgroundColor: "white",
      borderRadius: "12px",
      boxShadow: theme.shadows[5],
      padding: theme.spacing(2, 4, 3),
      "&.Mui-focused": {
        border: "2px solid red",
        "& .MuiOutlinedInput-notchedOutline": {
          border: "none",
        },
      },
    },
  }));
  const classes = useStyles();
  const [open, setOpen] = React.useState(false);
  const [title, setTitle] = useState("");
  const [desc, setDesc] = useState("");
  const [maxNrOfGuests, setMaxNrOfGuests] = useState(1);

  const handleClose = () => {
    setOpen(false);
  };

  const handleEdit = (close) => {
    close();
    setOpen(true);
    axios
      .get(
        `http://localhost:8762/acc/accommodation-id/${props.accommodationId}`
      )
      .then((response) => {
        // EDIT EVERYTHING EXCEPT: id
        // let accommodationDTO = response.data;
        // delete accommodationDTO["rooms"];
        // setAccommodation(accommodationDTO);
        // history.push("/become-a-host/location");

        //EDIT ONLY: title, description, maxNrOfGuests
        setTitle(response.data.name);
        setDesc(response.data.description);
        setMaxNrOfGuests(response.data.maxNumberOfGuest);
      });
  };

  const handleChange = () => {
    let changes = {
      name: title,
      description: desc,
      maxNumberOfGuest: maxNrOfGuests,
    };
    axios
      .put(
        `http://localhost:8762/acc/accommodation-id/${props.accommodationId}`,
        changes,
        {
          withCredentials: true,
        }
      )
      .then((response) => {
        props.setRequestDate(new Date());
        setOpen(false);
      });
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
      <Modal
        aria-labelledby="transition-modal-title"
        aria-describedby="transition-modal-description"
        className={classes.modal}
        open={open}
        onClose={handleClose}
        closeAfterTransition
        BackdropComponent={Backdrop}
        BackdropProps={{
          timeout: 500,
        }}
      >
        <Fade in={open}>
          <div className={classes.paper}>
            <div className="label">Change the title of your listing</div>
            <input
              className="text-input"
              type="text"
              value={title}
              autoFocus
              onChange={(event) => {
                setTitle(event.target.value);
              }}
            />

            <div className="label">Describe your place to guests</div>
            <h3 className="field-description">
              Mention the best features of your space, any special amenities
              like fast WiFi or parking, and what you love about the
              neighborhood.
            </h3>
            <textarea
              className="text-input"
              value={desc}
              style={{ height: "200px", paddingTop: "10px" }}
              onChange={(event) => {
                setDesc(event.target.value);
              }}
            />

            <div className="side-by-side">
              <div className="row">
                <div className="cell-left">
                  <div className="label">Set maximum number of your guests</div>
                </div>
                <div className="cell-right">
                  <button
                    type="button"
                    className="circle-button"
                    onClick={() => setMaxNrOfGuests(maxNrOfGuests - 1)}
                    disabled={maxNrOfGuests < 2}
                  >
                    <i className="fas fa-minus" />
                  </button>
                  {maxNrOfGuests}
                  {maxNrOfGuests > 49 && <span>+</span>}
                  <button
                    type="button"
                    className="circle-button"
                    onClick={() => setMaxNrOfGuests(maxNrOfGuests + 1)}
                    disabled={maxNrOfGuests > 49}
                  >
                    <i className="fas fa-plus" />
                  </button>
                </div>
              </div>

              <div className="row">
                <div className="cell-left">
                  <button
                    type="button"
                    onClick={handleClose}
                    className="back-button"
                  >
                    Cancel
                  </button>
                </div>
                <div className="cell-right" style={{ textAlign: "right" }}>
                  <button
                    type="button"
                    onClick={handleChange}
                    className="next-button"
                  >
                    Change
                  </button>
                </div>
              </div>
            </div>
          </div>
        </Fade>
      </Modal>
    </div>
  );
}

export default ActionsButton;
