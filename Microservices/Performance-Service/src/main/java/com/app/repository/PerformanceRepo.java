package com.app.repository;

import com.app.dto.PerformanceDto;
import com.app.model.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformanceRepo extends JpaRepository<Performance, Integer> {

    // Constructor Projection - Fetching only the required details from the entity
    @Query("Select new com.app.dto.PerformanceDto(p.rating, p.feedback, p.weekId) from Performance p where p.employeeId = :employeeId")
    List<PerformanceDto> findEmployeePerformance(@Param("employeeId") int employeeId);

    Performance findByEmployeeIdAndWeekId(int employeeId, int weekId);

    List<Performance> findByEmployeeId(int employeeId);
}
