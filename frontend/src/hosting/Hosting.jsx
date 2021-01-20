import React from "react";
import { Route, Switch } from "react-router-dom";
import Listings from "./listings/Listings";

function Hosting() {
  return (
    <React.Fragment>
      <div style={{ padding: "20px" }}>
        <Switch>
          <Route path="/hosting/listings">
            <Listings />
          </Route>
        </Switch>
      </div>
    </React.Fragment>
  );
}

export default Hosting;
