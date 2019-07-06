package org.m4m.domain.pipeline;

import org.m4m.domain.ICommandHandler;
import org.m4m.domain.MediaCodecPlugin;

class DrainCommandHandler implements ICommandHandler {
    protected final MediaCodecPlugin plugin;

    public DrainCommandHandler(MediaCodecPlugin mediaCodecPlugin) {
        this.plugin = mediaCodecPlugin;
    }

    public void handle() {
        this.plugin.drain(0);
    }
}
