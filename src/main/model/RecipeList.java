package model;

import java.util.ArrayList;

public class RecipeList {
    private ArrayList<Recipe> recipes;

    /*
     * EFFECTS: Creates a new empty list of recipes
     */
    public RecipeList() {
        recipes = new ArrayList<>();
    }

    /*
     * REQUIRES: A fully formed recipe with name, ingredients, and instructions
     * MODIFIES: this
     * EFFECTS: Adds a recipe to the list
     */
    public void addRecipe(Recipe r) {
        recipes.add(r);
    }

    /*
     * EFFECTS: Return the list of recipes
     */
    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    /*
     * EFFECTS: Returns true if a specified recipe is found in the list
     */
    public boolean findRecipe(String name) {

        if (recipes.isEmpty()) {
            System.out.println("You do not currently have any drinks on the menu.");
            return false;
        } else {
            for (Recipe i : recipes) {
                if (i.getRecipeName().equals(name)) {
                    return true;
                }
            }
        }
        System.out.println("Sorry, the cocktail you entered doesn't have a recipe for it yet.");
        return false;
    }

    /*
     * REQUIRES: The recipe exists in the list
     * EFFECTS: Returns the specified recipe
     */
    public Recipe getRecipe(String name) {
        Recipe toBeReturned = null;

        for (Recipe i : recipes) {
            if (i.getRecipeName().equals(name)) {
                toBeReturned = i;
            }
        }
        return toBeReturned;
    }

    /*
     * MODIFIES: this
     * EFFECTS: Removes the specified recipe from the list
     */
    public void removeRecipe(String name) {
        if (findRecipe(name)) {
            for (Recipe i : recipes) {
                if (name.equals(i.getRecipeName())) {
                    recipes.remove(i);
                }
            }
        } else {
            System.out.println("Sorry, your recipe was not found.");
        }
    }

    /*
     * EFFECTS: Displays the name of all recipes that can be made with your current stock
     */
    public RecipeList filterRecipesByStock(Stock s) {
        RecipeList filtered = new RecipeList();
        for (Recipe i : recipes) {
            if (i.ingredientsInStock(s)) {
                filtered.addRecipe(i);
            }
        }
        return filtered;
    }
}
