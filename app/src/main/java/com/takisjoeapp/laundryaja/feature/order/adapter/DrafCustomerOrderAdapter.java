package com.takisjoeapp.laundryaja.feature.order.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.takisjoeapp.laundryaja.R;
import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;

import java.util.List;

public class DrafCustomerOrderAdapter extends RecyclerView.Adapter<DrafCustomerOrderAdapter.ViewHolder>{

    private final List<Customer> customerList;
    private OnDrafCustomerOrderListener listener;

    public DrafCustomerOrderAdapter(List<Customer> customerList) {
        this.customerList = customerList;
    }

    @NonNull
    @Override
    public DrafCustomerOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer_order_draf, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrafCustomerOrderAdapter.ViewHolder holder, int position) {
        holder.tvNameCustomerOrderDraf.setText(customerList.get(position).getName());
        holder.tvPhoneCustomerOrderDraf.setText(customerList.get(position).getPhone());
        holder.layoutCustomerOrderDraf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,customerList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public void setListener(OnDrafCustomerOrderListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNameCustomerOrderDraf, tvPhoneCustomerOrderDraf;
        private LinearLayout layoutCustomerOrderDraf;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameCustomerOrderDraf = itemView.findViewById(R.id.tvNameCustomerOrderDraf);
            tvPhoneCustomerOrderDraf = itemView.findViewById(R.id.tvPhoneCustomerOrderDraf);
            layoutCustomerOrderDraf = itemView.findViewById(R.id.layoutCustomerOrderDraf);
        }
    }
}
