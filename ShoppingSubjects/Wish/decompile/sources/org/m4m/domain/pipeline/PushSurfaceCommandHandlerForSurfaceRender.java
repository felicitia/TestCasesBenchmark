package org.m4m.domain.pipeline;

import org.m4m.domain.Frame;
import org.m4m.domain.ICommandHandler;
import org.m4m.domain.IPluginOutput;
import org.m4m.domain.SurfaceRender;

public class PushSurfaceCommandHandlerForSurfaceRender implements ICommandHandler {
    private IPluginOutput decoder;
    private SurfaceRender render;

    public PushSurfaceCommandHandlerForSurfaceRender(IPluginOutput iPluginOutput, SurfaceRender surfaceRender) {
        this.decoder = iPluginOutput;
        this.render = surfaceRender;
    }

    public void handle() {
        Frame frame = this.decoder.getFrame();
        if (!frame.equals((Object) Frame.EOF())) {
            this.decoder.releaseOutputBuffer(frame.getBufferIndex());
        }
        this.render.push(frame);
    }
}
