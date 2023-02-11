package com.takisjoeapp.laundryaja.feature.customer.present.ui.profile;

import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.takisjoeapp.laundryaja.R;
import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.customer.present.CustomerSharedViewModel;
import com.takisjoeapp.laundryaja.feature.customer.present.ui.main.CustomerFragment;
import com.takisjoeapp.laundryaja.feature.order.present.OrderActivity;
import com.takisjoeapp.laundryaja.util.address.AddressUtils;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileCustomerFragment extends Fragment {

    private ProfileCustomerViewModel mViewModel;
    private CustomerFragment fragment;
    private TextView tvNameCustomer, tvInfo1Customer, tvInfo2Customer;
    private ImageView ivProfileCustomer;
    private CircleImageView ivProfileCustomerCircle;
    private Button btnAddOrderCustomer, btnAddReportCustomer;
    private Toolbar tbProfileCustomer;


    public static ProfileCustomerFragment newInstance() {
        return new ProfileCustomerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_customer, container, false);
        tvNameCustomer = view.findViewById(R.id.tvNameCustomer);
        tvInfo1Customer = view.findViewById(R.id.tvInfo1Customer);
        tvInfo2Customer = view.findViewById(R.id.tvInfo2Customer);
//        ivProfileCustomer = view.findViewById(R.id.ivProfileCustomer);
        ivProfileCustomerCircle = view.findViewById(R.id.ivProfileCustomer);
        btnAddOrderCustomer = view.findViewById(R.id.btnAddOrderCustomer);
        btnAddReportCustomer = view.findViewById(R.id.btnAddReportCustomer);
        tbProfileCustomer = view.findViewById(R.id.tbProfileCustomer);

        tbProfileCustomer.setTitle("Pelanggan");
        tbProfileCustomer.setTitleTextColor(Color.WHITE);
        tbProfileCustomer.setNavigationIcon(R.drawable.ic_round_arrow_back_ios_new_black);
        tbProfileCustomer.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the back button click here
                getActivity().onBackPressed();
//                finish();
            }
        });



//        Picasso.get().load(R.mipmap.ic_person).into(ivProfileCustomer);
//        Bitmap bitmap = ((BitmapDrawable) ivProfileCustomer.getDrawable()).getBitmap();
//        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
//        roundedBitmapDrawable.setCircular(true);
//        ivProfileCustomer.setImageDrawable(roundedBitmapDrawable);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileCustomerViewModel.class);

        // TODO: Use the ViewModel

        CustomerSharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(CustomerSharedViewModel.class);
        sharedViewModel.getSelectedCustomer().observe(getViewLifecycleOwner(), new Observer<Customer>() {
            @Override
            public void onChanged(Customer customer) {
                tvNameCustomer.setText(customer.getName());
                tvInfo1Customer.setText(AddressUtils.parseText(getActivity().getApplication(), customer.getAddress()));
                tvInfo2Customer.setText(customer.getPhone());
                Toast.makeText(getContext(), customer.getName(), Toast.LENGTH_SHORT).show();

                if (customer.getPhoto()!=null) {
                    Picasso.get()
                            .load(customer.getPhoto())
                            .into(ivProfileCustomerCircle);
                }else {
                    Picasso.get()
                            .load("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_640.png")
                            .into(ivProfileCustomerCircle);
                }
            }
        });

        btnAddOrderCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), OrderActivity.class));

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

    }
}