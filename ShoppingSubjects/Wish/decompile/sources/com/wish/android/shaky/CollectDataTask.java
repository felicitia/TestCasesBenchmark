package com.wish.android.shaky;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import java.io.File;

class CollectDataTask extends AsyncTask<Bitmap, Void, Result> {
    private static final String TAG = "CollectDataTask";
    private final Activity activity;
    private final Callback callback;
    private final ShakeDelegate delegate;

    interface Callback {
        void onDataReady(Result result);
    }

    CollectDataTask(Activity activity2, ShakeDelegate shakeDelegate, Callback callback2) {
        this.activity = activity2;
        this.delegate = shakeDelegate;
        this.callback = callback2;
    }

    /* access modifiers changed from: protected */
    public Result doInBackground(Bitmap... bitmapArr) {
        File[] listFiles;
        String screenshotDirectoryRoot = getScreenshotDirectoryRoot(this.activity);
        Uri uri = null;
        if (screenshotDirectoryRoot == null) {
            return null;
        }
        File file = new File(screenshotDirectoryRoot);
        if (file.exists()) {
            for (File file2 : file.listFiles()) {
                if (!file2.delete()) {
                    String str = TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Could not delete old screenshot:");
                    sb.append(file2);
                    Log.e(str, sb.toString());
                }
            }
        }
        Bitmap bitmap = (bitmapArr == null || bitmapArr.length == 0) ? null : bitmapArr[0];
        if (bitmap != null) {
            uri = Uri.fromFile(Utils.writeBitmapToDirectory(bitmap, file));
        }
        Result result = new Result();
        result.setScreenshotUri(uri);
        this.delegate.collectData(this.activity, result);
        return result;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Result result) {
        super.onPostExecute(result);
        this.callback.onDataReady(result);
    }

    private static String getScreenshotDirectoryRoot(Context context) {
        File filesDir = context.getFilesDir();
        if (filesDir == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(filesDir.getAbsolutePath());
        sb.append("/screenshots");
        return sb.toString();
    }
}
