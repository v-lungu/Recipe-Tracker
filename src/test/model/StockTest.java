package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StockTest {
    private Stock testStock;
    private Ingredient testIA = new Ingredient("juice", 5, "mL");
    private Ingredient testIB = new Ingredient("cherry", 1, "g");
    private Ingredient testIC = new Ingredient("orange juice", 50, "mL");

    @BeforeEach
    void runBefore(){
        testStock = new Stock();
    }

    @Test
    void testAddOneToStock() {
        assertEquals(0, testStock.getStock().size());
        testStock.addToStock(testIA);
        assertEquals(1, testStock.getStock().size());
        assertEquals(testIA.getIngredient(), testStock.getStock().get(0).getIngredient());
    }

    @Test
    void testAddSeveralToStock() {
        assertEquals(0, testStock.getStock().size());
        testStock.addToStock(testIA);
        testStock.addToStock(testIB);
        testStock.addToStock(testIC);
        assertEquals(3, testStock.getStock().size());
        assertEquals(testIA.getIngredient(), testStock.getStock().get(0).getIngredient());
        assertEquals(testIB.getIngredient(), testStock.getStock().get(1).getIngredient());
        assertEquals(testIC.getIngredient(), testStock.getStock().get(2).getIngredient());
    }

    @Test
    void testRemoveFound() {
        assertEquals(0, testStock.getStock().size());
        testStock.addToStock(testIA);
        testStock.addToStock(testIB);
        testStock.addToStock(testIC);
        assertEquals(3, testStock.getStock().size());
        testStock.removeFromStock("cherry");
        assertEquals(2, testStock.getStock().size());
        assertEquals(testIA.getIngredient(), testStock.getStock().get(0).getIngredient());
        assertEquals(testIC.getIngredient(), testStock.getStock().get(1).getIngredient());
    }

    @Test
    void testRemoveNotFound() {
        assertEquals(0, testStock.getStock().size());
        testStock.addToStock(testIA);
        testStock.addToStock(testIB);
        testStock.addToStock(testIC);
        assertEquals(3, testStock.getStock().size());
        testStock.removeFromStock("steak");
        assertEquals(3, testStock.getStock().size());
        assertEquals(testIA.getIngredient(), testStock.getStock().get(0).getIngredient());
        assertEquals(testIB.getIngredient(), testStock.getStock().get(1).getIngredient());
        assertEquals(testIC.getIngredient(), testStock.getStock().get(2).getIngredient());
    }

    @Test
    void testEditFound() {
        assertEquals(0, testStock.getStock().size());
        testStock.addToStock(testIA);
        testStock.addToStock(testIB);
        testStock.addToStock(testIC);
        assertEquals(3, testStock.getStock().size());
        testStock.editStock("cherry", 15, "mL");
        assertEquals(3, testStock.getStock().size());
        assertEquals("cherry", testStock.getStock().get(1).getName());
        assertEquals(15, testStock.getStock().get(1).getAmount());
        assertEquals("mL", testStock.getStock().get(1).getUnit());
    }

    @Test
    void testEditNotFound() {
        assertEquals(0, testStock.getStock().size());
        testStock.addToStock(testIA);
        testStock.addToStock(testIB);
        testStock.addToStock(testIC);
        assertEquals(3, testStock.getStock().size());
        testStock.editStock("steak", 15, "mL");
        assertEquals(3, testStock.getStock().size());
        assertEquals(testIA.getIngredient(), testStock.getStock().get(0).getIngredient());
        assertEquals(testIB.getIngredient(), testStock.getStock().get(1).getIngredient());
        assertEquals(testIC.getIngredient(), testStock.getStock().get(2).getIngredient());
    }
}
