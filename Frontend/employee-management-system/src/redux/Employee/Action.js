import { api } from "../../config/config";

// Fetch all employees 
export const fetchEmployeesInBatch = (reqData) => async (dispatch) => {
    dispatch({type: "FETCH_EMPLOYEES_IN_BATCH_REQUEST"});
    try {
        const {data} = await api.get(`/employees/batch?pageNo=${reqData.pageNo}&size=${reqData.size}`);
        dispatch({type: "FETCH_EMPLOYEES_IN_BATCH_SUCCESS", payload: data});
        console.log("Employees details fetched successfully ", data);
    } catch (error) {
        dispatch({type: "FETCH_EMPLOYEES_IN_BATCH_FAILURE", payload: error})
        console.log("error while fetching all employees ", error);
    }
}

// Add Employee
export const createEmployee = (reqData) => async (dispatch) => {
    dispatch({type: "ADD_EMPLOYEE_REQUEST"})
    try {
        const {data} = await api.post(`/employee`, reqData.employeeDto);
        dispatch({type: "ADD_EMPLOYEE_SUCCESS", payload: data});
        console.log("Employee created successfully ", data);
    } catch (error) {
        dispatch({type: "ADD_EMPLOYEE_FAILURE", payload: error.response.data});
        console.log("error while creating new employee", error);
    }
}

// Update Employee
export const updateEmployee = (reqData) => async (dispatch) => {
    dispatch({type: "UPDATE_EMPLOYEE_REQUEST"})
    try {
        const {data} = await api.put(`/employee/${reqData.id}`, reqData.employeeDto);
        dispatch({type: "UPDATE_EMPLOYEE_SUCCESS", payload: data});
        console.log("EMployee details updated successfully", data);
    } catch (error) {
        dispatch({type: "UPDATE_EMPLOYEE_FAILURE", payload: error});
        console.log("Error while updating the employee ", error);
    }
}

// Delete Employee
export const deleteEmployee = (reqData) => async (dispatch) => {
    dispatch({type: "DELETE_EMPLOYEE_REQUEST"});
    try {
        const {data} = await api.delete(`/employee/${reqData.id}`);
        dispatch({type: "DELETE_EMPLOYEE_SUCCESS", payload: data});
        console.log("Emloyee deleted successfully ", data);
    } catch (error) {
        dispatch({type: "DELETE_EMPLOYEE_FAILURE", payload: error});
        console.log("error while deleting employee", error);
    }
}

