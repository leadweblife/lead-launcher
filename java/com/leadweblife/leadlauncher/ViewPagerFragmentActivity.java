package com.leadweblife.leadlauncher;

/**
 * Created by ankesh on 18/4/16.
 */

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * The <code>ViewPagerFragmentActivity</code> class is the fragment activity hosting the ViewPager
 * @author mwho
 */


public class ViewPagerFragmentActivity extends FragmentActivity{
    /** maintains the pager adapter*/
    PagerAdapter mPagerAdapter;

    @Override
    public void onBackPressed() {

    }

    /* (non-Javadoc)
         * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
         */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main2);
        //initialsie the pager
        this.initialisePaging();

//        Boolean chk=isMyLauncherDefault();
//        if(chk==false) {
            resetPreferredLauncherAndOpenChooser(this);
        //}

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

    public static void resetPreferredLauncherAndOpenChooser(Context context) {

        try {
            PackageManager packageManager = context.getPackageManager();
            ComponentName componentName = new ComponentName(context, AppsListActivity.class);
            packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

            Intent selector = new Intent(Intent.ACTION_MAIN);
            selector.addCategory(Intent.CATEGORY_HOME);
            selector.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(selector);

            packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT, PackageManager.DONT_KILL_APP);
        }
        catch (Exception ex){

        }

    }


    /**
     * Initialise the fragments to be paged
     */
    private void initialisePaging() {

        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, TabMain.class.getName()));
        fragments.add(Fragment.instantiate(this, TabAppList.class.getName()));
        //fragments.add(Fragment.instantiate(this, Tab3Fragment.class.getName()));
        this.mPagerAdapter  = new PagerAdapter(super.getSupportFragmentManager(), fragments);
        //
        ViewPager pager = (ViewPager)super.findViewById(R.id.container);
        pager.setAdapter(this.mPagerAdapter);
    }
}