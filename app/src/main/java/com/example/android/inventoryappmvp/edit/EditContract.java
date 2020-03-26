package com.example.android.inventoryappmvp.edit;

import android.view.MenuItem;
import com.example.android.inventoryappmvp.BasePresenter;
import com.example.android.inventoryappmvp.BaseView;
import com.example.android.inventoryappmvp.data.Inventory;

public class EditContract {
    interface Presenter extends BasePresenter {
        void save(Inventory inventory);

        boolean validate(Inventory inventory);

        void deleteInventory(long id);

        void getInventoryAndPopulate(long id);

        void update(Inventory inventory);
    }

    interface View extends BaseView<Presenter> {

        void showErrorMessage(int field);

        void clearPreErrors();

        boolean onOptionsItemSelected(MenuItem item);

        void close();

        void populate(Inventory inventory);
    }
}
