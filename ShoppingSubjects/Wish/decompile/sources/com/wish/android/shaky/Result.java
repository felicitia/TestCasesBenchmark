package com.wish.android.shaky;

import android.net.Uri;
import android.os.Bundle;
import java.util.ArrayList;

public class Result {
    private static final String ATTACHMENTS;
    private static final String MESSAGE;
    private static final String PREFIX = "com.wish.android.shaky.Result";
    private static final String SCREENSHOT_URI;
    private static final String TITLE;
    private ArrayList<Uri> attachments;
    private final Bundle data;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX);
        sb.append(".message");
        MESSAGE = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(PREFIX);
        sb2.append(".title");
        TITLE = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(PREFIX);
        sb3.append(".screenshotUri");
        SCREENSHOT_URI = sb3.toString();
        StringBuilder sb4 = new StringBuilder();
        sb4.append(PREFIX);
        sb4.append(".attachments");
        ATTACHMENTS = sb4.toString();
    }

    Result(Bundle bundle) {
        this.data = bundle;
    }

    Result() {
        this.data = new Bundle();
    }

    public String getMessage() {
        return this.data.getString(MESSAGE);
    }

    public void setMessage(String str) {
        this.data.putString(MESSAGE, str);
    }

    public String getTitle() {
        return this.data.getString(TITLE);
    }

    public void setTitle(String str) {
        this.data.putString(TITLE, str);
    }

    public Uri getScreenshotUri() {
        return (Uri) this.data.getParcelable(SCREENSHOT_URI);
    }

    /* access modifiers changed from: 0000 */
    public void setScreenshotUri(Uri uri) {
        if (getScreenshotUri() == null && uri != null) {
            getAttachments().add(uri);
        }
        this.data.putParcelable(SCREENSHOT_URI, uri);
    }

    public ArrayList<Uri> getAttachments() {
        if (this.attachments == null) {
            ArrayList<Uri> parcelableArrayList = this.data.getParcelableArrayList(ATTACHMENTS);
            if (parcelableArrayList == null) {
                parcelableArrayList = new ArrayList<>();
            }
            this.attachments = parcelableArrayList;
        }
        return this.attachments;
    }

    /* access modifiers changed from: 0000 */
    public void setAttachments(ArrayList<Uri> arrayList) {
        this.attachments = arrayList;
    }

    public Bundle getData() {
        if (this.attachments != null) {
            this.data.putParcelableArrayList(ATTACHMENTS, this.attachments);
        }
        return this.data;
    }
}
