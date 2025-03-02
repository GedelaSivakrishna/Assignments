package com.app.serviceImpl;

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

    @Override
    public List<PerformanceDto> viewEmployeesPerformance(int employeeId) {
        return performanceRepo.findEmployeePerformance(employeeId);
    }

    @Override
    public PerformanceDto employeeWeeklyFeedback(int employeeId, int weekId) {
        return mapper.map(performanceRepo.findByEmployeeIdAndWeekId(employeeId, weekId), PerformanceDto.class);
    }

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
