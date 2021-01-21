import React, { useEffect, useState } from "react";
import Avatar from "@material-ui/core/Avatar";
import StarRatings from "react-star-ratings";
import noImage from "./no-image.jpg";
import axios from "axios";
import ActionsButton from "./ActionsButton";
import { Link } from "react-router-dom";

function Review({ review }) {
  const [accommodation, setAccommodation] = useState(null);
  const [requestDate, setRequestDate] = useState(new Date());

  let content = (
    <div style={{ height: "100px", width: "100%", background: "lightgrey" }} />
  );

  useEffect(() => {
    axios
      .get(
        `http://localhost:8762/acc/accommodation-id/${review.accommodationId}`
      )
      .then((response) => {
        setAccommodation(response.data);
      });
  }, [requestDate]);

  if (accommodation !== null) {
    content = (
      <React.Fragment>
        <div className="side-by-side">
          <div className="row">
            <div className="cell-left">
              <Avatar
                alt=""
                src={
                  accommodation.pictures ? accommodation.pictures[0] : noImage
                }
                style={{ width: "100px", height: "100px", marginRight: "10px" }}
              />
            </div>
            <div className="cell-left" style={{ verticalAlign: "top" }}>
              <p style={{ fontSize: "14px", color: "#aaa" }}>
                {accommodation.address.city}, {accommodation.address.country}
              </p>

              <Link
                className="black-pink-link"
                to={`/accommodation/${accommodation.id}`}
              >
                <h4>{accommodation.name}</h4>
              </Link>

              <StarRatings
                rating={review.rating}
                edit="false"
                starDimension="18px"
                starSpacing="1px"
                starRatedColor="#FF385C"
                starEmptyColor="#dce0e0"
                numberOfStars={5}
                name="rating"
              />

              <p style={{ margin: "10px 0" }}>{review.message}</p>

              <p style={{ fontSize: "14px", color: "#aaa" }}>{review.date}</p>
            </div>
            <div
              className="cell-right"
              style={{ textAlign: "right", verticalAlign: "bottom" }}
            >
              <ActionsButton review={review} setRequestDate={setRequestDate} />
            </div>
          </div>
        </div>
      </React.Fragment>
    );
  }

  return (
    <React.Fragment>
      <li>{content}</li>
    </React.Fragment>
  );
}

export default Review;
