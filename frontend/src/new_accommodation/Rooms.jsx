import React, { useContext, useState } from "react";
import { NewAccommodationContext } from "../context/NewAccommodationContext";
import { useHistory } from "react-router-dom";
import Room from "./Room";

function Rooms() {
  const history = useHistory();
  const [accommodation, setAccommodation] = useContext(NewAccommodationContext);

  const [maxNrOfGuests, setMaxNrOfGuests] = useState(1);
  const [bedrooms, setBedrooms] = useState(1);
  const [beds, setBeds] = useState(1);
  const [bathrooms, setBathrooms] = useState(1);

  let accommodationWithRoom = accommodation;
  accommodationWithRoom["rooms"] = {};
  setAccommodation(accommodationWithRoom);

  let bedroomList = [];
  if (bedrooms > 0) {
    for (let i = 0; i < bedrooms; i++) {
      bedroomList.push(<Room key={i} index={i} type="Bedroom" />);
    }
  }

  const handleNext = () => {
    let newAccommodation = accommodation;
    newAccommodation["maxNrOfGuests"] = maxNrOfGuests;
    setAccommodation(newAccommodation);
    history.push("/become-a-host/save");
  };

  return (
    <React.Fragment>
      <h2>How many guests can your place accommodate?</h2>
      <p>
        Check that you have enough beds to accommodate all your guests
        comfortably.
      </p>

      <div className="side-by-side">
        <div className="row">
          <div className="cell-left">
            <div className="label">Guests</div>
          </div>
          <div className="cell-right">
            <button
              type="button"
              className="circle-button"
              onClick={() => setMaxNrOfGuests(maxNrOfGuests - 1)}
              disabled={maxNrOfGuests < 2}
            >
              <i className="fas fa-minus" />
            </button>
            {maxNrOfGuests}
            {maxNrOfGuests > 15 && <span>+</span>}
            <button
              type="button"
              className="circle-button"
              onClick={() => setMaxNrOfGuests(maxNrOfGuests + 1)}
              disabled={maxNrOfGuests > 15}
            >
              <i className="fas fa-plus" />
            </button>
          </div>
        </div>

        <p style={{ marginTop: "30px" }}>How many bedrooms can guests use?</p>
        <div className="row">
          <div className="cell-left">
            <div className="label">Bedrooms</div>
          </div>
          <div className="cell-right">
            <button
              type="button"
              className="circle-button"
              onClick={() => setBedrooms(bedrooms - 1)}
              disabled={bedrooms < 1}
            >
              <i className="fas fa-minus" />
            </button>
            {bedrooms}
            {bedrooms > 49 && <span>+</span>}
            <button
              type="button"
              className="circle-button"
              onClick={() => setBedrooms(bedrooms + 1)}
              disabled={bedrooms > 49}
            >
              <i className="fas fa-plus" />
            </button>
          </div>
        </div>
      </div>

      {bedroomList}

      <div className="side-by-side">
        <p>How many bathrooms can guests use?</p>
        <div className="row">
          <div className="cell-left">
            <div className="label">Bathrooms</div>
          </div>
          <div className="cell-right">
            <button
              type="button"
              className="circle-button"
              onClick={() => setBathrooms(bathrooms - 1)}
              disabled={bathrooms < 1}
            >
              <i className="fas fa-minus" />
            </button>
            {bathrooms}
            {bathrooms > 49 && <span>+</span>}
            <button
              type="button"
              className="circle-button"
              onClick={() => setBathrooms(bathrooms + 1)}
              disabled={bathrooms > 49}
            >
              <i className="fas fa-plus" />
            </button>
          </div>
        </div>
      </div>
      <div className="side-by-side">
        <div className="row">
          <div className="cell-left" width="100%">
            <button
              type="button"
              className="back-button"
              onClick={() => {
                history.push("/become-a-host/description");
              }}
            >
              <i className="fas fa-angle-left"></i> Back
            </button>
          </div>

          <div className="cell-right" style={{ textAlign: "right" }}>
            <button type="button" className="next-button" onClick={handleNext}>
              Save
            </button>
          </div>
        </div>
      </div>
    </React.Fragment>
  );
}

export default Rooms;
