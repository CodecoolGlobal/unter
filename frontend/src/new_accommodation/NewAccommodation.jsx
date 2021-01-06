import React from "react";
import { Route, Switch } from "react-router-dom";
import Location from "./Location";
import Room from "./Room";
import Description from "./Description";

function NewAccommodation() {
  return (
    <div className="container">
      <div className="content">
        <Switch>
          <Route path="/become-a-host/description">
            <Description />
          </Route>
          <Route path="/become-a-host/location">
            <Location />
          </Route>
          <Route path="/become-a-host/room">
            <Room />
          </Route>
        </Switch>
      </div>
    </div>
  );
}

export default NewAccommodation;
