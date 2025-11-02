// File: DotenvLoader.java
import java.io.*;
import java.util.*;

public class DotenvLoader {
    private final Map<String, String> vars = new HashMap<>();

    public DotenvLoader(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) continue;
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim().replace("\"", "");
                    vars.put(key, value);
                }
            }
        } catch (IOException e) {
            // file not found or unreadable — that’s fine for optional .env
        }
    }

    public String get(String key) {
        return vars.get(key);
    }
}
