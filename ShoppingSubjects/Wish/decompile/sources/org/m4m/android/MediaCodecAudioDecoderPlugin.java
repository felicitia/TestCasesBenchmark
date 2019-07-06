package org.m4m.android;

import android.media.MediaCodec;
import java.io.IOException;
import org.m4m.domain.ISurfaceWrapper;
import org.m4m.domain.MediaFormat;

public class MediaCodecAudioDecoderPlugin extends MediaCodecDecoderPlugin {
    public MediaCodecAudioDecoderPlugin() {
        super("audio/mp4a-latm");
    }

    public void configure(MediaFormat mediaFormat, ISurfaceWrapper iSurfaceWrapper, int i) {
        this.mediaCodec.configure(MediaFormatTranslator.from(mediaFormat), null, null, i);
    }

    public void release() {
        this.mediaCodec.release();
    }

    public void recreate() {
        try {
            release();
            this.mediaCodec = MediaCodec.createDecoderByType("audio/mp4a-latm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
