package org.m4m.android;

import android.media.MediaFormat;

public class MediaFormatTranslator {
    public static MediaFormat from(org.m4m.domain.MediaFormat mediaFormat) {
        if (mediaFormat instanceof VideoFormatAndroid) {
            return ((VideoFormatAndroid) mediaFormat).getNativeFormat();
        }
        if (mediaFormat instanceof AudioFormatAndroid) {
            return ((AudioFormatAndroid) mediaFormat).getNativeFormat();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Please, don't use MediaFormatTranslator function with this type:");
        sb.append(mediaFormat.getClass().toString());
        throw new UnsupportedOperationException(sb.toString());
    }

    public static org.m4m.domain.MediaFormat toDomain(MediaFormat mediaFormat) {
        if (mediaFormat.getString("mime").startsWith("video")) {
            return new VideoFormatAndroid(mediaFormat);
        }
        if (mediaFormat.getString("mime").startsWith("audio")) {
            return new AudioFormatAndroid(mediaFormat);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unrecognized mime type:");
        sb.append(mediaFormat.getString("mime"));
        throw new UnsupportedOperationException(sb.toString());
    }
}
