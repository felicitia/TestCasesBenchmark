package com.google.android.exoplayer2.trackselection;

import android.graphics.Point;
import android.text.TextUtils;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.RendererCapabilities;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelection.Factory;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class DefaultTrackSelector extends MappingTrackSelector {
    private static final int[] NO_TRACKS = new int[0];
    private final Factory adaptiveTrackSelectionFactory;
    private final AtomicReference<Parameters> paramsReference;

    private static final class AudioConfigurationTuple {
        public final int channelCount;
        public final String mimeType;
        public final int sampleRate;

        public AudioConfigurationTuple(int i, int i2, String str) {
            this.channelCount = i;
            this.sampleRate = i2;
            this.mimeType = str;
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            AudioConfigurationTuple audioConfigurationTuple = (AudioConfigurationTuple) obj;
            if (!(this.channelCount == audioConfigurationTuple.channelCount && this.sampleRate == audioConfigurationTuple.sampleRate && TextUtils.equals(this.mimeType, audioConfigurationTuple.mimeType))) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            return (((this.channelCount * 31) + this.sampleRate) * 31) + (this.mimeType != null ? this.mimeType.hashCode() : 0);
        }
    }

    private static final class AudioTrackScore implements Comparable<AudioTrackScore> {
        private final int bitrate;
        private final int channelCount;
        private final int defaultSelectionFlagScore;
        private final int matchLanguageScore;
        private final Parameters parameters;
        private final int sampleRate;
        private final int withinRendererCapabilitiesScore;

        public AudioTrackScore(Format format, Parameters parameters2, int i) {
            this.parameters = parameters2;
            this.withinRendererCapabilitiesScore = DefaultTrackSelector.isSupported(i, false) ? 1 : 0;
            this.matchLanguageScore = DefaultTrackSelector.formatHasLanguage(format, parameters2.preferredAudioLanguage) ? 1 : 0;
            int i2 = 1;
            if ((format.selectionFlags & 1) == 0) {
                i2 = 0;
            }
            this.defaultSelectionFlagScore = i2;
            this.channelCount = format.channelCount;
            this.sampleRate = format.sampleRate;
            this.bitrate = format.bitrate;
        }

        public int compareTo(AudioTrackScore audioTrackScore) {
            if (this.withinRendererCapabilitiesScore != audioTrackScore.withinRendererCapabilitiesScore) {
                return DefaultTrackSelector.compareInts(this.withinRendererCapabilitiesScore, audioTrackScore.withinRendererCapabilitiesScore);
            }
            if (this.matchLanguageScore != audioTrackScore.matchLanguageScore) {
                return DefaultTrackSelector.compareInts(this.matchLanguageScore, audioTrackScore.matchLanguageScore);
            }
            if (this.defaultSelectionFlagScore != audioTrackScore.defaultSelectionFlagScore) {
                return DefaultTrackSelector.compareInts(this.defaultSelectionFlagScore, audioTrackScore.defaultSelectionFlagScore);
            }
            if (this.parameters.forceLowestBitrate) {
                return DefaultTrackSelector.compareInts(audioTrackScore.bitrate, this.bitrate);
            }
            int i = 1;
            if (this.withinRendererCapabilitiesScore != 1) {
                i = -1;
            }
            if (this.channelCount != audioTrackScore.channelCount) {
                return i * DefaultTrackSelector.compareInts(this.channelCount, audioTrackScore.channelCount);
            }
            if (this.sampleRate != audioTrackScore.sampleRate) {
                return i * DefaultTrackSelector.compareInts(this.sampleRate, audioTrackScore.sampleRate);
            }
            return i * DefaultTrackSelector.compareInts(this.bitrate, audioTrackScore.bitrate);
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            AudioTrackScore audioTrackScore = (AudioTrackScore) obj;
            if (!(this.withinRendererCapabilitiesScore == audioTrackScore.withinRendererCapabilitiesScore && this.matchLanguageScore == audioTrackScore.matchLanguageScore && this.defaultSelectionFlagScore == audioTrackScore.defaultSelectionFlagScore && this.channelCount == audioTrackScore.channelCount && this.sampleRate == audioTrackScore.sampleRate && this.bitrate == audioTrackScore.bitrate)) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            return (((((((((this.withinRendererCapabilitiesScore * 31) + this.matchLanguageScore) * 31) + this.defaultSelectionFlagScore) * 31) + this.channelCount) * 31) + this.sampleRate) * 31) + this.bitrate;
        }
    }

    public static final class Parameters {
        public static final Parameters DEFAULT = new Parameters();
        public final boolean allowMixedMimeAdaptiveness;
        public final boolean allowNonSeamlessAdaptiveness;
        public final int disabledTextTrackSelectionFlags;
        public final boolean exceedRendererCapabilitiesIfNecessary;
        public final boolean exceedVideoConstraintsIfNecessary;
        public final boolean forceLowestBitrate;
        public final int maxVideoBitrate;
        public final int maxVideoHeight;
        public final int maxVideoWidth;
        public final String preferredAudioLanguage;
        public final String preferredTextLanguage;
        public final boolean selectUndeterminedTextLanguage;
        public final int viewportHeight;
        public final boolean viewportOrientationMayChange;
        public final int viewportWidth;

        private Parameters() {
            this(null, null, false, 0, false, false, true, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, true, true, Integer.MAX_VALUE, Integer.MAX_VALUE, true);
        }

        private Parameters(String str, String str2, boolean z, int i, boolean z2, boolean z3, boolean z4, int i2, int i3, int i4, boolean z5, boolean z6, int i5, int i6, boolean z7) {
            this.preferredAudioLanguage = Util.normalizeLanguageCode(str);
            this.preferredTextLanguage = Util.normalizeLanguageCode(str2);
            this.selectUndeterminedTextLanguage = z;
            this.disabledTextTrackSelectionFlags = i;
            this.forceLowestBitrate = z2;
            this.allowMixedMimeAdaptiveness = z3;
            this.allowNonSeamlessAdaptiveness = z4;
            this.maxVideoWidth = i2;
            this.maxVideoHeight = i3;
            this.maxVideoBitrate = i4;
            this.exceedVideoConstraintsIfNecessary = z5;
            this.exceedRendererCapabilitiesIfNecessary = z6;
            this.viewportWidth = i5;
            this.viewportHeight = i6;
            this.viewportOrientationMayChange = z7;
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Parameters parameters = (Parameters) obj;
            if (!(this.selectUndeterminedTextLanguage == parameters.selectUndeterminedTextLanguage && this.disabledTextTrackSelectionFlags == parameters.disabledTextTrackSelectionFlags && this.forceLowestBitrate == parameters.forceLowestBitrate && this.allowMixedMimeAdaptiveness == parameters.allowMixedMimeAdaptiveness && this.allowNonSeamlessAdaptiveness == parameters.allowNonSeamlessAdaptiveness && this.maxVideoWidth == parameters.maxVideoWidth && this.maxVideoHeight == parameters.maxVideoHeight && this.exceedVideoConstraintsIfNecessary == parameters.exceedVideoConstraintsIfNecessary && this.exceedRendererCapabilitiesIfNecessary == parameters.exceedRendererCapabilitiesIfNecessary && this.viewportOrientationMayChange == parameters.viewportOrientationMayChange && this.viewportWidth == parameters.viewportWidth && this.viewportHeight == parameters.viewportHeight && this.maxVideoBitrate == parameters.maxVideoBitrate && TextUtils.equals(this.preferredAudioLanguage, parameters.preferredAudioLanguage) && TextUtils.equals(this.preferredTextLanguage, parameters.preferredTextLanguage))) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            return ((((((((((((((((((((((((((((this.selectUndeterminedTextLanguage ? 1 : 0) * true) + this.disabledTextTrackSelectionFlags) * 31) + (this.forceLowestBitrate ? 1 : 0)) * 31) + (this.allowMixedMimeAdaptiveness ? 1 : 0)) * 31) + (this.allowNonSeamlessAdaptiveness ? 1 : 0)) * 31) + this.maxVideoWidth) * 31) + this.maxVideoHeight) * 31) + (this.exceedVideoConstraintsIfNecessary ? 1 : 0)) * 31) + (this.exceedRendererCapabilitiesIfNecessary ? 1 : 0)) * 31) + (this.viewportOrientationMayChange ? 1 : 0)) * 31) + this.viewportWidth) * 31) + this.viewportHeight) * 31) + this.maxVideoBitrate) * 31) + this.preferredAudioLanguage.hashCode()) * 31) + this.preferredTextLanguage.hashCode();
        }
    }

    private static int compareFormatValues(int i, int i2) {
        if (i == -1) {
            return i2 == -1 ? 0 : -1;
        }
        if (i2 == -1) {
            return 1;
        }
        return i - i2;
    }

    /* access modifiers changed from: private */
    public static int compareInts(int i, int i2) {
        if (i > i2) {
            return 1;
        }
        return i2 > i ? -1 : 0;
    }

    protected static boolean isSupported(int i, boolean z) {
        int i2 = i & 7;
        return i2 == 4 || (z && i2 == 3);
    }

    public DefaultTrackSelector() {
        this(null);
    }

    public DefaultTrackSelector(Factory factory) {
        this.adaptiveTrackSelectionFactory = factory;
        this.paramsReference = new AtomicReference<>(Parameters.DEFAULT);
    }

    /* access modifiers changed from: protected */
    public TrackSelection[] selectTracks(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray[] trackGroupArrayArr, int[][][] iArr) throws ExoPlaybackException {
        RendererCapabilities[] rendererCapabilitiesArr2 = rendererCapabilitiesArr;
        int length = rendererCapabilitiesArr2.length;
        TrackSelection[] trackSelectionArr = new TrackSelection[length];
        Parameters parameters = (Parameters) this.paramsReference.get();
        boolean z = false;
        int i = 0;
        boolean z2 = false;
        while (true) {
            boolean z3 = true;
            if (i >= length) {
                break;
            }
            if (2 == rendererCapabilitiesArr2[i].getTrackType()) {
                if (!z) {
                    trackSelectionArr[i] = selectVideoTrack(rendererCapabilitiesArr2[i], trackGroupArrayArr[i], iArr[i], parameters, this.adaptiveTrackSelectionFactory);
                    z = trackSelectionArr[i] != null;
                }
                if (trackGroupArrayArr[i].length <= 0) {
                    z3 = false;
                }
                z2 |= z3;
            }
            i++;
        }
        boolean z4 = false;
        boolean z5 = false;
        for (int i2 = 0; i2 < length; i2++) {
            switch (rendererCapabilitiesArr2[i2].getTrackType()) {
                case 1:
                    if (z4) {
                        break;
                    } else {
                        trackSelectionArr[i2] = selectAudioTrack(trackGroupArrayArr[i2], iArr[i2], parameters, z2 ? null : this.adaptiveTrackSelectionFactory);
                        if (trackSelectionArr[i2] == null) {
                            z4 = false;
                            break;
                        } else {
                            z4 = true;
                            break;
                        }
                    }
                case 2:
                    break;
                case 3:
                    if (z5) {
                        break;
                    } else {
                        trackSelectionArr[i2] = selectTextTrack(trackGroupArrayArr[i2], iArr[i2], parameters);
                        if (trackSelectionArr[i2] == null) {
                            z5 = false;
                            break;
                        } else {
                            z5 = true;
                            break;
                        }
                    }
                default:
                    trackSelectionArr[i2] = selectOtherTrack(rendererCapabilitiesArr2[i2].getTrackType(), trackGroupArrayArr[i2], iArr[i2], parameters);
                    break;
            }
        }
        return trackSelectionArr;
    }

    /* access modifiers changed from: protected */
    public TrackSelection selectVideoTrack(RendererCapabilities rendererCapabilities, TrackGroupArray trackGroupArray, int[][] iArr, Parameters parameters, Factory factory) throws ExoPlaybackException {
        TrackSelection selectAdaptiveVideoTrack = (parameters.forceLowestBitrate || factory == null) ? null : selectAdaptiveVideoTrack(rendererCapabilities, trackGroupArray, iArr, parameters, factory);
        return selectAdaptiveVideoTrack == null ? selectFixedVideoTrack(trackGroupArray, iArr, parameters) : selectAdaptiveVideoTrack;
    }

    private static TrackSelection selectAdaptiveVideoTrack(RendererCapabilities rendererCapabilities, TrackGroupArray trackGroupArray, int[][] iArr, Parameters parameters, Factory factory) throws ExoPlaybackException {
        TrackGroupArray trackGroupArray2 = trackGroupArray;
        Parameters parameters2 = parameters;
        int i = parameters2.allowNonSeamlessAdaptiveness ? 24 : 16;
        boolean z = parameters2.allowMixedMimeAdaptiveness && (rendererCapabilities.supportsMixedMimeTypeAdaptation() & i) != 0;
        for (int i2 = 0; i2 < trackGroupArray2.length; i2++) {
            TrackGroup trackGroup = trackGroupArray2.get(i2);
            int[] adaptiveVideoTracksForGroup = getAdaptiveVideoTracksForGroup(trackGroup, iArr[i2], z, i, parameters2.maxVideoWidth, parameters2.maxVideoHeight, parameters2.maxVideoBitrate, parameters2.viewportWidth, parameters2.viewportHeight, parameters2.viewportOrientationMayChange);
            if (adaptiveVideoTracksForGroup.length > 0) {
                return factory.createTrackSelection(trackGroup, adaptiveVideoTracksForGroup);
            }
            Factory factory2 = factory;
        }
        return null;
    }

    private static int[] getAdaptiveVideoTracksForGroup(TrackGroup trackGroup, int[] iArr, boolean z, int i, int i2, int i3, int i4, int i5, int i6, boolean z2) {
        String str;
        TrackGroup trackGroup2 = trackGroup;
        if (trackGroup2.length < 2) {
            return NO_TRACKS;
        }
        List viewportFilteredTrackIndices = getViewportFilteredTrackIndices(trackGroup2, i5, i6, z2);
        if (viewportFilteredTrackIndices.size() < 2) {
            return NO_TRACKS;
        }
        if (!z) {
            HashSet hashSet = new HashSet();
            String str2 = null;
            int i7 = 0;
            for (int i8 = 0; i8 < viewportFilteredTrackIndices.size(); i8++) {
                String str3 = trackGroup2.getFormat(((Integer) viewportFilteredTrackIndices.get(i8)).intValue()).sampleMimeType;
                if (hashSet.add(str3)) {
                    int adaptiveVideoTrackCountForMimeType = getAdaptiveVideoTrackCountForMimeType(trackGroup2, iArr, i, str3, i2, i3, i4, viewportFilteredTrackIndices);
                    if (adaptiveVideoTrackCountForMimeType > i7) {
                        i7 = adaptiveVideoTrackCountForMimeType;
                        str2 = str3;
                    }
                }
            }
            str = str2;
        } else {
            str = null;
        }
        filterAdaptiveVideoTrackCountForMimeType(trackGroup2, iArr, i, str, i2, i3, i4, viewportFilteredTrackIndices);
        return viewportFilteredTrackIndices.size() < 2 ? NO_TRACKS : Util.toArray(viewportFilteredTrackIndices);
    }

    private static int getAdaptiveVideoTrackCountForMimeType(TrackGroup trackGroup, int[] iArr, int i, String str, int i2, int i3, int i4, List<Integer> list) {
        int i5 = 0;
        for (int i6 = 0; i6 < list.size(); i6++) {
            int intValue = ((Integer) list.get(i6)).intValue();
            if (isSupportedAdaptiveVideoTrack(trackGroup.getFormat(intValue), str, iArr[intValue], i, i2, i3, i4)) {
                i5++;
            }
        }
        return i5;
    }

    private static void filterAdaptiveVideoTrackCountForMimeType(TrackGroup trackGroup, int[] iArr, int i, String str, int i2, int i3, int i4, List<Integer> list) {
        List<Integer> list2 = list;
        for (int size = list.size() - 1; size >= 0; size--) {
            int intValue = ((Integer) list2.get(size)).intValue();
            if (!isSupportedAdaptiveVideoTrack(trackGroup.getFormat(intValue), str, iArr[intValue], i, i2, i3, i4)) {
                list2.remove(size);
            }
        }
    }

    private static boolean isSupportedAdaptiveVideoTrack(Format format, String str, int i, int i2, int i3, int i4, int i5) {
        if (!isSupported(i, false) || (i & i2) == 0) {
            return false;
        }
        if (str != null && !Util.areEqual(format.sampleMimeType, str)) {
            return false;
        }
        if (format.width != -1 && format.width > i3) {
            return false;
        }
        if (format.height != -1 && format.height > i4) {
            return false;
        }
        if (format.bitrate == -1 || format.bitrate <= i5) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0092, code lost:
        if (compareFormatValues(r2.bitrate, r10) < 0) goto L_0x0094;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.exoplayer2.trackselection.TrackSelection selectFixedVideoTrack(com.google.android.exoplayer2.source.TrackGroupArray r21, int[][] r22, com.google.android.exoplayer2.trackselection.DefaultTrackSelector.Parameters r23) {
        /*
            r0 = r21
            r1 = r23
            r3 = -1
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = -1
            r10 = -1
        L_0x000b:
            int r11 = r0.length
            if (r5 >= r11) goto L_0x00db
            com.google.android.exoplayer2.source.TrackGroup r11 = r0.get(r5)
            int r12 = r1.viewportWidth
            int r13 = r1.viewportHeight
            boolean r14 = r1.viewportOrientationMayChange
            java.util.List r12 = getViewportFilteredTrackIndices(r11, r12, r13, r14)
            r14 = r22[r5]
            r15 = r10
            r10 = r9
            r9 = r8
            r8 = r7
            r7 = r6
            r6 = 0
        L_0x0025:
            int r2 = r11.length
            if (r6 >= r2) goto L_0x00cc
            r2 = r14[r6]
            boolean r4 = r1.exceedRendererCapabilitiesIfNecessary
            boolean r2 = isSupported(r2, r4)
            if (r2 == 0) goto L_0x00c1
            com.google.android.exoplayer2.Format r2 = r11.getFormat(r6)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r6)
            boolean r4 = r12.contains(r4)
            r18 = 1
            if (r4 == 0) goto L_0x0065
            int r4 = r2.width
            if (r4 == r3) goto L_0x004d
            int r4 = r2.width
            int r3 = r1.maxVideoWidth
            if (r4 > r3) goto L_0x0065
        L_0x004d:
            int r3 = r2.height
            r4 = -1
            if (r3 == r4) goto L_0x0058
            int r3 = r2.height
            int r4 = r1.maxVideoHeight
            if (r3 > r4) goto L_0x0065
        L_0x0058:
            int r3 = r2.bitrate
            r4 = -1
            if (r3 == r4) goto L_0x0063
            int r3 = r2.bitrate
            int r4 = r1.maxVideoBitrate
            if (r3 > r4) goto L_0x0065
        L_0x0063:
            r3 = 1
            goto L_0x0066
        L_0x0065:
            r3 = 0
        L_0x0066:
            if (r3 != 0) goto L_0x006d
            boolean r4 = r1.exceedVideoConstraintsIfNecessary
            if (r4 != 0) goto L_0x006d
            goto L_0x00c1
        L_0x006d:
            if (r3 == 0) goto L_0x0071
            r4 = 2
            goto L_0x0072
        L_0x0071:
            r4 = 1
        L_0x0072:
            r0 = r14[r6]
            r19 = r8
            r8 = 0
            boolean r0 = isSupported(r0, r8)
            if (r0 == 0) goto L_0x007f
            int r4 = r4 + 1000
        L_0x007f:
            if (r4 <= r9) goto L_0x0084
            r17 = 1
            goto L_0x0086
        L_0x0084:
            r17 = 0
        L_0x0086:
            if (r4 != r9) goto L_0x00b5
            boolean r8 = r1.forceLowestBitrate
            if (r8 == 0) goto L_0x009a
            int r0 = r2.bitrate
            int r0 = compareFormatValues(r0, r10)
            if (r0 >= 0) goto L_0x0097
        L_0x0094:
            r17 = 1
            goto L_0x00b5
        L_0x0097:
            r17 = 0
            goto L_0x00b5
        L_0x009a:
            int r8 = r2.getPixelCount()
            if (r8 == r15) goto L_0x00a5
            int r8 = compareFormatValues(r8, r15)
            goto L_0x00ab
        L_0x00a5:
            int r8 = r2.bitrate
            int r8 = compareFormatValues(r8, r10)
        L_0x00ab:
            if (r0 == 0) goto L_0x00b2
            if (r3 == 0) goto L_0x00b2
            if (r8 <= 0) goto L_0x0097
            goto L_0x0094
        L_0x00b2:
            if (r8 >= 0) goto L_0x0097
            goto L_0x0094
        L_0x00b5:
            if (r17 == 0) goto L_0x00c3
            int r10 = r2.bitrate
            int r15 = r2.getPixelCount()
            r9 = r4
            r8 = r6
            r7 = r11
            goto L_0x00c5
        L_0x00c1:
            r19 = r8
        L_0x00c3:
            r8 = r19
        L_0x00c5:
            int r6 = r6 + 1
            r0 = r21
            r3 = -1
            goto L_0x0025
        L_0x00cc:
            r19 = r8
            int r5 = r5 + 1
            r6 = r7
            r8 = r9
            r9 = r10
            r10 = r15
            r7 = r19
            r0 = r21
            r3 = -1
            goto L_0x000b
        L_0x00db:
            if (r6 != 0) goto L_0x00e0
            r16 = 0
            goto L_0x00e7
        L_0x00e0:
            com.google.android.exoplayer2.trackselection.FixedTrackSelection r2 = new com.google.android.exoplayer2.trackselection.FixedTrackSelection
            r2.<init>(r6, r7)
            r16 = r2
        L_0x00e7:
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.trackselection.DefaultTrackSelector.selectFixedVideoTrack(com.google.android.exoplayer2.source.TrackGroupArray, int[][], com.google.android.exoplayer2.trackselection.DefaultTrackSelector$Parameters):com.google.android.exoplayer2.trackselection.TrackSelection");
    }

    /* access modifiers changed from: protected */
    public TrackSelection selectAudioTrack(TrackGroupArray trackGroupArray, int[][] iArr, Parameters parameters, Factory factory) throws ExoPlaybackException {
        TrackGroupArray trackGroupArray2 = trackGroupArray;
        Parameters parameters2 = parameters;
        Factory factory2 = factory;
        AudioTrackScore audioTrackScore = null;
        int i = 0;
        int i2 = -1;
        int i3 = -1;
        while (i < trackGroupArray2.length) {
            TrackGroup trackGroup = trackGroupArray2.get(i);
            int[] iArr2 = iArr[i];
            int i4 = i3;
            AudioTrackScore audioTrackScore2 = audioTrackScore;
            int i5 = i2;
            for (int i6 = 0; i6 < trackGroup.length; i6++) {
                if (isSupported(iArr2[i6], parameters2.exceedRendererCapabilitiesIfNecessary)) {
                    AudioTrackScore audioTrackScore3 = new AudioTrackScore(trackGroup.getFormat(i6), parameters2, iArr2[i6]);
                    if (audioTrackScore2 == null || audioTrackScore3.compareTo(audioTrackScore2) > 0) {
                        i5 = i;
                        i4 = i6;
                        audioTrackScore2 = audioTrackScore3;
                    }
                }
            }
            i++;
            i2 = i5;
            audioTrackScore = audioTrackScore2;
            i3 = i4;
        }
        if (i2 == -1) {
            return null;
        }
        TrackGroup trackGroup2 = trackGroupArray2.get(i2);
        if (!parameters2.forceLowestBitrate && factory2 != null) {
            int[] adaptiveAudioTracks = getAdaptiveAudioTracks(trackGroup2, iArr[i2], parameters2.allowMixedMimeAdaptiveness);
            if (adaptiveAudioTracks.length > 0) {
                return factory2.createTrackSelection(trackGroup2, adaptiveAudioTracks);
            }
        }
        return new FixedTrackSelection(trackGroup2, i3);
    }

    private static int[] getAdaptiveAudioTracks(TrackGroup trackGroup, int[] iArr, boolean z) {
        HashSet hashSet = new HashSet();
        AudioConfigurationTuple audioConfigurationTuple = null;
        int i = 0;
        for (int i2 = 0; i2 < trackGroup.length; i2++) {
            Format format = trackGroup.getFormat(i2);
            AudioConfigurationTuple audioConfigurationTuple2 = new AudioConfigurationTuple(format.channelCount, format.sampleRate, z ? null : format.sampleMimeType);
            if (hashSet.add(audioConfigurationTuple2)) {
                int adaptiveAudioTrackCount = getAdaptiveAudioTrackCount(trackGroup, iArr, audioConfigurationTuple2);
                if (adaptiveAudioTrackCount > i) {
                    i = adaptiveAudioTrackCount;
                    audioConfigurationTuple = audioConfigurationTuple2;
                }
            }
        }
        if (i <= 1) {
            return NO_TRACKS;
        }
        int[] iArr2 = new int[i];
        int i3 = 0;
        for (int i4 = 0; i4 < trackGroup.length; i4++) {
            if (isSupportedAdaptiveAudioTrack(trackGroup.getFormat(i4), iArr[i4], audioConfigurationTuple)) {
                int i5 = i3 + 1;
                iArr2[i3] = i4;
                i3 = i5;
            }
        }
        return iArr2;
    }

    private static int getAdaptiveAudioTrackCount(TrackGroup trackGroup, int[] iArr, AudioConfigurationTuple audioConfigurationTuple) {
        int i = 0;
        for (int i2 = 0; i2 < trackGroup.length; i2++) {
            if (isSupportedAdaptiveAudioTrack(trackGroup.getFormat(i2), iArr[i2], audioConfigurationTuple)) {
                i++;
            }
        }
        return i;
    }

    private static boolean isSupportedAdaptiveAudioTrack(Format format, int i, AudioConfigurationTuple audioConfigurationTuple) {
        if (!isSupported(i, false) || format.channelCount != audioConfigurationTuple.channelCount || format.sampleRate != audioConfigurationTuple.sampleRate) {
            return false;
        }
        if (audioConfigurationTuple.mimeType == null || TextUtils.equals(audioConfigurationTuple.mimeType, format.sampleMimeType)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public TrackSelection selectTextTrack(TrackGroupArray trackGroupArray, int[][] iArr, Parameters parameters) throws ExoPlaybackException {
        int i;
        TrackGroupArray trackGroupArray2 = trackGroupArray;
        Parameters parameters2 = parameters;
        int i2 = 0;
        TrackGroup trackGroup = null;
        int i3 = 0;
        int i4 = 0;
        while (i2 < trackGroupArray2.length) {
            TrackGroup trackGroup2 = trackGroupArray2.get(i2);
            int[] iArr2 = iArr[i2];
            int i5 = i4;
            int i6 = i3;
            TrackGroup trackGroup3 = trackGroup;
            for (int i7 = 0; i7 < trackGroup2.length; i7++) {
                if (isSupported(iArr2[i7], parameters2.exceedRendererCapabilitiesIfNecessary)) {
                    Format format = trackGroup2.getFormat(i7);
                    int i8 = format.selectionFlags & (parameters2.disabledTextTrackSelectionFlags ^ -1);
                    boolean z = (i8 & 1) != 0;
                    boolean z2 = (i8 & 2) != 0;
                    boolean formatHasLanguage = formatHasLanguage(format, parameters2.preferredTextLanguage);
                    if (formatHasLanguage || (parameters2.selectUndeterminedTextLanguage && formatHasNoLanguage(format))) {
                        int i9 = z ? 8 : !z2 ? 6 : 4;
                        i = i9 + (formatHasLanguage ? 1 : 0);
                    } else if (z) {
                        i = 3;
                    } else if (z2) {
                        i = formatHasLanguage(format, parameters2.preferredAudioLanguage) ? 2 : 1;
                    }
                    if (isSupported(iArr2[i7], false)) {
                        i += 1000;
                    }
                    if (i > i5) {
                        i6 = i7;
                        trackGroup3 = trackGroup2;
                        i5 = i;
                    }
                }
            }
            i2++;
            trackGroup = trackGroup3;
            i3 = i6;
            i4 = i5;
        }
        if (trackGroup == null) {
            return null;
        }
        return new FixedTrackSelection(trackGroup, i3);
    }

    /* access modifiers changed from: protected */
    public TrackSelection selectOtherTrack(int i, TrackGroupArray trackGroupArray, int[][] iArr, Parameters parameters) throws ExoPlaybackException {
        TrackGroup trackGroup = null;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < trackGroupArray.length) {
            TrackGroup trackGroup2 = trackGroupArray.get(i2);
            int[] iArr2 = iArr[i2];
            int i5 = i4;
            int i6 = i3;
            TrackGroup trackGroup3 = trackGroup;
            for (int i7 = 0; i7 < trackGroup2.length; i7++) {
                if (isSupported(iArr2[i7], parameters.exceedRendererCapabilitiesIfNecessary)) {
                    int i8 = 1;
                    if ((trackGroup2.getFormat(i7).selectionFlags & 1) != 0) {
                        i8 = 2;
                    }
                    if (isSupported(iArr2[i7], false)) {
                        i8 += 1000;
                    }
                    if (i8 > i5) {
                        i6 = i7;
                        trackGroup3 = trackGroup2;
                        i5 = i8;
                    }
                }
            }
            i2++;
            trackGroup = trackGroup3;
            i3 = i6;
            i4 = i5;
        }
        if (trackGroup == null) {
            return null;
        }
        return new FixedTrackSelection(trackGroup, i3);
    }

    protected static boolean formatHasNoLanguage(Format format) {
        return TextUtils.isEmpty(format.language) || formatHasLanguage(format, "und");
    }

    protected static boolean formatHasLanguage(Format format, String str) {
        return str != null && TextUtils.equals(str, Util.normalizeLanguageCode(format.language));
    }

    private static List<Integer> getViewportFilteredTrackIndices(TrackGroup trackGroup, int i, int i2, boolean z) {
        ArrayList arrayList = new ArrayList(trackGroup.length);
        for (int i3 = 0; i3 < trackGroup.length; i3++) {
            arrayList.add(Integer.valueOf(i3));
        }
        if (i == Integer.MAX_VALUE || i2 == Integer.MAX_VALUE) {
            return arrayList;
        }
        int i4 = Integer.MAX_VALUE;
        for (int i5 = 0; i5 < trackGroup.length; i5++) {
            Format format = trackGroup.getFormat(i5);
            if (format.width > 0 && format.height > 0) {
                Point maxVideoSizeInViewport = getMaxVideoSizeInViewport(z, i, i2, format.width, format.height);
                int i6 = format.width * format.height;
                if (format.width >= ((int) (((float) maxVideoSizeInViewport.x) * 0.98f)) && format.height >= ((int) (((float) maxVideoSizeInViewport.y) * 0.98f)) && i6 < i4) {
                    i4 = i6;
                }
            }
        }
        if (i4 != Integer.MAX_VALUE) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                int pixelCount = trackGroup.getFormat(((Integer) arrayList.get(size)).intValue()).getPixelCount();
                if (pixelCount == -1 || pixelCount > i4) {
                    arrayList.remove(size);
                }
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000c, code lost:
        if (r1 != r3) goto L_0x0012;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Point getMaxVideoSizeInViewport(boolean r3, int r4, int r5, int r6, int r7) {
        /*
            if (r3 == 0) goto L_0x000f
            r3 = 0
            r0 = 1
            if (r6 <= r7) goto L_0x0008
            r1 = 1
            goto L_0x0009
        L_0x0008:
            r1 = 0
        L_0x0009:
            if (r4 <= r5) goto L_0x000c
            r3 = 1
        L_0x000c:
            if (r1 == r3) goto L_0x000f
            goto L_0x0012
        L_0x000f:
            r2 = r5
            r5 = r4
            r4 = r2
        L_0x0012:
            int r3 = r6 * r4
            int r0 = r7 * r5
            if (r3 < r0) goto L_0x0022
            android.graphics.Point r3 = new android.graphics.Point
            int r4 = com.google.android.exoplayer2.util.Util.ceilDivide(r0, r6)
            r3.<init>(r5, r4)
            return r3
        L_0x0022:
            android.graphics.Point r5 = new android.graphics.Point
            int r3 = com.google.android.exoplayer2.util.Util.ceilDivide(r3, r7)
            r5.<init>(r3, r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.trackselection.DefaultTrackSelector.getMaxVideoSizeInViewport(boolean, int, int, int, int):android.graphics.Point");
    }
}
