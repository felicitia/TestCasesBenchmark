package org.m4m.domain.pipeline;

import org.m4m.domain.Command;
import org.m4m.domain.ICommandHandler;
import org.m4m.domain.IVideoOutput;
import org.m4m.domain.Plugin;

class ConfigureVideoDecoderCommandHandler implements ICommandHandler {
    private final Plugin input;
    protected final IVideoOutput output;

    public ConfigureVideoDecoderCommandHandler(IVideoOutput iVideoOutput, Plugin plugin) {
        this.output = iVideoOutput;
        this.input = plugin;
    }

    public void handle() {
        this.output.getOutputCommandQueue().queue(Command.HasData, Integer.valueOf(this.input.getTrackId()));
        this.input.setInputResolution(this.output.getOutputResolution());
    }
}
