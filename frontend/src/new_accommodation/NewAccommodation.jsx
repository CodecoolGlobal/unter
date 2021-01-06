import React from "react";
import { Route, Switch } from "react-router-dom";
import Location from "./Location";

function NewAccommodation() {
  return (
    <div className="container">
      <div className="content">
        <Switch>
          <Route path="/become-a-host/location">
            <Location />
          </Route>
        </Switch>
      </div>
    </div>
  );
}

export default NewAccommodation;
