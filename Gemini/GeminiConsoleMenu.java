import java.util.Scanner;

public class GeminiConsoleMenu {
    private static String apiKey = null;
    private static boolean isRunning = true;

    // ANSI Color codes
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String PURPLE = "\u001B[35m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String CYAN = "\u001B[36m";

    public static void main(String[] args) {
        // Display the banner first
        ConsoleBanner.display();
        DotenvLoader dotenv = new DotenvLoader(".env");
        apiKey = dotenv.get("GEMINI_API_KEY"); 

        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            System.out.println(YELLOW + "Choose an option:" + RESET);
            System.out.println(GREEN + "1. Set API Key 🔑" + RESET);
            System.out.println(CYAN + "2. Start AI Chat 🤖" + RESET);
            System.out.println(RED + "3. Exit ❌" + RESET);
            System.out.print(PURPLE + "Enter your choice: " + RESET);

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print(PURPLE + "Enter your Gemini API Key: " + RESET);
                    apiKey = scanner.nextLine().trim();

                    if (apiKey.isEmpty()) {
                        System.out.println(RED + "⚠️ API key cannot be empty. Please try again.\n" + RESET);
                    } else {
                        System.out.println(GREEN + "✅ API key set successfully!\n" + RESET);
                    }
                    break;

                case "2":
                    if (apiKey == null || apiKey.isEmpty()) {
                        System.out.println(RED + "⚠️ API key not set. Please select option 1 first.\n" + RESET);
                    } else {
                        System.out.print(CYAN + "You: " + RESET);
                        String userPrompt = scanner.nextLine();
                        String response = GeminiAPI.sendRequest(apiKey, userPrompt);
                        System.out.println(GREEN + "Gemini ✨: " + RESET + response + "\n");
                    }
                    break;

                case "3":
                    System.out.println(RED + "👋 Exiting Gemini Console. Goodbye!" + RESET);
                    isRunning = false;
                    break;

                default:
                    System.out.println(RED + "❌ Invalid option. Try again.\n" + RESET);
            }
        }

        scanner.close();
    }
}
