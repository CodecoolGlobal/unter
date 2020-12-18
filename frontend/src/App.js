import "./App.scss";
import React, { useState } from "react";
import Home from "./home_page/Home";
import Header from "./permanent/Header";
import Footer from "./permanent/Footer";
import { Route, BrowserRouter as Router, Switch } from "react-router-dom";
import SearchPage from "./search_page/SearchPage";
import { HeaderProvider } from "./context/HeaderCloseContext";
import AccommodationPage from "./accommodation_page/AccommodationPage";
import {AccommodationNumberProvider} from "./context/AccommodationNumber";

function App() {
    return (
        <div className="App">
            <Router>
                <HeaderProvider>
                    <AccommodationNumberProvider>
                    <Header />
                    <Switch>
                        <Route path="/search">
                            <SearchPage />
                        </Route>
                        <Route path="/accommodation">
                            <AccommodationPage/>
                        </Route>
                        <Route path="/">
                            <Home />
                        </Route>
                    </Switch>
                </AccommodationNumberProvider>
                </HeaderProvider>
                <Footer />
            </Router>
        </div>
    );
}

export default App;
