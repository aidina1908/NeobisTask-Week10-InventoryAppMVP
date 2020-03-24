package com.example.android.inventoryappmvp.main;

import android.content.Intent;

public class MainContract {
    interface Presenter {
        void deleteAllInventories(String name, double price, String supplier, int quantity);
        void onActivityResult(int requestCode, int resultCode, Intent data);
        void update();
        void insert();

    }

    interface View{
        void showAlertDialog();

    }
}
