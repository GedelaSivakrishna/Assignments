import axios from "axios"

export const API_BASE_URL = "http://localhost:8080"

export const api = axios.create({
    baseURL : API_BASE_URL,
    // headers : {
    //     "Content-Type" : "application/json"
    // }
})

// Adding headers to every request through Interceptor
axios.interceptors.request.use(
    (config) => 
        config.headers["Content-Type"] = "application/json"
    
)