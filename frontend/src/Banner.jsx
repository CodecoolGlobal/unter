import React from "react";
import "./Banner.css";
import { Button } from "@material-ui/core";

function Banner() {
    return (
        <div className="banner">
            <div className="banner__info">
                <h1>Go Near</h1>
                <h5>
                    Settle in somewhere new. Discover stays to live, work, or
                    just relax.
                </h5>
                <Button variant="outlined">Explore Nearby</Button>
            </div>
        </div>
    );
}

export default Banner;
