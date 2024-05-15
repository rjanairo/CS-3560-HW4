package Scheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class User {
    public static Schedule cal = new Schedule();

    public static void createTask() {
        Scanner scanner = new Scanner(System.in);

        String eventName, input, frequency = "Once";
        int option;
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;

        System.out.println("What is the name of your task?");
        eventName = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.print("Enter a start date and time (yyyy-MM-dd HH:mm): ");
        input = scanner.nextLine();
        try {
            startTime = LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Task creation aborted.");
            return;
        }

        System.out.print("Enter an end date and time (yyyy-MM-dd HH:mm): ");
        input = scanner.nextLine();
        try {
            endTime = LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Task creation aborted.");
            return;
        }

        System.out.println("Select the type of task:\n(1) Regular Task\n(2) Recurring Task\n(3) AntiTask");
        option = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (option) {
            case 1:
                cal.addTask(new Task(eventName, startTime, endTime, frequency));
                break;
            case 2:
                System.out.print("Enter the frequency (e.g., 'Weekly'): ");
                frequency = scanner.nextLine();
                cal.addTask(new RecurringTask(eventName, startTime, endTime, frequency));
                break;
            case 3:
                cal.addTask(new AntiTask(eventName, startTime, endTime));
                break;
            default:
                System.out.println("Invalid option. Task creation aborted.");
                break;
        }
    }

    public static void deleteTask() {
        Scanner scanner = new Scanner(System.in);

        String eventName, input;
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;

        System.out.println("What is the name of the task you wish to delete?");
        eventName = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.print("Enter a start date and time (yyyy-MM-dd HH:mm): ");
        input = scanner.nextLine();
        try {
            startTime = LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Task deletion aborted.");
            return;
        }

        System.out.print("Enter an end date and time (yyyy-MM-dd HH:mm): ");
        input = scanner.nextLine();
        try {
            endTime = LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Task deletion aborted.");
            return;
        }

        cal.deleteTask(eventName, startTime, endTime);
    }

    public static void editTask() {
        Scanner scanner = new Scanner(System.in);

        String eventName, input, newEventName;
        LocalDateTime startTime = null, newStartTime = null;
        LocalDateTime endTime = null, newEndTime = null;

        System.out.println("What is the name of the task you wish to edit?");
        eventName = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.print("Enter the old start date and time (yyyy-MM-dd HH:mm): ");
        input = scanner.nextLine();
        try {
            startTime = LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Task editing aborted.");
            return;
        }

        System.out.print("Enter the old end date and time (yyyy-MM-dd HH:mm): ");
        input = scanner.nextLine();
        try {
            endTime = LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Task editing aborted.");
            return;
        }

        System.out.println("Enter the new name of your task:");
        newEventName = scanner.nextLine();

        System.out.print("Enter the new start date and time (yyyy-MM-dd HH:mm): ");
        input = scanner.nextLine();
        try {
            newStartTime = LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Task editing aborted.");
            return;
        }

        System.out.print("Enter the new end date and time (yyyy-MM-dd HH:mm): ");
        input = scanner.nextLine();
        try {
            newEndTime = LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Task editing aborted.");
            return;
        }

        cal.editTask(eventName, startTime, endTime, newEventName, newStartTime, newEndTime);
    }

    public static void viewDailySchedule() {
        Scanner scanner = new Scanner(System.in);
        String input;
        LocalDateTime date = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.print("Enter a date and time (yyyy-MM-dd HH:mm): ");
        input = scanner.nextLine();
        try {
            date = LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Viewing daily schedule aborted.");
            return;
        }

        cal.printDailySchedule(date);
    }

    public static void viewWeeklySchedule() {
        Scanner scanner = new Scanner(System.in);
        String input;
        LocalDateTime startDate = null;
        LocalDateTime endDate;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.print("Enter a start date and time (yyyy-MM-dd HH:mm): ");
        input = scanner.nextLine();
        try {
            startDate = LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Viewing weekly schedule aborted.");
            return;
        }

        endDate = startDate.plusDays(7);
        cal.printWeeklySchedule(startDate, endDate);
    }

    public static void viewMonthlySchedule() {
        Scanner scanner = new Scanner(System.in);
        String input;
        LocalDateTime startDate = null;
        LocalDateTime endDate;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.print("Enter a start date and time (yyyy-MM-dd HH:mm): ");
        input = scanner.nextLine();
        try {
            startDate = LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Viewing monthly schedule aborted.");
            return;
        }

        endDate = startDate.plusDays(30);
        cal.printWeeklySchedule(startDate, endDate);
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int input;

        System.out.println("Welcome to your Scheduler.\nPlease select a task.");
        do {
            System.out.println("(1) To import a schedule (CSV file)");
            System.out.println("(2) To export your schedule (CSV file)");
            System.out.println("(3) To add an event to your schedule");
            System.out.println("(4) To delete an event from your schedule");
            System.out.println("(5) To edit an event in your schedule");
            System.out.println("(6) To print a daily schedule");
            System.out.println("(7) To print a weekly schedule");
            System.out.println("(8) To print a monthly schedule");
            System.out.println("(9) To print your entire schedule");
            System.out.println("(0) Exit\n");
            input = scn.nextInt();
            scn.nextLine(); // consume newline

            switch (input) {
                case 1:
                    System.out.println("Please enter the filepath of the csv file you wish to import: ");
                    String filepath = scn.nextLine();
                    cal.importCSV(filepath);
                    break;
                case 2:
                    System.out.println("Attempting to export to csv...");
                    cal.exportCSV();
                    break;
                case 3:
                    createTask();
                    break;
                case 4:
                    deleteTask();
                    break;
                case 5:
                    editTask();
                    break;
                case 6:
                    viewDailySchedule();
                    break;
                case 7:
                    viewWeeklySchedule();
                    break;
                case 8:
                    viewMonthlySchedule();
                    break;
                case 9:
                    cal.printSchedule();
                    break;
                case 0:
                    // Exit
                    break;
                default:
                    System.out.println("Error: Please input an integer between 0-9\n\n");
                    break;
            }

        } while (input != 0);

        System.out.println("\nThank you for using the scheduler!");
    }
}
