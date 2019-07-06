package org.m4m.domain;

import org.m4m.AudioFormat;

public class Resampler {
    private boolean configured;
    protected int inputChannelCount;
    protected int inputSampleRate;

    public void setInputParameters(AudioFormat audioFormat) {
        int audioChannelCount = audioFormat.getAudioChannelCount();
        int audioSampleRateInHz = audioFormat.getAudioSampleRateInHz();
        if ((audioChannelCount != 1 && audioChannelCount != 2) || !sampleRateSupported(audioSampleRateInHz)) {
            throw new IllegalArgumentException("Given input audio parameters not supported.");
        } else if (this.inputChannelCount != audioChannelCount || this.inputSampleRate != audioSampleRateInHz) {
            this.inputChannelCount = audioChannelCount;
            this.inputSampleRate = audioSampleRateInHz;
            allocateInitInternalBuffers();
        }
    }

    public void resampleFrame(Frame frame) {
        if (!this.configured) {
            throw new IllegalArgumentException("Resampler not configured.");
        }
    }

    /* access modifiers changed from: protected */
    public void allocateInitInternalBuffers() {
        this.configured = true;
    }

    public boolean sampleRateSupported(int i) {
        for (SampleRate value : SampleRate.values()) {
            if (value.getValue() == i) {
                return true;
            }
        }
        return false;
    }
}
