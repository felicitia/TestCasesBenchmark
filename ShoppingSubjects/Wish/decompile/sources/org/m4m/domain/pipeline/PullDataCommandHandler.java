package org.m4m.domain.pipeline;

import org.m4m.domain.Frame;
import org.m4m.domain.ICommandHandler;
import org.m4m.domain.IPluginOutput;
import org.m4m.domain.Render;

class PullDataCommandHandler implements ICommandHandler {
    protected Render input;
    protected IPluginOutput output;

    public PullDataCommandHandler(IPluginOutput iPluginOutput, Render render) {
        this.input = render;
        this.output = iPluginOutput;
    }

    public void handle() {
        Frame frame = this.output.getFrame();
        this.input.pushWithReleaser(frame, this.output);
        if (Frame.EOF().equals((Object) frame)) {
            this.input.drain(frame.getBufferIndex());
        }
    }
}
