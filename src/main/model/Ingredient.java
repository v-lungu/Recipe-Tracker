package model;

// Represents an ingredient and the amount in units
public class Ingredient {
    private String ingredientName;
    private String ingredientUnit;
    private int ingredientAmount;

    /*
     * REQUIRES: name not empty, valid unit of measurement for unit (g, mL, or part), and positive amount
     * EFFECTS: Creates an ingredient item with an amount in a specified unit of measurement
     */
    public Ingredient(String name, int amount, String unit) {
        ingredientName = name;
        ingredientUnit = unit;
        ingredientAmount = amount;
    }

    /*
     * EFFECTS: Return the name of the ingredient
     */
    public String getName() {
        return ingredientName;
    }

    /*
     * EFFECTS: Return the amount of the ingredient
     */
    public int getAmount() {
        return ingredientAmount;
    }

    /*
     * EFFECTS: Return the unit of the ingredient
     */
    public String getUnit() {
        return ingredientUnit;
    }

    /*
     * EFFECTS: Returns a String of the amount, unit, and name of the ingredient
     */
    public String getIngredient() {
        return ingredientAmount + ingredientUnit + "   " + ingredientName;
    }

    /*
     * REQUIRES: Valid unit of measurement for unit, and positive amount
     * MODIFIES: this
     * EFFECTS: Edits the ingredient amount and unit of measurement
     */
    public void editIngredient(int amount, String unit) {
        ingredientUnit = unit;
        ingredientAmount = amount;
    }

    /*
     * EFFECTS: Returns true if the ingredient is found within the stock
     */
    public boolean inStock(Stock s) {
        boolean found = false;
        for (Ingredient i : s.getStock()) {
            if (ingredientName.equals(i.getName())) {
                if (ingredientAmount <= i.getAmount()) {
                    found = true;
                    break;
                }
            }
        }
        return found;
    }
}
