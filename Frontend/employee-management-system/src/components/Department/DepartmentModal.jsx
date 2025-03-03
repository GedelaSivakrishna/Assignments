import React, { useEffect, useState } from "react";
import Box from "@mui/material/Box";
import Modal from "@mui/material/Modal";
import Fade from "@mui/material/Fade";
import Button from "@mui/material/Button";
import { Chip, InputLabel, Stack, TextField } from "@mui/material";
import { useForm } from "react-hook-form";
import CancelIcon from "@mui/icons-material/Cancel";
import DepartmentDeleteModal from "./DepartmentDeleteModal";
import { useDispatch } from "react-redux";
import { updateDepartment } from "../../redux/Department/Action";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 550,
  //   height: 500,
  bgcolor: "background.paper",
  boxShadow: 24,
  p: 4,
};

const DepartmentModal = ({ open, setOpenModal, row }) => {
  const [openDeleteModal, setOpenDeleteModal] = useState(false);
  const [selectedEmployees, setSelectedEmployees] = useState([]); // Default selected department
  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm({
    defaultValues: {
      name: row?.name,
      location: row?.location,
      employees: row?.employeesCount,
    },
  });
  const dispatch = useDispatch();

  useEffect(() => {
    if (row) {
      const employeesEmail = row.employees.map((emp) => emp.email);
      reset({
        name: row.name,
        location: row.location,
        employees: employeesEmail.length,
      });
      setSelectedEmployees(employeesEmail);
    }
  }, [row, reset]);

  const handleClose = (event, reason) => {
    if (reason === "backdropClick") {
      return;
    }
    setOpenModal(false);
  };

  function handleChipClick(employee) {
    // Functional Form Of useState, setting new state through previous state,
    // This way, state gets updated immediately
    setSelectedEmployees((prevSelected) => {
      const newSelected = prevSelected.includes(employee)
        ? prevSelected.filter((emp) => emp !== employee) // Remove if already selected
        : [...prevSelected, employee]; // Add if not selected

      reset((prev) => ({
        ...prev,
        employees: newSelected.length,
      }));

      return newSelected;
    });
  }

  function handleUpdateDepartment(data) {
    const empIds = row.employees
      .filter((emp) => selectedEmployees.includes(emp.email))
      .map((emp) => emp.id);
    const reqData = {
      deptId: row.id,
      updateDepartmentDto: {
        name: data.name,
        location: data.location,
        empIds: empIds,
      },
    };
    dispatch(updateDepartment(reqData));
    handleClose();
  }

  function handleDeleteDepartment() {
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
              {/* Department Details */}
              <div className="flex flex-wrap justify-start gap-5">
                <div>
                  <InputLabel>Name</InputLabel>
                  <TextField
                    name="name"
                    type="text"
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

                <div>
                  <InputLabel>Employees</InputLabel>
                  <TextField
                    name="employees"
                    type="number"
                    {...register("employees")}
                    disabled
                  />
                </div>
              </div>

              {/*Department Employees Selection */}
              <div>
                <InputLabel>Employees</InputLabel>
                {selectedEmployees && row && (
                  <Stack
                    direction="row"
                    sx={{ display: "flex", flexWrap: "wrap" }}
                    gap={1}
                  >
                    {row.employees.map(({ email }) => (
                      <Chip
                        key={email}
                        label={email}
                        variant={
                          selectedEmployees.includes(email)
                            ? "filled"
                            : "outlined"
                        }
                        onClick={() => handleChipClick(email)}
                        color={
                          selectedEmployees.includes(email)
                            ? "primary"
                            : "default"
                        }
                      />
                    ))}
                  </Stack>
                )}

                {row?.employees.length === 0 && (
                  <p className="font-light">
                    No Employees working in this department
                  </p>
                )}
              </div>

              {/* Action Buttons */}
              <div className="flex justify-evenly">
                <Button
                  onClick={handleSubmit(handleUpdateDepartment)}
                  color="warning"
                  variant="contained"
                  sx={{ paddingX: "2rem" }}
                >
                  Update
                </Button>
                <Button
                  onClick={handleDeleteDepartment}
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

      <DepartmentDeleteModal
        open={openDeleteModal}
        setOpenDeleteModal={setOpenDeleteModal}
        id={row?.id}
      />
    </div>
  );
};

export default DepartmentModal;
