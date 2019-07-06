package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.upstream.HttpDataSource.InvalidResponseCodeException;

public final class ChunkedTrackBlacklistUtil {
    public static boolean shouldBlacklist(Exception exc) {
        boolean z = false;
        if (!(exc instanceof InvalidResponseCodeException)) {
            return false;
        }
        int i = ((InvalidResponseCodeException) exc).responseCode;
        if (i == 404 || i == 410) {
            z = true;
        }
        return z;
    }
}
