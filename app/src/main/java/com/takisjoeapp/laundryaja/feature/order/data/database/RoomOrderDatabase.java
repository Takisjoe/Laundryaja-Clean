package com.takisjoeapp.laundryaja.feature.order.data.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.takisjoeapp.laundryaja.database.room.DatabaseRoom;
import com.takisjoeapp.laundryaja.database.room.dao.CustomerDao;
import com.takisjoeapp.laundryaja.database.room.dao.OrderDao;
import com.takisjoeapp.laundryaja.feature.order.domain.entitas.Order;
import com.takisjoeapp.laundryaja.util.debug.DebugLog;

import java.util.List;

public class RoomOrderDatabase implements OrderDao {
    String LOG = "RoomOrderDatabase";
    private final OrderDao orderDao;

    public RoomOrderDatabase(Application application) {
        DatabaseRoom room = DatabaseRoom.getInstance(application);
        orderDao = room.orderDao();
        DebugLog.RULES("Memanggil divisi RoomOrderDatabase",LOG);
    }

    @Override
    public void insert(Order order) {
        new InsertOrderAsynTask(orderDao).execute(order);
    }

    @Override
    public void replace(Order order) {
        new ReplaceOrderAsyncTask(orderDao).execute(order);
    }


    @Override
    public void update(Order order) {
        new UpdateOrderAsyncTask(orderDao).execute(order);
    }


    @Override
    public void delete(Order order) {
        new DeleteOrderAsyncTask(orderDao).execute(order);
    }


    @Override
    public LiveData<List<Order>> read() {
        return orderDao.read();
    }


    @Override
    public LiveData<Order> searchById(String id) {
        return orderDao.searchById(id);
    }

    private static class InsertOrderAsynTask extends AsyncTask<Order, Void, Void> {
        private final OrderDao orderDao;

        public InsertOrderAsynTask(OrderDao orderDao) {
            this.orderDao = orderDao;
        }

        @Override
        protected Void doInBackground(Order... orders) {
            orderDao.insert(orders[0]);
            return null;
        }
    }

    private static class ReplaceOrderAsyncTask extends AsyncTask<Order, Void, Void> {
        private final OrderDao orderDao;

        public ReplaceOrderAsyncTask(OrderDao orderDao) {
            this.orderDao = orderDao;
        }

        @Override
        protected Void doInBackground(Order... orders) {
            orderDao.replace(orders[0]);
            return null;
        }
    }

    private static class UpdateOrderAsyncTask extends AsyncTask<Order, Void, Void> {
        private final OrderDao orderDao;

        public UpdateOrderAsyncTask(OrderDao orderDao) {
            this.orderDao = orderDao;
        }

        @Override
        protected Void doInBackground(Order... orders) {
            orderDao.update(orders[0]);
            return null;
        }


    }

    private static class DeleteOrderAsyncTask extends AsyncTask<Order, Void, Void> {
        private final OrderDao orderDao;

        public DeleteOrderAsyncTask(OrderDao orderDao) {
            this.orderDao = orderDao;
        }

        @Override
        protected Void doInBackground(Order... orders) {
            orderDao.delete(orders[0]);
            return null;
        }
    }

}
