package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeTest {
    Recipe testRecipe;

    private Ingredient testIA = new Ingredient("vodka", 50, "mL");
    private Ingredient testIB = new Ingredient("cherry", 15, "g");
    private Ingredient testIC = new Ingredient("orange juice", 100, "mL");
    private Ingredient testID = new Ingredient("avocado", 15, "g");

    private String testSA = "Add to a glass.";
    private String testSB = "Add ice.";
    private String testSC = "Stir vigorously.";

    private Ingredient stockIngredientA = new Ingredient("cherry", 100, "g");
    private Ingredient stockIngredientB = new Ingredient("vodka", 100, "mL");
    private Ingredient StockIngredientC = new Ingredient("orange juice", 100, "mL");
    private Ingredient StockIngredientD = new Ingredient("bitters", 15, "mL");
    private Stock testStock = new Stock();

    @BeforeEach
    void runBefore() {
        testRecipe = new Recipe("pina colada");

        testStock.addToStock(stockIngredientA);
        testStock.addToStock(stockIngredientB);
        testStock.addToStock(StockIngredientC);
        testStock.addToStock(StockIngredientD);
    }

    @Test
    void testAddIngredient() {
        assertEquals(0, testRecipe.getRecipeIngredients().size());
        testRecipe.addIngredient(testIA);
        assertEquals(1, testRecipe.getRecipeIngredients().size());
        assertEquals(testIA, testRecipe.getRecipeIngredients().get(0));
    }

    @Test
    void testAddSeveralIngredient() {
        assertEquals(0, testRecipe.getRecipeIngredients().size());
        testRecipe.addIngredient(testIA);
        testRecipe.addIngredient(testIB);
        testRecipe.addIngredient(testIC);
        assertEquals(3, testRecipe.getRecipeIngredients().size());
        assertEquals(testIA, testRecipe.getRecipeIngredients().get(0));
        assertEquals(testIB, testRecipe.getRecipeIngredients().get(1));
        assertEquals(testIC, testRecipe.getRecipeIngredients().get(2));
    }

    @Test
    void testAddStep() {
        assertEquals(0, testRecipe.getRecipeInstructions().size());
        testRecipe.addInstruction(testSA);
        assertEquals(1, testRecipe.getRecipeInstructions().size());
        assertEquals(testSA, testRecipe.getRecipeInstructions().get(0));
    }

    @Test
    void testAddSeveralStep() {
        assertEquals(0, testRecipe.getRecipeInstructions().size());
        testRecipe.addInstruction(testSA);
        testRecipe.addInstruction(testSB);
        testRecipe.addInstruction(testSC);
        assertEquals(3, testRecipe.getRecipeInstructions().size());
        assertEquals(testSA, testRecipe.getRecipeInstructions().get(0));
        assertEquals(testSB, testRecipe.getRecipeInstructions().get(1));
        assertEquals(testSC, testRecipe.getRecipeInstructions().get(2));
    }

    @Test
    void removeIngredientFound() {
        assertEquals(0, testRecipe.getRecipeIngredients().size());
        testRecipe.addIngredient(testIA);
        testRecipe.addIngredient(testIB);
        testRecipe.addIngredient(testIC);
        assertEquals(3, testRecipe.getRecipeIngredients().size());
        testRecipe.removeIngredient("cherry");
        assertEquals(2, testRecipe.getRecipeIngredients().size());
    }

    @Test
    void removeIngredientNotFound() {
        assertEquals(0, testRecipe.getRecipeIngredients().size());
        testRecipe.addIngredient(testIA);
        testRecipe.addIngredient(testIB);
        testRecipe.addIngredient(testIC);
        assertEquals(3, testRecipe.getRecipeIngredients().size());
        testRecipe.removeIngredient("steak");
        assertEquals(3, testRecipe.getRecipeIngredients().size());
    }

    @Test
    void testEditIngredientFound() {
        assertEquals(0, testRecipe.getRecipeIngredients().size());
        testRecipe.addIngredient(testIA);
        testRecipe.addIngredient(testIB);
        testRecipe.addIngredient(testIC);
        assertEquals(3, testRecipe.getRecipeIngredients().size());
        testRecipe.editIngredient("cherry", 5, "mL");
        assertEquals(3, testRecipe.getRecipeIngredients().size());
        assertEquals("cherry", testRecipe.getRecipeIngredients().get(1).getName());
        assertEquals(5, testRecipe.getRecipeIngredients().get(1).getAmount());
        assertEquals("mL", testRecipe.getRecipeIngredients().get(1).getUnit());
    }

    @Test
    void testEditIngredientNotFound() {
        assertEquals(0, testRecipe.getRecipeIngredients().size());
        testRecipe.addIngredient(testIA);
        testRecipe.addIngredient(testIB);
        testRecipe.addIngredient(testIC);
        assertEquals(3, testRecipe.getRecipeIngredients().size());
        testRecipe.editIngredient("macaroni", 5, "mL");
        assertEquals(3, testRecipe.getRecipeIngredients().size());
        assertEquals(testIA, testRecipe.getRecipeIngredients().get(0));
        assertEquals(testIB, testRecipe.getRecipeIngredients().get(1));
        assertEquals(testIC, testRecipe.getRecipeIngredients().get(2));
    }

    @Test
    void testEditStep() {
        assertEquals(0, testRecipe.getRecipeInstructions().size());
        testRecipe.addInstruction(testSA);
        testRecipe.addInstruction(testSB);
        testRecipe.addInstruction(testSC);
        assertEquals(3, testRecipe.getRecipeInstructions().size());
        testRecipe.editStep(2, "Throw it in the trash.");
        assertEquals(3, testRecipe.getRecipeInstructions().size());
        assertEquals("Throw it in the trash.", testRecipe.getRecipeInstructions().get(1));
    }

    @Test
    void testEditStepTooBig() {
        assertEquals(0, testRecipe.getRecipeInstructions().size());
        testRecipe.addInstruction(testSA);
        testRecipe.addInstruction(testSB);
        testRecipe.addInstruction(testSC);
        assertEquals(3, testRecipe.getRecipeInstructions().size());
        testRecipe.editStep(5, "Throw it in the trash.");
        assertEquals(3, testRecipe.getRecipeInstructions().size());
        assertEquals(testSA, testRecipe.getRecipeInstructions().get(0));
        assertEquals(testSB, testRecipe.getRecipeInstructions().get(1));
        assertEquals(testSC, testRecipe.getRecipeInstructions().get(2));
    }

    @Test
    void testIngredientsAllInStock() {
        testRecipe.addIngredient(testIA);
        testRecipe.addIngredient(testIB);
        testRecipe.addIngredient(testIC);
        assertTrue(testRecipe.ingredientsInStock(testStock));

    }

    @Test
    void testIngredientsNotAllInStock() {
        testRecipe.addIngredient(testIA);
        testRecipe.addIngredient(testIB);
        testRecipe.addIngredient(testIC);
        testRecipe.addIngredient(testID);
        assertFalse(testRecipe.ingredientsInStock(testStock));

    }
}
