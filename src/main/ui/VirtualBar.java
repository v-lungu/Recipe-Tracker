package ui;

import model.Recipe;
import model.RecipeList;
import model.Stock;
import model.Ingredient;

import java.util.Scanner;

public class VirtualBar {
    private RecipeList recipes;
    private Stock stock;
    private Scanner input;

    // EFFECTS: runs the Virtual Bar application
    public VirtualBar() {
        init();
        runBar();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runBar() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("e")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("s")) {
            runStock();
        } else if (command.equals("r")) {
            runRecipe();
        } else {
            System.out.println("Selection not valid, please try again.");
            runBar();
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes stock and list of recipes
    private void init() {
        recipes = new RecipeList();
        stock = new Stock();
        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tr -> recipes");
        System.out.println("\ts -> stock");
        System.out.println("\te -> exit");
    }

    // MODIFIES: this
    // EFFECTS: processes user input in the recipe menu
    private void runRecipe() {
        String command;
        displayRecipeMenu();

        command = input.next();
        command = command.toLowerCase();

        if (command.equals("b")) {
            runBar();
        } else {
            processRecipeCommand(command);
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command in the recipe menu
    private void processRecipeCommand(String command) {
        if (command.equals("a")) {
            addRecipe();
        } else if (command.equals("e")) {
            editRecipe();
        } else if (command.equals("r")) {
            removeRecipe();
        } else if (command.equals("v")) {
            viewRecipeList();
        } else if (command.equals("f")) {
            filterByStock();
        } else if (command.equals("d")) {
            viewRecipe();
        } else {
            System.out.println("Selection not valid, please try again!");
            runRecipe();
        }
    }

    // EFFECTS: displays menu of recipe options to user
    private void displayRecipeMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add");
        System.out.println("\te -> edit");
        System.out.println("\tr -> remove");
        System.out.println("\tv -> view list");
        System.out.println("\tf -> filter by stock");
        System.out.println("\td -> view recipe");
        System.out.println("\tb -> back");
    }

    private void addRecipe() {
        String name;

        System.out.println("What is the name of the cocktail?");
        name = input.next();
        Recipe add = new Recipe(name);

        addIngredients(add);
        addInstructions(add);

        recipes.addRecipe(add);

        runRecipe();
    }

    private void addIngredients(Recipe add) {
        boolean addIngredient = true;
        String command;

        while (addIngredient) {
            System.out.println("Add an ingredient? (y/n): ");
            command = input.next();

            if (command.equals("y")) {
                System.out.println("What is the ingredient, unit of measurement (g/ml) and amount (positive integer)?");
                String ingredient = input.next();
                String unit = input.next();
                int amount = Integer.parseInt(input.next());
                Ingredient x = new Ingredient(ingredient, amount, unit);
                add.addIngredient(x);
            } else if (command.equals("n")) {
                addIngredient = false;
            } else {
                System.out.println("Please enter a valid input");
                addIngredients(add);
            }
        }
    }

    private void addInstructions(Recipe add) {
        boolean addInstruction = true;
        String command;
        String step;

        while (addInstruction) {
            System.out.println("Add a step? (y/n): ");
            command = input.next();

            if (command.equals("y")) {
                System.out.println("Please type out the step: ");
                step = input.next();
                add.addInstruction(step);
            } else if (command.equals("n")) {
                addInstruction = false;
            } else {
                System.out.println("Please enter a valid input");
                addInstructions(add);
            }
        }
    }

    private void editRecipe() {
        String command;
        Recipe x;

        System.out.println("What is the name of the recipe you'd like to edit?");
        command = input.next();

        x = recipes.findRecipe(command);
        if (x.equals(null)) {
            runBar();
        } else {
            System.out.println("What would you like to edit: i = ingredients, s = instructions");
            command = input.next();
            if (command.equals("i")) {
                editIngredients(x);
            } else if (command.equals("s")) {
                System.out.println("Please rewrite the steps.");
                addInstructions(x);
            }
        }
        runRecipe();
    }

    private void editIngredients(Recipe x) {
        String name;
        String command;

        int amount;
        String unit;

        System.out.println("What is the name of the ingredient you are changing?");
        name = input.next();

        for (Ingredient i : x.getRecipeIngredients()) {
            if (name.equals(i.getName())) {
                System.out.println("Would you like to edit or remove this ingredient? (e/r)");
                command = input.next();
                if (command.equals("e")) {
                    System.out.println("What is the new amount and unit of this ingredient?");
                    amount = Integer.parseInt(input.next());
                    unit = input.next();
                    i.editIngredient(amount, unit);
                } else {
                    x.removeIngredient(name);
                }
            }
        }
        runRecipe();
    }

    private void removeRecipe() {
        String name;

        System.out.println("What is the name of the recipe you would like to remove?");
        name = input.next();
        recipes.removeRecipe(name);

        runBar();
    }

    private void viewRecipeList() {
        recipes.viewRecipeNames();
        runBar();
    }

    private void filterByStock() {
        recipes.filterRecipesByStock(stock);
    }

    private void viewRecipe() {
        String name;

        System.out.println("What is the name of the cocktail you'd like to see?");
        name = input.next();

        recipes.recipeDetails(name);

        runRecipe();
    }

    // MODIFIES: this
    // EFFECTS: processes user input in the stock menu
    private void runStock() {
        String command;
        displayStockMenu();

        command = input.next();
        command = command.toLowerCase();

        if (command.equals("b")) {
            runBar();
        } else {
            processStockCommand(command);
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command in the recipe menu
    private void processStockCommand(String command) {
        if (command.equals("a")) {
            addStock();
        } else if (command.equals("e")) {
            editStock();
        } else if (command.equals("v")) {
            viewStock();
        } else {
            System.out.println("Selection not valid, please try again!");
            runStock();
        }
    }

    // EFFECTS: displays menu of recipe options to user
    private void displayStockMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add");
        System.out.println("\te -> edit");
        System.out.println("\tv -> view list");
        System.out.println("\tb -> back");
    }

    private void addStock() {
        boolean addIngredient = true;
        String command;

        String ingredient;
        String unit;
        int amount;

        Ingredient x;

        while (addIngredient) {
            System.out.println("Add an ingredient? (y/n): ");
            command = input.next();

            if (command.equals("y")) {
                System.out.println("What is the ingredient, unit of measurement (g/ml) and amount (positive integer)?");
                ingredient = input.next();
                unit = input.next();
                amount = Integer.parseInt(input.next());
                x = new Ingredient(ingredient, amount, unit);
                stock.addToStock(x);
            } else {
                addIngredient = false;
            }
        }
        runStock();
    }

    private void editStock() {
        String name;
        String command;

        int amount;
        String unit;

        System.out.println("What is the name of the ingredient you are changing?");
        name = input.next();

        for (Ingredient i : stock.getStock()) {
            if (name.equals(i.getName())) {
                System.out.println("Would you like to edit or remove this ingredient? (e/r)");
                command = input.next();
                if (command.equals("e")) {
                    System.out.println("What is the new amount and unit of this ingredient?");
                    amount = Integer.parseInt(input.next());
                    unit = input.next();
                    i.editIngredient(amount, unit);
                } else {
                    stock.removeFromStock(name);
                }
            }
        }
        runStock();
    }

    private void viewStock() {
        stock.displayStock();
        runStock();
    }
}

