package persistence;

import model.Ingredient;
import model.Recipe;
import model.RecipeList;
import model.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {
    Recipe recipeA;
    Recipe recipeB;
    Recipe recipeC;
    Recipe recipeD;

    Ingredient ingredientA;
    Ingredient ingredientB;
    Ingredient ingredientC;
    Ingredient ingredientD;

    @BeforeEach
    void runBefore() {
        try {
            recipeA = new Recipe("pina colada");
            recipeB = new Recipe("moscow mule");
            recipeC = new Recipe("whiskey sour");
            recipeD = new Recipe("bloody mary");

            ingredientA = new Ingredient("cherry", 15, "mL");
            ingredientB = new Ingredient("vodka", 50, "mL");
            ingredientC = new Ingredient("ice", 18, "g");
            ingredientD = new Ingredient("rum", 25, "mL");
        } catch (Exception e) {
            fail("nothing should fail");
        }
        ;
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            RecipeList rl = reader.readRecipeList();
            Stock s = reader.readStock();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmpty.json");
        try {
            RecipeList rl = reader.readRecipeList();
            Stock s = reader.readStock();

            assertEquals(0, rl.getRecipes().size());
            assertEquals(0, s.getStock().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralRecipeList() {
        try {
            recipeA.addIngredient(ingredientA);
            recipeA.addIngredient(ingredientB);
            recipeA.addInstruction("cat");
            recipeA.addInstruction("dog");

            recipeB.addIngredient(ingredientC);
            recipeB.addInstruction("cat and dog");

            recipeC.addIngredient(ingredientA);
            recipeC.addIngredient(ingredientB);
            recipeC.addIngredient(ingredientC);
            recipeC.addIngredient(ingredientD);

            recipeD.addInstruction("cat");
            recipeD.addInstruction("dog");
            recipeD.addInstruction("pineapple");

            JsonReader reader = new JsonReader("./data/testReaderGeneralRecipeList.json");

            RecipeList rl = reader.readRecipeList();
            Stock s = reader.readStock();

            assertEquals(4, rl.getRecipes().size());
            assertEquals(0, s.getStock().size());

            checkRecipe(recipeA.getRecipeInstructions(), recipeA.getRecipeIngredients(), rl.getRecipes().get(0));
            checkRecipe(recipeB.getRecipeInstructions(), recipeB.getRecipeIngredients(), rl.getRecipes().get(1));
            checkRecipe(recipeC.getRecipeInstructions(), recipeC.getRecipeIngredients(), rl.getRecipes().get(2));
            checkRecipe(recipeD.getRecipeInstructions(), recipeD.getRecipeIngredients(), rl.getRecipes().get(3));
        } catch (Exception e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralStock() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralStock.json");
        try {
            RecipeList rl = reader.readRecipeList();
            Stock s = reader.readStock();

            assertEquals(0, rl.getRecipes().size());
            assertEquals(4, s.getStock().size());

            checkIngredient(ingredientA.getName(), ingredientA.getAmount(), ingredientA.getUnit(), s.getStock().get(0));
            checkIngredient(ingredientB.getName(), ingredientB.getAmount(), ingredientB.getUnit(), s.getStock().get(1));
            checkIngredient(ingredientC.getName(), ingredientC.getAmount(), ingredientC.getUnit(), s.getStock().get(2));
            checkIngredient(ingredientD.getName(), ingredientD.getAmount(), ingredientD.getUnit(), s.getStock().get(3));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneral() {
        try {
            recipeA.addIngredient(ingredientA);
            recipeA.addIngredient(ingredientB);
            recipeA.addInstruction("cat");
            recipeA.addInstruction("dog");

            recipeB.addIngredient(ingredientC);
            recipeB.addInstruction("cat and dog");

            recipeC.addIngredient(ingredientA);
            recipeC.addIngredient(ingredientB);
            recipeC.addIngredient(ingredientC);
            recipeC.addIngredient(ingredientD);

            recipeD.addInstruction("cat");
            recipeD.addInstruction("dog");
            recipeD.addInstruction("pineapple");

            JsonReader reader = new JsonReader("./data/testReaderGeneral.json");

            RecipeList rl = reader.readRecipeList();
            Stock s = reader.readStock();

            assertEquals(4, rl.getRecipes().size());
            assertEquals(4, s.getStock().size());

            checkRecipe(recipeA.getRecipeInstructions(), recipeA.getRecipeIngredients(), rl.getRecipes().get(0));
            checkRecipe(recipeB.getRecipeInstructions(), recipeB.getRecipeIngredients(), rl.getRecipes().get(1));
            checkRecipe(recipeC.getRecipeInstructions(), recipeC.getRecipeIngredients(), rl.getRecipes().get(2));
            checkRecipe(recipeD.getRecipeInstructions(), recipeD.getRecipeIngredients(), rl.getRecipes().get(3));

            checkIngredient(ingredientA.getName(), ingredientA.getAmount(), ingredientA.getUnit(), s.getStock().get(0));
            checkIngredient(ingredientB.getName(), ingredientB.getAmount(), ingredientB.getUnit(), s.getStock().get(1));
            checkIngredient(ingredientC.getName(), ingredientC.getAmount(), ingredientC.getUnit(), s.getStock().get(2));
            checkIngredient(ingredientD.getName(), ingredientD.getAmount(), ingredientD.getUnit(), s.getStock().get(3));
        } catch (Exception e) {
            fail("Couldn't read from file");
        }
    }

}
