# Virtual Bar

## Virtually a Real Bar


The Virtual bar is a place to enjoy the simple niceties of a real bar while the pandemic has us all locked down. 
Obviously, we are not currently able to enjoy real bars due to the virus situation, so I want to bring a little slice
of the bar experience to our home. 

Functionally the Virtual Bar will act as a storage system for cocktail recipes as well as a repository for the current 
inventory and stock of your home bar. This way you will always know what ingredients you have at your disposal and what 
recipes can be prepared with what's on hand. This is the primary functionality of the project, with a few extra 
features included to make these lists work for the user. The Virtual Bar will allow you to add your own cocktail 
recipes, edit existing recipes, and view them by ingredients on hand. 

Ultimately, this project is meant for anyone who enjoys drinking **responsibly** at home. On a more practical level, 
it strikes me as both a realistic project for this course, and a chance for me to engage with the topics taught in the 
course. 
 
#### Functional Features
- Recipe list 
- Stock and inventory 
- Editing capabilities 

## User Stories

### Part 1: 
- I want to be able to add new drink recipes
- I want to view all my recipes filtered for what I have in stock
- I want to be able to see a recipe in detail
- I want to be able to edit my recipes
- I want to be able to see an inventory of all ingredients I have at home
- I want to be able to edit my home inventory 

### Part 2:
- I want to be able to save my recipes and stock
- I want to be able to load my recipes and stock from file 

### Part 4:
- Task 2: The Recipe class has been designed and tested to be robust
    - The constructor and addIngredient methods have utilized thrown exceptions 
    - All other methods are internally robust, and can handle not finding searched for items
- Task 3: The major change I would make to my design is to create an abstract class IngredientList that implements many
of the duplicate functions in the Recipe and Stock classes. These two classes  share many of the same functions when it 
comes to adding and modifying a list of ingredients. I would also try to split up my GUI file into more manageable 
chunks. The class ended up fairly bloated and I would've liked more organization.