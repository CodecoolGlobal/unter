import React from "react";
import "./Banner.scss";
import { Button } from "@material-ui/core";
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
                <Button
                    onClick={() => history.push("/search?city=Budapest&lat=47.498382&lng=19.040471")}
                    variant="outlined"
                >
                    Explore Nearby
                </Button>
            </div>
        </div>
    );
}

export default Banner;
