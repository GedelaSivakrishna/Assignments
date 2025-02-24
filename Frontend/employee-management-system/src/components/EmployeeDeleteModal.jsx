import React, { useState } from "react";
import Button from "@mui/material/Button";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";
import { useDispatch } from "react-redux";
import { deleteEmployee } from "../redux/Employee/Action";

export default function EmployeeDeleteModal({ open, setOpenDeleteModal, id }) {
  const dispatch = useDispatch();
  const handleClose = () => {
    setOpenDeleteModal(false);
  };

  function handleDeleteEmployee() {
    const reqData = {
      id: id,
    };
    dispatch(deleteEmployee(reqData));
    handleClose();
  }

  return (
    <>
      <Dialog
        open={open}
        onClose={handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">
          {"Do you want to delete the employee"}
        </DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            Employee details will be deleted permanently. This action cannot be
            undone
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Disagree</Button>
          <Button color="error" onClick={handleDeleteEmployee} autoFocus>
            Agree
          </Button>
        </DialogActions>
      </Dialog>
    </>
  );
}
