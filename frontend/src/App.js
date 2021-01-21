import "./App.scss";
import React, { useState } from "react";
import Home from "./home_page/Home";
import Header from "./permanent/Header";
import Footer from "./permanent/Footer";
import { Route, BrowserRouter as Router, Switch } from "react-router-dom";
import SearchPage from "./search_page/SearchPage";
import { HeaderProvider } from "./context/HeaderCloseContext";
import AccommodationPage from "./accommodation_page/AccommodationPage";
import { AccommodationNumberProvider } from "./context/AccommodationNumber";
import UserPage from "./user_page/UserPage";
import Location from "./new_accommodation/Location";
import { NewAccommodationProvider } from "./context/NewAccommodationContext";
import NewAccommodation from "./new_accommodation/NewAccommodation";
import NewReview from "./review/NewReview";
import Reviews from "./review/Reviews";
import Hosting from "./hosting/Hosting";
import Rooms from "./new_accommodation/Rooms";
import { RoomsProvider } from "./context/RoomsContext";


function App() {
  return (
    <div className="App">
      <Router>
        <HeaderProvider>
          <AccommodationNumberProvider>
            <NewAccommodationProvider>
              <RoomsProvider>
                <Header />
                <Switch>
                  <Route path="/search">
                    <SearchPage />
                  </Route>
                  <Route path="/profile">
                    <UserPage />
                  </Route>
                  <Route path="/accommodation">
                    <AccommodationPage />
                  </Route>
                  <Route path="/become-a-host">
                    <NewAccommodation />
                  </Route>
                  <Route path="/hosting">
                    <Hosting />
                  </Route>
                  <Route path="/reviews">
                    <Reviews />
                  </Route>
                  <Route path="/write-review">
                    <NewReview />
                  </Route>
                  <Route path="/">
                    <Home />
                  </Route>
                </Switch>
              </RoomsProvider>
            </NewAccommodationProvider>
          </AccommodationNumberProvider>
        </HeaderProvider>
        <Footer />
      </Router>
    </div>
  );
}

export default App;
