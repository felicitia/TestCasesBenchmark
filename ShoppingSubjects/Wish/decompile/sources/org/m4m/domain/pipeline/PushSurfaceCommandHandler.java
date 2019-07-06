package org.m4m.domain.pipeline;

import org.m4m.domain.Frame;
import org.m4m.domain.ICommandHandler;
import org.m4m.domain.IPluginOutput;
import org.m4m.domain.MediaCodecPlugin;

class PushSurfaceCommandHandler implements ICommandHandler {
    protected final MediaCodecPlugin input;
    protected final IPluginOutput output;

    public PushSurfaceCommandHandler(IPluginOutput iPluginOutput, MediaCodecPlugin mediaCodecPlugin) {
        this.output = iPluginOutput;
        this.input = mediaCodecPlugin;
    }

    public void handle() {
        Frame frame = this.output.getFrame();
        this.output.releaseOutputBuffer(frame.getBufferIndex());
        if (frame.getLength() != 0) {
            this.output.waitForSurface(frame.getSampleTime());
        }
        if (frame.getLength() != 0) {
            this.input.notifySurfaceReady(this.output.getSurface());
        }
        this.input.push(frame);
        this.input.checkIfOutputQueueHasData();
    }
}
