import React, { useState, useContext } from "react";
import { useHistory } from "react-router-dom";
import { NewAccommodationContext } from "../context/NewAccommodationContext";

function Description() {
  const history = useHistory();
  const [accommodation, setAccommodation] = useContext(NewAccommodationContext);

  const propertyTypes = [
    "Apartment",
    "Bed and breakfast",
    "House",
    "Hotel",
    "Unique space",
  ];

  const [title, setTitle] = useState("");
  const [desc, setDesc] = useState("");
  const [type, setType] = useState("Apartment");

  const handleNext = () => {
    let newAccommodation = accommodation;
    newAccommodation["name"] = title;
    newAccommodation["description"] = desc;
    newAccommodation["type"] = type.replace(" ", "_").toUpperCase();
    newAccommodation["hostId"] = 1;
    setAccommodation(newAccommodation);
    history.push("/become-a-host/rooms");
  };

  return (
    <React.Fragment>
      <h2>What kind of place are you listing?</h2>

      <div className="label">Choose a property type</div>
      <select
        className="dropdown"
        onChange={(event) => setType(event.target.value)}
      >
        {propertyTypes.map((type, index) => (
          <option key={index}>{type}</option>
        ))}
      </select>

      <div className="label">Create a title for your listing</div>
      <input
        type="text"
        value={title}
        onChange={(event) => {
          setTitle(event.target.value);
        }}
      />

      <div className="label">Describe your place to guests</div>
      <h3>
        Mention the best features of your space, any special amenities like fast
        WiFi or parking, and what you love about the neighborhood.
      </h3>
      <textarea
        value={desc}
        style={{ height: "200px", paddingTop: "10px" }}
        onChange={(event) => {
          setDesc(event.target.value);
        }}
      />

      <div className="side-by-side">
        <div className="row">
          <div className="cell-left" width="100%">
            <button
              type="button"
              className="back-button"
              onClick={() => {
                history.push("/become-a-host/location");
              }}
            >
              <i className="fas fa-angle-left"></i> Back
            </button>
          </div>

          <div className="cell-right" style={{ textAlign: "right" }}>
            <button
              type="button"
              className="next-button"
              onClick={handleNext}
              disabled={title === ""}
            >
              Next
            </button>
          </div>
        </div>
      </div>
    </React.Fragment>
  );
}

export default Description;
