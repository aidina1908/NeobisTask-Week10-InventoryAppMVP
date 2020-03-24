package com.example.android.inventoryappmvp.main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.android.inventoryappmvp.Inventory;
import com.example.android.inventoryappmvp.data.InventoryDao;
import com.example.android.inventoryappmvp.data.InventoryDatabase;
import com.example.android.inventoryappmvp.edit.EditActivity;

public class MainPresenter implements MainContract.Presenter {

    public static final int ADD_INVENTORY_REQUEST = 1;
    public static final int EDIT_INVENTORY_REQUEST = 2;
    private static final int RESULT_OK = 1;

    MainContract.View view;
    InventoryDao inventoryDao;
    Context context;
    InventoryDatabase database = InventoryDatabase.getInstance(context);

    public MainPresenter(MainContract.View view, Context context) {
        this.view = view;
        this.context = context;
        this.inventoryDao = inventoryDao;
    }

    @Override
    public void deleteAllInventories(String name, double price, String supplier, int quantity) {
        view.showAlertDialog();
        database.inventoryDao().deleteAllInventories();

    }

    @Override
    public void update() {

    }

    @Override
    public void insert() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == ADD_INVENTORY_REQUEST && resultCode == RESULT_OK){
            String name = data.getStringExtra(EditActivity.EXTRA_NAME);
           // byte [] image = data.getByteArrayExtra(EditActivity.EXTRA_IMAGE);
            double price = data.getDoubleExtra(EditActivity.EXTRA_PRICE, 1);
            int quantity = data.getIntExtra(EditActivity.EXTRA_QUANTITY,1);
            String supplier = data.getStringExtra(EditActivity.EXTRA_SUPPLIER);

            Inventory inventory = new Inventory(name, quantity, price, supplier);
            database.inventoryDao().insert(inventory);

            Toast.makeText(this.context, "Product saved", Toast.LENGTH_SHORT).show();
        }else  if(requestCode == EDIT_INVENTORY_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(EditActivity.EXTRA_ID,-1);

            if (id == -1){
                Toast.makeText(this.context,"Inventory can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String name = data.getStringExtra(EditActivity.EXTRA_NAME);
           // byte [] image = data.getByteArrayExtra(EditActivity.EXTRA_IMAGE);
            double price = data.getDoubleExtra(EditActivity.EXTRA_PRICE, 1);
            int quantity = data.getIntExtra(EditActivity.EXTRA_QUANTITY,1);
            String supplier = data.getStringExtra(EditActivity.EXTRA_SUPPLIER);

            Inventory inventory = new Inventory(name, quantity, price, supplier);
            inventory.setId(id);
            database.inventoryDao().update(inventory);

        }else {
            Toast.makeText(this.context, "Product not saved", Toast.LENGTH_SHORT).show();
        }
    };
 }
