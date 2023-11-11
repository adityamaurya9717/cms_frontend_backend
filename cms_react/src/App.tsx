import React, { useState, useMemo } from 'react';
import { createRoot } from "react-dom/client";
import { Provider } from 'react-redux'
import store from './store'
import { BrowserRouter } from "react-router-dom";
import logo from './logo.svg';
import './App.css';
import SideBar from './components/UI/SideBar';
import NavigationBar from './components/UI/NavigationBar';
import MainComponent from './components/UI/MainComponent';

function App() {


  return (
    // Redux Provider
    <Provider store={store}>
      <BrowserRouter>
        <div >
          {/* <SideBar show={showSidebar} /> */}
          <MainComponent />
        </div>
      </BrowserRouter>
    </Provider>

  );
}

export default App;
