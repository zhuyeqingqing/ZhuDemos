package cn.zhuwl.test.MediaUtil;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;

public class MediaImageUtil {
    /**
     * 根据图片文件获取图片在媒体数据库的id和content开头的URI
     * @param context
     * @param imageFile
     * @return
     */
    public static Uri getImageContentUri(Context context, java.io.File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[] { MediaStore.Images.Media._ID }, MediaStore.Images.Media.DATA + "=? ",
                new String[] { filePath }, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    /**
     * 根据图片文件，把图片信息存进媒体数据库中
     * @param context
     * @param file
     */
    public void saveMedia(Context context, File file){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // 判断是否有SD卡,优先使用SD卡存储,当没有SD卡时使用手机存储
            //Logger.i("pipi", file.getName());
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, file.getName());
            contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/");
            Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            //Logger.i("hehe","uri:"+uri.toString());
        }
    }

    /**
     * 查询媒体数据库中所有图片信息
     * @param context
     */
    public void getImagesInfo(Context context) {
        ContentResolver resolver = context.getContentResolver();
        String[] projection={
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.WIDTH,
                MediaStore.Images.Media.HEIGHT,
                MediaStore.Images.Media.SIZE
        };
        Cursor c = resolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);//这里就像查询数据库一样就可以了
        while(c.moveToNext()){
            int id=c.getInt(c.getColumnIndex(MediaStore.Images.Media._ID));
            String path=c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
            double width=c.getDouble(c.getColumnIndex(MediaStore.Images.Media.WIDTH));
            double height=c.getDouble(c.getColumnIndex(MediaStore.Images.Media.HEIGHT));
            double size=c.getDouble(c.getColumnIndex(MediaStore.Images.Media.SIZE));
            StringBuilder sb=new StringBuilder();
            sb.append("id=").append(id)
                    .append(",path=").append(path)
                    .append(",width=").append(width)
                    .append(",height=").append(height)
                    .append(",size=").append(size);
            Log.i("main",sb.toString());
        }

    }
}
