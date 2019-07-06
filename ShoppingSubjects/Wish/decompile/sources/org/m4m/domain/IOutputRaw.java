package org.m4m.domain;

public interface IOutputRaw {
    boolean canConnectFirst(IInputRaw iInputRaw);

    void fillCommandQueues();

    CommandQueue getOutputCommandQueue();
}
