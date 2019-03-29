package com.uottawa.vedant.cookhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

//display a recipe
public class RecipePage extends AppCompatActivity {

    Recipe recipe;
    ListView ingredients;
    ListView instructions;
    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter2;
    ArrayList<String> ingredientsList = new ArrayList<>();
    ArrayList<String> instructionsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_page);

        recipe = (Recipe)this.getIntent().getSerializableExtra("recipe");
        TextView name = (TextView) findViewById(R.id.name);
        name.setText(recipe.getName());
        TextView type = (TextView) findViewById(R.id.type);
        type.setText(recipe.getType().getName());
        TextView category = (TextView) findViewById(R.id.category);
        category.setText(recipe.getCategory().getName());

        instructionsList.add(recipe.getDescription());
        ArrayList<Ingredient> recpieIngredientsList = recipe.getIngredients();
        for(int i = 0; i < recpieIngredientsList.size(); i++)
        {
            ingredientsList.add(recpieIngredientsList.get(i).getName());
        }
        ingredients = (ListView) findViewById(R.id.ingredientsListView);
        instructions = (ListView) findViewById(R.id.instructionsListView);

        adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ingredientsList);
        ingredients.setAdapter(adapter1);
        adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, instructionsList);
        instructions.setAdapter(adapter2);

        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();

        final Intent homepage = new Intent(getApplicationContext(), MainActivity.class);
        final Intent editpage = new Intent(getApplicationContext(), EditIngredients.class);

        Button edit = (Button) findViewById(R.id.r_edit);
        Button home = (Button) findViewById(R.id.home);

        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //Starting a new Intent
                editpage.putExtra("booledit", true);
                editpage.putExtra("recipe", recipe);
                startActivity(editpage);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //Starting a new Intent
                startActivity(homepage);
            }
        });
    }
}
