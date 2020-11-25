import React from "react";
import "./Banner.css";
import { Button } from "@material-ui/core";
import Search from './Search'
import { useHistory } from "react-router-dom";

function Banner() {
    const history = useHistory();
    return (
        <div className="banner">
            <div className="banner__info">
                <h1>Go Near</h1>
                <h5>
                    Settle in somewhere new. Discover stays to live, work, or
                    just relax.
                </h5>
                <Button onClick={()=> history.push('/search')} variant="outlined">Explore Nearby</Button>
            </div>
        </div>
    );
}

export default Banner;
