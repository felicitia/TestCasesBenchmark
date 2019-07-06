package org.m4m.android;

import android.media.MediaCodec;
import org.m4m.domain.IMediaCodec.BufferInfo;

public class BufferInfoTranslator {
    public static BufferInfo convertFromAndroid(MediaCodec.BufferInfo bufferInfo, BufferInfo bufferInfo2) {
        bufferInfo2.flags = bufferInfo.flags;
        bufferInfo2.presentationTimeUs = bufferInfo.presentationTimeUs;
        bufferInfo2.offset = bufferInfo.offset;
        bufferInfo2.size = bufferInfo.size;
        return bufferInfo2;
    }
}
