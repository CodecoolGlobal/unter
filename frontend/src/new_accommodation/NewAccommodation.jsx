import React from "react";
import { Route, Switch } from "react-router-dom";
import Location from "./Location";
import Rooms from "./Rooms";
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
          <Route path="/become-a-host/rooms">
            <Rooms />
          </Route>
        </Switch>
      </div>
    </div>
  );
}

export default NewAccommodation;
