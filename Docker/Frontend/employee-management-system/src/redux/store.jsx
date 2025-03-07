import { configureStore } from '@reduxjs/toolkit'
import { employeeReducer } from './Employee/Reducer'
import { departmentReducer } from './Department/Reducer'

// Don't need to add thunk middleware because It's already added in 
// redux@toolkit
export const store = configureStore({
  reducer: {
    employee: employeeReducer,
    department: departmentReducer
  },
})

