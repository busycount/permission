package com.busycount.permission.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.busycount.permission.PermissionListener;

import java.util.ArrayList;
import java.util.List;


public class Main2Activity extends AppCompatActivity implements PermissionListener {

    public static void start(Context context) {
        Intent starter = new Intent(context, Main2Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        RvAdapter adapter = new RvAdapter();
        adapter.setData(list);
        recyclerView.setAdapter(adapter);
    }


    public void onNew(View view) {
        Main3Activity.start(this);
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
