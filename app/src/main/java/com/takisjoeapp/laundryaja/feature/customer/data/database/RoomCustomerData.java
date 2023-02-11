package com.takisjoeapp.laundryaja.feature.customer.data.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.database.room.dao.CustomerDao;
import com.takisjoeapp.laundryaja.database.room.DatabaseRoom;

import java.util.List;

public class RoomCustomerData implements CustomerDao {
    private final CustomerDao customerDao;


    public RoomCustomerData(Application application) {
        DatabaseRoom room = DatabaseRoom.getInstance(application);
        customerDao = room.customerDao();
    }

    @Override
    public void insert(Customer customer) {
        //Memastikan customer belum pernah di Insert

        new InsertCustomerAsynTask(customerDao).execute(customer);
        System.out.println("Room Customer - Menambah Room"+customerDao.readAll().size());
    }

    @Override
    public void insertCustomer(Customer customer) {
        new AddCustomerAsynTask(customerDao).execute(customer);

    }

    @Override
    public void update(Customer customer) {
        new UpdateCustomerAsynTask(customerDao).execute(customer);
        System.out.println("Room Customer - Mengupdate Room");

    }

    @Override
    public void delete(Customer customer) {
        new DeleteCustomerAsynTask(customerDao).execute(customer);
        System.out.println("Room Customer - Menghapus Room");

    }

    @Override
    public void clearAll() {
        new ClearAllCustomerAsynTask(customerDao).execute();
        System.out.println("Room Customer - ClearAll Room");

    }


    @Override
    public LiveData<List<Customer>> read() {
        System.out.println("Room Customer - Mengambil semua data Room");

        return customerDao.read();
    }

    @Override
    public List<Customer> readAll() {
        return customerDao.readAll();
    }

    @Override
    public LiveData<Customer> myAccount(String uid) {
        return null;
    }

    private static class InsertCustomerAsynTask extends AsyncTask<Customer, Void, Void> {
        private final CustomerDao customerDao;

        public InsertCustomerAsynTask(CustomerDao customerDao) {
            this.customerDao = customerDao;
        }

        @Override
        protected Void doInBackground(Customer... customers) {
            customerDao.insert(customers[0]);
            return null;
        }
    }

    private static class UpdateCustomerAsynTask extends AsyncTask<Customer, Void, Void> {
        private final CustomerDao customerDao;

        public UpdateCustomerAsynTask(CustomerDao customerDao) {
            this.customerDao = customerDao;
        }

        @Override
        protected Void doInBackground(Customer... customers) {
            customerDao.update(customers[0]);

            return null;
        }
    }

    private static class DeleteCustomerAsynTask extends AsyncTask<Customer, Void, Void> {
        private final CustomerDao customerDao;

        public DeleteCustomerAsynTask(CustomerDao customerDao) {
            this.customerDao = customerDao;
        }

        @Override
        protected Void doInBackground(Customer... customers) {
            customerDao.delete(customers[0]);
            return null;
        }
    }

    private static class ClearAllCustomerAsynTask extends AsyncTask<Customer, Void, Void> {
        private final CustomerDao customerDao;

        public ClearAllCustomerAsynTask(CustomerDao customerDao) {
            this.customerDao = customerDao;
        }

        @Override
        protected Void doInBackground(Customer... customers) {
            customerDao.clearAll();
            return null;
        }
    }

    private static class AddCustomerAsynTask extends AsyncTask<Customer,Void,Void>{
        private final CustomerDao customerDao;

        public AddCustomerAsynTask(CustomerDao customerDao) {
            this.customerDao = customerDao;
        }

        @Override
        protected Void doInBackground(Customer... customers) {
            customerDao.insertCustomer(customers[0]);
            return null;
        }
    }
}
