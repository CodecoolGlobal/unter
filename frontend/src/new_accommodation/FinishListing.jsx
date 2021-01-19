import React, { useContext, useEffect, useState } from "react";
import { NewAccommodationContext } from "../context/NewAccommodationContext";
import axios from "axios";
import { RoomsContext } from "../context/RoomsContext";

function FinishListing() {
  // eslint-disable-next-line no-unused-vars
  const [accommodation, setAccommodation] = useContext(NewAccommodationContext);
  const [
    bedrooms,
    // eslint-disable-next-line no-unused-vars
    setBedrooms,
    commonSpaces,
    // eslint-disable-next-line no-unused-vars
    setCommonSpaces,
    nrOfBaths,
    // eslint-disable-next-line no-unused-vars
    setNrOfBaths,
  ] = useContext(RoomsContext);

  const [success, setSuccess] = useState(false);
  const roomTypes = { BEDROOM: "0", BATHROOM: "1", COMMON_SPACES: "2" };

  let content = (
    <React.Fragment>
      <h2 className="subtitle">Saving your listing...</h2>
    </React.Fragment>
  );

  let newAccommodation = { ...accommodation };

  let bedroomsCleaned = { ...bedrooms };
  let bedroomList = [];

  const removeEmpty = (bedroomsCleaned, bedroomList, callback) => {
    for (let room in bedroomsCleaned) {
      for (let bed in bedroomsCleaned[room]) {
        // eslint-disable-next-line eqeqeq
        if (bedroomsCleaned[room][bed] == false) {
          delete bedroomsCleaned[room][bed];
        }
      }
      if (Object.keys(bedroomsCleaned[room]) < 1) {
        delete bedroomsCleaned[room];
      }
    }

    callback(bedroomsCleaned, bedroomList);
  };

  const listRooms = (bedroomsCleaned, bedroomList) => {
    for (let room in bedroomsCleaned) {
      if (Object.keys(bedroomsCleaned[room]).length > 0) {
        bedroomList.push({
          beds: bedroomsCleaned[room],
          type: roomTypes["BEDROOM"],
        });
      }
    }
  };

  removeEmpty(bedroomsCleaned, bedroomList, listRooms);

  for (let bed in commonSpaces) {
    // eslint-disable-next-line eqeqeq
    if (commonSpaces[bed] == false) {
      delete commonSpaces[bed];
    }
  }

  let commonSpacesAsRoom = {
    beds: commonSpaces,
    type: roomTypes["COMMON_SPACES"],
  };

  let bathroomsList = [];
  for (let i = 0; i < nrOfBaths; i++)
    bathroomsList.push({ beds: {}, type: roomTypes["BATHROOM"] });

  newAccommodation["rooms"] = [...bathroomsList];
  if (Object.keys(commonSpacesAsRoom.beds).length > 0) {
    newAccommodation["rooms"].push(commonSpacesAsRoom);
  }
  if (bedroomList[0] !== undefined) {
    newAccommodation["rooms"] = [...newAccommodation["rooms"], ...bedroomList];
  }

  useEffect(() => {
    axios
      .post(`http://localhost:8762/acc`, newAccommodation, {
        withCredentials: true,
      })
      .then((response) => {
        setSuccess(true);
      });
    // eslint-disable-next-line react-hooks/exhaustive-deps
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
