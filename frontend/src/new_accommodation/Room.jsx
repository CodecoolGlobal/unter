import React, { useState, useContext } from "react";
import toSentenceCaseWithDash from "./toSentenceCaseWithDash";
import { RoomsContext } from "../context/RoomsContext";

function Room(props) {
  const [bedrooms, setBedrooms, commonSpaces, setCommonSpaces] = useContext(
    RoomsContext
  );
  const [addBedsButton, setAddBedsButton] = useState("Add beds");
  const id = props.index === undefined ? props.id : props.index;
  const isBedroom = props.type === "Bedroom";
  const bedTypes = ["SINGLE", "DOUBLE", "KING", "COUCH", "TODDLER"];

  let defaultBeds = {
    SINGLE: 0,
    DOUBLE: 0,
    KING: 0,
    COUCH: 0,
    TODDLER: 0,
  };

  let beds = defaultBeds;
  if (isBedroom) {
    beds =
      bedrooms !== undefined
        ? bedrooms[id] !== undefined
          ? bedrooms[id]
          : defaultBeds
        : defaultBeds;
  } else {
    beds = commonSpaces !== undefined ? commonSpaces : defaultBeds;
  }

  const [numberOfBeds, setNumberOfBeds] = useState(
    Object.keys(beds).reduce((acc, value) => acc + beds[value], 0)
  );

  const saveBeds = (beds) => {
    if (isBedroom) {
      let newBedrooms = { ...bedrooms };
      newBedrooms[id] = beds;
      setBedrooms(newBedrooms);
    } else {
      setCommonSpaces(beds);
    }
  };

  const increaseBeds = (beds, bedType) => {
    beds = beds === undefined ? defaultBeds : beds;
    beds[bedType] = !(bedType in beds) ? 1 : beds[bedType] + 1;
    saveBeds(beds);
    setNumberOfBeds(numberOfBeds + 1);
  };

  const decreaseBeds = (beds, bedType) => {
    beds = beds === undefined ? defaultBeds : beds;
    if (beds[bedType] > 0) {
      beds[bedType] = beds[bedType] - 1;
    }
    saveBeds(beds);
    setNumberOfBeds(numberOfBeds - 1);
  };

  let bedList = bedTypes.map((bedType, index) => {
    return (
      <div
        className="row"
        key={index}
        style={{ fontSize: "14px", fontWeight: "bold" }}
      >
        <div className="cell-left">{toSentenceCaseWithDash(bedType)}</div>

        <div className="cell-right">
          <button
            type="button"
            className="circle-button"
            onClick={() => decreaseBeds(beds, bedType)}
            disabled={beds[bedType] ? beds[bedType] < 1 : true}
          >
            <i className="fas fa-minus" />
          </button>

          {beds[bedType] ? beds[bedType] : 0}

          <button
            type="button"
            className="circle-button"
            onClick={() => increaseBeds(beds, bedType)}
            disabled={beds === undefined ? true : beds[bedType] > 4}
          >
            <i className="fas fa-plus" />
          </button>
        </div>
      </div>
    );
  });

  const handleClick = (event) => {
    if (addBedsButton === "Add beds") {
      setAddBedsButton("Done");
    } else {
      setAddBedsButton("Add beds");
    }
  };

  return (
    <React.Fragment>
      <div
        className="side-by-side"
        style={{
          borderBottom: "1px solid lightgray",
          padding: "10px 0",
        }}
      >
        <div className="row">
          <div className="cell-left" style={{ verticalAlign: "middle" }}>
            <div
              style={{
                fontSize: "16px",
                marginBottom: "5px",
              }}
            >
              {props.type} {props.index === undefined ? "" : id + 1}
            </div>
            <h3 className="field-description">{numberOfBeds} beds</h3>
          </div>

          <div className="cell-right" style={{ textAlign: "right" }}>
            <button
              type="button"
              className="add-button"
              onClick={(event) => handleClick(event)}
            >
              {addBedsButton}
            </button>
          </div>
        </div>

        {addBedsButton === "Done" && bedList}
      </div>
    </React.Fragment>
  );
}

export default Room;
