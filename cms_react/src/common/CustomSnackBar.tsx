import { Alert, AlertColor, Box, Snackbar, SnackbarOrigin } from '@mui/material'
import React from 'react'

interface SnakeBarData{
    open:boolean,
    alertType:AlertColor,
    message:string
}

interface State extends SnackbarOrigin {
    open: boolean;
  }
function CustomSnackBar(props:any) {
    let snakeBarData = props.data as SnakeBarData
    // severity = success,error,warning,info
    const [state, setState] = React.useState<State>({
        open: snakeBarData.open,
        vertical: 'top',
        horizontal: 'center',
      });
      const { vertical, horizontal, open } = state;
      const handleClose = () => {
        setState({ ...state, open: false });
      };
    console.log("snack Bar component load = ",state)

  return (
    <Box sx={{ width: 500 }}>
    
    <Snackbar
      sx={{width:700,height:60}}
      autoHideDuration={3000}
      anchorOrigin={{ vertical, horizontal }}
      open={open}
      onClose={handleClose}
      key={vertical + horizontal}
    >
         <Alert onClose={handleClose} severity={snakeBarData.alertType} sx={{ width: '100%' }}>
          {snakeBarData.message}
        </Alert>
        </Snackbar>
  </Box>
  )
}

export default CustomSnackBar