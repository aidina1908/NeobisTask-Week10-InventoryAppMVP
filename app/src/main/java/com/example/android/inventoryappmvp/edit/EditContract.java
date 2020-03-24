package com.example.android.inventoryappmvp.edit;

import com.example.android.inventoryappmvp.Inventory;

public class EditContract {
    interface Presenter{
      //  boolean validateInputs(String name, double price, String supplier, int quantity);
        void saveInventory(Inventory inventory);
        void update(Inventory inventory);
        void delete(String name, double price, String supplier, int quantity);

    }
    interface View{
        void showData(String name, double price, String supplier, int quantity);
        void initViews();
        void showAlertDialog();
        void close();


    }
}
