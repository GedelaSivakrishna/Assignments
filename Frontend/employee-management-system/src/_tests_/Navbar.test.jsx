import { fireEvent, render, screen } from "@testing-library/react";
import { BrowserRouter, useNavigate } from "react-router";
import { beforeEach, describe, expect, test, vi } from "vitest";
import Navbar from "../components/Navbar";

const mockSetSelectedButton = vi.fn();
const mockNavigate = vi.fn();

vi.mock("react-router-dom", async () => {
    const actual = await vi.importActual("react-router-dom");
    return {
        ...actual,
        useNavigate: () => mockNavigate,
    };
})


const renderNavbar = () => {
    return render(
        <BrowserRouter>
            <Navbar selectedButton={"EMPLOYEE"} setSelectedButton={mockSetSelectedButton}  />
        </BrowserRouter>
    )
}

describe("Navbar Component", () => {

    // Calls Navbar before each test case
    beforeEach(() => {
        renderNavbar();
    });

    test("renders navbar with all buttons", () => {
        expect(screen.getByText(/Employee Management System/i)).toBeInTheDocument();
        expect(screen.getByRole("button", {name: /add/i})).toBeInTheDocument();
        expect(screen.getByRole("button", {name: /Employee/i})).toBeInTheDocument();
        expect(screen.getByRole("button", {name: /Department/i})).toBeInTheDocument();
        expect(screen.getByRole("button", {name: /Logout/i})).toBeInTheDocument();
    });

    test("Calls setSelectedButton when clicked on Employee Management System text", () => {
        fireEvent.click(screen.getByText(/Employee Management System/i));
        expect(mockSetSelectedButton).toHaveBeenCalledWith("EMPLOYEE");
    });

    test("Calls setSelectedButton when clicked on Employee Button", () => {
        fireEvent.click(screen.getByRole("button",{name: /Employee/i}));
        expect(mockSetSelectedButton).toHaveBeenCalledWith("EMPLOYEE");
    });

    test("Calls setSelectedButton when clicked on Department Button", () => {
        fireEvent.click(screen.getByRole("button", {name: /Department/i}));
        expect(mockSetSelectedButton).toHaveBeenCalledWith("DEPARTMENT");
    });

    // test("Navigates to login page when clicked on Logout button", () => {
    //     fireEvent.click(screen.getByRole("button", {name: /Logout/i}));
    //     expect(mockNavigate).toHaveBeenCalledWith("/login");
    // });

});