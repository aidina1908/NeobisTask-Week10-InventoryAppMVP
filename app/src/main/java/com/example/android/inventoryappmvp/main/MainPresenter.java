package com.example.android.inventoryappmvp.main;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.example.android.inventoryappmvp.data.Inventory;
import com.example.android.inventoryappmvp.data.InventoryDao;
import java.util.List;


public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View mView;
    private final InventoryDao inventoryDao;

    public MainPresenter(MainContract.View view, InventoryDao inventoryDao) {
        this.mView = view;
        this.mView.setPresenter(this);
        this.inventoryDao = inventoryDao;
    }

    @Override
    public void start() {

    }

    @Override
    public void addNewInventory() {
        mView.showAddInventory();
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void populatePeople() {
        inventoryDao.findAllInventories().observeForever(new Observer<List<Inventory>>() {
            @Override
            public void onChanged(@Nullable List<Inventory> inventories) {
                mView.setInventories(inventories);
                if (inventories == null || inventories.size() < 1) {
                    mView.showEmptyMessage();
                }
            }
        });
    }

    @Override
    public void openEditScreen(Inventory inventory) {
        mView.showEditScreen(inventory.id);
    }


    @Override
    public void deleteAllInventories() {
        inventoryDao.deleteAllInventories();
    }
}