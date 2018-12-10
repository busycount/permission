package com.busycount.permission.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Main4Activity
 * <p>
 * 2018/12/10 | Count.C | Created
 */
public class Main4Activity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, Main4Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }
}
