# CookHelper

## [Introduction]()

## 

 

In a time were people have less and less time to cook meals our group
created an app to make the cooking process for everyday individuals easier. The
CookHelper app will aid individuals with various skills to create and store new
recipes. The app we passionately developed provides the ability the complete
the following tasks:

·       Add and delete recipes

·       Modify existing recipes

·       Search for recipes by name

·       Search for recipes by
ingredients/type/category

·       Store recipes for future access

 

This report
provides an in depth review of the deliverables we have produced for our CookHelper
application. The deliverables include: Software Requirements, UML Diagrams as
well as screenshots of our User Interface.  
  

## [Software Requirements]()

### [FunctionalRequirements]()

## 

 

    1. Must be made using Android Studio 2.1       
    2. Available for phones running android 4.0 and up     
    3. The app will be made using java and xml     
    4. Development is complete by the week of Nov 30th              
    5. A waterfall software development will take
      place  

 

 

 

### [Non-functional Requirements]()

## 

 

1.    
The app
must let the user to search for recipes using ingredients as search parameters

2.    
The
system must allow storage of their recipes that have been added by the user           

3.    
The
system must allow the recipes to be sorted by category 

4.    
The
system must contain instructions for the use of the app 

5.    
The
system must allow the stored recipes to be deleted by the user 

6.    
The
system must allow the user to stop and cancel the recipe they are entering 

7.    
The
system must provide different sections for information on a recipe;
ingredients, preparation and cooking instructions 

8.    
The
system must output search results in terms of relevance  

9.    
The system must allow the user to add recipe directions step by step

10.  The system must allow the
user to delete recipe directions step by step  

11.  The system must
automatically update the recipe instructions order while new instructions are
being added/deleted  

12.  The system shall number the
directions in a recipe.  

13.  The system shall allow the
creation of new categories whenever the user can choose a category for a
recipe.  

14.  The system shall allow the
creation of new cuisine types whenever the user can choose a cuisine for a
recipe.  

15.  The system shall allow the
input of amounts for ingredients for the recipe  

16.  All search options must be available in terms
ingredients and the category or food  

17.  The system shall allow the
creation of new categories.  

18.  The system shall allow the
creation of new cuisine types.  

19.  The system shall display a
recipe with the following components: ingredients, directions, category, name,
cuisine, and metadata  

20.  The system shall sort the
user’s list of recipes alphabetically.
   
  

## [Deliverable 1:Software
Requirements]()

## 

 

### [UseCase1:]()

## 

Name:Searching for a recipe based on ingredients

Actors:User

Preconditions:Enter a valid string argument.

Post-conditions:If the recipe does not exist
display no recipe found. If the recipe does exist then none will be displayed.

 

 

 

Actor Actions

 

System Responses

 

1. Enters in desired ingredient 

 

2. Ingredient is added to the search field

 

3. If desired add additional ingredients by
  pressing the “plus” button

 

4. Additional text fields appear for each
  new ingredient

 

5. Presses the search icon 

 

6. Checks stored recipes for the entered
  ingredients and displays recipes in order of most ingredients from list used

 

7. Selects recipe from the results 

 

8. Displays details for recipe

 

### [UseCase2:]()

## 

Name:Adding a Recipe.

Actors:User

Pre-Condition: Enter a valid string argument.

Post-Condition: The recipe will be added to the recipe list if it is a
valid recipe; otherwise do not return recipe and indicate to user why.

 

 

 

 

Actor Actions

 

System Responses

 

1. In the options selects to add a new
  recipe 

 

2. Opens a new activity that contains
  fields for the new recipe

 

3. Enters in the required information into
  the fields

 

4. Hits save recipe

 

5. System stores the recipe 

 

 

 

 

 

 

 

### [UseCase3:]()

## 

Name:Editing a Recipe.

Actors:User

Pre-Condition: Enter a valid string argument.

Post-Condition: The recipe will be edited and stored to recipe list.

 

 

 

 

Actor Actions

 

System Responses

 

1. When viewing a recipe user selects edit
  to edit a recipe 

 

2. Opens the edit ingredients activity that
  contains the editable fields filled out from the original recipe

 

3. Enters in the required information into
  the fields

 

4. Hits next

 

5. System passes information on to the edit
  instructions activity, the old recipe instructions are loaded onto display

 

6. Enter new and/or delete old instructions

 

 

 

7. Hits save

 

8. System stores the recipe
