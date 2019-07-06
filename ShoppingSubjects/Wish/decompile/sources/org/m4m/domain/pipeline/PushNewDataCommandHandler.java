package org.m4m.domain.pipeline;

import java.nio.ByteBuffer;
import org.m4m.domain.Frame;
import org.m4m.domain.ICommandHandler;
import org.m4m.domain.IOutput;
import org.m4m.domain.Render;

class PushNewDataCommandHandler implements ICommandHandler {
    private IOutput output;
    private Render render;

    public PushNewDataCommandHandler(IOutput iOutput, Render render2) {
        this.output = iOutput;
        this.render = render2;
    }

    public void handle() {
        Frame frame = new Frame(ByteBuffer.allocate(1048576), 1048576, 0, 0, 0, 0);
        this.output.pull(frame);
        this.render.push(frame);
    }
}
