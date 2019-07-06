package org.m4m.domain.pipeline;

import org.m4m.domain.Frame;
import org.m4m.domain.ICommandHandler;
import org.m4m.domain.IPluginOutput;
import org.m4m.domain.Plugin;

class PushSurfaceCommandHandlerForEffector implements ICommandHandler {
    protected final Plugin input;
    protected final IPluginOutput output;

    public PushSurfaceCommandHandlerForEffector(IPluginOutput iPluginOutput, Plugin plugin) {
        this.output = iPluginOutput;
        this.input = plugin;
    }

    public void handle() {
        Frame frame = this.output.getFrame();
        this.output.releaseOutputBuffer(frame.getBufferIndex());
        this.input.push(frame);
        this.input.checkIfOutputQueueHasData();
    }
}
