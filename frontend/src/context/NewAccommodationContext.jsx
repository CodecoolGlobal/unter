import React, { useState, createContext } from "react";

export const NewAccommodationContext = createContext();

export const NewAccommodationProvider = (props) => {
  const [accommodation, setAccommodation] = useState({ rooms: {} });

  return (
    <NewAccommodationContext.Provider value={[accommodation, setAccommodation]}>
      {props.children}
    </NewAccommodationContext.Provider>
  );
};
