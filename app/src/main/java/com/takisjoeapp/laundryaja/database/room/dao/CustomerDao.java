package com.takisjoeapp.laundryaja.database.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;

import java.util.List;

@Dao
public interface CustomerDao {

    @Insert
    void insert(Customer customer);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCustomer(Customer customer);

    @Update
    void update(Customer customer);

    @Delete
    void delete(Customer customer);

    @Query("DELETE FROM customer_table")
    void clearAll();

    @Query("SELECT * FROM customer_table ORDER BY name_customer")
    LiveData<List<Customer>> read();

    @Query("SELECT * FROM customer_table ORDER BY id")
    List<Customer> readAll();

    @Query("SELECT * FROM customer_table WHERE uid_customer LIKE :uid+'%'")
    LiveData<Customer> myAccount(String uid);
}