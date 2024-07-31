package com.wearesupermagical.capacitortelemetry;

import static android.content.Context.BATTERY_SERVICE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.provider.Settings.Secure;
import android.os.BatteryManager;
import android.os.Build;

import java.util.HashMap;
import java.util.Map;

public class Telemetry {

    public Map<String, Object> getData(Context context) {
        return new HashMap<String, Object>() {{
            put("deviceInfo", new HashMap<String, Object>() {{
                put("deviceName", deviceName());
                put("deviceUuid", deviceUuid(context));
                put("deviceIsSecure", deviceIsSecure());
                put("deviceModel", deviceModel());
                put("deviceBatteryLevel", deviceBatteryLevel(context));
                put("deviceBatteryState", deviceBatteryState(context));
                put("deviceOs", deviceOs());
                put("deviceOsVersion", deviceOsVersion());
                put("deviceScreenBrightness", deviceScreenBrightness());
                put("deviceAudioLevel", deviceAudioLevel());
                put("deviceIsGuidedAccessEnabled", deviceIsGuidedAccessEnabled());
            }});
            put("appInfo", new HashMap<String, Object>() {{
                put("appVersion", appVersion(context));
                put("appBuild", appBuild(context));
                put("appBundleId", appBundleId(context));
            }});
            put("networkInfo", new HashMap<String, Object>() {{
                put("networkConnection", networkConnection(context));
            }});
        }};
    }

    private String deviceName() {
        return Build.MODEL;
    }

    @SuppressLint("HardwareIds")
    private String deviceUuid(Context context) {
        return Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
    }

    private Boolean deviceIsSecure() {
        return false;
    }

    private String deviceModel() {
        return Build.MANUFACTURER;
    }

    private Float deviceBatteryLevel(Context context) {
        BatteryManager bm = (BatteryManager) context.getSystemService(BATTERY_SERVICE);
        return bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY) / 100f;

    }

    private String deviceBatteryState(Context context) {
        IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        var batteryStatus = context.registerReceiver(null, iFilter);
        if (batteryStatus == null) {
            return "Unknown";
        }
        return switch (batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1)) {
            case BatteryManager.BATTERY_STATUS_FULL -> "Full";
            case BatteryManager.BATTERY_STATUS_CHARGING -> "Charging";
            default -> "Discharging";
        };
    }

    private String deviceOs() {
        return "Android";
    }

    private String deviceOsVersion() {
        return String.valueOf(Build.VERSION.SDK_INT);
    }

    private Float deviceScreenBrightness() {
        return 0f;
    }

    private Float deviceAudioLevel() {
        return 0f;
    }

    private Boolean deviceIsGuidedAccessEnabled() {
        return false;
    }

    private String appVersion(Context context) {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "Unknown";
        }
    }

    private String appBuild(Context context) {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return String.valueOf(pInfo.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            return "Unknown";
        }
    }

    private String appBundleId(Context context) {
        return context.getPackageName();
    }

    private String networkConnection(Context context) {
        String permission = android.Manifest.permission.ACCESS_NETWORK_STATE;
        if (context.checkCallingOrSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            return "Unknown";
        }

        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.M) {
            return "Unknown";
        }

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkCapabilities networkCapabilities = manager.getNetworkCapabilities(manager.getActiveNetwork());

        if (networkCapabilities == null) {
            return "Unavailable";
        }

        if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
            return "Ethernet";
        }
        if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
            return "Wifi";
        }
        if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
            return "Cellular";
        }

        return "Unavailable";
    }
}
