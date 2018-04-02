package practicaltest01var02.eim.systems.cs.pub.ro.practicaltest01var06;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Random;

public class ProcessingThread extends Thread {

    private Context context;
    Random random = new Random();
    private boolean isRunning = true;

    public ProcessingThread(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
       while(isRunning) {
           sendMessage();
           sleep();
       }
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }

    public void sleep() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION1);
        intent.putExtra("message", "Number =" + random.nextInt());
        context.sendBroadcast(intent);
    }

    public void stopThread() {
        isRunning = false;
    }
}
