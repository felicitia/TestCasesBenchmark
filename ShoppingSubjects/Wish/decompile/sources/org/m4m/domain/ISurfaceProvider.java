package org.m4m.domain;

interface ISurfaceProvider {
    ISurface getSurface();

    void waitForSurface(long j);
}
