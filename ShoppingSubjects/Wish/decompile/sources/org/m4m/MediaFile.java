package org.m4m;

import org.m4m.domain.MediaFormatType;
import org.m4m.domain.MediaSource;

public class MediaFile {
    private MediaSource mediaSource;

    public MediaFile(MediaSource mediaSource2) {
        this.mediaSource = mediaSource2;
    }

    public MediaSource getMediaSource() {
        return this.mediaSource;
    }

    public VideoFormat getVideoFormat(int i) {
        return (VideoFormat) this.mediaSource.getMediaFormatByType(MediaFormatType.VIDEO);
    }

    public AudioFormat getAudioFormat(int i) {
        return (AudioFormat) this.mediaSource.getMediaFormatByType(MediaFormatType.AUDIO);
    }

    public long getDurationInMicroSec() {
        return this.mediaSource.getDurationInMicroSec();
    }

    public long getSegmentsDurationInMicroSec() {
        return this.mediaSource.getSegmentsDurationInMicroSec();
    }

    public void start() {
        this.mediaSource.start();
    }

    public int getRotation() {
        return this.mediaSource.getRotation();
    }
}
