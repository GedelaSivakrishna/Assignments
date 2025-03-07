import { lazy, Suspense, useState } from "react";
import "./App.css";
import { Navigate, Route, Routes } from "react-router";
import Loader from "./components/Loader";
// import Dashboard from "./components/Dashboard";
// import Login from "./components/Login";

// Lazy load the components only when the particular path is visited
const Dashboard = lazy(() => import("./components/Dashboard"));
const Login = lazy(() => import("./components/Login"));

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  return (
    <div>
      <Suspense fallback={<Loader/>}>
          <Routes>
            <Route
              path="/"
              element={
                isLoggedIn ? <Navigate to="/dashboard" /> : <Navigate to="/login" />
              }
            />
              <Route
                path="/login"
                element={<Login setIsLoggedIn={setIsLoggedIn} />}
              />
              <Route path="/dashboard" element={<Dashboard />} />
              <Route path="/loader" element={<Loader/>} />
          </Routes>
      </Suspense>
    </div>
  );
}

export default App;
