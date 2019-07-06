package com.google.android.exoplayer2.mediacodec;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.MediaCodecInfo;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecInfo.CodecProfileLevel;
import android.media.MediaCodecList;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.util.SparseIntArray;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint({"InlinedApi"})
@TargetApi(16)
public final class MediaCodecUtil {
    private static final SparseIntArray AVC_LEVEL_NUMBER_TO_CONST = new SparseIntArray();
    private static final SparseIntArray AVC_PROFILE_NUMBER_TO_CONST = new SparseIntArray();
    private static final Map<String, Integer> HEVC_CODEC_STRING_TO_PROFILE_LEVEL = new HashMap();
    private static final MediaCodecInfo PASSTHROUGH_DECODER_INFO = MediaCodecInfo.newPassthroughInstance("OMX.google.raw.decoder");
    private static final Pattern PROFILE_PATTERN = Pattern.compile("^\\D?(\\d+)$");
    private static final HashMap<CodecKey, List<MediaCodecInfo>> decoderInfosCache = new HashMap<>();
    private static int maxH264DecodableFrameSize = -1;

    private static final class CodecKey {
        public final String mimeType;
        public final boolean secure;

        public CodecKey(String str, boolean z) {
            this.mimeType = str;
            this.secure = z;
        }

        public int hashCode() {
            return (((this.mimeType == null ? 0 : this.mimeType.hashCode()) + 31) * 31) + (this.secure ? 1231 : 1237);
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != CodecKey.class) {
                return false;
            }
            CodecKey codecKey = (CodecKey) obj;
            if (!TextUtils.equals(this.mimeType, codecKey.mimeType) || this.secure != codecKey.secure) {
                z = false;
            }
            return z;
        }
    }

    public static class DecoderQueryException extends Exception {
        private DecoderQueryException(Throwable th) {
            super("Failed to query underlying media codecs", th);
        }
    }

    private interface MediaCodecListCompat {
        int getCodecCount();

        MediaCodecInfo getCodecInfoAt(int i);

        boolean isSecurePlaybackSupported(String str, CodecCapabilities codecCapabilities);

        boolean secureDecodersExplicit();
    }

    private static final class MediaCodecListCompatV16 implements MediaCodecListCompat {
        public boolean secureDecodersExplicit() {
            return false;
        }

        private MediaCodecListCompatV16() {
        }

        public int getCodecCount() {
            return MediaCodecList.getCodecCount();
        }

        public MediaCodecInfo getCodecInfoAt(int i) {
            return MediaCodecList.getCodecInfoAt(i);
        }

        public boolean isSecurePlaybackSupported(String str, CodecCapabilities codecCapabilities) {
            return "video/avc".equals(str);
        }
    }

    @TargetApi(21)
    private static final class MediaCodecListCompatV21 implements MediaCodecListCompat {
        private final int codecKind;
        private MediaCodecInfo[] mediaCodecInfos;

        public boolean secureDecodersExplicit() {
            return true;
        }

        public MediaCodecListCompatV21(boolean z) {
            this.codecKind = z ? 1 : 0;
        }

        public int getCodecCount() {
            ensureMediaCodecInfosInitialized();
            return this.mediaCodecInfos.length;
        }

        public MediaCodecInfo getCodecInfoAt(int i) {
            ensureMediaCodecInfosInitialized();
            return this.mediaCodecInfos[i];
        }

        public boolean isSecurePlaybackSupported(String str, CodecCapabilities codecCapabilities) {
            return codecCapabilities.isFeatureSupported("secure-playback");
        }

        private void ensureMediaCodecInfosInitialized() {
            if (this.mediaCodecInfos == null) {
                this.mediaCodecInfos = new MediaCodecList(this.codecKind).getCodecInfos();
            }
        }
    }

    private static int avcLevelToMaxFrameSize(int i) {
        switch (i) {
            case 1:
                return 25344;
            case 2:
                return 25344;
            case 8:
                return 101376;
            case 16:
                return 101376;
            case 32:
                return 101376;
            case 64:
                return 202752;
            case 128:
                return 414720;
            case 256:
                return 414720;
            case 512:
                return 921600;
            case 1024:
                return 1310720;
            case 2048:
                return 2097152;
            case 4096:
                return 2097152;
            case 8192:
                return 2228224;
            case 16384:
                return 5652480;
            case 32768:
                return 9437184;
            case 65536:
                return 9437184;
            default:
                return -1;
        }
    }

    static {
        AVC_PROFILE_NUMBER_TO_CONST.put(66, 1);
        AVC_PROFILE_NUMBER_TO_CONST.put(77, 2);
        AVC_PROFILE_NUMBER_TO_CONST.put(88, 4);
        AVC_PROFILE_NUMBER_TO_CONST.put(100, 8);
        AVC_LEVEL_NUMBER_TO_CONST.put(10, 1);
        AVC_LEVEL_NUMBER_TO_CONST.put(11, 4);
        AVC_LEVEL_NUMBER_TO_CONST.put(12, 8);
        AVC_LEVEL_NUMBER_TO_CONST.put(13, 16);
        AVC_LEVEL_NUMBER_TO_CONST.put(20, 32);
        AVC_LEVEL_NUMBER_TO_CONST.put(21, 64);
        AVC_LEVEL_NUMBER_TO_CONST.put(22, 128);
        AVC_LEVEL_NUMBER_TO_CONST.put(30, 256);
        AVC_LEVEL_NUMBER_TO_CONST.put(31, 512);
        AVC_LEVEL_NUMBER_TO_CONST.put(32, 1024);
        AVC_LEVEL_NUMBER_TO_CONST.put(40, 2048);
        AVC_LEVEL_NUMBER_TO_CONST.put(41, 4096);
        AVC_LEVEL_NUMBER_TO_CONST.put(42, 8192);
        AVC_LEVEL_NUMBER_TO_CONST.put(50, 16384);
        AVC_LEVEL_NUMBER_TO_CONST.put(51, 32768);
        AVC_LEVEL_NUMBER_TO_CONST.put(52, 65536);
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L30", Integer.valueOf(1));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L60", Integer.valueOf(4));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L63", Integer.valueOf(16));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L90", Integer.valueOf(64));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L93", Integer.valueOf(256));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L120", Integer.valueOf(1024));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L123", Integer.valueOf(4096));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L150", Integer.valueOf(16384));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L153", Integer.valueOf(65536));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L156", Integer.valueOf(262144));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L180", Integer.valueOf(1048576));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L183", Integer.valueOf(4194304));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L186", Integer.valueOf(16777216));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H30", Integer.valueOf(2));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H60", Integer.valueOf(8));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H63", Integer.valueOf(32));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H90", Integer.valueOf(128));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H93", Integer.valueOf(512));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H120", Integer.valueOf(2048));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H123", Integer.valueOf(8192));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H150", Integer.valueOf(32768));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H153", Integer.valueOf(131072));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H156", Integer.valueOf(524288));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H180", Integer.valueOf(2097152));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H183", Integer.valueOf(8388608));
        HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H186", Integer.valueOf(33554432));
    }

    public static MediaCodecInfo getPassthroughDecoderInfo() {
        return PASSTHROUGH_DECODER_INFO;
    }

    public static MediaCodecInfo getDecoderInfo(String str, boolean z) throws DecoderQueryException {
        List decoderInfos = getDecoderInfos(str, z);
        if (decoderInfos.isEmpty()) {
            return null;
        }
        return (MediaCodecInfo) decoderInfos.get(0);
    }

    public static synchronized List<MediaCodecInfo> getDecoderInfos(String str, boolean z) throws DecoderQueryException {
        synchronized (MediaCodecUtil.class) {
            CodecKey codecKey = new CodecKey(str, z);
            List<MediaCodecInfo> list = (List) decoderInfosCache.get(codecKey);
            if (list != null) {
                return list;
            }
            MediaCodecListCompat mediaCodecListCompatV21 = Util.SDK_INT >= 21 ? new MediaCodecListCompatV21(z) : new MediaCodecListCompatV16();
            ArrayList decoderInfosInternal = getDecoderInfosInternal(codecKey, mediaCodecListCompatV21, str);
            if (z && decoderInfosInternal.isEmpty() && 21 <= Util.SDK_INT && Util.SDK_INT <= 23) {
                mediaCodecListCompatV21 = new MediaCodecListCompatV16();
                decoderInfosInternal = getDecoderInfosInternal(codecKey, mediaCodecListCompatV21, str);
                if (!decoderInfosInternal.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("MediaCodecList API didn't list secure decoder for: ");
                    sb.append(str);
                    sb.append(". Assuming: ");
                    sb.append(((MediaCodecInfo) decoderInfosInternal.get(0)).name);
                    Log.w("MediaCodecUtil", sb.toString());
                }
            }
            if ("audio/eac3-joc".equals(str)) {
                decoderInfosInternal.addAll(getDecoderInfosInternal(new CodecKey("audio/eac3", codecKey.secure), mediaCodecListCompatV21, str));
            }
            applyWorkarounds(decoderInfosInternal);
            List<MediaCodecInfo> unmodifiableList = Collections.unmodifiableList(decoderInfosInternal);
            decoderInfosCache.put(codecKey, unmodifiableList);
            return unmodifiableList;
        }
    }

    public static int maxH264DecodableFrameSize() throws DecoderQueryException {
        if (maxH264DecodableFrameSize == -1) {
            int i = 0;
            MediaCodecInfo decoderInfo = getDecoderInfo("video/avc", false);
            if (decoderInfo != null) {
                CodecProfileLevel[] profileLevels = decoderInfo.getProfileLevels();
                int length = profileLevels.length;
                int i2 = 0;
                while (i < length) {
                    i2 = Math.max(avcLevelToMaxFrameSize(profileLevels[i].level), i2);
                    i++;
                }
                i = Math.max(i2, Util.SDK_INT >= 21 ? 345600 : 172800);
            }
            maxH264DecodableFrameSize = i;
        }
        return maxH264DecodableFrameSize;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0026, code lost:
        if (r3.equals("hev1") != false) goto L_0x003e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.util.Pair<java.lang.Integer, java.lang.Integer> getCodecProfileAndLevel(java.lang.String r6) {
        /*
            r0 = 0
            if (r6 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r1 = "\\."
            java.lang.String[] r1 = r6.split(r1)
            r2 = 0
            r3 = r1[r2]
            r4 = -1
            int r5 = r3.hashCode()
            switch(r5) {
                case 3006243: goto L_0x0033;
                case 3006244: goto L_0x0029;
                case 3199032: goto L_0x0020;
                case 3214780: goto L_0x0016;
                default: goto L_0x0015;
            }
        L_0x0015:
            goto L_0x003d
        L_0x0016:
            java.lang.String r2 = "hvc1"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x003d
            r2 = 1
            goto L_0x003e
        L_0x0020:
            java.lang.String r5 = "hev1"
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L_0x003d
            goto L_0x003e
        L_0x0029:
            java.lang.String r2 = "avc2"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x003d
            r2 = 3
            goto L_0x003e
        L_0x0033:
            java.lang.String r2 = "avc1"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x003d
            r2 = 2
            goto L_0x003e
        L_0x003d:
            r2 = -1
        L_0x003e:
            switch(r2) {
                case 0: goto L_0x0047;
                case 1: goto L_0x0047;
                case 2: goto L_0x0042;
                case 3: goto L_0x0042;
                default: goto L_0x0041;
            }
        L_0x0041:
            return r0
        L_0x0042:
            android.util.Pair r6 = getAvcProfileAndLevel(r6, r1)
            return r6
        L_0x0047:
            android.util.Pair r6 = getHevcProfileAndLevel(r6, r1)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.mediacodec.MediaCodecUtil.getCodecProfileAndLevel(java.lang.String):android.util.Pair");
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x0090 A[Catch:{ Exception -> 0x00ef }] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00b2 A[ADDED_TO_REGION, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.ArrayList<com.google.android.exoplayer2.mediacodec.MediaCodecInfo> getDecoderInfosInternal(com.google.android.exoplayer2.mediacodec.MediaCodecUtil.CodecKey r20, com.google.android.exoplayer2.mediacodec.MediaCodecUtil.MediaCodecListCompat r21, java.lang.String r22) throws com.google.android.exoplayer2.mediacodec.MediaCodecUtil.DecoderQueryException {
        /*
            r1 = r20
            r2 = r21
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Exception -> 0x00ef }
            r3.<init>()     // Catch:{ Exception -> 0x00ef }
            java.lang.String r4 = r1.mimeType     // Catch:{ Exception -> 0x00ef }
            int r5 = r21.getCodecCount()     // Catch:{ Exception -> 0x00ef }
            boolean r6 = r21.secureDecodersExplicit()     // Catch:{ Exception -> 0x00ef }
            r8 = 0
        L_0x0014:
            if (r8 >= r5) goto L_0x00ee
            android.media.MediaCodecInfo r9 = r2.getCodecInfoAt(r8)     // Catch:{ Exception -> 0x00ef }
            java.lang.String r10 = r9.getName()     // Catch:{ Exception -> 0x00ef }
            r11 = r22
            boolean r12 = isCodecUsableDecoder(r9, r10, r6, r11)     // Catch:{ Exception -> 0x00ef }
            if (r12 == 0) goto L_0x00e4
            java.lang.String[] r12 = r9.getSupportedTypes()     // Catch:{ Exception -> 0x00ef }
            int r13 = r12.length     // Catch:{ Exception -> 0x00ef }
            r14 = 0
        L_0x002c:
            if (r14 >= r13) goto L_0x00e4
            r15 = r12[r14]     // Catch:{ Exception -> 0x00ef }
            boolean r16 = r15.equalsIgnoreCase(r4)     // Catch:{ Exception -> 0x00ef }
            if (r16 == 0) goto L_0x00d6
            android.media.MediaCodecInfo$CodecCapabilities r7 = r9.getCapabilitiesForType(r15)     // Catch:{ Exception -> 0x0084 }
            r17 = r5
            boolean r5 = r2.isSecurePlaybackSupported(r4, r7)     // Catch:{ Exception -> 0x0082 }
            boolean r2 = codecNeedsDisableAdaptationWorkaround(r10)     // Catch:{ Exception -> 0x0082 }
            if (r6 == 0) goto L_0x0051
            r18 = r9
            boolean r9 = r1.secure     // Catch:{ Exception -> 0x004f }
            if (r9 == r5) goto L_0x004d
            goto L_0x0053
        L_0x004d:
            r9 = 0
            goto L_0x005a
        L_0x004f:
            r0 = move-exception
            goto L_0x0089
        L_0x0051:
            r18 = r9
        L_0x0053:
            if (r6 != 0) goto L_0x0063
            boolean r9 = r1.secure     // Catch:{ Exception -> 0x004f }
            if (r9 != 0) goto L_0x0063
            goto L_0x004d
        L_0x005a:
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r2 = com.google.android.exoplayer2.mediacodec.MediaCodecInfo.newInstance(r10, r4, r7, r2, r9)     // Catch:{ Exception -> 0x004f }
            r3.add(r2)     // Catch:{ Exception -> 0x004f }
            goto L_0x00da
        L_0x0063:
            r9 = 0
            if (r6 != 0) goto L_0x00da
            if (r5 == 0) goto L_0x00da
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004f }
            r5.<init>()     // Catch:{ Exception -> 0x004f }
            r5.append(r10)     // Catch:{ Exception -> 0x004f }
            java.lang.String r9 = ".secure"
            r5.append(r9)     // Catch:{ Exception -> 0x004f }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x004f }
            r9 = 1
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r2 = com.google.android.exoplayer2.mediacodec.MediaCodecInfo.newInstance(r5, r4, r7, r2, r9)     // Catch:{ Exception -> 0x004f }
            r3.add(r2)     // Catch:{ Exception -> 0x004f }
            return r3
        L_0x0082:
            r0 = move-exception
            goto L_0x0087
        L_0x0084:
            r0 = move-exception
            r17 = r5
        L_0x0087:
            r18 = r9
        L_0x0089:
            r2 = r0
            int r5 = com.google.android.exoplayer2.util.Util.SDK_INT     // Catch:{ Exception -> 0x00ef }
            r7 = 23
            if (r5 > r7) goto L_0x00b2
            boolean r5 = r3.isEmpty()     // Catch:{ Exception -> 0x00ef }
            if (r5 != 0) goto L_0x00b2
            java.lang.String r2 = "MediaCodecUtil"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ef }
            r5.<init>()     // Catch:{ Exception -> 0x00ef }
            java.lang.String r7 = "Skipping codec "
            r5.append(r7)     // Catch:{ Exception -> 0x00ef }
            r5.append(r10)     // Catch:{ Exception -> 0x00ef }
            java.lang.String r7 = " (failed to query capabilities)"
            r5.append(r7)     // Catch:{ Exception -> 0x00ef }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x00ef }
            android.util.Log.e(r2, r5)     // Catch:{ Exception -> 0x00ef }
            goto L_0x00da
        L_0x00b2:
            java.lang.String r1 = "MediaCodecUtil"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ef }
            r3.<init>()     // Catch:{ Exception -> 0x00ef }
            java.lang.String r4 = "Failed to query codec "
            r3.append(r4)     // Catch:{ Exception -> 0x00ef }
            r3.append(r10)     // Catch:{ Exception -> 0x00ef }
            java.lang.String r4 = " ("
            r3.append(r4)     // Catch:{ Exception -> 0x00ef }
            r3.append(r15)     // Catch:{ Exception -> 0x00ef }
            java.lang.String r4 = ")"
            r3.append(r4)     // Catch:{ Exception -> 0x00ef }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x00ef }
            android.util.Log.e(r1, r3)     // Catch:{ Exception -> 0x00ef }
            throw r2     // Catch:{ Exception -> 0x00ef }
        L_0x00d6:
            r17 = r5
            r18 = r9
        L_0x00da:
            int r14 = r14 + 1
            r5 = r17
            r9 = r18
            r2 = r21
            goto L_0x002c
        L_0x00e4:
            r17 = r5
            int r8 = r8 + 1
            r5 = r17
            r2 = r21
            goto L_0x0014
        L_0x00ee:
            return r3
        L_0x00ef:
            r0 = move-exception
            r1 = r0
            com.google.android.exoplayer2.mediacodec.MediaCodecUtil$DecoderQueryException r2 = new com.google.android.exoplayer2.mediacodec.MediaCodecUtil$DecoderQueryException
            r3 = 0
            r2.<init>(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.mediacodec.MediaCodecUtil.getDecoderInfosInternal(com.google.android.exoplayer2.mediacodec.MediaCodecUtil$CodecKey, com.google.android.exoplayer2.mediacodec.MediaCodecUtil$MediaCodecListCompat, java.lang.String):java.util.ArrayList");
    }

    private static boolean isCodecUsableDecoder(MediaCodecInfo mediaCodecInfo, String str, boolean z, String str2) {
        if (mediaCodecInfo.isEncoder() || (!z && str.endsWith(".secure"))) {
            return false;
        }
        if (Util.SDK_INT < 21 && ("CIPAACDecoder".equals(str) || "CIPMP3Decoder".equals(str) || "CIPVorbisDecoder".equals(str) || "CIPAMRNBDecoder".equals(str) || "AACDecoder".equals(str) || "MP3Decoder".equals(str))) {
            return false;
        }
        if (Util.SDK_INT < 18 && "OMX.SEC.MP3.Decoder".equals(str)) {
            return false;
        }
        if (Util.SDK_INT < 18 && "OMX.MTK.AUDIO.DECODER.AAC".equals(str) && ("a70".equals(Util.DEVICE) || ("Xiaomi".equals(Util.MANUFACTURER) && Util.DEVICE.startsWith("HM")))) {
            return false;
        }
        if (Util.SDK_INT == 16 && "OMX.qcom.audio.decoder.mp3".equals(str) && ("dlxu".equals(Util.DEVICE) || "protou".equals(Util.DEVICE) || "ville".equals(Util.DEVICE) || "villeplus".equals(Util.DEVICE) || "villec2".equals(Util.DEVICE) || Util.DEVICE.startsWith("gee") || "C6602".equals(Util.DEVICE) || "C6603".equals(Util.DEVICE) || "C6606".equals(Util.DEVICE) || "C6616".equals(Util.DEVICE) || "L36h".equals(Util.DEVICE) || "SO-02E".equals(Util.DEVICE))) {
            return false;
        }
        if (Util.SDK_INT == 16 && "OMX.qcom.audio.decoder.aac".equals(str) && ("C1504".equals(Util.DEVICE) || "C1505".equals(Util.DEVICE) || "C1604".equals(Util.DEVICE) || "C1605".equals(Util.DEVICE))) {
            return false;
        }
        if (Util.SDK_INT < 24 && (("OMX.SEC.aac.dec".equals(str) || "OMX.Exynos.AAC.Decoder".equals(str)) && Util.MANUFACTURER.equals("samsung") && (Util.DEVICE.startsWith("zeroflte") || Util.DEVICE.startsWith("zerolte") || Util.DEVICE.startsWith("zenlte") || Util.DEVICE.equals("SC-05G") || Util.DEVICE.equals("marinelteatt") || Util.DEVICE.equals("404SC") || Util.DEVICE.equals("SC-04G") || Util.DEVICE.equals("SCV31")))) {
            return false;
        }
        if (Util.SDK_INT <= 19 && "OMX.SEC.vp8.dec".equals(str) && "samsung".equals(Util.MANUFACTURER) && (Util.DEVICE.startsWith("d2") || Util.DEVICE.startsWith("serrano") || Util.DEVICE.startsWith("jflte") || Util.DEVICE.startsWith("santos") || Util.DEVICE.startsWith("t0"))) {
            return false;
        }
        if (Util.SDK_INT <= 19 && Util.DEVICE.startsWith("jflte") && "OMX.qcom.video.decoder.vp8".equals(str)) {
            return false;
        }
        if (!"audio/eac3-joc".equals(str2) || !"OMX.MTK.AUDIO.DECODER.DSPAC3".equals(str)) {
            return true;
        }
        return false;
    }

    private static void applyWorkarounds(List<MediaCodecInfo> list) {
        if (Util.SDK_INT < 26) {
            if (list.size() > 1 && "OMX.MTK.AUDIO.DECODER.RAW".equals(((MediaCodecInfo) list.get(0)).name)) {
                for (int i = 1; i < list.size(); i++) {
                    MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) list.get(i);
                    if ("OMX.google.raw.decoder".equals(mediaCodecInfo.name)) {
                        list.remove(i);
                        list.add(0, mediaCodecInfo);
                        return;
                    }
                }
            }
        }
    }

    private static boolean codecNeedsDisableAdaptationWorkaround(String str) {
        return Util.SDK_INT <= 22 && (Util.MODEL.equals("ODROID-XU3") || Util.MODEL.equals("Nexus 10")) && ("OMX.Exynos.AVC.Decoder".equals(str) || "OMX.Exynos.AVC.Decoder.secure".equals(str));
    }

    private static Pair<Integer, Integer> getHevcProfileAndLevel(String str, String[] strArr) {
        int i;
        if (strArr.length < 4) {
            StringBuilder sb = new StringBuilder();
            sb.append("Ignoring malformed HEVC codec string: ");
            sb.append(str);
            Log.w("MediaCodecUtil", sb.toString());
            return null;
        }
        Matcher matcher = PROFILE_PATTERN.matcher(strArr[1]);
        if (!matcher.matches()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Ignoring malformed HEVC codec string: ");
            sb2.append(str);
            Log.w("MediaCodecUtil", sb2.toString());
            return null;
        }
        String group = matcher.group(1);
        if ("1".equals(group)) {
            i = 1;
        } else if ("2".equals(group)) {
            i = 2;
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Unknown HEVC profile string: ");
            sb3.append(group);
            Log.w("MediaCodecUtil", sb3.toString());
            return null;
        }
        Integer num = (Integer) HEVC_CODEC_STRING_TO_PROFILE_LEVEL.get(strArr[3]);
        if (num != null) {
            return new Pair<>(Integer.valueOf(i), num);
        }
        StringBuilder sb4 = new StringBuilder();
        sb4.append("Unknown HEVC level string: ");
        sb4.append(matcher.group(1));
        Log.w("MediaCodecUtil", sb4.toString());
        return null;
    }

    private static Pair<Integer, Integer> getAvcProfileAndLevel(String str, String[] strArr) {
        Integer num;
        Integer num2;
        if (strArr.length < 2) {
            StringBuilder sb = new StringBuilder();
            sb.append("Ignoring malformed AVC codec string: ");
            sb.append(str);
            Log.w("MediaCodecUtil", sb.toString());
            return null;
        }
        try {
            if (strArr[1].length() == 6) {
                Integer valueOf = Integer.valueOf(Integer.parseInt(strArr[1].substring(0, 2), 16));
                num = Integer.valueOf(Integer.parseInt(strArr[1].substring(4), 16));
                num2 = valueOf;
            } else if (strArr.length >= 3) {
                num2 = Integer.valueOf(Integer.parseInt(strArr[1]));
                num = Integer.valueOf(Integer.parseInt(strArr[2]));
            } else {
                String str2 = "MediaCodecUtil";
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Ignoring malformed AVC codec string: ");
                sb2.append(str);
                Log.w(str2, sb2.toString());
                return null;
            }
            Integer valueOf2 = Integer.valueOf(AVC_PROFILE_NUMBER_TO_CONST.get(num2.intValue()));
            if (valueOf2 == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Unknown AVC profile: ");
                sb3.append(num2);
                Log.w("MediaCodecUtil", sb3.toString());
                return null;
            }
            Integer valueOf3 = Integer.valueOf(AVC_LEVEL_NUMBER_TO_CONST.get(num.intValue()));
            if (valueOf3 != null) {
                return new Pair<>(valueOf2, valueOf3);
            }
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Unknown AVC level: ");
            sb4.append(num);
            Log.w("MediaCodecUtil", sb4.toString());
            return null;
        } catch (NumberFormatException unused) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append("Ignoring malformed AVC codec string: ");
            sb5.append(str);
            Log.w("MediaCodecUtil", sb5.toString());
            return null;
        }
    }
}
