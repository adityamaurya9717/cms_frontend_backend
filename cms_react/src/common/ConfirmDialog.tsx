import { Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, Slide } from "@mui/material";
import { TransitionProps } from "@mui/material/transitions";
import React, { useState } from "react";

const Transition = React.forwardRef(function Transition(
    props: TransitionProps & {
      children: React.ReactElement<any, any>;
    },
    ref: React.Ref<unknown>,
  ) {
    return <Slide direction="down" ref={ref} {...props} />;
  });
const ConfirmDialog = (props: any) => {

    const boxShown = props.show;

    const acceptedHandler = (isSucces: boolean) => {
        props.successHandler(isSucces);
    }
    return (
        <Dialog
            open={boxShown}
            TransitionComponent={Transition}
            keepMounted
            onClose={()=>{acceptedHandler(true)}}
            aria-describedby="alert-dialog-slide-description"
        >
            <DialogTitle>Are You Sure ?</DialogTitle>
            <DialogContent dividers={true}>
                <DialogContentText id="alert-dialog-slide-description">
                  Are You sure To Delete A Item
                </DialogContentText>
            </DialogContent>
            <DialogActions>
                <Button onClick={(e) => acceptedHandler(true)} >Yes</Button>
                <Button onClick={(e) => acceptedHandler(false)} >No</Button>
            </DialogActions>

        </Dialog>

    )

}

export default ConfirmDialog;