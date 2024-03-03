import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private List<Task> tasks;
    private Scanner scanner;

    public TaskManager() {
        tasks = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    listTasks();
                    break;
                case 3:
                    removeTask();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        System.out.println("Exiting the task manager.");
        scanner.close();
    }

    private void printMenu() {
        System.out.println("Task Manager Menu:");
        System.out.println("1. Add Task");
        System.out.println("2. List Tasks");
        System.out.println("3. Remove Task");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private void addTask() {
        System.out.println("Enter task details:");
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Due Date (yyyy-MM-dd): ");
        LocalDate dueDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Priority (HIGH, MEDIUM, LOW): ");
        Priority priority = Priority.valueOf(scanner.nextLine().toUpperCase());

        Task newTask = new Task(title, dueDate, priority);
        tasks.add(newTask);
        System.out.println("Task added successfully.");
    }

    private void listTasks() {
        System.out.println("Tasks:");
        for (Task task : tasks) {
            System.out.println("- " + task.getTitle() + " (Due: " + task.getDueDate() + ", Priority: " + task.getPriority() + ")");
        }
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        }
    }

    private void removeTask() {
        listTasks();
        if (!tasks.isEmpty()) {
            System.out.print("Enter the title of the task to remove: ");
            String titleToRemove = scanner.nextLine();
            Task taskToRemove = null;
            for (Task task : tasks) {
                if (task.getTitle().equals(titleToRemove)) {
                    taskToRemove = task;
                    break;
                }
            }
            if (taskToRemove != null) {
                tasks.remove(taskToRemove);
                System.out.println("Task removed successfully.");
            } else {
                System.out.println("Task not found.");
            }
        }
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        taskManager.start();
    }
}

class Task {
    private String title;
    private LocalDate dueDate;
    private Priority priority;

    public Task(String title, LocalDate dueDate, Priority priority) {
        this.title = title;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}

enum Priority {
    HIGH,
    MEDIUM,
    LOW
}
