package org.m4m.domain;

public abstract class MediaFormat {
    /* access modifiers changed from: protected */
    public abstract int getInteger(String str);

    /* access modifiers changed from: protected */
    public abstract long getLong(String str);

    /* access modifiers changed from: protected */
    public abstract String getString(String str);

    /* access modifiers changed from: protected */
    public abstract void setInteger(String str, int i);

    public String getMimeType() {
        return getString("mime");
    }

    public long getDuration() {
        return getLong("durationUs");
    }
}
