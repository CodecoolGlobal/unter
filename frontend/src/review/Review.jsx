import React, { useEffect, useState } from "react";
import Avatar from "@material-ui/core/Avatar";
import StarRatings from "react-star-ratings";
import noImage from "./no-image.jpg";
import { makeStyles } from "@material-ui/core/styles";
import axios from "axios";

const useStyles = makeStyles((theme) => ({
  root: {
    display: "flex",
    "& > *": {
      margin: theme.spacing(1),
    },
  },
  large: {
    width: theme.spacing(7),
    height: theme.spacing(7),
  },
}));

function Review({ review }) {
  const classes = useStyles();
  const [accommodation, setAccommodation] = useState(null);

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
  }, [setAccommodation]);

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

              <h4>{accommodation.name}</h4>

              <StarRatings
                rating={review.rating}
                edit="false"
                starDimension="18px"
                starSpacing="2px"
                starRatedColor="#FF385C"
                starEmptyColor="#dce0e0"
                numberOfStars={5}
                name="rating"
              />

              <p style={{ margin: "10px 0" }}>{review.message}</p>

              <p style={{ fontSize: "14px", color: "#aaa" }}>{review.date}</p>
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
