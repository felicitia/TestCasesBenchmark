package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioProcessor.UnhandledFormatException;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

final class TrimmingAudioProcessor implements AudioProcessor {
    private ByteBuffer buffer = EMPTY_BUFFER;
    private int channelCount = -1;
    private byte[] endBuffer;
    private int endBufferSize;
    private boolean inputEnded;
    private boolean isActive;
    private ByteBuffer outputBuffer = EMPTY_BUFFER;
    private int pendingTrimStartBytes;
    private int sampleRateHz;
    private int trimEndSamples;
    private int trimStartSamples;

    public int getOutputEncoding() {
        return 2;
    }

    public void setTrimSampleCount(int i, int i2) {
        this.trimStartSamples = i;
        this.trimEndSamples = i2;
    }

    public boolean configure(int i, int i2, int i3) throws UnhandledFormatException {
        if (i3 != 2) {
            throw new UnhandledFormatException(i, i2, i3);
        }
        this.channelCount = i2;
        this.sampleRateHz = i;
        this.endBuffer = new byte[(this.trimEndSamples * i2 * 2)];
        this.endBufferSize = 0;
        this.pendingTrimStartBytes = this.trimStartSamples * i2 * 2;
        boolean z = this.isActive;
        this.isActive = (this.trimStartSamples == 0 && this.trimEndSamples == 0) ? false : true;
        if (z != this.isActive) {
            return true;
        }
        return false;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public int getOutputChannelCount() {
        return this.channelCount;
    }

    public int getOutputSampleRateHz() {
        return this.sampleRateHz;
    }

    public void queueInput(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        int i = limit - position;
        int min = Math.min(i, this.pendingTrimStartBytes);
        this.pendingTrimStartBytes -= min;
        byteBuffer.position(position + min);
        if (this.pendingTrimStartBytes <= 0) {
            int i2 = i - min;
            int length = (this.endBufferSize + i2) - this.endBuffer.length;
            if (this.buffer.capacity() < length) {
                this.buffer = ByteBuffer.allocateDirect(length).order(ByteOrder.nativeOrder());
            } else {
                this.buffer.clear();
            }
            int constrainValue = Util.constrainValue(length, 0, this.endBufferSize);
            this.buffer.put(this.endBuffer, 0, constrainValue);
            int constrainValue2 = Util.constrainValue(length - constrainValue, 0, i2);
            byteBuffer.limit(byteBuffer.position() + constrainValue2);
            this.buffer.put(byteBuffer);
            byteBuffer.limit(limit);
            int i3 = i2 - constrainValue2;
            this.endBufferSize -= constrainValue;
            System.arraycopy(this.endBuffer, constrainValue, this.endBuffer, 0, this.endBufferSize);
            byteBuffer.get(this.endBuffer, this.endBufferSize, i3);
            this.endBufferSize += i3;
            this.buffer.flip();
            this.outputBuffer = this.buffer;
        }
    }

    public void queueEndOfStream() {
        this.inputEnded = true;
    }

    public ByteBuffer getOutput() {
        ByteBuffer byteBuffer = this.outputBuffer;
        this.outputBuffer = EMPTY_BUFFER;
        return byteBuffer;
    }

    public boolean isEnded() {
        return this.inputEnded && this.outputBuffer == EMPTY_BUFFER;
    }

    public void flush() {
        this.outputBuffer = EMPTY_BUFFER;
        this.inputEnded = false;
        this.pendingTrimStartBytes = 0;
        this.endBufferSize = 0;
    }

    public void reset() {
        flush();
        this.buffer = EMPTY_BUFFER;
        this.channelCount = -1;
        this.sampleRateHz = -1;
        this.endBuffer = null;
    }
}
