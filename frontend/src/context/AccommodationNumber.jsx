import React, { useState, createContext } from "react";

export const AccommodationNumberContext = createContext();

export const AccommodationNumberProvider = (props) => {
    const [accommodations, setAccommodations] = useState([]);
    return (
        <AccommodationNumberContext.Provider value={[accommodations, setAccommodations]}>
            {props.children}
        </AccommodationNumberContext.Provider>
    );
};