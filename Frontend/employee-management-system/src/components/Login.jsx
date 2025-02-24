import { Button, Grid2, TextField } from "@mui/material";
import React, { useState } from "react";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router";
import "../App.css"

const Login = ({setIsLoggedIn}) => {
  const navigate = useNavigate();
  const [showErrorMsg, setShowErrorMsg] = useState(false);
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({
    defaultValues: {
      username: "",
      password: ""
    }
  });

  function handleFormSubmit(data) {
    console.log(data);
    if(data.username === "user" && data.password === "password") {
        console.log("Login successfull, calling setIsLoggedIn ");
        setIsLoggedIn(true);
        navigate("/dashboard");
    }
    else {
      setShowErrorMsg(true);
    }
  }

  return (
    <div className="h-[100vh] w-[100vw] flex justify-center items-center loginBg ">
      <div className="flex flex-col gap-y-5">
        <h1 className="text-4xl font-bold">Employee Management System</h1>
        <div className="max-w-5xl flex justify-center">
          <Grid2
            container
            direction="column"
            spacing={2}
            padding={"2rem"}
            boxShadow="rgba(9, 30, 66, 0.25) 0px 4px 8px -2px, rgba(9, 30, 66, 0.08) 0px 0px 0px 1px;"
            borderRadius="5px"
            width="350px"
            sx={{backgroundColor: "white"}}
          >
            <Grid2>
              <TextField
                name="username"
                type="text"
                placeholder="Enter the username"
                fullWidth
                {...register("username", { required: {
                  value: true,
                  message: "Please enter the username"
                },
                pattern: {
                  value: /^[A-Za-z][A-Za-z0-9]*$/,
                  message: "Invalid username"
                }
               })}
               onChange={() => setShowErrorMsg(false)}
              />
              {errors.username && <p className="text-red-500">{errors.username.message}</p>}
            </Grid2>

            <Grid2>
              <TextField
                name="password"
                type="password"
                placeholder="Enter the password"
                fullWidth
                {...register("password", { required: {
                  value: true,
                  message: "Please enter the password"
                }
              })}
                onChange={() => setShowErrorMsg(false)}
              />
              {errors.password && <p className="text-red-500">{errors.password.message}</p>}
            </Grid2>
            <Grid2>
              {showErrorMsg && <p className="text-red-500">Invalid Login credentials</p>}
              <Button
               onClick={handleSubmit(handleFormSubmit)}
               fullWidth variant="contained">
                Login
              </Button>
            </Grid2>
          </Grid2>
        </div>
      </div>
    </div>
  );
};

export default Login;


