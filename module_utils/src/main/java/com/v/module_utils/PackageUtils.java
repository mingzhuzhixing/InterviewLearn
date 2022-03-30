/**
 * Copyright (c) Tapas Mobile.  All Rights Reserved.
 *
 * @author DaiHui
 * @version 1.0
 */

package com.v.module_utils;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 *
 */
public class PackageUtils {
    static int sMyVersionCode = -1;
    static String sMyversionName = null;
    static PackageManager sPackageManager = null;
    static String sSelfBuildTime = null;

    private static void ensureService() {
        if (sPackageManager == null) {
            sPackageManager = Utilities.getApplicationContext().getPackageManager();
        }
    }

    public static int getVersionCode(String pkgName) {
        ensureService();
        try {
            PackageInfo info = sPackageManager.getPackageInfo(pkgName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String getVersionName(String pkgName) {
        ensureService();
        try {
            PackageInfo info = sPackageManager.getPackageInfo(pkgName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static long guessBuildTime(String pkgName) {
        ensureService();
        ZipFile zf = null;
        try {
            ApplicationInfo ai = sPackageManager.getApplicationInfo(pkgName, 0);
            zf = new ZipFile(ai.sourceDir);
            ZipEntry ze = zf.getEntry("classes.dex");
            return ze.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (zf != null) {
                try {
                    zf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

    public static boolean isLegacyDevice() {
        return Build.VERSION_CODES.GINGERBREAD_MR1 >= Build.VERSION.SDK_INT;
    }

    public static boolean installPackage(Context context, String path) {
        return installPackage(context, path, null);
    }

    public static boolean installPackage(Context context, String path, String installerPackage) {
        try {
            Intent intent = new Intent(Intent.ACTION_INSTALL_PACKAGE);
            File file = new File(path);
            if (!file.exists())
                return false;
            Uri uri = Uri.fromFile(file);
            String type = "application/vnd.android.package-archive";
            intent.setDataAndType(uri, type);
            if (installerPackage != null) {
                intent.putExtra(Intent.EXTRA_INSTALLER_PACKAGE_NAME, installerPackage);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isPkgInstalled(Context context, String packageName) {
        if (packageName == null || "".equals(packageName)) {
            return false;
        }
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName, 0);
            return info != null;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static boolean isActivityInstalled(Context context, String packageName, String className) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName, className));
        List<ResolveInfo> activityList = context.getPackageManager().queryIntentActivities(intent, PackageManager.GET_META_DATA);
        return activityList.size() > 0;
    }

    public static boolean isReceiverInstalled(Context context, String packageName, String className) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName, className));
        List<ResolveInfo> broadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, PackageManager.GET_META_DATA);
        return broadcastReceivers.size() > 0;
    }

    public static boolean isPkgInstalledByAction(Context context, String pkgName, String action) {
        try {
            Intent intent = new Intent(action);
            if (pkgName != null) {
                intent.setPackage(pkgName);
            }
            List<ResolveInfo> apps = context.getPackageManager().queryIntentActivities(intent, 0);
            return apps != null && apps.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isPkgInstalledByIntent(Context context, Intent intent) {
        try {
            List<ResolveInfo> apps = context.getPackageManager().queryIntentActivities(intent, 0);
            return apps != null && apps.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isSystemPkg(Context context, String pkg) {
        if (pkg == null || "".equals(pkg)) {
            return false;
        }
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(pkg, 0);
            if (info != null) {
                List<PackageInfo> apps = context.getPackageManager().getInstalledPackages(0);
                return apps != null && apps.size() > 0;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }


    //获取当前apk包信息
    public static PackageInfo getPackageInfo(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            return pm.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断当前应用程序处于前台还是后台
     *
     * @return true 在后台 false 在前台
     */
    public static boolean isApplicationBroughtToBackground(final Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
            if (appProcesses == null || appProcesses.isEmpty()) {
                return false;
            }
            for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
                if (TextUtils.equals(appProcess.processName, context.getPackageName())) {
                    boolean isBackground = (appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                            && appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE);
                    boolean isLockedState = keyguardManager.inKeyguardRestrictedInputMode();
                    return isBackground || isLockedState;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
