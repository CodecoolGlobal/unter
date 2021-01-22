import React, { useEffect, useState } from "react";
import Avatar from "@material-ui/core/Avatar";
import StarRatings from "react-star-ratings";
import axios from "axios";

function Review({ review }) {
  const [guest, setGuest] = useState({ firstName: "Lajos" });

  let content = (
    <div style={{ height: "100px", width: "100%", background: "lightgrey" }} />
  );
  /*
  useEffect(() => {
    axios
      .get(`http://localhost:8762/user/get-user-data/${review.guestId}`)
      .then((response) => {
        let newGuest = { ...response.data, avatar: "" };
        setGuest(newGuest);
      });
  }, [setGuest]);*/

  if (guest !== null) {
    content = (
      <React.Fragment>
        <div className="side-by-side">
          <div className="row">
            <div className="cell-left">
              <Avatar
                alt=""
                src={guest.avatar ? guest.avatar : ""}
                style={{ width: "50px", height: "50px", marginRight: "10px" }}
              />
            </div>
            <div
              className="cell-right"
              style={{ verticalAlign: "top", width: "100%" }}
            >
              <p style={{ fontSize: "14px", color: "#aaa" }}>{review.date}</p>

              <h4>{guest.firstName}</h4>

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
            </div>
          </div>
        </div>
        <p style={{ margin: "10px 0" }}>{review.message}</p>
      </React.Fragment>
    );
  }

  return (
    <React.Fragment>
      <div className="guest-review">{content}</div>
    </React.Fragment>
  );
}

export default Review;
