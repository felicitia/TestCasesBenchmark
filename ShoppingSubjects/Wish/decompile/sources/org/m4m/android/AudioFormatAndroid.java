package org.m4m.android;

import android.media.MediaFormat;
import org.m4m.AudioFormat;

public class AudioFormatAndroid extends AudioFormat {
    private MediaFormat mediaFormat;

    AudioFormatAndroid(MediaFormat mediaFormat2) {
        this.mediaFormat = mediaFormat2;
        setAudioCodec(mediaFormat2.getString("mime"));
    }

    public AudioFormatAndroid(String str, int i, int i2) {
        this.mediaFormat = MediaFormat.createAudioFormat(str, i, i2);
        setAudioCodec(str);
    }

    public MediaFormat getNativeFormat() {
        return this.mediaFormat;
    }

    public void setInteger(String str, int i) {
        this.mediaFormat.setInteger(str, i);
    }

    public int getInteger(String str) {
        return this.mediaFormat.getInteger(str);
    }

    /* access modifiers changed from: protected */
    public long getLong(String str) {
        return this.mediaFormat.getLong(str);
    }

    /* access modifiers changed from: protected */
    public String getString(String str) {
        return this.mediaFormat.getString(str);
    }
}
