package persistence;

import model.Ingredient;
import model.Recipe;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkRecipe(ArrayList<String> steps, ArrayList<Ingredient> ingredients, Recipe recipe) {
        assertEquals(steps, recipe.getRecipeInstructions());
        int a = 0;
        for (Ingredient i : recipe.getRecipeIngredients()) {
            String name = ingredients.get(a).getName();
            int amount = ingredients.get(a).getAmount();
            String unit = ingredients.get(a).getUnit();

            checkIngredient(name, amount, unit, i);
            a++;
        }

    }

    protected void checkIngredient(String name, int amount, String unit, Ingredient ingredient) {
        assertEquals(name, ingredient.getName());
        assertEquals(amount, ingredient.getAmount());
        assertEquals(unit, ingredient.getUnit());
    }
}
