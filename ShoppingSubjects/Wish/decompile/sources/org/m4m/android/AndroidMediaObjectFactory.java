package org.m4m.android;

import android.content.Context;
import android.opengl.EGL14;
import android.view.Surface;
import java.io.IOException;
import org.m4m.IProgressListener;
import org.m4m.Uri;
import org.m4m.android.graphics.EglUtil;
import org.m4m.android.graphics.FrameBuffer;
import org.m4m.domain.AudioDecoder;
import org.m4m.domain.AudioEncoder;
import org.m4m.domain.IAndroidMediaObjectFactory;
import org.m4m.domain.IEffectorSurface;
import org.m4m.domain.IEglContext;
import org.m4m.domain.IFrameBuffer;
import org.m4m.domain.ISurfaceWrapper;
import org.m4m.domain.MediaFormat;
import org.m4m.domain.MediaSource;
import org.m4m.domain.MuxRender;
import org.m4m.domain.ProgressTracker;
import org.m4m.domain.Render;
import org.m4m.domain.VideoDecoder;
import org.m4m.domain.VideoEncoder;
import org.m4m.domain.graphics.IEglUtil;

public class AndroidMediaObjectFactory implements IAndroidMediaObjectFactory {
    MediaCodecEncoderPlugin audioMediaCodec;
    private final Context context;

    public static class Converter {
        public static ISurfaceWrapper convert(Surface surface) {
            return new SurfaceWrapper(surface);
        }
    }

    private int getDeviceSpecificTimeout() {
        return 10;
    }

    public AndroidMediaObjectFactory(Context context2) {
        this.context = context2;
    }

    public MediaSource createMediaSource(Uri uri) throws IOException {
        MediaExtractorPlugin mediaExtractorPlugin = new MediaExtractorPlugin();
        mediaExtractorPlugin.setDataSource(this.context, uri);
        return new MediaSource(mediaExtractorPlugin);
    }

    public VideoDecoder createVideoDecoder(MediaFormat mediaFormat) {
        VideoDecoder videoDecoder = new VideoDecoder(new MediaCodecVideoDecoderPlugin(mediaFormat));
        videoDecoder.setTimeout(getDeviceSpecificTimeout());
        return videoDecoder;
    }

    public VideoEncoder createVideoEncoder() {
        VideoEncoder videoEncoder = new VideoEncoder(new MediaCodecEncoderPlugin("video/avc", getEglUtil()));
        videoEncoder.setTimeout(getDeviceSpecificTimeout());
        return videoEncoder;
    }

    public AudioDecoder createAudioDecoder() {
        AudioDecoder audioDecoder = new AudioDecoder(new MediaCodecAudioDecoderPlugin());
        audioDecoder.setTimeout(getDeviceSpecificTimeout());
        return audioDecoder;
    }

    public AudioEncoder createAudioEncoder(String str) {
        if (str == null) {
            str = "audio/mp4a-latm";
        }
        this.audioMediaCodec = MediaCodecEncoderPlugin.createByCodecName(str, getEglUtil());
        AudioEncoder audioEncoder = new AudioEncoder(this.audioMediaCodec);
        audioEncoder.setTimeout(getDeviceSpecificTimeout());
        return audioEncoder;
    }

    public Render createSink(String str, int i, IProgressListener iProgressListener, ProgressTracker progressTracker) throws IOException {
        if (str == null) {
            return null;
        }
        MediaMuxerPlugin mediaMuxerPlugin = new MediaMuxerPlugin(str, 0);
        mediaMuxerPlugin.setOrientationHint(i);
        return new MuxRender(mediaMuxerPlugin, iProgressListener, progressTracker);
    }

    public IEffectorSurface createEffectorSurface() {
        return new EffectorSurface(getEglUtil());
    }

    public IEglContext getCurrentEglContext() {
        return new EGLContextWrapper(EGL14.eglGetCurrentContext());
    }

    public IEglUtil getEglUtil() {
        return EglUtil.getInstance();
    }

    public IFrameBuffer createFrameBuffer() {
        return new FrameBuffer(getEglUtil());
    }
}
