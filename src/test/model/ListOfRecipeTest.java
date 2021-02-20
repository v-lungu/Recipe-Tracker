package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfRecipeTest {
    private RecipeList testRecipeList;

    private Recipe recipeA = new Recipe("pina colada");
    private Recipe recipeB = new Recipe("moscow mule");
    private Recipe recipeC = new Recipe("whiskey sour");
    private Recipe recipeD = new Recipe("bloody mary");

    private Ingredient ingredientA = new Ingredient("cherry", 15, "mL");
    private Ingredient ingredientB = new Ingredient("vodka", 50, "mL");
    private Ingredient ingredientC = new Ingredient("ice", 18, "g");

    private Stock testStock = new Stock();

    @BeforeEach
    void runBefore() {
        testRecipeList = new RecipeList();
        testRecipeList.addRecipe(recipeA);
        testRecipeList.addRecipe(recipeB);
        testRecipeList.addRecipe(recipeC);
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
    }
}
