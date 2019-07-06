package org.m4m.domain.pipeline;

import org.m4m.domain.Command;
import org.m4m.domain.Frame;
import org.m4m.domain.ICommandHandler;
import org.m4m.domain.IFrameAllocator;
import org.m4m.domain.IOutput;
import org.m4m.domain.Plugin;

class EofCommandHandler implements ICommandHandler {
    private IFrameAllocator inputWithAllocator;
    protected IOutput output;
    protected Plugin plugin;

    public EofCommandHandler(IOutput iOutput, Plugin plugin2, IFrameAllocator iFrameAllocator) {
        this.output = iOutput;
        this.plugin = plugin2;
        this.inputWithAllocator = iFrameAllocator;
    }

    public void handle() {
        Frame findFreeFrame = this.inputWithAllocator.findFreeFrame();
        if (findFreeFrame != null) {
            this.plugin.drain(findFreeFrame.getBufferIndex());
        } else {
            handleNoFreeInputBuffer();
        }
    }

    private void handleNoFreeInputBuffer() {
        this.output.getOutputCommandQueue().queue(Command.EndOfFile, Integer.valueOf(this.plugin.getTrackId()));
        this.plugin.skipProcessing();
        this.plugin.getInputCommandQueue().queue(Command.NeedData, Integer.valueOf(this.plugin.getTrackId()));
    }
}
