package org.m4m.domain.pipeline;

import org.m4m.domain.Command;
import org.m4m.domain.ICommandHandler;
import org.m4m.domain.IVideoOutput;
import org.m4m.domain.Plugin;

class ConfigureVideoEffectorCommandHandler implements ICommandHandler {
    private final Plugin input;
    private final IVideoOutput output;

    public ConfigureVideoEffectorCommandHandler(IVideoOutput iVideoOutput, Plugin plugin) {
        this.output = iVideoOutput;
        this.input = plugin;
    }

    public void handle() {
        this.output.getOutputCommandQueue().queue(Command.OutputFormatChanged, Integer.valueOf(this.input.getTrackId()));
        this.input.getInputCommandQueue().queue(Command.NeedData, Integer.valueOf(this.input.getTrackId()));
        this.input.setInputResolution(this.output.getOutputResolution());
    }
}
