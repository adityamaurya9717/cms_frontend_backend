
import React, { Component, lazy,useCallback,useMemo } from 'react';
import { BrowserRouter,useNavigate, Link, Route, Routes, useLocation, NavLink } from 'react-router-dom'
import Box from '@mui/material/Box';
import { Button, Divider, List, ListItem, ListItemText } from '@mui/material';
import './sidebar.css'

import Product from '../../dashboards/components/products/AddProduct';
import AddUser from '../../dashboards/components/AddUser';
const UserDashboard = lazy(()=>import("../../dashboards/components/UserDashBoard"))

const ListStyle = {
  width: '100%',
  backgroundColor: 'lightgray',
  paddingBottom: '40px',
};
const ListItemStyle = {
  paddingBottom: '10px',
  textAlign: 'center',
  boxShadow: '2px 2px 2px',

};
const menus: Array<any> = [
  { name: 'Users', to: '/userdasboard' },
  { name: 'Product', to: '/product' },
  { name: 'Category', to: '/category' },
  { name: 'Brand', to: '/brand' },

];


const SideBar = (props: any) => {
  //const menus: Array<Object> = ['Add User', 'Add Product', 'Add Category'];

  const show: boolean = props.showSidebar as boolean;
  const navigate = useNavigate()
  const {pathname} = useLocation();


  const click = () => {
    console.log("click")
  }
  return (
      <div className="sidebar">
        <ul>
          {show == true ?
            menus.map((item, index) => {
              //return <li key={index} ><Link to={item.to}>{item.name}</Link> </li>
              //return <li key={index} onClick={()=> navigate(item.to)} >{item.name} </li>
              return <NavLink key={index}  className="li"  style={({ isActive }) => 
              (isActive ? {color: 'green'} : {color: 'black'})} to={item.to}>
                {item.name}

                </NavLink>
              //return <li key={index} onClick={()=> navigate(item.to)} >{item.name} </li>
            }) : null
          }
        </ul>



        {/* <List sx={ListStyle} component="nav" aria-label="mailbox folders">

          {menus.map((item, index) => {
            return (
              <React.Fragment key={index}>

                <ListItem key={index} button>
                  <ListItemText sx={ListItemStyle} primary={item} />
                </ListItem>

              </React.Fragment>
            )
          })
          }

        </List> */}

      </div>

      
  
      // <div className="content">

      //   <Routes>
      //     <Route path="userdasboard"  element={
      //       <React.Suspense fallback={<>...</>}>
      //       <UserDashboard />
      //     </React.Suspense>
      //     }>
      //       <Route path="add-user" element={<AddUser />}></Route>
      //       <Route path="show-user" element={<AddUser />}></Route>

      //       </Route>
      //     <Route path="product" element={<Product />} />
      //     <Route path="category" element={<Product />} />
      //   </Routes>


     // </div>
      


  )
}





export default SideBar;