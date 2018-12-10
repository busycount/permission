# permission
&lt;Android> Permission


## Provide
Android danger permission request
> only support android.support.v4.app.FragmentActivity,android.support.v4.app.Fragment


First
```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <uses-permission android:name="android.permission.READ_CONTACTS" />
    <uses-permission android:name="android.permission.WRITE_CONTACTS" />
</manifest>
```

Second
```java
PermissionUtil.request(context,
                       new String[]{Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS},
                       new PermissionListener() {
                           @Override
                           public void onGranted() {
                           }

                           @Override
                           public void onDenied(String[] denied) {
                           }
                       });
```


## Implementation
Step 1. Add the JitPack repository to your build file Add it in your root build.gradle at the end of repositories:
```xml
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency, [Lastest release](https://github.com/busycount/permission/releases)
```xml
dependencies {
    implementation 'com.github.busycount:permission:lastest'
}
```