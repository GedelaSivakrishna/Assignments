package com.app.service.serviceImpl;

import com.app.constants.Constants;
import com.app.dto.PerformanceDto;
import com.app.exceptions.InvalidRatingException;
import com.app.model.Performance;
import com.app.repository.PerformanceRepo;
import com.app.service.PerformanceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class PerformanceServiceImpl implements PerformanceService {

    @Autowired
    private PerformanceRepo performanceRepo;
    @Autowired
    private ModelMapper mapper;

    /***
     * Method to create performance for an employee
     * @param employeeId id of the employee
     * @param performanceDto this dto contains the performance information like rating, feedback, week Id
     * @return the newly created performance
     * @throws InvalidRatingException if the rating in the dto is less than 1 or greater than 4
     */
    @Override
    public PerformanceDto createPerformance(int employeeId, PerformanceDto performanceDto) {

        if(performanceDto.getRating() < Constants.MIN_RATING_VAL || performanceDto.getRating() > Constants.MAX_RATING_VAL) {
            throw new InvalidRatingException("Invalid rating ! Rating must be between " +
                    Constants.MIN_RATING_VAL + " and " + Constants.MAX_RATING_VAL);
        }

        Performance newPerformance = new Performance();
        newPerformance.setRating(performanceDto.getRating());
        newPerformance.setFeedback(performanceDto.getFeedback());
        newPerformance.setEmployeeId(employeeId);
        newPerformance.setWeekId(performanceDto.getWeekId());

        Performance createdPerformance = performanceRepo.save(newPerformance);

        PerformanceDto dto = new PerformanceDto();
        dto.setRating(createdPerformance.getRating());
        dto.setFeedback(createdPerformance.getFeedback());
        dto.setWeekId(createdPerformance.getWeekId());

        return dto;
    }

    /***
     * Method to view all the performance of them employee
     * @param employeeId id of the employee
     * @return List of employee performances
     */
    @Override
    public List<PerformanceDto> viewEmployeesPerformance(int employeeId) {
        return performanceRepo.findEmployeePerformance(employeeId);
    }

    /***
     * Method to find the weekly performance of employee
     * @param employeeId id of the employee
     * @param weekId id of the week
     * @return performance of the particular week
     */
    @Override
    public PerformanceDto employeeWeeklyFeedback(int employeeId, int weekId) {
        return mapper.map(performanceRepo.findByEmployeeIdAndWeekId(employeeId, weekId), PerformanceDto.class);
    }

    /***
     * Method to find the top performer
     * @param employeeIds list of ids of employees
     * @return id of the top performing employee
     */
    @Override
    public Integer topPerformer(List<Integer> employeeIds) {
        int maxRating = Integer.MIN_VALUE;
        int topPerformerEmployeeId = -1;
        for(int employeeId: employeeIds) {
            int currRating = 0;

            // Find the employees performance
            List<Performance> employeePerformances = performanceRepo.findByEmployeeId(employeeId);

            // sum up their rating
            for(Performance performance : employeePerformances)
                currRating += performance.getRating();

            // find the max rating
            if(currRating > maxRating) {
                topPerformerEmployeeId = employeeId;
                maxRating = currRating;
            }

        }
        return topPerformerEmployeeId;
    }

    /***
     * Method to find the least performer
     * @param employeeIds list of ids of employees
     * @return id of the least performer
     */
    @Override
    public Integer leastPerformer(List<Integer> employeeIds) {
        int maxRating = Integer.MAX_VALUE;
        int leastPerformerEmployeeId = -1;
        for(int employeeId: employeeIds) {
            int currRating = 0;

            // Find the employees performance
            List<Performance> employeePerformances = performanceRepo.findByEmployeeId(employeeId);

            // sum up their rating
            for(Performance performance : employeePerformances)
                currRating += performance.getRating();

            // find the max rating
            if(currRating < maxRating) {
                leastPerformerEmployeeId = employeeId;
                maxRating = currRating;
            }

        }
        return leastPerformerEmployeeId;
    }

    /***
     * Method to update the employee feedback
     * @param employeeId id of the employee
     * @param weekId id of the week
     * @param updatedFeedback new feedback values
     * @return updated employee performance
     */
    @Override
    public PerformanceDto updateEmployeeFeedback(int employeeId, int weekId, PerformanceDto updatedFeedback) {
        Performance performance = performanceRepo.findByEmployeeIdAndWeekId(employeeId, weekId);
        if(!Objects.isNull(updatedFeedback.getFeedback()) && !performance.getFeedback().equals(updatedFeedback.getFeedback())) {
            performance.setFeedback(updatedFeedback.getFeedback());
        }
        if(updatedFeedback.getRating() != 0 && updatedFeedback.getRating() != performance.getRating()) {
            if(updatedFeedback.getRating() < Constants.MIN_RATING_VAL || updatedFeedback.getRating() > Constants.MAX_RATING_VAL) {
                throw new InvalidRatingException("Invalid rating ! Rating must be between " +
                        Constants.MIN_RATING_VAL + " and " + Constants.MAX_RATING_VAL);
            }
            performance.setRating(updatedFeedback.getRating());
        }
        PerformanceDto performanceDto = mapper.map(performanceRepo.save(performance), PerformanceDto.class);
        return performanceDto;
    }
}
