package org.m4m.domain.pipeline;

import org.m4m.domain.Frame;
import org.m4m.domain.ICommandHandler;
import org.m4m.domain.IInput;

class SkipOutputFormatChangeCommandHandler implements ICommandHandler {
    private IInput encoder;

    public SkipOutputFormatChangeCommandHandler(IInput iInput) {
        this.encoder = iInput;
    }

    public void handle() {
        this.encoder.push(Frame.empty());
    }
}
