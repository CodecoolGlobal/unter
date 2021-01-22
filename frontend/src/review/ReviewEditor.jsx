import React, { useState } from "react";
import { Modal, Fade, makeStyles, Backdrop } from "@material-ui/core";
import StarRatings from "react-star-ratings";
import "./Review.scss";
import axios from "axios";
import { useHistory } from "react-router-dom";

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

function ReviewEditor({ open, setOpen, accommodationId, redirect, review }) {
  const history = useHistory();
  const userId = localStorage.getItem("id");
  if (!userId) history.push("/");
  const classes = useStyles();
  const [message, setMessage] = useState(
    review !== undefined ? review.message : ""
  );
  const [rating, setRating] = useState(
    review !== undefined ? review.rating : 5
  );
  const meaningOfRating = {
    1: "Terrible",
    2: "Bad",
    3: "Okay",
    4: "Good",
    5: "Great",
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleSaving = () => {
    let newReview = {
      accommodationId: accommodationId,
      guestId: userId,
      rating: rating,
      message: message,
    };

    if (review !== undefined) {
      axios
        .put(`http://localhost:8762/review/review-id/${review.id}`, newReview, {
          withCredentials: true,
        })
        .then(() => {
          handleClose();
          window.location.reload();
        });
    } else {
      axios
        .post(`http://localhost:8762/review`, newReview, {
          withCredentials: true,
        })
        .then(() => {
          handleClose();
          history.push(redirect);
        });
    }
  };

  return (
    <React.Fragment>
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
            <h2 className="subtitle">How was your stay at this place?</h2>
            <div className="label">{meaningOfRating[rating]}</div>

            <StarRatings
              rating={rating}
              starDimension="38px"
              starRatedColor="#edb54a"
              starHoverColor="#edb54a"
              starEmptyColor="#dce0e0"
              changeRating={(newRating) => {
                setRating(newRating);
              }}
              numberOfStars={5}
              name="rating"
            />

            <div className="label">Write a review</div>
            <p>Tell future travelers about what they can expect.</p>
            <textarea
              className="text-input"
              autoFocus
              value={message}
              style={{ height: "200px", paddingTop: "10px" }}
              onChange={(event) => {
                setMessage(event.target.value);
              }}
            />

            <div style={{ textAlign: "center" }}>
              <button
                type="button"
                onClick={handleSaving}
                className="next-button"
                disabled={message === ""}
              >
                Save
              </button>
            </div>
          </div>
        </Fade>
      </Modal>
    </React.Fragment>
  );
}

export default ReviewEditor;