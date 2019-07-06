package org.m4m.domain;

public abstract class SurfaceRender extends Render {
    public abstract ISurface getSurface();

    public abstract void onSurfaceAvailable(IOnSurfaceReady iOnSurfaceReady);
}
