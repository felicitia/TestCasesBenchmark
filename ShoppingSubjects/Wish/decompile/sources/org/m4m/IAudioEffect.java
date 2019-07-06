package org.m4m;

import java.nio.ByteBuffer;
import org.m4m.domain.MediaFormat;
import org.m4m.domain.Pair;

public interface IAudioEffect {
    void applyEffect(ByteBuffer byteBuffer, long j);

    MediaFormat getMediaFormat();

    Pair<Long, Long> getSegment();
}
