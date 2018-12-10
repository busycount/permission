package com.busycount.permission;

/**
 * PermissionListener
 * <p>
 * 2018/11/30 | Count.C | Created
 */
public interface PermissionListener {

    void onGranted();

    void onDenied(String[] denied);
}
