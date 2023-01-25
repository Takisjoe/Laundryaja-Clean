package com.takisjoeapp.laundryaja.feature.customer.present.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.takisjoeapp.laundryaja.R;
import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.util.time.TimeUtils;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<Customer> customerList;

    public CustomerAdapter(List<Customer> customerList) {
        this.customerList = customerList;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context, "Is click", Toast.LENGTH_SHORT).show();
    }

    @NonNull
    @Override
    public CustomerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.ViewHolder holder, int position) {
        holder.tv_customer_name.setText(customerList.get(position).getName());
        holder.tv_customer_username.setText(customerList.get(position).getUsername());
        holder.tv_customer_phone.setText(customerList.get(position).getPhone());
        holder.tv_customer_address.setText(TimeUtils.getRelativeLastTime(customerList.get(position).getCreatedAt()));
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_customer_name, tv_customer_username, tv_customer_phone, tv_customer_address;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_customer_name = itemView.findViewById(R.id.tv_customer_name);
            tv_customer_username = itemView.findViewById(R.id.tv_customer_username);
            tv_customer_phone = itemView.findViewById(R.id.tv_customer_phone);
            tv_customer_address = itemView.findViewById(R.id.tv_customer_address);
        }
    }
}
