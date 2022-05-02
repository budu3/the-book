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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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

            URL url = null;
            BufferedReader br = null;
            try {
                url = new URL("https://example.com");
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("GET");

                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String output;
                StringBuilder builder = new StringBuilder();
                while ((output = br.readLine()) != null){
                    builder.append(output);
                }
                Log.d("Service->", builder.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            /*
            try{
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
             */
            stopSelf(msg.arg1);
            Log.d("Service->","Message has been handled");

        }

    }

    @Override
    public void onCreate() {
        HandlerThread thread = new HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        serviceLooper = thread.getLooper();
        serviceHandler = new ServiceHandler(serviceLooper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service has started", LENGTH_LONG).show();
        Log.d("Service->","Service has started");

        Message msg = serviceHandler.obtainMessage();
        msg.arg1 = startId;
        serviceHandler.sendMessage(msg);

        return START_STICKY;
    }

    public void onDestroy(){
        Toast.makeText(this, "Service has ended", LENGTH_LONG);
        Log.d("Service->","Service has ended");
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
}
