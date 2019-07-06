package org.m4m;

import org.m4m.domain.MediaFormat;

public abstract class AudioFormat extends MediaFormat {
    private String mimeType;

    /* access modifiers changed from: protected */
    public void setAudioCodec(String str) {
        this.mimeType = str;
    }

    public String getAudioCodec() {
        return this.mimeType;
    }

    public int getAudioSampleRateInHz() {
        try {
            return getInteger("sample-rate");
        } catch (NullPointerException unused) {
            throw new RuntimeException("No info available.");
        }
    }

    public int getAudioChannelCount() {
        try {
            return getInteger("channel-count");
        } catch (NullPointerException unused) {
            throw new RuntimeException("No info available.");
        }
    }

    public void setAudioBitrateInBytes(int i) {
        setInteger("bitrate", i);
    }

    public void setAudioProfile(int i) {
        setInteger("aac-profile", i);
    }
}
