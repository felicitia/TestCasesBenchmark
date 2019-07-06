package org.m4m.domain.pipeline;

import org.m4m.domain.Command;
import org.m4m.domain.Frame;
import org.m4m.domain.ICommandHandler;
import org.m4m.domain.IFrameAllocator;
import org.m4m.domain.IOutput;
import org.m4m.domain.Plugin;

class PushDataCommandHandler implements ICommandHandler {
    private IFrameAllocator inputWithAllocator;
    protected IOutput output;
    protected Plugin plugin;

    public PushDataCommandHandler(IOutput iOutput, Plugin plugin2, IFrameAllocator iFrameAllocator) {
        this.output = iOutput;
        this.plugin = plugin2;
        this.inputWithAllocator = iFrameAllocator;
    }

    public void handle() {
        Frame findFreeFrame = this.inputWithAllocator.findFreeFrame();
        if (findFreeFrame == null) {
            restoreCommands();
            return;
        }
        this.output.pull(findFreeFrame);
        this.plugin.push(findFreeFrame);
        this.plugin.checkIfOutputQueueHasData();
    }

    private void restoreCommands() {
        this.output.getOutputCommandQueue().queue(Command.HasData, Integer.valueOf(this.plugin.getTrackId()));
        this.plugin.skipProcessing();
        this.plugin.getInputCommandQueue().queue(Command.NeedData, Integer.valueOf(this.plugin.getTrackId()));
    }
}
