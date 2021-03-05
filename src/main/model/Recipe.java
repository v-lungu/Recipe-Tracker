package model;

import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;

import persistence.Writable;

// Represents a recipe with ingredients and a list of steps
public class Recipe implements Writable {
    private String cocktail;
    private ArrayList<Ingredient> ingredientList;
    private ArrayList<String> instructions;

    /*
     * REQUIRES: Non-empty name
     * EFFECTS: Creates a new cocktail recipe with a list for ingredients and a list of instructions
     */
    public Recipe(String name) {
        cocktail = name;
        ingredientList = new ArrayList<>();
        instructions = new ArrayList<>();
    }

    /*
     * EFFECTS: Return the name of the cocktail
     */
    public String getRecipeName() {
        return cocktail;
    }

    /*
     * EFFECTS: Return the list of ingredients
     */
    public ArrayList<Ingredient> getRecipeIngredients() {
        return ingredientList;
    }

    /*
     * EFFECTS: Return the list of steps
     */
    public ArrayList<String> getRecipeInstructions() {
        return instructions;
    }

    /*
     * REQUIRES: Ingredient has not been previously added to the list
     * MODIFIES: this
     * EFFECTS: Adds a new ingredient to the recipe
     */
    public void addIngredient(Ingredient i) {
        ingredientList.add(i);
    }

    /*
     * MODIFIES: this
     * EFFECTS: Adds a new step to the recipe
     */
    public void addInstruction(String step) {
        instructions.add(step);
    }

    /*
     * MODIFIES: this
     * EFFECTS: If found, deleted the specified ingredient from the recipe
     */
    public void removeIngredient(String name) {
        Boolean found = false;

        for (Ingredient i : ingredientList) {
            if (name.equals(i.getName())) {
                ingredientList.remove(i);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("That ingredient was not found, please try again.");
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: If found, edits the specified ingredient with the new request
     */
    public void editIngredient(String name, int amount, String unit) {
        Boolean found = false;

        for (Ingredient i : ingredientList) {
            if (name.equals(i.getName())) {
                i.editIngredient(amount, unit);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("That ingredient was not found, please try again.");
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: If found, edits the specified step with the new request
     */
    public void editStep(int step, String instruction) {
        if (step - 1 > instructions.size()) {
            System.out.println("That step specified does not exist, please try again.");
        } else {
            instructions.set(step - 1, instruction);
        }
    }

    /*
     * EFFECTS: Returns true if all ingredients used in the cocktail are found in the stock
     */
    public Boolean ingredientsInStock(Stock s) {
        boolean found = false;
        for (Ingredient i : ingredientList) {
            found = i.inStock(s);
            if (!found) {
                break;
            }
        }
        return found;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", cocktail);
        json.put("recipe-ingredients", ingredientsToJson());
        json.put("steps", instructionsToJson());
        return json;
    }

    private JSONArray ingredientsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Ingredient i : ingredientList) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }

    private JSONArray instructionsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String i : instructions) {
            jsonArray.put(i);
        }

        return jsonArray;
    }
}
