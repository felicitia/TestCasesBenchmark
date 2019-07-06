package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioProcessor.UnhandledFormatException;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

public final class SonicAudioProcessor implements AudioProcessor {
    private ByteBuffer buffer = EMPTY_BUFFER;
    private int channelCount = -1;
    private long inputBytes;
    private boolean inputEnded;
    private ByteBuffer outputBuffer = EMPTY_BUFFER;
    private long outputBytes;
    private int outputSampleRateHz = -1;
    private int pendingOutputSampleRateHz = -1;
    private float pitch = 1.0f;
    private int sampleRateHz = -1;
    private ShortBuffer shortBuffer = this.buffer.asShortBuffer();
    private Sonic sonic;
    private float speed = 1.0f;

    public int getOutputEncoding() {
        return 2;
    }

    public float setSpeed(float f) {
        this.speed = Util.constrainValue(f, 0.1f, 8.0f);
        return this.speed;
    }

    public float setPitch(float f) {
        this.pitch = Util.constrainValue(f, 0.1f, 8.0f);
        return f;
    }

    public long scaleDurationForSpeedup(long j) {
        long j2;
        if (this.outputBytes < 1024) {
            return (long) (((double) this.speed) * ((double) j));
        }
        if (this.outputSampleRateHz == this.sampleRateHz) {
            j2 = Util.scaleLargeTimestamp(j, this.inputBytes, this.outputBytes);
        } else {
            j2 = Util.scaleLargeTimestamp(j, this.inputBytes * ((long) this.outputSampleRateHz), this.outputBytes * ((long) this.sampleRateHz));
        }
        return j2;
    }

    public boolean configure(int i, int i2, int i3) throws UnhandledFormatException {
        if (i3 != 2) {
            throw new UnhandledFormatException(i, i2, i3);
        }
        int i4 = this.pendingOutputSampleRateHz == -1 ? i : this.pendingOutputSampleRateHz;
        if (this.sampleRateHz == i && this.channelCount == i2 && this.outputSampleRateHz == i4) {
            return false;
        }
        this.sampleRateHz = i;
        this.channelCount = i2;
        this.outputSampleRateHz = i4;
        return true;
    }

    public boolean isActive() {
        return Math.abs(this.speed - 1.0f) >= 0.01f || Math.abs(this.pitch - 1.0f) >= 0.01f || this.outputSampleRateHz != this.sampleRateHz;
    }

    public int getOutputChannelCount() {
        return this.channelCount;
    }

    public int getOutputSampleRateHz() {
        return this.outputSampleRateHz;
    }

    public void queueInput(ByteBuffer byteBuffer) {
        if (byteBuffer.hasRemaining()) {
            ShortBuffer asShortBuffer = byteBuffer.asShortBuffer();
            int remaining = byteBuffer.remaining();
            this.inputBytes += (long) remaining;
            this.sonic.queueInput(asShortBuffer);
            byteBuffer.position(byteBuffer.position() + remaining);
        }
        int samplesAvailable = this.sonic.getSamplesAvailable() * this.channelCount * 2;
        if (samplesAvailable > 0) {
            if (this.buffer.capacity() < samplesAvailable) {
                this.buffer = ByteBuffer.allocateDirect(samplesAvailable).order(ByteOrder.nativeOrder());
                this.shortBuffer = this.buffer.asShortBuffer();
            } else {
                this.buffer.clear();
                this.shortBuffer.clear();
            }
            this.sonic.getOutput(this.shortBuffer);
            this.outputBytes += (long) samplesAvailable;
            this.buffer.limit(samplesAvailable);
            this.outputBuffer = this.buffer;
        }
    }

    public void queueEndOfStream() {
        this.sonic.queueEndOfStream();
        this.inputEnded = true;
    }

    public ByteBuffer getOutput() {
        ByteBuffer byteBuffer = this.outputBuffer;
        this.outputBuffer = EMPTY_BUFFER;
        return byteBuffer;
    }

    public boolean isEnded() {
        return this.inputEnded && (this.sonic == null || this.sonic.getSamplesAvailable() == 0);
    }

    public void flush() {
        Sonic sonic2 = new Sonic(this.sampleRateHz, this.channelCount, this.speed, this.pitch, this.outputSampleRateHz);
        this.sonic = sonic2;
        this.outputBuffer = EMPTY_BUFFER;
        this.inputBytes = 0;
        this.outputBytes = 0;
        this.inputEnded = false;
    }

    public void reset() {
        this.sonic = null;
        this.buffer = EMPTY_BUFFER;
        this.shortBuffer = this.buffer.asShortBuffer();
        this.outputBuffer = EMPTY_BUFFER;
        this.channelCount = -1;
        this.sampleRateHz = -1;
        this.outputSampleRateHz = -1;
        this.inputBytes = 0;
        this.outputBytes = 0;
        this.inputEnded = false;
        this.pendingOutputSampleRateHz = -1;
    }
}
