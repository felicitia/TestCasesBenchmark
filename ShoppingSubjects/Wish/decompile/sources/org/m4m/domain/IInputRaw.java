package org.m4m.domain;

public interface IInputRaw {
    boolean canConnectFirst(IOutputRaw iOutputRaw);

    void fillCommandQueues();

    CommandQueue getInputCommandQueue();
}
