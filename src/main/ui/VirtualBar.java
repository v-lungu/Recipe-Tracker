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
    private Scanner inputLine;

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
        inputLine = new Scanner(System.in);
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

    // MODIFIES: this
    // EFFECTS: Adds a recipe to the list
    private void addRecipe() {
        String name;

        System.out.println("What is the name of the cocktail?");
        name = inputLine.nextLine();
        Recipe add = new Recipe(name);

        addIngredients(add);
        addInstructions(add);

        recipes.addRecipe(add);

        runRecipe();
    }

    // MODIFIES: this
    // EFFECTS: Adds an ingredient to the specified recipe
    private void addIngredients(Recipe add) {
        boolean addIngredient = true;
        String command;

        while (addIngredient) {
            System.out.println("Add an ingredient? (y/n): ");
            command = input.next();

            if (command.equals("y")) {
                System.out.println("What is the ingredient, and amount (positive integer) unit of measurement (g/ml)?");
                String ingredient = inputLine.nextLine();
                int amount = Integer.parseInt(input.next());
                String unit = input.next();
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

    // MODIFIES: this
    // EFFECTS: Adds an instruction to the specified recipe
    private void addInstructions(Recipe add) {
        boolean addInstruction = true;
        String command;
        String step;

        while (addInstruction) {
            System.out.println("Add a step? (y/n): ");
            command = input.next();

            if (command.equals("y")) {
                System.out.println("Please type out the step: ");
                step = inputLine.nextLine();
                add.addInstruction(step);
            } else if (command.equals("n")) {
                addInstruction = false;
            } else {
                System.out.println("Please enter a valid input");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Finds the specified recipe and allows the user to edit the ingredients and instructions
    private void editRecipe() {
        String command;
        Recipe toBeChanged;

        System.out.println("What is the name of the recipe you'd like to edit?");
        command = inputLine.nextLine();

        if (recipes.findRecipe(command)) {
            toBeChanged = recipes.getRecipe(command);
            System.out.println("What would you like to edit: i = ingredients, s = instructions");
            command = input.next();
            if (command.equals("i")) {
                editIngredients(toBeChanged);
            } else if (command.equals("s")) {
                System.out.println("Please specify which step you would like to change: ");
                editInstructions(toBeChanged);
            } else {
                System.out.println("Please enter a valid input:");
                editRecipe();
            }
        }
        runRecipe();
    }

    // MODIFIES: this
    // EFFECTS: Allows the user to add an ingredient or edit existing ingredients
    private void editIngredients(Recipe x) {
        String name;
        String command;

        int amount;
        String unit;

        System.out.println("Would you like to edit or remove an ingredient? (e/r)");
        command = input.next();

        System.out.println("What is the name of the ingredient you are changing?");
        name = inputLine.nextLine();

        if (command.equals("e")) {
            System.out.println("What is the new amount and unit of this ingredient?");
            amount = Integer.parseInt(input.next());
            unit = input.next();
            x.editIngredient(name, amount, unit);
        } else if (command.equals("r")) {
            x.removeIngredient(name);
        } else {
            System.out.println("Please enter a valid input");
            editIngredients(x);
        }

        runRecipe();
    }

    // MODIFIES: this
    // EFFECTS: Allows the user to edit the instructions
    private void editInstructions(Recipe x) {
        int step = 0;
        String change;
        boolean done = false;

        System.out.println("Please specify which step of the recipe you'd like to change: ");
        while (!done) {
            try {
                step = Integer.parseInt(input.next());
                done = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
                editInstructions(x);
            }
        }
        System.out.println("What would you like the new instruction to be?");
        change = inputLine.nextLine();

        x.editStep(step, change);
        runRecipe();
    }

    // MODIFIES: this
    // EFFECTS: Allows the user to remove a recipe from the list
    private void removeRecipe() {
        String name;

        System.out.println("What is the name of the recipe you would like to remove?");
        name = inputLine.nextLine();
        recipes.removeRecipe(name);

        runBar();
    }

    // MODIFIES: this
    // EFFECTS: Allows the user to view the list of recipes
    private void viewRecipeList() {
        if (recipes.getRecipes().isEmpty()) {
            System.out.println("You do not currently have any drinks on the menu.");
        } else {
            for (Recipe i : recipes.getRecipes()) {
                System.out.println(i.getRecipeName());
            }
        }
        runRecipe();
    }

    // MODIFIES: this
    // EFFECTS: Shows list of recipes filtered by what is currently stocked
    private void filterByStock() {
        RecipeList filtered = recipes.filterRecipesByStock(stock);

        if (filtered.getRecipes().isEmpty()) {
            System.out.println("You do not currently have any drinks on the menu.");
        } else {
            for (Recipe i : filtered.getRecipes()) {
                System.out.println(i.getRecipeName());
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Shows the details of the specified recipe
    private void viewRecipe() {
        String name;
        int step = 1;
        Recipe display;

        System.out.println("What is the name of the cocktail you'd like to see?");
        name = inputLine.nextLine();

        if (recipes.findRecipe(name)) {
            display = recipes.getRecipe(name);

            System.out.println(display.getRecipeName());
            System.out.println("Ingredients");
            for (Ingredient i : display.getRecipeIngredients()) {
                System.out.println(i.getIngredient());
            }

            for (String i : display.getRecipeInstructions()) {
                System.out.println(step + ". " + i);
                step++;
            }
        }
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

    // MODIFIES: this
    // EFFECTS: Adds to the stock
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
                System.out.println("What is the ingredient, amount (positive integer), and unit of measurement?");
                ingredient = inputLine.nextLine();
                amount = Integer.parseInt(input.next());
                unit = input.next();
                x = new Ingredient(ingredient, amount, unit);
                stock.addToStock(x);
            } else {
                addIngredient = false;
            }
        }
        runStock();
    }

    // MODIFIES: this
    // EFFECTS: Allows editing of the existing stock
    private void editStock() {
        String name;
        String command;

        int amount;
        String unit;

        System.out.println("What is the name of the ingredient you are changing?");
        name = inputLine.nextLine();

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
                    break;
                }
            }
        }
        runStock();
    }

    // MODIFIES: this
    // EFFECTS: Displays a list of the entire stock
    private void viewStock() {
        for (Ingredient i : stock.getStock()) {
            System.out.println(i.getIngredient());
        }
        runStock();
    }
}

