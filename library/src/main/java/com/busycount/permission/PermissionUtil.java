package com.busycount.permission;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * PermissionUtil
 * <p>
 * Danger Permission :
 * group:android.permission-group.CALENDAR
 * android.Manifest.permission.READ_CALENDAR
 * android.Manifest.permission.WRITE_CALENDAR
 * <p>
 * group:android.permission-group.CAMERA
 * android.Manifest.permission.CAMERA
 * <p>
 * group:android.permission-group.CONTACTS
 * android.Manifest.permission.WRITE_CONTACTS
 * android.Manifest.permission.READ_CONTACTS
 * android.Manifest.permission.GET_ACCOUNTS
 * <p>
 * android.permission-group.MICROPHONE
 * android.Manifest.permission.RECORD_AUDIO
 * <p>
 * group:android.permission-group.LOCATION
 * android.Manifest.permission.ACCESS_FINE_LOCATION
 * android.Manifest.permission.ACCESS_COARSE_LOCATION
 * <p>
 * group:android.permission-group.PHONE
 * android.Manifest.permission.READ_CALL_LOG
 * android.Manifest.permission.WRITE_CALL_LOG
 * android.Manifest.permission.READ_PHONE_STATE
 * android.Manifest.permission.CALL_PHONE
 * android.Manifest.permission.USE_SIP
 * android.Manifest.permission.PROCESS_OUTGOING_CALLS
 * <p>
 * group:android.permission-group.SENSORS
 * android.Manifest.permission.BODY_SENSORS
 * <p>
 * android.permission-group.SMS
 * android.Manifest.permission.READ_SMS
 * android.Manifest.permission.SEND_SMS
 * android.Manifest.permission.RECEIVE_WAP_PUSH
 * android.Manifest.permission.RECEIVE_MMS
 * android.Manifest.permission.RECEIVE_SMS
 * <p>
 * android.permission-group.STORAGE
 * android.Manifest.permission.READ_EXTERNAL_STORAGE
 * android.Manifest.permission.WRITE_EXTERNAL_STORAGE
 * <p>
 * 2018/11/30 | Count.C | Created
 */
public class PermissionUtil {

    public static void request(@NonNull Context context, @NonNull String[] permissions, @NonNull PermissionListener listener) {
        FragmentActivity activity = null;
        while (context instanceof ContextWrapper) {
            if (context instanceof FragmentActivity) {
                activity = (FragmentActivity) context;
                break;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (activity != null) {
            request(activity, permissions, listener);
        }
    }

    public static void request(@NonNull Fragment fragment, @NonNull String[] permissions, @NonNull PermissionListener listener) {
        FragmentActivity activity = fragment.getActivity();
        if (activity != null) {
            request(activity, permissions, listener);
        }
    }


    public static void request(@NonNull FragmentActivity activity, @NonNull String[] permissions, @NonNull PermissionListener listener) {
        if (permissions.length == 0) {
            listener.onGranted();
            return;
        }
        List<String> unGranted = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                unGranted.add(permission);
            }
        }
        if (unGranted.isEmpty()) {
            listener.onGranted();
        } else {
            String[] unGrantedPermissions = new String[unGranted.size()];
            requestFragmentManager(activity.getSupportFragmentManager(), unGranted.toArray(unGrantedPermissions), listener);
        }
    }


    private static void requestFragmentManager(@NonNull FragmentManager fm, @NonNull String[] permissions, @NonNull PermissionListener listener) {
        PermissionFragment fragment = (PermissionFragment) fm.findFragmentByTag(PermissionFragment.TAG);
        if (fragment == null) {
            fragment = new PermissionFragment();
            fragment.setPermissions(permissions).setPermissionListener(listener);
            fm.beginTransaction().add(fragment, PermissionFragment.TAG).commitAllowingStateLoss();
        } else {
            fragment.setPermissions(permissions).setPermissionListener(listener).requestPermission();
        }
    }

}
