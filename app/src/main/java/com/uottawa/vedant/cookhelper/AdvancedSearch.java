package com.uottawa.vedant.cookhelper;

import android.content.DialogInterface;
import android.content.Intent;
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
import java.util.ArrayList;
import java.util.List;

public class AdvancedSearch extends AppCompatActivity {

    Switch needed;
    Spinner spinner;
    Spinner type;
    Button addButton;
    Button search;
    Button reset;
    EditText ingredient;
    ListView list;
    ArrayList<Ingredient> listItems;
    ArrayAdapter<Ingredient> adapter;
    ArrayAdapter<String> adapterType;
    ArrayAdapter<String> adapterCategory;
    List<String> types;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search);

        spinner = (Spinner) findViewById(R.id.spinner);
        type = (Spinner) findViewById(R.id.typeSpinner);

        types = new ArrayList<>();
        types.add("Any");
        types.add("Breakfast");
        types.add("Lunch");
        types.add("Dinner");
        types.add("Snack");
        adapterType = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, types);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapterType);


        List<String> categories;

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
        spinner.setAdapter(adapterCategory);

        addButton = (Button) findViewById(R.id.add);
        search = (Button) findViewById(R.id.aSearch);
        ingredient = (EditText) findViewById(R.id.ingredient);
        list = (ListView) findViewById(R.id.listView);
        listItems =new ArrayList<>();

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, listItems);
        list.setAdapter(adapter);

        needed = (Switch) findViewById(R.id.manditorySwitch);
        needed.setChecked(true);
        addButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(needed.isChecked()){
                    listItems.add(new Ingredient(ingredient.getText().toString()+"   *"));
                }else{
                    listItems.add(new Ingredient(ingredient.getText().toString()));
                }
                adapter.notifyDataSetChanged();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String categoryString = spinner.getSelectedItem().toString();
                String typeString = type.getSelectedItem().toString();
                searchResults(typeString, categoryString);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, final int position,
                                    long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(
                        AdvancedSearch.this);
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

        reset = (Button) findViewById(R.id.Reset);
        reset.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                listItems.clear();
                ingredient.setText("");
                spinner.setSelection(0);
                type.setSelection(0);
                needed.setChecked(true);
                adapter.notifyDataSetChanged();
                adapterType.notifyDataSetChanged();
                adapterCategory.notifyDataSetChanged();
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

    //Starts activity that searches by type,category, key word and ingredients.
    public void searchResults(String typeString, String categoryString)
    {
        Intent intent = new Intent(AdvancedSearch.this, SearchResults.class);
        intent.putExtra("type", typeString);
        intent.putExtra("category", categoryString);
        intent.putExtra("ingredients", listItems);
        intent.putExtra("key", "Advanced Search");
        startActivity(intent);
    }

}
