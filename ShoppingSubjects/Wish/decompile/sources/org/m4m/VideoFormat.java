package org.m4m;

import org.m4m.domain.MediaFormat;
import org.m4m.domain.Resolution;

public abstract class VideoFormat extends MediaFormat {
    private int height;
    private String mimeType;
    private int width;

    /* access modifiers changed from: protected */
    public void setVideoCodec(String str) {
        this.mimeType = str;
    }

    public void setVideoFrameSize(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public Resolution getVideoFrameSize() {
        return new Resolution(this.width, this.height);
    }

    public void setVideoBitRateInKBytes(int i) {
        if (((double) (this.width * this.height * 30 * 2)) * 7.0E-5d < ((double) i)) {
            i = (int) (((double) (this.width * this.height * 30 * 2)) * 7.0E-5d);
        }
        setInteger("bitrate", i * 1024);
    }

    public void setVideoFrameRate(int i) {
        setInteger("frame-rate", i);
    }

    public void setVideoIFrameInterval(int i) {
        setInteger("i-frame-interval", i);
    }

    public void setColorFormat(int i) {
        setInteger("color-format", i);
    }
}
