import "./App.scss";
import React from "react";
import Home from "./home_page/Home";
import Header from "./permanent/Header";
import Footer from "./permanent/Footer";
import { Route, BrowserRouter as Router, Switch } from "react-router-dom";
import SearchPage from "./search_page/SearchPage";
import { HeaderProvider } from "./context/HeaderCloseContext";
import AccommodationPage from "./accommodation_page/AccommodationPage";
import { AccommodationNumberProvider } from "./context/AccommodationNumber";
import Location from "./new_accommodation/Location";
import { NewAccommodationProvider } from "./context/NewAccommodationContext";
import NewAccommodation from "./new_accommodation/NewAccommodation";
import NewReview from "./review/NewReview";

function App() {
  return (
    <div className="App">
      <Router>
        <HeaderProvider>
          <AccommodationNumberProvider>
            <NewAccommodationProvider>
              <Header />
              <Switch>
                <Route path="/search">
                  <SearchPage />
                </Route>
                <Route path="/accommodation">
                  <AccommodationPage />
                </Route>
                <Route path="/become-a-host">
                  <NewAccommodation />
                </Route>
                <Route path="/write-review">
                  <NewReview />
                </Route>
                <Route path="/">
                  <Home />
                </Route>
              </Switch>
            </NewAccommodationProvider>
          </AccommodationNumberProvider>
        </HeaderProvider>
        <Footer />
      </Router>
    </div>
  );
}

export default App;
