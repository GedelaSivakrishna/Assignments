const initialState = {
  departments: [],
  totalDepartmentCount: 0,
  totalDepartmentPages: 0,
  createdDepartment: null,
  updatedDepartment: null,
  deleteDepartmentStatus: null,
  loading: false,
  fetchDepartmentError: null,
  createDepartmentError: null,
  updatedDepartmentError: null,
  deleteDepartmentError: null,
  departmentEmployees: [],
};

export const departmentReducer = (state = initialState, { type, payload }) => {
  switch (type) {
    case "FETCH_DEPARTMENTS_IN_BATCH_REQUEST":
      return { ...state, loading: true };
    case "FETCH_DEPARTMENTS_IN_BATCH_SUCCESS":
      return {
        ...state,
        departments: payload.content,
        totalDepartmentCount: payload.totalElements,
        totalDepartmentPages: payload.totalPages,
        loading: false,
      };
    case "FETCH_DEPARTMENTS_IN_BATCH_FAILURE":
      return { ...state, fetchDepartmentError: payload, loading: false };
    case "ADD_DEPARTMENT_REQUEST":
      return { ...state, loading: true };
    case "ADD_DEPARTMENT_SUCCESS":
      return { ...state, createdDepartment: payload, loading: false };
    case "ADD_DEPARTMENT_FAILURE":
      return { ...state, createDepartmentError: payload, loading: false };
    case "UPDATE_DEPARTMENT_REQUEST":
      return { ...state, loading: true };
    case "UPDATE_DEPARTMENT_SUCCESS":
      return { ...state, updatedDepartment: payload, loading: false };
    case "UPDATE_DEPARTMENT_FAILURE":
      return { ...state, updatedDepartmentError: payload, loading: false };
    case "DELETE_DEPARTMENT_REQUEST":
      return { ...state, loading: true };
    case "DELETE_DEPARTMENT_SUCCESS":
      return { ...state, deleteDepartmentStatus: payload, loading: false };
    case "DELETE_DEPARTMENT_FAILURE":
      return { ...state, deleteDepartmentError: error, loading: false };
    case "DEPARTMENT_EMPLOYEES_COUNT":
      return { ...state, departmentEmployees: payload };
    default:
      return state;
  }
};
