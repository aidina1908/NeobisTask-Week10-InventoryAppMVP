package com.example.android.inventoryappmvp.edit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.android.inventoryappmvp.data.Inventory;
import com.example.android.inventoryappmvp.R;
import com.example.android.inventoryappmvp.data.InventoryDatabase;
import com.example.android.inventoryappmvp.main.MainContract;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;


public class EditActivity extends AppCompatActivity implements EditContract.View {

    public static final int EXTRA_NAME = 10;
    public static final int EXTRA_PRICE = 11;
    public static final int EXTRA_SUPPLIER = 12;
    final int CAMERA_INTENT = 51;
    public static final String EXTRA_ID = "extra_id";

    private EditContract.Presenter mPresenter;
   // private ImageView mProductImage;
    //Button button;
    //Bitmap bmpImage;
    private EditText mNameEditText;
    private EditText mPriceEditText;
    private EditText mSupplierEditText;
    private EditText mQuantityEditText;
    private FloatingActionButton mFab;

    private TextInputLayout nameTextLayout;
    private TextInputLayout priceTextLayout;
    private TextInputLayout quantityTextLayout;
    private TextInputLayout supplierTextLayout;

    private Inventory inventory;
    private boolean mEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        inventory = new Inventory();
        checkMode();
        initViews();

        InventoryDatabase db = InventoryDatabase.getInstance(getApplication());
        mPresenter = new EditPresenter(this, db.inventoryDao());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    protected void onStart() {
        setTitle("Add Product");
        super.onStart();
        if (mEditMode) {
            mPresenter.getInventoryAndPopulate(inventory.id);
        }
    }

    private void checkMode() {
        setTitle("Edit Product");
        if (getIntent().getExtras() != null) {
            inventory.id = getIntent().getLongExtra(EXTRA_ID, 0);
            mEditMode = true;
        }
    }

    private void initViews() {
        mNameEditText = (EditText) findViewById(R.id.edit_text_name);
        mPriceEditText = (EditText) findViewById(R.id.edit_text_price);
        mSupplierEditText = (EditText) findViewById(R.id.edit_text_supplier);
        mQuantityEditText = (EditText) findViewById(R.id.edit_text_quantity);
       // mProductImage = (ImageView) findViewById(R.id.productImage);
        //button = (Button) findViewById(R.id.button_camera);

        nameTextLayout = (TextInputLayout) findViewById(R.id.nameTextLayout);
        priceTextLayout = (TextInputLayout) findViewById(R.id.priceTextLayout);
        quantityTextLayout = (TextInputLayout)findViewById(R.id.quantityTextLayout);
        supplierTextLayout = (TextInputLayout)findViewById(R.id.supplierTextLayout);

        mFab = (FloatingActionButton) findViewById(R.id.fabbutton);
        mFab.setImageResource(mEditMode ? R.drawable.ic_refresh : R.drawable.ic_done);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // inventory.image = mProductImage.getImageAlpha();
                inventory.name = mNameEditText.getText().toString();
               // inventory.price = Double.parseDouble(mPriceEditText.getText().toString());
               // inventory.price = Double.parseDouble(mPriceEditText.getText().toString());
                inventory.price = mPriceEditText.getText().toString();
                inventory.supplier = mSupplierEditText.getText().toString();
                inventory.quantity = mQuantityEditText.getText().toString();

                boolean valid = mPresenter.validate(inventory);
                if (!valid) return;
                if (mEditMode) {
                    mPresenter.update(inventory);
                } else {
                    mPresenter.save(inventory);
                }
            }
        });
    }

    @Override
    public void setPresenter(EditContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showErrorMessage(int field) {
        if (field == EXTRA_NAME) {
            mNameEditText.setError(getString(R.string.invalid_name));
        } else if (field == EXTRA_PRICE) {
            mPriceEditText.setError(getString(R.string.invalid_price));
        } else if (field == EXTRA_SUPPLIER) {
            mSupplierEditText.setError(getString(R.string.invalid_supplier));
        }
    }

    @Override
    public void clearPreErrors() {
        nameTextLayout.setErrorEnabled(false);
        priceTextLayout.setErrorEnabled(false);
        supplierTextLayout.setErrorEnabled(false);
        quantityTextLayout.setErrorEnabled(false);
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void populate(Inventory inventory) {
        this.inventory = inventory;
       // mProductImage.setImageResource(inventory.image);
        mNameEditText.setText(inventory.name);
        mPriceEditText.setText(String.valueOf(inventory.price));
        mSupplierEditText.setText(inventory.supplier);
        mQuantityEditText.setText(inventory.quantity);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                showAlertDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);}}

    public void showAlertDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete");
        alert.setMessage("Do you want delete product?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int i) {
              //  mPresenter.deleteInventory(long id);
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alert.create().show();
    }

    public void takePicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, CAMERA_INTENT);
        }
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMERA_INTENT:
                bmpImage = (Bitmap) data.getExtras().get("data");
                if (bmpImage != null) {
                    mProductImage.setImageBitmap(bmpImage);
                } else {
                    Toast.makeText(this, "Bitmap is null", Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }*/
}