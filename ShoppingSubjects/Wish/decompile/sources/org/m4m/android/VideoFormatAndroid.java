package org.m4m.android;

import android.media.MediaFormat;
import org.m4m.VideoFormat;

public class VideoFormatAndroid extends VideoFormat {
    private MediaFormat mediaFormat;

    VideoFormatAndroid(MediaFormat mediaFormat2) {
        this.mediaFormat = mediaFormat2;
        setVideoFrameSize(mediaFormat2.getInteger("width"), mediaFormat2.getInteger("height"));
        setVideoCodec(mediaFormat2.getString("mime"));
    }

    public VideoFormatAndroid(String str, int i, int i2) {
        if (i > 1280 || i2 > 1280) {
            if (i > i2) {
                i = 1280;
                i2 = 720;
            } else {
                i = 720;
                i2 = 1280;
            }
        }
        this.mediaFormat = MediaFormat.createVideoFormat(str, i, i2);
        setVideoFrameSize(i, i2);
        setVideoCodec(str);
    }

    public MediaFormat getNativeFormat() {
        if (this.mediaFormat.containsKey("rotation-degrees")) {
            this.mediaFormat.setInteger("rotation-degrees", 0);
        }
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
