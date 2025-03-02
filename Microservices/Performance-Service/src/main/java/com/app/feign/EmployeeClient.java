package com.app.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("employee-service")
public interface EmployeeClient {

    // create employee feedback
    // fetch employee feedback
    // update employee feedback
    // top performing employees
    // fetch all feedbacks

}
