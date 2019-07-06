package com.wish.android.shakedelegate;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import com.wish.android.shaky.Result;
import com.wish.android.shaky.ShakeDelegate;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class LogCatShakeDelegateWrapper extends ShakeDelegateWrapper {
    private static final String TAG = "LogCatShakeDelegateWrapper";

    public LogCatShakeDelegateWrapper(ShakeDelegate shakeDelegate) {
        super(shakeDelegate);
    }

    public void collectData(Activity activity, Result result) {
        super.collectData(activity, result);
        result.getAttachments().add(Uri.fromFile(FileHelper.writeStringToFile(new File(activity.getFilesDir(), "logcat.txt"), getDeviceLog())));
    }

    private String getDeviceLog() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("logcat -d").getInputStream()));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return sb.toString();
                }
                sb.append(readLine);
                sb.append("\n");
            }
        } catch (IOException e) {
            String str = TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Failed to read device log. ");
            sb2.append(e.getCause());
            Log.e(str, sb2.toString());
            return null;
        }
    }
}
