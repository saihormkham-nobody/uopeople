import java.util.Scanner;

/**
 * Simple Quiz Game
 * This program presents a 5-question quiz to the user and calculates their score.
 */
public class main {
    public static void main(String[] args) {
        // Create Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Variables to track score
        int score = 0;

        // Define questions, options and correct answers
        String[] questions = {
            "Which of the following data types in Java is NOT used to store integer values?",
            "Which of the following is the correct way to declare a method in Java?",
            "What is the output of the following code snippet?\nint x = 5;\nint y = 2;\nSystem.out.println(x > y ? \"x is greater than y\" : \"x is less than or equal to y\");",
            "What is the value of \"result\" after executing the following code snippet?\nint result = 10;\nresult -= 2 * 3;",
            "Which of the following is not a valid Java identifier?"
        };

        String[][] options = {
            {"A. int", "B. long", "C. byte", "D. float"},
            {"A. int myMethod( ) { }", "B. void myMethod { }", "C. myMethod( ) { }", "D. int myMethod;"},
            {"A. x is greater than y", "B. x is less than or equal to y", "C. 5", "D. 2"},
            {"A. 4", "B. 6", "C. 8", "D. 10"},
            {"A. myVariable", "B. _variable", "C. 123variable", "D. $variable"}
        };

        char[] correctAnswers = {'D', 'A', 'A', 'A', 'C'};

        int totalQuestions = questions.length; // Total number of questions

        // Display welcome message
        System.out.println("===================================");
        System.out.println("         Self-Quiz Unit 1          ");
        System.out.println("===================================");
        System.out.println("Please enter your answer (A, B, C, or D) for each question.");

        // Loop through each question
        for (int i = 0; i < totalQuestions; i++) {
            // Display question and options
            System.out.println("Question " + (i + 1) + ": " + questions[i]);
            for (int j = 0; j < options[i].length; j++) {
                System.out.println(options[i][j]);
            }

            // Get and validate user input
            char userAnswer = 'Z'; // Initialize with an invalid character

            do {
                // Get user input
                System.out.print("Your answer: ");
                String input = scanner.next().toUpperCase();

                if(input.length() != 1) {
                    System.out.println("Invalid input. Please enter a single character (A, B, C, or D).");
                    continue;
                }

                if(input.charAt(0) >= 'A' && input.charAt(0) <= 'D') {
                    userAnswer = input.charAt(0);
                } else {
                    System.out.println("Invalid input. Please enter A, B, C, or D.");
                }
            } while (userAnswer < 'A' || userAnswer > 'D');


            // Process result with if statement
            if (userAnswer == correctAnswers[i]) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is " + correctAnswers[i] + ".");
            }

            System.out.println(); // Add a blank line between questions
        }

        // Calculate and display final score
        double percentage = (double) score / totalQuestions * 100;

        System.out.println("You answered " + score + " out of " + totalQuestions + " questions correctly.");
        System.out.printf("Your final score is: %.1f%%\n", percentage);
        System.out.println("===================================");

        // Provide feedback based on score
        if (percentage == 100) {
            System.out.println("Perfect score! Excellent job!");
        } else if (percentage >= 80) {
            System.out.println("Great job! You did very well.");
        } else if (percentage >= 60) {
            System.out.println("Good effort! Keep studying.");
        } else {
            System.out.println("Please do not skip Learning Guide Unit 1.");
        }

        // Close the scanner to prevent resource leak
        scanner.close();
    }
}