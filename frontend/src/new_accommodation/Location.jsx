import React, { useState, useContext, useEffect } from "react";
import "./NewAccommodation.scss";
/*AFTER PLACES API WORKS: 
import usePlacesAutocomplete, {
  getGeocode,
  getLatLng,
} from "use-places-autocomplete";
import useOnclickOutside from "react-cool-onclickoutside";
*/
import { NewAccommodationContext } from "../context/NewAccommodationContext";
import { useHistory } from "react-router-dom";
import { CountryDropdown } from "react-country-region-selector";
import axios from "axios";

function NewAccommodationLandingPage() {
  const history = useHistory();
  const [accommodation, setAccommodation] = useContext(NewAccommodationContext);

  const checkSavedLocationData = (data) => {
    return (
      accommodation !== undefined &&
      accommodation.address !== undefined &&
      data in accommodation.address
    );
  };

  // AFTER PLACES API WORKS: const [coordinates, setCoordinates] = useState({});
  const [country, setCountry] = useState(
    checkSavedLocationData("country") ? accommodation.address.country : ""
  );
  const [zipCode, setZipCode] = useState(
    checkSavedLocationData("zipCode") ? accommodation.address.zipCode : ""
  );
  const [city, setCity] = useState(
    checkSavedLocationData("city") ? accommodation.address.city : ""
  );
  const [street, setStreet] = useState(
    checkSavedLocationData("street") ? accommodation.address.street : ""
  );
  const [houseNumber, setHouseNumber] = useState(
    checkSavedLocationData("houseNumber")
      ? accommodation.address.houseNumber
      : ""
  );

  useEffect(() => {
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
        });
    }
  }, [city]);

  /* AFTER PLACES API WORKS: 
  const {
    ready,
    value,
    suggestions: { status, data },
    setValue,
    clearSuggestions,
  } = usePlacesAutocomplete();

  const ref = useOnclickOutside(() => {
    clearSuggestions();
  });

  const handleSelect = ({ description }) => () => {
    setValue(description, false);
    clearSuggestions();

    getGeocode({ address: description })
      .then((results) => getLatLng(results[0]))
      .then(({ lat, lng }) => {
        setCoordinates({ latitude: lat, longitude: lng });
      })
      .catch((error) => {
        console.log("Error: ", error);
      });
  };

  const addressWithCoordinates = (
    <div ref={ref}>
      <input
      className="text-input"
        value={value}
        onChange={(event) => {
          setValue(event.target.value);
        }}
        disabled={!ready}
        placeholder="Property address"
      />
      {status === "OK" && <ul>{renderSuggestions()}</ul>}
    </div>
  );

  const renderSuggestions = () =>
    data.map((suggestion) => {
      const {
        place_id,
        structured_formatting: { main_text, secondary_text },
      } = suggestion;
      return (
        <li key={place_id} onClick={handleSelect(suggestion)}>
          <strong>{main_text}</strong> <small>{secondary_text}</small>
        </li>
      );
    });

    */

  const handleContinue = () => {
    let newAccommodation = accommodation;
    let address = {};

    address["country"] = country;
    address["city"] = city;
    address["zipCode"] = zipCode;
    address["street"] = street;
    address["houseNumber"] = houseNumber;
    //AFTER PLACES API WORKS: newAccommodation["coordinate"] = coordinates;
    newAccommodation["address"] = address;
    setAccommodation(newAccommodation);
    history.push("/become-a-host/description");
  };

  let details = (
    <React.Fragment>
      <div className="label">Country</div>
      <CountryDropdown
        className="dropdown-form"
        value={country}
        onChange={(v) => setCountry(v)}
      />

      <div className="side-by-side">
        <div className="row">
          <div className="cell-left">
            <div className="label">City</div>
            <input
              className="text-input"
              type="text"
              value={city}
              onChange={(event) => {
                setCity(event.target.value);
              }}
            />
          </div>
          <div className="cell-right">
            <div className="label">ZIP Code</div>
            <input
              className="text-input"
              type="text"
              value={zipCode}
              onChange={(event) => {
                setZipCode(event.target.value);
              }}
            />
          </div>
        </div>

        <div className="row">
          <div className="cell-left">
            <div className="label">Street</div>
            <input
              className="text-input"
              type="text"
              value={street}
              onChange={(event) => {
                setStreet(event.target.value);
              }}
            />
          </div>
          <div className="cell-right">
            <div className="label">House number</div>
            <input
              className="text-input"
              type="text"
              value={houseNumber}
              onChange={(event) => {
                setHouseNumber(event.target.value);
              }}
            />
          </div>
        </div>
      </div>
    </React.Fragment>
  );

  return (
    <React.Fragment>
      <h2 className="subtitle">Where’s your place located?</h2>
      <p>
        Guests will only get your exact address once they’ve booked a
        reservation.
      </p>

      {
        //AFTER PLACES API WORKS:
        //addressWithCoordinates
      }

      {
        //AFTER PLACES API WORKS:
        //value !== "" &&
        details
      }

      <button
        type="button"
        className="next-button"
        onClick={handleContinue}
        disabled={
          //AFTER PLACES API WORKS:
          //value === "" &&
          country === "" ||
          city === "" ||
          zipCode === "" ||
          street === "" ||
          houseNumber === ""
        }
      >
        Continue
      </button>
    </React.Fragment>
  );
}

export default NewAccommodationLandingPage;
