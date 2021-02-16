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
     * MODIFIES: this
     * EFFECTS: Removes the specified recipe from the list
     */
    public void removeRecipe(String name) {
        Boolean found = false;

        for (Recipe i : recipes) {
            if (name.equals(i.getRecipeName())) {
                recipes.remove(i);
                found = true;
            }
        }
        if (!found) {
            System.out.println("The specified recipe was not found.");
        }
    }

    /*
     * EFFECTS: Displays the name of all the recipes and a unique message if there are no recipes in the list
     */
    public void viewRecipeNames() {
        if (recipes.isEmpty()) {
            System.out.println("You do not currently have any drinks on the menu.");
        } else {
            for (Recipe i : recipes) {
                System.out.println(i.getRecipeName());
            }
        }
    }

    /*
     * EFFECTS: Displays the full recipe that matches the name of the search term entered
     */
    public void recipeDetails(String name) {
        boolean found = false;
        if (recipes.isEmpty()) {
            System.out.println("You do not currently have any drinks on the menu.");
        } else {
            for (Recipe i : recipes) {
                if (i.getRecipeName().equals(name)) {
                    i.displayRecipe();
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("Sorry, the cocktail you entered doesn't have a recipe for it yet.");
        }
    }

    /*
     * EFFECTS: Finds and returns a specific recipe, and null if not found
     */
    public Recipe findRecipe(String name) {
        boolean found = false;

        if (recipes.isEmpty()) {
            System.out.println("You do not currently have any drinks on the menu.");
        } else {
            for (Recipe i : recipes) {
                if (i.getRecipeName().equals(name)) {
                    return i;
                }
            }
        }
        System.out.println("Sorry, the cocktail you entered doesn't have a recipe for it yet.");
        return null;
    }

    /*
     * EFFECTS: Displays the name of all recipes that can be made with your current stock
     */
    public void filterRecipesByStock(Stock s) {
        ArrayList<Recipe> filtered = new ArrayList<>();
        for (Recipe i : recipes) {
            if (i.ingredientsInStock(s)) {
                filtered.add(i);
            }
        }
        if (filtered.isEmpty()) {
            System.out.println("Sorry, you don't have ingredients to make any of the cocktails in our list.");
        } else {
            for (Recipe i : filtered) {
                System.out.println(i.getRecipeName());
            }
        }
    }
}
