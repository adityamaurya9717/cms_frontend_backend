import React, { Fragment,useEffect } from 'react';
import { NavLink, Outlet,Route,Routes,useNavigate } from 'react-router-dom';
import './css/userdashboard.css'
import ShowUser from './ShowUser';
import AddUser from './AddUser';
const UserDashBoard = () => {
    const navigate = useNavigate()
    const activeLink = (isActive:boolean)=>{
        return isActive? {backgroundColor:'white'} : {backgroundColor:'lightblue'};
    }
    useEffect(()=>{
      navigate("add-user")
    },[])

    return(
    <Fragment>
        <div>
            <header className="header-container">
                <NavLink  style={({isActive})=> activeLink(isActive)}  className="header-container_navlink"  to="add-user"> Add User</NavLink>
                <NavLink style={({isActive})=> activeLink(isActive)} className="header-container_navlink" to="show-user"> Show-User</NavLink>
            </header>
            <Outlet />
        

        </div>

    </Fragment>
    )

}

export default UserDashBoard;