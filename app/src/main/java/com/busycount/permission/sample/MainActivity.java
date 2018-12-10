package com.busycount.permission.sample;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.busycount.permission.PermissionListener;
import com.busycount.permission.PermissionUtil;


public class MainActivity extends AppCompatActivity implements PermissionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onRequest1(View view) {
        String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        PermissionUtil.request(this, permissions, this);
    }


    public void onRequest2(View view) {
        String[] permissions = new String[]{"ab", Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        PermissionUtil.request(this, permissions, this);
    }

    public void onNew(View view) {
        Main2Activity.start(this);
    }

    @Override
    public void onGranted() {
        Toast.makeText(this, "onGranted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDenied(String[] denied) {
        StringBuilder builder = new StringBuilder();
        for (String str : denied) {
            builder.append(str).append(" , ");
        }
        Log.d("console", "onDenied: " + builder.toString());
        Toast.makeText(this, "onDenied " + builder.toString(), Toast.LENGTH_SHORT).show();
    }

}
