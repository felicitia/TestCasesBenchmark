package com.google.android.exoplayer2.source.ads;

import android.net.Uri;
import com.google.android.exoplayer2.util.Assertions;
import java.util.Arrays;

public final class AdPlaybackState {
    public static final AdPlaybackState NONE = new AdPlaybackState(new long[0]);
    public final int adGroupCount;
    public final long[] adGroupTimesUs;
    public final AdGroup[] adGroups;
    public final long adResumePositionUs;
    public final long contentDurationUs;

    public static final class AdGroup {
        public final int count;
        public final long[] durationsUs;
        public final int[] states;
        public final Uri[] uris;

        public AdGroup() {
            this(-1, new int[0], new Uri[0], new long[0]);
        }

        private AdGroup(int i, int[] iArr, Uri[] uriArr, long[] jArr) {
            Assertions.checkArgument(iArr.length == uriArr.length);
            this.count = i;
            this.states = iArr;
            this.uris = uriArr;
            this.durationsUs = jArr;
        }

        public int getFirstAdIndexToPlay() {
            return getNextAdIndexToPlay(-1);
        }

        public int getNextAdIndexToPlay(int i) {
            int i2 = i + 1;
            while (i2 < this.states.length && this.states[i2] != 0 && this.states[i2] != 1) {
                i2++;
            }
            return i2;
        }

        public boolean hasUnplayedAds() {
            return this.count == -1 || getFirstAdIndexToPlay() < this.count;
        }
    }

    public AdPlaybackState(long... jArr) {
        int length = jArr.length;
        this.adGroupCount = length;
        this.adGroupTimesUs = Arrays.copyOf(jArr, length);
        this.adGroups = new AdGroup[length];
        for (int i = 0; i < length; i++) {
            this.adGroups[i] = new AdGroup();
        }
        this.adResumePositionUs = 0;
        this.contentDurationUs = -9223372036854775807L;
    }

    public int getAdGroupIndexForPositionUs(long j) {
        int length = this.adGroupTimesUs.length - 1;
        while (length >= 0 && (this.adGroupTimesUs[length] == Long.MIN_VALUE || this.adGroupTimesUs[length] > j)) {
            length--;
        }
        if (length < 0 || !this.adGroups[length].hasUnplayedAds()) {
            return -1;
        }
        return length;
    }

    public int getAdGroupIndexAfterPositionUs(long j) {
        int i = 0;
        while (i < this.adGroupTimesUs.length && this.adGroupTimesUs[i] != Long.MIN_VALUE && (j >= this.adGroupTimesUs[i] || !this.adGroups[i].hasUnplayedAds())) {
            i++;
        }
        if (i < this.adGroupTimesUs.length) {
            return i;
        }
        return -1;
    }
}
