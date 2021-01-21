import React, { useState } from "react";
import { Link } from "react-router-dom";
import Review from "./Review";

function ReviewSection({ accommodationId }) {
  const dummyReviews = [
    {
      id: 5,
      accommodationId: 1,
      guestId: 5,
      rating: 2.0,
      message:
        "The place was a bit far from the center and the bed was uncomfortable. Budapest is nice but many people dont speak English. We were a bit disappointed.",
      date: "2018-02-02",
    },
    {
      id: 6,
      accommodationId: 1,
      guestId: 4,
      rating: 3.0,
      message:
        "We had some issues with the wifi and the bathroom was dirty and got cleaned after we arrived which was a bit weird. But we had fun and the location of the place is very close to everything.",
      date: "2018-01-30",
    },
  ];
  const [reviews, setReview] = useState(dummyReviews);
  const [rating, setRating] = useState(4);

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
        {reviews.map((review) => (
          <Review review={review} />
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
    </div>
  );
}

export default ReviewSection;
