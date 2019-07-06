package com.google.android.exoplayer2.text.webvtt;

import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.regex.Pattern;

public final class WebvttParserUtil {
    private static final Pattern COMMENT = Pattern.compile("^NOTE(( |\t).*)?$");
    private static final Pattern HEADER = Pattern.compile("^﻿?WEBVTT(( |\t).*)?$");

    public static void validateWebvttHeaderLine(ParsableByteArray parsableByteArray) throws SubtitleDecoderException {
        String readLine = parsableByteArray.readLine();
        if (readLine == null || !HEADER.matcher(readLine).matches()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Expected WEBVTT. Got ");
            sb.append(readLine);
            throw new SubtitleDecoderException(sb.toString());
        }
    }

    public static long parseTimestampUs(String str) throws NumberFormatException {
        String[] split = str.split("\\.", 2);
        int i = 0;
        String[] split2 = split[0].split(":");
        long j = 0;
        while (i < split2.length) {
            i++;
            j = (j * 60) + Long.parseLong(split2[i]);
        }
        long j2 = j * 1000;
        if (split.length == 2) {
            j2 += Long.parseLong(split[1]);
        }
        return j2 * 1000;
    }

    public static float parsePercentage(String str) throws NumberFormatException {
        if (str.endsWith("%")) {
            return Float.parseFloat(str.substring(0, str.length() - 1)) / 100.0f;
        }
        throw new NumberFormatException("Percentages must end with %");
    }
}
