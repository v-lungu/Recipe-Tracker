package model;

import java.util.ArrayList;

public class Recipe {
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
     * REQUIRES: A recipe with fully formed name, ingredients, and instructions
     * EFFECTS: Displays the recipe formatted for reading
     */
    public void displayRecipe() {
        System.out.println(cocktail);
        System.out.println("Ingredients");
        for (Ingredient i : ingredientList) {
            i.getIngredient();
        }
        int step = 1;
        for (String i : instructions) {
            System.out.println(step + ". " + i);
            step++;
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
}
