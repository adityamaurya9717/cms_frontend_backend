import React from 'react';
import MenuIcon from '@mui/icons-material/Menu';
import './navigation.css'
import { IconButton } from '@mui/material';
const NavigationBar = (props:any)=>{
    const navigation = ()=>{
        props.onSideBar()
    }

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
        </header>
    )
}
export default NavigationBar;