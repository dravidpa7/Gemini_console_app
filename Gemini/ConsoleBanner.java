// ConsoleBanner.java
public class ConsoleBanner {
    // ANSI color codes for terminal text styling
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String PURPLE = "\u001B[35m";

    public static void display() {
        System.out.println();
        System.out.println(YELLOW + BOLD + " Welcome to Gemini Console App " + RESET);
        System.out.println(PURPLE +
                "   ██████╗ ███████╗███╗   ███╗██╗███╗   ██╗██╗\n" +
                "  ██╔════╝ ██╔════╝████╗ ████║██║████╗  ██║██║\n" +
                "  ██║  ███╗█████╗  ██╔████╔██║██║██╔██╗ ██║██║\n" +
                "  ██║   ██║██╔══╝  ██║╚██╔╝██║██║██║╚██╗██║██║\n" +
                "  ╚██████╔╝███████╗██║ ╚═╝ ██║██║██║ ╚████║██║\n" +
                "   ╚═════╝ ╚══════╝╚═╝     ╚═╝╚═╝╚═╝  ╚═══╝╚═╝" + RESET);
        System.out.println(CYAN + "\nPowered by Gemini API AI Text Assistant\n" + RESET);
        System.out.println("-----------------------------------------------------\n");
    }
}
