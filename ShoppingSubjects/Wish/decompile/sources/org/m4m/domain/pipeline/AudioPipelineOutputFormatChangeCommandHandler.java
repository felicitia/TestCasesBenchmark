package org.m4m.domain.pipeline;

import org.m4m.domain.Frame;
import org.m4m.domain.ICommandHandler;
import org.m4m.domain.MediaCodecPlugin;

/* compiled from: AudioOutputFormatChangedCommandHadler */
class AudioPipelineOutputFormatChangeCommandHandler implements ICommandHandler {
    private MediaCodecPlugin input;
    private MediaCodecPlugin output;

    public AudioPipelineOutputFormatChangeCommandHandler(MediaCodecPlugin mediaCodecPlugin, MediaCodecPlugin mediaCodecPlugin2) {
        this.output = mediaCodecPlugin;
        this.input = mediaCodecPlugin2;
    }

    public void handle() {
        this.input.setInputMediaFormat(this.output.getOutputMediaFormat());
        this.input.push(Frame.empty());
    }
}
