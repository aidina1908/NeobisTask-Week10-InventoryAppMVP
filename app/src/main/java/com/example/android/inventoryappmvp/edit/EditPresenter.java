package com.example.android.inventoryappmvp.edit;

import android.widget.EditText;

import com.example.android.inventoryappmvp.R;
import com.example.android.inventoryappmvp.data.Inventory;
import com.example.android.inventoryappmvp.Util;
import com.example.android.inventoryappmvp.data.InventoryDao;

public class EditPresenter implements EditContract.Presenter {
    public static final int EXTRA_NAME = 10;
    public static final int EXTRA_PRICE = 11;
    public static final int EXTRA_SUPPLIER = 12;

    private EditText mNameEditText;
    private EditText mPriceEditText;
    private EditText mSupplierEditText;
    private EditText mQuantityEditText;

    private final EditContract.View mView;
    private final InventoryDao inventoryDao;

    public EditPresenter(EditContract.View mMainView, InventoryDao inventoryDao) {
        this.mView = mMainView;
        this.mView.setPresenter(this);
        this.inventoryDao = inventoryDao;
    }

    @Override
    public void start() {
    }

    @Override
    public void save(Inventory inventory) {
        long ids = this.inventoryDao.insertInventory(inventory);
        mView.close();
    }

    @Override
    public void update(Inventory inventory) {
        int ids = this.inventoryDao.updateInventory(inventory);
        mView.close();
    }

    @Override
    public void deleteInventory(long inventoryId){
        Inventory inventory = inventoryDao.findInventory(inventoryId);
        //inventoryDao.deleteInventory(inventory);
         inventoryDao.deleteInventory(inventory);
    }

    @Override
    public boolean validate(Inventory inventory) {
        mView.clearPreErrors();
        if (inventory.name.isEmpty() || !Util.isValidName(inventory.name)) {
            mView.showErrorMessage(EXTRA_NAME);
            return false;
        }
        if (inventory.supplier.isEmpty() || !Util.isValidSupplier(inventory.supplier)) {
            mView.showErrorMessage(EXTRA_SUPPLIER);
            return false;
        }
        return true;
    }

    @Override
    public void getInventoryAndPopulate(long id) {
        Inventory inventory = inventoryDao.findInventory(id);
        if (inventory != null) {
            mView.populate(inventory);
        }
    }
}