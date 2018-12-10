package com.busycount.permission;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * PermissionFragment
 * <p>
 * 2018/11/30 | Count.C | Created
 */
public class PermissionFragment extends Fragment {
    public static final String TAG = "PermissionFragment";

    private static final int REQUEST_CODE = 0x6c;
    private String[] permissions;
    private PermissionListener permissionListener;

    public PermissionFragment setPermissions(String[] permissions) {
        this.permissions = permissions;
        return this;
    }

    public PermissionFragment setPermissionListener(PermissionListener permissionListener) {
        this.permissionListener = permissionListener;
        return this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermission();
    }

    public void requestPermission() {
        if (permissions == null || permissions.length == 0 || permissionListener == null) {
            return;
        }
        List<String> list = new ArrayList<>();
        for (String str : permissions) {
            if (!checkPermission(str)) {
                list.add(str);
            }
        }
        if (list.isEmpty()) {
            onGranted();
        } else {
            String[] strArr = new String[list.size()];
            requestPermissions(list.toArray(strArr), REQUEST_CODE);
        }
    }

    private boolean checkPermission(String permission) {
        if (getContext() == null) {
            return false;
        } else {
            return ContextCompat.checkSelfPermission(getContext(), permission) == PackageManager.PERMISSION_GRANTED;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length <= 0) {
                return;
            }
            List<String> deniedList = new ArrayList<>();
            for (int i = 0, length = grantResults.length; i < length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    deniedList.add(permissions[i]);
                }
            }
            if (deniedList.isEmpty()) {
                onGranted();
            } else {
                onDenied(deniedList);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        permissionListener = null;
    }

    private void onGranted() {
        if (permissionListener != null) {
            permissionListener.onGranted();
        }
    }

    private void onDenied(List<String> deniedList) {
        if (permissionListener != null) {
            String[] str = new String[deniedList.size()];
            permissionListener.onDenied(deniedList.toArray(str));
        }
    }

}
