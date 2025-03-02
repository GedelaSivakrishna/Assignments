package com.app.service;

import com.app.dto.PerformanceDto;

import java.util.List;

public interface PerformanceService {

    PerformanceDto createPerformance(int employeeId, PerformanceDto performanceDto);

    List<PerformanceDto> viewEmployeesPerformance(int employeeId);

    PerformanceDto employeeWeeklyFeedback(int employeeId, int weekId);

    Integer topPerformer(List<Integer> employeeIds);

    Integer leastPerformer(List<Integer> employeeIds);

    PerformanceDto updateEmployeeFeedback(int employeeId,
               int weekId, PerformanceDto updatedFeedback);

}
