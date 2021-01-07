import React, { useContext, useEffect } from "react";
import { NewAccommodationContext } from "../context/NewAccommodationContext";
import axios from "axios";

function FinishListing() {
  const [accommodation, setAccommodation] = useContext(NewAccommodationContext);
  const city =
    accommodation.address === undefined ||
    accommodation.address.city === undefined
      ? ""
      : accommodation.address.city;

  let content = (
    <React.Fragment>
      <h2>Saving your listing...</h2>
    </React.Fragment>
  );

  useEffect(() => {
    console.log("it");
    if (city !== "") {
      axios
        .get(
          `http://open.mapquestapi.com/geocoding/v1/address?key=AGLLGoolFA9orBIVXAHGMfGAtq0pvT6e&location=${city}`
        )
        .then((response) => {
          let latitude = response.data.results[0].locations[0].latLng.lat;
          let longitude = response.data.results[0].locations[0].latLng.lng;
          let newAccommodation = accommodation;
          newAccommodation["coordinate"] = {
            longitude: longitude,
            latitude: latitude,
          };
          setAccommodation(newAccommodation);
          console.log(accommodation);
        });
    }
  }, [city]);

  if (accommodation.coordinate !== undefined) {
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
