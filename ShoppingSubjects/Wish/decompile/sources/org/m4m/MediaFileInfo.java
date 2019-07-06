package org.m4m;

import java.io.IOException;
import org.m4m.domain.IAndroidMediaObjectFactory;
import org.m4m.domain.ISurfaceWrapper;
import org.m4m.domain.MediaFormat;
import org.m4m.domain.MediaSource;

public class MediaFileInfo {
    MediaFormat audioFormat = null;
    private IAndroidMediaObjectFactory factory = null;
    MediaFile file;
    private ISurfaceWrapper outputSurface = null;
    MediaSource source;
    MediaFormat videoFormat = null;

    public MediaFileInfo(IAndroidMediaObjectFactory iAndroidMediaObjectFactory) {
        this.factory = iAndroidMediaObjectFactory;
    }

    public void setUri(Uri uri) throws IOException {
        this.source = this.factory.createMediaSource(uri);
        prepareMediaFile();
    }

    private void prepareMediaFile() {
        this.file = new MediaFile(this.source);
        int i = 0;
        for (MediaFormat mediaFormat : this.source.getMediaFormats()) {
            int i2 = i + 1;
            this.source.selectTrack(i);
            i = i2;
        }
        this.videoFormat = this.file.getVideoFormat(0);
        this.audioFormat = this.file.getAudioFormat(0);
    }

    public MediaFormat getVideoFormat() {
        return this.videoFormat;
    }

    public MediaFormat getAudioFormat() {
        return this.audioFormat;
    }

    public long getDurationInMicroSec() {
        return this.file.getDurationInMicroSec();
    }

    public int getRotation() {
        return this.file.getRotation();
    }
}
