package org.m4m.domain;

import org.m4m.AudioFormat;

public class AudioEncoder extends Encoder {
    private AudioFormat inputAudioFormat;
    private Resampler resampler = null;

    /* access modifiers changed from: protected */
    public void initInputCommandQueue() {
    }

    public void setOutputSurface(ISurface iSurface) {
    }

    public void waitForSurface(long j) {
    }

    public AudioEncoder(IMediaCodec iMediaCodec) {
        super(iMediaCodec);
    }

    public void setMediaFormat(MediaFormat mediaFormat) {
        this.mediaFormat = mediaFormat;
    }

    public void setInputMediaFormat(MediaFormat mediaFormat) {
        AudioFormat audioFormat = (AudioFormat) mediaFormat;
        this.inputAudioFormat = audioFormat;
        if (this.resampler != null) {
            this.resampler.setInputParameters(this.inputAudioFormat);
        } else if (this.inputAudioFormat.getAudioSampleRateInHz() != audioFormat.getAudioSampleRateInHz() && this.inputAudioFormat.getAudioChannelCount() != audioFormat.getAudioSampleRateInHz()) {
            throw new UnsupportedOperationException("");
        }
    }

    public void drain(int i) {
        if (this.state == PluginState.Normal) {
            super.drain(i);
        }
    }

    public void push(Frame frame) {
        if (frame.equals((Object) Frame.EOF())) {
            this.mediaCodec.queueInputBuffer(frame.getBufferIndex(), 0, 0, frame.getSampleTime(), frame.getFlags());
            checkIfOutputQueueHasData();
        } else if (!frame.equals((Object) Frame.empty())) {
            if (this.resampler != null) {
                resampleAudioFrame(frame);
            }
            this.mediaCodec.queueInputBuffer(frame.getBufferIndex(), 0, frame.getLength(), frame.getSampleTime(), 0);
            checkIfOutputQueueHasData();
        }
        super.push(frame);
    }

    public void resampleAudioFrame(Frame frame) {
        this.resampler.resampleFrame(frame);
    }
}
