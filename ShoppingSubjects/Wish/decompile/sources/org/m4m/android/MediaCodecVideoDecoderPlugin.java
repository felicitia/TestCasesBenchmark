package org.m4m.android;

import android.media.MediaCodec;
import android.view.Surface;
import java.io.IOException;
import org.m4m.domain.ISurfaceWrapper;
import org.m4m.domain.MediaFormat;

public class MediaCodecVideoDecoderPlugin extends MediaCodecDecoderPlugin {
    String mime = "";

    public MediaCodecVideoDecoderPlugin(MediaFormat mediaFormat) {
        super(getMimeTypeFor(mediaFormat));
        this.mime = getMimeTypeFor(mediaFormat);
    }

    public void configure(MediaFormat mediaFormat, ISurfaceWrapper iSurfaceWrapper, int i) {
        this.mediaCodec.configure(MediaFormatTranslator.from(mediaFormat), iSurfaceWrapper != null ? (Surface) ((SurfaceWrapper) iSurfaceWrapper).getNativeObject() : null, null, i);
    }

    public void release() {
        this.mediaCodec.release();
    }

    public void recreate() {
        release();
        try {
            this.mediaCodec = MediaCodec.createDecoderByType(this.mime);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getMimeTypeFor(MediaFormat mediaFormat) {
        return ((VideoFormatAndroid) mediaFormat).getString("mime");
    }
}
