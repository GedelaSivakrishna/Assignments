package com.app.controller;

import com.app.dto.PerformanceDto;
import com.app.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("performance")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @PostMapping("create")
    public ResponseEntity<PerformanceDto> createPerformanceHandler(@RequestParam("empId") int employeeId,
                                                                   @RequestBody PerformanceDto performanceDto) {
        return new ResponseEntity<>(performanceService.createPerformance(employeeId, performanceDto), HttpStatus.CREATED);
    }

    @GetMapping("employee")
    public ResponseEntity<List<PerformanceDto>> viewEmployeePerformanceHandler(@RequestParam("empId") int employeeId) {
        System.out.println("Request received in performance service with employee ID: " + employeeId);
       List<PerformanceDto> employeePerformances = performanceService.viewEmployeesPerformance(employeeId);
        System.out.println("Fetched the details from database in performance service, sending back to employee service");
        return new ResponseEntity<>(employeePerformances, HttpStatus.OK);
    }

    @GetMapping("employee/week")
    public ResponseEntity<PerformanceDto> employeeWeeklyFeedbackHandler(@RequestParam("empId") int employeeId ,@RequestParam("wId") int weekId) {
        return new ResponseEntity<>(performanceService.employeeWeeklyFeedback(employeeId,weekId), HttpStatus.OK);
    }

    @GetMapping("employee/top")
    public ResponseEntity<Integer> topPerformerHandler(@RequestParam("empIds") List<Integer> employeeIds) {
        return new ResponseEntity<>(performanceService.topPerformer(employeeIds), HttpStatus.OK);
    }

    @GetMapping("employee/least")
    public ResponseEntity<Integer> leastPerformerHandler(@RequestParam("empIds") List<Integer> employeeIds) {
        return new ResponseEntity<>(performanceService.leastPerformer(employeeIds), HttpStatus.OK);
    }

    @PutMapping("employee/update")
    public ResponseEntity<PerformanceDto> updateFeedbackHandler(@RequestParam("empId") int employeeId, @RequestParam("wId") int weekId,
                                                                @RequestBody PerformanceDto performanceDto) {
        return new ResponseEntity<>(performanceService.updateEmployeeFeedback(employeeId, weekId, performanceDto), HttpStatus.OK);
    }
}
