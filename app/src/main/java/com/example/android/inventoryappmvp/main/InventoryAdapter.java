package com.example.android.inventoryappmvp.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.inventoryappmvp.data.Inventory;
import com.example.android.inventoryappmvp.R;
import java.util.ArrayList;
import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ViewHolder> {
    private List<Inventory> mValues;
        private MainContract.OnItemClickListener mOnItemClickListener;

        public InventoryAdapter(MainContract.OnItemClickListener onItemClickListener) {
            mValues = new ArrayList<>();
            mOnItemClickListener = onItemClickListener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
           // holder.productImageView.setImageResource(mValues.get(position).image);
            holder.nameTextView.setText(mValues.get(position).name);
            holder.priceTextView.setText(String.valueOf(mValues.get(position).price));
            holder.supplierTextView.setText(mValues.get(position).supplier);
            holder.quantityTextView.setText(mValues.get(position).quantity);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.clickItem(holder.mItem);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public void setValues(List<Inventory> values) {
            mValues = values;
            notifyDataSetChanged();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            //public final ImageView productImageView;
            public final TextView nameTextView;
            public final TextView priceTextView;
            public final TextView supplierTextView;
            public final TextView quantityTextView;
            public Inventory mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
               // productImageView = (ImageView) view.findViewById(R.id.productImage);
                nameTextView = (TextView) view.findViewById(R.id.product_name);
                priceTextView = (TextView) view.findViewById(R.id.productPrice);
                supplierTextView = (TextView) view.findViewById(R.id.product_supplier);
                quantityTextView = (TextView) view.findViewById(R.id.productQuantity);

            }
        }
}