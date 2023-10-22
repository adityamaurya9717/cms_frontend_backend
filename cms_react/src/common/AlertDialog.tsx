import * as React from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import Slide from '@mui/material/Slide';
import { TransitionProps } from '@mui/material/transitions';

const dialogBoxStyle = {

  width:'500px',

}
const Transition = React.forwardRef(function Transition(
    props: TransitionProps & {
      children: React.ReactElement<any, any>;
    },
    ref: React.Ref<unknown>,
  ) {
    return <Slide direction="down" ref={ref} {...props} />;
  });
  
  export default function AlertDialogSlide(props:any) {
    const obj = {...props.data}
    const content = {
        message:obj.message
    }
    console.log("Dialog Component called isOpen=",props.isOpen)

    //  auto close after 3 second
    let intervalId:any = null;
     React.useEffect(()=>{
       if(props.isOpen==true){
        intervalId =  setTimeout(()=>{
          console.log("set timeout called")
          props.onCloseHandler()
        },2500)
       }
     },[props.isOpen])
  
  
    const handleClose = () => {
      clearInterval(intervalId)
      props.onCloseHandler()
    };
  
    return (
    
        <Dialog
          open={props.isOpen}
          TransitionComponent={Transition}
          keepMounted
          onClose={handleClose}
          aria-describedby="alert-dialog-slide-description"
        >
          <div style={dialogBoxStyle}>
          <DialogTitle >Message</DialogTitle>
          <DialogContent dividers={true}>
            <DialogContentText id="alert-dialog-slide-description">
             {content.message}
            </DialogContentText>
          </DialogContent>
          <DialogActions>
            <Button onClick={handleClose}>Close</Button>
          </DialogActions>

          </div>
        </Dialog>
     
    );
  }