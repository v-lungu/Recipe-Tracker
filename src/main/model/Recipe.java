package model;

import java.util.ArrayList;

public class Recipe {
    private ArrayList<Ingredient> ingredientList;
    private ArrayList<String> instructions;

    public Recipe() {
        ingredientList = new ArrayList<>();
        instructions = new ArrayList<>();
    }

    public void addIngredient(Ingredient i) {
        ingredientList.add(i);
    }

    public void addInstructionMultiple(String step) {
        instructions.add(step);
    }
}
