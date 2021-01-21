import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import Review from "./Review";
import axios from "axios";
import { Button } from "@material-ui/core";
import ReviewEditor from "../review/ReviewEditor";

function ReviewSection({ accommodationId }) {
  const [open, setOpen] = React.useState(false);
  const [reviews, setReviews] = useState([]);
  const [rating, setRating] = useState(5);

  useEffect(() => {
    axios
      .get(`http://localhost:8762/review/accommodation-id/${accommodationId}`)
      .then((response) => {
        setReviews(response.data);
      });
  }, [setReviews]);

  useEffect(() => {
    axios
      .get(`http://localhost:8762/review/rating-avg/${accommodationId}`)
      .then((response) => {
        setRating(response.data);
      });
  }, [setRating]);

  let titleHasReviews = `${rating} (${reviews.length} ${
    reviews.length === 1 ? "review" : "reviews"
  })`;

  let titleNoReviews = "No reviews (yet)";

  let content = (
    <div style={{ maxWidth: "320px" }}>
      We’re here to help your trip go smoothly. Every reservation is covered by{" "}
      <Link
        className="black-pink-link"
        to={{
          pathname:
            "https://www.airbnb.com/help/article/544/what-if-i-need-to-cancel-because-of-a-problem-with-the-listing-or-host",
        }}
        target="blank"
      >
        Airbnb’s Guest Refund Policy
      </Link>
      {"."}
    </div>
  );

  if (reviews.length > 0) {
    content = (
      <div className="review-box">
        {reviews.map((review, index) => (
          <Review key={index} review={review} />
        ))}
      </div>
    );
  }

  return (
    <div className="acc-section">
      <h3 className="section-title">
        <i style={{ color: "#ff385c" }} className="fas fa-star" />{" "}
        {reviews.length === 0 ? titleNoReviews : titleHasReviews}
      </h3>

      {content}

      <Button variant="outlined" color="default" onClick={() => setOpen(true)}>
        WRITE A REVIEW
      </Button>

      <ReviewEditor
        open={open}
        setOpen={setOpen}
        accommodationId={accommodationId}
        redirect="/reviews"
      />
    </div>
  );
}

export default ReviewSection;
