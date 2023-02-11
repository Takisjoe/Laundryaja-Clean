package com.takisjoeapp.laundryaja.feature.order.present.adapter;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.takisjoeapp.laundryaja.R;
import com.takisjoeapp.laundryaja.feature.order.domain.entitas.Order;
import com.takisjoeapp.laundryaja.util.qrcode.QRUtil;
import com.takisjoeapp.laundryaja.util.time.TimeUtils;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private Application application;
    private final List<Order> orderList;
    private OnItemOrderClickListener onItemOrderClickListener;

    public OrderAdapter(Application application, List<Order> orderList) {
        this.application = application;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        holder.ivQROrder.setImageBitmap(QRUtil.generateQRCode(orderList.get(position).getId()));
        holder.tvNameCustomer.setText(orderList.get(position).getId());
        holder.tvDeadlineOrder.setText(TimeUtils.getRelativeTimeToCome(orderList.get(position).getCompletionDate()));
        holder.tvInfoOrder.setText(orderList.get(position).getStatus());
    }

    public void addOnItemOrderClickListener(OnItemOrderClickListener onItemOrderClickListener) {
        this.onItemOrderClickListener = onItemOrderClickListener;
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvNameCustomer;
        private final TextView tvDeadlineOrder;
        private final TextView tvInfoOrder;
        private final ImageView ivQROrder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameCustomer = itemView.findViewById(R.id.tvNameCustomerItemOrder);
            tvDeadlineOrder = itemView.findViewById(R.id.tvDeadlineOrder);
            tvInfoOrder = itemView.findViewById(R.id.tvInfoOrder);
            ivQROrder = itemView.findViewById(R.id.ivQROrder);
        }
    }
}
