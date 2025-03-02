package com.app.dto;

public class PerformanceDto {
    private int rating;
    private String feedback;
    private int weekId;

    public PerformanceDto(int rating, String feedback, int weekId) {
        this.rating = rating;
        this.feedback = feedback;
        this.weekId = weekId;
    }

    public PerformanceDto() {
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getWeekId() {
        return weekId;
    }

    public void setWeekId(int weekId) {
        this.weekId = weekId;
    }
}
