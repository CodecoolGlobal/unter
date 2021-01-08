import React, { useState, useContext } from "react";
import { NewAccommodationContext } from "../context/NewAccommodationContext";

function Room(props) {
  const [accommodation, setAccommodation] = useContext(NewAccommodationContext);
  const [beds, setBeds] = useState({});
  const [numberOfBeds, setNumberOfBeds] = useState(0);
  const [addBedsButton, setAddBedsButton] = useState("Add beds");
  const id = props.index === undefined ? props.id : props.index + 1;
  const roomTypes = { Bedroom: "1", "Common spaces": "3" };
  const bedTypes = ["Single", "Double", "King", "Couch", "Toddler"];

  const saveRoom = () => {
    let newAccommodation = accommodation;
    newAccommodation.rooms[id] = {
      beds: { ...beds },
      type: roomTypes[props.type],
    };

    setAccommodation(newAccommodation);
  };

  const increaseBeds = (bedType) => {
    let added = {};
    added[bedType] = beds[bedType] === undefined ? 1 : beds[bedType] + 1;
    let newBeds = { ...beds, ...added };
    setNumberOfBeds(numberOfBeds + 1);
    setBeds(newBeds);
    saveRoom();
  };

  const decreaseBeds = (bedType) => {
    let newBeds = { ...beds };
    if (beds[bedType] <= 1) {
      delete newBeds[bedType];
    } else {
      let taken = {};
      taken[bedType] = beds[bedType] - 1;
      newBeds = { ...beds, ...taken };
    }
    setNumberOfBeds(numberOfBeds - 1);
    setBeds(newBeds);
    saveRoom();
  };

  let bedList = bedTypes.map((bedType, index) => {
    return (
      <div
        className="row"
        key={index}
        style={{ fontSize: "14px", fontWeight: "bold" }}
      >
        <div className="cell-left">{bedType}</div>

        <div className="cell-right">
          <button
            type="button"
            className="circle-button"
            onClick={() => decreaseBeds(bedType)}
            disabled={beds[bedType] === undefined || beds[bedType] < 1}
          >
            <i className="fas fa-minus" />
          </button>

          {beds[bedType] === undefined ? 0 : beds[bedType]}

          <button
            type="button"
            className="circle-button"
            onClick={() => increaseBeds(bedType)}
            disabled={beds[bedType] > 4}
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
              {props.type} {props.index === undefined ? "" : id}
            </div>
            <h3>{numberOfBeds} beds</h3>
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
