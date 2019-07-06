package org.m4m.domain;

import java.io.IOException;
import org.m4m.domain.graphics.TextureRenderer.FillMode;

public class VideoTimeScaler extends MediaCodecPlugin {
    private boolean awaitCurrentFileSample;
    private ISurface encoderSurface;
    private IAndroidMediaObjectFactory factory;
    private FillMode fillMode;
    IFrameBuffer frameBuffer;
    private long frames;
    private IEffectorSurface internalSurface;
    private float[] matrix;
    private int outputAngle;
    private PreviewContext previewContext;
    IPreview previewRender;
    private boolean previewRenderMode;
    private Resolution resolution;
    private FileSegment segment;
    private int timeScale;

    public boolean canConnectFirst(IOutputRaw iOutputRaw) {
        return false;
    }

    public void checkIfOutputQueueHasData() {
    }

    public void configure() {
    }

    public void pull(Frame frame) {
    }

    public void setMediaFormat(MediaFormat mediaFormat) {
    }

    public void waitForSurface(long j) {
    }

    /* access modifiers changed from: protected */
    public void initInputCommandQueue() {
        getInputCommandQueue().queue(Command.NeedInputFormat, Integer.valueOf(getTrackId()));
    }

    /* access modifiers changed from: protected */
    public void feedMeIfNotDraining() {
        feedMeIfNotDraining(getTrackId());
    }

    /* access modifiers changed from: protected */
    public void hasData(Frame frame) {
        if (this.frameCount < 2) {
            renderSurfaceForFrame(frame);
        }
        super.hasData();
    }

    private void renderSurfaceForFrame(Frame frame) {
        if (!this.previewRenderMode) {
            this.encoderSurface.setPresentationTime(frame.getSampleTime() * 1000);
            this.encoderSurface.swapBuffers();
            this.frameCount++;
        }
    }

    public void push(Frame frame) {
        if (!frame.equals((Object) Frame.empty())) {
            applyEffectorOperations(frame);
        } else {
            configureNextData();
        }
    }

    private void applyEffectorOperations(Frame frame) {
        initFrameBuffer();
        long sampleTime = frame.getSampleTime();
        if (sampleTime >= ((Long) this.segment.pair.left).longValue()) {
            long longValue = ((Long) this.segment.pair.left).longValue();
            if (sampleTime < ((Long) this.segment.pair.right).longValue()) {
                this.frames++;
                sampleTime = longValue + ((sampleTime - ((Long) this.segment.pair.left).longValue()) / ((long) this.timeScale));
                if (this.frames % ((long) this.timeScale) != 0) {
                    feedMeIfNotDraining();
                    return;
                }
            } else {
                sampleTime = longValue + ((((Long) this.segment.pair.right).longValue() - ((Long) this.segment.pair.left).longValue()) / ((long) this.timeScale)) + (sampleTime - ((Long) this.segment.pair.right).longValue());
            }
        }
        apply();
        frame.setSampleTime(sampleTime);
        updatePreview();
        hasData(frame);
    }

    private void configureNextData() {
        if (this.awaitCurrentFileSample) {
            setTransitionTrack();
            this.awaitCurrentFileSample = false;
        }
        feedMeIfNotDraining();
    }

    private void setTransitionTrack() {
        if (getTrackId() == 0) {
            setTrackId(1);
        } else {
            setTrackId(0);
        }
    }

    private void needData(int i) {
        if (this.state != PluginState.Draining && this.state != PluginState.Drained) {
            getInputCommandQueue().queue(Command.NeedData, Integer.valueOf(i));
        }
    }

    private void apply() {
        prepareSurface();
        bindFB();
        this.internalSurface.drawImage(getInputIndex(), this.matrix, this.fillMode);
        unbindFB();
        renderOntoEncoderContext(this.fillMode);
    }

    private void prepareSurface() {
        if (this.previewRender == null) {
            this.internalSurface.awaitAndCopyNewImage();
            this.internalSurface.getTransformMatrix(this.matrix);
            return;
        }
        this.matrix = this.previewContext.previewTexture.getTransformMatrix();
    }

    private void feedMeIfNotDraining(int i) {
        if (this.frameCount < 2) {
            needData(i);
        }
    }

    private void updatePreview() {
        if (this.previewRender != null) {
            this.previewRender.renderSurfaceFromFrameBuffer(this.frameBuffer.getTextureId());
        }
    }

    private void renderOntoEncoderContext(FillMode fillMode2) {
        if (this.frameBuffer != null) {
            this.internalSurface.drawImage2D(this.frameBuffer.getTextureId(), this.matrix, this.outputAngle, fillMode2);
        }
    }

    private int getInputIndex() {
        if (this.previewRender == null) {
            return this.internalSurface.getSurfaceId();
        }
        return this.previewContext.previewTextureId;
    }

    private void initFrameBuffer() {
        if (this.previewRender != null && this.frameBuffer == null) {
            this.frameBuffer = this.factory.createFrameBuffer();
            this.frameBuffer.setResolution(this.resolution);
        }
    }

    private void unbindFB() {
        if (this.previewRender != null) {
            this.frameBuffer.unbind();
        }
    }

    private void bindFB() {
        if (this.frameBuffer != null) {
            this.frameBuffer.bind();
        }
    }

    public void releaseOutputBuffer(int i) {
        this.frameCount--;
        feedMeIfNotDraining();
    }

    public Frame getFrame() {
        if (this.state == PluginState.Drained) {
            throw new RuntimeException("Out of order operation.");
        }
        Frame frame = new Frame(null, 1, 1, 0, 0, 0);
        return frame;
    }

    public void start() {
        initInputCommandQueue();
        if (this.encoderSurface != null || this.previewRenderMode) {
            if (this.encoderSurface != null) {
                this.encoderSurface.makeCurrent();
            }
            this.internalSurface = this.factory.createEffectorSurface();
            return;
        }
        throw new RuntimeException("Encoder surface not set.");
    }

    public void close() throws IOException {
        stop();
        super.close();
    }

    public void stop() {
        super.stop();
        if (this.previewRender != null) {
            this.previewRender.setListener(null);
            this.previewRender.requestRendering();
            this.previewRender = null;
        }
        if (this.frameBuffer != null) {
            this.frameBuffer.release();
            this.frameBuffer = null;
        }
        if (this.internalSurface != null) {
            this.internalSurface.release();
            this.internalSurface = null;
        }
    }

    public void setOutputSurface(ISurface iSurface) {
        this.encoderSurface = iSurface;
    }

    public ISurface getSurface() {
        if (this.internalSurface != null) {
            return this.internalSurface;
        }
        throw new RuntimeException("Effector surface not set.");
    }

    public void onSurfaceAvailable(ISurfaceListener iSurfaceListener) {
        if (this.previewRender == null) {
            iSurfaceListener.onSurfaceAvailable(this.factory.getCurrentEglContext());
            return;
        }
        if (this.previewRenderMode) {
            iSurfaceListener.onSurfaceAvailable(this.previewContext.eglContext);
        } else {
            this.previewContext = this.previewRender.getSharedContext();
            iSurfaceListener.onSurfaceAvailable(this.previewContext.eglContext);
        }
    }

    public void setInputResolution(Resolution resolution2) {
        this.resolution = resolution2;
        super.setInputResolution(resolution2);
    }

    public void setTrackId(int i) {
        this.trackId = i;
    }

    public void drain(int i) {
        getInputCommandQueue().clear();
        getOutputCommandQueue().queue(Command.EndOfFile, Integer.valueOf(0));
    }
}
