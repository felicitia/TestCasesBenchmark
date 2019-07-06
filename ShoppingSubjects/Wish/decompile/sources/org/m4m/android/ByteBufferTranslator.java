package org.m4m.android;

import android.media.MediaCodec.BufferInfo;
import org.m4m.domain.IMediaCodec;

public class ByteBufferTranslator {
    public static BufferInfo from(IMediaCodec.BufferInfo bufferInfo) {
        BufferInfo bufferInfo2 = new BufferInfo();
        bufferInfo2.flags = bufferInfo.flags;
        bufferInfo2.offset = bufferInfo.offset;
        bufferInfo2.size = bufferInfo.size;
        bufferInfo2.presentationTimeUs = bufferInfo.presentationTimeUs;
        return bufferInfo2;
    }
}
