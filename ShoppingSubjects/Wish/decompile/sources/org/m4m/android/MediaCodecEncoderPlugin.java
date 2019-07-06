package org.m4m.android;

import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.opengl.EGLContext;
import android.view.Surface;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.m4m.domain.IEglContext;
import org.m4m.domain.IMediaCodec;
import org.m4m.domain.ISurface;
import org.m4m.domain.ISurfaceWrapper;
import org.m4m.domain.IWrapper;
import org.m4m.domain.MediaFormat;
import org.m4m.domain.graphics.IEglUtil;

public class MediaCodecEncoderPlugin implements IMediaCodec {
    private IEglUtil eglUtil;
    private BufferInfo inputBufferInfo;
    private ByteBuffer[] inputBuffers;
    private MediaCodec mediaCodec;
    private BufferInfo outputBufferInfo;
    private ByteBuffer[] outputBuffers;

    public void recreate() {
    }

    private MediaCodecEncoderPlugin(IEglUtil iEglUtil) {
        this.eglUtil = iEglUtil;
        init();
    }

    public MediaCodecEncoderPlugin(String str, IEglUtil iEglUtil) {
        try {
            this.eglUtil = iEglUtil;
            init();
            this.mediaCodec = MediaCodec.createEncoderByType(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        this.outputBufferInfo = new BufferInfo();
        this.inputBufferInfo = new BufferInfo();
    }

    public static MediaCodecEncoderPlugin createByCodecName(String str, IEglUtil iEglUtil) {
        MediaCodecEncoderPlugin mediaCodecEncoderPlugin = new MediaCodecEncoderPlugin(iEglUtil);
        String name = selectCodec(str).getName();
        if (name != null) {
            try {
                mediaCodecEncoderPlugin.mediaCodec = MediaCodec.createByCodecName(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mediaCodecEncoderPlugin;
    }

    private static MediaCodecInfo selectCodec(String str) {
        int codecCount = MediaCodecList.getCodecCount();
        for (int i = 0; i < codecCount; i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            if (codecInfoAt.isEncoder()) {
                for (String equalsIgnoreCase : codecInfoAt.getSupportedTypes()) {
                    if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                        return codecInfoAt;
                    }
                }
                continue;
            }
        }
        return null;
    }

    public void configure(MediaFormat mediaFormat, ISurfaceWrapper iSurfaceWrapper, int i) {
        Surface surface;
        if (mediaFormat.getMimeType().startsWith("video")) {
            MediaCodec mediaCodec2 = this.mediaCodec;
            android.media.MediaFormat from = MediaFormatTranslator.from(mediaFormat);
            if (iSurfaceWrapper == null) {
                surface = null;
            } else {
                surface = (Surface) ((SurfaceWrapper) iSurfaceWrapper).getNativeObject();
            }
            mediaCodec2.configure(from, surface, null, i);
        } else if (mediaFormat.getMimeType().startsWith("audio")) {
            this.mediaCodec.configure(MediaFormatTranslator.from(mediaFormat), null, null, i);
        }
    }

    public void start() {
        this.mediaCodec.start();
        this.inputBuffers = null;
        this.outputBuffers = null;
    }

    public void releaseOutputBuffer(int i, boolean z) {
        this.mediaCodec.releaseOutputBuffer(i, z);
    }

    public ISurface createInputSurface() {
        return new Surface(this.mediaCodec, this.eglUtil);
    }

    public ISurface createSimpleInputSurface(IEglContext iEglContext) {
        return new SimpleSurface(this.mediaCodec, (EGLContext) ((IWrapper) iEglContext).getNativeObject());
    }

    public ByteBuffer[] getInputBuffers() {
        if (this.inputBuffers == null) {
            this.inputBuffers = this.mediaCodec.getInputBuffers();
        }
        return this.inputBuffers;
    }

    public ByteBuffer[] getOutputBuffers() {
        if (this.outputBuffers == null) {
            this.outputBuffers = this.mediaCodec.getOutputBuffers();
        }
        return this.outputBuffers;
    }

    public void queueInputBuffer(int i, int i2, int i3, long j, int i4) {
        this.mediaCodec.queueInputBuffer(i, i2, i3, j, i4);
    }

    public int dequeueInputBuffer(long j) {
        return this.mediaCodec.dequeueInputBuffer(j);
    }

    public int dequeueOutputBuffer(IMediaCodec.BufferInfo bufferInfo, long j) {
        int dequeueOutputBuffer = this.mediaCodec.dequeueOutputBuffer(this.outputBufferInfo, j);
        if (dequeueOutputBuffer == -3) {
            this.outputBuffers = null;
            getOutputBuffers();
        }
        BufferInfoTranslator.convertFromAndroid(this.outputBufferInfo, bufferInfo);
        return dequeueOutputBuffer;
    }

    public MediaFormat getOutputFormat() {
        return MediaFormatTranslator.toDomain(this.mediaCodec.getOutputFormat());
    }

    public void signalEndOfInputStream() {
        this.mediaCodec.signalEndOfInputStream();
    }

    public void stop() {
        this.mediaCodec.stop();
    }

    public void release() {
        this.mediaCodec.release();
    }
}
