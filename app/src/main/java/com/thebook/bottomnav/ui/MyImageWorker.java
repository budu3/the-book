package com.thebook.bottomnav.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.renderscript.ScriptGroup;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyImageWorker extends Worker {
    public MyImageWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        getRemoteImages();
        return Result.success();
    }

    private void getRemoteImages(){
        URL url = null;
        BufferedReader br = null;
        String result = null;

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[10240];
        int len = 0;

        try {
            File file = new File(getApplicationContext().getCacheDir(), "a-ghost-story.jpg");
            boolean success = file.createNewFile();
            Log.d("MyImageWorker",file.getAbsolutePath());
            Log.d("MyImageWorker->", ""+success);
            Log.d("MyImageWorker->", "exists "+file.exists());

            url = new URL("https://raw.githubusercontent.com/budu3/the-book/master/code/assets/images/a-ghost-story.jpg");

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream in = conn.getInputStream();
            Log.d("MyImageWorker->",""+conn.getResponseCode());
            BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
            Bitmap bitmap = BitmapFactory.decodeStream(bufferedInputStream);

            if (bitmap != null)
                Log.d("MyImageWorker->", "ByteCount "+bitmap.getByteCount());
            else
                Log.d("MyImageWorker->", "bitmap is null");

            in.close();

            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, new FileOutputStream(file));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("MyImageWorker", "IOException");
        }

    }
}
