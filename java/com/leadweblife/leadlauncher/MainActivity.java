package com.leadweblife.leadlauncher;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.AnalogClock;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.DonutProgress;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


import org.json.JSONArray;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class MainActivity extends Activity {


    DonutProgress donut_progress;
    Intent intent;
    MyResultReceiver resultReceiver;
    TextView textView;
    ImageView ivclean;
    private static boolean mWallpaperChecked;
    private final BroadcastReceiver mWallpaperReceiver = new WallpaperIntentReceiver();


    private static ArrayList<ApplicationInfo> mApplications;
    private static LinkedList<ApplicationInfo> mFavorites;


    private LayoutAnimationController mShowLayoutAnimation;
    private LayoutAnimationController mHideLayoutAnimation;

    private boolean mBlockAnimation;

    private boolean mHomeDown;
    private boolean mBackDown;

    private View mShowApplications;
    private CheckBox mShowApplicationsCheck;

    private ApplicationStackLayout mApplicationsStack;

    private Animation mGridEntry;
    private Animation mGridExit;


    ArrayList<ListAppMain> appList;
    ListView listView;

    DBHandler dbh;

    JSONArray contacts = null;
    ArrayList<HashMap<String, String>> contactList;
    private final BroadcastReceiver mApplicationsReceiver = new ApplicationsIntentReceiver();
    Boolean showappoffline;

    Boolean showingAppList;
    Boolean showingGrid;

    //GridView gridView;
    AdView mAdView;


    @Override
    public void onBackPressed() {

        if(showingAppList==true){

            fab.setVisibility(View.VISIBLE);
            ftr.setVisibility(View.VISIBLE);
            fabcall.setVisibility(View.VISIBLE);
            fabmsg.setVisibility(View.VISIBLE);
            fabcam.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
            analogClock.setVisibility(View.VISIBLE);
            fset.setVisibility(View.GONE);
            mAdView.setVisibility(View.GONE);
            donut_progress.setVisibility(View.VISIBLE);
        }

    }

    private Camera camera;
    private boolean isFlashlightOn=false;
    Parameters params;
    FloatingActionButton ftr;
    ImageView iv;

    AnalogClock analogClock;
    FloatingActionButton fab;
    FloatingActionButton fabcall;
    FloatingActionButton fabmsg;
    FloatingActionButton fabcam;
    FloatingActionButton fset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Boolean chk=isMyLauncherDefault();
        if(chk==false) {
            resetPreferredLauncherAndOpenChooser(MainActivity.this);
        }

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("012345678ABCDEF")
                .build();

        showingAppList=false;
        showingGrid=false;
        setDefaultWallpaper();
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        textView=(TextView)findViewById(R.id.textView);
        ivclean=(ImageView)findViewById(R.id.ivclean);

        donut_progress=(DonutProgress)findViewById(R.id.donut_progress);
        donut_progress.setMax(100);
        //donut_progress.setVisibility(View.GONE);

        donut_progress.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try {
                    List<ApplicationInfo> packages;
                    PackageManager pm;
                    pm = getPackageManager();
                    //get a list of installed apps.
                    packages = pm.getInstalledApplications(0);

                    ActivityManager mActivityManager = (ActivityManager) getBaseContext().getSystemService(Context.ACTIVITY_SERVICE);

                    for (ApplicationInfo packageInfo : packages) {
                        if ((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1) continue;
                        if (packageInfo.packageName.contains("leadweblife")) continue;
                        mActivityManager.killBackgroundProcesses(packageInfo.packageName);
                        Toast.makeText(MainActivity.this,packageInfo.packageName +" freed",Toast.LENGTH_SHORT).show();
                    }
                    //Toast.makeText(MainActivity.this,"Memory Freed",Toast.LENGTH_LONG).show();

                    ActivityManager actManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                    ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
                    actManager.getMemoryInfo(memInfo);
                    long totalMemory = memInfo.totalMem;
                    long freeMemory = memInfo.availMem;
                    long usedMemory = totalMemory - freeMemory;

                    double percused = (usedMemory * 100) / totalMemory;

                    int iram = (int) percused;
                    donut_progress.setProgress(iram);



                }
                catch(Exception ex){

                }
            }
        });

        ivclean.setVisibility(View.GONE);
        ivclean.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                textView.setText("Clicked");
                List<ApplicationInfo> packages;
                PackageManager pm;
                pm = getPackageManager();
                //get a list of installed apps.
                packages = pm.getInstalledApplications(0);

                ActivityManager mActivityManager = (ActivityManager)getBaseContext().getSystemService(Context.ACTIVITY_SERVICE);

                for (ApplicationInfo packageInfo : packages) {
                    if((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM)==1)continue;
                    if(packageInfo.packageName.equals("leadweblife")) continue;
                    mActivityManager.killBackgroundProcesses(packageInfo.packageName);
                }
//                ActivityManager actvityManager = (ActivityManager)
//                        getApplicationContext().getSystemService( getApplicationContext().ACTIVITY_SERVICE );
//                List<ActivityManager.RunningAppProcessInfo> procInfos = actvityManager.getRunningAppProcesses();
//
//                for(int pnum = 0; pnum < procInfos.size(); pnum++)
//                {
//                    if((procInfos.get(pnum)).processName.contains("android")||(procInfos.get(pnum)).processName.contains("system")||(procInfos.get(pnum)).processName.contains("huawei")||(procInfos.get(pnum)).processName.contains("leadweblife"))
//                    {
//                        //Toast.makeText(getApplicationContext(), "system apps", Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    {
                       // actvityManager.killBackgroundProcesses(procInfos.get(pnum).processName);
                        //Toast.makeText(getApplicationContext(), "killed "+procInfos.get(pnum).processName, Toast.LENGTH_SHORT).show();

                        textView.setText("Memory Free");
                    //}
              //  }

            }
        });

       // textView.setText("");
//        String tram=getTotalRAM();
//        tram=tram.replaceAll(" ","");
//        tram=tram.replaceAll("MB","");
//        int iram;
//        if(tram.contains("GB")){
//            tram=tram.replaceAll("GB","");
//            Double ltram=Double.parseDouble(tram)*1024;
//            iram=ltram.intValue();
//            Toast.makeText(MainActivity.this,tram+" "+ ltram+" "+ iram,Toast.LENGTH_LONG).show();
//        }
//        else {
//            Double ltram = Double.parseDouble(tram);
//            iram=ltram.intValue();
//            Toast.makeText(MainActivity.this,tram+" "+ ltram+" "+ iram,Toast.LENGTH_LONG).show();
//        }

//
//        ActivityManager actManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//        ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
//        actManager.getMemoryInfo(memInfo);
//        long totalMemory = memInfo.totalMem;
//        long freeMemory=memInfo.availMem;
//        long usedMemory=totalMemory-freeMemory;
//
//        double percused=(usedMemory*100)/totalMemory;
//
//        int iram=(int)percused;
       // Toast.makeText(MainActivity.this,iram,Toast.LENGTH_LONG).show();


        //donut_progress.setProgress(iram);
        //donut_progress.setVisibility(View.GONE);

//        analogClock=(AnalogClock)findViewById(R.id.analogClock);
//        analogClock.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(MainActivity.this,com.leadweblife.leadlauncher.alarm.AlarmActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });



//        TextView textView=(TextView)findViewById(R.id.textView);
//        textView.setText(String.valueOf(totalMemory + "  " + freeMemory));
        iv=(ImageView)findViewById(R.id.iv);
        ftr = (FloatingActionButton) findViewById(R.id.ftorch);
        ftr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();


                if (isFlashlightOn) {
                    setFlashlightOff();
                } else {
                    setFlashlightOn();
                }
            }
        });




        analogClock=(AnalogClock)findViewById(R.id.analogClock);
        fab = (FloatingActionButton) findViewById(R.id.fapp);
        fabcall = (FloatingActionButton) findViewById(R.id.fcall);
        fabmsg = (FloatingActionButton) findViewById(R.id.fmsg);
        fabcam= (FloatingActionButton) findViewById(R.id.fab);
        listView = (ListView) findViewById(R.id.listView1);
        fset=(FloatingActionButton)findViewById(R.id.fset);
        fset.setVisibility(View.GONE);


        listView.setVisibility(View.GONE);
//        gridView = (GridView) findViewById(R.id.gridView1);
//        gridView.setVisibility(View.GONE);


//        fset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if(showingGrid==false)
//                {
//                    listView.setVisibility(View.GONE);
//                    gridView.setVisibility(View.VISIBLE);
//                    showingGrid=true;
//                }
//                else
//                {
//                    listView.setVisibility(View.VISIBLE);
//                    gridView.setVisibility(View.GONE);
//                    showingGrid=false;
//                }
//            }
//        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                //Intent intent = new Intent(MainActivity.this, MainActivity.class);
                //startActivity(intent);
                //finishscreen();
                ftr.setVisibility(View.GONE);
                fab.setVisibility(View.GONE);
                fabcall.setVisibility(View.GONE);
                fabmsg.setVisibility(View.GONE);
                fabcam.setVisibility(View.GONE);
                //fset.setVisibility(View.VISIBLE);
                mAdView.setVisibility(View.VISIBLE);
               // donut_progress.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
//              if(showingGrid==false)
//                  listView.setVisibility(View.VISIBLE);
//              else
//                  gridView.setVisibility(View.VISIBLE);

                analogClock.setVisibility(View.GONE);
                showingAppList=true;
            }
        });



        fabcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                Intent intent=new Intent(Intent.ACTION_DIAL);
                startActivity(intent);
                //finishscreen();


            }
        });



        fabmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setType("vnd.android-dir/mms-sms");
                startActivity(intent);

                //finishscreen();


            }
        });


        fabcam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);
                startActivity(intent);

                //finishscreen();


            }
        });



        registerIntentReceivers();
        setDefaultWallpaper();
//        loadApplications(true);
//
//        bindApplications();
//        bindFavorites(true);
//        bindRecents();
//        bindButtons();

        mGridEntry = AnimationUtils.loadAnimation(this, R.anim.grid_entry);

        mGridExit = AnimationUtils.loadAnimation(this, R.anim.grid_exit);



        //loadimage("http://www.mycodeforweb.com/wallapi/wall.jpg");



        //listView=(ListView)findViewById(R.id.listView1);
        dbh=new DBHandler(MainActivity.this);
        showappoffline=false;
        final PackageManager manager = getPackageManager();
        appList = getApps();

//        if(dbh.getAppCount()>0)
//            showappoffline=true;

        registerIntentReceivers();
        showApps();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appList.get(arg2).packname
                        .toString());
                MainActivity.this.startActivity(i);
            }
        });

//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//                                    long arg3) {
//                // TODO Auto-generated method stub
//                Intent i = manager.getLaunchIntentForPackage(appList.get(arg2).packname
//                        .toString());
//                MainActivity.this.startActivity(i);
//            }
//        });


        mAdView.loadAd(adRequest);
        mAdView.setVisibility(View.GONE);
        fset.setVisibility(View.GONE);
    }


    private void showApps() {
        // TODO Auto-generated method stub

        ArrayAdapter<ListAppMain> adapter = new ArrayAdapter<ListAppMain>(this, R.layout.list_list_item,
                appList) {


            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                //convertView = getViewDetails(position, convertView);
                if(convertView == null) {
//        if(showingGrid==false) {
                    convertView = getLayoutInflater().inflate(R.layout.list_list_item,
                            null);
                    // }

                    ImageView appIcon = (ImageView) convertView
                            .findViewById(R.id.imageView1);
                    appIcon.setImageDrawable(appList.get(position).icon);

                    TextView appName = (TextView) convertView
                            .findViewById(R.id.textView1);
                    appName.setText(appList.get(position).appName);

                    TextView packName = (TextView) convertView
                            .findViewById(R.id.item_app_name);
                    packName.setText(appList.get(position).packname);
                }
                return convertView;
            }
        };

        listView.setAdapter(adapter);
        //gridView.setAdapter(adapter);



    }



    private ArrayList<ListAppMain> getApps() {
        // TODO Auto-generated method stub

        dbh=new DBHandler(MainActivity.this);


        PackageManager manager = getPackageManager();
        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableActivities = manager.queryIntentActivities(
                i, 0);

        ArrayList<ListAppMain> temp = new ArrayList<ListAppMain>();


        if(showappoffline==false) {
            dbh.deleteApp();


            for (ResolveInfo ri : availableActivities) {
//        App app = new App();
//        app.packname = ri.activityInfo.packageName;
//        app.appName = ri.loadLabel(manager).toString();
//        //.substring(app.packname.lastIndexOf(".") + 1);
//        app.icon = ri.activityInfo.loadIcon(manager);
//        temp.add(app);

                dbh.addApp(new DBApp(ri.loadLabel(manager).toString(), ri.activityInfo.packageName));
            }

            if (dbh.getAppCount() > 0) {
                List<DBApp> contacts = dbh.getallApp();

                for (DBApp cn : contacts) {

                    HashMap<String, String> contact = new HashMap<String, String>();

//                    contact.put(TAG_slNo, cn.getslno());
//                    contact.put(TAG_my, cn.getmonthyear());
//                    contact.put(TAG_Amount, cn.getamount());

                    ListAppMain app = new ListAppMain();
                    app.packname = cn.get_pname();
                    app.appName = cn.get_name();

                    for (ResolveInfo ri : availableActivities) {

                        if (ri.activityInfo.packageName.toString().equalsIgnoreCase(cn.get_pname()))
                            app.icon = ri.activityInfo.loadIcon(manager);


                    }
                    temp.add(app);
                }
            }
        }
        else
        {
            if (dbh.getAppCount() > 0) {
                List<DBApp> contacts = dbh.getallApp();

                for (DBApp cn : contacts) {

                    HashMap<String, String> contact = new HashMap<String, String>();

//                    contact.put(TAG_slNo, cn.getslno());
//                    contact.put(TAG_my, cn.getmonthyear());
//                    contact.put(TAG_Amount, cn.getamount());

                    ListAppMain app = new ListAppMain();
                    app.packname = cn.get_pname();
                    app.appName = cn.get_name();

                    for (ResolveInfo ri : availableActivities) {

                        if (ri.activityInfo.packageName.toString().equalsIgnoreCase(cn.get_pname()))
                            app.icon = ri.activityInfo.loadIcon(manager);


                    }
                    temp.add(app);
                }
            }
        }


        return temp;
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.navSwitch) {

            Intent intent=new Intent(MainActivity.this,AppsListActivity.class);
            startActivity(intent);
            finishscreen();

        }

        return super.onOptionsItemSelected(item);
    }






//    private void registerIntentReceivers() {
//        IntentFilter filter = new IntentFilter(Intent.ACTION_WALLPAPER_CHANGED);
////        registerReceiver(mWallpaperReceiver, filter);
//
//        filter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
//        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
//        filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
//        filter.addDataScheme("package");
//        registerReceiver(mApplicationsReceiver, filter);
//    }

    private class ApplicationsIntentReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // loadApplications(false);
            // bindApplications();
            // bindRecents();
            // bindFavorites(false);
            showappoffline=false;
            appList = getApps();
            showApps();
        }
    }
















































    /**
     * Registers various intent receivers. The current implementation registers
     * only a wallpaper intent receiver to let other applications change the
     * wallpaper.
     */
    private void registerIntentReceivers() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_WALLPAPER_CHANGED);
        registerReceiver(mWallpaperReceiver, filter);


        filter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
        filter.addAction(Intent.ACTION_PACKAGE_INSTALL);
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
        filter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);

        filter.addDataScheme("package");
        registerReceiver(mApplicationsReceiver, filter);
    }
    private void setFlashlightOn() {

        try {
            boolean isCameraFlash = getApplicationContext().getPackageManager()
                    .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
            if (!isCameraFlash) {
                showNoCameraAlert();
            } else {
                camera = Camera.open();
                params = camera.getParameters();
                params = camera.getParameters();
                params.setFlashMode(Parameters.FLASH_MODE_TORCH);
                camera.setParameters(params);
                camera.startPreview();
                isFlashlightOn = true;
                ftr.setImageResource(R.drawable.picon);
            }
        }
        catch (Exception ex){

        }

    }


    /**
     * When no wallpaper was manually set, a default wallpaper is used instead.
     */
    private void setDefaultWallpaper() {
        if (!mWallpaperChecked) {

            Drawable wallpaper = peekWallpaper();
            if (wallpaper == null) {
                try {
                    clearWallpaper();
                } catch (IOException e) {


                    //Log.e(LOG_TAG, "Failed to clear wallpaper " + e);
                }
            } else {
                getWindow().setBackgroundDrawable(new ClippedDrawable(wallpaper));
            }
            mWallpaperChecked = true;
        }
    }


    private void setFlashlightOff() {
try {
    if (camera == null || params == null) {
        return;
    }

    params = camera.getParameters();
    params.setFlashMode(Parameters.FLASH_MODE_OFF);
    camera.setParameters(params);
    camera.stopPreview();
    isFlashlightOn = false;
    ftr.setImageResource(R.drawable.picoff);

    if (camera != null) {
        camera.release();
        camera = null;
    }
}
catch (Exception ex){
    Toast.makeText(MainActivity.this,ex.toString(),Toast.LENGTH_LONG).show();
        }
    }
    private void showNoCameraAlert(){
//        new AlertDialog.Builder(this)
//                .setTitle("Error: No Camera Flash!")
//                .setMessage("Flashlight not available in this Android device!")
//                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        //finish(); // close the Android app
//                    }
//                })
//                .setIcon(android.R.drawable.ic_dialog_alert)
//                .show();
        Toast.makeText(MainActivity.this,"No Camera Flash",Toast.LENGTH_LONG).show();
        return;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (camera != null) {
            camera.release();
            camera = null;
        }
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
    private  void finishscreen(){
        this.finish();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


private void loadimage(String imageurl){
//    Picasso.with(this).invalidate(imageurl);
//    Picasso.with(this)
//            .load(imageurl)
//    .placeholder(R.drawable.blank)   // optional
//    .error(R.drawable.blank)      // optional
//    .resize(200, 300)                        // optional
//    //.rotate(90)                             // optional
//    //.networkPolicy(NetworkPolicy.NO_CACHE)
//    .into(iv);
}



    public String getTotalRAM() {

        RandomAccessFile reader = null;
        String load = null;
        DecimalFormat twoDecimalForm = new DecimalFormat("#.##");
        double totRam = 0;
        String lastValue = "";
        try {
            reader = new RandomAccessFile("/proc/meminfo", "r");
            load = reader.readLine();

            // Get the Number value from the string
            Pattern p = Pattern.compile("(\\d+)");
            Matcher m = p.matcher(load);
            String value = "";
            while (m.find()) {
                value = m.group(1);
                // System.out.println("Ram : " + value);
            }
            reader.close();

            totRam = Double.parseDouble(value);
            // totRam = totRam / 1024;

            double mb = totRam / 1024.0;
            double gb = totRam / 1048576.0;
            double tb = totRam / 1073741824.0;

            if (tb > 1) {
                lastValue = twoDecimalForm.format(tb).concat(" TB");
            } else if (gb > 1) {
                lastValue = twoDecimalForm.format(gb).concat(" GB");
            } else if (mb > 1) {
                lastValue = twoDecimalForm.format(mb).concat(" MB");
            } else {
                lastValue = twoDecimalForm.format(totRam).concat(" KB");
            }



        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            // Streams.close(reader);
        }

        return lastValue;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intent);
    }


    class UpdateUI implements Runnable
    {
        String updateString;

        public UpdateUI(String updateString) {
            this.updateString = updateString;
        }
        public void run() {
            //txtview.setText(updateString);
            //arcProgress.setProgress(Integer.parseInt(updateString));
            donut_progress.setProgress(Integer.parseInt(updateString));
            Toast.makeText(MainActivity.this,updateString,Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("ParcelCreator")
    public class MyResultReceiver extends ResultReceiver {
        public MyResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            runOnUiThread(new UpdateUI(String.valueOf(resultCode)));

        }
    }


    /**
     * When a drawable is attached to a View, the View gives the Drawable its dimensions
     * by calling Drawable.setBounds(). In this application, the View that draws the
     * wallpaper has the same size as the screen. However, the wallpaper might be larger
     * that the screen which means it will be automatically stretched. Because stretching
     * a bitmap while drawing it is very expensive, we use a ClippedDrawable instead.
     * This drawable simply draws another wallpaper but makes sure it is not stretched
     * by always giving it its intrinsic dimensions. If the wallpaper is larger than the
     * screen, it will simply get clipped but it won't impact performance.
     */
    private class ClippedDrawable extends Drawable {
        private final Drawable mWallpaper;

        public ClippedDrawable(Drawable wallpaper) {
            mWallpaper = wallpaper;
        }

        @Override
        public void setBounds(int left, int top, int right, int bottom) {
            super.setBounds(left, top, right, bottom);
            // Ensure the wallpaper is as large as it really is, to avoid stretching it
            // at drawing time
            mWallpaper.setBounds(left, top, left + mWallpaper.getIntrinsicWidth(),
                    top + mWallpaper.getIntrinsicHeight());
        }

        public void draw(Canvas canvas) {
            mWallpaper.draw(canvas);
        }

        public void setAlpha(int alpha) {
            mWallpaper.setAlpha(alpha);
        }

        public void setColorFilter(ColorFilter cf) {
            mWallpaper.setColorFilter(cf);
        }

        public int getOpacity() {
            return mWallpaper.getOpacity();
        }
    }

    /**
     * Receives intents from other applications to change the wallpaper.
     */
    private class WallpaperIntentReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            getWindow().setBackgroundDrawable(new ClippedDrawable(getWallpaper()));
        }
    }



class ListAppMain {
    String appName, packname;
    Drawable icon;
}

}
