package com.leadweblife.leadlauncher;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ankesh on 27/4/16.
 */
public class AppListAlphabet extends Activity {

    ArrayList<App> appListA,appListB,appListC,appListD,appListE,appListF,appListG,appListH,appListI,appListJ,appListK,appListL,appListM;
    ArrayList<App> appListN,appListO,appListP,appListQ,appListR,appListS,appListT,appListU,appListV,appListW,appListX,appListY,appListZ;

    GridView gridViewA,gridViewB,gridViewC,gridViewD,gridViewE,gridViewF,gridViewG,gridViewH,gridViewI,gridViewJ,gridViewK,gridViewL,gridViewM;
    GridView gridViewN,gridViewO,gridViewP,gridViewQ,gridViewR,gridViewS,gridViewT,gridViewU,gridViewV,gridViewW,gridViewX,gridViewY,gridViewZ;

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
        setContentView(R.layout.show_app_alpabhet);


        gridViewA = (GridView) findViewById(R.id.gridViewA);
        gridViewB = (GridView) findViewById(R.id.gridViewB);
        gridViewC = (GridView) findViewById(R.id.gridViewC);
        gridViewD= (GridView) findViewById(R.id.gridViewD);
        gridViewE= (GridView) findViewById(R.id.gridViewE);
        gridViewF= (GridView) findViewById(R.id.gridViewF);
        gridViewG= (GridView) findViewById(R.id.gridViewG);
        gridViewH= (GridView) findViewById(R.id.gridViewH);
        gridViewI= (GridView) findViewById(R.id.gridViewI);
        gridViewJ= (GridView) findViewById(R.id.gridViewJ);
        gridViewK= (GridView) findViewById(R.id.gridViewK);
        gridViewL= (GridView) findViewById(R.id.gridViewL);
        gridViewM= (GridView) findViewById(R.id.gridViewM);


        gridViewN= (GridView) findViewById(R.id.gridViewN);
        gridViewO= (GridView) findViewById(R.id.gridViewO);
        gridViewP= (GridView) findViewById(R.id.gridViewP);
        gridViewQ= (GridView) findViewById(R.id.gridViewQ);
        gridViewR= (GridView) findViewById(R.id.gridViewR);
        gridViewS= (GridView) findViewById(R.id.gridViewS);
        gridViewT= (GridView) findViewById(R.id.gridViewT);
        gridViewU= (GridView) findViewById(R.id.gridViewU);
        gridViewV= (GridView) findViewById(R.id.gridViewV);
        gridViewW= (GridView) findViewById(R.id.gridViewW);
        gridViewX= (GridView) findViewById(R.id.gridViewX);
        gridViewY= (GridView) findViewById(R.id.gridViewY);
        gridViewZ= (GridView) findViewById(R.id.gridViewZ);


        final PackageManager manager = getPackageManager();
//        appListA = getAppsA();
//        appListB= getAppsB();
//        appListC= getAppsC();

        appListA=new ArrayList<>();
        appListB=new ArrayList<>();
        appListC=new ArrayList<>();
        appListD=new ArrayList<>();
        appListE=new ArrayList<>();
        appListF=new ArrayList<>();
        appListG=new ArrayList<>();
        appListH=new ArrayList<>();
        appListI=new ArrayList<>();
        appListJ=new ArrayList<>();
        appListK=new ArrayList<>();
        appListL=new ArrayList<>();
        appListM=new ArrayList<>();


        appListN=new ArrayList<>();
        appListO=new ArrayList<>();
        appListP=new ArrayList<>();
        appListQ=new ArrayList<>();
        appListR=new ArrayList<>();
        appListS=new ArrayList<>();
        appListT=new ArrayList<>();
        appListU=new ArrayList<>();
        appListV=new ArrayList<>();
        appListW=new ArrayList<>();
        appListX=new ArrayList<>();
        appListY=new ArrayList<>();
        appListZ=new ArrayList<>();


        getApps();
        showApps();
//        gridViewA.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//                                    long arg3) {
//                // TODO Auto-generated method stub
////                Intent i = manager.getLaunchIntentForPackage(appList.get(arg2).packname
////                        .toString());
////                AppListAlphabet.this.startActivity(i);
//            }
//        });

        dbh=new DBHandler(AppListAlphabet.this);

        contactList = new ArrayList<HashMap<String, String>>();

//        AdView mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder()
//                .addTestDevice("012345678ABCDEF")
//                .build();
//        mAdView.loadAd(adRequest);

    }

    private void showApps() {
        // TODO Auto-generated method stub

        gridViewA = (GridView) findViewById(R.id.gridViewA);

        ArrayAdapter<App> adapter = new ArrayAdapter<App>(this, R.layout.list_item,
                appListA) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item,
                            null);
                }

                ImageView appIcon = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                appIcon.setImageDrawable(appListA.get(position).icon);

                TextView appName = (TextView) convertView
                        .findViewById(R.id.textView1);
                appName.setText(appListA.get(position).appName);

                return convertView;
            }
        };

        gridViewA.setAdapter(adapter);






        gridViewB = (GridView) findViewById(R.id.gridViewB);
        ArrayAdapter<App> adapterb = new ArrayAdapter<App>(this, R.layout.list_item,
                appListB) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item,
                            null);
                }
                convertView = getLayoutInflater().inflate(R.layout.list_item,
                        null);

                ImageView appIcon = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                appIcon.setImageDrawable(appListB.get(position).icon);

                TextView appName = (TextView) convertView
                        .findViewById(R.id.textView1);
                appName.setText(appListB.get(position).appName);

                return convertView;
            }
        };
        gridViewB.setAdapter(adapterb);




        gridViewC = (GridView) findViewById(R.id.gridViewC);

        ArrayAdapter<App> adapterc = new ArrayAdapter<App>(this, R.layout.list_item,
                appListC) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item,
                            null);
                }

                ImageView appIcon = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                appIcon.setImageDrawable(appListC.get(position).icon);

                TextView appName = (TextView) convertView
                        .findViewById(R.id.textView1);
                appName.setText(appListC.get(position).appName);

                return convertView;
            }
        };

        gridViewC.setAdapter(adapterc);



        ArrayAdapter<App> adapterd = new ArrayAdapter<App>(this, R.layout.list_item,
                appListD) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item,
                            null);
                }

                ImageView appIcon = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                appIcon.setImageDrawable(appListD.get(position).icon);

                TextView appName = (TextView) convertView
                        .findViewById(R.id.textView1);
                appName.setText(appListD.get(position).appName);

                return convertView;
            }
        };

        gridViewD.setAdapter(adapterd);




        ArrayAdapter<App> adaptere = new ArrayAdapter<App>(this, R.layout.list_item,
                appListE) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item,
                            null);
                }

                ImageView appIcon = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                appIcon.setImageDrawable(appListE.get(position).icon);

                TextView appName = (TextView) convertView
                        .findViewById(R.id.textView1);
                appName.setText(appListE.get(position).appName);

                return convertView;
            }
        };

        gridViewE.setAdapter(adaptere);




        ArrayAdapter<App> adapterf = new ArrayAdapter<App>(this, R.layout.list_item,
                appListF) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item,
                            null);
                }

                ImageView appIcon = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                appIcon.setImageDrawable(appListF.get(position).icon);

                TextView appName = (TextView) convertView
                        .findViewById(R.id.textView1);
                appName.setText(appListF.get(position).appName);

                return convertView;
            }
        };

        gridViewF.setAdapter(adapterf);


        ArrayAdapter<App> adapterg = new ArrayAdapter<App>(this, R.layout.list_item,
                appListG) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item,
                            null);
                }

                ImageView appIcon = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                appIcon.setImageDrawable(appListG.get(position).icon);

                TextView appName = (TextView) convertView
                        .findViewById(R.id.textView1);
                appName.setText(appListG.get(position).appName);

                return convertView;
            }
        };

        gridViewG.setAdapter(adapterg);



        ArrayAdapter<App> adapterh = new ArrayAdapter<App>(this, R.layout.list_item,
                appListH) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item,
                            null);
                }

                ImageView appIcon = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                appIcon.setImageDrawable(appListH.get(position).icon);

                TextView appName = (TextView) convertView
                        .findViewById(R.id.textView1);
                appName.setText(appListH.get(position).appName);

                return convertView;
            }
        };

        gridViewH.setAdapter(adapterh);


        ArrayAdapter<App> adapteri = new ArrayAdapter<App>(this, R.layout.list_item,
                appListI) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item,
                            null);
                }

                ImageView appIcon = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                appIcon.setImageDrawable(appListI.get(position).icon);

                TextView appName = (TextView) convertView
                        .findViewById(R.id.textView1);
                appName.setText(appListI.get(position).appName);

                return convertView;
            }
        };

        gridViewI.setAdapter(adapteri);



        ArrayAdapter<App> adapterj = new ArrayAdapter<App>(this, R.layout.list_item,
                appListJ) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item,
                            null);
                }

                ImageView appIcon = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                appIcon.setImageDrawable(appListJ.get(position).icon);

                TextView appName = (TextView) convertView
                        .findViewById(R.id.textView1);
                appName.setText(appListJ.get(position).appName);

                return convertView;
            }
        };

        gridViewJ.setAdapter(adapterj);




        ArrayAdapter<App> adapterk = new ArrayAdapter<App>(this, R.layout.list_item,
                appListK) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item,
                            null);
                }

                ImageView appIcon = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                appIcon.setImageDrawable(appListK.get(position).icon);

                TextView appName = (TextView) convertView
                        .findViewById(R.id.textView1);
                appName.setText(appListK.get(position).appName);

                return convertView;
            }
        };

        gridViewK.setAdapter(adapterk);



        ArrayAdapter<App> adapterl = new ArrayAdapter<App>(this, R.layout.list_item,
                appListL) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item,
                            null);
                }

                ImageView appIcon = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                appIcon.setImageDrawable(appListL.get(position).icon);

                TextView appName = (TextView) convertView
                        .findViewById(R.id.textView1);
                appName.setText(appListL.get(position).appName);

                return convertView;
            }
        };

        gridViewL.setAdapter(adapterl);



        ArrayAdapter<App> adapterm = new ArrayAdapter<App>(this, R.layout.list_item,
                appListM) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item,
                            null);
                }

                ImageView appIcon = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                appIcon.setImageDrawable(appListM.get(position).icon);

                TextView appName = (TextView) convertView
                        .findViewById(R.id.textView1);
                appName.setText(appListM.get(position).appName);

                return convertView;
            }
        };

        gridViewM.setAdapter(adapterm);



        ArrayAdapter<App> adaptern = new ArrayAdapter<App>(this, R.layout.list_item,
                appListN) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item,
                            null);
                }

                ImageView appIcon = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                appIcon.setImageDrawable(appListN.get(position).icon);

                TextView appName = (TextView) convertView
                        .findViewById(R.id.textView1);
                appName.setText(appListN.get(position).appName);

                return convertView;
            }
        };

        gridViewN.setAdapter(adaptern);


        ArrayAdapter<App> adaptero = new ArrayAdapter<App>(this, R.layout.list_item,
                appListO) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item,
                            null);
                }

                ImageView appIcon = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                appIcon.setImageDrawable(appListO.get(position).icon);

                TextView appName = (TextView) convertView
                        .findViewById(R.id.textView1);
                appName.setText(appListO.get(position).appName);

                return convertView;
            }
        };

        gridViewO.setAdapter(adaptero);



        ArrayAdapter<App> adapterp = new ArrayAdapter<App>(this, R.layout.list_item,
                appListP) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item,
                            null);
                }

                ImageView appIcon = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                appIcon.setImageDrawable(appListP.get(position).icon);

                TextView appName = (TextView) convertView
                        .findViewById(R.id.textView1);
                appName.setText(appListP.get(position).appName);

                return convertView;
            }
        };

        gridViewP.setAdapter(adapterp);





        ArrayAdapter<App> adapterq = new ArrayAdapter<App>(this, R.layout.list_item,
                appListQ) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item,
                            null);
                }

                ImageView appIcon = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                appIcon.setImageDrawable(appListQ.get(position).icon);

                TextView appName = (TextView) convertView
                        .findViewById(R.id.textView1);
                appName.setText(appListQ.get(position).appName);

                return convertView;
            }
        };

        gridViewQ.setAdapter(adapterq);




        ArrayAdapter<App> adapterr = new ArrayAdapter<App>(this, R.layout.list_item,
                appListR) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item,
                            null);
                }

                ImageView appIcon = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                appIcon.setImageDrawable(appListR.get(position).icon);

                TextView appName = (TextView) convertView
                        .findViewById(R.id.textView1);
                appName.setText(appListR.get(position).appName);

                return convertView;
            }
        };

        gridViewR.setAdapter(adapterr);




        ArrayAdapter<App> adapters = new ArrayAdapter<App>(this, R.layout.list_item,
                appListS) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item,
                            null);
                }

                ImageView appIcon = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                appIcon.setImageDrawable(appListS.get(position).icon);

                TextView appName = (TextView) convertView
                        .findViewById(R.id.textView1);
                appName.setText(appListS.get(position).appName);

                return convertView;
            }
        };

        gridViewS.setAdapter(adapters);




        ArrayAdapter<App> adaptert = new ArrayAdapter<App>(this, R.layout.list_item,
                appListT) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item,
                            null);
                }

                ImageView appIcon = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                appIcon.setImageDrawable(appListT.get(position).icon);

                TextView appName = (TextView) convertView
                        .findViewById(R.id.textView1);
                appName.setText(appListT.get(position).appName);

                return convertView;
            }
        };

        gridViewT.setAdapter(adaptert);



        ArrayAdapter<App> adapteru = new ArrayAdapter<App>(this, R.layout.list_item,
                appListU) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item,
                            null);
                }

                ImageView appIcon = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                appIcon.setImageDrawable(appListU.get(position).icon);

                TextView appName = (TextView) convertView
                        .findViewById(R.id.textView1);
                appName.setText(appListU.get(position).appName);

                return convertView;
            }
        };

        gridViewU.setAdapter(adapteru);



        ArrayAdapter<App> adapterv = new ArrayAdapter<App>(this, R.layout.list_item,
                appListV) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item,
                            null);
                }

                ImageView appIcon = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                appIcon.setImageDrawable(appListV.get(position).icon);

                TextView appName = (TextView) convertView
                        .findViewById(R.id.textView1);
                appName.setText(appListV.get(position).appName);

                return convertView;
            }
        };

        gridViewV.setAdapter(adapterv);

//
//        ArrayAdapter<App> adapterV = new ArrayAdapter<App>(this, R.layout.list_item,
//                appListV) {
//
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                if (convertView == null) {
//                    convertView = getLayoutInflater().inflate(R.layout.list_item,
//                            null);
//                }
//
//                ImageView appIcon = (ImageView) convertView
//                        .findViewById(R.id.imageView1);
//                appIcon.setImageDrawable(appListV.get(position).icon);
//
//                TextView appName = (TextView) convertView
//                        .findViewById(R.id.textView1);
//                appName.setText(appListV.get(position).appName);
//
//                return convertView;
//            }
//        };
//
//        gridViewV.setAdapter(adapterv);




        ArrayAdapter<App> adapterx = new ArrayAdapter<App>(this, R.layout.list_item,
                appListX) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item,
                            null);
                }

                ImageView appIcon = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                appIcon.setImageDrawable(appListX.get(position).icon);

                TextView appName = (TextView) convertView
                        .findViewById(R.id.textView1);
                appName.setText(appListX.get(position).appName);

                return convertView;
            }
        };

        gridViewX.setAdapter(adapterx);


        ArrayAdapter<App> adapterw = new ArrayAdapter<App>(this, R.layout.list_item,
                appListW) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item,
                            null);
                }

                ImageView appIcon = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                appIcon.setImageDrawable(appListW.get(position).icon);

                TextView appName = (TextView) convertView
                        .findViewById(R.id.textView1);
                appName.setText(appListW.get(position).appName);

                return convertView;
            }
        };

        gridViewW.setAdapter(adapterw);



        ArrayAdapter<App> adaptery = new ArrayAdapter<App>(this, R.layout.list_item,
                appListY) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item,
                            null);
                }

                ImageView appIcon = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                appIcon.setImageDrawable(appListY.get(position).icon);

                TextView appName = (TextView) convertView
                        .findViewById(R.id.textView1);
                appName.setText(appListY.get(position).appName);

                return convertView;
            }
        };

        gridViewY.setAdapter(adaptery);




        ArrayAdapter<App> adapterz = new ArrayAdapter<App>(this, R.layout.list_item,
                appListZ) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item,
                            null);
                }

                ImageView appIcon = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                appIcon.setImageDrawable(appListZ.get(position).icon);

                TextView appName = (TextView) convertView
                        .findViewById(R.id.textView1);
                appName.setText(appListZ.get(position).appName);

                return convertView;
            }
        };

        gridViewZ.setAdapter(adapterz);
    }


    private ArrayList<App> getApps() {
        // TODO Auto-generated method stub

        dbh=new DBHandler(AppListAlphabet.this);
        //  dbh.deleteApp();

        PackageManager manager = getPackageManager();
        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableActivities = manager.queryIntentActivities(
                i, 0);

        ArrayList<App> temp = new ArrayList<App>();


        if(dbh.getAppCount()>0) {
            List<DBApp> contacts = dbh.getallApp();

            for (DBApp cn : contacts) {

                HashMap<String, String> contact = new HashMap<String, String>();


                App app=new App();

                String appPname=cn.get_pname();
                String appName=cn.get_name();

                app.packname=appPname;
                app.appName=appName;



                SharedPreferences pref;
                pref= getSharedPreferences("leadlaunch", MODE_PRIVATE);
                String showwhat=pref.getString("showapp","");

                for (ResolveInfo ri : availableActivities) {

                    if (ri.activityInfo.packageName.toString().equalsIgnoreCase(cn.get_pname()))
                        app.icon = ri.activityInfo.loadIcon(manager);

                }

                String  appFChar=appName.substring(0,1).toString().toUpperCase();


                switch (appFChar)
                {
                    case "A":
                        appListA.add(app);
                        break;
                    case "B":
                        appListB.add(app);
                        break;
                    case "C":
                        appListC.add(app);
                        break;
                    case "D":
                        appListD.add(app);
                        break;
                    case "E":
                        appListE.add(app);
                        break;
                    case "F":
                        appListF.add(app);
                        break;
                    case "G":
                        appListG.add(app);
                        break;
                    case "H":
                        appListH.add(app);
                        break;
                    case "I":
                        appListI.add(app);
                        break;
                    case "J":
                        appListJ.add(app);
                        break;
                    case "K":
                        appListK.add(app);
                        break;
                    case "L":
                        appListL.add(app);
                        break;
                    case "M":
                        appListM.add(app);
                        break;
                    case "N":
                        appListN.add(app);
                        break;
                    case "O":
                        appListO.add(app);
                        break;
                    case "P":
                        appListP.add(app);
                        break;
                    case "Q":
                        appListQ.add(app);
                        break;
                    case "R":
                        appListR.add(app);
                        break;
                    case "S":
                        appListS.add(app);
                        break;
                    case "T":
                        appListT.add(app);
                        break;
                    case "U":
                        appListU.add(app);
                        break;
                    case "V":
                        appListV.add(app);
                        break;
                    case "W":
                        appListW.add(app);
                        break;
                    case "X":
                        appListX.add(app);
                        break;
                    case "Y":
                        appListY.add(app);
                        break;
                    case "Z":
                        appListZ.add(app);
                        break;

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

            Intent intent=new Intent(AppListAlphabet.this,ListAppListActivity.class);
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
