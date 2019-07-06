package org.m4m.domain.pipeline;

import org.m4m.domain.Command;
import org.m4m.domain.Frame;
import org.m4m.domain.ICommandHandler;
import org.m4m.domain.IFrameAllocator;
import org.m4m.domain.IOutput;
import org.m4m.domain.MultipleMediaSource;
import org.m4m.domain.Plugin;

class OutputFormatChangedHandler implements ICommandHandler {
    private IFrameAllocator inputWithAllocator;
    protected IOutput output;
    protected Plugin plugin;

    public OutputFormatChangedHandler(IOutput iOutput, Plugin plugin2, IFrameAllocator iFrameAllocator) {
        this.output = iOutput;
        this.plugin = plugin2;
        this.inputWithAllocator = iFrameAllocator;
    }

    public void handle() {
        if (this.output instanceof MultipleMediaSource) {
            Frame findFreeFrame = this.inputWithAllocator.findFreeFrame();
            if (findFreeFrame == null) {
                restoreCommands();
                return;
            }
            this.plugin.drain(findFreeFrame.getBufferIndex());
            this.plugin.stop();
            this.plugin.setMediaFormat(this.output.getMediaFormatByType(this.plugin.getMediaFormatType()));
            this.plugin.configure();
            this.plugin.start();
            this.plugin.setTrackId(this.plugin.getTrackId());
            MultipleMediaSource multipleMediaSource = (MultipleMediaSource) this.output;
            int trackIdByMediaType = multipleMediaSource.getTrackIdByMediaType(this.plugin.getMediaFormatType());
            multipleMediaSource.selectTrack(trackIdByMediaType);
            multipleMediaSource.setTrackMap(trackIdByMediaType, this.plugin.getTrackId());
            multipleMediaSource.nextFile();
        }
    }

    private void restoreCommands() {
        this.output.getOutputCommandQueue().queue(Command.OutputFormatChanged, Integer.valueOf(this.plugin.getTrackId()));
        this.plugin.getInputCommandQueue().clear();
        this.plugin.skipProcessing();
        this.plugin.getInputCommandQueue().queue(Command.NeedData, Integer.valueOf(this.plugin.getTrackId()));
    }
}
