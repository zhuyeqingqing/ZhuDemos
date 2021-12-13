package cn.zhuwl.test.Util;


import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class DeviceHelper {
    private static final String TAG = "DEVICEHelper";
    private static final String KEY_DEVICE_ID = "DEVICE_ID";
    private static String mDeviceIdPath = null;
    private static final String DEVICE_NAME = ".shareit_device_ids";
    private static String mDeviceId;
    private static String mMacAddress = null;
    private static String mAndroidId = null;
    private static String mImei = null;
    private static String mStorageCID = null;
    private static String mBuildSN = null;
    private static String SOC_HOST = "mmc_host";
    private static String SOC_SERIAL_PATH = "/mmc0/mmc0:0001/cid";
    private static String GAID = null;

    public DeviceHelper() {
    }

    public static String getDeviceId(Context ctx) {
        SharedPreferences sharedPreferences = ctx.getApplicationContext().getSharedPreferences("Settings", 0);
        String devId = sharedPreferences.getString("DEVICE_ID","");
        if (TextUtils.isEmpty(mDeviceId)) {
            mDeviceId = getOrCreateDeviceId(ctx.getApplicationContext());
        }

        return mDeviceId;
    }

    public static String getOrCreateDeviceId(Context ctx) {
        Settings settings = new Settings(ctx);
        String id = settings.get("DEVICE_ID");
        return id;
    }
        /*if (TextUtils.isEmpty(id)) {
            id = getIdFromFile("DEVICE_ID");
        }

        if (!TextUtils.isEmpty(id) && !isBadMacId(id) && !isBadAndroid(id)) {
            return id;
        } else {
            DeviceHelper.IDType type = DeviceHelper.IDType.MAC;

            try {
                id = getMacAddress(ctx);
                if (TextUtils.isEmpty(id)) {
                    type = DeviceHelper.IDType.ANDROID;
                    id = getAndroidID(ctx);
                    if (isBadAndroid(id)) {
                        id = null;
                    }
                }

                if (TextUtils.isEmpty(id)) {
                    type = DeviceHelper.IDType.UUID;
                    id = getUUID();
                }
            } catch (Exception var5) {
                type = DeviceHelper.IDType.UUID;
                id = getUUID();
            }

            id = type.getTag() + "." + id;
            settings.set("DEVICE_ID", id);
            putIdToFile("DEVICE_ID", id);
            return id;
        }*/
    //}


}
