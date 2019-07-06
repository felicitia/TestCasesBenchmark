package org.m4m.android;

import android.media.MediaMuxer;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.m4m.domain.IMediaCodec.BufferInfo;
import org.m4m.domain.IMediaMuxer;
import org.m4m.domain.MediaFormat;

public class MediaMuxerPlugin implements IMediaMuxer {
    private long[] lastPresentationTime = new long[2];
    private final MediaMuxer mediaMuxer;

    public MediaMuxerPlugin(String str, int i) throws IOException {
        this.mediaMuxer = new MediaMuxer(str, i);
    }

    public int addTrack(MediaFormat mediaFormat) {
        return this.mediaMuxer.addTrack(MediaFormatTranslator.from(mediaFormat));
    }

    public void release() {
        this.mediaMuxer.release();
    }

    public void setOrientationHint(int i) {
        this.mediaMuxer.setOrientationHint(i);
    }

    public void start() {
        this.mediaMuxer.start();
    }

    public void stop() {
        this.mediaMuxer.stop();
    }

    public void writeSampleData(int i, ByteBuffer byteBuffer, BufferInfo bufferInfo) {
        if (bufferInfo.size != 0 && this.lastPresentationTime[i] <= bufferInfo.presentationTimeUs && (bufferInfo.flags & 2) == 0) {
            this.lastPresentationTime[i] = bufferInfo.presentationTimeUs;
            this.mediaMuxer.writeSampleData(i, byteBuffer, ByteBufferTranslator.from(bufferInfo));
        }
    }
}
