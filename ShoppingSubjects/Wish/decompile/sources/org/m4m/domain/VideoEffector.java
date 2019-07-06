package org.m4m.domain;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import org.m4m.IVideoEffect;
import org.m4m.domain.graphics.TextureRenderer.FillMode;

public class VideoEffector extends MediaCodecPlugin {
    private boolean awaitCurrentFileSample;
    private ISurface encoderSurface;
    private IAndroidMediaObjectFactory factory;
    private FillMode fillMode;
    IFrameBuffer frameBuffer;
    private long frames;
    private IEffectorSurface internalOverlappingSurface;
    private IEffectorSurface internalSurface;
    private float[] matrix;
    private int outputAngle;
    private PreviewContext previewContext;
    IPreview previewRender;
    private boolean previewRenderMode;
    private long previousPts;
    private Resolution resolution;
    private Frame saved;
    private FileSegment segment;
    private int skipTransitionEffect;
    private int timeScale;
    private LinkedList<IVideoEffect> videoEffects;

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
        if (this.saved == null) {
            if (this.frameCount < 2) {
                renderSurfaceForFrame(frame);
            }
            super.hasData();
        }
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
        applyEffects(frame);
        frame.setSampleTime(sampleTime);
        updatePreview();
        hasData(frame);
    }

    private void configureNextData() {
        if (this.awaitCurrentFileSample) {
            setTransitionTrack();
            exchangeTransitionSurface();
            this.awaitCurrentFileSample = false;
            this.skipTransitionEffect++;
        }
        feedMeIfNotDraining();
    }

    private void exchangeTransitionSurface() {
        IEffectorSurface iEffectorSurface = this.internalOverlappingSurface;
        this.internalOverlappingSurface = this.internalSurface;
        this.internalSurface = iEffectorSurface;
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

    private IVideoEffect applyEffects(Frame frame) {
        boolean z;
        IVideoEffect iVideoEffect;
        if (this.saved != null) {
            return null;
        }
        prepareSurface();
        long sampleTime = frame.getSampleTime();
        Iterator it = this.videoEffects.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                iVideoEffect = null;
                break;
            }
            iVideoEffect = (IVideoEffect) it.next();
            if (isFrameInSegment(sampleTime, iVideoEffect.getSegment())) {
                if (sampleTime - this.previousPts > 1000000) {
                    FileSegment segment2 = iVideoEffect.getSegment();
                    iVideoEffect.setSegment(new FileSegment(sampleTime, (sampleTime + ((Long) segment2.pair.right).longValue()) - ((Long) segment2.pair.left).longValue()));
                }
                bindFB();
                iVideoEffect.applyEffect(getInputIndex(), sampleTime, this.matrix);
                this.outputAngle = iVideoEffect.getAngle();
                unbindFB();
                z = true;
            }
        }
        this.previousPts = sampleTime;
        if (!z) {
            bindFB();
            this.internalSurface.drawImage(getInputIndex(), this.matrix, this.fillMode);
            unbindFB();
        }
        renderOntoEncoderContext(this.fillMode);
        return iVideoEffect;
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

    private boolean isFrameInSegment(long j, FileSegment fileSegment) {
        return (((Long) fileSegment.pair.left).longValue() <= j && j <= ((Long) fileSegment.pair.right).longValue()) || (((Long) fileSegment.pair.left).longValue() == 0 && ((Long) fileSegment.pair.right).longValue() == 0);
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
            this.internalOverlappingSurface = this.factory.createEffectorSurface();
            Iterator it = this.videoEffects.iterator();
            while (it.hasNext()) {
                ((IVideoEffect) it.next()).start();
            }
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
            Iterator it = this.videoEffects.iterator();
            while (it.hasNext()) {
                ((IVideoEffect) it.next()).setFillMode(FillMode.PreserveAspectFit);
            }
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
        if (this.internalOverlappingSurface != null) {
            this.internalOverlappingSurface.release();
            this.internalOverlappingSurface = null;
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
        if (this.previewRender != null) {
            Iterator it = this.videoEffects.iterator();
            while (it.hasNext()) {
                ((IVideoEffect) it.next()).setFillMode(FillMode.PreserveSize);
            }
        }
        super.setInputResolution(resolution2);
        Iterator it2 = this.videoEffects.iterator();
        while (it2.hasNext()) {
            ((IVideoEffect) it2.next()).setInputResolution(resolution2);
        }
    }

    public IPreview getPreview() {
        return this.previewRender;
    }

    public void setTrackId(int i) {
        this.trackId = i;
    }

    public void drain(int i) {
        getInputCommandQueue().clear();
        getOutputCommandQueue().queue(Command.EndOfFile, Integer.valueOf(0));
    }

    public void setTimeScale(int i) {
        this.timeScale = i;
    }

    public void setTimeScalerSegment(FileSegment fileSegment) {
        this.segment = fileSegment;
    }
}
