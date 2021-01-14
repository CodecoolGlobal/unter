import React, { useContext, useEffect, useState } from "react";
import { NewAccommodationContext } from "../context/NewAccommodationContext";
import axios from "axios";
import { RoomsContext } from "../context/RoomsContext";

function FinishListing() {
  const [accommodation, setAccommodation] = useContext(NewAccommodationContext);
  const [bedrooms, setBedrooms, commonSpaces, setCommonSpaces] = useContext(
    RoomsContext
  );
  const [success, setSuccess] = useState(false);
  const roomTypes = { Bedroom: "1", "Common spaces": "3" };

  let content = (
    <React.Fragment>
      <h2 className="subtitle">Saving your listing...</h2>
    </React.Fragment>
  );

  useEffect(() => {
    axios
      .post(`http://localhost:8762/acc`, accommodation, {
        withCredentials: true,
      })
      .then((response) => {
        setSuccess(true);
        console.log(accommodation);
      });
  }, [setSuccess]);

  if (success) {
    content = (
      <React.Fragment>
        <h2 className="subtitle">Your accommodation is saved!</h2>
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
