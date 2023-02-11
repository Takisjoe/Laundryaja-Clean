package com.takisjoeapp.laundryaja.feature.order.present.ui.add;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.takisjoeapp.laundryaja.R;
import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.order.adapter.DrafCustomerOrderAdapter;
import com.takisjoeapp.laundryaja.feature.order.domain.entitas.Order;
import com.takisjoeapp.laundryaja.feature.order.domain.usecase.OnOrderUseCaseListener;
import com.takisjoeapp.laundryaja.util.address.Address;
import com.takisjoeapp.laundryaja.util.address.AddressSheetButtom;
import com.takisjoeapp.laundryaja.util.address.AddressUtils;
import com.takisjoeapp.laundryaja.util.debug.DebugLog;
import com.takisjoeapp.laundryaja.util.time.TimeUtils;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddOrderFragment extends Fragment {
    private final Order order = new Order();

    private Toolbar toolbarAddOrder;
    private CreateOrderViewModel createOrderViewModel;
    private RecyclerView rvSearchCustomerOrder;
    private TextInputLayout tilSearchCustomerOrder;
    private TextInputEditText catatanAddOrder, tieSearchCustomerOrder, tieNameCustomerOrder, tiePhoneCustomerOrder, tieAddressCustomerOrder;
    private TextView tvNameCustomerOrder, tvAddressCustomerOrder, tvPhoneCustomerOrder;
    private CircleImageView ivProfileCustomerOrder;
    private Button btnAddCustomerLayoutOrder, btnSaveCustomerOrder, btnAddOrderFinish;
    private TextView textView, tvDateDiterima, tvDateFinish;
    private CardView cardSellectedCustomer;

    private ImageView ivHideProfileCustomerOrder, ivHideCreateCustomer;
    private LinearLayout layoutSearchCustomer, layRegisterCustomerOrder;
    private ConstraintLayout layAddCustomerLayoutOrder, layProfileCustomer;
    private CalendarView calendarViewAddOrder;
    private ProgressBar proggesBarSearch;

    private String alamat = "";

    public static AddOrderFragment newInstance() {
        return new AddOrderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_order, container, false);
        rvSearchCustomerOrder = view.findViewById(R.id.rvSearchCustomerOrder);
        tieSearchCustomerOrder = view.findViewById(R.id.tieSearchCustomerOrder);
        layAddCustomerLayoutOrder = view.findViewById(R.id.layAddCustomerLayoutOrder);
        tvNameCustomerOrder = view.findViewById(R.id.tvNameCustomerOrder);
        tvAddressCustomerOrder = view.findViewById(R.id.tvAddressCustomerOrder);
        tvPhoneCustomerOrder = view.findViewById(R.id.tvPhoneCustomerOrder);
        ivProfileCustomerOrder = view.findViewById(R.id.ivProfileCustomerOrder);
        layoutSearchCustomer = view.findViewById(R.id.layoutSearchCustomer);
        btnAddCustomerLayoutOrder = view.findViewById(R.id.btnAddCustomerLayoutOrder);
        layProfileCustomer = view.findViewById(R.id.layProfileCustomer);
        layRegisterCustomerOrder = view.findViewById(R.id.layRegisterCustomerOrder);
        ivHideProfileCustomerOrder = view.findViewById(R.id.ivHideProfileCustomerOrder);
        ivHideCreateCustomer = view.findViewById(R.id.ivHideCreateCustomer);
        btnSaveCustomerOrder = view.findViewById(R.id.btnSaveCustomerOrder);
        tieNameCustomerOrder = view.findViewById(R.id.tieNameCustomerOrder);
        tiePhoneCustomerOrder = view.findViewById(R.id.tiePhoneCustomerOrder);
        tieAddressCustomerOrder = view.findViewById(R.id.tieAddressCustomerOrder);
        tilSearchCustomerOrder = view.findViewById(R.id.tilSearchCustomerOrder);
        cardSellectedCustomer = view.findViewById(R.id.cardSellectedCustomer);
        calendarViewAddOrder = view.findViewById(R.id.calendarViewAddOrder);
        toolbarAddOrder = view.findViewById(R.id.toolbarAddOrder);
        tvDateDiterima = view.findViewById(R.id.tvDateDiterima);
        tvDateFinish = view.findViewById(R.id.tvDateFinish);
        proggesBarSearch = view.findViewById(R.id.proggesBarSearch);
        catatanAddOrder = view.findViewById(R.id.catatanAddOrder);
        btnAddOrderFinish = view.findViewById(R.id.btnAddOrderFinish);
        textView = view.findViewById(R.id.textView);

        toolbarAddOrder.setTitle("Buat Order");
        toolbarAddOrder.setTitleTextColor(Color.WHITE);
        rvSearchCustomerOrder.setLayoutManager(new LinearLayoutManager(getContext()));

        cardSearchCustomer();
        getCatatan();

        proggesBarSearch.setVisibility(View.GONE);
        cardSellectedCustomer.setVisibility(View.VISIBLE);
        return view;
    }

    private void cardSearchCustomer() {
        layoutSearchCustomer.setVisibility(View.VISIBLE);
        layAddCustomerLayoutOrder.setVisibility(View.GONE); //Tombol untuk mengaktifkan Create Customer
        layProfileCustomer.setVisibility(View.GONE);
        layRegisterCustomerOrder.setVisibility(View.GONE);

        tilSearchCustomerOrder.setHelperText("Cari berdasarkan nama atau whatsapp pelanggan");

        btnAddCustomerLayoutOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardCreateCustomer();
            }
        });

    }

    private void cardCreateCustomer() {
        layRegisterCustomerOrder.setVisibility(View.VISIBLE);
        layProfileCustomer.setVisibility(View.GONE);
        layoutSearchCustomer.setVisibility(View.GONE);
        layAddCustomerLayoutOrder.setVisibility(View.GONE);

        tieAddressCustomerOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddressSheetButtom(getActivity().getApplication(), new AddressSheetButtom.OnShowListener() {
                    @Override
                    public void onAddress(String id, String address) {
                        tieAddressCustomerOrder.setText(address);
                        alamat = id;
                    }

                    @Override
                    public void onAddressComplete(Address provinsi, Address kota, Address kecamatan, Address kelurahan, String detail) {

                    }
                }).show(getChildFragmentManager(), null);
            }
        });

        btnSaveCustomerOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createOrderViewModel.addNewCustomer(
                        tieNameCustomerOrder.getText().toString(), tiePhoneCustomerOrder.getText().toString(), alamat, new OnOrderUseCaseListener.DrafNewCustomerOrder() {
                            @Override
                            public void onSuccess(Customer customer) {
                                Toast.makeText(getContext(), "Tersimpan", Toast.LENGTH_SHORT).show();
                                cardProfileCustomer(customer);
                            }

                            @Override
                            public void onFailure(String Error) {
                                if (Error.contains("Nama")) {
                                    Toast.makeText(getContext(), Error, Toast.LENGTH_SHORT).show();
                                }
                                if (Error.contains("Whatsapp")) {
                                    Toast.makeText(getContext(), Error, Toast.LENGTH_SHORT).show();
                                }
                                if (Error.contains("Alamat")) {
                                    Toast.makeText(getContext(), Error, Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            }
        });

        ivHideCreateCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardSearchCustomer();
            }
        });
    }

    private void cardProfileCustomer(@NonNull Customer customer) {
        layProfileCustomer.setVisibility(View.VISIBLE);
        layoutSearchCustomer.setVisibility(View.GONE);
        layRegisterCustomerOrder.setVisibility(View.GONE);
        layAddCustomerLayoutOrder.setVisibility(View.GONE);

        ivHideProfileCustomerOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardSearchCustomer();
            }
        });

//        Customer customer = mViewModel.getPrefCustomer();
//        if (customer != null) {
//            mViewModel.saveOrder().setCustomerId(customer.getId());
        tvNameCustomerOrder.setText(customer.getName());
        tvAddressCustomerOrder.setText(AddressUtils.parseText(getActivity().getApplication(), customer.getAddress()));
        tvPhoneCustomerOrder.setText(customer.getPhone());
//        } else {
//            cardSearchCustomer();
//        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(AddOrderViewModel.class);
        createOrderViewModel = new ViewModelProvider(this).get(CreateOrderViewModel.class);
//        mViewModel.callAllCustomer(getViewLifecycleOwner());

        tieSearchCustomerOrder.addTextChangedListener(createOrderViewModel.selectCustomer(getViewLifecycleOwner(), new OnOrderUseCaseListener.DrafCustomerOrder() {
            @Override
            public void onSuccess(DrafCustomerOrderAdapter adapter) {
                rvSearchCustomerOrder.setAdapter(adapter);

                if (adapter.getItemCount() == 0) {
                    btnAddCustomerLayoutOrder.setVisibility(View.VISIBLE);
                }else {
                    btnAddCustomerLayoutOrder.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoad(boolean isLoad, int detik) {
                if (isLoad) {
                    proggesBarSearch.setVisibility(View.VISIBLE);
                }else {
                    proggesBarSearch.setVisibility(View.GONE);
                }
            }

            @Override
            public void onSelected(Customer customer, boolean isSelected, int count) {
                if (isSelected) {
                    DebugLog.DEBUG("Selected " + customer.getName(), "Draf Customer");
                    cardProfileCustomer(customer);
                } else {
                    cardSearchCustomer();
                }

                if (count == 0) {
//                    proggesBarSearch.setVisibility(View.VISIBLE);
                    layAddCustomerLayoutOrder.setVisibility(View.VISIBLE);
                } else {
//                    layAddCustomerLayoutOrder.setVisibility(View.GONE);
                    proggesBarSearch.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFailure(String Error) {

            }
        }));

        btnAddOrderFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order.setCatatan(catatanAddOrder.getText().toString());

                createOrderViewModel.createOrder(
                        "Testing Customer",
                        order.getOrderDate(),
                        order.getCompletionDate(),
                        order.getCatatan(),
                        new OnOrderUseCaseListener.CreateOrder() {
                            @Override
                            public void onSuccess(Order order) {
                                Toast.makeText(getContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(String Error) {
                                Toast.makeText(getContext(), "Gagal", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
//                mViewModel.saveOrder().setCatatan(catatanAddOrder.getText().toString());
//
//                mViewModel.addOrder(new OrderUseCaseImpl.OnAddOrderListener() {
//                    @Override
//                    public void onSuccess() {
//                        getActivity().onBackPressed();
//                    }
//
//                    @Override
//                    public void onFailure(String error) {
//                        Toast.makeText(getContext(), "Gagal : " + error, Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });
        viewCalendar();
        viewDetail();
    }

    private void viewDetail() {
        tvDateDiterima.setText(TimeUtils.getCurrentDate("dd MMMM yyyy") + "\n" + TimeUtils.getCurrentTime("HH:mm"));
//        mViewModel.saveOrder().setOrderDate(TimeUtils.getTimestamp());

    }

    private void viewCalendar() {
        //Start
        order.setOrderDate(TimeUtils.getTimestamp());

        //Finish
        calendarViewAddOrder.setMinDate(TimeUtils.getTimestamp());
        calendarViewAddOrder.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                long selectedDateInMillis = calendar.getTimeInMillis();
                order.setCompletionDate(selectedDateInMillis);
                createOrderViewModel.saveOrder(selectedDateInMillis, order.getCatatan());
//                mViewModel.saveOrder().setCompletionDate(selectedDateInMillis);
                tvDateFinish.setText(TimeUtils.formatDate(selectedDateInMillis, "dd MMMM yyyy") + "\n" + TimeUtils.formatDate(selectedDateInMillis, "HH:mm"));
            }
        });
    }

    private void getCatatan() {
        catatanAddOrder.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                order.setCatatan(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
////        Customer customer = mViewModel.getPrefCustomer();
//        if (customer != null) {
//            cardProfileCustomer(customer);
//            tvNameCustomerOrder.setText(customer.getName());
//            tvAddressCustomerOrder.setText(customer.getAddress());
//            tvPhoneCustomerOrder.setText(customer.getPhone());
//        }
    }


}