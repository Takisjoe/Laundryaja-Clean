package com.takisjoeapp.laundryaja.database.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.takisjoeapp.laundryaja.feature.order.domain.entitas.Order;

import java.util.List;

@Dao
public interface OrderDao {

    @Insert
    void insert(Order order);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void replace(Order order);

    @Update
    void update(Order order);

    @Delete
    void delete(Order order);

    @Query("SELECT * FROM order_table ORDER BY completionDate_order")
    LiveData<List<Order>> read();

    @Query("SELECT * FROM order_table WHERE id LIKE :id+'%'")
    LiveData<Order> searchById(String id);
}
