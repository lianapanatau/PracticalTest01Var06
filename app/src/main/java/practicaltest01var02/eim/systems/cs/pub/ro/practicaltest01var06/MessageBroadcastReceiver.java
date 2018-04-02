package practicaltest01var02.eim.systems.cs.pub.ro.practicaltest01var06;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MessageBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(getClass().getSimpleName(), "onReceiveBroadcast");

        String action = intent.getAction();
        String data = null;
        if (Constants.ACTION1.equals(action)) {
            data = intent.getStringExtra("message");
            Log.d(getClass().getSimpleName(), "onReceiveBroadcast=> data " + data);
        }

        Intent startedServiceActivityIntent = new Intent(context, PracticalTest01Var02PlayActivity.class);
        startedServiceActivityIntent.putExtra("message", data);
        startedServiceActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(startedServiceActivityIntent);
    }
}
