import React, { lazy, Suspense, useState } from "react";
import Navbar from "./Navbar";
// import EmployeeTable from ;
// import DepartmentTable from 
import "../App.css";
import Loader from "./Loader";

const DepartmentTable = lazy(() => import("./Department/Department"));
const EmployeeTable = lazy(() => import("./Employee/Employee"));

const Dashboard = () => {
  const [selectedButton, setSelectedButton] = useState("EMPLOYEE");
  return (
    <div>
      <Navbar
        selectedButton={selectedButton}
        setSelectedButton={setSelectedButton}
      />

      <Suspense fallback={<Loader />}>
        {selectedButton === "EMPLOYEE" && <EmployeeTable />}
      </Suspense>

      <Suspense fallback={<Loader />}>
        {selectedButton === "DEPARTMENT" && <DepartmentTable />}
      </Suspense>
    </div>
  );
};

export default Dashboard;
