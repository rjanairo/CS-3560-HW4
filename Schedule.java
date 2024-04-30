package Scheduler;

import java.text.ParseException;
import java.time.Duration;
//import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Comparator;
import java.io.*;
//import com.opencsv.CSVReader;

public class Schedule
{
    private ArrayList<Task> taskList;

    public Schedule(){
        taskList = new ArrayList<Task>();
    }

    /** Allows the user to check availability in his/her schedule.
     * @param startTime      The date to check for availability.
     * @param endTime        The start time to check for availability.
     * @return true if there's an availability in the schedule, false otherwise.
     */
    public boolean checkAvailability(LocalDateTime startTime, LocalDateTime endTime)
    {
        boolean isAvailable = true;

        for (int i = 0; i < taskList.size(); i++) {
            LocalDateTime objStartDate = taskList.get(i).getStartDate();
            LocalDateTime objEndDate = taskList.get(i).getEndDate();
            String event = taskList.get(i).getName();

            //if-else can be more optimized to one if statement
            if (objStartDate.isBefore(startTime) && objEndDate.isBefore(endTime)) {
                
            }else if(objStartDate.isAfter(startTime) && objEndDate.isAfter(endTime)){
                
            }else{
                return false;
            }
        }

        return isAvailable;
    }

    /** Allows the user to check if he/she is attempting to create a task
     *  that overlaps an existing task.
     * @param task  A task that may already exist within the user's schedule.
     * @return true if there's an overlap in the schedule, false otherwise.
     */
    public boolean checkOverlap(Task task)
    {
        return false;
    }

    /**
     * 
     * @param eventName
     * @param startTime
     * @param endTime
     * @param frequency
     * @return
     * 
     */
    public boolean addTask(String eventName, LocalDateTime startTime, LocalDateTime endTime, String frequency)
    {
        boolean avail = checkAvailability(startTime, endTime);

        if(avail){
            if (frequency.equals("Weekly")) {
                LocalDateTime currentDateTime = startTime;
    
                LocalTime eventStartTime = startTime.toLocalTime();
                LocalTime eventEndTime = endTime.toLocalTime();
    
                Duration duration = Duration.between(eventStartTime, eventEndTime);
                long hours = duration.toHours();
    
                while (currentDateTime.isBefore(endTime)) {
                    Task event = new Task(eventName, currentDateTime, currentDateTime.plusHours(hours), frequency);
                    taskList.add(event);
                    currentDateTime = currentDateTime.plusWeeks(1);
                }
                //System.out.println("true");
                return true;
    
            } else {
                Task event = new Task(eventName, startTime, endTime, frequency);
                taskList.add(event);
                //System.out.println("arrayList size is: " + taskList.size());
                System.out.println();
                return true;
            }
        }else{
            //System.out.println("f");
            System.out.println("Date or time is unavailable. Please try again.");
            return false;
        }
    }

    public boolean addAntiTask(String eventName, LocalDateTime startTime, LocalDateTime endTime, String frequency){
        
        for (int i = 0; i < taskList.size(); i++) {
            LocalDateTime objStartDate = taskList.get(i).getStartDate();
            LocalDateTime objEndDate = taskList.get(i).getEndDate();
            String event = taskList.get(i).getName();
            if (objStartDate.isBefore(startTime) && objEndDate.isBefore(endTime)) {
                
            }else if(objStartDate.isAfter(startTime) && objEndDate.isAfter(endTime)){
                
            }else{
                taskList.remove(i);
            }
        }

        addTask(eventName, startTime, endTime, frequency);

        return true;
    }

    /**
     * 
     * @param eventName
     * @param startTime
     * @param endTime
     * @return
     */
    public boolean deleteTask(String eventName, LocalDateTime startTime, LocalDateTime endTime)
    {
        
        for (int i = 0; i < taskList.size(); i++) {
            LocalDateTime objStartDate = taskList.get(i).getStartDate();
            LocalDateTime objEndDate = taskList.get(i).getEndDate();
            String event = taskList.get(i).getName();
            if (objStartDate.toLocalDate().equals(startTime.toLocalDate()) && objEndDate.toLocalDate().equals(endTime.toLocalDate()) && event.equals(eventName)) {
                taskList.remove(i);
                return true;
            }
        }
        return false;

    }

    /** Allows the user to edit a task in his/her schedule.
     * @param taskName  A task that will be edited from the user's schedule.
     * @return true if the task was successfully edited from the schedule, false otherwise.
     */
    public void editTask(String oldName, LocalDateTime oldStartDate, LocalDateTime oldEndDate, String newName, LocalDateTime newStartDate, LocalDateTime newEndDate)
    {
        if(deleteTask(oldName, oldStartDate, oldEndDate)){
            
            if(checkAvailability(newStartDate, newEndDate)){
                addTask(newName, newStartDate, newEndDate, "daily");
            }else{
                System.out.println("Please try again. New date/time is unavailable.");
                System.out.println("Reinserting old task");
                addTask(newName, newStartDate, newEndDate, "daily");
            }

        }else{
            System.out.println("Task is not found. Please try again.");
        }

    }

    public void orderSchedule(){

        taskList.sort(new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                return task1.getStartDate().compareTo(task2.getStartDate());
            }
        });

    }

    /** Displays the user's schedule for the day.
     */
    public void printDailySchedule(LocalDateTime dayTime)
    {
        orderSchedule();

        System.out.println("Your schedule for " + dayTime.toLocalDate() + " is: \n");
        System.out.println(taskList.size());

        for (int i = 0; i < taskList.size(); i++) {
            LocalDateTime objDate = taskList.get(i).getStartDate();
            //System.out.println("Checking " + dayTime.toLocalDate() + " and " + objDate.toLocalDate());

            if (objDate.toLocalDate().equals(dayTime.toLocalDate())) {
                //System.out.println("Found similar.");

                System.out.println(taskList.get(i).getName() + " " + taskList.get(i).getStartDate() +
                        " " + taskList.get(i).getEndDate());
                System.out.println();
            }
        }
    }

    /** Displays the user's schedule for the week.
     */
    public void printWeeklySchedule(LocalDateTime startDate, LocalDateTime endDate)
    {
        orderSchedule();

        System.out.println("Your schedule for " + startDate.toLocalDate() + " to " + endDate.toLocalDate() + " is:\n");
        System.out.println(taskList.size());

        for(int i = 0; i < taskList.size(); i++){
            LocalDateTime objDate = taskList.get(i).getStartDate();

            if(objDate.toLocalDate().isBefore(endDate.toLocalDate()) &&
                    objDate.toLocalDate().isAfter(startDate.toLocalDate())){
                System.out.println(taskList.get(i).getName() + " " + taskList.get(i).getStartDate() +
                        " " + taskList.get(i).getEndDate());
                System.out.println();
            }
        }
    }

    /** Displays the user's schedule for the month.
     */
    public void printMonthlySchedule(LocalDateTime startDate, LocalDateTime endDate)
    {
        orderSchedule();

        System.out.println("Your schedule for the month from " + startDate.toLocalDate() + " to " +
                           endDate.toLocalDate() + " is :\n");
        System.out.println(taskList.size());

        for(int i = 0; i < taskList.size(); i++){
            LocalDateTime objDate = taskList.get(i).getStartDate();

            if(objDate.toLocalDate().isBefore(endDate.toLocalDate()) &&
                    objDate.toLocalDate().isAfter(startDate.toLocalDate())){
                System.out.println(taskList.get(i).getName() + " " + taskList.get(i).getStartDate() +
                        " " + taskList.get(i).getEndDate());
                System.out.println();
            }
        }
    }

    public void printSchedule(){
        orderSchedule();

        for(int i = 0; i < taskList.size(); i++){

            System.out.println(taskList.get(i).getName() + " " + taskList.get(i).getStartDate() +
                        " " + taskList.get(i).getEndDate());
            System.out.println();

        }
    }

    public void exportCSV(){
        String filePath = "C:\\Users\\Neil\\Desktop\\CS\\CS3560\\Scheduler-project\\HW4\\output.csv";
        String delimiter = ",";
        FileWriter writer = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String startDateString, endDateString;

        try {
            writer = new FileWriter(filePath);

            // Write the header row
            writer.append("eventName").append(delimiter)
                    .append("startTime").append(delimiter)
                    .append("endTime").append(delimiter)
                    .append("frequency").append('\n');

            // Write the data rows

            for(int i = 0; i < taskList.size(); i++){

                writer.append(taskList.get(i).getName());
                writer.append(delimiter);
                writer.append(taskList.get(i).getStartDate().format(formatter));
                writer.append(delimiter);
                writer.append(taskList.get(i).getEndDate().format(formatter));
                writer.append(delimiter);
                writer.append(taskList.get(i).getFrequency());
                writer.append('\n');
                // System.out.println(taskList.get(i).getName() + " " + taskList.get(i).getStartDate() +
                //             " " + taskList.get(i).getEndDate());
                // System.out.println();
    
            }

            System.out.println("CSV file written successfully");
        } catch (IOException e) {
            System.out.println("Error writing CSV file: " + e.getMessage());
        } finally {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                System.out.println("Error closing writer: " + e.getMessage());
            }
        }
        
    }

    public void importCSV(String filepath){
        File file = new File(filepath);
        BufferedReader reader = null;
        // BufferedReader reader = new BufferedReader(new FileReader(file));

        try{
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                // create a new Task object and add it to the ArrayList
                // System.out.println(values[0]);
                // System.out.println(values[1]);
                // System.out.println(values[2]);
                // System.out.println(values[3]);
                String eventName = values[0];
                LocalDateTime startDate = LocalDateTime.parse(values[1], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                LocalDateTime endDate = LocalDateTime.parse(values[2], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                //System.out.println(values[3]);
                String frequency = values[3];
                Task task = new Task(eventName, startDate, endDate, frequency);
                taskList.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                System.out.println("Error closing reader: " + e.getMessage());
            }
        }
    }
}
