package com.thebook.bottomnav;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process; //using this instead of java.lang.Process
import android.util.Log;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class SimpleService extends Service {
    private Looper serviceLooper;
    private ServiceHandler serviceHandler;


    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper){
            super(looper);
        }

        @Override
        public void handleMessage(Message msg){
            try{
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            stopSelf(msg.arg1);
            Log.d("Service->","Service has started");
        }

    }
    public SimpleService() {
    }

    @Override
    public void onCreate() {
        HandlerThread thread = new HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        serviceLooper = thread.getLooper();
        serviceHandler = new ServiceHandler(serviceLooper);
        //super.onCreate();
    }

    public void onStart(){
        //Toast.makeText(this, "The Service has been started", Toast.LENGTH_LONG).show();
        //Log.d("Service->","Service has started");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "onStartCommand", LENGTH_LONG).show();
        Log.d("Service->","onStartCommand has started");

        Message msg = serviceHandler.obtainMessage();
        msg.arg1 = startId;
        serviceHandler.sendMessage(msg);

        return START_STICKY;
    }

    public void onDestroy(){
        Toast.makeText(this, "service done", LENGTH_LONG);
        Log.d("Service->","Service has ended");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }
}
