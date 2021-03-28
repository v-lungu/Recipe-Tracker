package ui;

import javafx.scene.control.RadioButton;
import model.Ingredient;
import model.Recipe;
import model.RecipeList;
import model.Stock;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import javax.swing.*;
import java.util.Set;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;


public class VirtualBarGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/testReaderGeneralRecipeList.json";
    private RecipeList recipes;
    private Stock stock;
    private Recipe currentRecipe = new Recipe("filler");
    private Ingredient currentIngredient = new Ingredient("filler", 1, "g");
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

    private JPanel addIngredientRecipe;
    private JPanel addStepRecipe;

    private JPanel addStock;
    private JPanel editStock;
    private JPanel viewStock;

    CardLayout c1;
    CardLayout c2;

    JButton startRecipeButton;
    JButton addStepButton;
    JButton addIngredientButton;
    JButton doneRecipeButton;
    JButton findButton;
    JButton removeButton;
    JButton removeStockButton;
    JButton addIngredientStockButton;
    private Set<JButton> contentButtons;

    JRadioButton mlStockButton = new JRadioButton();
    JRadioButton gramStockButton = new JRadioButton();
    JRadioButton mlRecipeButton = new JRadioButton();
    JRadioButton gramRecipeButton = new JRadioButton();

    JTextField ingredientText = new JTextField(20);
    JTextField recipeNameText;
    JTextField amountText;
    JTextField unitText;
    JTextField stepText;
    JTextField searchRecipeText;
    JTextField removeRecipeText;
    JTextField editStockNameText;
    JTextField ingredientStockText;
    JTextField amountStockText;

    JLabel mainLabel;
    JLabel viewRecipes;
    JLabel viewRecipeTitle = new JLabel("<html> Cocktails on Menu <br/><br/> </html>");
    JLabel viewFilteredRecipes;
    JLabel viewFilteredTitle = new JLabel("<html> Cocktails on Menu and in Stock <br/><br/> </html>");
    JLabel formattedRecipe;
    JLabel removeSuccess;
    JLabel viewStockLabel;
    JLabel removeStockSuccess;

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
        c1 = new CardLayout(5, 5);
        menuContainer = new JPanel(c1);
        menuContainer.add(mainMenu, "main");
        menuContainer.add(recipesMenu, "recipes");
        menuContainer.add(stockMenu, "stock");

        add(menuContainer, BorderLayout.WEST);

    }

    private void createCardLayoutContent() {
        c2 = new CardLayout(5, 5);
        contentContainer = new JPanel(c2);
        contentContainer.add(mainContent, "main panel");
        contentContainer.add(addRecipe, "add recipe");
        contentContainer.add(removeRecipe, "remove recipe");
        contentContainer.add(displayAllRecipe, "view recipes");
        contentContainer.add(displayByStock, "view stock recipes");
        contentContainer.add(displayRecipe, "view recipe");
        contentContainer.add(viewStock, "view stock");
        contentContainer.add(editStock, "edit stock");
        contentContainer.add(addStock, "add stock");

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

        groupContentButtons();
    }

    private void groupContentButtons() {
        contentButtons = new HashSet<>();
        contentButtons.add(startRecipeButton);
        contentButtons.add(addIngredientButton);
        contentButtons.add(addStepButton);
        contentButtons.add(doneRecipeButton);
        contentButtons.add(findButton);
        contentButtons.add(removeButton);
        contentButtons.add(addIngredientStockButton);
    }

    // EFFECTS: Creates buttons for main menu
    private void createMainMenu() {
        mainMenu = new JPanel();
        mainMenu.setLayout(new GridLayout(4, 0));
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
        stockMenu.setLayout(new GridLayout(4, 0));
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
        removeRecipePanel();
        viewRecipesPanel();
        viewFilteredPanel();
        viewRecipePanel();
    }

    private void addRecipePanel() {
        addRecipe = new JPanel();

        Label recipeName = new Label("Cocktail name:");
        addRecipe.add(recipeName);
        recipeNameText = new JTextField(20);
        addRecipe.add(recipeNameText);
        startRecipeButton = new JButton("Start");
        startRecipeButton.addActionListener(this);
        addRecipe.add(startRecipeButton);

        addIngredientsSection();
        addStepSection();
    }

    private void addStepSection() {
        addStepRecipe = new JPanel();
        addStepRecipe.setLayout(new BoxLayout(addStepRecipe, BoxLayout.Y_AXIS));
        Label steps = new Label("Step:");
        addStepRecipe.add(steps);
        stepText = new JTextField(30);
        addStepRecipe.add(stepText);
        addStepButton = new JButton("Add");
        addStepButton.addActionListener(this);
        addStepRecipe.add(addStepButton);

        doneRecipeButton = new JButton("Finished Recipe");
        doneRecipeButton.addActionListener(this);
        addStepRecipe.add(doneRecipeButton);
        addRecipe.add(addStepRecipe);
        addStepRecipe.setVisible(false);
    }

    private void addIngredientsSection() {
        addIngredientRecipe = new JPanel();
        addIngredientRecipe.setLayout(new BoxLayout(addIngredientRecipe, BoxLayout.Y_AXIS));

        Label ingredients = new Label("Ingredient Adder");
        addIngredientRecipe.add(ingredients);

        Label ingredient = new Label("Ingredient");
        addIngredientRecipe.add(ingredient);
        ingredientText = new JTextField(20);
        addIngredientRecipe.add(ingredientText);
        Label amount = new Label("Amount");
        addIngredientRecipe.add(amount);
        amountText = new JTextField(20);
        addIngredientRecipe.add(amountText);
      //  Label unit = new Label("Unit");
      //  addIngredientRecipe.add(unit);
       // unitText = new JTextField(30);
      //  addIngredientRecipe.add(unitText);

        addUnitRecipe();

        addIngredientButton = new JButton("Add");
        addIngredientButton.addActionListener(this);
        addIngredientRecipe.add(addIngredientButton);

        addRecipe.add(addIngredientRecipe);
        addIngredientRecipe.setVisible(false);
    }

    private void addUnitRecipe() {
        JPanel unitRecipeBox = new JPanel();
        unitRecipeBox.setLayout(new BoxLayout(unitRecipeBox, BoxLayout.PAGE_AXIS));

        JPanel gramBox = new JPanel();
        gramRecipeButton = new JRadioButton();
        gramBox.add(gramRecipeButton);
        Label j3Label = new Label("grams(g)");
        gramBox.add(j3Label);

        JPanel mlBox = new JPanel();
        mlRecipeButton = new JRadioButton();
        mlBox.add(mlRecipeButton);
        Label j4Label = new Label("milliliter(mL)");
        mlBox.add(j4Label);

        ButtonGroup unitRecipeGroup = new ButtonGroup();
        unitRecipeGroup.add(gramRecipeButton);
        unitRecipeGroup.add(mlRecipeButton);

        unitRecipeBox.add(gramBox);
        unitRecipeBox.add(mlBox);
        addIngredientRecipe.add(unitRecipeBox);
    }

    private void removeRecipePanel() {
        removeRecipe = new JPanel();

        removeRecipeText = new JTextField(20);
        removeRecipe.add(removeRecipeText);
        removeButton = new JButton("Remove");
        removeButton.addActionListener(this);
        removeRecipe.add(removeButton);

        removeSuccess = new JLabel("");
        removeRecipe.add(removeSuccess);
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

    private void viewRecipePanel() {
        displayRecipe = new JPanel(new BorderLayout());

        JPanel testMenu = new JPanel();
        displayRecipe.add(testMenu, BorderLayout.NORTH);

        searchRecipeText = new JTextField(20);
        testMenu.add(searchRecipeText);
        findButton = new JButton("View");
        findButton.addActionListener(this);
        testMenu.add(findButton);

        formattedRecipe = new JLabel();
        formattedRecipe.setText("");
        displayRecipe.add(formattedRecipe, BorderLayout.CENTER);

    }


    private void createStockContent() {
        addStockPanel();
        //       editRecipePanel();
        editStockPanel();
        viewStockPanel();
    }

    private void addStockPanel() {
        addStock = new JPanel();
        addStock.setLayout(new BoxLayout(addStock, BoxLayout.Y_AXIS));

        Label ingredients = new Label("Ingredient Adder:");
        addStock.add(ingredients);

        Label ingredient = new Label("Ingredient name:");
        addStock.add(ingredient);
        ingredientStockText = new JTextField(20);
        addStock.add(ingredientStockText);
        Label amount = new Label("Amount");
        addStock.add(amount);
        amountStockText = new JTextField(20);
        addStock.add(amountStockText);

        addUnitStock();

        addIngredientStockButton = new JButton("Add");
        addIngredientStockButton.addActionListener(this);
        addStock.add(addIngredientStockButton);
    }

    private void addUnitStock() {
        JPanel unitBox = new JPanel();
        unitBox.setLayout(new BoxLayout(unitBox, BoxLayout.PAGE_AXIS));

        JPanel gramBox = new JPanel();
        gramStockButton = new JRadioButton();
        gramBox.add(gramStockButton);
        Label j1Label = new Label("grams(g)");
        gramBox.add(j1Label);

        JPanel mlBox = new JPanel();
        mlStockButton = new JRadioButton();
        mlBox.add(mlStockButton);
        Label j2Label = new Label("milliliter(mL)");
        mlBox.add(j2Label);

        ButtonGroup unitGroup = new ButtonGroup();
        unitGroup.add(gramStockButton);
        unitGroup.add(mlStockButton);

        unitBox.add(gramBox);
        unitBox.add(mlBox);
        addStock.add(unitBox);
    }

    private void editStockPanel() {
        editStock = new JPanel();

        editStockNameText = new JTextField(20);
        editStock.add(editStockNameText);
        removeStockButton = new JButton("Remove");
        removeStockButton.addActionListener(this);
        editStock.add(removeStockButton);

        removeStockSuccess = new JLabel("");
        removeRecipe.add(removeStockSuccess);
    }

    private void viewStockPanel() {
        viewStock = new JPanel();
        viewStock.setLayout(new BoxLayout(viewStock, BoxLayout.Y_AXIS));

        viewStockLabel = new JLabel();
        viewStock.add(viewStockLabel);
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
        } else if (contentButtons.contains(e.getSource())) {
            runContentButtons(e);
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
        } else if (e.getSource() == removeRecipeButton) {
            c2.show(contentContainer, "remove recipe");
        } else if (e.getSource() == listRecipeButton) {
            updateRecipeList();
            c2.show(contentContainer, "view recipes");
        } else if (e.getSource() == stockFilterButton) {
            updateRecipesStockList();
            c2.show(contentContainer, "view stock recipes");
        } else if (e.getSource() == recipeButton) {
            c2.show(contentContainer, "view recipe");
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
        if (e.getSource() == addStockButton) {
            c2.show(contentContainer, "add stock");
        } else if (e.getSource() == editStockButton) {
            c2.show(contentContainer, "edit stock");
        } else if (e.getSource() == viewStockButton) {
            updateStockList();
            c2.show(contentContainer, "view stock");
        }
    }

    private void updateStockList() {
        StringBuilder result = new StringBuilder();

        result.append("<html> Stock List <br/> <br/>");
        for (Ingredient i : stock.getStock()) {
            result.append(i.getIngredient() + " <br/> ");
        }
        result.append("</html>");

        viewStockLabel.setText(result.toString());
    }

    private void runContentButtons(ActionEvent e) {
        if (e.getSource() == startRecipeButton) {
            startAddRecipe();
        } else if (e.getSource() == addIngredientButton) {
            addIngredientToRecipe();
        } else if (e.getSource() == addStepButton) {
            String step = stepText.getText();
            currentRecipe.addInstruction(step);
            stepText.setText("");
        } else if (e.getSource() == doneRecipeButton) {
            finishAddingRecipe();
            playSound("./data/beerOpen.wav");
        } else if (e.getSource() == removeButton) {
            removeRecipe();
        } else if (e.getSource() == findButton) {
            viewRecipe();
            playSound("./data/beerOpen.wav");
        } else if (e.getSource() == removeStockButton) {
            stock.removeFromStock(editStockNameText.getText());
            removeStockSuccess.setText("Ingredient removed successfully");
        } else if (e.getSource() == addIngredientStockButton) {
            addIngredientToStock();
        }
    }

    private void removeRecipe() {
        if (recipes.findRecipe(removeRecipeText.getText())) {
            recipes.removeRecipe(removeRecipeText.getText());
            removeSuccess.setText("Cocktail removed successfully");
        } else {
            removeSuccess.setText("Cocktail not found");
        }
    }

    private void startAddRecipe() {
        currentRecipe = new Recipe(recipeNameText.getText());
        addIngredientRecipe.setVisible(true);
        addStepRecipe.setVisible(true);
    }

    private void finishAddingRecipe() {
        recipes.addRecipe(currentRecipe);
        addIngredientRecipe.setVisible(false);
        addStepRecipe.setVisible(false);
        recipeNameText.setText("");
    }

    private void addIngredientToRecipe() {
        if (gramRecipeButton.isSelected()) {
            currentIngredient = new Ingredient(ingredientText.getText(),
                    Integer.parseInt(amountText.getText()),"g");
            gramRecipeButton.setSelected(false);
        } else if (mlStockButton.isSelected()) {
            currentIngredient = new Ingredient(ingredientText.getText(),
                    Integer.parseInt(amountText.getText()),"mL");
            mlRecipeButton.setSelected(false);
        }
        currentRecipe.addIngredient(currentIngredient);
        ingredientText.setText("");
        amountText.setText("");
    }

    private void addIngredientToStock() {
        if (gramStockButton.isSelected()) {
            currentIngredient = new Ingredient(ingredientStockText.getText(),
                    Integer.parseInt(amountStockText.getText()),"g");
            gramStockButton.setSelected(false);
        } else if (mlStockButton.isSelected()) {
            currentIngredient = new Ingredient(ingredientStockText.getText(),
                    Integer.parseInt(amountStockText.getText()),"mL");
            mlStockButton.setSelected(false);
        }
        stock.addToStock(currentIngredient);
        ingredientStockText.setText("");
        amountStockText.setText("");
    }

    private void viewRecipe() {
        if (!recipes.findRecipe(searchRecipeText.getText())) {
            formattedRecipe.setText("The cocktail cannot be found");
        } else {
            Recipe viewRecipe = recipes.getRecipe(searchRecipeText.getText());

            String name = viewRecipe.getRecipeName();
            ArrayList<Ingredient> ingredients = viewRecipe.getRecipeIngredients();
            ArrayList<String> steps = viewRecipe.getRecipeInstructions();

            StringBuilder result = new StringBuilder();

            result.append("<html>");
            result.append(name + " <br/> <br/> ");
            result.append("INGREDIENTS" + " <br/> ");
            for (Ingredient i : ingredients) {
                result.append(i.getIngredient() + " <br/> ");
            }
            result.append("<br/> STEPS" + " <br/> ");
            for (String i : steps) {
                result.append(i + " <br/> ");
            }
            result.append("</html>");

            formattedRecipe.setText(result.toString());
            displayRecipe.add(formattedRecipe);
        }
    }

    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

}
