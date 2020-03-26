package com.example.android.inventoryappmvp;

import android.util.Patterns;

import java.util.regex.Pattern;

    public class Util {

        public static boolean isValidName(String name) {
            Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
            return patron.matcher(name).matches();
        }
        public static boolean isValidSupplier(String supplier) {
            Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
            return patron.matcher(supplier).matches();
        }
}
