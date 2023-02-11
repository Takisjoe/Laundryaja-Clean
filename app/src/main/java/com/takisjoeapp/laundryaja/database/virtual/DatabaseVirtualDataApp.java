package com.takisjoeapp.laundryaja.database.virtual;


import android.content.Context;

import com.takisjoeapp.laundryaja.util.debug.DebugLog;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseVirtualDataApp {
    String LOG = "VirtualDataAPP";
    private final Context context;

    public DatabaseVirtualDataApp(Context context) {
        this.context = context;
    }

    public void create(Map<String,Object> map) {
        DebugLog.DEBUG("Create data", LOG);
        VirtualDatabase dataApp = new VirtualDatabase(context);
        dataApp.create(map);
    }

    public List<VirtualDataModel> read() {
        DebugLog.DEBUG("Read data", LOG);

        VirtualDatabase dataApp = new VirtualDatabase(context);
        return dataApp.read();
    }

    public void update(String id, Map<String,Object> map) {
        DebugLog.DEBUG("Update data "+id, LOG);

        VirtualDatabase dataApp = new VirtualDatabase(context);
        dataApp.update(id,map);
    }

    public void delete(String id) {
        DebugLog.DEBUG("Delete data "+id, LOG);

        VirtualDatabase dataApp = new VirtualDatabase(context);
        dataApp.delete(id);
    }

    public void removeAll() {
        DebugLog.DEBUG("RemoveAll data", LOG);

        VirtualDatabase dataApp = new VirtualDatabase(context);
        dataApp.removeAll();
    }
}
