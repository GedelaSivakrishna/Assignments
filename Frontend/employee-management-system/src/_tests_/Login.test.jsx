import { BrowserRouter } from "react-router";
import { expect, test, vi } from "vitest";
import Login from "../components/Login";
import { fireEvent, render, screen, waitFor } from "@testing-library/react";

const mockSetIsLoggedIn = vi.fn();

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

// test("displays error for invalid credentials", async () => {
//     renderLogin();
//     fireEvent.change(screen.getByPlaceholderText(/Enter the username/i), { target: {value: "wronguser"}});
//     fireEvent.change(screen.getByPlaceholderText(/Enter the password/i), {target: {value: "wrongpassword"}});
//     fireEvent.click(screen.getByRole("button", { name: /login/i}));
//     await waitFor(() => {
//         expect(screen.getByText((content, element) => {
//             const hasText = (node) => node.textContent === "Invalid Login credentials";
//             const elementHasText = hasText(element);
//             const childrenDontHaveText = Array.from(element?.children || []).every(
//                 (child) => !hasText(child)
//             );
//             return elementHasText && childrenDontHaveText;
//         })).toBeInTheDocument();
//     });
// })

// test("redirects to dashboard on successfull login", async () => {
//     renderLogin();
//     fireEvent.change(screen.getByPlaceholderText(/Enter the username/i), { target: {value: "user"}});
//     fireEvent.change(screen.getByPlaceholderText(/Enter the password/i), { target: {value: "password"}});
//     fireEvent.click(screen.getByRole("button", { name: /login/i}));
//     await waitFor(() => {
//         expect(mockSetIsLoggedIn).toHaveBeenCalledWith(true);
//     });
// })

