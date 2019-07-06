package org.m4m.domain;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import org.m4m.IAudioEffect;

public class AudioEffector extends MediaCodecPlugin {
    private LinkedList<IAudioEffect> audioEffects;
    private LinkedList<Frame> framesOutput;
    private LinkedList<Frame> framesPool;

    public void checkIfOutputQueueHasData() {
    }

    public void close() throws IOException {
    }

    public void configure() {
    }

    public ISurface getSurface() {
        return null;
    }

    public void pull(Frame frame) {
    }

    public void releaseOutputBuffer(int i) {
    }

    public void setOutputSurface(ISurface iSurface) {
    }

    public void waitForSurface(long j) {
    }

    /* access modifiers changed from: protected */
    public void initInputCommandQueue() {
        feedMeIfNotDraining();
    }

    /* access modifiers changed from: protected */
    public void feedMeIfNotDraining() {
        if (this.state != PluginState.Draining && this.state != PluginState.Drained) {
            getInputCommandQueue().queue(Command.NeedData, Integer.valueOf(getTrackId()));
        }
    }

    public void push(Frame frame) {
        super.push(frame);
        if (!frame.equals((Object) Frame.empty()) && !frame.equals((Object) Frame.EOF())) {
            applyEffects(frame);
        }
        if (this.framesPool.size() > 0) {
            feedMeIfNotDraining();
        }
        if (!frame.equals((Object) Frame.empty())) {
            hasData();
        }
    }

    private void applyEffects(Frame frame) {
        Iterator it = this.audioEffects.iterator();
        while (it.hasNext()) {
            IAudioEffect iAudioEffect = (IAudioEffect) it.next();
            Pair segment = iAudioEffect.getSegment();
            if (segment == null || (((Long) segment.left).longValue() <= frame.getSampleTime() && ((Long) segment.right).longValue() >= frame.getSampleTime())) {
                iAudioEffect.applyEffect(frame.getByteBuffer(), frame.getSampleTime());
                this.mediaFormat = iAudioEffect.getMediaFormat();
            }
        }
    }

    public Frame findFreeFrame() {
        if (this.framesPool.size() <= 0) {
            return null;
        }
        Iterator it = this.framesPool.iterator();
        Frame frame = (Frame) it.next();
        this.framesOutput.add(frame);
        it.remove();
        return frame;
    }

    public Frame getFrame() {
        Frame frame;
        if (this.framesOutput.size() > 0) {
            Iterator it = this.framesOutput.iterator();
            frame = (Frame) it.next();
            this.framesPool.add(frame);
            it.remove();
        } else {
            frame = null;
        }
        if (this.framesPool.size() > 0) {
            feedMeIfNotDraining();
        }
        return frame;
    }

    private void outputFormatChanged() {
        getOutputCommandQueue().queue(Command.OutputFormatChanged, Integer.valueOf(0));
    }

    public void setInputMediaFormat(MediaFormat mediaFormat) {
        this.outputMediaFormat = mediaFormat;
        outputFormatChanged();
    }

    public void start() {
        setState(PluginState.Normal);
    }

    public void stop() {
        setState(PluginState.Paused);
    }

    public void setMediaFormat(MediaFormat mediaFormat) {
        this.mediaFormat = mediaFormat;
    }

    public MediaFormat getOutputMediaFormat() {
        return this.mediaFormat;
    }

    public void reInitInputCommandQueue() {
        getInputCommandQueue().queue.clear();
        feedMeIfNotDraining();
    }

    public void setTrackId(int i) {
        this.trackId = i;
    }
}
