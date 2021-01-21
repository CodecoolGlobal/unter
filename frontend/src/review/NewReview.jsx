import React, { useState } from "react";
import StarRatings from "react-star-ratings";
import "./Review.scss";
import axios from "axios";
import { useHistory } from "react-router-dom";

function NewReview() {
  const history = useHistory();
  const userId = localStorage.getItem("id");
  if (userId === undefined) history.push("/");
  const [message, setMessage] = useState("");
  const [rating, setRating] = useState(5);
  const [hoverRating, setHoverRating] = useState(5);
  const meaningOfRating = {
    1: "Terrible",
    2: "Bad",
    3: "Okay",
    4: "Good",
    5: "Great",
  };

  //TODO : accId, history
  const saveReview = () => {
    axios
      .post(
        `http://localhost:8762/review`,
        {
          accommodationId: 20,
          guestId: userId,
          rating: rating,
          message: message,
        },
        {
          withCredentials: true,
        }
      )
      .then(() => history.push("/reviews"));
  };

  return (
    <div className="container">
      <div className="content">
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
          value={message}
          style={{ height: "200px", paddingTop: "10px" }}
          onChange={(event) => {
            setMessage(event.target.value);
          }}
        />

        <div style={{ textAlign: "center" }}>
          <button
            type="button"
            onClick={saveReview}
            className="next-button"
            disabled={message === ""}
          >
            Save
          </button>
        </div>
      </div>
    </div>
  );
}

export default NewReview;
