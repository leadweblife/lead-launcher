package com.leadweblife.leadlauncher;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ankesh on 12/4/16.
 */


public class AppsListActivity extends Activity {

        ArrayList<App> appList;
        GridView gridView;

    DBHandler dbh;

    JSONArray contacts = null;
    ArrayList<HashMap<String, String>> contactList;

    String[] social={"facebook","whatsapp","twitter","pinterest","chat","social","tumblr","evernote"};
    String[] game={"game","cricket","hockey","race","racing","sudoku"};
    String[] musicp={"music","song","sing","player"};
    String[] videop={"video","vlc","youtube","vimeo"};

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_list);

        final PackageManager manager = getPackageManager();
        appList = getApps();
        showApps();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appList.get(arg2).packname
                        .toString());
                AppsListActivity.this.startActivity(i);
            }
        });

        dbh=new DBHandler(AppsListActivity.this);

        contactList = new ArrayList<HashMap<String, String>>();
//
//        AdView mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder()
//                .addTestDevice("012345678ABCDEF")
//                .build();
//        mAdView.loadAd(adRequest);

        }

private void showApps() {
        // TODO Auto-generated method stub

        gridView = (GridView) findViewById(R.id.gridView1);

        ArrayAdapter<App> adapter = new ArrayAdapter<App>(this, R.layout.list_item,
        appList) {


@Override
public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
        convertView = getLayoutInflater().inflate(R.layout.list_item,
        null);
        }

        ImageView appIcon = (ImageView) convertView
        .findViewById(R.id.imageView1);
        appIcon.setImageDrawable(appList.get(position).icon);

        TextView appName = (TextView) convertView
        .findViewById(R.id.textView1);
        appName.setText(appList.get(position).appName);

        return convertView;
        }
        };

        gridView.setAdapter(adapter);
        }


    private ArrayList<App> getApps() {
        // TODO Auto-generated method stub

        dbh=new DBHandler(AppsListActivity.this);
      //  dbh.deleteApp();

        PackageManager manager = getPackageManager();
        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableActivities = manager.queryIntentActivities(
        i, 0);

        ArrayList<App> temp = new ArrayList<App>();
//        for (ResolveInfo ri : availableActivities) {
////        App app = new App();
////        app.packname = ri.activityInfo.packageName;
////        app.appName = ri.loadLabel(manager).toString();
////        //.substring(app.packname.lastIndexOf(".") + 1);
////        app.icon = ri.activityInfo.loadIcon(manager);
////        temp.add(app);
//
//            dbh.addApp(new DBApp(ri.loadLabel(manager).toString(), ri.activityInfo.packageName));
//        }
            if(dbh.getAppCount()>0) {
                List<DBApp> contacts = dbh.getallApp();

                for (DBApp cn : contacts) {

                    HashMap<String, String> contact = new HashMap<String, String>();

//                    contact.put(TAG_slNo, cn.getslno());
//                    contact.put(TAG_my, cn.getmonthyear());
//                    contact.put(TAG_Amount, cn.getamount());

                    App app=new App();
                    app.packname=cn.get_pname();
                    app.appName=cn.get_name();

                    String appPname=cn.get_pname();

                    SharedPreferences pref;
                    pref= getSharedPreferences("leadlaunch", MODE_PRIVATE);
                    String showwhat=pref.getString("showapp","");

                                if(showwhat.equalsIgnoreCase("social")) {

                                    for (int k = 0; k < social.length; k++) {
                                        if (appPname.contains(social[k])) {

                                            for (ResolveInfo ri : availableActivities) {

                                                if (ri.activityInfo.packageName.toString().equalsIgnoreCase(cn.get_pname()))
                                                    app.icon = ri.activityInfo.loadIcon(manager);

                                            }
                                            temp.add(app);

                                        }
                                    }
                                }
                            else if(showwhat.equalsIgnoreCase("game"))
                              {

                                  for (int k = 0; k < game.length; k++) {
                                      if (appPname.contains(game[k])) {

                                          for (ResolveInfo ri : availableActivities) {

                                              if (ri.activityInfo.packageName.toString().equalsIgnoreCase(cn.get_pname()))
                                                  app.icon = ri.activityInfo.loadIcon(manager);

                                          }
                                          temp.add(app);

                                      }
                                  }
                              }
                              else if(showwhat.equalsIgnoreCase("music"))
                                {

                                    for (int k = 0; k < musicp.length; k++) {
                                        if (appPname.contains(musicp[k])) {

                                            for (ResolveInfo ri : availableActivities) {

                                                if (ri.activityInfo.packageName.toString().equalsIgnoreCase(cn.get_pname()))
                                                    app.icon = ri.activityInfo.loadIcon(manager);

                                            }
                                            temp.add(app);

                                        }
                                    }
                                }
                                else if(showwhat.equalsIgnoreCase("video"))
                                {

                                    for (int k = 0; k < videop.length; k++) {
                                        if (appPname.contains(videop[k])) {

                                            for (ResolveInfo ri : availableActivities) {

                                                if (ri.activityInfo.packageName.toString().equalsIgnoreCase(cn.get_pname()))
                                                    app.icon = ri.activityInfo.loadIcon(manager);

                                            }
                                            temp.add(app);

                                        }
                                    }
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

            Intent intent=new Intent(AppsListActivity.this,ListAppListActivity.class);
            startActivity(intent);
            finishscreen();


        }

        return super.onOptionsItemSelected(item);
    }


    private  void  finishscreen()
    {
        this.finish();

    }


}

class App {
    String appName, packname;
    Drawable icon;
}

