package com.google.android.exoplayer2.source.smoothstreaming.manifest;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmInitData.SchemeData;
import com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest.ProtectionElement;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest.StreamElement;
import com.google.android.exoplayer2.upstream.ParsingLoadable.Parser;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class SsManifestParser implements Parser<SsManifest> {
    private final XmlPullParserFactory xmlParserFactory;

    private static abstract class ElementParser {
        private final String baseUri;
        private final List<Pair<String, Object>> normalizedAttributes = new LinkedList();
        private final ElementParser parent;
        private final String tag;

        /* access modifiers changed from: protected */
        public void addChild(Object obj) {
        }

        /* access modifiers changed from: protected */
        public abstract Object build();

        /* access modifiers changed from: protected */
        public boolean handleChildInline(String str) {
            return false;
        }

        /* access modifiers changed from: protected */
        public void parseEndTag(XmlPullParser xmlPullParser) {
        }

        /* access modifiers changed from: protected */
        public void parseStartTag(XmlPullParser xmlPullParser) throws ParserException {
        }

        /* access modifiers changed from: protected */
        public void parseText(XmlPullParser xmlPullParser) {
        }

        public ElementParser(ElementParser elementParser, String str, String str2) {
            this.parent = elementParser;
            this.baseUri = str;
            this.tag = str2;
        }

        public final Object parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
            boolean z = false;
            int i = 0;
            while (true) {
                switch (xmlPullParser.getEventType()) {
                    case 1:
                        return null;
                    case 2:
                        String name = xmlPullParser.getName();
                        if (!this.tag.equals(name)) {
                            if (z) {
                                if (i <= 0) {
                                    if (!handleChildInline(name)) {
                                        ElementParser newChildParser = newChildParser(this, name, this.baseUri);
                                        if (newChildParser != null) {
                                            addChild(newChildParser.parse(xmlPullParser));
                                            break;
                                        } else {
                                            i = 1;
                                            break;
                                        }
                                    } else {
                                        parseStartTag(xmlPullParser);
                                        break;
                                    }
                                } else {
                                    i++;
                                    break;
                                }
                            } else {
                                break;
                            }
                        } else {
                            parseStartTag(xmlPullParser);
                            z = true;
                            break;
                        }
                    case 3:
                        if (z) {
                            if (i <= 0) {
                                String name2 = xmlPullParser.getName();
                                parseEndTag(xmlPullParser);
                                if (handleChildInline(name2)) {
                                    break;
                                } else {
                                    return build();
                                }
                            } else {
                                i--;
                                break;
                            }
                        } else {
                            continue;
                        }
                    case 4:
                        if (z && i == 0) {
                            parseText(xmlPullParser);
                            break;
                        }
                }
                xmlPullParser.next();
            }
        }

        private ElementParser newChildParser(ElementParser elementParser, String str, String str2) {
            if ("QualityLevel".equals(str)) {
                return new QualityLevelParser(elementParser, str2);
            }
            if ("Protection".equals(str)) {
                return new ProtectionParser(elementParser, str2);
            }
            if ("StreamIndex".equals(str)) {
                return new StreamIndexParser(elementParser, str2);
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public final void putNormalizedAttribute(String str, Object obj) {
            this.normalizedAttributes.add(Pair.create(str, obj));
        }

        /* access modifiers changed from: protected */
        public final Object getNormalizedAttribute(String str) {
            for (int i = 0; i < this.normalizedAttributes.size(); i++) {
                Pair pair = (Pair) this.normalizedAttributes.get(i);
                if (((String) pair.first).equals(str)) {
                    return pair.second;
                }
            }
            return this.parent == null ? null : this.parent.getNormalizedAttribute(str);
        }

        /* access modifiers changed from: protected */
        public final String parseRequiredString(XmlPullParser xmlPullParser, String str) throws MissingFieldException {
            String attributeValue = xmlPullParser.getAttributeValue(null, str);
            if (attributeValue != null) {
                return attributeValue;
            }
            throw new MissingFieldException(str);
        }

        /* access modifiers changed from: protected */
        public final int parseInt(XmlPullParser xmlPullParser, String str, int i) throws ParserException {
            String attributeValue = xmlPullParser.getAttributeValue(null, str);
            if (attributeValue == null) {
                return i;
            }
            try {
                return Integer.parseInt(attributeValue);
            } catch (NumberFormatException e) {
                throw new ParserException((Throwable) e);
            }
        }

        /* access modifiers changed from: protected */
        public final int parseRequiredInt(XmlPullParser xmlPullParser, String str) throws ParserException {
            String attributeValue = xmlPullParser.getAttributeValue(null, str);
            if (attributeValue != null) {
                try {
                    return Integer.parseInt(attributeValue);
                } catch (NumberFormatException e) {
                    throw new ParserException((Throwable) e);
                }
            } else {
                throw new MissingFieldException(str);
            }
        }

        /* access modifiers changed from: protected */
        public final long parseLong(XmlPullParser xmlPullParser, String str, long j) throws ParserException {
            String attributeValue = xmlPullParser.getAttributeValue(null, str);
            if (attributeValue == null) {
                return j;
            }
            try {
                return Long.parseLong(attributeValue);
            } catch (NumberFormatException e) {
                throw new ParserException((Throwable) e);
            }
        }

        /* access modifiers changed from: protected */
        public final long parseRequiredLong(XmlPullParser xmlPullParser, String str) throws ParserException {
            String attributeValue = xmlPullParser.getAttributeValue(null, str);
            if (attributeValue != null) {
                try {
                    return Long.parseLong(attributeValue);
                } catch (NumberFormatException e) {
                    throw new ParserException((Throwable) e);
                }
            } else {
                throw new MissingFieldException(str);
            }
        }

        /* access modifiers changed from: protected */
        public final boolean parseBoolean(XmlPullParser xmlPullParser, String str, boolean z) {
            String attributeValue = xmlPullParser.getAttributeValue(null, str);
            return attributeValue != null ? Boolean.parseBoolean(attributeValue) : z;
        }
    }

    public static class MissingFieldException extends ParserException {
        public MissingFieldException(String str) {
            StringBuilder sb = new StringBuilder();
            sb.append("Missing required field: ");
            sb.append(str);
            super(sb.toString());
        }
    }

    private static class ProtectionParser extends ElementParser {
        private boolean inProtectionHeader;
        private byte[] initData;
        private UUID uuid;

        public ProtectionParser(ElementParser elementParser, String str) {
            super(elementParser, str, "Protection");
        }

        public boolean handleChildInline(String str) {
            return "ProtectionHeader".equals(str);
        }

        public void parseStartTag(XmlPullParser xmlPullParser) {
            if ("ProtectionHeader".equals(xmlPullParser.getName())) {
                this.inProtectionHeader = true;
                this.uuid = UUID.fromString(stripCurlyBraces(xmlPullParser.getAttributeValue(null, "SystemID")));
            }
        }

        public void parseText(XmlPullParser xmlPullParser) {
            if (this.inProtectionHeader) {
                this.initData = Base64.decode(xmlPullParser.getText(), 0);
            }
        }

        public void parseEndTag(XmlPullParser xmlPullParser) {
            if ("ProtectionHeader".equals(xmlPullParser.getName())) {
                this.inProtectionHeader = false;
            }
        }

        public Object build() {
            return new ProtectionElement(this.uuid, PsshAtomUtil.buildPsshAtom(this.uuid, this.initData));
        }

        private static String stripCurlyBraces(String str) {
            return (str.charAt(0) == '{' && str.charAt(str.length() - 1) == '}') ? str.substring(1, str.length() - 1) : str;
        }
    }

    private static class QualityLevelParser extends ElementParser {
        private Format format;

        public QualityLevelParser(ElementParser elementParser, String str) {
            super(elementParser, str, "QualityLevel");
        }

        public void parseStartTag(XmlPullParser xmlPullParser) throws ParserException {
            int intValue = ((Integer) getNormalizedAttribute("Type")).intValue();
            String attributeValue = xmlPullParser.getAttributeValue(null, "Index");
            int parseRequiredInt = parseRequiredInt(xmlPullParser, "Bitrate");
            String fourCCToMimeType = fourCCToMimeType(parseRequiredString(xmlPullParser, "FourCC"));
            if (intValue == 2) {
                this.format = Format.createVideoContainerFormat(attributeValue, "video/mp4", fourCCToMimeType, null, parseRequiredInt, parseRequiredInt(xmlPullParser, "MaxWidth"), parseRequiredInt(xmlPullParser, "MaxHeight"), -1.0f, buildCodecSpecificData(xmlPullParser.getAttributeValue(null, "CodecPrivateData")), 0);
            } else if (intValue == 1) {
                if (fourCCToMimeType == null) {
                    fourCCToMimeType = "audio/mp4a-latm";
                }
                int parseRequiredInt2 = parseRequiredInt(xmlPullParser, "Channels");
                int parseRequiredInt3 = parseRequiredInt(xmlPullParser, "SamplingRate");
                List buildCodecSpecificData = buildCodecSpecificData(xmlPullParser.getAttributeValue(null, "CodecPrivateData"));
                if (buildCodecSpecificData.isEmpty() && "audio/mp4a-latm".equals(fourCCToMimeType)) {
                    buildCodecSpecificData = Collections.singletonList(CodecSpecificDataUtil.buildAacLcAudioSpecificConfig(parseRequiredInt3, parseRequiredInt2));
                }
                this.format = Format.createAudioContainerFormat(attributeValue, "audio/mp4", fourCCToMimeType, null, parseRequiredInt, parseRequiredInt2, parseRequiredInt3, buildCodecSpecificData, 0, (String) getNormalizedAttribute("Language"));
            } else if (intValue == 3) {
                this.format = Format.createTextContainerFormat(attributeValue, "application/mp4", fourCCToMimeType, null, parseRequiredInt, 0, (String) getNormalizedAttribute("Language"));
            } else {
                this.format = Format.createContainerFormat(attributeValue, "application/mp4", fourCCToMimeType, null, parseRequiredInt, 0, null);
            }
        }

        public Object build() {
            return this.format;
        }

        private static List<byte[]> buildCodecSpecificData(String str) {
            ArrayList arrayList = new ArrayList();
            if (!TextUtils.isEmpty(str)) {
                byte[] bytesFromHexString = Util.getBytesFromHexString(str);
                byte[][] splitNalUnits = CodecSpecificDataUtil.splitNalUnits(bytesFromHexString);
                if (splitNalUnits == null) {
                    arrayList.add(bytesFromHexString);
                } else {
                    Collections.addAll(arrayList, splitNalUnits);
                }
            }
            return arrayList;
        }

        private static String fourCCToMimeType(String str) {
            if (str.equalsIgnoreCase("H264") || str.equalsIgnoreCase("X264") || str.equalsIgnoreCase("AVC1") || str.equalsIgnoreCase("DAVC")) {
                return "video/avc";
            }
            if (str.equalsIgnoreCase("AAC") || str.equalsIgnoreCase("AACL") || str.equalsIgnoreCase("AACH") || str.equalsIgnoreCase("AACP")) {
                return "audio/mp4a-latm";
            }
            if (str.equalsIgnoreCase("TTML")) {
                return "application/ttml+xml";
            }
            if (str.equalsIgnoreCase("ac-3") || str.equalsIgnoreCase("dac3")) {
                return "audio/ac3";
            }
            if (str.equalsIgnoreCase("ec-3") || str.equalsIgnoreCase("dec3")) {
                return "audio/eac3";
            }
            if (str.equalsIgnoreCase("dtsc")) {
                return "audio/vnd.dts";
            }
            if (str.equalsIgnoreCase("dtsh") || str.equalsIgnoreCase("dtsl")) {
                return "audio/vnd.dts.hd";
            }
            if (str.equalsIgnoreCase("dtse")) {
                return "audio/vnd.dts.hd;profile=lbr";
            }
            if (str.equalsIgnoreCase("opus")) {
                return "audio/opus";
            }
            return null;
        }
    }

    private static class SmoothStreamingMediaParser extends ElementParser {
        private long duration;
        private long dvrWindowLength;
        private boolean isLive;
        private int lookAheadCount = -1;
        private int majorVersion;
        private int minorVersion;
        private ProtectionElement protectionElement = null;
        private final List<StreamElement> streamElements = new LinkedList();
        private long timescale;

        public SmoothStreamingMediaParser(ElementParser elementParser, String str) {
            super(elementParser, str, "SmoothStreamingMedia");
        }

        public void parseStartTag(XmlPullParser xmlPullParser) throws ParserException {
            this.majorVersion = parseRequiredInt(xmlPullParser, "MajorVersion");
            this.minorVersion = parseRequiredInt(xmlPullParser, "MinorVersion");
            this.timescale = parseLong(xmlPullParser, "TimeScale", 10000000);
            this.duration = parseRequiredLong(xmlPullParser, "Duration");
            this.dvrWindowLength = parseLong(xmlPullParser, "DVRWindowLength", 0);
            this.lookAheadCount = parseInt(xmlPullParser, "LookaheadCount", -1);
            this.isLive = parseBoolean(xmlPullParser, "IsLive", false);
            putNormalizedAttribute("TimeScale", Long.valueOf(this.timescale));
        }

        public void addChild(Object obj) {
            if (obj instanceof StreamElement) {
                this.streamElements.add((StreamElement) obj);
            } else if (obj instanceof ProtectionElement) {
                Assertions.checkState(this.protectionElement == null);
                this.protectionElement = (ProtectionElement) obj;
            }
        }

        public Object build() {
            StreamElement[] streamElementArr = new StreamElement[this.streamElements.size()];
            this.streamElements.toArray(streamElementArr);
            if (this.protectionElement != null) {
                DrmInitData drmInitData = new DrmInitData(new SchemeData(this.protectionElement.uuid, "video/mp4", this.protectionElement.data));
                for (StreamElement streamElement : streamElementArr) {
                    for (int i = 0; i < streamElement.formats.length; i++) {
                        streamElement.formats[i] = streamElement.formats[i].copyWithDrmInitData(drmInitData);
                    }
                }
            }
            SsManifest ssManifest = new SsManifest(this.majorVersion, this.minorVersion, this.timescale, this.duration, this.dvrWindowLength, this.lookAheadCount, this.isLive, this.protectionElement, streamElementArr);
            return ssManifest;
        }
    }

    private static class StreamIndexParser extends ElementParser {
        private final String baseUri;
        private int displayHeight;
        private int displayWidth;
        private final List<Format> formats = new LinkedList();
        private String language;
        private long lastChunkDuration;
        private int maxHeight;
        private int maxWidth;
        private String name;
        private ArrayList<Long> startTimes;
        private String subType;
        private long timescale;
        private int type;
        private String url;

        public StreamIndexParser(ElementParser elementParser, String str) {
            super(elementParser, str, "StreamIndex");
            this.baseUri = str;
        }

        public boolean handleChildInline(String str) {
            return "c".equals(str);
        }

        public void parseStartTag(XmlPullParser xmlPullParser) throws ParserException {
            if ("c".equals(xmlPullParser.getName())) {
                parseStreamFragmentStartTag(xmlPullParser);
            } else {
                parseStreamElementStartTag(xmlPullParser);
            }
        }

        private void parseStreamFragmentStartTag(XmlPullParser xmlPullParser) throws ParserException {
            int size = this.startTimes.size();
            long parseLong = parseLong(xmlPullParser, "t", -9223372036854775807L);
            int i = 1;
            if (parseLong == -9223372036854775807L) {
                if (size == 0) {
                    parseLong = 0;
                } else if (this.lastChunkDuration != -1) {
                    parseLong = ((Long) this.startTimes.get(size - 1)).longValue() + this.lastChunkDuration;
                } else {
                    throw new ParserException("Unable to infer start time");
                }
            }
            this.startTimes.add(Long.valueOf(parseLong));
            this.lastChunkDuration = parseLong(xmlPullParser, "d", -9223372036854775807L);
            long parseLong2 = parseLong(xmlPullParser, "r", 1);
            if (parseLong2 <= 1 || this.lastChunkDuration != -9223372036854775807L) {
                while (true) {
                    long j = (long) i;
                    if (j < parseLong2) {
                        this.startTimes.add(Long.valueOf(parseLong + (this.lastChunkDuration * j)));
                        i++;
                    } else {
                        return;
                    }
                }
            } else {
                throw new ParserException("Repeated chunk with unspecified duration");
            }
        }

        private void parseStreamElementStartTag(XmlPullParser xmlPullParser) throws ParserException {
            this.type = parseType(xmlPullParser);
            putNormalizedAttribute("Type", Integer.valueOf(this.type));
            if (this.type == 3) {
                this.subType = parseRequiredString(xmlPullParser, "Subtype");
            } else {
                this.subType = xmlPullParser.getAttributeValue(null, "Subtype");
            }
            this.name = xmlPullParser.getAttributeValue(null, "Name");
            this.url = parseRequiredString(xmlPullParser, "Url");
            this.maxWidth = parseInt(xmlPullParser, "MaxWidth", -1);
            this.maxHeight = parseInt(xmlPullParser, "MaxHeight", -1);
            this.displayWidth = parseInt(xmlPullParser, "DisplayWidth", -1);
            this.displayHeight = parseInt(xmlPullParser, "DisplayHeight", -1);
            this.language = xmlPullParser.getAttributeValue(null, "Language");
            putNormalizedAttribute("Language", this.language);
            this.timescale = (long) parseInt(xmlPullParser, "TimeScale", -1);
            if (this.timescale == -1) {
                this.timescale = ((Long) getNormalizedAttribute("TimeScale")).longValue();
            }
            this.startTimes = new ArrayList<>();
        }

        private int parseType(XmlPullParser xmlPullParser) throws ParserException {
            String attributeValue = xmlPullParser.getAttributeValue(null, "Type");
            if (attributeValue == null) {
                throw new MissingFieldException("Type");
            } else if ("audio".equalsIgnoreCase(attributeValue)) {
                return 1;
            } else {
                if ("video".equalsIgnoreCase(attributeValue)) {
                    return 2;
                }
                if ("text".equalsIgnoreCase(attributeValue)) {
                    return 3;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid key value[");
                sb.append(attributeValue);
                sb.append("]");
                throw new ParserException(sb.toString());
            }
        }

        public void addChild(Object obj) {
            if (obj instanceof Format) {
                this.formats.add((Format) obj);
            }
        }

        public Object build() {
            Format[] formatArr = new Format[this.formats.size()];
            this.formats.toArray(formatArr);
            String str = this.baseUri;
            String str2 = this.url;
            int i = this.type;
            String str3 = this.subType;
            long j = this.timescale;
            String str4 = this.name;
            int i2 = this.maxWidth;
            int i3 = this.maxHeight;
            int i4 = this.displayWidth;
            int i5 = this.displayHeight;
            String str5 = this.language;
            String str6 = str5;
            Format[] formatArr2 = formatArr;
            String str7 = str6;
            Format[] formatArr3 = formatArr2;
            StreamElement streamElement = new StreamElement(str, str2, i, str3, j, str4, i2, i3, i4, i5, str7, formatArr3, this.startTimes, this.lastChunkDuration);
            return streamElement;
        }
    }

    public SsManifest parse(Uri uri, InputStream inputStream) throws IOException {
        try {
            XmlPullParser newPullParser = this.xmlParserFactory.newPullParser();
            newPullParser.setInput(inputStream, null);
            return (SsManifest) new SmoothStreamingMediaParser(null, uri.toString()).parse(newPullParser);
        } catch (XmlPullParserException e) {
            throw new ParserException((Throwable) e);
        }
    }
}
