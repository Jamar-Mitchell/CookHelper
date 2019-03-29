package com.uottawa.vedant.cookhelper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.uottawa.vedant.cookhelper.RecipeStorage.saveRecipes;

public class EditInstructions extends AppCompatActivity{

    Button add;
    Button save;
    ListView list;
    ArrayList<String> listItems;
    ArrayList<Recipe> recipeList;
    ArrayAdapter<String> adapter;
    EditText instruct;
    Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Intent myRecipes = new Intent(this, MyRecipes.class);
        ctx=getApplicationContext();
        recipeList = ((MyApplication) this.getApplication()).getRecipeList();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_instructions);
        final Recipe recipe = (Recipe) getIntent().getSerializableExtra("recipe");
        save = (Button) findViewById(R.id.save);
        add = (Button) findViewById(R.id.add);
        instruct = (EditText) findViewById(R.id.enter);
        listItems =new ArrayList<>();
        list = (ListView) findViewById(R.id.instruction);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, listItems);
        list.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                listItems.add("- " + instruct.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });

        /* saves the information given from the edit ingredients page with the information from the
         edit instructions page as a new recipe.*/
        save.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                String instructions = "";
                for(int i = 0; i < listItems.size(); i++)
                {
                    instructions = instructions + listItems.get(i) + "\n";
                }
                recipe.setDescription(instructions);
                recipeList.add(recipe);
                saveRecipes(recipeList,ctx);

                startActivity(myRecipes);
            }
        });

// Displays the old ingredients from editing a recipe.
        Recipe old = (Recipe) this.getIntent().getSerializableExtra("recipe");
        List<String> tempInstructions = Arrays.asList(old.getDescription().split("\n"));
        for(int i = 0; i < tempInstructions.size(); i++)
        {
            listItems.add(tempInstructions.get(i));
        }
        adapter.notifyDataSetChanged();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, final int position,
                                    long id) {

                AlertDialog.Builder adb = new AlertDialog.Builder(
                        EditInstructions.this);
                adb.setTitle("Delete");
                adb.setMessage("Delete "
                        +list.getItemAtPosition(position) + " from the list?");
                adb.setNegativeButton("Cancel",null);
                adb.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        listItems.remove(position);
                        adapter.notifyDataSetChanged();

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
