package model;

import java.util.ArrayList;

public class Recipe {
    private ArrayList<Ingredient> ingredientList;
    private ArrayList<String> instructions;

    /*
     * EFFECTS: Creates a new recipe with a list for ingredients and a list of instructions
     */
    public Recipe() {
        ingredientList = new ArrayList<>();
        instructions = new ArrayList<>();
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
}
