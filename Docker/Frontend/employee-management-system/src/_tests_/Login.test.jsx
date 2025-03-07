import { BrowserRouter } from "react-router";
import { expect, test, vi } from "vitest";
import Login from "../components/Login";
import { fireEvent, render, screen, waitFor } from "@testing-library/react";
import { act } from "react";

const mockSetIsLoggedIn = vi.fn();
// const mockSetShowErrorMsg = vi.spyOn();

const renderLogin = () => {
  return render(
    <BrowserRouter>
      <Login setIsLoggedIn={mockSetIsLoggedIn} />
    </BrowserRouter>
  );
};

test("Check If all login components are displayed or not", () => {
  renderLogin();
  expect(screen.getByText(/Employee Management System/i)).toBeInTheDocument();
  expect(screen.getByPlaceholderText(/Enter the username/i)).toBeInTheDocument();
  expect(screen.getByPlaceholderText(/Enter the password/i)).toBeInTheDocument();
  expect(screen.getByRole("button", { name: /login/i})).toBeInTheDocument();
});

test("show error message for missing input fields", async () => {
    renderLogin();
    fireEvent.click(screen.getByRole("button", {name: /login/i}));
    expect(await screen.findByText(/Please enter the username/i)).toBeInTheDocument();
    expect(await screen.findByText(/please enter the password/i)).toBeInTheDocument();
});

// test("show error message for invalid username format", async () => {
//   renderLogin();

//   await act(async () => {

//     fireEvent.input(screen.getByPlaceholderText(/Enter the username/i), {
//       target : {
//         value: "123user"
//       },
//     });

//     fireEvent.click(screen.getByRole("button", {name: /login/i}));

//   })

//   expect(await screen.findByText(/Invalid username/i)).toBeInTheDocument();
// });



// test("shows error message for invalid credentials", async () => {
//   renderLogin();
  
//   fireEvent.input(screen.getByPlaceholderText(/Enter the username/i), {
//     target: { value: "wronguser" },
//   });
//   fireEvent.input(screen.getByPlaceholderText(/Enter the password/i), {
//     target: { value: "wrongpassword" },
//   });

//   fireEvent.click(screen.getByRole("button", { name: /login/i }));

//   await waitFor(() => {
//     expect(mockSetShowErrorMsg).toHaveBeenCalledWith(true);
//     expect(screen.getByText(/Invalid Login credentials/i)).toBeInTheDocument();
//   });

// });


// test("redirects to dashboard on successfull login", async () => {
//     renderLogin();
//     fireEvent.change(screen.getByPlaceholderText(/Enter the username/i), { target: {value: "user"}});
//     fireEvent.change(screen.getByPlaceholderText(/Enter the password/i), { target: {value: "password"}});
//     fireEvent.click(screen.getByRole("button", { name: /login/i}));
//     await waitFor(() => {
//         expect(mockSetIsLoggedIn).toHaveBeenCalledWith(true);
//     });
// })

test("hides error message when user starts typing again", async () => {
  renderLogin();

  // Enter invalid credentials to trigger error message
  fireEvent.input(screen.getByPlaceholderText(/Enter the username/i), {
    target: { value: "wronguser" },
  });
  fireEvent.input(screen.getByPlaceholderText(/Enter the password/i), {
    target: { value: "wrongpassword" },
  });

  fireEvent.click(screen.getByRole("button", { name: /login/i }));

  expect(await screen.findByText(/Invalid Login credentials/i)).toBeInTheDocument();

  // Change username to clear error
  fireEvent.input(screen.getByPlaceholderText(/Enter the username/i), {
    target: { value: "correctuser" },
  });

  expect(screen.queryByText(/Invalid Login credentials/i)).not.toBeInTheDocument();
});

