package com.leadweblife.leadlauncher;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10-04-2016.
 */
public class SplashActivity extends Activity {


    Button btstart;
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        Boolean chk=isMyLauncherDefault();
//        if(chk==true)
//        {
//            Intent intent=new Intent(this,MainActivity.class);
//            startActivity(intent);
//            finish();
//        }
//        else {
//            resetPreferredLauncherAndOpenChooser(SplashActivity.this);
//        }

        btstart=(Button)findViewById(R.id.btstart);
        btstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SplashActivity.this,ViewPagerFragmentActivity.class);
                //Intent intent=new Intent(SplashActivity.this,AppListAlphabet.class);
                startActivity(intent);
                //finishscreen();

            }
        });

    }

    private void finishscreen(){
        this.finish();
    }

    public static void resetPreferredLauncherAndOpenChooser(Context context) {

        PackageManager packageManager = context.getPackageManager();
        ComponentName componentName = new ComponentName(context, AppsListActivity.class);
        packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

        Intent selector = new Intent(Intent.ACTION_MAIN);
        selector.addCategory(Intent.CATEGORY_HOME);
        selector.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(selector);

        packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT, PackageManager.DONT_KILL_APP);


    }

    private boolean isMyLauncherDefault() {
        final IntentFilter filter = new IntentFilter(Intent.ACTION_MAIN);
        filter.addCategory(Intent.CATEGORY_HOME);

        List<IntentFilter> filters = new ArrayList<IntentFilter>();
        filters.add(filter);

        final String myPackageName = getPackageName();
        List<ComponentName> activities = new ArrayList<ComponentName>();
        PackageManager packageManager = (PackageManager) getPackageManager();

        // You can use name of your package here as third argument
        packageManager.getPreferredActivities(filters, activities, null);

        if(activities.size() == 0) //no default
            return true;

        for (ComponentName activity : activities) {
            if (myPackageName.equals(activity.getPackageName())) {
                return true;
            }
        }
        return false;
    }
    }
