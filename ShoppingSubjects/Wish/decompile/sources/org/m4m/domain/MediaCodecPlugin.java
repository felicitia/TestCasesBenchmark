package org.m4m.domain;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;
import org.m4m.domain.IMediaCodec.BufferInfo;

public abstract class MediaCodecPlugin extends Plugin {
    protected HashMap<Integer, Frame> bufferIndexToFrame = new HashMap<>();
    protected int frameCount;
    protected Queue<Integer> inputBufferIndexes = new LinkedList();
    protected ByteBuffer[] inputBuffers = null;
    protected final IMediaCodec mediaCodec;
    protected Queue<Integer> outputBufferIndexes = new LinkedList();
    protected Queue<BufferInfo> outputBufferInfos = new LinkedList();
    protected MediaFormat outputMediaFormat = null;
    protected int outputTrackId;
    protected int timeout = 10;

    public MediaCodecPlugin(IMediaCodec iMediaCodec) {
        this.mediaCodec = iMediaCodec;
    }

    public void checkIfOutputQueueHasData() {
        getOutputBufferIndex();
    }

    /* access modifiers changed from: protected */
    public void feedMeIfNotDraining() {
        if (this.state != PluginState.Draining && this.state != PluginState.Drained) {
            int dequeueInputBuffer = this.mediaCodec.dequeueInputBuffer((long) this.timeout);
            if (dequeueInputBuffer >= 0) {
                this.inputBufferIndexes.add(Integer.valueOf(dequeueInputBuffer));
                super.feedMeIfNotDraining();
            } else if (this.inputBufferIndexes.size() > 0) {
                Pair first = getInputCommandQueue().first();
                if (first == null || first.left != Command.NeedData) {
                    super.feedMeIfNotDraining();
                }
            }
        }
    }

    public Frame getFrame() {
        Frame frame;
        feedMeIfNotDraining();
        Integer num = (Integer) this.outputBufferIndexes.poll();
        BufferInfo bufferInfo = (BufferInfo) this.outputBufferInfos.poll();
        if ((this.state == PluginState.Draining || this.state == PluginState.Drained) && num == null) {
            if (getOutputBufferIndex() < 0) {
                return Frame.EOF();
            }
            num = (Integer) this.outputBufferIndexes.poll();
            bufferInfo = (BufferInfo) this.outputBufferInfos.poll();
        }
        if (num == null) {
            return Frame.empty();
        }
        while (isStatusToSkip(num) && this.outputBufferIndexes.size() > 0) {
            num = (Integer) this.outputBufferIndexes.poll();
            bufferInfo = (BufferInfo) this.outputBufferInfos.poll();
        }
        ByteBuffer byteBuffer = this.mediaCodec.getOutputBuffers()[num.intValue()];
        if (this.bufferIndexToFrame.containsKey(num)) {
            frame = (Frame) this.bufferIndexToFrame.get(num);
            frame.set(byteBuffer, bufferInfo.size, bufferInfo.presentationTimeUs, num.intValue(), bufferInfo.flags, this.outputTrackId);
        } else {
            frame = new Frame(byteBuffer, bufferInfo.size, bufferInfo.presentationTimeUs, num.intValue(), bufferInfo.flags, this.outputTrackId);
            this.bufferIndexToFrame.put(num, frame);
            Logger logger = Logger.getLogger("AMP");
            StringBuilder sb = new StringBuilder();
            sb.append("New frame allocated for buffer ");
            sb.append(num);
            logger.info(sb.toString());
        }
        checkIfOutputQueueHasData();
        if (frame.equals((Object) Frame.EOF()) && frame.getSampleTime() < -1) {
            frame.setSampleTime(0);
        }
        return frame;
    }

    /* access modifiers changed from: protected */
    public int getOutputBufferIndex() {
        BufferInfo bufferInfo = new BufferInfo();
        int dequeueOutputBuffer = this.mediaCodec.dequeueOutputBuffer(bufferInfo, (long) this.timeout);
        if (this.state == PluginState.Draining && dequeueOutputBuffer == -1) {
            this.state = PluginState.Drained;
        }
        if (!(dequeueOutputBuffer == -1 || dequeueOutputBuffer == -2)) {
            this.outputBufferIndexes.add(Integer.valueOf(dequeueOutputBuffer));
            this.outputBufferInfos.add(bufferInfo);
        }
        if (dequeueOutputBuffer >= 0) {
            hasData();
        }
        if (bufferInfo.isEof() && this.state != PluginState.Drained) {
            getInputCommandQueue().clear();
            setState(PluginState.Draining);
        }
        if (dequeueOutputBuffer == -2) {
            this.outputMediaFormat = this.mediaCodec.getOutputFormat();
            outputFormatChanged();
        }
        return dequeueOutputBuffer;
    }

    /* access modifiers changed from: protected */
    public boolean isStatusToSkip(Integer num) {
        return num.intValue() == -3 || num.intValue() == -2;
    }

    private void outputFormatChanged() {
        getOutputCommandQueue().queue(Command.OutputFormatChanged, Integer.valueOf(0));
    }

    /* access modifiers changed from: protected */
    public void hasData() {
        getOutputCommandQueue().queue(Command.HasData, Integer.valueOf(0));
    }

    public void drain(int i) {
        super.drain(i);
    }

    public Frame findFreeFrame() {
        if (this.state == PluginState.Draining || this.state == PluginState.Drained) {
            return Frame.EOF();
        }
        if (this.inputBufferIndexes.size() == 0) {
            return null;
        }
        int intValue = ((Integer) this.inputBufferIndexes.poll()).intValue();
        Frame frame = new Frame(this.inputBuffers[intValue], 0, 0, intValue, 0, 0);
        return frame;
    }

    public void setOutputTrackId(int i) {
        this.outputTrackId = i;
    }

    public MediaFormat getOutputMediaFormat() {
        return this.mediaCodec.getOutputFormat();
    }

    public void fillCommandQueues() {
        if (this.state == PluginState.Normal) {
            checkIfOutputQueueHasData();
            feedMeIfNotDraining();
        }
    }

    public void start() {
        this.mediaCodec.start();
        this.inputBuffers = this.mediaCodec.getInputBuffers();
        setState(PluginState.Normal);
    }

    public void stop() {
        setState(PluginState.Paused);
        this.mediaCodec.stop();
    }

    public void close() throws IOException {
        this.mediaCodec.release();
    }

    public void setTimeout(int i) {
        this.timeout = i;
    }

    public void setInputMediaFormat(MediaFormat mediaFormat) {
        this.mediaFormat = mediaFormat;
    }
}
