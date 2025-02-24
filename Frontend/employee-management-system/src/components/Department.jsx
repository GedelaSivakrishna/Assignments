import React, { useEffect, useState } from "react";
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
import DepartmentModal from "./DepartmentModal";
import { useDispatch, useSelector } from "react-redux";
import {fetchDepartmentsInBatch } from "../redux/Department/Action";

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
    id: "location",
    numeric: false,
    disablePadding: false,
    label: "Location",
  },
  {
    id: "employeesCount",
    numeric: false,
    disablePadding: false,
    label: "Employees",
  },
];

function DepartmentTableHead(props) {
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

DepartmentTableHead.propTypes = {
  numSelected: PropTypes.number.isRequired,
  onRequestSort: PropTypes.func.isRequired,
  onSelectAllClick: PropTypes.func.isRequired,
  order: PropTypes.oneOf(["asc", "desc"]).isRequired,
  orderBy: PropTypes.string.isRequired,
  rowCount: PropTypes.number.isRequired,
};

export default function DepartmentTable() {
  const [openDepartmentModal, setOpenDepartmentModal] = useState(false);
  const [order, setOrder] = React.useState("asc");
  const [orderBy, setOrderBy] = React.useState("name");
  const [selected, setSelected] = React.useState([]);
  const [page, setPage] = React.useState(0);
  const [dense, setDense] = React.useState(false);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);
  const dispatch = useDispatch();
  const departments = useSelector((store) => store.department.departments);
  const totalDepartmentCount = useSelector((store) => store.department.totalDepartmentCount);
  // When the below states updates from store, fetch the department details again
  const updatedDepartment = useSelector(
    (store) => store.department?.updatedDepartment
  );
  const createdDepartment = useSelector(
    (store) => store.department?.createdDepartment
  );
  const deleteDepartmentStatus = useSelector(
    (store) => store.department?.deleteDepartmentStatus
  );
  const createdEmployee = useSelector(
    (store) => store.employee?.createdEmployee
  );
  const updatedEmployee = useSelector(
    (store) => store.employee?.updatedEmployee
  );
  const deleteEmployeeStatus = useSelector(
    (store) => store.employee?.deleteEmployeeStatus
  );
  const [currRow, setCurrRow] = useState();

  useEffect(() => {
    const reqData = {
      pageNo: page,
      size: rowsPerPage,
    }
    dispatch(fetchDepartmentsInBatch(reqData));
  }, [
    createdDepartment,
    updatedDepartment,
    deleteDepartmentStatus,
    createdEmployee,
    updatedEmployee,
    deleteEmployeeStatus,
    page,
    rowsPerPage
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
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  // Avoid a layout jump when reaching the last page with empty rows.
  // const emptyRows =
  //   page > 0
  //     ? Math.max(0, (1 + page) * rowsPerPage - departments?.length)
  //     : 0;

  const visibleRows = React.useMemo(
    () => {
      if(!departments || departments.length === 0) return [];
      return [...departments].sort(getComparator(order, orderBy))
    }, [departments, order, orderBy]);

  return (
    <div className="min-h-screen flex justify-center items-center bg-gray-50 py-14">
      <Box sx={{ width: "80%" }}>
        <Paper
          sx={{
            width: "100%",
            mb: 2,
            boxShadow: "rgba(0, 0, 0, 0.24) 0px 3px 8px;",
          }}
        >
          {/* <DepartmentTableToolbar numSelected={selected.length} /> */}
          <TableContainer sx={{ minHeight: 350 }}>
            {visibleRows && (
              <Table
                sx={{ minWidth: 750 }}
                aria-labelledby="tableTitle"
                size={dense ? "small" : "medium"}
              >
                <DepartmentTableHead
                  numSelected={selected.length}
                  order={order}
                  orderBy={orderBy}
                  onSelectAllClick={handleSelectAllClick}
                  onRequestSort={handleRequestSort}
                  rowCount={departments?.length}
                />
                <TableBody>
                  {visibleRows.map((row, index) => {
                    const isItemSelected = selected.includes(row.id);
                    const labelId = `enhanced-table-checkbox-${index}`;

                    return (
                      <TableRow
                        hover
                        onClick={() => {
                          setOpenDepartmentModal(true);
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
                        <TableCell align="center">{row.location}</TableCell>
                        <TableCell align="center">
                          {row.employeesCount}
                        </TableCell>
                      </TableRow>
                    );
                  })}
  
                </TableBody>
              </Table>
            )}
          </TableContainer>
          <TablePagination
            rowsPerPageOptions={[5, 10, 25]}
            component="div"
            count={totalDepartmentCount}
            rowsPerPage={rowsPerPage}
            page={page}
            onPageChange={handleChangePage}
            onRowsPerPageChange={handleChangeRowsPerPage}
          />
        </Paper>
      </Box>

      <DepartmentModal
        open={openDepartmentModal}
        setOpenModal={setOpenDepartmentModal}
        row={currRow}
      />
    </div>
  );
}
