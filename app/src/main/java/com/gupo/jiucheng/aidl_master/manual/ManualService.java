package com.gupo.jiucheng.aidl_master.manual;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ManualService extends Service {
    public ManualService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
