package com.example.android.inventoryappmvp.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.android.inventoryappmvp.Inventory;


@Database(entities = {Inventory.class}, version = 2)
public abstract class InventoryDatabase extends RoomDatabase {

    private static InventoryDatabase instance;

    public abstract InventoryDao inventoryDao();

    public static InventoryDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    InventoryDatabase.class, "inventory_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;

    }
}