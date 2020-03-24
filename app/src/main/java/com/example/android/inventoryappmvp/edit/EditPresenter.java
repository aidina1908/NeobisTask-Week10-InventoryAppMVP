package com.example.android.inventoryappmvp.edit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.android.inventoryappmvp.Inventory;
import com.example.android.inventoryappmvp.data.InventoryDao;
import com.example.android.inventoryappmvp.data.InventoryDatabase;
import com.example.android.inventoryappmvp.edit.EditContract;

import static android.app.Activity.RESULT_OK;

public class EditPresenter implements EditContract.Presenter {
    public static final String EXTRA_ID =
            "com.example.android.inventoryappmvp.EXTRA_ID";
    public static final String EXTRA_NAME =
            "com.example.android.inventoryappmvp.EXTRA_NAME";
    public static final String EXTRA_IMAGE =
            "com.example.android.inventoryappmvp.EXTRA_IMAGE";
    public static final String EXTRA_PRICE =
            "com.example.android.inventoryappmvp.EXTRA_PRICE";
    public static final String EXTRA_QUANTITY =
            "com.example.android.inventoryappmvp.EXTRA_QUANTITY";
    public static final String EXTRA_SUPPLIER =
            "com.example.android.inventoryappmvp.EXTRA_SUPPLIER";

    EditContract.View view;
    Context context;
    InventoryDatabase database = InventoryDatabase.getInstance(context);
    private final InventoryDao inventoryDao;

    private ImageView productImageView;
   // Button button;
    //Bitmap bmpImage;
    private EditText editTextName;
    private EditText editTextPrice;
    private EditText editTextSupplier;
    private NumberPicker numberPickerQuantity;


    public EditPresenter (EditContract.View view, InventoryDao inventoryDao){
        this.view = view;
      //  this.context = context;
        this.inventoryDao = inventoryDao;
    }


   public void start(){

   }

    @Override
    public void saveInventory(Inventory inventory){
        long ids = this.inventoryDao.insert(inventory);
        view.close();
    }

    @Override
    public void update(Inventory inventory){
        int ids = this.inventoryDao.update(inventory);
        view.close();
    }
    /*@Override
    public void saveInventory(){
        String name = editTextName.getText().toString();
        // byte[] image = productImageView.
        // Bitmap bitmap = ((BitmapDrawable)productImageView.getDrawable()).getBitmap();
        // Bitmap bitmap = productImageView.getDrawingCache();
        double price = Double.parseDouble(editTextPrice.getText().toString());
        int quantity = numberPickerQuantity.getValue();
        String supplier = editTextSupplier.getText().toString();

        if (name.trim().isEmpty() ||  supplier.trim().isEmpty()) {
            Toast.makeText(this.context, "Please insert all data", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        // data.putExtra(EXTRA_IMAGE,image);
        data.putExtra(EXTRA_PRICE, price);
        data.putExtra(EXTRA_QUANTITY, quantity);
        data.putExtra(EXTRA_SUPPLIER, supplier);
        data.putExtra(EXTRA_ID,id);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }*/


    /*  @Override
      public void delete(String name, double price, String supplier, int quantity){

      }*/
    @Override
    public void delete(String name, double price, String supplier, int quantity){
        view.showAlertDialog();
        Inventory inventory = new Inventory(name, quantity, price, supplier);
        database.inventoryDao().delete(inventory);
    }

}

