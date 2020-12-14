import React, { useState, createContext } from "react";

export const HeaderContext = createContext();

export const HeaderProvider = (props) => {
    const [show, setShow] = useState(false);
    return (
        <HeaderContext.Provider value={[show, setShow]}>
            {props.children}
        </HeaderContext.Provider>
    );
};