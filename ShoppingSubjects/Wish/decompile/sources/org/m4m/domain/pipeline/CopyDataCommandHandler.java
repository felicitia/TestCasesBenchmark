package org.m4m.domain.pipeline;

import org.m4m.domain.Command;
import org.m4m.domain.Frame;
import org.m4m.domain.ICommandHandler;
import org.m4m.domain.IPluginOutput;
import org.m4m.domain.MediaCodecPlugin;

class CopyDataCommandHandler implements ICommandHandler {
    private final MediaCodecPlugin input;
    private final IPluginOutput output;

    public CopyDataCommandHandler(IPluginOutput iPluginOutput, MediaCodecPlugin mediaCodecPlugin) {
        this.output = iPluginOutput;
        this.input = mediaCodecPlugin;
    }

    public void handle() {
        Frame findFreeFrame = this.input.findFreeFrame();
        if (findFreeFrame == null) {
            restoreCommands();
            return;
        }
        Frame frame = this.output.getFrame();
        if (frame != null) {
            findFreeFrame.copyDataFrom(frame);
            this.input.push(findFreeFrame);
            this.output.releaseOutputBuffer(frame.getBufferIndex());
        }
    }

    private void restoreCommands() {
        this.output.getOutputCommandQueue().queue(Command.HasData, Integer.valueOf(0));
        this.input.getInputCommandQueue().clear();
        this.input.skipProcessing();
        this.input.getInputCommandQueue().queue(Command.NeedData, Integer.valueOf(0));
    }
}
