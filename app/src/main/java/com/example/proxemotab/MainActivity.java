package com.example.proxemotab;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proxemotab.ui.main.SectionsPagerAdapter;

import java.io.File;

//public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{
public class MainActivity extends AppCompatActivity{
    // Storage Permissions
    private static final int STORAGE_PERMISSION_CODE = 100;
    private static final int VIBRATE_PERMISSION_CODE = 101;

    private CoordinatorLayout coordinatorLayout;

    //private TabLayout tabLayout;
    //private ViewPager viewPager;

    /**
     * TODO: create global variables that safe the usernames throughout the session and can be accessed from each tab
     * @param savedInstanceState
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);
        checkPermission(Manifest.permission.VIBRATE, VIBRATE_PERMISSION_CODE);
/*
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("ProxemoTab2"));
        tabLayout.addTab(tabLayout.newTab().setText("Settings"));
        tabLayout.addTab(tabLayout.newTab().setText("ProxemoTab1"));
/*        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
*/
        ViewPager viewPager = findViewById(R.id.view_pager);

        //alternativ zu der Zeile darunter
        //Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());


        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabLayout);
        tabs.setupWithViewPager(viewPager);

        //tabLayout.addOnTabSelectedListener();

    }

    //übernommen von https://www.geeksforgeeks.org/android-how-to-request-permissions-in-android-application/
    public void checkPermission(String permission, int requestCode) {
        // Check if we have write permission
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
        }

    }


    public void export (View view){

        try{
        Context context = getApplicationContext();
        File fileLocation = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "ProxemoTabLog.csv");
        Uri path = FileProvider.getUriForFile(context, "com.example.proxemotab.fileprovider", fileLocation);
        Intent fileIntent = new Intent(Intent.ACTION_SEND);
        fileIntent.setType("text/csv");
        fileIntent.putExtra(Intent.EXTRA_SUBJECT, "ProxemoData");
        fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        fileIntent.putExtra(Intent.EXTRA_STREAM, path);
        startActivity(Intent.createChooser(fileIntent, "Send ProxemoData"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

 */
}