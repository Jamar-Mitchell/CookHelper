package com.uottawa.vedant.cookhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import static com.uottawa.vedant.cookhelper.RecipeStorage.loadRecipes;
import static com.uottawa.vedant.cookhelper.RecipeStorage.saveRecipes;

//main navigation page
public class MainActivity extends AppCompatActivity {

    ArrayList<Recipe> recipeList;

    //initializes buttons
    Button asearch;
    Button recipe;
    Button search;
    Button help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipeList = loadRecipes(this.getApplicationContext());
        ((MyApplication) this.getApplication()).setRecipeList(recipeList);

        final Intent helppage = new Intent(getApplicationContext(), HelpactivityActivity.class);

        asearch = (Button) findViewById(R.id.mainAdvancedSearchButton);
        recipe = (Button) findViewById(R.id.mainMyRecipeButton);
        search = (Button) findViewById(R.id.mainSearchButton);
        help = (Button) findViewById(R.id.helpbtn);

        // brings to the advance search page
        asearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //Starting a new Intent
                startActivity(advSearch);
            }
        });
        // brings to the recipes page
        recipe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //Starting a new Intent
                startActivity(myRecipes);
            }
        });
        // brings to the help page
        help.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //Starting a new Intent
                startActivity(helppage);
            }
        });
        //starts the search activity with a given key word
        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                EditText editText = (EditText) findViewById(R.id.mainSearchText);
                searchResults.putExtra("key", editText.getText().toString());
                searchResults.putExtra("type", "Any");
                searchResults.putExtra("category", "Any");
                searchResults.putExtra("ingredients", new ArrayList<Ingredient>());
                startActivity(searchResults);
            }
        });



    }


}
