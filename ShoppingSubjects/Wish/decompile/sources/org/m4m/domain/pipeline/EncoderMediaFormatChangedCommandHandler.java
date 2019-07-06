package org.m4m.domain.pipeline;

import org.m4m.domain.ICommandHandler;
import org.m4m.domain.Plugin;
import org.m4m.domain.Render;

class EncoderMediaFormatChangedCommandHandler implements ICommandHandler {
    protected Plugin plugin;
    protected Render render;

    public EncoderMediaFormatChangedCommandHandler(Plugin plugin2, Render render2) {
        this.plugin = plugin2;
        this.render = render2;
    }

    public void handle() {
        this.render.setMediaFormat(this.plugin.getOutputMediaFormat());
        this.plugin.setOutputTrackId(this.render.getTrackIdByMediaFormat(this.plugin.getOutputMediaFormat()));
        this.render.start();
        this.plugin.checkIfOutputQueueHasData();
    }
}
