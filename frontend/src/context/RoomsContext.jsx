import React, { useState, createContext } from "react";

export const RoomsContext = createContext();

export const RoomsProvider = (props) => {
  const [bedrooms, setBedrooms] = useState({});
  const [commonSpaces, setCommonSpaces] = useState({});
  const [nrOfBaths, setNrOfBaths] = useState(1);
  return (
    <RoomsContext.Provider
      value={[
        bedrooms,
        setBedrooms,
        commonSpaces,
        setCommonSpaces,
        nrOfBaths,
        setNrOfBaths,
      ]}
    >
      {props.children}
    </RoomsContext.Provider>
  );
};
