package practicaltest01var02.eim.systems.cs.pub.ro.practicaltest01var06;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PracticalTest01Var06Service extends Service {
    ProcessingThread processingThread = null;

    public PracticalTest01Var06Service() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        processingThread = new ProcessingThread(this);
        processingThread.start();

        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }
}
