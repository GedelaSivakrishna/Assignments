import React, { lazy, Suspense, useEffect, useState } from "react";
import EmployeeModal from "./EmployeeModal";
import PropTypes from "prop-types";
import Box from "@mui/material/Box";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TablePagination from "@mui/material/TablePagination";
import TableRow from "@mui/material/TableRow";
import TableSortLabel from "@mui/material/TableSortLabel";
import Paper from "@mui/material/Paper";
import { visuallyHidden } from "@mui/utils";
import { useDispatch, useSelector } from "react-redux";
import { fetchEmployeesInBatch } from "../redux/Employee/Action";
import Loader from "./Loader";

// Function to sort the columns
function descendingComparator(a, b, orderBy) {
  if (b[orderBy] < a[orderBy]) {
    return -1;
  }
  if (b[orderBy] > a[orderBy]) {
    return 1;
  }
  return 0;
}

function getComparator(order, orderBy) {
  return order === "desc"
    ? (a, b) => descendingComparator(a, b, orderBy)
    : (a, b) => -descendingComparator(a, b, orderBy);
}

// Employee Table Header Column Names
const headCells = [
  {
    id: "name",
    numeric: false,
    disablePadding: true,
    label: "Name",
  },
  {
    id: "email",
    numeric: false,
    disablePadding: false,
    label: "Email",
  },
  {
    id: "dateOfJoining",
    numeric: false,
    disablePadding: false,
    label: "Date of Joining",
  },
  {
    id: "salary",
    numeric: true,
    disablePadding: false,
    label: "Salary",
  },
];

function EmployeeTableHead(props) {
  const {
    onSelectAllClick,
    order,
    orderBy,
    numSelected,
    rowCount,
    onRequestSort,
  } = props;
  const createSortHandler = (property) => (event) => {
    onRequestSort(event, property);
  };

  return (
    <TableHead>
      <TableRow sx={{ backgroundColor: "yellowgreen" }}>
        {headCells.map((headCell) => (
          <TableCell
            key={headCell.id}
            align={"center"}
            padding={headCell.disablePadding ? "none" : "normal"}
            sortDirection={orderBy === headCell.id ? order : false}
            sx={{ fontWeight: "bold" }}
          >
            <TableSortLabel
              active={orderBy === headCell.id}
              direction={orderBy === headCell.id ? order : "asc"}
              onClick={createSortHandler(headCell.id)}
            >
              {headCell.label}
              {orderBy === headCell.id ? (
                <Box component="span" sx={visuallyHidden}>
                  {order === "desc" ? "sorted descending" : "sorted ascending"}
                </Box>
              ) : null}
            </TableSortLabel>
          </TableCell>
        ))}
      </TableRow>
    </TableHead>
  );
}

EmployeeTableHead.propTypes = {
  numSelected: PropTypes.number.isRequired,
  onRequestSort: PropTypes.func.isRequired,
  onSelectAllClick: PropTypes.func.isRequired,
  order: PropTypes.oneOf(["asc", "desc"]).isRequired,
  orderBy: PropTypes.string.isRequired,
  rowCount: PropTypes.number.isRequired,
};

export default function EmployeeTable() {
  const [openEmployeeModal, setOpenEmployeeModal] = useState(false);
  const [order, setOrder] = React.useState("asc");
  const [orderBy, setOrderBy] = React.useState("name");
  const [selected, setSelected] = React.useState([]);
  const [page, setPage] = React.useState(0);
  const [dense, setDense] = React.useState(false);
  const [rowsPerPage, setRowsPerPage] = useState(5);
  const dispatch = useDispatch();
  const employees = useSelector((store) => store.employee.employees);
  const totalEmployeesCount = useSelector(
    (store) => store.employee.totalEmployeesCount
  );
  // When the below states update, fetch employee details again
  const createdEmployee = useSelector(
    (store) => store.employee?.createdEmployee
  );
  const updatedEmployee = useSelector(
    (store) => store.employee?.updatedEmployee
  );
  const deleteEmployeeStatus = useSelector(
    (store) => store.employee?.deleteEmployeeStatus
  );
  const updatedDepartment = useSelector(
    (store) => store.department?.updatedDepartment
  );
  const createdDepartment = useSelector(
    (store) => store.department?.createdDepartment
  );
  const deleteDepartmentStatus = useSelector(
    (store) => store.department?.deleteDepartmentStatus
  );
  const [currRow, setCurrRow] = useState({
    name: "",
    email: "",
    salary: "",
    dateOfJoining: "",
  });

  useEffect(() => {
    const reqData = {
      pageNo: page,
      size: rowsPerPage,
    };
    dispatch(fetchEmployeesInBatch(reqData));
  }, [
    createdEmployee,
    updatedEmployee,
    deleteEmployeeStatus,
    createdDepartment,
    updatedDepartment,
    deleteDepartmentStatus,
    page,
    rowsPerPage,
  ]);

  const handleRequestSort = (event, property) => {
    const isAsc = orderBy === property && order === "asc";
    setOrder(isAsc ? "desc" : "asc");
    setOrderBy(property);
  };

  const handleSelectAllClick = (event) => {
    if (event.target.checked) {
      const newSelected = rows.map((n) => n.id);
      setSelected(newSelected);
      return;
    }
    setSelected([]);
  };

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    console.log("Change rows per page function value", event.target.value);
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  // Avoid a layout jump when reaching the last page with empty rows.
  // const emptyRows =
  //   page > 0 ? Math.max(0, (1 + page) * rowsPerPage - employees?.length) : 0;

  const visibleRows = React.useMemo(() => {
    if (!employees || employees.length === 0) return [];
    return [...employees].sort(getComparator(order, orderBy));
  }, [employees, order, orderBy]);

  return (
    <div className="flex min-h-screen justify-center items-center bg-gray-50 py-14">
      <Box sx={{ width: "80%" }}>
        <Paper
          sx={{
            width: "100%",
            mb: 2,
            boxShadow: "rgba(0, 0, 0, 0.24) 0px 3px 8px;",
          }}
        >
          {/* <EmployeeTableToolbar numSelected={selected.length} /> */}
          <TableContainer sx={{ minHeight: 350 }}>
            {visibleRows ? (
              <Table sx={{ minWidth: 750 }} aria-labelledby="tableTitle">
                <EmployeeTableHead
                  numSelected={selected.length}
                  order={order}
                  orderBy={orderBy}
                  onSelectAllClick={handleSelectAllClick}
                  onRequestSort={handleRequestSort}
                  rowCount={employees.length}
                />
                <TableBody>
                  {visibleRows.map((row, index) => {
                    const isItemSelected = selected.includes(row.id);
                    const labelId = `enhanced-table-checkbox-${index}`;

                    return (
                      <TableRow
                        hover
                        onClick={() => {
                          setOpenEmployeeModal(true);
                          setCurrRow(row);
                        }}
                        role="checkbox"
                        aria-checked={isItemSelected}
                        tabIndex={-1}
                        key={row.id}
                        selected={isItemSelected}
                        sx={{ cursor: "pointer" }}
                      >
                        <TableCell
                          component="th"
                          id={labelId}
                          scope="row"
                          padding="none"
                          align="center"
                        >
                          {row.name}
                        </TableCell>
                        <TableCell align="center">{row.email}</TableCell>
                        <TableCell align="center">
                          {row.dateOfJoining}
                        </TableCell>
                        <TableCell align="center">{row.salary}</TableCell>
                      </TableRow>
                    );
                  })}

                </TableBody>
              </Table>
            ) : (
              <Loader/>
            )}
          </TableContainer>
          <TablePagination
            rowsPerPageOptions={[5, 10, 20]}
            component="div"
            count={totalEmployeesCount}
            rowsPerPage={rowsPerPage}
            page={page}
            onPageChange={handleChangePage}
            onRowsPerPageChange={handleChangeRowsPerPage}
          />
        </Paper>
      </Box>
      
        <EmployeeModal
          open={openEmployeeModal}
          setOpenModal={setOpenEmployeeModal}
          row={currRow}
        />
    </div>
  );
}
