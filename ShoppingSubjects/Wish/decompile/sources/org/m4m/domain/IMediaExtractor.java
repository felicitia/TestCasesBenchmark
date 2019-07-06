package org.m4m.domain;

import java.nio.ByteBuffer;

public interface IMediaExtractor {
    boolean advance();

    int getRotation();

    int getSampleFlags();

    long getSampleTime();

    int getSampleTrackIndex();

    int getTrackCount();

    MediaFormat getTrackFormat(int i);

    int readSampleData(ByteBuffer byteBuffer);

    void release();

    void seekTo(long j, int i);

    void selectTrack(int i);
}
