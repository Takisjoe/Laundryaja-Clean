package com.takisjoeapp.laundryaja.database.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.takisjoeapp.laundryaja.database.room.dao.CustomerDao;
import com.takisjoeapp.laundryaja.database.room.dao.OrderDao;
import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.customer.helper.CustomerBuilder;
import com.takisjoeapp.laundryaja.feature.order.domain.entitas.Order;

@Database(entities = {Customer.class, Order.class}, version = 5, exportSchema = false)
@TypeConverters({ListStringConverter.class, NoteTypeConverter.class})
public abstract class DatabaseRoom extends RoomDatabase {

    private static DatabaseRoom instance;

    public abstract CustomerDao customerDao();

    public abstract OrderDao orderDao();

    public static synchronized DatabaseRoom getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            DatabaseRoom.class, "room_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    private static final RoomDatabase.Callback callback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsynTask(instance).execute();
        }
    };

    private static class PopulateDbAsynTask extends AsyncTask<Void, Void, Void> {
        private final CustomerDao customerDao;
        private final OrderDao orderDao;

        public PopulateDbAsynTask(@NonNull DatabaseRoom db) {
            customerDao = db.customerDao();
            orderDao = db.orderDao();
        }

        @Nullable
        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < 3; i++) {
                Customer customer = new CustomerBuilder()
                        .setId("id-"+i)
                        .setName("Vin-"+i)
                        .build();
            }

            for (int i = 0; i < 3; i++) {
                //Create Order
            }
            return null;
        }
    }
}
