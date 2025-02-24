import React, { lazy, Suspense, useState } from "react";
import Navbar from "./Navbar";
// import EmployeeTable from "./Employee";
// import DepartmentTable from "./Department";
import "../App.css";
import Loader from "./Loader";

const DepartmentTable = lazy(() => import("./Department"));
const EmployeeTable = lazy(() => import("./Employee"));

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
