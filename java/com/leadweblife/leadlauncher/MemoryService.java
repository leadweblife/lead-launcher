package com.leadweblife.leadlauncher;

/**
 * Created by Admin on 14-04-2016.
 */
import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ankesh on 14/4/16.
 */
public class MemoryService extends Service{

    Timer timer = new Timer();
    MyTimerTask timerTask;
    ResultReceiver resultReceiver;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        resultReceiver = intent.getParcelableExtra("receiver");

        timerTask = new MyTimerTask();
        timer.scheduleAtFixedRate(timerTask, 1000, 1000);
        return START_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        Bundle bundle = new Bundle();
        bundle.putString("end", "Timer Stopped....");
        resultReceiver.send(30, bundle);
    }

    class MyTimerTask extends TimerTask
    {
        public MyTimerTask() {
            Bundle bundle = new Bundle();
            bundle.putString("start", "Timer Started....");
            resultReceiver.send(50, bundle);
        }
        @Override
        public void run() {
            //SimpleDateFormat dateFormat = new SimpleDateFormat("s");
            //resultReceiver.send(Integer.parseInt(dateFormat.format(System.currentTimeMillis())), null)

    try {
        ActivityManager actManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
        actManager.getMemoryInfo(memInfo);
        long totalMemory = memInfo.totalMem;
        long freeMemory = memInfo.availMem;
        long usedMemory = totalMemory - freeMemory;

        double percused = (usedMemory * 100) / totalMemory;

        int iram = (int) percused;

//Percentage can be calculated for API 16+
        //long percentAvail = mi.availMem / mi.totalMem;
        //arcProgress.setProgress((int)availableMegs);
        resultReceiver.send(iram, null);
    }
    catch (Exception ex){

    }

        }
    }
}


