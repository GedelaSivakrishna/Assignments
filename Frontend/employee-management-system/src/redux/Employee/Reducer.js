
const initialState = {
    employees: [],
    totalEmployeesCount: 0,
    createdEmployee: null,
    createEmployeeError: null,
    updatedEmployee: null,
    updateEmployeeError: null,
    deleteEmployeeStatus: "",
    deleteEmployeeError: null,
    loading: false,
    error: null
};

export const employeeReducer = (state=initialState,{type, payload}) => {
    switch(type) {
        case "FETCH_EMPLOYEES_IN_BATCH_REQUEST":
            return {...state, loading: true};
        case "FETCH_EMPLOYEES_IN_BATCH_SUCCESS":
            return {...state, employees: payload.content, totalEmployeesCount: payload.totalElements, loading: false};
        case "FETCH_EMPLOYEES_IN_BATCH_FAILURE":
            return {...state, error: payload, loading: false};
        case "ADD_EMPLOYEE_REQUEST":
            return {...state, loading: true};
        case "ADD_EMPLOYEE_SUCCESS":
            return {...state, createdEmployee: payload, loading: false};
        case "ADD_EMPLOYEE_FAILURE":
            return {...state, createEmployeeError: payload, loading: false};
        case "UPDATE_EMPLOYEE_REQUEST":
            return {...state, loading: true};
        case "UPDATE_EMPLOYEE_SUCCESS":
            return {...state, updatedEmployee: payload, loading: false};
        case "UPDATE_EMPLOYEE_FAILURE":
            return {...state, error: payload, loading: false};
        case "DELETE_EMPLOYEE_REQUEST":
            return {...state, loading: true};
        case "DELETE_EMPLOYEE_SUCCESS":
            return {...state, deleteEmployeeStatus: payload, loading: false};
        case "DELETE_EMPLOYEE_FAILURE":
            return {...state, error: payload, loading: false};
        default:
            return state;
    }
}