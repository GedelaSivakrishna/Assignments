import React, { useState } from "react";
import Button from "@mui/material/Button";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";
import { useDispatch } from "react-redux";
import { deleteDepartment } from "../redux/Department/Action";

export default function DepartmentDeleteModal({
  open,
  setOpenDeleteModal,
  id,
}) {
  const dispatch = useDispatch();
  const handleClose = () => {
    setOpenDeleteModal(false);
  };

  function handleDeleteDepartment() {
    const reqData = {
      deptId: id,
    };
    dispatch(deleteDepartment(reqData));
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
          {"Do you want to delete the department"}
        </DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            This department will be removed from the employees department list.
            This action cannot be undone
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Disagree</Button>
          <Button color="error" onClick={handleDeleteDepartment} autoFocus>
            Agree
          </Button>
        </DialogActions>
      </Dialog>
    </>
  );
}
