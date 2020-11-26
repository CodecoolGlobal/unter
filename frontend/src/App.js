import "./App.css";
import React , {useState} from 'react'
import Home from "./Home";
import Header from "./Header";
import Footer from "./Footer"
import { Route,BrowserRouter as Router,Switch } from "react-router-dom";
import SearchPage from "./SearchPage"
import {HeaderProvider} from './context/HeaderCloseContext';

function App() {

    return (
        <div className="App">
            <Router>
            <HeaderProvider>
            <Header />
            <Switch>
            <Route path="/search">
                    <SearchPage/>
                </Route>
                <Route path="/">
                    <Home />
                </Route>
               
            </Switch>
            
            </HeaderProvider>
            <Footer/>
            </Router>
        </div>
    );
}

export default App;
