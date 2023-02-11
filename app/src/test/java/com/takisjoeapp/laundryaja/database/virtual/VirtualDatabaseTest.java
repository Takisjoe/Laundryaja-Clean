package com.takisjoeapp.laundryaja.database.virtual;

import android.content.Context;

import com.takisjoeapp.laundryaja.util.debug.ModeService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class VirtualDatabaseTest {

    @Mock
    Context context;

    @Test
    public void testCreate() {
        DatabaseVirtualDataApp database = new DatabaseVirtualDataApp(context);
        Map<String, Object> map = new HashMap<>();
        map.put("Nama", "Hyco");
        map.put("Umur", 10);
        database.create(map);
    }

    @Test
    public void testRead() {
        DatabaseVirtualDataApp dataApp = new DatabaseVirtualDataApp(context);
        List<VirtualDataModel> read = dataApp.read();
        for (int i = 0; i < read.size(); i++) {
            System.out.print(read.get(i).getId());
            System.out.println(read.get(i).getObject().toString());
        }
    }

    @Test
    public void testUpdate() {
        ModeService.DEBUG.setContext(context);
        DatabaseVirtualDataApp dataApp = new DatabaseVirtualDataApp(ModeService.DEBUG.getContext());
        Map<String, Object> map = new HashMap<>();
        map.put("Nama", "Vin");
        dataApp.update("ID-0", map);
        testRead();
    }

    @Test
    public void testDelete() {
        DatabaseVirtualDataApp dataApp = new DatabaseVirtualDataApp(context);
        dataApp.delete("ID-0");
    }

    @Test
    public void testRemoveAll() {
        DatabaseVirtualDataApp dataApp = new DatabaseVirtualDataApp(context);
        dataApp.removeAll();
    }
}