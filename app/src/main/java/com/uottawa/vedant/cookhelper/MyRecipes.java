package com.uottawa.vedant.cookhelper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.graphics.Color;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.uottawa.vedant.cookhelper.RecipeStorage.loadRecipes;
import static com.uottawa.vedant.cookhelper.RecipeStorage.saveRecipes;

// displays all created recipes
public class MyRecipes extends AppCompatActivity {


    Button add;
    ListView list;
    ArrayAdapter<String> adapter;
    ArrayList<String> listItems = new ArrayList<>();

    ArrayList<Recipe> recipeList = new ArrayList<>();

    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context ctx =this.getApplicationContext();
        recipeList = ((MyApplication) this.getApplication()).getRecipeList();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);
        add = (Button) findViewById(R.id.add);
        list = (ListView) findViewById(R.id.listRecipes);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listItems);
        list.setAdapter(adapter);
        final Intent addRecipe = new Intent(context, EditIngredients.class);
        final Intent viewRecipe = new Intent(context, RecipePage.class);


        for (int i = 0; i<recipeList.size(); i++){
            listItems.add(recipeList.get(i).getName());
        }

        adapter.notifyDataSetChanged();


        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                //Starting a new Intent
                addRecipe.putExtra("booledit",false);
                startActivity(addRecipe);
            }
        });


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, final int position,
                                    long id) {

                AlertDialog.Builder adb = new AlertDialog.Builder(
                        MyRecipes.this);
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
                        recipeList= loadRecipes(ctx);

                    }
                });
                adb.setNeutralButton("View Recipe",new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        Recipe temp = recipeList.get(position);
                        viewRecipe.putExtra("recipe",temp);
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


}
