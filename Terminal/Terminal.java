import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Terminal {
    private static final int MAX_HISTORY_SIZE = 10;
    private static final String EXIT_COMMAND = "exit";

    private static List<String> commandHistory = new ArrayList<>();

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input;
            while (true) {
                System.out.print("$ "); // Prompt symbol
                input = reader.readLine();

                if (input.isEmpty()) {
                    continue; // Skip if input is empty
                }

                // Add the command to history
                addToCommandHistory(input);

                // Handle specific commands
                if (input.equalsIgnoreCase(EXIT_COMMAND)) {
                    System.out.println("Exiting the terminal.");
                    break;
                } else if (input.equalsIgnoreCase("history")) {
                    displayCommandHistory();
                } else {
                    // Execute command
                    executeCommand(input);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading user input: " + e.getMessage());
        }
    }

    private static void addToCommandHistory(String command) {
        if (commandHistory.size() == MAX_HISTORY_SIZE) {
            commandHistory.remove(0); // Remove the oldest command if history size exceeds the limit
        }
        commandHistory.add(command);
    }

    private static void displayCommandHistory() {
        System.out.println("Command History:");
        for (String command : commandHistory) {
            System.out.println(command);
        }
    }

    private static void executeCommand(String input) {
        // Split the input into command and arguments
        String[] parts = input.trim().split("\\s+");
        String command = parts[0];
        String[] arguments = new String[parts.length - 1];
        System.arraycopy(parts, 1, arguments, 0, arguments.length);

        // Implement command execution logic here
        System.out.println("Executing command: " + command);
        System.out.println("Arguments:");
        for (String arg : arguments) {
            System.out.println(arg);
        }
        // You can further process the command and its arguments to perform specific actions
    }
}

