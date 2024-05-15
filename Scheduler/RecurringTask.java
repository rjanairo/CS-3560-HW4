package Scheduler;

import java.time.LocalDateTime;

public class RecurringTask extends Task {
    public RecurringTask(String name, LocalDateTime startTime, LocalDateTime endTime, String frequency) {
        super(name, startTime, endTime, frequency);
    }

    @Override
    public String toString() {
        return "RecurringTask{name='" + getName() + '\'' + ", startTime=" + getStartTime() + ", endTime=" + getEndTime() + ", frequency='" + getFrequency() + '\'' + '}';
    }
}
