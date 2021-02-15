package ui;

import model.RecipeList;
import model.Stock;

import java.util.Scanner;

public class VirtualBar {
    private RecipeList recipes;
    private Stock stock;
    private Scanner input;

    // EFFECTS: runs the Virtual Bar application
    public VirtualBar() {
        runBar();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runBar() {
        boolean keepGoing = true;
        String command = null;

        init();

        while(keepGoing) {
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
        String command = null;
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
            doDeposit();
        } else if (command.equals("e")) {
            doWithdrawal();
        } else if (command.equals("r")) {
            doTransfer();
        } else if (command.equals("v")) {
            doTransfer();
        } else if (command.equals("f")) {
            doTransfer();
        } else if (command.equals("d")) {
            doTransfer();
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
    // EFFECTS: processes user input in the stock menu
    private void runStock() {
        String command = null;
        displayRecipeMenu();

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
            doDeposit();
        } else if (command.equals("e")) {
            doWithdrawal();
        } else if (command.equals("r")) {
            doTransfer();
        } else if (command.equals("v")) {
            doTransfer();
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
        System.out.println("\tr -> remove");
        System.out.println("\tv -> view list");
        System.out.println("\tb -> back");
    }
}
