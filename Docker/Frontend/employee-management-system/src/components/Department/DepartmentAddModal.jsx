import React, { useState } from "react";
import Box from "@mui/material/Box";
import Modal from "@mui/material/Modal";
import Fade from "@mui/material/Fade";
import Button from "@mui/material/Button";
import { InputLabel, TextField } from "@mui/material";
import { useForm } from "react-hook-form";
import CancelIcon from "@mui/icons-material/Cancel";
import { useDispatch } from "react-redux";
import { createDepartment } from "../../redux/Department/Action";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 550,
  bgcolor: "background.paper",
  boxShadow: 24,
  p: 4,
};

const DepartmentAddModal = ({ open, setOpenModal }) => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({
    defaultValues: {
      name: "",
      location: "",
    },
  });
  const dispatch = useDispatch();

  const handleClose = (event, reason) => {
    if (reason === "backdropClick") {
      return;
    }
    setOpenModal(false);
  };

  function handleSaveDepartment(data) {
    const reqData = {
      departmentDto: data,
    };
    dispatch(createDepartment(reqData));
    handleClose();
  }

  function handleCancel() {
    handleClose();
  }

  return (
    <div>
      <Modal
        aria-labelledby="transition-modal-title"
        aria-describedby="transition-modal-description"
        open={open}
        onClose={handleClose}
        closeAfterTransition
      >
        <Fade in={open}>
          <Box sx={style} gap={5}>
            <div className="flex flex-col gap-y-5">
              <div className="text-end">
                <CancelIcon
                  onClick={handleClose}
                  className=" scale-125 hover:cursor-pointer hover:scale-150 
                   transition-all duration-500 ease-out"
                />
              </div>
              {/* Department Details */}
              <div className="flex flex-wrap justify-start gap-5">
                <div>
                  <InputLabel>Name</InputLabel>
                  <TextField
                    name="name"
                    type="text"
                    placeholder="Enter new department name"
                    {...register("name", {
                      required: {
                        value: true,
                        message: "Please enter department name",
                      },
                    })}
                  />
                  {errors.name && (
                    <p className="text-red-500">{errors.name.message}</p>
                  )}
                </div>

                <div>
                  <InputLabel>Location</InputLabel>
                  <TextField
                    name="location"
                    type="text"
                    placeholder="Enter the department location"
                    {...register("location", {
                      required: {
                        value: true,
                        message: "Please enter the location",
                      },
                    })}
                  />
                  {errors.email && (
                    <p className="text-red-500">{errors.location.message}</p>
                  )}
                </div>
              </div>

              {/* Action Buttons */}
              <div className="flex justify-evenly">
                <Button
                  onClick={handleCancel}
                  color="warning"
                  variant="contained"
                  sx={{ paddingX: "2rem" }}
                >
                  Cancel
                </Button>

                <Button
                  onClick={handleSubmit(handleSaveDepartment)}
                  color="success"
                  variant="contained"
                  sx={{ paddingX: "2rem" }}
                >
                  Save
                </Button>
              </div>
            </div>
          </Box>
        </Fade>
      </Modal>
    </div>
  );
};

export default DepartmentAddModal;
