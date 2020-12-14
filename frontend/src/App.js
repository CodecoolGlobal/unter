import "./App.scss";
import React, { useState } from "react";
import Home from "./home_page/Home";
import Header from "./permanent/Header";
import Footer from "./permanent/Footer";
import { Route, BrowserRouter as Router, Switch } from "react-router-dom";
import SearchPage from "./search_page/SearchPage";
import { HeaderProvider } from "./context/HeaderCloseContext";

function App() {
    return (
        <div className="App">
            <Router>
                <HeaderProvider>
                    <Header />
                    <Switch>
                        <Route path="/search">
                            <SearchPage />
                        </Route>
                        <Route path="/">
                            <Home />
                        </Route>
                    </Switch>
                </HeaderProvider>
                <Footer />
            </Router>
        </div>
    );
}

export default App;
