package Scheduler;

import java.time.LocalDateTime;

public class Task {
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String frequency;

    public Task(String name, LocalDateTime startTime, LocalDateTime endTime, String frequency) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.frequency = frequency;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Task{name='" + name + '\'' + ", startTime=" + startTime + ", endTime=" + endTime + ", frequency='" + frequency + '\'' + '}';
    }
}
