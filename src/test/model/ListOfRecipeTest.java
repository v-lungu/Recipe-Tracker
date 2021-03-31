package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfRecipeTest {
    private RecipeList testRecipeList;

    private Recipe recipeA;
    private Recipe recipeB;
    private Recipe recipeC;
    private Recipe recipeD;

    private Ingredient ingredientA;
    private Ingredient ingredientB;
    private Ingredient ingredientC;

    private Stock testStock;

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

            testStock = new Stock();

            testRecipeList = new RecipeList();
            testRecipeList.addRecipe(recipeA);
            testRecipeList.addRecipe(recipeB);
            testRecipeList.addRecipe(recipeC);
        } catch (Exception e) {
            fail("nothing should fail");
        }
    }

    @Test
    void testAddRecipe() {
        assertEquals(3, testRecipeList.getRecipes().size());
        testRecipeList.addRecipe(recipeD);
        assertEquals(4, testRecipeList.getRecipes().size());
    }

    @Test
    void testGetRecipes() {
        assertEquals(3, testRecipeList.getRecipes().size());
        assertEquals(recipeA, testRecipeList.getRecipes().get(0));
        assertEquals(recipeB, testRecipeList.getRecipes().get(1));
        assertEquals(recipeC, testRecipeList.getRecipes().get(2));
    }

    @Test
    void testFindRecipeFound() {
        assertTrue(testRecipeList.findRecipe("moscow mule"));
    }

    @Test
    void testFindRecipeNotFound() {
        assertFalse(testRecipeList.findRecipe("carrot"));
    }

    @Test
    void testGetRecipe() {
        assertEquals(recipeA, testRecipeList.getRecipe("pina colada"));
    }

    @Test
    void testRemoveRecipeFound() {
        assertEquals(3, testRecipeList.getRecipes().size());
        testRecipeList.removeRecipe("moscow mule");
        assertEquals(2, testRecipeList.getRecipes().size());
        assertEquals(recipeA, testRecipeList.getRecipes().get(0));
        assertEquals(recipeC, testRecipeList.getRecipes().get(1));
    }

    @Test
    void testRemoveRecipeNotFound() {
        assertEquals(3, testRecipeList.getRecipes().size());
        testRecipeList.removeRecipe("avocado");
        assertEquals(3, testRecipeList.getRecipes().size());
    }

    @Test
    void testFilterByStock() {
        try {
            testStock.addToStock(ingredientA);
            testStock.addToStock(ingredientC);

            recipeA.addIngredient(ingredientA);
            recipeA.addIngredient(ingredientB);
            recipeA.addIngredient(ingredientC);

            recipeB.addIngredient(ingredientA);

            recipeC.addIngredient(ingredientC);
            recipeC.addIngredient(ingredientA);

            RecipeList filtered = testRecipeList.filterRecipesByStock(testStock);

            assertEquals(2, filtered.getRecipes().size());
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }
}
