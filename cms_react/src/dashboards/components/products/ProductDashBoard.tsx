import React,{useState} from 'react';
import '../css/productdashboard.css'
import '../css/userdashboard.css'
import { NavLink, Outlet } from 'react-router-dom';

const ProductDashBoard = ()=>{
    const activeLink = (isActive:boolean)=>{
        return isActive? {backgroundColor:'white'} : {backgroundColor:'lightblue'};
    }

 
    return (
        <div style={box}>
           <header className="header-container">
           <NavLink style={({isActive})=> activeLink(isActive)} className="header-container_navlink"  to="add-product">Add Product</NavLink>
            <NavLink style={({isActive})=> activeLink(isActive)} className="header-container_navlink" to="show-product"> Show-Product</NavLink>   
           </header>
           <Outlet />
        </div>
    )

}

const box = {
    backgroundColor:'white',
    height:'100vh',

}

export default ProductDashBoard;