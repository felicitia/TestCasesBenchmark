package org.m4m.domain;

import java.io.IOException;
import java.util.ArrayList;
import org.m4m.AudioFormat;
import org.m4m.IProgressListener;
import org.m4m.VideoFormat;
import org.m4m.domain.IMediaCodec.BufferInfo;

public class MuxRender extends Render {
    private int audioTrackId = -1;
    private int connectedPluginsCount = 0;
    private int drainCount = 0;
    private FrameBuffer frameBuffer = new FrameBuffer(0);
    private IMediaMuxer muxer;
    private final IMediaMuxer notReadyMuxer;
    private final IProgressListener progressListener;
    private final ProgressTracker progressTracker;
    private ArrayList<IPluginOutput> releasersList = new ArrayList<>();
    private int tracksCount = 0;
    private int videoTrackId = -1;
    private boolean zeroFramesReceived = true;

    public boolean canConnectFirst(IOutputRaw iOutputRaw) {
        return true;
    }

    public void fillCommandQueues() {
    }

    /* access modifiers changed from: protected */
    public void initInputCommandQueue() {
    }

    public MuxRender(IMediaMuxer iMediaMuxer, IProgressListener iProgressListener, ProgressTracker progressTracker2) {
        this.notReadyMuxer = iMediaMuxer;
        this.progressListener = iProgressListener;
        this.progressTracker = progressTracker2;
    }

    public void push(Frame frame) {
        if (this.zeroFramesReceived) {
            this.zeroFramesReceived = false;
        }
        if (this.frameBuffer.areAllTracksConfigured()) {
            writeBufferedFrames();
            writeSampleData(frame);
            feedMeIfNotDraining();
            return;
        }
        this.frameBuffer.push(frame);
        getInputCommandQueue().queue(Command.NeedInputFormat, Integer.valueOf(0));
    }

    public void pushWithReleaser(Frame frame, IPluginOutput iPluginOutput) {
        if (this.zeroFramesReceived) {
            this.zeroFramesReceived = false;
        }
        if (this.frameBuffer.areAllTracksConfigured()) {
            writeBufferedFrames();
            writeSampleData(frame);
            iPluginOutput.releaseOutputBuffer(frame.getBufferIndex());
            feedMeIfNotDraining();
            return;
        }
        this.frameBuffer.push(frame);
        this.releasersList.add(iPluginOutput);
        getInputCommandQueue().queue(Command.NeedInputFormat, Integer.valueOf(0));
    }

    private void writeBufferedFrames() {
        while (this.frameBuffer.canPull()) {
            Frame pull = this.frameBuffer.pull();
            writeSampleData(pull);
            ((IPluginOutput) this.releasersList.get(0)).releaseOutputBuffer(pull.getBufferIndex());
            this.releasersList.remove(0);
        }
    }

    private void writeSampleData(Frame frame) {
        BufferInfo bufferInfo = new BufferInfo();
        bufferInfo.flags = frame.getFlags();
        bufferInfo.presentationTimeUs = frame.getSampleTime();
        bufferInfo.size = frame.getLength();
        this.muxer.writeSampleData(frame.getTrackId(), frame.getByteBuffer(), bufferInfo);
        this.progressTracker.track((float) frame.getSampleTime());
        this.progressListener.onMediaProgress(this.progressTracker.getProgress());
    }

    public void drain(int i) {
        this.drainCount++;
        if (this.drainCount == this.connectedPluginsCount) {
            closeRender();
            this.progressListener.onMediaStop();
            if (this.onStopListener != null) {
                this.onStopListener.onStop();
            }
            getInputCommandQueue().clear();
            setState(PluginState.Drained);
        }
        if (this.frameBuffer.areAllTracksConfigured()) {
            feedMeIfNotDraining();
        } else {
            getInputCommandQueue().queue(Command.NeedInputFormat, Integer.valueOf(0));
        }
    }

    public void configure() {
        this.connectedPluginsCount++;
        getInputCommandQueue().queue(Command.NeedInputFormat, Integer.valueOf(0));
        this.frameBuffer.addTrack();
    }

    public void setMediaFormat(MediaFormat mediaFormat) {
        int addTrack = this.notReadyMuxer.addTrack(mediaFormat);
        if (mediaFormat instanceof VideoFormat) {
            this.videoTrackId = addTrack;
        }
        if (mediaFormat instanceof AudioFormat) {
            this.audioTrackId = addTrack;
        }
        this.frameBuffer.configure(this.tracksCount);
        this.tracksCount++;
    }

    public int getTrackIdByMediaFormat(MediaFormat mediaFormat) {
        if (mediaFormat instanceof VideoFormat) {
            if (this.videoTrackId != -1) {
                return this.videoTrackId;
            }
            throw new IllegalStateException("Video track not initialised");
        } else if (!(mediaFormat instanceof AudioFormat)) {
            return -1;
        } else {
            if (this.audioTrackId != -1) {
                return this.audioTrackId;
            }
            throw new IllegalStateException("Audio track not initialised");
        }
    }

    public void start() {
        if (this.connectedPluginsCount == this.tracksCount) {
            this.notReadyMuxer.start();
            this.muxer = this.notReadyMuxer;
            for (int i = 0; i < this.tracksCount; i++) {
                feedMeIfNotDraining();
            }
        }
    }

    public void close() throws IOException {
        closeRender();
    }

    private void closeRender() {
        if (this.muxer != null) {
            try {
                this.muxer.stop();
                this.muxer.release();
                this.muxer = null;
            } catch (Exception e) {
                if (!this.zeroFramesReceived) {
                    throw new RuntimeException("Failed to close the render.", e);
                }
            }
        }
    }
}
