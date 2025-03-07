import React, { useEffect, useState } from "react";
import Box from "@mui/material/Box";
import Modal from "@mui/material/Modal";
import Fade from "@mui/material/Fade";
import Button from "@mui/material/Button";
import { Alert, Chip, InputLabel, Snackbar, Stack, TextField } from "@mui/material";
import { useForm } from "react-hook-form";
import CancelIcon from "@mui/icons-material/Cancel";
import EmployeeDeleteModal from "./EmployeeDeleteModal";
import { useDispatch, useSelector } from "react-redux";
import { fetchDepartmentsInBatch } from "../../redux/Department/Action";
import { updateEmployee } from "../../redux/Employee/Action";

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

const EmployeeModal = ({ open, setOpenModal, row }) => {
  const [openDeleteModal, setOpenDeleteModal] = useState(false);
  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm({
    defaultValues: {
      name: row.name,
      email: row.email,
      dateOfJoining: row.dateOfJoining,
      salary: row.salary,
    },
  });
  const dispatch = useDispatch();
  const storeDepartments = useSelector(
    (store) => store.department?.departments
  );
  const totalDepartmentPages = useSelector(store => store.department.totalDepartmentPages);
  const departments = storeDepartments?.map((dep) => dep.name);
  const userDepartments = row?.departments?.map((dept) => dept.name);
  const [selectedDepartments, setSelectedDepartments] = useState(); // Default selected department
  const [page, setPage] = useState(0);

  useEffect(() => {
    if (row) {
      reset({
        name: row.name,
        email: row.email,
        dateOfJoining: row.dateOfJoining,
        salary: row.salary,
      });
      setSelectedDepartments(userDepartments);
    }
  }, [row]);

  useEffect(() => {
    const reqData = {
      pageNo: page,
      size: 5,
    }
    dispatch(fetchDepartmentsInBatch(reqData));
  }, [page]);

  function handlePreviousBtnClick() {
    setPage(page-1);
  }

  function handleNextBtnClick() {
    setPage(page + 1);
  }

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

  function handleUpdateEmployee(data) {
    const deptIds = storeDepartments
      ?.filter((storeDept) => selectedDepartments.includes(storeDept.name))
      .map((dept) => dept.id);
    data.deptIds = deptIds;
    let reqData = {
      id: row.id,
      employeeDto: data,
    };
    dispatch(updateEmployee(reqData));
    handleClose();
  }

  function handleDeleteEmployee() {
    setOpenDeleteModal(true);
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
                  />
                  {errors.salary && (
                    <p className="text-red-500">{errors.salary.message}</p>
                  )}
                </div>
              </div>

              {/* Employee Departments Selection */}
              {selectedDepartments && (
                <div>
                  <InputLabel>Department</InputLabel>
                  <Stack
                    direction="row"
                    sx={{ display: "flex", flexWrap: "wrap" }}
                    gap={1}
                  >
                    {departments?.map((department) => (
                      <Chip
                        key={department}
                        label={department}
                        variant={
                          selectedDepartments?.includes(department)
                            ? "filled"
                            : "outlined"
                        }
                        onClick={() => handleChipClick(department)}
                        color={
                          selectedDepartments?.includes(department)
                            ? "primary"
                            : "default"
                        }
                      />
                    ))}
                  </Stack>
                  <div className="flex justify-around min-w-full pt-4">
                      <Button 
                      disabled={page == 0}
                      variant="outlined" onClick={handlePreviousBtnClick}>Prev</Button>
                      <Button
                      disabled={page == totalDepartmentPages-1}
                      variant="outlined" onClick={handleNextBtnClick}>Next</Button>
                  </div>
                </div>
              )}



              {/* Action Buttons */}
              <div className="flex justify-evenly">
                <Button
                  onClick={handleSubmit(handleUpdateEmployee)}
                  color="warning"
                  variant="contained"
                  sx={{ paddingX: "2rem" }}
                >
                  Update
                </Button>
                <Button
                  onClick={handleDeleteEmployee}
                  color="error"
                  variant="contained"
                  sx={{ paddingX: "2rem" }}
                >
                  Delete
                </Button>
              </div>
            </div>
          </Box>
        </Fade>
      </Modal>

        {/* Have to implement Lazy Loading here */}
      {openDeleteModal &&
         <EmployeeDeleteModal
          open={openDeleteModal}
          setOpenDeleteModal={setOpenDeleteModal}
          id={row?.id}
        /> }

    </div>
  );
};

export default EmployeeModal;
