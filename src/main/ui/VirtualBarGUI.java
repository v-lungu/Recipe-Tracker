package ui;

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
    private static final String JSON_STORE = "./data/bar.json";
    private RecipeList recipes;
    private Stock stock;
    private Scanner input;
    private Scanner inputLine;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 900;

    JButton recipesButton;
    JButton stockButton;
    JButton saveButton;
    JButton loadButton;
    Set<JButton> mainMenuButtons;

    JButton addRecipeButton;
    JButton editRecipeButton;
    JButton removeRecipeButton;
    JButton listRecipeButton;
    JButton stockFilterButton;
    JButton recipeButton;
    JButton recipeBackButton;
    Set<JButton> recipesButtons;

    JButton addStockButton;
    JButton editStockButton;
    JButton viewStockButton;
    JButton stockBackButton;
    Set<JButton> stockButtons;

    Set<JButton> backButtons;

    JPanel menuContainer;
    JPanel contentContainer;

    JPanel mainMenu;
    JPanel recipesMenu;
    JPanel stockMenu;

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

    private void initGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        createMainMenu();
        createRecipesMenu();
        createStockMenu();

        createCardLayoutMenu();

        groupButtons();
    }

    private void createCardLayoutMenu() {
        CardLayout c1 = new CardLayout(5,5);
        menuContainer = new JPanel(c1);
        menuContainer.setPreferredSize(new Dimension(100, 700));
        menuContainer.add(mainMenu);
        menuContainer.add(recipesMenu);

        menuContainer.add(stockMenu);

        add(menuContainer, BorderLayout.WEST);

        contentContainer = new JPanel();
        contentContainer.setPreferredSize(new Dimension(700, 700));
        contentContainer.setBackground(Color.green);
        add(contentContainer, BorderLayout.CENTER);
    }

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
    }

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

    private void createRecipesMenu() {
        recipesMenu = new JPanel();
        recipesMenu.setLayout(new GridLayout(7, 0));
        recipesMenu.setSize(new Dimension(300, 600));

        addRecipesMenuButtons();
    }

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

    public void actionPerformed(ActionEvent e) {
        if (backButtons.contains(e.getSource())) {
            mainMenu.setVisible(true);
            recipesMenu.setVisible(false);
            stockMenu.setVisible(false);
        } else if (mainMenuButtons.contains(e.getSource())) {
            runBar(e);
        } else if (recipesButtons.contains(e.getSource())) {
            runRecipes(e);
        } else if (stockButtons.contains(e.getSource())) {
            runStock(e);
        }
    }

    private void runBar(ActionEvent e) {
        if (e.getSource() == recipesButton) {
            mainMenu.setVisible(false);
            recipesMenu.setVisible(true);
        } else if (e.getSource() == stockButton) {
            mainMenu.setVisible(false);
            stockMenu.setVisible(true);
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
    }

    private void runRecipes(ActionEvent e) {
    }

    private void runStock(ActionEvent e) {
    }


}
