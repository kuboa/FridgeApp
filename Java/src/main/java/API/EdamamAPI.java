package API;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;


public class EdamamAPI {

    private static final String APP_ID = "ef56e20a";
    private static final String API_KEY = "d8cbd5ce32845014814316f6d3185ca1";

    public static String searchRecipes(String ingredients) {
        try {
            String encodedIngredients = URLEncoder.encode(ingredients, StandardCharsets.UTF_8.toString());
            String url = "https://api.edamam.com/search?q=" + encodedIngredients + "&app_id=" + APP_ID + "&app_key=" + API_KEY;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonObject = new JSONObject(response.body());
            if (jsonObject.getJSONArray("hits").length() > 0) {
                String recipeUrl = jsonObject.getJSONArray("hits").getJSONObject(0).getJSONObject("recipe").getString("url");
                return recipeUrl;
            }
            return "レシピが見つかりません";
        } catch (Exception e) {
            e.printStackTrace();
            return "レシピの取得に失敗しました";
        }
    }
}
