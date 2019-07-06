package org.m4m.domain.pipeline;

import org.m4m.domain.Frame;
import org.m4m.domain.ICameraSource;
import org.m4m.domain.ICommandHandler;
import org.m4m.domain.MediaCodecPlugin;

class CaptureSourcePullSurfaceCommandHandler implements ICommandHandler {
    private MediaCodecPlugin plugin;
    private ICameraSource source;

    public CaptureSourcePullSurfaceCommandHandler(ICameraSource iCameraSource, MediaCodecPlugin mediaCodecPlugin) {
        this.source = iCameraSource;
        this.plugin = mediaCodecPlugin;
    }

    public void handle() {
        Frame frame = this.source.getFrame();
        if (!frame.equals((Object) Frame.EOF()) && frame.getLength() != 0) {
            this.plugin.notifySurfaceReady(this.source.getSurface());
        }
        this.plugin.push(frame);
        this.plugin.checkIfOutputQueueHasData();
    }
}
