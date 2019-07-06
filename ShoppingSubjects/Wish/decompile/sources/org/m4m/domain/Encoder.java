package org.m4m.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Encoder extends MediaCodecPlugin implements ITransform {
    ArrayList<IOnSurfaceReady> listeners = new ArrayList<>();
    private ISurface surface;

    public Encoder(IMediaCodec iMediaCodec) {
        super(iMediaCodec);
        initInputCommandQueue();
    }

    public ISurface getSurface() {
        if (this.surface == null) {
            this.surface = this.mediaCodec.createInputSurface();
            Iterator it = this.listeners.iterator();
            while (it.hasNext()) {
                ((IOnSurfaceReady) it.next()).onSurfaceReady();
            }
        }
        return this.surface;
    }

    public ISurface getSimpleSurface(IEglContext iEglContext) {
        if (this.surface == null) {
            this.surface = this.mediaCodec.createSimpleInputSurface(iEglContext);
        }
        return this.surface;
    }

    public void checkIfOutputQueueHasData() {
        do {
        } while (-1 != getOutputBufferIndex());
    }

    public void push(Frame frame) {
        feedMeIfNotDraining();
    }

    public void configure() {
        this.mediaCodec.configure(this.mediaFormat, null, 1);
    }

    public void pull(Frame frame) {
        throw new UnsupportedOperationException("Unexpected call of pull() in Encoder.");
    }

    public void releaseOutputBuffer(int i) {
        this.mediaCodec.releaseOutputBuffer(i, false);
    }

    public void setTrackId(int i) {
        this.trackId = i;
    }

    public void close() throws IOException {
        super.close();
        if (this.surface != null) {
            this.surface.release();
            this.surface = null;
        }
    }
}
