import React, { useState, useContext } from "react";
import "./NewAccommodation.scss";
import usePlacesAutocomplete, {
  getGeocode,
  getLatLng,
} from "use-places-autocomplete";
import useOnclickOutside from "react-cool-onclickoutside";
import { NewAccommodationContext } from "../context/NewAccommodationContext";
import { useHistory } from "react-router-dom";
import { CountryDropdown } from "react-country-region-selector";

function NewAccommodationLandingPage() {
  const history = useHistory();
  const [accommodation, setAccommodation] = useContext(NewAccommodationContext);

  const [coordinates, setCoordinates] = useState({});
  const [country, setCountry] = useState("");
  const [zipCode, setZipCode] = useState("");
  const [city, setCity] = useState("");
  const [street, setStreet] = useState("");
  const [houseNumber, setHouseNumber] = useState("");

  const {
    ready,
    value,
    suggestions: { status, data },
    setValue,
    clearSuggestions,
  } = usePlacesAutocomplete({
    requestOptions: {
      /* Define search scope here */
    },
    debounce: 300,
  });

  const [aptSuite, setAptSuite] = useState("");

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

  const handleContinue = () => {
    let newAccommodation = accommodation;
    let address = {};

    address["country"] = country;
    address["zipCode"] = zipCode;
    address["city"] = zipCode;
    address["street"] = street;
    address["houseNumber"] = houseNumber;

    //TODO: delete this line after api works
    setCoordinates({ latitude: 1, longitude: 1 });

    newAccommodation["coordinate"] = coordinates;
    newAccommodation["address"] = address;
    setAccommodation(newAccommodation);
    console.log(accommodation);
    //history.push("/become-a-host/room");
  };

  let details = (
    <React.Fragment>
      <div className="label">Country</div>
      <CountryDropdown
        className="dropdown"
        value={country}
        onChange={(v) => setCountry(v)}
      />

      <div className="side-by-side">
        <div className="row">
          <div className="cell-left">
            <div className="label">City</div>
            <input
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
              type="text"
              value={houseNumber}
              onChange={(event) => {
                setHouseNumber(event.target.value);
              }}
            />
          </div>
        </div>
      </div>

      <div className="label">Apt, suite (optional)</div>
      <input
        type="text"
        value={aptSuite}
        onChange={(event) => {
          setAptSuite(event.target.value);
        }}
      />
    </React.Fragment>
  );

  return (
    <React.Fragment>
      <h2>Where’s your place located?</h2>
      <p>
        Guests will only get your exact address once they’ve booked a
        reservation.
      </p>

      <div ref={ref}>
        <input
          value={value}
          onChange={(event) => {
            setValue(event.target.value);
          }}
          disabled={!ready}
          placeholder="Property address"
        />
        {status === "OK" && <ul>{renderSuggestions()}</ul>}
      </div>

      {
        //value !== "" &&
        details
      }

      <button
        type="button"
        className="next-button"
        onClick={handleContinue}
        disabled={value === ""}
      >
        Continue
      </button>
    </React.Fragment>
  );
}

export default NewAccommodationLandingPage;
