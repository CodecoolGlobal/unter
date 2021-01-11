import React, { useContext, useEffect, useState } from "react";
import { NewAccommodationContext } from "../context/NewAccommodationContext";
import axios from "axios";

function FinishListing() {
  const [accommodation, setAccommodation] = useContext(NewAccommodationContext);
  const [success, setSuccess] = useState(false);

  let content = (
    <React.Fragment>
      <h2>Saving your listing...</h2>
    </React.Fragment>
  );

  useEffect(() => {
    axios
        .post(`http://localhost:8762/acc`, accommodation, { withCredentials: true })
        .then((response) => {
      setSuccess(true);
      console.log(accommodation);
    });
  }, [setSuccess]);

  if (success) {
    content = (
      <React.Fragment>
        <h2>Your accommodation is saved!</h2>
        <p>
          Create memorable experiences for travelers and earn money to pursue
          the things you love.
        </p>
      </React.Fragment>
    );
  }
  return <div>{content}</div>;
}

export default FinishListing;
