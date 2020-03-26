package com.example.android.inventoryappmvp.data;

import android.graphics.Bitmap;
import android.text.Editable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_table")
public class Inventory {

    @PrimaryKey(autoGenerate = true)
    public long id;

   // public byte[] image;

    public String name;

    public String quantity;

    public String  price;

    public String supplier;

    public Inventory(String name, String quantity, String  price, String supplier) {
        this.name = name;
       // this.image = image;
        this.quantity = quantity;
        this.price = price;
        this.supplier = supplier;

    }

    @Ignore
    public Inventory(){
        this.name = "";
        this.supplier = "";
        this.price = "";
        this.quantity = "";

    }
}
