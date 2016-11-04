package com.leadweblife.leadlauncher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Admin on 10-04-2016.
 */
public class LauncherMainActivity extends Activity{

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launchermain);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(LauncherMainActivity.this,AppsListActivity.class);
//                startActivity(intent);
//                finishscreen();
//
//
//            }
//        });


        ImageButton imageButton;
        imageButton=(ImageButton)findViewById(R.id.imageButton);
      imageButton.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(LauncherMainActivity.this,AppsListActivity.class);
                startActivity(intent);
                finishscreen();
          }
      });
    }

    private void finishscreen(){
        this.finish();
    }
}
