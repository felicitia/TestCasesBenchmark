package org.m4m.domain.pipeline;

import org.m4m.domain.AudioEncoder;
import org.m4m.domain.Command;
import org.m4m.domain.Frame;
import org.m4m.domain.ICommandHandler;
import org.m4m.domain.IMicrophoneSource;

class MicrophoneSourcePullFrameCommandHandler implements ICommandHandler {
    private AudioEncoder encoder;
    private IMicrophoneSource source;

    public MicrophoneSourcePullFrameCommandHandler(IMicrophoneSource iMicrophoneSource, AudioEncoder audioEncoder) {
        this.source = iMicrophoneSource;
        this.encoder = audioEncoder;
    }

    public void handle() {
        Frame findFreeFrame = this.encoder.findFreeFrame();
        if (findFreeFrame == null) {
            handleNoFreeInputBuffer();
            return;
        }
        this.source.getOutputCommandQueue().queue(Command.NextPair, Integer.valueOf(this.encoder.getTrackId()));
        this.source.pull(findFreeFrame);
        this.encoder.push(findFreeFrame);
        if (!findFreeFrame.equals((Object) Frame.EOF())) {
            this.encoder.checkIfOutputQueueHasData();
        }
    }

    private void handleNoFreeInputBuffer() {
        this.source.getOutputCommandQueue().queue(Command.HasData, Integer.valueOf(this.encoder.getTrackId()));
        this.encoder.skipProcessing();
        this.encoder.getInputCommandQueue().queue(Command.NeedData, Integer.valueOf(this.encoder.getTrackId()));
    }
}
