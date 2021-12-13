    package cn.zhuwl.test.Util;

    import android.app.Activity;
    import android.content.Context;
    import android.provider.Settings;
    import android.telephony.TelephonyManager;
    import android.util.Log;

    public class DeviceUtil {


        public static void getImieStatus(Activity context) {
            try {
                TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);

                String deviceId = tm.getDeviceId();

                Log.e("DeviceUtil ", "deviceId:"+deviceId);
            } catch (Exception e){
                Log.i("DeviceUtil","e:"+e);
            }

    }

    public static void getAndroidId(Activity context){
        String androidId = Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);
        String deviceId = Settings.System.getString(context.getContentResolver(), "DEVICE_ID");

        Log.e("DeviceUtil", "androidId:" + androidId);
        Log.e("DeviceUtil", "deviceId:" + deviceId);
    }

}