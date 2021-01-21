import React, { useState, useEffect } from "react";
import Avatar from "@material-ui/core/Avatar";
import Button from "@material-ui/core/Button";
import { Link } from "react-router-dom";
import axios from "axios";
import Review from "./Review";
/*
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
*/

function Reviews() {
  const userId = localStorage.getItem("id");
  const [reviews, setReviews] = useState([]);

  /* //TODO: write reviews from this page, I have started it:
  const dummyHosts = [
    {
      hostId: 1,
      accommodationId: 1,
      name: "Julie Walker",
      avatar:
        "https://cdn4.iconfinder.com/data/icons/avatars-xmas-giveaway/128/girl_avatar_child_kid-512.png",
    },
    {
      hostId: 2,
      accommodationId: 2,
      name: "Remy Sharp",
      avatar:
        "https://cdn4.iconfinder.com/data/icons/avatars-xmas-giveaway/128/man_male_avatar_portrait-512.png",
    },
  ];
  const [hosts, setHosts] = useState(dummyHosts);
  const noHostsToReview =
    "Nobody to review right now. Looks like it’s time for another trip!";
  const classes = useStyles();

  let hostItems = hosts.map((host) => (
    <div className="row">
      <div>
        <Avatar
          alt={host.name}
          src={host.avatar ? host.avatar : "/static/images/avatar/1.jpg"}
          className={classes.large}
        />
      </div>
      <div>
        <Link to={`/review/${host.accommodationId}`}>
          Write a review for {host.name}
        </Link>
      </div>
    </div>
  ));

  let hostList = <div className="side-by-side">{hostItems}</div>;
*/

  useEffect(() => {
    axios
      .get(`http://localhost:8762/review/guest-id/${userId}`)
      .then((response) => {
        setReviews(response.data);
      });
  }, [setReviews]);

  let noReviewsWritten = (
    <p style={{ margin: "10px 0" }}>You have not written any reviews yet.</p>
  );

  let reviewItems = [];

  if (reviews.length > 0) {
    reviewItems = reviews.map((review) => <Review review={review} />);

    /*let accommodation = {
        name: "Nice house",
        address: { city: "Budapest", country: "Hungary" },
        pictures:
          "https://www.mellowmoodhotels.com/wp-content/uploads/2018/10/promenade-city-hotel-double-room-6-1920x1080.jpg",
      };
      displayReview(review, accommodation);*/
  }

  let reviewList = <ul className="review-list">{reviewItems}</ul>;

  return (
    <div className="container">
      <div className="content">
        <h2>Reviews by you</h2>

        {/*
        <div className="panel">
          <div className="panel-header">Reviews to write</div>
          <div className="panel-body">
            {hosts.length === 0 ? noHostsToReview : hostList}
          </div>
        </div> */}

        <div className="panel">
          <div className="panel-header">Past reviews you’ve written</div>
          <div className="panel-body">
            {reviewItems.length === 0 ? noReviewsWritten : reviewList}
          </div>
        </div>
      </div>
    </div>
  );
}

export default Reviews;
