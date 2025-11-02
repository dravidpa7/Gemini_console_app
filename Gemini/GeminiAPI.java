import java.net.URI;
import java.net.http.*;
import java.io.IOException;

public class GeminiAPI {
    private static final String MODEL = "gemini-2.5-flash";
    private static final String ENDPOINT =
        "https://generativelanguage.googleapis.com/v1beta/models/" + MODEL + ":generateContent";

    public static String sendRequest(String apiKey, String prompt) {
        try {
            // Build request JSON
            String jsonBody = "{ \"contents\": [{ \"parts\": [{ \"text\": " 
                + jsonEscape(prompt) + " }] }] }";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ENDPOINT))
                .header("Content-Type", "application/json")
                .header("x-goog-api-key", apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

            HttpResponse<String> resp = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (resp.statusCode() != 200) {
                return "Error: HTTP " + resp.statusCode() + " — " + resp.body();
            }

            String body = resp.body();

            // ✅ Extract just the text content safely
            String extracted = extractText(body);
            return extracted != null ? extracted : "No response text found.";

        } catch (IOException | InterruptedException e) {
            return "Error: " + e.getMessage();
        }
    }

    // Simple JSON string escaper
    private static String jsonEscape(String s) {
        if (s == null) return "\"\"";
        String escaped = s.replace("\\", "\\\\")
                          .replace("\"", "\\\"")
                          .replace("\n", "\\n")
                          .replace("\r", "\\r");
        return "\"" + escaped + "\"";
    }

    // 🔍 Minimal parser to extract "text" from Gemini response
    private static String extractText(String json) {
        String marker = "\"text\":";
        int idx = json.indexOf(marker);
        if (idx == -1) return null;

        int start = json.indexOf("\"", idx + marker.length());
        int end = json.indexOf("\"", start + 1);
        if (start == -1 || end == -1) return null;

        String text = json.substring(start + 1, end);
        // unescape basic JSON chars
        return text.replace("\\n", "\n").replace("\\\"", "\"");
    }
}
