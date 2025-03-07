package com.app.feign;

import com.app.dto.PerformanceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "performance-service", path = "performance")
public interface PerformanceClient {

    // Create Employee Feedback
    @PostMapping("create")
    public ResponseEntity<PerformanceDto> createPerformanceHandler(@RequestParam("empId") int employeeId,
                                                                   @RequestBody PerformanceDto performanceDto);

    // View employee performance
    @GetMapping("employee")
    public ResponseEntity<List<PerformanceDto>> viewEmployeePerformanceHandler(@RequestParam("empId") int employeeId);

    // View employee weekly performance
    @GetMapping("employee/week")
    public ResponseEntity<PerformanceDto> employeeWeeklyFeedbackHandler(@RequestParam("empId") int employeeId ,@RequestParam("wId") int weekId);

    // Get top performing employee id
    @GetMapping("employee/top")
    public ResponseEntity<Integer> topPerformerHandler(@RequestParam("empIds") List<Integer> employeeIds);

    // Get the least performing employee id
    @GetMapping("employee/least")
    public ResponseEntity<Integer> leastPerformerHandler(@RequestParam("empIds") List<Integer> employeeIds);

    // Update employee performance
    @PutMapping("employee/update")
    public ResponseEntity<PerformanceDto> updateFeedbackHandler(@RequestParam("empId") int employeeId,
                                                                @RequestParam("wId") int weekId,
                                                                @RequestBody PerformanceDto performanceDto);

}
