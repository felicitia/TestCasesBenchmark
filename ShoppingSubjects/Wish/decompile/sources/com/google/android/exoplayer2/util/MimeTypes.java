package com.google.android.exoplayer2.util;

import android.text.TextUtils;

public final class MimeTypes {
    public static String getMimeTypeFromMp4ObjectType(int i) {
        switch (i) {
            case 32:
                return "video/mp4v-es";
            case 33:
                return "video/avc";
            case 35:
                return "video/hevc";
            case 64:
            case 102:
            case 103:
            case 104:
                return "audio/mp4a-latm";
            case 96:
            case 97:
                return "video/mpeg2";
            case 105:
            case 107:
                return "audio/mpeg";
            case 165:
                return "audio/ac3";
            case 166:
                return "audio/eac3";
            case 169:
            case 172:
                return "audio/vnd.dts";
            case 170:
            case 171:
                return "audio/vnd.dts.hd";
            case 173:
                return "audio/opus";
            default:
                return null;
        }
    }

    public static boolean isAudio(String str) {
        return "audio".equals(getTopLevelType(str));
    }

    public static boolean isVideo(String str) {
        return "video".equals(getTopLevelType(str));
    }

    public static boolean isText(String str) {
        return "text".equals(getTopLevelType(str));
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0083  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getMediaMimeType(java.lang.String r3) {
        /*
            r0 = 0
            if (r3 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r3 = r3.trim()
            java.lang.String r1 = "avc1"
            boolean r1 = r3.startsWith(r1)
            if (r1 != 0) goto L_0x0101
            java.lang.String r1 = "avc3"
            boolean r1 = r3.startsWith(r1)
            if (r1 == 0) goto L_0x001a
            goto L_0x0101
        L_0x001a:
            java.lang.String r1 = "hev1"
            boolean r1 = r3.startsWith(r1)
            if (r1 != 0) goto L_0x00fe
            java.lang.String r1 = "hvc1"
            boolean r1 = r3.startsWith(r1)
            if (r1 == 0) goto L_0x002c
            goto L_0x00fe
        L_0x002c:
            java.lang.String r1 = "vp9"
            boolean r1 = r3.startsWith(r1)
            if (r1 != 0) goto L_0x00fb
            java.lang.String r1 = "vp09"
            boolean r1 = r3.startsWith(r1)
            if (r1 == 0) goto L_0x003e
            goto L_0x00fb
        L_0x003e:
            java.lang.String r1 = "vp8"
            boolean r1 = r3.startsWith(r1)
            if (r1 != 0) goto L_0x00f8
            java.lang.String r1 = "vp08"
            boolean r1 = r3.startsWith(r1)
            if (r1 == 0) goto L_0x0050
            goto L_0x00f8
        L_0x0050:
            java.lang.String r1 = "mp4a"
            boolean r1 = r3.startsWith(r1)
            if (r1 == 0) goto L_0x0086
            java.lang.String r1 = "mp4a."
            boolean r1 = r3.startsWith(r1)
            if (r1 == 0) goto L_0x0080
            r1 = 5
            java.lang.String r3 = r3.substring(r1)
            int r1 = r3.length()
            r2 = 2
            if (r1 < r2) goto L_0x0080
            r1 = 0
            java.lang.String r3 = r3.substring(r1, r2)     // Catch:{ NumberFormatException -> 0x0080 }
            java.lang.String r3 = com.google.android.exoplayer2.util.Util.toUpperInvariant(r3)     // Catch:{ NumberFormatException -> 0x0080 }
            r1 = 16
            int r3 = java.lang.Integer.parseInt(r3, r1)     // Catch:{ NumberFormatException -> 0x0080 }
            java.lang.String r3 = getMimeTypeFromMp4ObjectType(r3)     // Catch:{ NumberFormatException -> 0x0080 }
            goto L_0x0081
        L_0x0080:
            r3 = r0
        L_0x0081:
            if (r3 != 0) goto L_0x0085
            java.lang.String r3 = "audio/mp4a-latm"
        L_0x0085:
            return r3
        L_0x0086:
            java.lang.String r1 = "ac-3"
            boolean r1 = r3.startsWith(r1)
            if (r1 != 0) goto L_0x00f5
            java.lang.String r1 = "dac3"
            boolean r1 = r3.startsWith(r1)
            if (r1 == 0) goto L_0x0097
            goto L_0x00f5
        L_0x0097:
            java.lang.String r1 = "ec-3"
            boolean r1 = r3.startsWith(r1)
            if (r1 != 0) goto L_0x00f2
            java.lang.String r1 = "dec3"
            boolean r1 = r3.startsWith(r1)
            if (r1 == 0) goto L_0x00a8
            goto L_0x00f2
        L_0x00a8:
            java.lang.String r1 = "ec+3"
            boolean r1 = r3.startsWith(r1)
            if (r1 == 0) goto L_0x00b3
            java.lang.String r3 = "audio/eac3-joc"
            return r3
        L_0x00b3:
            java.lang.String r1 = "dtsc"
            boolean r1 = r3.startsWith(r1)
            if (r1 != 0) goto L_0x00ef
            java.lang.String r1 = "dtse"
            boolean r1 = r3.startsWith(r1)
            if (r1 == 0) goto L_0x00c4
            goto L_0x00ef
        L_0x00c4:
            java.lang.String r1 = "dtsh"
            boolean r1 = r3.startsWith(r1)
            if (r1 != 0) goto L_0x00ec
            java.lang.String r1 = "dtsl"
            boolean r1 = r3.startsWith(r1)
            if (r1 == 0) goto L_0x00d5
            goto L_0x00ec
        L_0x00d5:
            java.lang.String r1 = "opus"
            boolean r1 = r3.startsWith(r1)
            if (r1 == 0) goto L_0x00e0
            java.lang.String r3 = "audio/opus"
            return r3
        L_0x00e0:
            java.lang.String r1 = "vorbis"
            boolean r3 = r3.startsWith(r1)
            if (r3 == 0) goto L_0x00eb
            java.lang.String r3 = "audio/vorbis"
            return r3
        L_0x00eb:
            return r0
        L_0x00ec:
            java.lang.String r3 = "audio/vnd.dts.hd"
            return r3
        L_0x00ef:
            java.lang.String r3 = "audio/vnd.dts"
            return r3
        L_0x00f2:
            java.lang.String r3 = "audio/eac3"
            return r3
        L_0x00f5:
            java.lang.String r3 = "audio/ac3"
            return r3
        L_0x00f8:
            java.lang.String r3 = "video/x-vnd.on2.vp8"
            return r3
        L_0x00fb:
            java.lang.String r3 = "video/x-vnd.on2.vp9"
            return r3
        L_0x00fe:
            java.lang.String r3 = "video/hevc"
            return r3
        L_0x0101:
            java.lang.String r3 = "video/avc"
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.util.MimeTypes.getMediaMimeType(java.lang.String):java.lang.String");
    }

    public static int getTrackType(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (isAudio(str)) {
            return 1;
        }
        if (isVideo(str)) {
            return 2;
        }
        if (isText(str) || "application/cea-608".equals(str) || "application/cea-708".equals(str) || "application/x-mp4-cea-608".equals(str) || "application/x-subrip".equals(str) || "application/ttml+xml".equals(str) || "application/x-quicktime-tx3g".equals(str) || "application/x-mp4-vtt".equals(str) || "application/x-rawcc".equals(str) || "application/vobsub".equals(str) || "application/pgs".equals(str) || "application/dvbsubs".equals(str)) {
            return 3;
        }
        if ("application/id3".equals(str) || "application/x-emsg".equals(str) || "application/x-scte35".equals(str) || "application/x-camera-motion".equals(str)) {
            return 4;
        }
        return -1;
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getEncoding(java.lang.String r3) {
        /*
            int r0 = r3.hashCode()
            r1 = 0
            r2 = 5
            switch(r0) {
                case -2123537834: goto L_0x003c;
                case -1095064472: goto L_0x0032;
                case 187078296: goto L_0x0028;
                case 1504578661: goto L_0x001e;
                case 1505942594: goto L_0x0014;
                case 1556697186: goto L_0x000a;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x0046
        L_0x000a:
            java.lang.String r0 = "audio/true-hd"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0046
            r3 = 5
            goto L_0x0047
        L_0x0014:
            java.lang.String r0 = "audio/vnd.dts.hd"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0046
            r3 = 4
            goto L_0x0047
        L_0x001e:
            java.lang.String r0 = "audio/eac3"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0046
            r3 = 1
            goto L_0x0047
        L_0x0028:
            java.lang.String r0 = "audio/ac3"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0046
            r3 = 0
            goto L_0x0047
        L_0x0032:
            java.lang.String r0 = "audio/vnd.dts"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0046
            r3 = 3
            goto L_0x0047
        L_0x003c:
            java.lang.String r0 = "audio/eac3-joc"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0046
            r3 = 2
            goto L_0x0047
        L_0x0046:
            r3 = -1
        L_0x0047:
            switch(r3) {
                case 0: goto L_0x0055;
                case 1: goto L_0x0053;
                case 2: goto L_0x0053;
                case 3: goto L_0x0051;
                case 4: goto L_0x004e;
                case 5: goto L_0x004b;
                default: goto L_0x004a;
            }
        L_0x004a:
            return r1
        L_0x004b:
            r3 = 14
            return r3
        L_0x004e:
            r3 = 8
            return r3
        L_0x0051:
            r3 = 7
            return r3
        L_0x0053:
            r3 = 6
            return r3
        L_0x0055:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.util.MimeTypes.getEncoding(java.lang.String):int");
    }

    private static String getTopLevelType(String str) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(47);
        if (indexOf != -1) {
            return str.substring(0, indexOf);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid mime type: ");
        sb.append(str);
        throw new IllegalArgumentException(sb.toString());
    }
}
