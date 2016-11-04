package com.leadweblife.leadlauncher;

/**
 * Created by ankesh on 18/4/16.
 */

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.DonutProgress;

import java.io.IOException;

/**
 * @author mwho
 *
 */
public class TabMain extends Fragment {
    /** (non-Javadoc)
     * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
     */
    private static boolean mWallpaperChecked;
    private final BroadcastReceiver mWallpaperReceiver = new WallpaperIntentReceiver();
    private final BroadcastReceiver mMusicReceiver = new MusicIntentReceiver();
    private final BroadcastReceiver mNewMsgReceiver = new NewMsgIntentReceiver();

    private Animation mGridEntry;
    private Animation mGridExit;
    LayoutInflater infl;
    View mView;
    DonutProgress donut_progress;
    private Camera camera;
    private boolean isFlashlightOn=false;
    Camera.Parameters params;


//    FloatingActionButton ftr;
//    FloatingActionButton fabcall;
//    FloatingActionButton fabmsg;
//    FloatingActionButton fabcam;

    private final int PICK_CONTACT=0;

    ImageButton ibcall,ibcontact,ibmsg,ibplaystore,ibemail,ibcamera,ibgallery,ibfile,ibmusic,ibvideo,ibbrowser,ibtorch,ibsetting;
    ImageButton ibcal,ibappset,appsocial,appgame;

    boolean  isResumed = false;
    private static final int SELECT_PICTURE = 123;

    SharedPreferences pref;
    private android.widget.LinearLayout.LayoutParams layoutParams;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if (container == null) {
            // We have different layouts, and in one of them this
            // fragment's containing frame doesn't exist.  The fragment
            // may still be created from its saved state, but there is
            // no reason to try to create its view hierarchy because it
            // won't be displayed.  Note this is not needed -- we could
            // just run the code below, where we would create and return
            // the view hierarchy; it would just never be used.
            //return null;
        }
        //return (LinearLayout)inflater.inflate(R.layout.tab_frag_main_layout, container, false);

        infl = inflater;
        mView= inflater.inflate(R.layout.main_tab_new, container, false);
        return mView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mGridEntry = AnimationUtils.loadAnimation(getActivity(), R.anim.grid_entry);
        mGridExit = AnimationUtils.loadAnimation(getActivity(), R.anim.grid_exit);

        setDefaultWallpaper();
        pref= getActivity().getSharedPreferences("leadlaunch", getActivity().MODE_PRIVATE);

        //donut_progress=(DonutProgress)mView.findViewById(R.id.donut_progress);
//        donut_progress.setMax(100);
//
//        donut_progress.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                try {
//
//
//                    //textView.setText("Memory Free");
//
//                    List<android.content.pm.ApplicationInfo> packages;
//                    PackageManager pm;
//                    pm = getActivity().getPackageManager();
//                    //get a list of installed apps.
//                    packages = pm.getInstalledApplications(0);
//
//                    ActivityManager mActivityManager = (ActivityManager) getActivity().getBaseContext().getSystemService(Context.ACTIVITY_SERVICE);
//
//                    for (android.content.pm.ApplicationInfo packageInfo : packages) {
//                        if ((packageInfo.flags & android.content.pm.ApplicationInfo.FLAG_SYSTEM) == 1)
//                            continue;
//                        if (packageInfo.packageName.contains("leadweblife")) continue;
//                        mActivityManager.killBackgroundProcesses(packageInfo.packageName);
//                        Toast.makeText(getActivity(), packageInfo.packageName + " freed", Toast.LENGTH_SHORT).show();
//                    }
//                    //Toast.makeText(MainActivity.this,"Memory Freed",Toast.LENGTH_LONG).show();
//
//                    ActivityManager actManager = (ActivityManager) getActivity().getSystemService(getActivity().ACTIVITY_SERVICE);
//                    ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
//                    actManager.getMemoryInfo(memInfo);
//                    long totalMemory = memInfo.totalMem;
//                    long freeMemory = memInfo.availMem;
//                    long usedMemory = totalMemory - freeMemory;
//
//                    double percused = (usedMemory * 100) / totalMemory;
//
//                    int iram = (int) percused;
//                    donut_progress.setProgress(iram);
//
//
//                } catch (Exception ex) {
//
//                }
//            }
//        });


        ibcal=(ImageButton)getActivity().findViewById(R.id.ibdate);
        ibcal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                PackageManager manager = getActivity().getPackageManager();
                Intent i = manager.getLaunchIntentForPackage("com.android.calendar");
                startActivity(i);
            }
        });


//        ibappset=(ImageButton)getActivity().findViewById(R.id.ibappsetting);
//        ibappset.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(Intent.ACTION_DIAL);
//                startActivity(intent);
//            }
//        });

        appsocial=(ImageButton)getActivity().findViewById(R.id.ibsocial);
        appsocial.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor;
                editor=pref.edit();

                Intent intent=new Intent(getActivity().getBaseContext(), AppsListActivity.class);
                editor.putString("showapp","social");
                editor.commit();
                startActivity(intent);
            }
        });

        appgame=(ImageButton)getActivity().findViewById(R.id.ibgame);
        appgame.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor;
                editor=pref.edit();

                Intent intent=new Intent(getActivity().getBaseContext(), AppsListActivity.class);
                editor.putString("showapp","game");
                editor.commit();
                startActivity(intent);
            }
        });


        ibcall=(ImageButton)getActivity().findViewById(R.id.ibcall);
        ibcall.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                startActivity(intent);
            }
        });
        //ondragOption();

//        ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(getActivity().getBaseContext(), R.anim.flipping);
//        anim.setTarget(ibcall);
//        anim.setDuration(3000);
//        anim.start();

        // int colorFrom = getResources().getColor(R.color.colorAccent);
        int colorFrom = getResources().getColor(R.color.colorAccent);
        int colorTo = getResources().getColor(R.color.colorPrimary);
//        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
//        colorAnimation.setDuration(100); // milliseconds
//        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//
//            @Override
//            public void onAnimationUpdate(ValueAnimator animator) {
//                ibcall.setBackgroundColor((int) animator.getAnimatedValue());
//            }
//
//        });
//        colorAnimation.start();
//
//        ObjectAnimator.ofObject(ibcall, "backgroundColor", new ArgbEvaluator(), colorFrom, colorTo)
//                .setDuration(1000)
//                .start();


//        ValueAnimator anim = ValueAnimator.ofInt(Color.parseColor("#000000"), Color.parseColor("#FFFFFF"));
//        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                ibcall.setBackgroundColor((Integer) animation.getAnimatedValue());
//            }
//        });
//
//        anim.start();
//        anim.setDuration(10000);

        ibcontact=(ImageButton)getActivity().findViewById(R.id.ibcontact);

//        AnimatorSet set;
//        set = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity().getBaseContext(),R.anim.rotate);
//        set.setTarget(ibcontact);
//        set.start();
        ibcontact.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                try
                {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
                    startActivityForResult(intent, PICK_CONTACT);
                }
                catch (Exception e) {
                    Intent intent = getActivity().getIntent();
                    //getActivity().finish();
                    startActivity(intent);
                }
            }
        });

        ibmsg=(ImageButton)getActivity().findViewById(R.id.ibmsg);
        ibmsg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setType("vnd.android-dir/mms-sms");
                startActivity(intent);
            }
        });

        ibplaystore=(ImageButton)getActivity().findViewById(R.id.ibplaystore);
        ibplaystore.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                try {

//                    Intent launchIntent = getActivity().getPackageManager().getLaunchIntentForPackage("com.android.vending");
//                    //ComponentName comp = new ComponentName("com.android.vending", "com.google.android.finsky.activities.LaunchUrlHandlerActivity"); // package name and activity
//                    //launchIntent.setComponent(comp);
//                    launchIntent.setData(Uri.parse("market://search?q=leadweblife"));
//
//                    startActivity(launchIntent);

                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=leadweblife")));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/search?q=leadweblife")));

                    }                }
                catch (Exception ex){

                }

            }
        });

        ibemail=(ImageButton)getActivity().findViewById(R.id.ibemail);
        ibemail.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                try{
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_APP_EMAIL);
                    getActivity().startActivity(intent);
                }
                catch (Exception ex) {
                }


            }
        });


        ibcamera=(ImageButton)getActivity().findViewById(R.id.ibcamera);

//        AnimatorSet setcam;
//        setcam = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity().getBaseContext(), R.anim.rotate_side);
//        setcam.setTarget(ibcamera);
//        setcam.start();
        ibcamera.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    startActivity(intent);
                }
                catch (Exception ex) {
                }
            }
        });

        ibgallery=(ImageButton)getActivity().findViewById(R.id.ibgallery);
        ibgallery.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                try{
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);

                    startActivityForResult(Intent.createChooser(intent,
                            "Select Picture"), SELECT_PICTURE);

                }
                catch (Exception ex) {
                }

            }
        });

        ibfile=(ImageButton)getActivity().findViewById(R.id.ibfile);
        ibfile.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                try{
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.setType("file/*");
                    startActivity(intent);
                }
                catch (Exception ex) {
                }
            }
        });

        ibmusic=(ImageButton)getActivity().findViewById(R.id.ibmusic);
        ibmusic.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                try{
//                Intent intent = new Intent();
//                intent.setAction(android.content.Intent.ACTION_VIEW);
                    //intent.setType("audio/*");


                    Intent i;
                    PackageManager manager = getActivity().getPackageManager();
                    try {

//                        i = manager.getLaunchIntentForPackage("com.google.android.music");
//                        if (i == null)
//                            throw new PackageManager.NameNotFoundException();
//                        //i.addCategory(Intent.CATEGORY_LAUNCHER);
//                        startActivity(i);

                        SharedPreferences.Editor editor;
                        editor=pref.edit();

                        Intent intent=new Intent(getActivity().getBaseContext(), AppsListActivity.class);
                        editor.putString("showapp","music");
                        editor.commit();
                        startActivity(intent);


                    } catch (Exception e) {

                        i = manager.getLaunchIntentForPackage("com.android.music");
                        if (i == null)
                            throw new PackageManager.NameNotFoundException();
                        //i.addCategory(Intent.CATEGORY_LAUNCHER);
                        startActivity(i);
                    }




                    //startActivity(intent);
                }
                catch (Exception ex) {
                }
            }
        });

        ibvideo=(ImageButton)getActivity().findViewById(R.id.ibvideo);
        ibvideo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

//                try{
//                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
//                    intent.setType("video/mp4");
//                    startActivity(intent);
//                }
//                catch (Exception ex) {
//                }


                SharedPreferences.Editor editor;
                editor=pref.edit();

                Intent intent=new Intent(getActivity().getBaseContext(), AppsListActivity.class);
                editor.putString("showapp","video");
                editor.commit();
                startActivity(intent);
            }
        });

        ibbrowser=(ImageButton)getActivity().findViewById(R.id.ibbrowser);
        ibbrowser.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                try{
                    Intent i = new Intent();
                    i.setAction(Intent.ACTION_VIEW);
                    i.addCategory(Intent.CATEGORY_BROWSABLE);
                    i.setData(Uri.parse("http://www.leadweblife.com"));
                    startActivity(i);
                }
                catch (Exception ex) {
                }
            }
        });

        ibtorch=(ImageButton)getActivity().findViewById(R.id.ibtorch);
        ibtorch.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if (isFlashlightOn) {
                    setFlashlightOff();
                } else {
                    setFlashlightOn();
                }
            }
        });

        ibsetting=(ImageButton)getActivity().findViewById(R.id.ibsetting);
        ibsetting.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(
                        new Intent(Settings.ACTION_SETTINGS));
            }
        });



        //fab = (FloatingActionButton) findViewById(R.id.fapp);
//        fabcall = (FloatingActionButton) getActivity().findViewById(R.id.fcall);
//        fabmsg = (FloatingActionButton) getActivity().findViewById(R.id.fmsg);
//        fabcam= (FloatingActionButton) getActivity().findViewById(R.id.fab);
//        ftr = (FloatingActionButton) getActivity().findViewById(R.id.ftorch);


//        ftr.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//
//
//                if (isFlashlightOn) {
//                    setFlashlightOff();
//                } else {
//                    setFlashlightOn();
//                }
//            }
//        });
//
//
//        fabcall.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//
//                Intent intent=new Intent(Intent.ACTION_DIAL);
//                startActivity(intent);
//                //finishscreen();
//
//
//            }
//        });
//
//
//
//        fabmsg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//                Intent intent = new Intent(Intent.ACTION_MAIN);
//                intent.addCategory(Intent.CATEGORY_DEFAULT);
//                intent.setType("vnd.android-dir/mms-sms");
//                startActivity(intent);
//
//                //finishscreen();
//
//
//            }
//        });
//
//
//        fabcam.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//                startActivity(intent);
//                startActivity(intent);
//
//                //finishscreen();
//
//
//            }
//        });


        registerIntentReceivers();
    }


    private void setFlashlightOn() {

        try {
            boolean isCameraFlash = getActivity().getApplicationContext().getPackageManager()
                    .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
            if (!isCameraFlash) {
                showNoCameraAlert();
            } else {
                camera = Camera.open();
                params = camera.getParameters();
                params = camera.getParameters();
                params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(params);
                camera.startPreview();
                isFlashlightOn = true;
                ibtorch.setImageResource(R.drawable.picon);
            }
        }
        catch (Exception ex){

        }

    }

    private void setFlashlightOff() {
        try {
            if (camera == null || params == null) {
                return;
            }

            params = camera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(params);
            camera.stopPreview();
            isFlashlightOn = false;
            ibtorch.setImageResource(R.drawable.picoff);

            if (camera != null) {
                camera.release();
                camera = null;
            }
        }
        catch (Exception ex){
            Toast.makeText(getActivity(),ex.toString(),Toast.LENGTH_LONG).show();
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
        Toast.makeText(getActivity(),"No Camera Flash",Toast.LENGTH_LONG).show();
        return;
    }


    private void registerIntentReceivers() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_WALLPAPER_CHANGED);
        getActivity().registerReceiver(mWallpaperReceiver, filter);


        IntentFilter filternewmsg = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        getActivity().registerReceiver(mNewMsgReceiver, filternewmsg);


        IntentFilter filterm=new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        getActivity().registerReceiver(mMusicReceiver, filterm);


//        filter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
//        filter.addAction(Intent.ACTION_PACKAGE_INSTALL);
//        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
//        filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
//        filter.addDataScheme("package");
//        registerReceiver(mApplicationsReceiver, filter);
    }


    /**
     * When no wallpaper was manually set, a default wallpaper is used instead.
     */
    private void setDefaultWallpaper() {
        if (!mWallpaperChecked) {

            Drawable wallpaper = mView.getContext().peekWallpaper();
            if (wallpaper == null) {
                try {
                    getActivity().clearWallpaper();
                } catch (IOException e) {


                    //Log.e(LOG_TAG, "Failed to clear wallpaper " + e);
                }
            } else {
                getActivity().getWindow().setBackgroundDrawable(new ClippedDrawable(wallpaper));
            }
            mWallpaperChecked = true;
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
            getActivity().getWindow().setBackgroundDrawable(new ClippedDrawable(getActivity().getWallpaper()));
        }
    }

    private class NewMsgIntentReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            int nmsg=checkNewMsg();
            if(nmsg>0){
                ibmsg.setImageResource(R.drawable.lead_msgfill);
                ibmsg.setBackgroundColor(Color.parseColor("#e6e6e6"));
//            RotateAnimation ranim = (RotateAnimation)AnimationUtils.loadAnimation(getActivity().getBaseContext(), R.anim.rotate);
//            ibmsg.setAnimation(ranim);
            }
            else
            {
                ibmsg.setImageResource(R.drawable.lead_msg);
                ibmsg.setBackgroundColor(Color.parseColor("#ff99cc00"));
//            RotateAnimation ranim = (RotateAnimation)AnimationUtils.loadAnimation(getActivity().getBaseContext(), R.anim.rotate);
//            ibmsg.setAnimation(ranim);
            }
        }
    }

    private class MusicIntentReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //getActivity().getWindow().setBackgroundDrawable(new ClippedDrawable(getActivity().getWallpaper()));


            if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
                int state = intent.getIntExtra("state", -1);
                switch (state) {
                    case 0:
                        ibmusic.setImageResource(R.drawable.lead_music);
                        break;
                    case 1:
                        ibmusic.setImageResource(R.drawable.lead_headset);
                        Toast.makeText(getActivity(),"Take care of your ear.\n Dont play music loudly",Toast.LENGTH_LONG).show();
                        break;
                    default:
                        ibmusic.setImageResource(R.drawable.lead_music);
                }
            }

        }
    }



    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case (PICK_CONTACT) :
                if (resultCode == Activity.RESULT_OK) {
                    Cursor cursor =  getActivity().managedQuery(data.getData(), null, null, null, null);
                    cursor.moveToNext();
                }
                break;
            case (SELECT_PICTURE):
                if (resultCode == Activity.RESULT_OK) {

                    Uri selectedImage = data.getData();
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };

                    Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();


                    //ibgallery.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                    Drawable dr = new BitmapDrawable(picturePath);
                    LinearLayout linearLayout = (LinearLayout)getActivity().findViewById(R.id.liid);
                    linearLayout.setBackgroundDrawable(dr);
                    clearBG();
                }

                break;
        }
    }


    private  void  clearBG(){

        ibcall.setBackgroundColor(Color.TRANSPARENT);

        ShapeDrawable shapedrawable = new ShapeDrawable();
        shapedrawable.setShape(new RectShape());
        shapedrawable.getPaint().setColor(Color.parseColor("#00ddff"));
        shapedrawable.getPaint().setStrokeWidth(20f);
        shapedrawable.getPaint().setStyle(Paint.Style.STROKE);
        ibcall.setBackground(shapedrawable);


        ibcontact.setBackgroundColor(Color.TRANSPARENT);
        ShapeDrawable shapedrawablec = new ShapeDrawable();
        shapedrawablec.setShape(new RectShape());
        shapedrawablec.getPaint().setColor(Color.parseColor("#0099cc"));
        shapedrawablec.getPaint().setStrokeWidth(20f);
        shapedrawablec.getPaint().setStyle(Paint.Style.STROKE);
        ibcontact.setBackground(shapedrawablec);


        ibmsg.setBackgroundColor(Color.TRANSPARENT);
        ShapeDrawable shapedrawablemsg = new ShapeDrawable();
        shapedrawablemsg.setShape(new RectShape());
        shapedrawablemsg.getPaint().setColor(Color.parseColor("#ff99cc00"));
        shapedrawablemsg.getPaint().setStrokeWidth(20f);
        shapedrawablemsg.getPaint().setStyle(Paint.Style.STROKE);
        ibmsg.setBackground(shapedrawablemsg);


        ibplaystore.setBackgroundColor(Color.TRANSPARENT);
        ShapeDrawable shapedrawableps = new ShapeDrawable();
        shapedrawableps.setShape(new RectShape());

        shapedrawableps.getPaint().setColor(Color.parseColor("#ffffbb33"));
        shapedrawableps.getPaint().setStrokeWidth(20f);
        shapedrawableps.getPaint().setStyle(Paint.Style.STROKE);
        ibplaystore.setBackground(shapedrawableps);


        ibemail.setBackgroundColor(Color.TRANSPARENT);
        ShapeDrawable shapedrawableemail = new ShapeDrawable();
        shapedrawableemail.setShape(new RectShape());
        shapedrawableemail.getPaint().setColor(Color.parseColor("#FF4081"));
        shapedrawableemail.getPaint().setStrokeWidth(20f);
        shapedrawableemail.getPaint().setStyle(Paint.Style.STROKE);
        ibemail.setBackground(shapedrawableemail);


        ibcamera.setBackgroundColor(Color.TRANSPARENT);
        ShapeDrawable shapedrawablecam = new ShapeDrawable();
        shapedrawablecam.setShape(new RectShape());
        shapedrawablecam.getPaint().setColor(Color.parseColor("#ff0099cc"));
        shapedrawablecam.getPaint().setStrokeWidth(20f);
        shapedrawablecam.getPaint().setStyle(Paint.Style.STROKE);
        ibcamera.setBackground(shapedrawablecam);


        ibgallery.setBackgroundColor(Color.TRANSPARENT);
        ShapeDrawable shapedrawableg = new ShapeDrawable();
        shapedrawableg.setShape(new RectShape());
        shapedrawableg.getPaint().setColor(Color.parseColor("#ffaa66cc"));
        shapedrawableg.getPaint().setStrokeWidth(20f);
        shapedrawableg.getPaint().setStyle(Paint.Style.STROKE);
        ibgallery.setBackground(shapedrawableg);


        ibfile.setBackgroundColor(Color.TRANSPARENT);
        ShapeDrawable shapedrawablefile = new ShapeDrawable();
        shapedrawablefile.setShape(new RectShape());
        shapedrawablefile.getPaint().setColor(Color.parseColor("#ffff8800"));
        shapedrawablefile.getPaint().setStrokeWidth(20f);
        shapedrawablefile.getPaint().setStyle(Paint.Style.STROKE);
        ibfile.setBackground(shapedrawablefile);

        ibmusic.setBackgroundColor(Color.TRANSPARENT);
        ShapeDrawable shapedrawablemusic = new ShapeDrawable();
        shapedrawablemusic.setShape(new RectShape());
        shapedrawablemusic.getPaint().setColor(Color.parseColor("#ff00ddff"));
        shapedrawablemusic.getPaint().setStrokeWidth(20f);
        shapedrawablemusic.getPaint().setStyle(Paint.Style.STROKE);
        ibmusic.setBackground(shapedrawablemusic);

        ibvideo.setBackgroundColor(Color.TRANSPARENT);
        ShapeDrawable shapedrawablevid = new ShapeDrawable();
        shapedrawablevid.setShape(new RectShape());
        shapedrawablevid.getPaint().setColor(Color.parseColor("#FF4081"));
        shapedrawablevid.getPaint().setStrokeWidth(20f);
        shapedrawablevid.getPaint().setStyle(Paint.Style.STROKE);
        ibvideo.setBackground(shapedrawablevid);

        ibbrowser.setBackgroundColor(Color.TRANSPARENT);
        ShapeDrawable shapedrawablebro = new ShapeDrawable();
        shapedrawablebro.setShape(new RectShape());
        shapedrawablebro.getPaint().setColor(Color.parseColor("#ff0099cc"));
        shapedrawablebro.getPaint().setStrokeWidth(20f);
        shapedrawablebro.getPaint().setStyle(Paint.Style.STROKE);
        ibbrowser.setBackground(shapedrawablebro);

        ibtorch.setBackgroundColor(Color.TRANSPARENT);
        ShapeDrawable shapedrawabletorch = new ShapeDrawable();
        shapedrawabletorch.setShape(new RectShape());
        shapedrawabletorch.getPaint().setColor(Color.parseColor("#ffaa66cc"));
        shapedrawabletorch.getPaint().setStrokeWidth(20f);
        shapedrawabletorch.getPaint().setStyle(Paint.Style.STROKE);
        ibtorch.setBackground(shapedrawabletorch);

        ibsetting.setBackgroundColor(Color.TRANSPARENT);
        ShapeDrawable shapedrawableset = new ShapeDrawable();
        shapedrawableset.setShape(new RectShape());
        shapedrawableset.getPaint().setColor(Color.parseColor("#ffff8800"));
        shapedrawableset.getPaint().setStrokeWidth(20f);
        shapedrawableset.getPaint().setStyle(Paint.Style.STROKE);
        ibsetting.setBackground(shapedrawableset);
    }

    @Override
    public void onResume() {
        super.onResume();
        isResumed = true;

        int nmsg=checkNewMsg();
        if(nmsg>0){
            ibmsg.setImageResource(R.drawable.lead_msgfill);
            ibmsg.setBackgroundColor(Color.parseColor("#e6e6e6"));
//            RotateAnimation ranim = (RotateAnimation)AnimationUtils.loadAnimation(getActivity().getBaseContext(), R.anim.rotate);
//            ibmsg.setAnimation(ranim);
        }
        else
        {
            ibmsg.setImageResource(R.drawable.lead_msg);
            ibmsg.setBackgroundColor(Color.parseColor("#ff99cc00"));
//            RotateAnimation ranim = (RotateAnimation)AnimationUtils.loadAnimation(getActivity().getBaseContext(), R.anim.rotate);
//            ibmsg.setAnimation(ranim);
        }
    }

    private  int  checkNewMsg(){
        final Uri SMS_INBOX = Uri.parse("content://sms/inbox");

        Cursor c = getActivity().getContentResolver().query(SMS_INBOX, null, "read = 0", null, null);
        int unreadMessagesCount = c.getCount();
        c.deactivate();

        return unreadMessagesCount;
    }

    /**
     * Drag and Drop
     */

    private void ondragOption(){

        ibcall.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(ibcall);

                v.startDrag(dragData,myShadow,null,0);
                return true;
            }
        });

        ibcall.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction())
                {
                    case DragEvent.ACTION_DRAG_STARTED:
                        layoutParams = (LinearLayout.LayoutParams)v.getLayoutParams();
                       // Log.d(msg, "Action is DragEvent.ACTION_DRAG_STARTED");

                        // Do nothing
                        break;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        //Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENTERED");
                        int x_cord = (int) event.getX();
                        int y_cord = (int) event.getY();
                        break;

                    case DragEvent.ACTION_DRAG_EXITED :
                        //Log.d(msg, "Action is DragEvent.ACTION_DRAG_EXITED");
                        x_cord = (int) event.getX();
                        y_cord = (int) event.getY();
                        layoutParams.leftMargin = x_cord;
                        layoutParams.topMargin = y_cord;
                        v.setLayoutParams(layoutParams);
                        break;

                    case DragEvent.ACTION_DRAG_LOCATION  :
                        //Log.d(msg, "Action is DragEvent.ACTION_DRAG_LOCATION");
                        x_cord = (int) event.getX();
                        y_cord = (int) event.getY();
                        break;

                    case DragEvent.ACTION_DRAG_ENDED   :
                        //Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENDED");

                        // Do nothing
                        break;

                    case DragEvent.ACTION_DROP:
                        //Log.d(msg, "ACTION_DROP event");

                        // Do nothing
                        break;
                    default: break;
                }
                return true;
            }
        });

        ibcall.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(ibcall);

                    ibcall.startDrag(data, shadowBuilder, ibcall, 0);
                    ibcall.setVisibility(View.INVISIBLE);
                    return true;
                }
                else
                {
                    return false;
                }
            }
        });



    }

}
