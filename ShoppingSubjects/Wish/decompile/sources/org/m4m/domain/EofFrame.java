package org.m4m.domain;

import java.nio.ByteBuffer;

class EofFrame extends Frame {
    public int hashCode() {
        return 0;
    }

    public EofFrame() {
        super(ByteBuffer.allocate(0), -1, 0, 0, 4, -1);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        return equals((Frame) obj);
    }

    public boolean equals(Frame frame) {
        return (frame.getFlags() & 4) != 0 || frame.getLength() == -1;
    }
}
