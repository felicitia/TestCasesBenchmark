package com.google.android.exoplayer2.trackselection;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.RendererCapabilities;
import com.google.android.exoplayer2.RendererConfiguration;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelection.Factory;
import java.util.Arrays;
import java.util.Map;

public abstract class MappingTrackSelector extends TrackSelector {
    private MappedTrackInfo currentMappedTrackInfo;
    private final SparseBooleanArray rendererDisabledFlags = new SparseBooleanArray();
    private final SparseArray<Map<TrackGroupArray, SelectionOverride>> selectionOverrides = new SparseArray<>();
    private int tunnelingAudioSessionId = 0;

    public static final class MappedTrackInfo {
        private final int[][][] formatSupport;
        public final int length;
        private final int[] mixedMimeTypeAdaptiveSupport;
        private final int[] rendererTrackTypes;
        private final TrackGroupArray[] trackGroups;
        private final TrackGroupArray unassociatedTrackGroups;

        MappedTrackInfo(int[] iArr, TrackGroupArray[] trackGroupArrayArr, int[] iArr2, int[][][] iArr3, TrackGroupArray trackGroupArray) {
            this.rendererTrackTypes = iArr;
            this.trackGroups = trackGroupArrayArr;
            this.formatSupport = iArr3;
            this.mixedMimeTypeAdaptiveSupport = iArr2;
            this.unassociatedTrackGroups = trackGroupArray;
            this.length = trackGroupArrayArr.length;
        }
    }

    public static final class SelectionOverride {
        public final Factory factory;
        public final int groupIndex;
        public final int[] tracks;

        public TrackSelection createTrackSelection(TrackGroupArray trackGroupArray) {
            return this.factory.createTrackSelection(trackGroupArray.get(this.groupIndex), this.tracks);
        }
    }

    /* access modifiers changed from: protected */
    public abstract TrackSelection[] selectTracks(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray[] trackGroupArrayArr, int[][][] iArr) throws ExoPlaybackException;

    public final boolean hasSelectionOverride(int i, TrackGroupArray trackGroupArray) {
        Map map = (Map) this.selectionOverrides.get(i);
        return map != null && map.containsKey(trackGroupArray);
    }

    public final TrackSelectorResult selectTracks(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray trackGroupArray) throws ExoPlaybackException {
        int[] iArr;
        RendererCapabilities[] rendererCapabilitiesArr2 = rendererCapabilitiesArr;
        TrackGroupArray trackGroupArray2 = trackGroupArray;
        int[] iArr2 = new int[(rendererCapabilitiesArr2.length + 1)];
        TrackGroup[][] trackGroupArr = new TrackGroup[(rendererCapabilitiesArr2.length + 1)][];
        int[][][] iArr3 = new int[(rendererCapabilitiesArr2.length + 1)][][];
        for (int i = 0; i < trackGroupArr.length; i++) {
            trackGroupArr[i] = new TrackGroup[trackGroupArray2.length];
            iArr3[i] = new int[trackGroupArray2.length][];
        }
        int[] mixedMimeTypeAdaptationSupport = getMixedMimeTypeAdaptationSupport(rendererCapabilitiesArr);
        for (int i2 = 0; i2 < trackGroupArray2.length; i2++) {
            TrackGroup trackGroup = trackGroupArray2.get(i2);
            int findRenderer = findRenderer(rendererCapabilitiesArr2, trackGroup);
            if (findRenderer == rendererCapabilitiesArr2.length) {
                iArr = new int[trackGroup.length];
            } else {
                iArr = getFormatSupport(rendererCapabilitiesArr2[findRenderer], trackGroup);
            }
            int i3 = iArr2[findRenderer];
            trackGroupArr[findRenderer][i3] = trackGroup;
            iArr3[findRenderer][i3] = iArr;
            iArr2[findRenderer] = iArr2[findRenderer] + 1;
        }
        TrackGroupArray[] trackGroupArrayArr = new TrackGroupArray[rendererCapabilitiesArr2.length];
        int[] iArr4 = new int[rendererCapabilitiesArr2.length];
        for (int i4 = 0; i4 < rendererCapabilitiesArr2.length; i4++) {
            int i5 = iArr2[i4];
            trackGroupArrayArr[i4] = new TrackGroupArray((TrackGroup[]) Arrays.copyOf(trackGroupArr[i4], i5));
            iArr3[i4] = (int[][]) Arrays.copyOf(iArr3[i4], i5);
            iArr4[i4] = rendererCapabilitiesArr2[i4].getTrackType();
        }
        TrackGroupArray trackGroupArray3 = new TrackGroupArray((TrackGroup[]) Arrays.copyOf(trackGroupArr[rendererCapabilitiesArr2.length], iArr2[rendererCapabilitiesArr2.length]));
        TrackSelection[] selectTracks = selectTracks(rendererCapabilitiesArr2, trackGroupArrayArr, iArr3);
        int i6 = 0;
        while (true) {
            TrackSelection trackSelection = null;
            if (i6 >= rendererCapabilitiesArr2.length) {
                break;
            }
            if (this.rendererDisabledFlags.get(i6)) {
                selectTracks[i6] = null;
            } else {
                TrackGroupArray trackGroupArray4 = trackGroupArrayArr[i6];
                if (hasSelectionOverride(i6, trackGroupArray4)) {
                    SelectionOverride selectionOverride = (SelectionOverride) ((Map) this.selectionOverrides.get(i6)).get(trackGroupArray4);
                    if (selectionOverride != null) {
                        trackSelection = selectionOverride.createTrackSelection(trackGroupArray4);
                    }
                    selectTracks[i6] = trackSelection;
                }
            }
            i6++;
        }
        boolean[] determineEnabledRenderers = determineEnabledRenderers(rendererCapabilitiesArr2, selectTracks);
        MappedTrackInfo mappedTrackInfo = new MappedTrackInfo(iArr4, trackGroupArrayArr, mixedMimeTypeAdaptationSupport, iArr3, trackGroupArray3);
        RendererConfiguration[] rendererConfigurationArr = new RendererConfiguration[rendererCapabilitiesArr2.length];
        for (int i7 = 0; i7 < rendererCapabilitiesArr2.length; i7++) {
            rendererConfigurationArr[i7] = determineEnabledRenderers[i7] ? RendererConfiguration.DEFAULT : null;
        }
        maybeConfigureRenderersForTunneling(rendererCapabilitiesArr2, trackGroupArrayArr, iArr3, rendererConfigurationArr, selectTracks, this.tunnelingAudioSessionId);
        TrackSelectorResult trackSelectorResult = new TrackSelectorResult(trackGroupArray2, determineEnabledRenderers, new TrackSelectionArray(selectTracks), mappedTrackInfo, rendererConfigurationArr);
        return trackSelectorResult;
    }

    private boolean[] determineEnabledRenderers(RendererCapabilities[] rendererCapabilitiesArr, TrackSelection[] trackSelectionArr) {
        boolean[] zArr = new boolean[trackSelectionArr.length];
        for (int i = 0; i < zArr.length; i++) {
            zArr[i] = !this.rendererDisabledFlags.get(i) && (rendererCapabilitiesArr[i].getTrackType() == 5 || trackSelectionArr[i] != null);
        }
        return zArr;
    }

    public final void onSelectionActivated(Object obj) {
        this.currentMappedTrackInfo = (MappedTrackInfo) obj;
    }

    private static int findRenderer(RendererCapabilities[] rendererCapabilitiesArr, TrackGroup trackGroup) throws ExoPlaybackException {
        int length = rendererCapabilitiesArr.length;
        int i = 0;
        int i2 = 0;
        while (i < rendererCapabilitiesArr.length) {
            RendererCapabilities rendererCapabilities = rendererCapabilitiesArr[i];
            int i3 = length;
            for (int i4 = 0; i4 < trackGroup.length; i4++) {
                int supportsFormat = rendererCapabilities.supportsFormat(trackGroup.getFormat(i4)) & 7;
                if (supportsFormat > i2) {
                    if (supportsFormat == 4) {
                        return i;
                    }
                    i3 = i;
                    i2 = supportsFormat;
                }
            }
            i++;
            length = i3;
        }
        return length;
    }

    private static int[] getFormatSupport(RendererCapabilities rendererCapabilities, TrackGroup trackGroup) throws ExoPlaybackException {
        int[] iArr = new int[trackGroup.length];
        for (int i = 0; i < trackGroup.length; i++) {
            iArr[i] = rendererCapabilities.supportsFormat(trackGroup.getFormat(i));
        }
        return iArr;
    }

    private static int[] getMixedMimeTypeAdaptationSupport(RendererCapabilities[] rendererCapabilitiesArr) throws ExoPlaybackException {
        int[] iArr = new int[rendererCapabilitiesArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = rendererCapabilitiesArr[i].supportsMixedMimeTypeAdaptation();
        }
        return iArr;
    }

    private static void maybeConfigureRenderersForTunneling(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray[] trackGroupArrayArr, int[][][] iArr, RendererConfiguration[] rendererConfigurationArr, TrackSelection[] trackSelectionArr, int i) {
        boolean z;
        if (i != 0) {
            boolean z2 = false;
            int i2 = 0;
            int i3 = -1;
            int i4 = -1;
            while (true) {
                if (i2 >= rendererCapabilitiesArr.length) {
                    z = true;
                    break;
                }
                int trackType = rendererCapabilitiesArr[i2].getTrackType();
                TrackSelection trackSelection = trackSelectionArr[i2];
                if ((trackType == 1 || trackType == 2) && trackSelection != null && rendererSupportsTunneling(iArr[i2], trackGroupArrayArr[i2], trackSelection)) {
                    if (trackType == 1) {
                        if (i3 != -1) {
                            break;
                        }
                        i3 = i2;
                    } else if (i4 != -1) {
                        break;
                    } else {
                        i4 = i2;
                    }
                }
                i2++;
            }
            z = false;
            if (!(i3 == -1 || i4 == -1)) {
                z2 = true;
            }
            if (z && z2) {
                RendererConfiguration rendererConfiguration = new RendererConfiguration(i);
                rendererConfigurationArr[i3] = rendererConfiguration;
                rendererConfigurationArr[i4] = rendererConfiguration;
            }
        }
    }

    private static boolean rendererSupportsTunneling(int[][] iArr, TrackGroupArray trackGroupArray, TrackSelection trackSelection) {
        if (trackSelection == null) {
            return false;
        }
        int indexOf = trackGroupArray.indexOf(trackSelection.getTrackGroup());
        for (int i = 0; i < trackSelection.length(); i++) {
            if ((iArr[indexOf][trackSelection.getIndexInTrackGroup(i)] & 32) != 32) {
                return false;
            }
        }
        return true;
    }
}
