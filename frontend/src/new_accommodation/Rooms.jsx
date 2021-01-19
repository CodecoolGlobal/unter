import React, { useContext, useState } from "react";
import { NewAccommodationContext } from "../context/NewAccommodationContext";
import { useHistory } from "react-router-dom";
import Room from "./Room";
import { RoomsContext } from "../context/RoomsContext";

function Rooms() {
  const history = useHistory();
  const [accommodation, setAccommodation] = useContext(NewAccommodationContext);
  const [
    bedrooms,
    setBedrooms,
    // eslint-disable-next-line no-unused-vars
    commonSpaces,
    // eslint-disable-next-line no-unused-vars
    setCommonSpaces,
    nrOfBaths,
    setNrOfBaths,
  ] = useContext(RoomsContext);
  const [maxNrOfGuests, setMaxNrOfGuests] = useState(1);
  const [nrOfBedrooms, setNrOfBedrooms] = useState(1);

  let bedroomList = [];
  if (nrOfBedrooms > 0) {
    for (let i = 0; i < nrOfBedrooms; i++) {
      bedroomList.push(<Room key={i} index={i} type="Bedroom" />);
    }
  }

  const handleNext = () => {
    accommodation["maxNumberOfGuest"] = maxNrOfGuests.toString();
    setAccommodation(accommodation);
    history.push("/become-a-host/save");
  };

  return (
    <React.Fragment>
      <h2 className="subtitle">How many guests can your place accommodate?</h2>
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
              onClick={() => {
                setNrOfBedrooms(nrOfBedrooms - 1);
                delete bedrooms[nrOfBedrooms - 1];
                setBedrooms(bedrooms);
              }}
              disabled={nrOfBedrooms < 1}
            >
              <i className="fas fa-minus" />
            </button>
            {nrOfBedrooms}
            {nrOfBedrooms > 49 && <span>+</span>}
            <button
              type="button"
              className="circle-button"
              onClick={() => setNrOfBedrooms(nrOfBedrooms + 1)}
              disabled={nrOfBedrooms > 49}
            >
              <i className="fas fa-plus" />
            </button>
          </div>
        </div>
      </div>

      {bedroomList}
      <Room id="1000" type="Common spaces" />

      <div className="side-by-side" style={{ marginTop: "30px" }}>
        <p>How many bathrooms can guests use?</p>
        <div className="row">
          <div className="cell-left">
            <div className="label">Bathrooms</div>
          </div>
          <div className="cell-right">
            <button
              type="button"
              className="circle-button"
              onClick={() => setNrOfBaths(nrOfBaths - 1)}
              disabled={nrOfBaths < 1}
            >
              <i className="fas fa-minus" />
            </button>
            {nrOfBaths}
            {nrOfBaths > 49 && <span>+</span>}
            <button
              type="button"
              className="circle-button"
              onClick={() => setNrOfBaths(nrOfBaths + 1)}
              disabled={nrOfBaths > 49}
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
