package com.leadweblife.leadlauncher;

/**
 * Created by ankesh on 18/4/16.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
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
 * @author mwho
 *
 */
public class TabAppList extends Fragment {
    /** (non-Javadoc)
     * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
     */

    ArrayList<App> appList;
    //GridView gridView;
    ListView gridView;

  //  DBHandler dbh;
  //  JSONArray contacts = null;
    //ArrayList<HashMap<String, String>> contactList;

    View mView;
    LayoutInflater infl;
    ArrayAdapter<App> adapter;

   // ArrayList<HashMap<String, String>> contactList;

    private final BroadcastReceiver mApplicationsReceiver = new ApplicationsIntentReceiver();

    TextView tvsearch;
    Button searchSendButton;



    ArrayList<App> appListNum,appListA,appListB,appListC,appListD,appListE,appListF,appListG,appListH,appListI,appListJ,appListK,appListL,appListM;
    ArrayList<App> appListN,appListO,appListP,appListQ,appListR,appListS,appListT,appListU,appListV,appListW,appListX,appListY,appListZ;

    GridView gridViewNum,gridViewA,gridViewB,gridViewC,gridViewD,gridViewE,gridViewF,gridViewG,gridViewH,gridViewI,gridViewJ,gridViewK,gridViewL,gridViewM;
    GridView gridViewN,gridViewO,gridViewP,gridViewQ,gridViewR,gridViewS,gridViewT,gridViewU,gridViewV,gridViewW,gridViewX,gridViewY,gridViewZ;

    DBHandler dbh;

    JSONArray contacts = null;
    ArrayList<HashMap<String, String>> contactList;

    String[] social={"facebook","whatsapp","twitter","pinterest","chat","social","tumblr","evernote"};
    String[] game={"game","cricket","hockey","race","racing","sudoku"};
    String[] musicp={"music","song","sing","player"};
    String[] videop={"video","vlc","youtube","vimeo"};



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

        infl = inflater;
        mView= inflater.inflate(R.layout.show_app_alpabhet, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        gridViewNum=(GridView)getView().findViewById(R.id.gridViewNum);
        gridViewA = (GridView)getView().findViewById(R.id.gridViewA);
        gridViewB = (GridView)getView().findViewById(R.id.gridViewB);
        gridViewC = (GridView) getView().findViewById(R.id.gridViewC);
        gridViewD= (GridView) getView().findViewById(R.id.gridViewD);
        gridViewE= (GridView) getView().findViewById(R.id.gridViewE);
        gridViewF= (GridView) getView().findViewById(R.id.gridViewF);
        gridViewG= (GridView) getView().findViewById(R.id.gridViewG);
        gridViewH= (GridView) getView().findViewById(R.id.gridViewH);
        gridViewI= (GridView) getView().findViewById(R.id.gridViewI);
        gridViewJ= (GridView) getView().findViewById(R.id.gridViewJ);
        gridViewK= (GridView) getView().findViewById(R.id.gridViewK);
        gridViewL= (GridView) getView().findViewById(R.id.gridViewL);
        gridViewM= (GridView) getView().findViewById(R.id.gridViewM);


        gridViewN= (GridView) getView().findViewById(R.id.gridViewN);
        gridViewO= (GridView) getView().findViewById(R.id.gridViewO);
        gridViewP= (GridView) getView().findViewById(R.id.gridViewP);
        gridViewQ= (GridView) getView().findViewById(R.id.gridViewQ);
        gridViewR= (GridView) getView().findViewById(R.id.gridViewR);
        gridViewS= (GridView) getView().findViewById(R.id.gridViewS);
        gridViewT= (GridView) getView().findViewById(R.id.gridViewT);
        gridViewU= (GridView) getView().findViewById(R.id.gridViewU);
        gridViewV= (GridView) getView().findViewById(R.id.gridViewV);
        gridViewW= (GridView) getView().findViewById(R.id.gridViewW);
        gridViewX= (GridView) getView().findViewById(R.id.gridViewX);
        gridViewY= (GridView) getView().findViewById(R.id.gridViewY);
        gridViewZ= (GridView) getView().findViewById(R.id.gridViewZ);


        final PackageManager manager = getActivity().getPackageManager();
        appListNum=new ArrayList<>();
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


        registerIntentReceivers();
        init();
        clickListeners();

        AdView mAdView = (AdView) mView.findViewById(R.id.adView);
        //AdView mAdView2 = (AdView) mView.getView().findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("012345678ABCDEF")
                .build();
        mAdView.loadAd(adRequest);
        //mAdView2.loadAd(adRequest);



    }

    private void registerIntentReceivers() {

        IntentFilter filter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
        filter.addAction(Intent.ACTION_PACKAGE_INSTALL);
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
        filter.addDataScheme("package");
        getActivity().registerReceiver(mApplicationsReceiver, filter);
    }


    private void init(){
       // final PackageManager manager = getActivity().getPackageManager();

       // dbh=new DBHandler(getActivity());
        contactList = new ArrayList<HashMap<String, String>>();
        contactList.clear();;
        Log.d("Init called", "Init called");

        appListNum.clear();
        appListA.clear();
        appListB.clear();
        appListC.clear();
        appListD.clear();
        appListE.clear();
        appListF.clear();
        appListG.clear();
        appListH.clear();
        appListI.clear();
        appListJ.clear();
        appListK.clear();
        appListL.clear();
        appListM.clear();
        appListN.clear();
        appListO.clear();
        appListP.clear();
        appListQ.clear();
        appListR.clear();
        appListS.clear();
        appListT.clear();
        appListU.clear();
        appListV.clear();
        appListW.clear();
        appListX.clear();
        appListY.clear();
        appListZ.clear();



        getApps();
        showApps();
    }


    private void getApps() {
        // TODO Auto-generated method stub

        dbh=new DBHandler(getActivity());
        dbh.deleteApp();

        PackageManager manager = getActivity().getPackageManager();
        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableActivities = manager.queryIntentActivities(
                i, 0);

        ArrayList<App> temp = new ArrayList<App>();
        for (ResolveInfo ri : availableActivities) {
                dbh.addApp(new DBApp(ri.loadLabel(manager).toString(), ri.activityInfo.packageName));
        }


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
                pref= getActivity().getSharedPreferences("leadlaunch", getActivity().MODE_PRIVATE);
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
                    default:
                        appListNum.add(app);

                }


            }
        }

        //return temp;
    }




    private ArrayList<App> getAppsSearch(String tsearch) {
        // TODO Auto-generated method stub

        dbh=new DBHandler(getActivity());
        //dbh.deleteApp();

        PackageManager manager = getActivity().getPackageManager();
        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableActivities = manager.queryIntentActivities(
                i, 0);

        ArrayList<App> temp = new ArrayList<App>();

        if(dbh.getAppCount()>0) {
            List<DBApp> contacts = dbh.getSelApp(tsearch);

            for (DBApp cn : contacts) {

                HashMap<String, String> contact = new HashMap<String, String>();

//                    contact.put(TAG_slNo, cn.getslno());
//                    contact.put(TAG_my, cn.getmonthyear());
//                    contact.put(TAG_Amount, cn.getamount());

                App app=new App();
                app.packname=cn.get_pname();
                app.appName=cn.get_name();

                for (ResolveInfo ri : availableActivities) {

                    if(ri.activityInfo.packageName.toString().equalsIgnoreCase(cn.get_pname()))
                        app.icon=ri.activityInfo.loadIcon(manager);

                }
                temp.add(app);
            }
        }

        return temp;
    }




    private void showApps() {
        // TODO Auto-generated method stub

       // gridViewA = (GridView) mView.findViewById(R.id.gridViewA);


        ArrayAdapter<App> adapterNum = new ArrayAdapter<App>(getActivity(), R.layout.list_item,
                appListNum) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
                            null);
                }

                ImageView appIcon = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                appIcon.setImageDrawable(appListNum.get(position).icon);

                TextView appName = (TextView) convertView
                        .findViewById(R.id.textView1);
                appName.setText(appListNum.get(position).appName);

                return convertView;
            }
        };

        if(adapterNum.getCount()>0)
            gridViewNum.setAdapter(adapterNum);
        else
        {
            TextView txtn=(TextView)getActivity().findViewById(R.id.txtnum);
            txtn.setVisibility(View.GONE);
            gridViewNum.setVisibility(View.GONE);
        }


        ArrayAdapter<App> adapter = new ArrayAdapter<App>(getActivity(), R.layout.list_item,
                appListA) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
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

        if(adapter.getCount()>0)
            gridViewA.setAdapter(adapter);
        else
        {
            TextView txta=(TextView)getActivity().findViewById(R.id.txta);
            txta.setVisibility(View.GONE);
            gridViewA.setVisibility(View.GONE);
        }





       // gridViewB = (GridView) mView.findViewById(R.id.gridViewB);
        ArrayAdapter<App> adapterb = new ArrayAdapter<App>(getActivity(), R.layout.list_item,
                appListB) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
                            null);
                }
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
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
        if(adapterb.getCount()>0)
            gridViewB.setAdapter(adapterb);
        else
        {
            TextView txtb=(TextView)getActivity().findViewById(R.id.txtb);
            txtb.setVisibility(View.GONE);
            gridViewB.setVisibility(View.GONE);
        }




     //   gridViewC = (GridView) mView.findViewById(R.id.gridViewC);

        ArrayAdapter<App> adapterc = new ArrayAdapter<App>(getActivity(), R.layout.list_item,
                appListC) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
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


        if(adapterc.getCount()>0)
            gridViewC.setAdapter(adapterc);
        else
        {
            TextView txtc=(TextView)getActivity().findViewById(R.id.txtc);
            txtc.setVisibility(View.GONE);
            gridViewC.setVisibility(View.GONE);
        }


        ArrayAdapter<App> adapterd = new ArrayAdapter<App>(getActivity(), R.layout.list_item,
                appListD) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
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

        if(adapterd.getCount()>0)
            gridViewD.setAdapter(adapterd);
        else
        {
            TextView txtd=(TextView)getActivity().findViewById(R.id.txtd);
            txtd.setVisibility(View.GONE);
            gridViewD.setVisibility(View.GONE);
        }




        ArrayAdapter<App> adaptere = new ArrayAdapter<App>(getActivity(), R.layout.list_item,
                appListE) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
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

        if(adaptere.getCount()>0)
            gridViewE.setAdapter(adaptere);
        else
        {
            TextView txte=(TextView)getActivity().findViewById(R.id.txte);
            txte.setVisibility(View.GONE);
            gridViewE.setVisibility(View.GONE);
        }




        ArrayAdapter<App> adapterf = new ArrayAdapter<App>(getActivity(), R.layout.list_item,
                appListF) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
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

        if(adapterf.getCount()>0)
            gridViewF.setAdapter(adapterf);
        else
        {
            TextView txtf=(TextView)getActivity().findViewById(R.id.txtf);
            txtf.setVisibility(View.GONE);
            gridViewF.setVisibility(View.GONE);
        }


        ArrayAdapter<App> adapterg = new ArrayAdapter<App>(getActivity(), R.layout.list_item,
                appListG) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
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

        if(adapterg.getCount()>0)
            gridViewG.setAdapter(adapterg);
        else
        {
            TextView txtg=(TextView)getActivity().findViewById(R.id.txtg);
            txtg.setVisibility(View.GONE);
            gridViewG.setVisibility(View.GONE);
        }



        ArrayAdapter<App> adapterh = new ArrayAdapter<App>(getActivity(), R.layout.list_item,
                appListH) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
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

        if(adapterh.getCount()>0)
            gridViewH.setAdapter(adapterh);
        else
        {
            TextView txth=(TextView)getActivity().findViewById(R.id.txth);
            txth.setVisibility(View.GONE);
            gridViewH.setVisibility(View.GONE);
        }


        ArrayAdapter<App> adapteri = new ArrayAdapter<App>(getActivity(), R.layout.list_item,
                appListI) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
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

        if(adapteri.getCount()>0)
            gridViewI.setAdapter(adapteri);
        else
        {
            TextView txti=(TextView)getActivity().findViewById(R.id.txti);
            txti.setVisibility(View.GONE);
            gridViewI.setVisibility(View.GONE);
        }



        ArrayAdapter<App> adapterj = new ArrayAdapter<App>(getActivity(), R.layout.list_item,
                appListJ) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
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


        if(adapterj.getCount()>0)
            gridViewJ.setAdapter(adapterj);
        else
        {
            TextView txtj=(TextView)getActivity().findViewById(R.id.txtj);
            txtj.setVisibility(View.GONE);
            gridViewJ.setVisibility(View.GONE);
        }



        ArrayAdapter<App> adapterk = new ArrayAdapter<App>(getActivity(), R.layout.list_item,
                appListK) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
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

        if(adapterk.getCount()>0)
            gridViewK.setAdapter(adapterk);
        else
        {
            TextView txtk=(TextView)getActivity().findViewById(R.id.txtk);
            txtk.setVisibility(View.GONE);
            gridViewK.setVisibility(View.GONE);
        }



        ArrayAdapter<App> adapterl = new ArrayAdapter<App>(getActivity(), R.layout.list_item,
                appListL) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
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
        if(adapterl.getCount()>0)
            gridViewL.setAdapter(adapterl);
        else
        {
            TextView txtl=(TextView)getActivity().findViewById(R.id.txtl);
            txtl.setVisibility(View.GONE);
            gridViewL.setVisibility(View.GONE);
        }


        ArrayAdapter<App> adapterm = new ArrayAdapter<App>(getActivity(), R.layout.list_item,
                appListM) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
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

        if(adapterm.getCount()>0)
            gridViewM.setAdapter(adapterm);
        else
        {
            TextView txtm=(TextView)getActivity().findViewById(R.id.txtm);
            txtm.setVisibility(View.GONE);
            gridViewM.setVisibility(View.GONE);
        }



        ArrayAdapter<App> adaptern = new ArrayAdapter<App>(getActivity(), R.layout.list_item,
                appListN) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
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

        if(adaptern.getCount()>0)
            gridViewN.setAdapter(adaptern);
        else
        {
            TextView txtn=(TextView)getActivity().findViewById(R.id.txtn);
            txtn.setVisibility(View.GONE);
            gridViewN.setVisibility(View.GONE);
        }


        ArrayAdapter<App> adaptero = new ArrayAdapter<App>(getActivity(), R.layout.list_item,
                appListO) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
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

        if(adaptero.getCount()>0)
            gridViewO.setAdapter(adaptero);
        else
        {
            TextView txto=(TextView)getActivity().findViewById(R.id.txto);
            txto.setVisibility(View.GONE);
            gridViewO.setVisibility(View.GONE);
        }



        ArrayAdapter<App> adapterp = new ArrayAdapter<App>(getActivity(), R.layout.list_item,
                appListP) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
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

        if(adapterp.getCount()>0)
            gridViewP.setAdapter(adapterp);
        else
        {
            TextView txtp=(TextView)getActivity().findViewById(R.id.txtp);
            txtp.setVisibility(View.GONE);
            gridViewP.setVisibility(View.GONE);
        }





        ArrayAdapter<App> adapterq = new ArrayAdapter<App>(getActivity(), R.layout.list_item,
                appListQ) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
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

        if(adapterq.getCount()>0)
            gridViewQ.setAdapter(adapterq);
        else
        {
            TextView txtq=(TextView)getActivity().findViewById(R.id.txtq);
            txtq.setVisibility(View.GONE);
            gridViewQ.setVisibility(View.GONE);
        }




        ArrayAdapter<App> adapterr = new ArrayAdapter<App>(getActivity(), R.layout.list_item,
                appListR) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
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
        if(adapterr.getCount()>0)
            gridViewR.setAdapter(adapterr);
        else
        {
            TextView txtr=(TextView)getActivity().findViewById(R.id.txtr);
            txtr.setVisibility(View.GONE);
            gridViewR.setVisibility(View.GONE);
        }



        ArrayAdapter<App> adapters = new ArrayAdapter<App>(getActivity(), R.layout.list_item,
                appListS) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
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

        if(adapters.getCount()>0)
            gridViewS.setAdapter(adapters);
        else
        {
            TextView txts=(TextView)getActivity().findViewById(R.id.txts);
            txts.setVisibility(View.GONE);
            gridViewS.setVisibility(View.GONE);
        }




        ArrayAdapter<App> adaptert = new ArrayAdapter<App>(getActivity(), R.layout.list_item,
                appListT) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
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

        if(adaptert.getCount()>0)
            gridViewT.setAdapter(adaptert);
        else
        {
            TextView txtt=(TextView)getActivity().findViewById(R.id.txtt);
            txtt.setVisibility(View.GONE);
            gridViewT.setVisibility(View.GONE);
        }



        ArrayAdapter<App> adapteru = new ArrayAdapter<App>(getActivity(), R.layout.list_item,
                appListU) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
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

        if(adapteru.getCount()>0)
            gridViewU.setAdapter(adapteru);
        else
        {
            TextView txtu=(TextView)getActivity().findViewById(R.id.txtu);
            txtu.setVisibility(View.GONE);
            gridViewU.setVisibility(View.GONE);
        }



        ArrayAdapter<App> adapterv = new ArrayAdapter<App>(getActivity(), R.layout.list_item,
                appListV) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
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

        if(adapterv.getCount()>0)
            gridViewV.setAdapter(adapterv);
        else
        {
            TextView txtv=(TextView)getActivity().findViewById(R.id.txtv);
            txtv.setVisibility(View.GONE);
            gridViewV.setVisibility(View.GONE);
        }

//
//        ArrayAdapter<App> adapterV = new ArrayAdapter<App>(getActivity(), R.layout.list_item,
//                appListV) {
//
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                if (convertView == null) {
//                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
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




        ArrayAdapter<App> adapterx = new ArrayAdapter<App>(getActivity(), R.layout.list_item,
                appListX) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
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

        if(adapterx.getCount()>0)
            gridViewX.setAdapter(adapterx);
        else
        {
            TextView txtx=(TextView)getActivity().findViewById(R.id.txtx);
            txtx.setVisibility(View.GONE);
            gridViewX.setVisibility(View.GONE);
        }


        ArrayAdapter<App> adapterw = new ArrayAdapter<App>(getActivity(), R.layout.list_item,
                appListW) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
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

        if(adapterw.getCount()>0)
            gridViewW.setAdapter(adapterw);
        else
        {
            TextView txtw=(TextView)getActivity().findViewById(R.id.txtw);
            txtw.setVisibility(View.GONE);
            gridViewW.setVisibility(View.GONE);
        }



        ArrayAdapter<App> adaptery = new ArrayAdapter<App>(getActivity(), R.layout.list_item,
                appListY) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
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

        if(adaptery.getCount()>0)
            gridViewY.setAdapter(adaptery);
        else
        {
            TextView txty=(TextView)getActivity().findViewById(R.id.txty);
            txty.setVisibility(View.GONE);
            gridViewY.setVisibility(View.GONE);
        }




        ArrayAdapter<App> adapterz = new ArrayAdapter<App>(getActivity(), R.layout.list_item,
                appListZ) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item,
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

        if(adapterz.getCount()>0)
            gridViewZ.setAdapter(adapterz);
        else
        {
            TextView txtz=(TextView)getActivity().findViewById(R.id.txtz);
            txtz.setVisibility(View.GONE);
            gridViewZ.setVisibility(View.GONE);
        }



    }



    private  void    clickListeners(){


        final PackageManager manager = getActivity().getPackageManager();

        gridViewNum.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appListNum.get(arg2).packname
                        .toString());
                TabAppList.this.startActivity(i);
            }
        });



        gridViewA.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appListA.get(arg2).packname
                        .toString());
                TabAppList.this.startActivity(i);
            }
        });


        gridViewB.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appListB.get(arg2).packname
                        .toString());
                TabAppList.this.startActivity(i);
            }
        });

        gridViewC.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appListC.get(arg2).packname
                        .toString());
                TabAppList.this.startActivity(i);
            }
        });

        gridViewD.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appListD.get(arg2).packname
                        .toString());
                TabAppList.this.startActivity(i);
            }
        });

        gridViewE.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appListE.get(arg2).packname
                        .toString());
                TabAppList.this.startActivity(i);
            }
        });

        gridViewF.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appListF.get(arg2).packname
                        .toString());
                TabAppList.this.startActivity(i);
            }
        });

        gridViewG.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appListG.get(arg2).packname
                        .toString());
                TabAppList.this.startActivity(i);
            }
        });

        gridViewH.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appListH.get(arg2).packname
                        .toString());
                TabAppList.this.startActivity(i);
            }
        });

        gridViewI.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appListI.get(arg2).packname
                        .toString());
                TabAppList.this.startActivity(i);
            }
        });

        gridViewJ.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appListJ.get(arg2).packname
                        .toString());
                TabAppList.this.startActivity(i);
            }
        });

        gridViewK.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appListK.get(arg2).packname
                        .toString());
                TabAppList.this.startActivity(i);
            }
        });

        gridViewL.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appListL.get(arg2).packname
                        .toString());
                TabAppList.this.startActivity(i);
            }
        });

        gridViewM.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appListM.get(arg2).packname
                        .toString());
                TabAppList.this.startActivity(i);
            }
        });

        gridViewN.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appListN.get(arg2).packname
                        .toString());
                TabAppList.this.startActivity(i);
            }
        });

        gridViewO.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appListO.get(arg2).packname
                        .toString());
                TabAppList.this.startActivity(i);
            }
        });

        gridViewP.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appListP.get(arg2).packname
                        .toString());
                TabAppList.this.startActivity(i);
            }
        });

        gridViewQ.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appListQ.get(arg2).packname
                        .toString());
                TabAppList.this.startActivity(i);
            }
        });

        gridViewR.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appListR.get(arg2).packname
                        .toString());
                TabAppList.this.startActivity(i);
            }
        });

        gridViewS.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appListS.get(arg2).packname
                        .toString());
                TabAppList.this.startActivity(i);
            }
        });

        gridViewT.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appListT.get(arg2).packname
                        .toString());
                TabAppList.this.startActivity(i);
            }
        });

        gridViewU.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appListU.get(arg2).packname
                        .toString());
                TabAppList.this.startActivity(i);
            }
        });

        gridViewV.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appListV.get(arg2).packname
                        .toString());
                TabAppList.this.startActivity(i);
            }
        });

        gridViewW.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appListW.get(arg2).packname
                        .toString());
                TabAppList.this.startActivity(i);
            }
        });

        gridViewX.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appListX.get(arg2).packname
                        .toString());
                TabAppList.this.startActivity(i);
            }
        });

        gridViewY.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appListY.get(arg2).packname
                        .toString());
                TabAppList.this.startActivity(i);
            }
        });

        gridViewZ.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i = manager.getLaunchIntentForPackage(appListZ.get(arg2).packname
                        .toString());
                TabAppList.this.startActivity(i);
            }
        });














    }














    class ApplicationsIntentReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {


            init();
//            getApps();
//            showApps();
            //showApps();
        }

}





}