package org.m4m.domain;

import java.io.IOException;
import org.m4m.IProgressListener;
import org.m4m.Uri;

public interface IAndroidMediaObjectFactory {
    Plugin createAudioDecoder();

    AudioEncoder createAudioEncoder(String str);

    IEffectorSurface createEffectorSurface();

    IFrameBuffer createFrameBuffer();

    MediaSource createMediaSource(Uri uri) throws IOException;

    Render createSink(String str, int i, IProgressListener iProgressListener, ProgressTracker progressTracker) throws IOException;

    VideoDecoder createVideoDecoder(MediaFormat mediaFormat);

    VideoEncoder createVideoEncoder();

    IEglContext getCurrentEglContext();
}
