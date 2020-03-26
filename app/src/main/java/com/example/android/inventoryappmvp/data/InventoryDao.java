package com.example.android.inventoryappmvp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
    public interface InventoryDao {

        @Insert(onConflict = IGNORE)
        long insertInventory(Inventory inventory);

        @Update
        int updateInventory(Inventory inventory);

        @Update
        void updateInventory(List<Inventory> inventories);

        @Delete
        void deleteInventory(Inventory inventory);

        @Query("SELECT * FROM product_table WHERE id=:id")
        Inventory findInventoryById(String id);

        @Query("SELECT * FROM product_table WHERE id = :id")
        Inventory findInventory(long id);

        @Query("DELETE FROM product_table WHERE name = :name  AND price=:price AND quantity = :quantity AND supplier = :supplier")
        void deleteInventory(String name, double price, int quantity, String supplier);

        @Query("DELETE FROM product_table")
        void deleteAllInventories();

        @Query("SELECT * FROM product_table ORDER BY quantity DESC")
        LiveData<List<Inventory>>findAllInventories();
}
