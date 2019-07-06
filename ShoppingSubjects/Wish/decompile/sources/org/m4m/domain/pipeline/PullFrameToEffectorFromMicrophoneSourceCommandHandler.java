package org.m4m.domain.pipeline;

import org.m4m.domain.Command;
import org.m4m.domain.Frame;
import org.m4m.domain.ICommandHandler;
import org.m4m.domain.IMicrophoneSource;
import org.m4m.domain.MediaCodecPlugin;

public class PullFrameToEffectorFromMicrophoneSourceCommandHandler implements ICommandHandler {
    private final MediaCodecPlugin input;
    private final IMicrophoneSource output;

    public PullFrameToEffectorFromMicrophoneSourceCommandHandler(IMicrophoneSource iMicrophoneSource, MediaCodecPlugin mediaCodecPlugin) {
        this.output = iMicrophoneSource;
        this.input = mediaCodecPlugin;
    }

    public void handle() {
        Frame findFreeFrame = this.input.findFreeFrame();
        if (findFreeFrame == null) {
            restoreCommands();
            return;
        }
        this.output.getOutputCommandQueue().queue(Command.NextPair, Integer.valueOf(this.input.getTrackId()));
        this.output.pull(findFreeFrame);
        this.input.push(findFreeFrame);
    }

    private void restoreCommands() {
        this.output.getOutputCommandQueue().queue(Command.HasData, Integer.valueOf(0));
        this.input.getInputCommandQueue().clear();
        this.input.skipProcessing();
        this.input.getInputCommandQueue().queue(Command.NeedData, Integer.valueOf(0));
    }
}
