package com.uottawa.vedant.cookhelper;

import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.EGLDisplay;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditIngredients extends AppCompatActivity{

    Spinner typeInput;
    Spinner categoryInput;
    Switch needed;
    Button next;
    Button add;
    EditText ingredient;
    EditText name;
    ListView list;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapterType;
    ArrayList<Ingredient> ingredientsList;
    Boolean fill;
    Recipe recipe;
    Category category;
    FoodType type;
    String instruction;
    ArrayAdapter<String> adapterCategory;
    List<String> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ingredients);

        final Intent editInstructions = new Intent(this, EditInstructions.class);
        typeInput = (Spinner) findViewById(R.id.type);
        categoryInput = (Spinner) findViewById(R.id.category);
        next = (Button) findViewById(R.id.Next);
        fill = (Boolean) this.getIntent().getSerializableExtra("booledit");
        add = (Button) findViewById(R.id.add);
        name = (EditText) findViewById(R.id.name);
        ingredient = (EditText) findViewById(R.id.ingredient);
        list = (ListView) findViewById(R.id.listView);
        listItems =new ArrayList<>();
        ingredientsList = new ArrayList<>();
        instruction = "";
        ArrayList<String> types;
        types = new ArrayList<>();
        types.add("Any");
        types.add("Breakfast");
        types.add("Lunch");
        types.add("Dinner");
        types.add("Snack");
        adapterType = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, types);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeInput.setAdapter(adapterType);


        categories = new ArrayList<>();
        categories.add("Any");
        categories.add("American");
        categories.add("Asian");
        categories.add("Italian");
        categories.add("Mexican");
        categories.add("Other");
        adapterCategory = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, categories);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryInput.setAdapter(adapterCategory);


// when the next button is clicked, data is sent from the edit ingredients page to the edit instructions page to be saved as a recipe.
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //Starting a new Intent
                type = new FoodType(typeInput.getSelectedItem().toString());
                category = new Category(categoryInput.getSelectedItem().toString());
                recipe = new Recipe(name.getText().toString(),instruction,category,type);
                for(int i = 0; i < listItems.size(); i++)
                {
                    ingredientsList.add(new Ingredient(listItems.get(i)));
                }
                recipe.setIngredients(ingredientsList);
                editInstructions.putExtra("recipe", recipe);
                startActivity(editInstructions);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, final int position,
                                    long id) {

                AlertDialog.Builder adb = new AlertDialog.Builder(
                        EditIngredients.this);
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
        needed = (Switch) findViewById(R.id.manditory);
        needed.setChecked(true);

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, listItems);
        list.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                   if(needed.isChecked())
                   {
                    listItems.add(ingredient.getText().toString() + "   *");
                   }
                   else
                   {
                      listItems.add(ingredient.getText().toString());
                   }
                adapter.notifyDataSetChanged();
            }
        });

        // if instead of a new recipe being added, an old recipe is being edited.
        if(fill)
        {
            recipe = (Recipe) this.getIntent().getSerializableExtra("recipe");
            ArrayList<Ingredient> tempIngredients = recipe.getIngredients();
            String tempType = recipe.getType().getName();
            String tempCategory = recipe.getCategory().getName();
            instruction = recipe.getDescription();
            ((EditText) findViewById(R.id.name)).setText(recipe.getName());
            for(int i = 0; i < tempIngredients.size(); i++)
            {
                listItems.add(tempIngredients.get(i).getName());
            }
            typeInput.setSelection(types.indexOf(tempType));
            categoryInput.setSelection(categories.indexOf(tempCategory));
            adapterCategory.notifyDataSetChanged();
            adapterType.notifyDataSetChanged();
            adapter.notifyDataSetChanged();
        }

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
