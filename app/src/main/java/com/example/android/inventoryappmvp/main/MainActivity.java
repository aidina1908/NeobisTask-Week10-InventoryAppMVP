package com.example.android.inventoryappmvp.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.example.android.inventoryappmvp.Inventory;
import com.example.android.inventoryappmvp.InventoryAdapter;
import com.example.android.inventoryappmvp.R;
import com.example.android.inventoryappmvp.data.InventoryDatabase;
import com.example.android.inventoryappmvp.edit.EditActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    InventoryDatabase database;
    public static final int ADD_INVENTORY_REQUEST = 1;
    public static final int EDIT_INVENTORY_REQUEST = 2;

    MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final InventoryAdapter adapter = new InventoryAdapter();
        recyclerView.setAdapter(adapter);

       // presenter = new MainPresenter(this, getApplicationContext());


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivityForResult(intent, ADD_INVENTORY_REQUEST);
            }
        });

        adapter.setOnItemClickListener(new InventoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Inventory inventory) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra(EditActivity.EXTRA_ID,inventory.getId());
               // intent.putExtra(EditActivity.EXTRA_IMAGE,inventory.getImage());
                intent.putExtra(EditActivity.EXTRA_NAME,inventory.getName());
                intent.putExtra(EditActivity.EXTRA_PRICE,inventory.getPrice());
                intent.putExtra(EditActivity.EXTRA_QUANTITY,inventory.getQuantity());
                intent.putExtra(EditActivity.EXTRA_SUPPLIER,inventory.getSupplier());
                startActivityForResult(intent, EDIT_INVENTORY_REQUEST);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.delete_all:
                showAlertDialog();
                return true;
            default: return super.onOptionsItemSelected(item);

        }
    }
    public void showAlertDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete");
        alert.setMessage("Do you want delete all products?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                database.inventoryDao().deleteAllInventories();

            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.create().show();
    }

}