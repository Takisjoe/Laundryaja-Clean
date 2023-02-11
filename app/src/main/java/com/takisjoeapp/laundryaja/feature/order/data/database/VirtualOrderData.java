package com.takisjoeapp.laundryaja.feature.order.data.database;

import com.takisjoeapp.laundryaja.database.virtual.DatabaseVirtualDataApp;
import com.takisjoeapp.laundryaja.util.debug.DebugLog;
import com.takisjoeapp.laundryaja.util.debug.ModeService;

public class VirtualOrderData {
    private DatabaseVirtualDataApp dataApp;
    private String LOG = "VirtualOrderData";

    public VirtualOrderData(ModeService modeService) {
        if (modeService != ModeService.DEBUG) {
            DebugLog.DEBUG("Akses data virtual tidak di ijinkan", LOG);
            return;
        }

        this.dataApp = new DatabaseVirtualDataApp(ModeService.DEBUG.getContext());
    }

}
