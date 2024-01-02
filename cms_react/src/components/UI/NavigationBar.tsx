import React, { useCallback } from 'react';
import MenuIcon from '@mui/icons-material/Menu';
import './navigation.css'
import { IconButton } from '@mui/material';
import { useNavigate, useNavigation } from 'react-router-dom';
const NavigationBar = (props:any)=>{
  const navigate = useNavigate();
    const navigation = ()=>{
        props.onSideBar()
    }

    const logoutHandler = useCallback(()=>{
      console.log("logout")
        localStorage.removeItem("token-cms")
        navigate("login")
    },[])

    return (
        <header className="header">
             <IconButton onClick={navigation}
              size="large"
              edge="start"
              color="primary"
             aria-label="menu"
             sx={{ ml: 2 }}
          >
            <MenuIcon style={{width:40,height:40}}/>
          </IconButton>
          <h1 style={{marginLeft:'25px'}}>Test</h1>
          <button onClick={logoutHandler} style={{float:'left'}}>Logout</button>

        </header>
    )
}
export default NavigationBar;