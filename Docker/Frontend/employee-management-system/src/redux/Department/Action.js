import { api } from "../../config/config";

// Fetch Departments 
export const fetchDepartmentsInBatch = (reqData) => async (dispatch) => {
    dispatch({type: "FETCH_DEPARTMENTS_IN_BATCH_REQUEST"});
    try {
        const {data} = await api.get(`/employees/department?pageNo=${reqData.pageNo}&size=${reqData.size}`);
        dispatch({type: "FETCH_DEPARTMENTS_IN_BATCH_SUCCESS", payload: data});
        console.log("All Departments fetched successfully ", data);
    } catch (error) {
        dispatch({type:"FETCH_DEPARTMENTS_IN_BATCH_FAILURE", payload: error});
        console.log("Error while fetching all departments", error);
    }
} 

// Add Department
export const createDepartment = (reqData) => async (dispatch) => {
    dispatch({type: "ADD_DEPARTMENT_REQUEST"});
    try {
        const {data} = await api.post("/department", reqData.departmentDto);
        dispatch({type: "ADD_DEPARTMENT_SUCCESS", payload: data});
        console.log("Created new Department successfully ", data);
    } catch (error) {
        dispatch({type: "ADD_DEPARTMENT_FAILURE", payload: error});
        console.log("Error, while creating new department", error);
    }
}

// Update Department
export const updateDepartment = (reqData) => async (dispatch) => {
    dispatch({type: "UPDATE_DEPARTMENT_REQUEST"});
    try {
        const {data} = await api.put(`/department/${reqData.deptId}`, reqData.updateDepartmentDto);
        dispatch({type: "UPDATE_DEPARTMENT_SUCCESS", payload: data});
        console.log("Department updated successfully ", data);
    } catch (error) {
        dispatch({type: "UPDATE_DEPARTMENT_FAILURE", payload: error});
        console.log("Error while updating department", error);
    }
}

// Delete Department
export const deleteDepartment = (reqData) => async (dispatch) => {
    dispatch({type: "DELETE_DEPARTMENT_REQUEST"})
    try {
        const {data} = await api.delete(`/department/${reqData.deptId}`);
        dispatch({type: "DELETE_DEPARTMENT_SUCCESS", payload: data});
        console.log("Department deleted successfully ");
    } catch (error) {
        dispatch({type: "DELETE_DEPARTMENT_FAILURE", payload: error});
        console.log("Error while deleting department", error);
    }
}

// Department Employees Count
export const departmentEmployeesCount = () => async (dispatch) => {
    try {
        const {data} = await api.get("/employees/department");
        dispatch({type: "DEPARTMENT_EMPLOYEES_COUNT", payload: data});
        console.log("Employees Count fetched successfully ", data);
    } catch (error) {
        console.log("Error while fetching employees in department count ", error);
    }
}