import React, { useState, useMemo } from 'react';
import { createRoot } from "react-dom/client";
import { Provider } from 'react-redux'
import store from './store'
import { BrowserRouter, Route, Routes, useNavigate } from "react-router-dom";
import logo from './logo.svg';
import './App.css';
import SideBar from './components/UI/SideBar';
import NavigationBar from './components/UI/NavigationBar';
import MainComponent from './components/UI/MainComponent';
import Login from './publiccomponent/login/Login';
import { boolean } from 'yup';

function App() {

  let isUserLogin = true;
 

  return (
    <div >
    <Provider store={store}>
    <BrowserRouter>
      
          {/* <SideBar show={showSidebar} /> */}
          <Login />

        </BrowserRouter>
    </Provider>
    </div>

     
  );
}

export default App;
