package com.uottawa.vedant.cookhelper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import static com.uottawa.vedant.cookhelper.RecipeStorage.loadRecipes;
import static com.uottawa.vedant.cookhelper.RecipeStorage.saveRecipes;

public class SearchResults extends AppCompatActivity {

    String key;
    ListView list;
    ArrayAdapter<String> adapter;
    ArrayList<String> listItems;
    ArrayList<Recipe> recipeList;
    IngredientsArrayList<Ingredient> ingredientsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        key = (String) this.getIntent().getSerializableExtra("key");
        TextView textView = (TextView) findViewById(R.id.searchText);
        textView.setText(key);
        if(key.equals("Advanced Search")) { key = ""; }

        listItems = new ArrayList<>();
        recipeList = new ArrayList<>();

        final Context ctx = this;
        String type = (String) this.getIntent().getSerializableExtra("type");
        String category = (String) this.getIntent().getSerializableExtra("category");
        ingredientsArrayList =
                convert((ArrayList<Ingredient>) this.getIntent().getSerializableExtra("ingredients"));

        if(ingredientsArrayList.isEmpty() && type.equals("Any") && category.equals("Any"))
        {
            recipeList = getResults();
        }
        else
        {
            recipeList = getResults(type, category, ingredientsArrayList);
        }
        list = (ListView) findViewById(R.id.listRecipes);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        list.setAdapter(adapter);
        final Intent viewRecipe = new Intent(ctx, RecipePage.class);


        for (int i = 0; i<recipeList.size(); i++){
            listItems.add(recipeList.get(i).getName());
        }

        adapter.notifyDataSetChanged();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, final int position,
                                    long id) {

                AlertDialog.Builder adb = new AlertDialog.Builder(
                        SearchResults.this);
                adb.setTitle("Delete");
                adb.setMessage("Recipe "
                        +list.getItemAtPosition(position));
                adb.setNegativeButton("Cancel",null);
                adb.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        listItems.remove(position);
                        recipeList.remove(position);
                        saveRecipes(recipeList,ctx);
                        adapter.notifyDataSetChanged();
                        recipeList = loadRecipes(ctx);

                    }
                });
                adb.setNeutralButton("View Recipe", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        Recipe temp = recipeList.get(position);
                        viewRecipe.putExtra("recipe", temp);
                        viewRecipe.putExtra("position", position);
                        startActivity(viewRecipe);

                    }
                });
                adb.show();
            }
        });
        final Intent homepage = new Intent(getApplicationContext(), MainActivity.class);
        Button home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //Starting a new Intent
                startActivity(homepage);
            }
        });
    }

    private ArrayList<Recipe> getResults()
    {
        ArrayList<Recipe> results = new ArrayList<>();
        ArrayList<Recipe> allRecipes = ((MyApplication) this.getApplication()).getRecipeList();
        for(int i = 0; i < allRecipes.size(); i++)
        {
            Recipe recipe = allRecipes.get(i);
            if(recipe.getName().contains(key))
            {
                results.add(recipe);
            }
        }
        return results;
    }

    private ArrayList<Recipe> getResults(String type, String category, ArrayList<Ingredient> ingredientsArrayList)
    {
        ArrayList<Recipe> results = new ArrayList<>();
        ArrayList<Recipe> allRecipes = ((MyApplication) this.getApplication()).getRecipeList();
        ArrayList<Ingredient> mandatoryIngredients = new IngredientsArrayList<>();
        ArrayList<Ingredient> optionalIngredients = new IngredientsArrayList<>();
        IngredientsArrayList<Ingredient> recipeIngredients;
        for(int i = 0; i < ingredientsArrayList.size(); i++)
        {
            Ingredient ingredient = ingredientsArrayList.get(i);
            if(ingredient.getName().contains("*"))
            {
                mandatoryIngredients.add(ingredientsArrayList.get(i));
            }
            else
            {
                optionalIngredients.add(ingredientsArrayList.get(i));
            }
        }
        if(mandatoryIngredients.isEmpty() && !optionalIngredients.isEmpty())
        {
            for(int i = 0; i < allRecipes.size(); i++)
            {
                Recipe recipe = allRecipes.get(i);
                recipeIngredients = convert(recipe.getIngredients());
                String recipeType = recipe.getType().getName();
                String recipeCategory = recipe.getCategory().getName();
                for(int j = 0; j < optionalIngredients.size(); j++)
                {
                    if (recipeIngredients.contains(optionalIngredients.get(j)) &&
                            (category.equals(recipeCategory) || category.equals("Any")) &&
                            (type.equals(recipeType) || type.equals("Any")))
                    {
                        results.add(recipe);
                    }
                }
            }
            return results;
        }
        for(int i = 0; i < allRecipes.size(); i++)
        {
            Recipe recipe = allRecipes.get(i);
            recipeIngredients = convert(recipe.getIngredients());
            boolean addRecipe = true;
            String recipeType = recipe.getType().getName();
            String recipeCategory = recipe.getCategory().getName();
            if((type.equals(recipeType) || type.equals("Any")) &&
               (category.equals(recipeCategory) || category.equals("Any")))
            {
                for(int j = 0; j < mandatoryIngredients.size(); j++)
                {
                    if(!recipeIngredients.contains(mandatoryIngredients.get(j)))
                    {
                        addRecipe = false;
                    }
                }
            }
            else
            {
                addRecipe = false;
            }
            if(addRecipe){ results.add(recipe); }
        }
        return results;
    }
    private IngredientsArrayList<Ingredient> convert (ArrayList<Ingredient> oldlist)
    {
        IngredientsArrayList<Ingredient> newlist= new IngredientsArrayList<>();
        for(int i = 0; i < oldlist.size(); i++)
        {
            newlist.add(new Ingredient(oldlist.get(i).getName()));
        }
        return newlist;
    }
}

class IngredientsArrayList<Ingredient> extends ArrayList<Ingredient>{
    @Override
    public boolean contains(Object i){
        boolean bool = false;
        com.uottawa.vedant.cookhelper.Ingredient ingredient1 =
                (com.uottawa.vedant.cookhelper.Ingredient) i;
        for(int a = 0; a < this.size(); a++)
        {
            com.uottawa.vedant.cookhelper.Ingredient ingredient2 =
                    (com.uottawa.vedant.cookhelper.Ingredient) this.get(a);
            if(ingredient2.getName().equals(ingredient1.getName()))
            {
                bool = true;
            }
        }
        return bool;
    }
}