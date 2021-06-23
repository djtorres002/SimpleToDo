package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {



    List<String> items;

    Button AddBtn;
    EditText etitem;
    RecyclerView rvitems;
    ItemsAdapter itemsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AddBtn = findViewById(R.id.AddBtn);
        etitem = findViewById(R.id.etitem);
        rvitems = findViewById(R.id.rvitems);
        
        items = new ArrayList<>();
        items.add("Buy milk");
        items.add("Buy eggs");
        items.add("Buy sugar");

        ItemsAdapter.OnLongClickListener onLongClickListener = new ItemsAdapter.OnLongClickListener(){

            @Override
            public void onItemLongClicked(int position){
                //Delete the item from the module
                items.remove(position);
                // Notify the adapter
                itemsAdapter.notifyItemRemoved(position);
                Toast.makeText(getApplicationContext(), "Item was removed", Toast.LENGTH_SHORT).show();
            }
        };
        itemsAdapter = new ItemsAdapter(items, onLongClickListener);
        rvitems.setAdapter(itemsAdapter);
        rvitems.setLayoutManager(new LinearLayoutManager(this));

        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoitem = etitem.getText().toString();
                // add item to the model
                items.add(todoitem);
                // Notify adapter that item is inserted
                itemsAdapter.notifyItemInserted(items.size() - 1);
                etitem.setText("");
                Toast.makeText(getApplicationContext(), "Item was added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}