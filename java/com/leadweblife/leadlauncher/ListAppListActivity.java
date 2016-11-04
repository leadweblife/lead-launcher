package com.leadweblife.leadlauncher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ankesh on 16/4/16.
 */
public class ListAppListActivity extends AppCompatActivity {

    ArrayList<ListApp> appList;
    ListView listView;

    DBHandler dbh;

    JSONArray contacts = null;
    ArrayList<HashMap<String, String>> contactList;
    private final BroadcastReceiver mApplicationsReceiver = new ApplicationsIntentReceiver();
Boolean showappoffline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity_apps_list);

        dbh=new DBHandler(ListAppListActivity.this);
        showappoffline=false;
        final PackageManager manager = getPackageManager();
        appList = getApps();

        if(dbh.getAppCount()>0)
         showappoffline=true;

        registerIntentReceivers();
        showApps();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appList.get(arg2).packname
                        .toString());
                ListAppListActivity.this.startActivity(i);
            }
        });



        contactList = new ArrayList<HashMap<String, String>>();

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("012345678ABCDEF")
                .build();
        mAdView.loadAd(adRequest);



    }



    private void showApps() {
        // TODO Auto-generated method stub

        listView = (ListView) findViewById(R.id.listView1);

        ArrayAdapter<ListApp> adapter = new ArrayAdapter<ListApp>(this, R.layout.list_item,
                appList) {


            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_list_item,
                            null);
                }

                ImageView appIcon = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                appIcon.setImageDrawable(appList.get(position).icon);

                TextView appName = (TextView) convertView
                        .findViewById(R.id.textView1);
                appName.setText(appList.get(position).appName);

                TextView packName=(TextView) convertView
                        .findViewById(R.id.item_app_name);
                packName.setText(appList.get(position).packname);

                return convertView;
            }
        };

        listView.setAdapter(adapter);
    }


    private ArrayList<ListApp> getApps() {
        // TODO Auto-generated method stub

        dbh=new DBHandler(ListAppListActivity.this);


        PackageManager manager = getPackageManager();
        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableActivities = manager.queryIntentActivities(
                i, 0);

        ArrayList<ListApp> temp = new ArrayList<ListApp>();


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

                    ListApp app = new ListApp();
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

                    ListApp app = new ListApp();
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

            Intent intent=new Intent(ListAppListActivity.this,AppsListActivity.class);
            startActivity(intent);
            finishscreen();

        }

        return super.onOptionsItemSelected(item);
    }


    private  void  finishscreen()
    {
        this.finish();

    }



    private void registerIntentReceivers() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_WALLPAPER_CHANGED);
//        registerReceiver(mWallpaperReceiver, filter);

        filter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
        filter.addDataScheme("package");
        registerReceiver(mApplicationsReceiver, filter);
    }

    private class ApplicationsIntentReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // loadApplications(false);
            // bindApplications();
            // bindRecents();
            // bindFavorites(false);
            showappoffline=false;
            showApps();
        }
    }
}


/**
 * Receives notifications when applications are added/removed.
 */




class ListApp {
    String appName, packname;
    Drawable icon;
}

/*
public class AppsListActivity extends Activity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_list);

        loadApps();
        loadListView();
        addClickListener();
    }


    private PackageManager manager;
    private List<AppDetail> apps;
    private void loadApps(){
        manager = getPackageManager();
        apps = new ArrayList<AppDetail>();

        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableActivities = manager.queryIntentActivities(i, 0);

        for(ResolveInfo ri:availableActivities){
            AppDetail app = new AppDetail();
            app.label = ri.loadLabel(manager);
            app.name = ri.activityInfo.packageName;
            app.icon = ri.activityInfo.loadIcon(manager);
            apps.add(app);
        }



    }



    private ListView list;
    private void loadListView(){
        list = (ListView)findViewById(R.id.apps_list);

        Collections.sort(apps,Collections.reverseOrder());

        ArrayAdapter<AppDetail> adapter = new ArrayAdapter<AppDetail>(this,
                R.layout.list_item,
                apps) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null){
                    convertView = getLayoutInflater().inflate(R.layout.list_item, null);
                }

                ImageView appIcon = (ImageView)convertView.findViewById(R.id.item_app_icon);
                appIcon.setImageDrawable(apps.get(position).icon);

                TextView appLabel = (TextView)convertView.findViewById(R.id.item_app_label);
                appLabel.setText(apps.get(position).label);

                TextView appName = (TextView)convertView.findViewById(R.id.item_app_name);
                appName.setText(apps.get(position).name);

                return convertView;
            }
        };

        list.setAdapter(adapter);
    }



    private void addClickListener(){
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos,
                                    long id) {
                Intent i = manager.getLaunchIntentForPackage(apps.get(pos).name.toString());
                AppsListActivity.this.startActivity(i);
            }
        });
    }
}
*/


