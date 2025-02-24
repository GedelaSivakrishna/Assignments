import { vi, describe, test, expect } from "vitest";
import { api } from "../config/config";
import { fetchEmployeesInBatch } from "../redux/Employee/Action";

vi.mock("../../config/config", () => ({
    api: {
        get: vi.fn(),
    }
}));

describe("fetchEmployeesInBatch", () => {
    const dispatch = vi.fn();

    test("dispatches success action when API call is successfull", async () => {
        const mockResponse = { employees: [{id: 1, name: "John Doe"}], totalPages: 5};
        api.get.mockResolvedValueOnce({data: mockResponse});

        await fetchEmployeesInBatch({pageNo: 1, size: 10 })(dispatch);

        expect(dispatch).toHaveBeenCalledWith({ type: "FETCH_EMPLOYEES_IN_BATCH_REQUEST"});
        expect(dispatch).toHaveBeenCalledWith({ type: "FETCH_EMPLOYEES_IN_BATCH_SUCCESS", payload: mockResponse});
    })
})