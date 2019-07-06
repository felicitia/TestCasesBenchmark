package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.format.MatchStrength;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.MergedStream;
import com.fasterxml.jackson.core.io.UTF32Reader;
import com.fasterxml.jackson.core.sym.BytesToNameCanonicalizer;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import java.io.ByteArrayInputStream;
import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public final class ByteSourceJsonBootstrapper {
    static final byte UTF8_BOM_1 = -17;
    static final byte UTF8_BOM_2 = -69;
    static final byte UTF8_BOM_3 = -65;
    protected boolean _bigEndian = true;
    private final boolean _bufferRecyclable;
    protected int _bytesPerChar = 0;
    protected final IOContext _context;
    protected final InputStream _in;
    protected final byte[] _inputBuffer;
    private int _inputEnd;
    protected int _inputProcessed;
    private int _inputPtr;

    public ByteSourceJsonBootstrapper(IOContext iOContext, InputStream inputStream) {
        this._context = iOContext;
        this._in = inputStream;
        this._inputBuffer = iOContext.allocReadIOBuffer();
        this._inputPtr = 0;
        this._inputEnd = 0;
        this._inputProcessed = 0;
        this._bufferRecyclable = true;
    }

    public ByteSourceJsonBootstrapper(IOContext iOContext, byte[] bArr, int i, int i2) {
        this._context = iOContext;
        this._in = null;
        this._inputBuffer = bArr;
        this._inputPtr = i;
        this._inputEnd = i2 + i;
        this._inputProcessed = -i;
        this._bufferRecyclable = false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x006a, code lost:
        if (checkUTF16(((r7._inputBuffer[r7._inputPtr] & 255) << 8) | (r7._inputBuffer[r7._inputPtr + 1] & 255)) != false) goto L_0x006e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0049, code lost:
        if (checkUTF16(r1 >>> 16) != false) goto L_0x006e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0073  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.core.JsonEncoding detectEncoding() throws java.io.IOException, com.fasterxml.jackson.core.JsonParseException {
        /*
            r7 = this;
            r0 = 4
            boolean r1 = r7.ensureLoaded(r0)
            r2 = 2
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L_0x004c
            byte[] r1 = r7._inputBuffer
            int r5 = r7._inputPtr
            byte r1 = r1[r5]
            int r1 = r1 << 24
            byte[] r5 = r7._inputBuffer
            int r6 = r7._inputPtr
            int r6 = r6 + r3
            byte r5 = r5[r6]
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r5 = r5 << 16
            r1 = r1 | r5
            byte[] r5 = r7._inputBuffer
            int r6 = r7._inputPtr
            int r6 = r6 + r2
            byte r2 = r5[r6]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r2 = r2 << 8
            r1 = r1 | r2
            byte[] r2 = r7._inputBuffer
            int r5 = r7._inputPtr
            int r5 = r5 + 3
            byte r2 = r2[r5]
            r2 = r2 & 255(0xff, float:3.57E-43)
            r1 = r1 | r2
            boolean r2 = r7.handleBOM(r1)
            if (r2 == 0) goto L_0x003c
            goto L_0x006e
        L_0x003c:
            boolean r2 = r7.checkUTF32(r1)
            if (r2 == 0) goto L_0x0043
            goto L_0x006e
        L_0x0043:
            int r1 = r1 >>> 16
            boolean r1 = r7.checkUTF16(r1)
            if (r1 == 0) goto L_0x006d
            goto L_0x006e
        L_0x004c:
            boolean r1 = r7.ensureLoaded(r2)
            if (r1 == 0) goto L_0x006d
            byte[] r1 = r7._inputBuffer
            int r2 = r7._inputPtr
            byte r1 = r1[r2]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r1 = r1 << 8
            byte[] r2 = r7._inputBuffer
            int r5 = r7._inputPtr
            int r5 = r5 + r3
            byte r2 = r2[r5]
            r2 = r2 & 255(0xff, float:3.57E-43)
            r1 = r1 | r2
            boolean r1 = r7.checkUTF16(r1)
            if (r1 == 0) goto L_0x006d
            goto L_0x006e
        L_0x006d:
            r3 = r4
        L_0x006e:
            if (r3 != 0) goto L_0x0073
            com.fasterxml.jackson.core.JsonEncoding r0 = com.fasterxml.jackson.core.JsonEncoding.UTF8
            goto L_0x0098
        L_0x0073:
            int r1 = r7._bytesPerChar
            if (r1 == r0) goto L_0x008f
            switch(r1) {
                case 1: goto L_0x008c;
                case 2: goto L_0x0082;
                default: goto L_0x007a;
            }
        L_0x007a:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "Internal error"
            r0.<init>(r1)
            throw r0
        L_0x0082:
            boolean r0 = r7._bigEndian
            if (r0 == 0) goto L_0x0089
            com.fasterxml.jackson.core.JsonEncoding r0 = com.fasterxml.jackson.core.JsonEncoding.UTF16_BE
            goto L_0x0098
        L_0x0089:
            com.fasterxml.jackson.core.JsonEncoding r0 = com.fasterxml.jackson.core.JsonEncoding.UTF16_LE
            goto L_0x0098
        L_0x008c:
            com.fasterxml.jackson.core.JsonEncoding r0 = com.fasterxml.jackson.core.JsonEncoding.UTF8
            goto L_0x0098
        L_0x008f:
            boolean r0 = r7._bigEndian
            if (r0 == 0) goto L_0x0096
            com.fasterxml.jackson.core.JsonEncoding r0 = com.fasterxml.jackson.core.JsonEncoding.UTF32_BE
            goto L_0x0098
        L_0x0096:
            com.fasterxml.jackson.core.JsonEncoding r0 = com.fasterxml.jackson.core.JsonEncoding.UTF32_LE
        L_0x0098:
            com.fasterxml.jackson.core.io.IOContext r1 = r7._context
            r1.setEncoding(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper.detectEncoding():com.fasterxml.jackson.core.JsonEncoding");
    }

    public Reader constructReader() throws IOException {
        JsonEncoding encoding = this._context.getEncoding();
        switch (encoding) {
            case UTF32_BE:
            case UTF32_LE:
                UTF32Reader uTF32Reader = new UTF32Reader(this._context, this._in, this._inputBuffer, this._inputPtr, this._inputEnd, this._context.getEncoding().isBigEndian());
                return uTF32Reader;
            case UTF16_BE:
            case UTF16_LE:
            case UTF8:
                InputStream inputStream = this._in;
                if (inputStream == 0) {
                    inputStream = new ByteArrayInputStream(this._inputBuffer, this._inputPtr, this._inputEnd);
                } else if (this._inputPtr < this._inputEnd) {
                    MergedStream mergedStream = new MergedStream(this._context, inputStream, this._inputBuffer, this._inputPtr, this._inputEnd);
                    inputStream = mergedStream;
                }
                return new InputStreamReader(inputStream, encoding.getJavaName());
            default:
                throw new RuntimeException("Internal error");
        }
    }

    public JsonParser constructParser(int i, ObjectCodec objectCodec, BytesToNameCanonicalizer bytesToNameCanonicalizer, CharsToNameCanonicalizer charsToNameCanonicalizer, boolean z, boolean z2) throws IOException, JsonParseException {
        boolean z3 = z;
        if (detectEncoding() != JsonEncoding.UTF8 || !z3) {
            boolean z4 = z2;
            ReaderBasedJsonParser readerBasedJsonParser = new ReaderBasedJsonParser(this._context, i, constructReader(), objectCodec, charsToNameCanonicalizer.makeChild(z, z2));
            return readerBasedJsonParser;
        }
        int i2 = i;
        ObjectCodec objectCodec2 = objectCodec;
        UTF8StreamJsonParser uTF8StreamJsonParser = new UTF8StreamJsonParser(this._context, i2, this._in, objectCodec2, bytesToNameCanonicalizer.makeChild(z3, z2), this._inputBuffer, this._inputPtr, this._inputEnd, this._bufferRecyclable);
        return uTF8StreamJsonParser;
    }

    public static MatchStrength hasJSONFormat(InputAccessor inputAccessor) throws IOException {
        if (!inputAccessor.hasMoreBytes()) {
            return MatchStrength.INCONCLUSIVE;
        }
        byte nextByte = inputAccessor.nextByte();
        if (nextByte == -17) {
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor.nextByte() != -69) {
                return MatchStrength.NO_MATCH;
            }
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor.nextByte() != -65) {
                return MatchStrength.NO_MATCH;
            }
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            nextByte = inputAccessor.nextByte();
        }
        int skipSpace = skipSpace(inputAccessor, nextByte);
        if (skipSpace < 0) {
            return MatchStrength.INCONCLUSIVE;
        }
        if (skipSpace == 123) {
            int skipSpace2 = skipSpace(inputAccessor);
            if (skipSpace2 < 0) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (skipSpace2 == 34 || skipSpace2 == 125) {
                return MatchStrength.SOLID_MATCH;
            }
            return MatchStrength.NO_MATCH;
        } else if (skipSpace == 91) {
            int skipSpace3 = skipSpace(inputAccessor);
            if (skipSpace3 < 0) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (skipSpace3 == 93 || skipSpace3 == 91) {
                return MatchStrength.SOLID_MATCH;
            }
            return MatchStrength.SOLID_MATCH;
        } else {
            MatchStrength matchStrength = MatchStrength.WEAK_MATCH;
            if (skipSpace == 34) {
                return matchStrength;
            }
            if (skipSpace <= 57 && skipSpace >= 48) {
                return matchStrength;
            }
            if (skipSpace == 45) {
                int skipSpace4 = skipSpace(inputAccessor);
                if (skipSpace4 < 0) {
                    return MatchStrength.INCONCLUSIVE;
                }
                if (skipSpace4 > 57 || skipSpace4 < 48) {
                    matchStrength = MatchStrength.NO_MATCH;
                }
                return matchStrength;
            } else if (skipSpace == 110) {
                return tryMatch(inputAccessor, "ull", matchStrength);
            } else {
                if (skipSpace == 116) {
                    return tryMatch(inputAccessor, "rue", matchStrength);
                }
                if (skipSpace == 102) {
                    return tryMatch(inputAccessor, "alse", matchStrength);
                }
                return MatchStrength.NO_MATCH;
            }
        }
    }

    private static MatchStrength tryMatch(InputAccessor inputAccessor, String str, MatchStrength matchStrength) throws IOException {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor.nextByte() != str.charAt(i)) {
                return MatchStrength.NO_MATCH;
            }
        }
        return matchStrength;
    }

    private static int skipSpace(InputAccessor inputAccessor) throws IOException {
        if (!inputAccessor.hasMoreBytes()) {
            return -1;
        }
        return skipSpace(inputAccessor, inputAccessor.nextByte());
    }

    private static int skipSpace(InputAccessor inputAccessor, byte b) throws IOException {
        while (true) {
            byte b2 = b & 255;
            if (b2 != 32 && b2 != 13 && b2 != 10 && b2 != 9) {
                return b2;
            }
            if (!inputAccessor.hasMoreBytes()) {
                return -1;
            }
            b = inputAccessor.nextByte();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0044  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean handleBOM(int r7) throws java.io.IOException {
        /*
            r6 = this;
            r0 = -16842752(0xfffffffffeff0000, float:-1.6947657E38)
            r1 = 65534(0xfffe, float:9.1833E-41)
            r2 = 65279(0xfeff, float:9.1475E-41)
            r3 = 0
            r4 = 1
            if (r7 == r0) goto L_0x0030
            r0 = -131072(0xfffffffffffe0000, float:NaN)
            r5 = 4
            if (r7 == r0) goto L_0x0026
            if (r7 == r2) goto L_0x001c
            if (r7 == r1) goto L_0x0016
            goto L_0x0035
        L_0x0016:
            java.lang.String r0 = "2143"
            r6.reportWeirdUCS4(r0)
            goto L_0x0030
        L_0x001c:
            r6._bigEndian = r4
            int r7 = r6._inputPtr
            int r7 = r7 + r5
            r6._inputPtr = r7
            r6._bytesPerChar = r5
            return r4
        L_0x0026:
            int r7 = r6._inputPtr
            int r7 = r7 + r5
            r6._inputPtr = r7
            r6._bytesPerChar = r5
            r6._bigEndian = r3
            return r4
        L_0x0030:
            java.lang.String r0 = "3412"
            r6.reportWeirdUCS4(r0)
        L_0x0035:
            int r0 = r7 >>> 16
            r5 = 2
            if (r0 != r2) goto L_0x0044
            int r7 = r6._inputPtr
            int r7 = r7 + r5
            r6._inputPtr = r7
            r6._bytesPerChar = r5
            r6._bigEndian = r4
            return r4
        L_0x0044:
            if (r0 != r1) goto L_0x0050
            int r7 = r6._inputPtr
            int r7 = r7 + r5
            r6._inputPtr = r7
            r6._bytesPerChar = r5
            r6._bigEndian = r3
            return r4
        L_0x0050:
            int r7 = r7 >>> 8
            r0 = 15711167(0xefbbbf, float:2.2016034E-38)
            if (r7 != r0) goto L_0x0062
            int r7 = r6._inputPtr
            int r7 = r7 + 3
            r6._inputPtr = r7
            r6._bytesPerChar = r4
            r6._bigEndian = r4
            return r4
        L_0x0062:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper.handleBOM(int):boolean");
    }

    private boolean checkUTF32(int i) throws IOException {
        if ((i >> 8) == 0) {
            this._bigEndian = true;
        } else if ((16777215 & i) == 0) {
            this._bigEndian = false;
        } else if ((-16711681 & i) == 0) {
            reportWeirdUCS4("3412");
        } else if ((i & -65281) != 0) {
            return false;
        } else {
            reportWeirdUCS4("2143");
        }
        this._bytesPerChar = 4;
        return true;
    }

    private boolean checkUTF16(int i) {
        if ((65280 & i) == 0) {
            this._bigEndian = true;
        } else if ((i & 255) != 0) {
            return false;
        } else {
            this._bigEndian = false;
        }
        this._bytesPerChar = 2;
        return true;
    }

    private void reportWeirdUCS4(String str) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("Unsupported UCS-4 endianness (");
        sb.append(str);
        sb.append(") detected");
        throw new CharConversionException(sb.toString());
    }

    /* access modifiers changed from: protected */
    public boolean ensureLoaded(int i) throws IOException {
        int i2;
        int i3 = this._inputEnd - this._inputPtr;
        while (i3 < i) {
            if (this._in == null) {
                i2 = -1;
            } else {
                i2 = this._in.read(this._inputBuffer, this._inputEnd, this._inputBuffer.length - this._inputEnd);
            }
            if (i2 < 1) {
                return false;
            }
            this._inputEnd += i2;
            i3 += i2;
        }
        return true;
    }
}
