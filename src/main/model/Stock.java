package model;

import java.util.ArrayList;

//Represents the stock of ingredients in a house at any given time
public class Stock {
    private ArrayList<Ingredient> stockList;

    /*
     * EFFECTS: Creates a new stock of ingredients
     */
    public Stock() {
        stockList = new ArrayList<>();
    }

    /*
     * EFFECTS: returns the list of ingredients in the stock
     */
    public ArrayList<Ingredient> getStock() {
        return stockList;
    }

    /*
     * MODIFIES: this
     * EFFECTS: Adds a new ingredient to the stock list
     */
    public void addToStock(Ingredient i) {
        stockList.add(i);
    }

    /*
     * MODIFIES: this
     * EFFECTS: If the name of an ingredient matches the requested removal, removes the ingredient from the list
     */
    public void removeFromStock(String name) {
        boolean checkIfFound = false;

        for (Ingredient i : stockList) {
            if (i.getName().equals(name)) {
                stockList.remove(i);
                checkIfFound = true;
                System.out.println("The ingredient has been removed from the stock list.");
                break;
            }
        }

        if (!checkIfFound) {
            System.out.println("The ingredient was not found in the stock list.");
        }
    }

    /*
     * REQUIRES: Valid unit of measurement, and positive amount
     * MODIFIES: this
     * EFFECTS: If the name of an ingredient matches the input will edit the stock item with new amount and unit
     */
    public void editStock(String name, int amount, String unit) {
        boolean checkIfFound = false;

        for (Ingredient i : stockList) {
            if (i.getName().equals(name)) {
                checkIfFound = true;
                i.editIngredient(amount, unit);
                System.out.println("The ingredient has been properly changed to:");
                i.getIngredient();
            }
        }

        if (!checkIfFound) {
            System.out.println("The ingredient was not found in the stock list.");
        }
    }

    /*
     * EFFECTS: Displays all ingredients in the stock list
     */
    public void displayStock() {
        for (Ingredient i : stockList) {
            System.out.println(i.getIngredient());
        }
    }
}
