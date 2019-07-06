package com.contextlogic.wish.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.view.View;
import com.contextlogic.wish.application.WishApplication;
import com.crashlytics.android.Crashlytics;
import java.io.File;
import java.io.FileOutputStream;

public class ScreenshotUtil {
    public static String getScreenshotDirectoryPath() {
        StringBuilder sb = new StringBuilder();
        sb.append(WishApplication.getInstance().getFilesDir().getAbsolutePath());
        sb.append("/");
        sb.append("screenshots");
        return sb.toString();
    }

    public static void cleanupScreenshotDirectory() {
        try {
            File file = new File(getScreenshotDirectoryPath());
            if (file.exists()) {
                if (file.isDirectory()) {
                    for (File delete : file.listFiles()) {
                        delete.delete();
                    }
                }
                file.delete();
            }
        } catch (Throwable unused) {
        }
    }

    public static Uri takeScreenshot(Activity activity) {
        try {
            File file = new File(getScreenshotDirectoryPath());
            file.mkdirs();
            View rootView = activity.getWindow().getDecorView().getRootView();
            rootView.setDrawingCacheEnabled(true);
            Bitmap createBitmap = Bitmap.createBitmap(rootView.getDrawingCache());
            rootView.setDrawingCacheEnabled(false);
            StringBuilder sb = new StringBuilder();
            sb.append(file.getAbsolutePath());
            sb.append("/");
            sb.append(String.format("screenshot%d.jpg", new Object[]{Long.valueOf(System.currentTimeMillis())}));
            File file2 = new File(sb.toString());
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            createBitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return Uri.fromFile(file2);
        } catch (Throwable th) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("screenshots");
            sb2.append(th.getMessage());
            sb2.append(th.toString());
            Crashlytics.logException(new Exception(sb2.toString()));
            return null;
        }
    }

    public static String getFileProviderAuthority() {
        return String.format("%s.fileprovider", new Object[]{WishApplication.getInstance().getPackageName()});
    }

    public static Uri getPublicScreenshotUri(Uri uri) {
        return FileProvider.getUriForFile(WishApplication.getInstance(), getFileProviderAuthority(), new File(uri.getPath()));
    }
}
