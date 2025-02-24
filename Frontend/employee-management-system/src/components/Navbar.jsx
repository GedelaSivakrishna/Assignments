import { Button } from "@mui/material";
import React, { lazy, Suspense, useState } from "react";
import { useNavigate } from "react-router";
// import AddEmployeeModal from "./AddEmployeeModal";
// import AddDepartmentModal from "./AddDepartmentModal";
import "../App.css";

const AddEmployeeModal = lazy(() => import("./AddEmployeeModal"));
const AddDepartmentModal = lazy(() => import("./AddDepartmentModal"));

const Navbar = ({ selectedButton, setSelectedButton }) => {
  const [openAddModal, setOpenAddModal] = useState(false);
  const navigate = useNavigate();

  function handleAdd() {
    setOpenAddModal(true);
  }

  return (
    <div className="box-border">
      <div className="h-16 flex justify-between items-center px-4  shadow-lg">
        {/* Logo */}
        <div>
          <h3
            onClick={() => setSelectedButton("EMPLOYEE")}
            className="font-bold cursor-pointer"
          >
            Employee Management System
          </h3>
        </div>

        {/* Buttons */}
        <div className="flex gap-x-4">
          <Button
            onClick={handleAdd}
            variant="contained"
            color="primary"
            sx={{ paddingX: "2rem" }}
          >
            ADD
          </Button>
          <Button
            onClick={() => setSelectedButton("EMPLOYEE")}
            variant={selectedButton === "EMPLOYEE" ? "contained" : "outlined"}
          >
            Employee
          </Button>
          <Button
            onClick={() => setSelectedButton("DEPARTMENT")}
            variant={selectedButton === "DEPARTMENT" ? "contained" : "outlined"}
          >
            Department
          </Button>
          <Button
            onClick={() => navigate("/login")}
            variant="contained"
            color="error"
          >
            Logout
          </Button>
        </div>
      </div>

      <Suspense>
        {selectedButton === "EMPLOYEE" && openAddModal && (
          <AddEmployeeModal
            open={openAddModal}
            setOpenModal={setOpenAddModal}
          />
        )}
      </Suspense>

      <Suspense>
        {selectedButton === "DEPARTMENT" && (
          <AddDepartmentModal
            open={openAddModal}
            setOpenModal={setOpenAddModal}
          />
        )}
      </Suspense>
    </div>
  );
};

export default Navbar;
