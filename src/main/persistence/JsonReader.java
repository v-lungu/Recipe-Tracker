package persistence;

import model.Ingredient;
import model.Recipe;
import model.RecipeList;
import model.Stock;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads RecipeList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public RecipeList readRecipeList() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRecipeList(jsonObject);
    }

    // EFFECTS: reads Stock from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Stock readStock() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseStock(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses RecipeList from JSON object and returns it
    private RecipeList parseRecipeList(JSONObject jsonObject) {
        RecipeList rl = new RecipeList();
        addRecipes(rl, jsonObject.getJSONObject("recipe-list"));
        return rl;
    }

    // EFFECTS: parses Stock from JSON object and returns it
    private Stock parseStock(JSONObject jsonObject) {
        Stock s = new Stock();
        addStock(s, jsonObject.getJSONObject("stock"));
        return s;
    }

    // MODIFIES: rl
    // EFFECTS: parses recipes from JSON object and adds them to workroom
    private void addRecipes(RecipeList rl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("recipes");
        for (Object json : jsonArray) {
            JSONObject nextRecipe = (JSONObject) json;
            addRecipe(rl, nextRecipe);
        }
    }

    // MODIFIES: rl
    // EFFECTS: parses recipe from JSON object and adds it to workroom
    private void addRecipe(RecipeList rl, JSONObject jsonObject) {
        JSONArray jsonArrayI = jsonObject.getJSONArray("recipe-ingredients");
        JSONArray jsonArrayS = jsonObject.getJSONArray("steps");
        String name = jsonObject.getString("name");
        Recipe recipe = new Recipe(name);

        for (Object json : jsonArrayI) {
            JSONObject nextIngredient = (JSONObject) json;
            addIngredient(recipe, nextIngredient);
        }

        for (Object json : jsonArrayS) {
            recipe.addInstruction(json.toString());
        }

        rl.addRecipe(recipe);
    }

    // MODIFIES: rl
    // EFFECTS: parses recipe from JSON object and adds it to workroom
    private void addIngredient(Recipe r, JSONObject jsonObject) {
        String name = jsonObject.getString("ingredient");
        int amount = jsonObject.getInt("amount");
        String unit = jsonObject.getString("unit");

        Ingredient ingredient = new Ingredient(name, amount, unit);
        r.addIngredient(ingredient);
    }

    // MODIFIES: rl
    // EFFECTS: parses recipes from JSON object and adds them to workroom
    private void addStock(Stock s, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("stock-ingredients");
        for (Object json : jsonArray) {
            JSONObject nextIngredient = (JSONObject) json;
            addIngredientStock(s, nextIngredient);
        }
    }

    private void addIngredientStock(Stock s, JSONObject jsonObject) {
        String name = jsonObject.getString("ingredient");
        int amount = jsonObject.getInt("amount");
        String unit = jsonObject.getString("unit");

        Ingredient ingredient = new Ingredient(name, amount, unit);
        s.addToStock(ingredient);
    }
}
