import React, { useEffect, useState } from "react";
import Box from "@mui/material/Box";
import Modal from "@mui/material/Modal";
import Fade from "@mui/material/Fade";
import Button from "@mui/material/Button";
import {
  Alert,
  Chip,
  InputLabel,
  Snackbar,
  Stack,
  TextField,
} from "@mui/material";
import { useForm } from "react-hook-form";
import CancelIcon from "@mui/icons-material/Cancel";
import { useDispatch, useSelector } from "react-redux";
import { createEmployee } from "../../redux/Employee/Action";

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

const EmployeeAddModal = ({ open, setOpenModal }) => {
  const [selectedDepartments, setSelectedDepartments] = useState([]); // Default selected department
  const storeDepartments = useSelector(
    (store) => store.department?.departments
  );
  const departments = storeDepartments?.map((dept) => dept.name);
  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm({
    defaultValues: {
      name: "",
      email: "",
      dateOfJoining: "",
      salary: "",
    },
  });
  const dispatch = useDispatch();
  const [alertOpen, setAlertOpen] = useState(false);
  const [errorAlertOpen, setErrorAlertOpen] = useState(false);
  const createdEmployee = useSelector(store => store.employee.createdEmployee);
  const loading = useSelector(store => store.employee.loading);
  const createEmployeeError = useSelector(store => store.employee.createEmployeeError);

  useEffect(()=>{
    if(createdEmployee) {
      setAlertOpen(true);
    }
    else if (createEmployeeError) {
      setErrorAlertOpen(true);
    }
  },[createdEmployee, createEmployeeError])

  const handleClose = (event, reason) => {
    if (reason === "backdropClick") {
      return;
    }
    setOpenModal(false);
  };

  function handleChipClick(department) {
    setSelectedDepartments(
      (prevSelected) =>
        prevSelected.includes(department)
          ? prevSelected.filter((dep) => dep !== department) // Remove if already selected
          : [...prevSelected, department] // Add if not selected
    );
  }

  function handleCancel() {
    reset({
      name: "",
      email: "",
      dateOfJoining: "",
      salary: "",
    });
    handleClose();
  }

  function handleSaveEmployee(data) {
    const deptIds = storeDepartments
      .filter((dept) => selectedDepartments.includes(dept.name))
      .map((dept) => dept.id);
    let reqData = {
      employeeDto: {
        ...data,
        deptIds: deptIds,
      },
    };
    dispatch(createEmployee(reqData));
    reset({
      name: "",
      email: "",
      dateOfJoining: "",
      salary: "",
    });
    // have to turn on loader here
    handleClose();
  }

  const handleAlertClose = () => {
    setAlertOpen(false);
  };

  const handleErrorAlertClose = () => {
    setErrorAlertOpen(false);
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
              {/* Employee Details */}
              <div className="flex flex-wrap justify-start gap-5">
                <div>
                  <InputLabel>Name</InputLabel>
                  <TextField
                    name="name"
                    type="text"
                    {...register("name", {
                      required: {
                        value: true,
                        message: "Please enter name",
                      },
                    })}
                    placeholder="Enter the employee name"
                  />
                  {errors.name && (
                    <p className="text-red-500">{errors.name.message}</p>
                  )}
                </div>

                <div>
                  <InputLabel>Email</InputLabel>
                  <TextField
                    name="email"
                    type="email"
                    {...register("email", {
                      required: {
                        value: true,
                        message: "Please enter the email",
                      },
                      pattern: {
                        value:
                          /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/,
                        message: "Please enter valid email",
                      },
                    })}
                    placeholder="Enter the email"
                  />
                  {errors.email && (
                    <p className="text-red-500">{errors.email.message}</p>
                  )}
                </div>

                <div>
                  <InputLabel>Date of Joining</InputLabel>
                  <TextField
                    name="dateOfJoining"
                    type="date"
                    sx={{ width: "223px" }}
                    {...register("dateOfJoining", {
                      required: {
                        value: true,
                        message: "Please select join date",
                      },
                    })}
                    placeholder="Select the date of joining"
                  />
                  {errors.dateOfJoining && (
                    <p className="text-red-500">
                      {errors.dateOfJoining.message}
                    </p>
                  )}
                </div>

                <div>
                  <InputLabel>Salary</InputLabel>
                  <TextField
                    name="salary"
                    type="number"
                    {...register("salary", {
                      required: {
                        value: true,
                        message: "Please enter the salary",
                      },
                      min: {
                        value: 1,
                        message: "Please enter a valid salary",
                      },
                    })}
                    placeholder="Enter the salary"
                  />
                  {errors.salary && (
                    <p className="text-red-500">{errors.salary.message}</p>
                  )}
                </div>
              </div>

              {/* Employee Departments Selection */}
              <div>
                <InputLabel>Department</InputLabel>
                <Stack
                  direction="row"
                  sx={{ display: "flex", flexWrap: "wrap" }}
                  gap={1}
                >
                  {departments.map((department) => (
                    <Chip
                      key={department}
                      label={department}
                      variant={
                        selectedDepartments.includes(department)
                          ? "filled"
                          : "outlined"
                      }
                      onClick={() => handleChipClick(department)}
                      color={
                        selectedDepartments.includes(department)
                          ? "primary"
                          : "default"
                      }
                    />
                  ))}
                </Stack>
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
                  onClick={handleSubmit(handleSaveEmployee)}
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

      <Snackbar
        open={alertOpen}
        autoHideDuration={5000} // Alert will close after 5 seconds
        onClose={handleAlertClose}
        anchorOrigin={{ vertical: "top", horizontal: "right" }} // Position
      >
        <Alert onClose={handleAlertClose} severity="success" variant="filled">
          Employee added successfully
        </Alert>
      </Snackbar>
      
      <Snackbar
        open={errorAlertOpen}
        autoHideDuration={5000} // Alert will close after 5 seconds
        onClose={handleErrorAlertClose}
        anchorOrigin={{ vertical: "top", horizontal: "right" }} // Position
      >
        <Alert onClose={handleErrorAlertClose} severity="error" variant="filled">
          {createEmployeeError}
        </Alert>
      </Snackbar>

    </div>
  );
};

export default EmployeeAddModal;
