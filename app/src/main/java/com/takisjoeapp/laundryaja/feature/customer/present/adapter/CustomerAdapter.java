package com.takisjoeapp.laundryaja.feature.customer.present.adapter;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.takisjoeapp.laundryaja.R;
import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.util.address.AddressUtils;
import com.takisjoeapp.laundryaja.util.time.TimeUtils;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder>  {

    private Application application;
    private OnItemCustomerClickListener clickListener;

    private final List<Customer> customerList;

    public void setClickListener(OnItemCustomerClickListener clickListener) {
        this.clickListener = clickListener;
    }


    public CustomerAdapter(Application application, List<Customer> customerList) {
        this.application = application;
        this.customerList = customerList;
    }

    @NonNull
    @Override
    public CustomerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.ViewHolder holder, int position) {
        holder.tv_customer_name.setText(customerList.get(position).getName());
        holder.tv_customer_username.setText(AddressUtils.parseText(application,customerList.get(position).getAddress()));
        holder.tv_customer_phone.setText(customerList.get(position).getPhone());
        holder.tv_customer_address.setText(TimeUtils.getRelativeLastTime(customerList.get(position).getCreatedAt()));

        holder.layoutItemCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(v,customerList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_customer_name;
        private final TextView tv_customer_username;
        private final TextView tv_customer_phone;
        private final TextView tv_customer_address;
        private final LinearLayout layoutItemCustomer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_customer_name = itemView.findViewById(R.id.tv_customer_name);
            tv_customer_username = itemView.findViewById(R.id.tv_customer_username);
            tv_customer_phone = itemView.findViewById(R.id.tv_customer_phone);
            tv_customer_address = itemView.findViewById(R.id.tv_customer_address);
            layoutItemCustomer = itemView.findViewById(R.id.layoutItemCustomer);
        }
    }
}
