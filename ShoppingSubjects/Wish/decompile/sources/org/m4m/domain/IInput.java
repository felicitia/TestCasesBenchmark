package org.m4m.domain;

import java.io.Closeable;

public interface IInput extends Closeable, IInputRaw {
    void push(Frame frame);

    void setMediaFormat(MediaFormat mediaFormat);
}
