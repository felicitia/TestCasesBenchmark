package org.m4m;

import java.io.IOException;
import java.io.Serializable;
import org.m4m.domain.AudioEffector;
import org.m4m.domain.AudioEncoder;
import org.m4m.domain.CommandProcessor;
import org.m4m.domain.FileSegment;
import org.m4m.domain.IAndroidMediaObjectFactory;
import org.m4m.domain.MediaFormatType;
import org.m4m.domain.MultipleMediaSource;
import org.m4m.domain.Pipeline;
import org.m4m.domain.Plugin;
import org.m4m.domain.ProgressTracker;
import org.m4m.domain.Render;
import org.m4m.domain.VideoEffector;
import org.m4m.domain.VideoEncoder;
import org.m4m.domain.VideoTimeScaler;

public class MediaComposer implements Serializable {
    private Plugin audioDecoder;
    private AudioEffector audioEffector;
    private AudioEncoder audioEncoder;
    private AudioFormat audioFormat;
    /* access modifiers changed from: private */
    public CommandProcessor commandProcessor;
    private IAndroidMediaObjectFactory factory;
    /* access modifiers changed from: private */
    public MultipleMediaSource multipleMediaSource;
    /* access modifiers changed from: private */
    public Pipeline pipeline;
    private IProgressListener progressListener;
    /* access modifiers changed from: private */
    public ProgressTracker progressTracker = new ProgressTracker();
    private FileSegment segment = new FileSegment(0, 0);
    private Render sink;
    private int timeScale = 1;
    private Plugin videoDecoder;
    private VideoEffector videoEffector;
    private VideoEncoder videoEncoder;
    private VideoFormat videoFormat;
    private VideoTimeScaler videoTimeScaler;

    public MediaComposer(IAndroidMediaObjectFactory iAndroidMediaObjectFactory, IProgressListener iProgressListener) {
        this.progressListener = iProgressListener;
        this.factory = iAndroidMediaObjectFactory;
        this.multipleMediaSource = new MultipleMediaSource();
    }

    public void addSourceFile(Uri uri) throws IOException, RuntimeException {
        this.multipleMediaSource.add(new MediaFile(this.factory.createMediaSource(uri)));
    }

    public void setTargetFile(String str, int i) throws IOException {
        this.sink = this.factory.createSink(str, i, this.progressListener, this.progressTracker);
    }

    public void setTargetVideoFormat(VideoFormat videoFormat2) {
        this.videoFormat = videoFormat2;
    }

    public void setTargetAudioFormat(AudioFormat audioFormat2) {
        this.audioFormat = audioFormat2;
    }

    public void start() {
        this.multipleMediaSource.verify();
        this.commandProcessor = new CommandProcessor(this.progressListener);
        this.pipeline = new Pipeline(this.commandProcessor);
        this.pipeline.setMediaSource(this.multipleMediaSource);
        if (this.videoFormat != null && this.multipleMediaSource.hasTrack(MediaFormatType.VIDEO)) {
            this.videoDecoder = this.factory.createVideoDecoder(this.videoFormat);
            this.videoEncoder = this.factory.createVideoEncoder();
            this.videoEncoder.setMediaFormat(this.videoFormat);
        }
        if (this.videoDecoder != null) {
            this.pipeline.addVideoDecoder(this.videoDecoder);
        }
        if (this.videoEncoder != null) {
            this.pipeline.addVideoEncoder(this.videoEncoder);
        }
        if (this.videoEffector != null) {
            this.videoEffector.setTimeScale(this.timeScale);
            this.videoEffector.setTimeScalerSegment(this.segment);
            this.pipeline.addVideoEffect(this.videoEffector);
        }
        if (this.videoTimeScaler != null && this.videoEffector == null) {
            this.pipeline.addVideoTimeScaler(this.videoTimeScaler);
        }
        if (this.audioFormat != null && this.multipleMediaSource.hasTrack(MediaFormatType.AUDIO)) {
            this.audioDecoder = this.factory.createAudioDecoder();
            this.audioEncoder = this.factory.createAudioEncoder(this.audioFormat.getAudioCodec());
            this.audioEncoder.setMediaFormat(this.audioFormat);
        }
        if (this.audioDecoder != null) {
            this.pipeline.addAudioDecoder(this.audioDecoder);
        }
        if (this.audioEncoder != null) {
            this.pipeline.addAudioEncoder(this.audioEncoder);
        }
        if (this.audioEffector != null) {
            this.audioEffector.setMediaFormat(this.audioFormat);
            this.pipeline.addAudioEffect(this.audioEffector);
        }
        this.pipeline.setSink(this.sink);
        startCommandsProcessingAsync();
    }

    public void stop() {
        if (this.pipeline != null) {
            this.pipeline.stop();
        }
        notifyOnMediaStop();
    }

    /* access modifiers changed from: private */
    public void notifyOnMediaStart() {
        this.progressListener.onMediaStart();
    }

    /* access modifiers changed from: private */
    public void notifyOnMediaDone() {
        this.progressListener.onMediaDone();
    }

    private void notifyOnMediaStop() {
        this.progressListener.onMediaStop();
    }

    /* access modifiers changed from: private */
    public void notifyOnMediaProgress(float f) {
        this.progressListener.onMediaProgress(f);
    }

    /* access modifiers changed from: private */
    public void notifyOnError(Exception exc) {
        this.progressListener.onError(exc);
    }

    private void startCommandsProcessingAsync() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    MediaComposer.this.pipeline.resolve();
                    MediaComposer.this.notifyOnMediaStart();
                    MediaComposer.this.notifyOnMediaProgress(0.0f);
                    MediaComposer.this.progressTracker.setFinish((float) MediaComposer.this.multipleMediaSource.getSegmentsDurationInMicroSec());
                    MediaComposer.this.commandProcessor.process();
                    try {
                        MediaComposer.this.pipeline.release();
                        MediaComposer.this.notifyOnMediaProgress(1.0f);
                        MediaComposer.this.notifyOnMediaDone();
                    } catch (IOException e) {
                        MediaComposer.this.notifyOnError(e);
                    }
                } catch (Exception e2) {
                    try {
                        MediaComposer.this.pipeline.release();
                        MediaComposer.this.notifyOnError(e2);
                    } catch (IOException e3) {
                        MediaComposer.this.notifyOnError(e2);
                        MediaComposer.this.notifyOnError(e3);
                    }
                }
            }
        }).start();
    }
}
