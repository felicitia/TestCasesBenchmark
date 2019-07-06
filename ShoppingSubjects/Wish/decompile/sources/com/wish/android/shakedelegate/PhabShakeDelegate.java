package com.wish.android.shakedelegate;

import android.app.Activity;
import android.text.TextUtils;
import com.wish.android.shaky.Result;
import com.wish.android.shaky.ShakeDelegate;

public class PhabShakeDelegate extends ShakeDelegateWrapper {
    public PhabShakeDelegate(ShakeDelegate shakeDelegate) {
        super(shakeDelegate);
    }

    public void submit(Activity activity, Result result) {
        String title = result.getTitle();
        String message = result.getMessage();
        String str = "";
        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(message)) {
            String substring = message.substring(0, Math.min(20, message.length()));
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append(title);
            sb.append("] ");
            sb.append(substring);
            str = sb.toString();
        }
        String str2 = "";
        if (!TextUtils.isEmpty(message)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(message);
            sb2.append("\n\n");
            String sb3 = sb2.toString();
            StringBuilder sb4 = new StringBuilder();
            sb4.append(sb3);
            sb4.append("!project #android_feedback\n");
            String sb5 = sb4.toString();
            StringBuilder sb6 = new StringBuilder();
            sb6.append(sb5);
            sb6.append("!priority triage\n");
            str2 = sb6.toString();
        }
        result.setTitle(str);
        result.setMessage(str2);
        super.submit(activity, result);
    }
}
