package com.wish.android.shaky;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import java.util.ArrayList;

public class EmailShakeDelegate extends ShakeDelegate {
    private int sensitivityLevel = 23;
    private String[] to;

    public EmailShakeDelegate(String[] strArr) {
        this.to = strArr;
    }

    public final void submit(Activity activity, Result result) {
        activity.startActivity(onSubmit(result));
    }

    public int getSensitivityLevel() {
        return this.sensitivityLevel;
    }

    public EmailShakeDelegate setSensitivityLevel(int i) {
        this.sensitivityLevel = i;
        return this;
    }

    public Intent onSubmit(Result result) {
        return createEmailIntent(this.to, result.getTitle(), result.getMessage(), result.getAttachments());
    }

    private Intent createEmailIntent(String[] strArr, String str, String str2, ArrayList<Uri> arrayList) {
        Intent intent = new Intent();
        intent.setFlags(335544320);
        intent.setAction("android.intent.action.SEND");
        intent.setType("plain/text");
        intent.putExtra("android.intent.extra.EMAIL", strArr);
        intent.putExtra("android.intent.extra.SUBJECT", str);
        intent.putExtra("android.intent.extra.TEXT", str2);
        if (arrayList != null) {
            if (arrayList.size() == 1) {
                intent.putExtra("android.intent.extra.STREAM", (Parcelable) arrayList.get(0));
            } else {
                intent.setAction("android.intent.action.SEND_MULTIPLE");
                intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
            }
        }
        return intent;
    }
}
