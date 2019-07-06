package org.m4m.domain.pipeline;

import org.m4m.domain.ICommandHandler;
import org.m4m.domain.Render;

public class DrainRenderCommandHandler implements ICommandHandler {
    protected final Render render;

    public DrainRenderCommandHandler(Render render2) {
        this.render = render2;
    }

    public void handle() {
        this.render.drain(0);
    }
}
