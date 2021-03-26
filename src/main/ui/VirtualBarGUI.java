package ui;

import model.Recipe;
import model.RecipeList;
import model.Stock;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import javax.swing.*;
import java.util.Set;

public class VirtualBarGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/testReaderGeneralRecipeList.json";
    private RecipeList recipes;
    private Stock stock;
    private Scanner input;
    private Scanner inputLine;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    private JButton recipesButton;
    private JButton stockButton;
    private JButton saveButton;
    private JButton loadButton;
    private Set<JButton> mainMenuButtons;

    private JButton addRecipeButton;
    private JButton editRecipeButton;
    private JButton removeRecipeButton;
    private JButton listRecipeButton;
    private JButton stockFilterButton;
    private JButton recipeButton;
    private JButton recipeBackButton;
    private Set<JButton> recipesButtons;

    private JButton addStockButton;
    private JButton editStockButton;
    private JButton viewStockButton;
    private JButton stockBackButton;
    private Set<JButton> stockButtons;

    private Set<JButton> backButtons;

    private JPanel menuContainer;
    private JPanel contentContainer;

    private JPanel mainMenu;
    private JPanel recipesMenu;
    private JPanel stockMenu;

    private JPanel mainContent;

    private JPanel addRecipe;
    private JPanel editRecipe;
    private JPanel removeRecipe;
    private JPanel displayAllRecipe;
    private JPanel displayByStock;
    private JPanel displayRecipe;

    private JPanel addStock;
    private JPanel editStock;
    private JPanel viewStock;

    CardLayout c1;
    CardLayout c2;

    JButton addStepButton;
    JButton addIngredientButton;
    JButton doneRecipeButton;
    private Set<JButton> contentButtons;

    JTextField ingredientText = new JTextField(20);
    JTextField recipeNameText;
    JTextField amountText;
    JTextField unitText;
    JTextField stepText;

    JLabel mainLabel;
    JLabel viewRecipes;
    JLabel viewRecipeTitle = new JLabel("<html> Cocktails on Menu <br/><br/> </html>");
    JLabel viewFilteredRecipes;
    JLabel viewFilteredTitle = new JLabel("<html> Cocktails on Menu and in Stock <br/><br/> </html>");

    // EFFECTS: runs the Virtual Bar application
    public VirtualBarGUI() {
        initFields();
        initGraphics();
    }

    // MODIFIES: this
    // EFFECTS: initializes stock and list of recipes
    private void initFields() {
        recipes = new RecipeList();
        stock = new Stock();
        input = new Scanner(System.in);
        inputLine = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: Sets up every panel and organizes it
    private void initGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        createMainMenu();
        createRecipesMenu();
        createStockMenu();

        createContent();

        createCardLayoutMenu();
        createCardLayoutContent();

        groupButtons();
    }

    // EFFECTS: Creates card layout for the menu
    private void createCardLayoutMenu() {
        c1 = new CardLayout(5,5);
        menuContainer = new JPanel(c1);
        menuContainer.add(mainMenu, "main");
        menuContainer.add(recipesMenu, "recipes");
        menuContainer.add(stockMenu, "stock");

        add(menuContainer, BorderLayout.WEST);

    }

    private void createCardLayoutContent() {
        c2 = new CardLayout(5,5);
        contentContainer = new JPanel(c2);
        contentContainer.add(mainContent, "main panel");
        contentContainer.add(addRecipe, "add recipe");
        contentContainer.add(displayAllRecipe, "view recipes");
        contentContainer.add(displayByStock, "view stock recipes");

        add(contentContainer, BorderLayout.CENTER);
    }

    // EFFECT: Groups buttons in order to create easy to navigate windows
    private void groupButtons() {
        mainMenuButtons = new HashSet<>();
        mainMenuButtons.add(recipesButton);
        mainMenuButtons.add(stockButton);
        mainMenuButtons.add(saveButton);
        mainMenuButtons.add(loadButton);

        recipesButtons = new HashSet<>();
        recipesButtons.add(addRecipeButton);
        recipesButtons.add(editRecipeButton);
        recipesButtons.add(removeRecipeButton);
        recipesButtons.add(listRecipeButton);
        recipesButtons.add(stockFilterButton);
        recipesButtons.add(recipeButton);

        stockButtons = new HashSet<>();
        stockButtons.add(addStockButton);
        stockButtons.add(editStockButton);
        stockButtons.add(viewStockButton);

        backButtons = new HashSet<>();
        backButtons.add(recipeBackButton);
        backButtons.add(stockBackButton);

        contentButtons = new HashSet<>();
        contentButtons.add(addIngredientButton);
        contentButtons.add(addStepButton);
        contentButtons.add(doneRecipeButton);
    }

    // EFFECTS: Creates buttons for main menu
    private void createMainMenu() {
        mainMenu = new JPanel();
        mainMenu.setLayout(new GridLayout(4,0));
        mainMenu.setSize(new Dimension(300, 600));

        recipesButton = new JButton("Recipes");
        recipesButton.addActionListener(this);
        mainMenu.add(recipesButton);

        stockButton = new JButton("Stock");
        stockButton.addActionListener(this);
        mainMenu.add(stockButton);

        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        mainMenu.add(saveButton);

        loadButton = new JButton("Load");
        loadButton.addActionListener(this);
        mainMenu.add(loadButton);
    }

    // EFFECTS: Creates graphics for recipes menu
    private void createRecipesMenu() {
        recipesMenu = new JPanel();
        recipesMenu.setLayout(new GridLayout(7, 0));
        recipesMenu.setSize(new Dimension(300, 600));

        addRecipesMenuButtons();
    }

    // EFFECTS: Creates all buttons for recipes menu
    private void addRecipesMenuButtons() {
        addRecipeButton = new JButton("Add");
        addRecipeButton.addActionListener(this);
        recipesMenu.add(addRecipeButton);

        editRecipeButton = new JButton("Edit");
        editRecipeButton.addActionListener(this);
        recipesMenu.add(editRecipeButton);

        removeRecipeButton = new JButton("Remove");
        removeRecipeButton.addActionListener(this);
        recipesMenu.add(removeRecipeButton);

        listRecipeButton = new JButton("View List");
        listRecipeButton.addActionListener(this);
        recipesMenu.add(listRecipeButton);

        stockFilterButton = new JButton("Stock Filter List");
        stockFilterButton.addActionListener(this);
        recipesMenu.add(stockFilterButton);

        recipeButton = new JButton("View Recipe");
        recipeButton.addActionListener(this);
        recipesMenu.add(recipeButton);

        recipeBackButton = new JButton("Back");
        recipeBackButton.addActionListener(this);
        recipesMenu.add(recipeBackButton);
    }

    // EFFECTS: Creates graphics for stock menu
    private void createStockMenu() {
        stockMenu = new JPanel();
        stockMenu.setLayout(new GridLayout(4,0));
        stockMenu.setSize(new Dimension(300, 600));

        addStockButton = new JButton("Add");
        addStockButton.addActionListener(this);
        stockMenu.add(addStockButton);

        editStockButton = new JButton("Edit");
        editStockButton.addActionListener(this);
        stockMenu.add(editStockButton);

        viewStockButton = new JButton("View List");
        viewStockButton.addActionListener(this);
        stockMenu.add(viewStockButton);

        stockBackButton = new JButton("Back");
        stockBackButton.addActionListener(this);
        stockMenu.add(stockBackButton);
    }

    private void createContent() {
        createMainMenuContent();
        createRecipesContent();
        createStockContent();
    }

    private void createMainMenuContent() {
        mainContent = new JPanel();
        mainLabel = new JLabel("Welcome");
        mainContent.add(mainLabel);
    }

    private void createRecipesContent() {
        addRecipePanel();
 //       editRecipePanel();
   //     removeRecipePanel();
        viewRecipesPanel();
        viewFilteredPanel();
        //viewRecipePanel();
    }

    private void addRecipePanel() {
        addRecipe = new JPanel();

        addIngredientsSection(true);

        Label steps = new Label("Step");
        addRecipe.add(steps);
        stepText = new JTextField(20);
        addRecipe.add(stepText);
        addStepButton = new JButton("Add");
        addStepButton.addActionListener(this);
        addRecipe.add(addStepButton);

        Label recipeName = new Label("Cocktail name:");
        addRecipe.add(recipeName);
        recipeNameText = new JTextField(20);
        addRecipe.add(recipeNameText);
        doneRecipeButton = new JButton("Add Recipe");
        doneRecipeButton.addActionListener(this);
        addRecipe.add(doneRecipeButton);

    }

    private void addIngredientsSection(boolean r) {
        Label ingredients = new Label("Ingredient Adder");
        addRecipe.add(ingredients);

        Label ingredient = new Label("Ingredient");
        addRecipe.add(ingredient);
        ingredientText = new JTextField(20);
        addRecipe.add(ingredientText);
        Label amount = new Label("Amount");
        addRecipe.add(amount);
        amountText = new JTextField(50);
        addRecipe.add(amountText);
        Label unit = new Label("Unit");
        addRecipe.add(unit);
        unitText = new JTextField(30);
        addRecipe.add(unitText);

        addIngredientButton = new JButton("Add");
        addIngredientButton.addActionListener(this);
        addRecipe.add(addIngredientButton);
    }

    private void viewRecipesPanel() {
        displayAllRecipe = new JPanel();
        displayAllRecipe.setLayout(new BoxLayout(displayAllRecipe, BoxLayout.Y_AXIS));

        displayAllRecipe.add(viewRecipeTitle);

        viewRecipes = new JLabel();
        displayAllRecipe.add(viewRecipes);
    }

    private void viewFilteredPanel() {
        displayByStock = new JPanel();
        displayByStock.setLayout(new BoxLayout(displayByStock, BoxLayout.Y_AXIS));

        displayByStock.add(viewFilteredTitle);

        viewFilteredRecipes = new JLabel();
        displayByStock.add(viewFilteredRecipes);
    }

    private void createStockContent() {
    }


    // EFFECTS: Handles button presses and redirects to correct response
    public void actionPerformed(ActionEvent e) {
        if (backButtons.contains(e.getSource())) {
            c1.show(menuContainer, "main");
            c2.show(contentContainer, "main panel");
            mainLabel.setText("Main Menu");
        } else if (mainMenuButtons.contains(e.getSource())) {
            runBar(e);
        } else if (recipesButtons.contains(e.getSource())) {
            runRecipesMenu(e);
        } else if (stockButtons.contains(e.getSource())) {
            runStockMenu(e);
        }
    }

    // EFFECTS: Handles main menu button presses
    private void runBar(ActionEvent e) {
        if (e.getSource() == recipesButton) {
            c1.show(menuContainer, "recipes");
        } else if (e.getSource() == stockButton) {
            c1.show(menuContainer, "stock");
        } else if (e.getSource() == saveButton) {
            saveBar();
        } else if (e.getSource() == loadButton) {
            loadBar();
        }
    }

    // EFFECTS: saves the workroom to file
    private void saveBar() {
        try {
            jsonWriter.open();
            jsonWriter.write(recipes, stock);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
        mainLabel.setText("Content saved");
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadBar() {
        try {
            recipes = jsonReader.readRecipeList();
            stock = jsonReader.readStock();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        mainLabel.setText("Content loaded");
    }

    private void runRecipesMenu(ActionEvent e) {
        if (e.getSource() == addRecipeButton) {
            c2.show(contentContainer, "add recipe");
        } else if (e.getSource() == listRecipeButton) {
            updateRecipeList();
            c2.show(contentContainer, "view recipes");
        } else if (e.getSource() == stockFilterButton) {
            updateRecipesStockList();
            c2.show(contentContainer, "view stock recipes");
        }
    }

    private void updateRecipeList() {
        StringBuilder result = new StringBuilder();

        result.append("<html>");
        for (Recipe i : recipes.getRecipes()) {
            result.append(i.getRecipeName() + " <br/> ");
        }
        result.append("</html>");

        viewRecipes.setText(result.toString());
    }

    private void updateRecipesStockList() {
        StringBuilder result = new StringBuilder();
        RecipeList filtered = recipes.filterRecipesByStock(stock);

        result.append("<html>");
        for (Recipe i : filtered.getRecipes()) {
            result.append(i.getRecipeName() + " <br/> ");
        }
        result.append("</html>");

        viewFilteredRecipes.setText(result.toString());
    }

    private void runStockMenu(ActionEvent e) {
    }


}
