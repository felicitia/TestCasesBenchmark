package com.bumptech.glide.load.resource.bitmap;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ImageHeaderParser {
    private static final byte[] a;
    private static final int[] b = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};
    private final b c;

    public enum ImageType {
        GIF(true),
        JPEG(false),
        PNG_A(true),
        PNG(false),
        UNKNOWN(false);
        
        private final boolean hasAlpha;

        private ImageType(boolean z) {
            this.hasAlpha = z;
        }

        public boolean hasAlpha() {
            return this.hasAlpha;
        }
    }

    private static class a {
        private final ByteBuffer a;

        public a(byte[] bArr) {
            this.a = ByteBuffer.wrap(bArr);
            this.a.order(ByteOrder.BIG_ENDIAN);
        }

        public void a(ByteOrder byteOrder) {
            this.a.order(byteOrder);
        }

        public int a() {
            return this.a.array().length;
        }

        public int a(int i) {
            return this.a.getInt(i);
        }

        public short b(int i) {
            return this.a.getShort(i);
        }
    }

    private static class b {
        private final InputStream a;

        public b(InputStream inputStream) {
            this.a = inputStream;
        }

        public int a() throws IOException {
            return ((this.a.read() << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (this.a.read() & 255);
        }

        public short b() throws IOException {
            return (short) (this.a.read() & 255);
        }

        public long a(long j) throws IOException {
            if (j < 0) {
                return 0;
            }
            long j2 = j;
            while (j2 > 0) {
                long skip = this.a.skip(j2);
                if (skip <= 0) {
                    if (this.a.read() == -1) {
                        break;
                    }
                    skip = 1;
                }
                j2 -= skip;
            }
            return j - j2;
        }

        public int a(byte[] bArr) throws IOException {
            int length = bArr.length;
            while (length > 0) {
                int read = this.a.read(bArr, bArr.length - length, length);
                if (read == -1) {
                    break;
                }
                length -= read;
            }
            return bArr.length - length;
        }

        public int c() throws IOException {
            return this.a.read();
        }
    }

    private static int a(int i, int i2) {
        return i + 2 + (12 * i2);
    }

    private static boolean a(int i) {
        return (i & 65496) == 65496 || i == 19789 || i == 18761;
    }

    static {
        byte[] bArr = new byte[0];
        try {
            bArr = "Exif\u0000\u0000".getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
        }
        a = bArr;
    }

    public ImageHeaderParser(InputStream inputStream) {
        this.c = new b(inputStream);
    }

    public boolean a() throws IOException {
        return b().hasAlpha();
    }

    public ImageType b() throws IOException {
        int a2 = this.c.a();
        if (a2 == 65496) {
            return ImageType.JPEG;
        }
        int a3 = ((a2 << 16) & SupportMenu.CATEGORY_MASK) | (this.c.a() & SupportMenu.USER_MASK);
        if (a3 == -1991225785) {
            this.c.a(21);
            return this.c.c() >= 3 ? ImageType.PNG_A : ImageType.PNG;
        } else if ((a3 >> 8) == 4671814) {
            return ImageType.GIF;
        } else {
            return ImageType.UNKNOWN;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003f A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int c() throws java.io.IOException {
        /*
            r7 = this;
            com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$b r0 = r7.c
            int r0 = r0.a()
            boolean r0 = a(r0)
            r1 = -1
            if (r0 != 0) goto L_0x000e
            return r1
        L_0x000e:
            byte[] r0 = r7.d()
            r2 = 0
            if (r0 == 0) goto L_0x001d
            int r3 = r0.length
            byte[] r4 = a
            int r4 = r4.length
            if (r3 <= r4) goto L_0x001d
            r3 = 1
            goto L_0x001e
        L_0x001d:
            r3 = r2
        L_0x001e:
            if (r3 == 0) goto L_0x0032
            r4 = r2
        L_0x0021:
            byte[] r5 = a
            int r5 = r5.length
            if (r4 >= r5) goto L_0x0032
            byte r5 = r0[r4]
            byte[] r6 = a
            byte r6 = r6[r4]
            if (r5 == r6) goto L_0x002f
            goto L_0x0033
        L_0x002f:
            int r4 = r4 + 1
            goto L_0x0021
        L_0x0032:
            r2 = r3
        L_0x0033:
            if (r2 == 0) goto L_0x003f
            com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$a r1 = new com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$a
            r1.<init>(r0)
            int r0 = a(r1)
            return r0
        L_0x003f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.ImageHeaderParser.c():int");
    }

    private byte[] d() throws IOException {
        short b2;
        int a2;
        long j;
        long a3;
        do {
            short b3 = this.c.b();
            if (b3 != 255) {
                if (Log.isLoggable("ImageHeaderParser", 3)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unknown segmentId=");
                    sb.append(b3);
                    Log.d("ImageHeaderParser", sb.toString());
                }
                return null;
            }
            b2 = this.c.b();
            if (b2 == 218) {
                return null;
            }
            if (b2 == 217) {
                if (Log.isLoggable("ImageHeaderParser", 3)) {
                    Log.d("ImageHeaderParser", "Found MARKER_EOI in exif segment");
                }
                return null;
            }
            a2 = this.c.a() - 2;
            if (b2 != 225) {
                j = (long) a2;
                a3 = this.c.a(j);
            } else {
                byte[] bArr = new byte[a2];
                int a4 = this.c.a(bArr);
                if (a4 == a2) {
                    return bArr;
                }
                if (Log.isLoggable("ImageHeaderParser", 3)) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Unable to read segment data, type: ");
                    sb2.append(b2);
                    sb2.append(", length: ");
                    sb2.append(a2);
                    sb2.append(", actually read: ");
                    sb2.append(a4);
                    Log.d("ImageHeaderParser", sb2.toString());
                }
                return null;
            }
        } while (a3 == j);
        if (Log.isLoggable("ImageHeaderParser", 3)) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Unable to skip enough data, type: ");
            sb3.append(b2);
            sb3.append(", wanted to skip: ");
            sb3.append(a2);
            sb3.append(", but actually skipped: ");
            sb3.append(a3);
            Log.d("ImageHeaderParser", sb3.toString());
        }
        return null;
    }

    private static int a(a aVar) {
        ByteOrder byteOrder;
        int length = "Exif\u0000\u0000".length();
        short b2 = aVar.b(length);
        if (b2 == 19789) {
            byteOrder = ByteOrder.BIG_ENDIAN;
        } else if (b2 == 18761) {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        } else {
            if (Log.isLoggable("ImageHeaderParser", 3)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unknown endianness = ");
                sb.append(b2);
                Log.d("ImageHeaderParser", sb.toString());
            }
            byteOrder = ByteOrder.BIG_ENDIAN;
        }
        aVar.a(byteOrder);
        int a2 = aVar.a(length + 4) + length;
        short b3 = aVar.b(a2);
        for (int i = 0; i < b3; i++) {
            int a3 = a(a2, i);
            short b4 = aVar.b(a3);
            if (b4 == 274) {
                short b5 = aVar.b(a3 + 2);
                if (b5 >= 1 && b5 <= 12) {
                    int a4 = aVar.a(a3 + 4);
                    if (a4 >= 0) {
                        if (Log.isLoggable("ImageHeaderParser", 3)) {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("Got tagIndex=");
                            sb2.append(i);
                            sb2.append(" tagType=");
                            sb2.append(b4);
                            sb2.append(" formatCode=");
                            sb2.append(b5);
                            sb2.append(" componentCount=");
                            sb2.append(a4);
                            Log.d("ImageHeaderParser", sb2.toString());
                        }
                        int i2 = a4 + b[b5];
                        if (i2 <= 4) {
                            int i3 = a3 + 8;
                            if (i3 < 0 || i3 > aVar.a()) {
                                if (Log.isLoggable("ImageHeaderParser", 3)) {
                                    StringBuilder sb3 = new StringBuilder();
                                    sb3.append("Illegal tagValueOffset=");
                                    sb3.append(i3);
                                    sb3.append(" tagType=");
                                    sb3.append(b4);
                                    Log.d("ImageHeaderParser", sb3.toString());
                                }
                            } else if (i2 >= 0 && i2 + i3 <= aVar.a()) {
                                return aVar.b(i3);
                            } else {
                                if (Log.isLoggable("ImageHeaderParser", 3)) {
                                    StringBuilder sb4 = new StringBuilder();
                                    sb4.append("Illegal number of bytes for TI tag data tagType=");
                                    sb4.append(b4);
                                    Log.d("ImageHeaderParser", sb4.toString());
                                }
                            }
                        } else if (Log.isLoggable("ImageHeaderParser", 3)) {
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append("Got byte count > 4, not orientation, continuing, formatCode=");
                            sb5.append(b5);
                            Log.d("ImageHeaderParser", sb5.toString());
                        }
                    } else if (Log.isLoggable("ImageHeaderParser", 3)) {
                        Log.d("ImageHeaderParser", "Negative tiff component count");
                    }
                } else if (Log.isLoggable("ImageHeaderParser", 3)) {
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append("Got invalid format code=");
                    sb6.append(b5);
                    Log.d("ImageHeaderParser", sb6.toString());
                }
            }
        }
        return -1;
    }
}
