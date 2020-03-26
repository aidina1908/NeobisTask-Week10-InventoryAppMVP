package com.example.android.inventoryappmvp.main;

import android.view.MenuItem;
import com.example.android.inventoryappmvp.BasePresenter;
import com.example.android.inventoryappmvp.BaseView;
import com.example.android.inventoryappmvp.data.Inventory;

import java.util.List;

public interface MainContract {

    interface Presenter extends BasePresenter {

        void addNewInventory();

        void result(int requestCode, int resultCode);

        void populatePeople();

        void openEditScreen(Inventory inventory);

        void deleteAllInventories();
    }

    interface View extends BaseView<Presenter> {

       void showAddInventory();

        void setInventories(List<Inventory> inventories);

        void showEditScreen(long id);

        void showEmptyMessage();

        void showAlertDialog();
    }

    interface OnItemClickListener {

        void clickItem(Inventory inventory);

        boolean onOptionsItemSelected(MenuItem item);

    }
}
