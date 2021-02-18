package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {
    private Ingredient testIngredient;

    private Ingredient testNotStockIngredient = new Ingredient("bitters", 5, "mL");

    private Ingredient stockIngredientA = new Ingredient("cherry", 1, "g");
    private Ingredient stockIngredientB = new Ingredient("vodka", 50, "mL");
    private Stock testStock = new Stock();



    @BeforeEach
    void runBefore() {
        testIngredient = new Ingredient("vodka", 25, "mL");

        testStock.addToStock(stockIngredientA);
        testStock.addToStock(stockIngredientB);
    }

    @Test
    void testGetIngredient() {
        assertEquals("25mL   vodka", testIngredient.getIngredient());
    }

    @Test
    void testEditIngredient() {
        testIngredient.editIngredient(10, "g");
        assertEquals(10, testIngredient.getAmount());
        assertEquals("g", testIngredient.getUnit());
        assertEquals("vodka", testIngredient.getName());
    }

    @Test
    void testInStock() {
        assertTrue(testIngredient.inStock(testStock));
    }

    @Test
    void testInStockNotEnough() {
        stockIngredientB.editIngredient(5, "ml");
        assertFalse(testIngredient.inStock(testStock));
    }

    @Test
    void testNotInStock() {
        assertFalse(testNotStockIngredient.inStock(testStock));
    }
}