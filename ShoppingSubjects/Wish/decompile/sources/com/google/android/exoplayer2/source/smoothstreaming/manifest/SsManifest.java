package com.google.android.exoplayer2.source.smoothstreaming.manifest;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.Util;
import java.util.List;
import java.util.UUID;

public class SsManifest {
    public final long durationUs;
    public final long dvrWindowLengthUs;
    public final boolean isLive;
    public final int lookAheadCount;
    public final int majorVersion;
    public final int minorVersion;
    public final ProtectionElement protectionElement;
    public final StreamElement[] streamElements;

    public static class ProtectionElement {
        public final byte[] data;
        public final UUID uuid;

        public ProtectionElement(UUID uuid2, byte[] bArr) {
            this.uuid = uuid2;
            this.data = bArr;
        }
    }

    public static class StreamElement {
        private final String baseUri;
        public final int chunkCount;
        private final List<Long> chunkStartTimes;
        private final long[] chunkStartTimesUs;
        private final String chunkTemplate;
        public final int displayHeight;
        public final int displayWidth;
        public final Format[] formats;
        public final String language;
        private final long lastChunkDurationUs;
        public final int maxHeight;
        public final int maxWidth;
        public final String name;
        public final String subType;
        public final long timescale;
        public final int type;

        public StreamElement(String str, String str2, int i, String str3, long j, String str4, int i2, int i3, int i4, int i5, String str5, Format[] formatArr, List<Long> list, long j2) {
            long j3 = j;
            this(str, str2, i, str3, j3, str4, i2, i3, i4, i5, str5, formatArr, list, Util.scaleLargeTimestamps(list, 1000000, j3), Util.scaleLargeTimestamp(j2, 1000000, j3));
        }

        private StreamElement(String str, String str2, int i, String str3, long j, String str4, int i2, int i3, int i4, int i5, String str5, Format[] formatArr, List<Long> list, long[] jArr, long j2) {
            this.baseUri = str;
            this.chunkTemplate = str2;
            this.type = i;
            this.subType = str3;
            this.timescale = j;
            this.name = str4;
            this.maxWidth = i2;
            this.maxHeight = i3;
            this.displayWidth = i4;
            this.displayHeight = i5;
            this.language = str5;
            this.formats = formatArr;
            this.chunkStartTimes = list;
            this.chunkStartTimesUs = jArr;
            this.lastChunkDurationUs = j2;
            this.chunkCount = list.size();
        }
    }

    public SsManifest(int i, int i2, long j, long j2, long j3, int i3, boolean z, ProtectionElement protectionElement2, StreamElement[] streamElementArr) {
        long j4 = -9223372036854775807L;
        long scaleLargeTimestamp = j2 == 0 ? -9223372036854775807L : Util.scaleLargeTimestamp(j2, 1000000, j);
        if (j3 != 0) {
            j4 = Util.scaleLargeTimestamp(j3, 1000000, j);
        }
        this(i, i2, scaleLargeTimestamp, j4, i3, z, protectionElement2, streamElementArr);
    }

    private SsManifest(int i, int i2, long j, long j2, int i3, boolean z, ProtectionElement protectionElement2, StreamElement[] streamElementArr) {
        this.majorVersion = i;
        this.minorVersion = i2;
        this.durationUs = j;
        this.dvrWindowLengthUs = j2;
        this.lookAheadCount = i3;
        this.isLive = z;
        this.protectionElement = protectionElement2;
        this.streamElements = streamElementArr;
    }
}
