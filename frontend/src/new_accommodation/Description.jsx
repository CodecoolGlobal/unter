import React, { useState, useContext } from "react";
import { useHistory } from "react-router-dom";
import { NewAccommodationContext } from "../context/NewAccommodationContext";
import toSentenceCase from "./toSentenceCase";

function Description() {
  const history = useHistory();
  const [accommodation, setAccommodation] = useContext(NewAccommodationContext);
  let isEditing = accommodation !== undefined && "id" in accommodation;

  const propertyTypes = [
    "Apartment",
    "Bed and breakfast",
    "House",
    "Hotel",
    "Unique space",
  ];

  const [title, setTitle] = useState(isEditing ? accommodation.name : "");
  const [desc, setDesc] = useState(isEditing ? accommodation.description : "");
  const [type, setType] = useState(
    isEditing
      ? accommodation.address.type !== undefined
        ? toSentenceCase(accommodation.address.type)
        : "Apartment"
      : "Apartment"
  );

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
      <h2 className="subtitle">What kind of place are you listing?</h2>

      <div className="label">Choose a property type</div>
      <select
        className="dropdown-form"
        onChange={(event) => setType(event.target.value)}
      >
        {propertyTypes.map((type, index) => (
          <option key={index}>{type}</option>
        ))}
      </select>

      <div className="label">Create a title for your listing</div>
      <input
        className="text-input"
        type="text"
        value={title}
        onChange={(event) => {
          setTitle(event.target.value);
        }}
      />

      <div className="label">Describe your place to guests</div>
      <h3 className="field-description">
        Mention the best features of your space, any special amenities like fast
        WiFi or parking, and what you love about the neighborhood.
      </h3>
      <textarea
        className="text-input"
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
