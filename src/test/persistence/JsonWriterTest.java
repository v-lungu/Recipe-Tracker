package persistence;

import model.Ingredient;
import model.Recipe;
import model.RecipeList;
import model.Stock;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {
    private Recipe recipeA = new Recipe("pina colada");
    private Recipe recipeB = new Recipe("moscow mule");
    private Recipe recipeC = new Recipe("whiskey sour");
    private Recipe recipeD = new Recipe("bloody mary");

    private Ingredient ingredientA = new Ingredient("cherry", 15, "mL");
    private Ingredient ingredientB = new Ingredient("vodka", 50, "mL");
    private Ingredient ingredientC = new Ingredient("ice", 18, "g");
    private Ingredient ingredientD = new Ingredient("rum", 25, "mL");


    @Test
    void testWriterInvalidFile() {
        try {
            RecipeList rl = new RecipeList();
            Stock s = new Stock();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyRecipesAndStock() {
        try {
            RecipeList rl = new RecipeList();
            Stock s = new Stock();

            JsonWriter writer = new JsonWriter("./data/testWriterEmpty.json");
            writer.open();
            writer.write(rl, s);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmpty.json");
            rl = reader.readRecipeList();
            s = reader.readStock();
            assertEquals(0, rl.getRecipes().size());
            assertEquals(0, s.getStock().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralRecipe() {
        try {
            RecipeList rl = new RecipeList();
            Stock s = new Stock();

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

            rl.addRecipe(recipeA);
            rl.addRecipe(recipeB);
            rl.addRecipe(recipeC);
            rl.addRecipe(recipeD);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralRecipeList.json");
            writer.open();
            writer.write(rl, s);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralRecipeList.json");
            rl = reader.readRecipeList();
            s = reader.readStock();
            assertEquals(4, rl.getRecipes().size());
            assertEquals(0, s.getStock().size());

            checkRecipe(recipeA.getRecipeInstructions(), recipeA.getRecipeIngredients(), rl.getRecipes().get(0));
            checkRecipe(recipeB.getRecipeInstructions(), recipeB.getRecipeIngredients(), rl.getRecipes().get(1));
            checkRecipe(recipeC.getRecipeInstructions(), recipeC.getRecipeIngredients(), rl.getRecipes().get(2));
            checkRecipe(recipeD.getRecipeInstructions(), recipeD.getRecipeIngredients(), rl.getRecipes().get(3));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralStock() {
        try {
            RecipeList rl = new RecipeList();
            Stock s = new Stock();

            s.addToStock(ingredientA);
            s.addToStock(ingredientB);
            s.addToStock(ingredientC);
            s.addToStock(ingredientD);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralStock.json");
            writer.open();
            writer.write(rl, s);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralStock.json");
            rl = reader.readRecipeList();
            s = reader.readStock();
            assertEquals(0, rl.getRecipes().size());
            assertEquals(4, s.getStock().size());

            checkIngredient(ingredientA.getName(), ingredientA.getAmount(), ingredientA.getUnit(), s.getStock().get(0));
            checkIngredient(ingredientB.getName(), ingredientB.getAmount(), ingredientB.getUnit(), s.getStock().get(1));
            checkIngredient(ingredientC.getName(), ingredientC.getAmount(), ingredientC.getUnit(), s.getStock().get(2));
            checkIngredient(ingredientD.getName(), ingredientD.getAmount(), ingredientD.getUnit(), s.getStock().get(3));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneral() {
        try {
            RecipeList rl = new RecipeList();
            Stock s = new Stock();

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

            rl.addRecipe(recipeA);
            rl.addRecipe(recipeB);
            rl.addRecipe(recipeC);
            rl.addRecipe(recipeD);

            s.addToStock(ingredientA);
            s.addToStock(ingredientB);
            s.addToStock(ingredientC);
            s.addToStock(ingredientD);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneral.json");
            writer.open();
            writer.write(rl, s);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneral.json");
            rl = reader.readRecipeList();
            s = reader.readStock();
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

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
