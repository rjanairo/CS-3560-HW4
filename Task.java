package Scheduler;

import java.rmi.registry.LocateRegistry;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;

public class Task
{
    private String taskName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String frequency;

    public Task(String eventName, LocalDateTime startTime, LocalDateTime endTime, String frequency){
        taskName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.frequency = frequency;
    }

    public String getName() {

        return taskName;
    }
    
    public LocalDateTime getStartDate() {

        return startTime;
    }

    public LocalDateTime getEndDate() {
        return endTime;
    }

    public String getFrequency(){
        return frequency;
    }
}

