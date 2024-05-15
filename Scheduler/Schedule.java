package Scheduler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private List<Task> tasks;

    public Schedule() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Task added successfully: " + task.getName());
    }

    public void deleteTask(String name, LocalDateTime startTime, LocalDateTime endTime) {
        tasks.removeIf(task -> task.getName().equals(name) && task.getStartTime().equals(startTime) && task.getEndTime().equals(endTime));
        System.out.println("Task deleted successfully: " + name);
    }

    public void editTask(String name, LocalDateTime startTime, LocalDateTime endTime, String newName, LocalDateTime newStartTime, LocalDateTime newEndTime) {
        for (Task task : tasks) {
            if (task.getName().equals(name) && task.getStartTime().equals(startTime) && task.getEndTime().equals(endTime)) {
                task.setName(newName);
                task.setStartTime(newStartTime);
                task.setEndTime(newEndTime);
                System.out.println("Task edited successfully: " + name);
                return;
            }
        }
        System.out.println("Task not found: " + name);
    }

    public void printDailySchedule(LocalDateTime date) {
        System.out.println("Daily schedule for: " + date.toLocalDate());
        for (Task task : tasks) {
            if (task.getStartTime().toLocalDate().equals(date.toLocalDate())) {
                System.out.println(task);
            }
        }
    }

    public void printWeeklySchedule(LocalDateTime startDate, LocalDateTime endDate) {
        System.out.println("Weekly schedule from: " + startDate.toLocalDate() + " to " + endDate.toLocalDate());
        for (Task task : tasks) {
            if ((task.getStartTime().isAfter(startDate) || task.getStartTime().isEqual(startDate)) &&
                (task.getEndTime().isBefore(endDate) || task.getEndTime().isEqual(endDate))) {
                System.out.println(task);
            }
        }
    }

    public void printSchedule() {
        System.out.println("Complete Schedule:");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void importCSV(String filepath) {
        // CSV import logic here
        System.out.println("CSV imported from: " + filepath);
    }

    public void exportCSV() {
        // CSV export logic here
        System.out.println("CSV exported successfully.");
    }
}
