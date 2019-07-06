package org.m4m.domain;

import java.nio.ByteBuffer;
import org.m4m.domain.IMediaCodec.BufferInfo;

public interface IMediaMuxer {
    int addTrack(MediaFormat mediaFormat);

    void release();

    void start();

    void stop();

    void writeSampleData(int i, ByteBuffer byteBuffer, BufferInfo bufferInfo);
}
