package com.example.android.inventoryappmvp.edit;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android.inventoryappmvp.Inventory;
import com.example.android.inventoryappmvp.R;
import com.example.android.inventoryappmvp.data.InventoryDatabase;


public class EditActivity extends AppCompatActivity implements EditContract.View {
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

    EditContract.Presenter presenter;
    InventoryDatabase database;
    private Inventory inventory;

    private ImageView productImageView;
    //Button button;
    Bitmap bmpImage;
    private EditText editTextName;
    private EditText editTextPrice;
    private EditText editTextSupplier;
    private NumberPicker numberPickerQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);
        initViews();
//        presenter = new EditPresenter(this,getApplicationContext());
       // InventoryDatabase.getInstance(getApplicationContext()).inventoryDao().insert(inventory);

        numberPickerQuantity.setMinValue(1);
        numberPickerQuantity.setMaxValue(100);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Product");
            editTextName.setText(intent.getStringExtra(EXTRA_NAME));
            //   productImageView.setImage(DataConverter.convertImage2ByteArray(bmpImage));
            // productImageView.setImageBitmap(bmpImage);
            editTextPrice.setText(String.valueOf(intent.getDoubleExtra(EXTRA_PRICE, 1)));
            numberPickerQuantity.setValue(intent.getIntExtra(EXTRA_QUANTITY, 1));
            editTextSupplier.setText((intent.getStringExtra(EXTRA_SUPPLIER)));
        } else {
            setTitle("Add Product");

        }
    }

    @Override
    public void initViews(){
        productImageView = findViewById(R.id.image);
        // button = findViewById(R.id.button_camera);
        bmpImage = null;
        editTextName = findViewById(R.id.edit_text_name);
        editTextPrice = findViewById(R.id.edit_text_price);
        editTextSupplier = findViewById(R.id.edit_text_supplier);
        numberPickerQuantity = findViewById(R.id.number_picker_quantity);
    }


    @Override
    public void showData(String name, double price, String supplier, int quantity){
        editTextName.setText(name);
        editTextPrice.setText(String.valueOf(price));
        editTextSupplier.setText(supplier);
        numberPickerQuantity.setValue(quantity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                presenter.saveInventory(inventory);
                return true;
            case R.id.action_delete:
                showAlertDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void showAlertDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete");
        alert.setMessage("Do you want delete product?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int i) {
               // database.inventoryDao().delete();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }

        });
        alert.create().show();
    }

    @Override
    public void close(){
        finish();
    }
}
