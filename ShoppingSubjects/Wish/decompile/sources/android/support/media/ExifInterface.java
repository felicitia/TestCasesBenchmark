package android.support.media;

import android.content.res.AssetManager.AssetInputStream;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class ExifInterface {
    /* access modifiers changed from: private */
    public static final Charset ASCII = Charset.forName("US-ASCII");
    private static final int[] BITS_PER_SAMPLE_GREYSCALE_1 = {4};
    private static final int[] BITS_PER_SAMPLE_GREYSCALE_2 = {8};
    private static final int[] BITS_PER_SAMPLE_RGB = {8, 8, 8};
    /* access modifiers changed from: private */
    public static final byte[] EXIF_ASCII_PREFIX = {65, 83, 67, 73, 73, 0, 0, 0};
    private static final ExifTag[] EXIF_POINTER_TAGS = {new ExifTag("SubIFDPointer", 330, 4), new ExifTag("ExifIFDPointer", 34665, 4), new ExifTag("GPSInfoIFDPointer", 34853, 4), new ExifTag("InteroperabilityIFDPointer", 40965, 4), new ExifTag("CameraSettingsIFDPointer", 8224, 1), new ExifTag("ImageProcessingIFDPointer", 8256, 1)};
    static final ExifTag[][] EXIF_TAGS = {IFD_TIFF_TAGS, IFD_EXIF_TAGS, IFD_GPS_TAGS, IFD_INTEROPERABILITY_TAGS, IFD_THUMBNAIL_TAGS, IFD_TIFF_TAGS, ORF_MAKER_NOTE_TAGS, ORF_CAMERA_SETTINGS_TAGS, ORF_IMAGE_PROCESSING_TAGS, PEF_TAGS};
    private static final List<Integer> FLIPPED_ROTATION_ORDER = Arrays.asList(new Integer[]{Integer.valueOf(2), Integer.valueOf(7), Integer.valueOf(4), Integer.valueOf(5)});
    static final byte[] IDENTIFIER_EXIF_APP1 = "Exif\u0000\u0000".getBytes(ASCII);
    private static final ExifTag[] IFD_EXIF_TAGS;
    static final int[] IFD_FORMAT_BYTES_PER_FORMAT = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1};
    static final String[] IFD_FORMAT_NAMES = {"", "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE"};
    private static final ExifTag[] IFD_GPS_TAGS = {new ExifTag("GPSVersionID", 0, 1), new ExifTag("GPSLatitudeRef", 1, 2), new ExifTag("GPSLatitude", 2, 5), new ExifTag("GPSLongitudeRef", 3, 2), new ExifTag("GPSLongitude", 4, 5), new ExifTag("GPSAltitudeRef", 5, 1), new ExifTag("GPSAltitude", 6, 5), new ExifTag("GPSTimeStamp", 7, 5), new ExifTag("GPSSatellites", 8, 2), new ExifTag("GPSStatus", 9, 2), new ExifTag("GPSMeasureMode", 10, 2), new ExifTag("GPSDOP", 11, 5), new ExifTag("GPSSpeedRef", 12, 2), new ExifTag("GPSSpeed", 13, 5), new ExifTag("GPSTrackRef", 14, 2), new ExifTag("GPSTrack", 15, 5), new ExifTag("GPSImgDirectionRef", 16, 2), new ExifTag("GPSImgDirection", 17, 5), new ExifTag("GPSMapDatum", 18, 2), new ExifTag("GPSDestLatitudeRef", 19, 2), new ExifTag("GPSDestLatitude", 20, 5), new ExifTag("GPSDestLongitudeRef", 21, 2), new ExifTag("GPSDestLongitude", 22, 5), new ExifTag("GPSDestBearingRef", 23, 2), new ExifTag("GPSDestBearing", 24, 5), new ExifTag("GPSDestDistanceRef", 25, 2), new ExifTag("GPSDestDistance", 26, 5), new ExifTag("GPSProcessingMethod", 27, 7), new ExifTag("GPSAreaInformation", 28, 7), new ExifTag("GPSDateStamp", 29, 2), new ExifTag("GPSDifferential", 30, 3)};
    private static final ExifTag[] IFD_INTEROPERABILITY_TAGS = {new ExifTag("InteroperabilityIndex", 1, 2)};
    private static final ExifTag[] IFD_THUMBNAIL_TAGS;
    private static final ExifTag[] IFD_TIFF_TAGS;
    private static final ExifTag JPEG_INTERCHANGE_FORMAT_LENGTH_TAG = new ExifTag("JPEGInterchangeFormatLength", 514, 4);
    private static final ExifTag JPEG_INTERCHANGE_FORMAT_TAG = new ExifTag("JPEGInterchangeFormat", 513, 4);
    static final byte[] JPEG_SIGNATURE = {-1, -40, -1};
    private static final ExifTag[] ORF_CAMERA_SETTINGS_TAGS = {new ExifTag("PreviewImageStart", 257, 4), new ExifTag("PreviewImageLength", 258, 4)};
    private static final ExifTag[] ORF_IMAGE_PROCESSING_TAGS = {new ExifTag("AspectFrame", 4371, 3)};
    private static final byte[] ORF_MAKER_NOTE_HEADER_1 = {79, 76, 89, 77, 80, 0};
    private static final byte[] ORF_MAKER_NOTE_HEADER_2 = {79, 76, 89, 77, 80, 85, 83, 0, 73, 73};
    private static final ExifTag[] ORF_MAKER_NOTE_TAGS = {new ExifTag("ThumbnailImage", 256, 7), new ExifTag("CameraSettingsIFDPointer", 8224, 4), new ExifTag("ImageProcessingIFDPointer", 8256, 4)};
    private static final ExifTag[] PEF_TAGS = {new ExifTag("ColorSpace", 55, 3)};
    private static final List<Integer> ROTATION_ORDER = Arrays.asList(new Integer[]{Integer.valueOf(1), Integer.valueOf(6), Integer.valueOf(3), Integer.valueOf(8)});
    private static final ExifTag TAG_RAF_IMAGE_SIZE = new ExifTag("StripOffsets", 273, 3);
    private static final HashMap<Integer, Integer> sExifPointerTagMap = new HashMap<>();
    private static final HashMap<Integer, ExifTag>[] sExifTagMapsForReading = new HashMap[EXIF_TAGS.length];
    private static final HashMap<String, ExifTag>[] sExifTagMapsForWriting = new HashMap[EXIF_TAGS.length];
    private static SimpleDateFormat sFormatter = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
    private static final Pattern sGpsTimestampPattern = Pattern.compile("^([0-9][0-9]):([0-9][0-9]):([0-9][0-9])$");
    private static final Pattern sNonZeroTimePattern = Pattern.compile(".*[1-9].*");
    private static final HashSet<String> sTagSetForCompatibility = new HashSet<>(Arrays.asList(new String[]{"FNumber", "DigitalZoomRatio", "ExposureTime", "SubjectDistance", "GPSTimeStamp"}));
    private final AssetInputStream mAssetInputStream;
    private final HashMap<String, ExifAttribute>[] mAttributes = new HashMap[EXIF_TAGS.length];
    private ByteOrder mExifByteOrder = ByteOrder.BIG_ENDIAN;
    private int mExifOffset;
    private final String mFilename;
    private boolean mHasThumbnail;
    private boolean mIsSupportedFile;
    private int mMimeType;
    private int mOrfMakerNoteOffset;
    private int mOrfThumbnailLength;
    private int mOrfThumbnailOffset;
    private int mRw2JpgFromRawOffset;
    private byte[] mThumbnailBytes;
    private int mThumbnailCompression;
    private int mThumbnailLength;
    private int mThumbnailOffset;

    private static class ByteOrderedDataInputStream extends InputStream implements DataInput {
        private static final ByteOrder BIG_ENDIAN = ByteOrder.BIG_ENDIAN;
        private static final ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
        private ByteOrder mByteOrder;
        private DataInputStream mDataInputStream;
        /* access modifiers changed from: private */
        public final int mLength;
        /* access modifiers changed from: private */
        public int mPosition;

        public ByteOrderedDataInputStream(InputStream inputStream) throws IOException {
            this.mByteOrder = ByteOrder.BIG_ENDIAN;
            this.mDataInputStream = new DataInputStream(inputStream);
            this.mLength = this.mDataInputStream.available();
            this.mPosition = 0;
            this.mDataInputStream.mark(this.mLength);
        }

        public ByteOrderedDataInputStream(byte[] bArr) throws IOException {
            this((InputStream) new ByteArrayInputStream(bArr));
        }

        public void setByteOrder(ByteOrder byteOrder) {
            this.mByteOrder = byteOrder;
        }

        public void seek(long j) throws IOException {
            if (((long) this.mPosition) > j) {
                this.mPosition = 0;
                this.mDataInputStream.reset();
                this.mDataInputStream.mark(this.mLength);
            } else {
                j -= (long) this.mPosition;
            }
            int i = (int) j;
            if (skipBytes(i) != i) {
                throw new IOException("Couldn't seek up to the byteCount");
            }
        }

        public int peek() {
            return this.mPosition;
        }

        public int available() throws IOException {
            return this.mDataInputStream.available();
        }

        public int read() throws IOException {
            this.mPosition++;
            return this.mDataInputStream.read();
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read = this.mDataInputStream.read(bArr, i, i2);
            this.mPosition += read;
            return read;
        }

        public int readUnsignedByte() throws IOException {
            this.mPosition++;
            return this.mDataInputStream.readUnsignedByte();
        }

        public String readLine() throws IOException {
            Log.d("ExifInterface", "Currently unsupported");
            return null;
        }

        public boolean readBoolean() throws IOException {
            this.mPosition++;
            return this.mDataInputStream.readBoolean();
        }

        public char readChar() throws IOException {
            this.mPosition += 2;
            return this.mDataInputStream.readChar();
        }

        public String readUTF() throws IOException {
            this.mPosition += 2;
            return this.mDataInputStream.readUTF();
        }

        public void readFully(byte[] bArr, int i, int i2) throws IOException {
            this.mPosition += i2;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            } else if (this.mDataInputStream.read(bArr, i, i2) != i2) {
                throw new IOException("Couldn't read up to the length of buffer");
            }
        }

        public void readFully(byte[] bArr) throws IOException {
            this.mPosition += bArr.length;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            } else if (this.mDataInputStream.read(bArr, 0, bArr.length) != bArr.length) {
                throw new IOException("Couldn't read up to the length of buffer");
            }
        }

        public byte readByte() throws IOException {
            this.mPosition++;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            }
            int read = this.mDataInputStream.read();
            if (read >= 0) {
                return (byte) read;
            }
            throw new EOFException();
        }

        public short readShort() throws IOException {
            this.mPosition += 2;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            }
            int read = this.mDataInputStream.read();
            int read2 = this.mDataInputStream.read();
            if ((read | read2) < 0) {
                throw new EOFException();
            } else if (this.mByteOrder == LITTLE_ENDIAN) {
                return (short) ((read2 << 8) + read);
            } else {
                if (this.mByteOrder == BIG_ENDIAN) {
                    return (short) ((read << 8) + read2);
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid byte order: ");
                sb.append(this.mByteOrder);
                throw new IOException(sb.toString());
            }
        }

        public int readInt() throws IOException {
            this.mPosition += 4;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            }
            int read = this.mDataInputStream.read();
            int read2 = this.mDataInputStream.read();
            int read3 = this.mDataInputStream.read();
            int read4 = this.mDataInputStream.read();
            if ((read | read2 | read3 | read4) < 0) {
                throw new EOFException();
            } else if (this.mByteOrder == LITTLE_ENDIAN) {
                return (read4 << 24) + (read3 << 16) + (read2 << 8) + read;
            } else {
                if (this.mByteOrder == BIG_ENDIAN) {
                    return (read << 24) + (read2 << 16) + (read3 << 8) + read4;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid byte order: ");
                sb.append(this.mByteOrder);
                throw new IOException(sb.toString());
            }
        }

        public int skipBytes(int i) throws IOException {
            int min = Math.min(i, this.mLength - this.mPosition);
            int i2 = 0;
            while (i2 < min) {
                i2 += this.mDataInputStream.skipBytes(min - i2);
            }
            this.mPosition += i2;
            return i2;
        }

        public int readUnsignedShort() throws IOException {
            this.mPosition += 2;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            }
            int read = this.mDataInputStream.read();
            int read2 = this.mDataInputStream.read();
            if ((read | read2) < 0) {
                throw new EOFException();
            } else if (this.mByteOrder == LITTLE_ENDIAN) {
                return (read2 << 8) + read;
            } else {
                if (this.mByteOrder == BIG_ENDIAN) {
                    return (read << 8) + read2;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid byte order: ");
                sb.append(this.mByteOrder);
                throw new IOException(sb.toString());
            }
        }

        public long readUnsignedInt() throws IOException {
            return ((long) readInt()) & 4294967295L;
        }

        public long readLong() throws IOException {
            this.mPosition += 8;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            }
            int read = this.mDataInputStream.read();
            int read2 = this.mDataInputStream.read();
            int read3 = this.mDataInputStream.read();
            int read4 = this.mDataInputStream.read();
            int read5 = this.mDataInputStream.read();
            int read6 = this.mDataInputStream.read();
            int read7 = this.mDataInputStream.read();
            int read8 = this.mDataInputStream.read();
            if ((read | read2 | read3 | read4 | read5 | read6 | read7 | read8) < 0) {
                throw new EOFException();
            } else if (this.mByteOrder == LITTLE_ENDIAN) {
                return (((long) read8) << 56) + (((long) read7) << 48) + (((long) read6) << 40) + (((long) read5) << 32) + (((long) read4) << 24) + (((long) read3) << 16) + (((long) read2) << 8) + ((long) read);
            } else {
                int i = read2;
                if (this.mByteOrder == BIG_ENDIAN) {
                    return (((long) read) << 56) + (((long) i) << 48) + (((long) read3) << 40) + (((long) read4) << 32) + (((long) read5) << 24) + (((long) read6) << 16) + (((long) read7) << 8) + ((long) read8);
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid byte order: ");
                sb.append(this.mByteOrder);
                throw new IOException(sb.toString());
            }
        }

        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readInt());
        }

        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readLong());
        }
    }

    private static class ExifAttribute {
        public final byte[] bytes;
        public final int format;
        public final int numberOfComponents;

        private ExifAttribute(int i, int i2, byte[] bArr) {
            this.format = i;
            this.numberOfComponents = i2;
            this.bytes = bArr;
        }

        public static ExifAttribute createUShort(int[] iArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[3] * iArr.length)]);
            wrap.order(byteOrder);
            for (int i : iArr) {
                wrap.putShort((short) i);
            }
            return new ExifAttribute(3, iArr.length, wrap.array());
        }

        public static ExifAttribute createUShort(int i, ByteOrder byteOrder) {
            return createUShort(new int[]{i}, byteOrder);
        }

        public static ExifAttribute createULong(long[] jArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[4] * jArr.length)]);
            wrap.order(byteOrder);
            for (long j : jArr) {
                wrap.putInt((int) j);
            }
            return new ExifAttribute(4, jArr.length, wrap.array());
        }

        public static ExifAttribute createULong(long j, ByteOrder byteOrder) {
            return createULong(new long[]{j}, byteOrder);
        }

        public static ExifAttribute createString(String str) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(0);
            byte[] bytes2 = sb.toString().getBytes(ExifInterface.ASCII);
            return new ExifAttribute(2, bytes2.length, bytes2);
        }

        public static ExifAttribute createURational(Rational[] rationalArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[5] * rationalArr.length)]);
            wrap.order(byteOrder);
            for (Rational rational : rationalArr) {
                wrap.putInt((int) rational.numerator);
                wrap.putInt((int) rational.denominator);
            }
            return new ExifAttribute(5, rationalArr.length, wrap.array());
        }

        public static ExifAttribute createURational(Rational rational, ByteOrder byteOrder) {
            return createURational(new Rational[]{rational}, byteOrder);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            sb.append(ExifInterface.IFD_FORMAT_NAMES[this.format]);
            sb.append(", data length:");
            sb.append(this.bytes.length);
            sb.append(")");
            return sb.toString();
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Removed duplicated region for block: B:177:0x0202 A[SYNTHETIC, Splitter:B:177:0x0202] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object getValue(java.nio.ByteOrder r11) {
            /*
                r10 = this;
                r0 = 0
                android.support.media.ExifInterface$ByteOrderedDataInputStream r1 = new android.support.media.ExifInterface$ByteOrderedDataInputStream     // Catch:{ IOException -> 0x01e7, all -> 0x01e4 }
                byte[] r2 = r10.bytes     // Catch:{ IOException -> 0x01e7, all -> 0x01e4 }
                r1.<init>(r2)     // Catch:{ IOException -> 0x01e7, all -> 0x01e4 }
                r1.setByteOrder(r11)     // Catch:{ IOException -> 0x01e2 }
                int r11 = r10.format     // Catch:{ IOException -> 0x01e2 }
                r2 = 1
                r3 = 0
                switch(r11) {
                    case 1: goto L_0x018f;
                    case 2: goto L_0x0132;
                    case 3: goto L_0x0112;
                    case 4: goto L_0x00f2;
                    case 5: goto L_0x00c7;
                    case 6: goto L_0x018f;
                    case 7: goto L_0x0132;
                    case 8: goto L_0x00a7;
                    case 9: goto L_0x0087;
                    case 10: goto L_0x005a;
                    case 11: goto L_0x0039;
                    case 12: goto L_0x0019;
                    default: goto L_0x0012;
                }
            L_0x0012:
                if (r1 == 0) goto L_0x01e1
                r1.close()     // Catch:{ IOException -> 0x01d9 }
                goto L_0x01e1
            L_0x0019:
                int r11 = r10.numberOfComponents     // Catch:{ IOException -> 0x01e2 }
                double[] r11 = new double[r11]     // Catch:{ IOException -> 0x01e2 }
            L_0x001d:
                int r2 = r10.numberOfComponents     // Catch:{ IOException -> 0x01e2 }
                if (r3 >= r2) goto L_0x002a
                double r4 = r1.readDouble()     // Catch:{ IOException -> 0x01e2 }
                r11[r3] = r4     // Catch:{ IOException -> 0x01e2 }
                int r3 = r3 + 1
                goto L_0x001d
            L_0x002a:
                if (r1 == 0) goto L_0x0038
                r1.close()     // Catch:{ IOException -> 0x0030 }
                goto L_0x0038
            L_0x0030:
                r0 = move-exception
                java.lang.String r1 = "ExifInterface"
                java.lang.String r2 = "IOException occurred while closing InputStream"
                android.util.Log.e(r1, r2, r0)
            L_0x0038:
                return r11
            L_0x0039:
                int r11 = r10.numberOfComponents     // Catch:{ IOException -> 0x01e2 }
                double[] r11 = new double[r11]     // Catch:{ IOException -> 0x01e2 }
            L_0x003d:
                int r2 = r10.numberOfComponents     // Catch:{ IOException -> 0x01e2 }
                if (r3 >= r2) goto L_0x004b
                float r2 = r1.readFloat()     // Catch:{ IOException -> 0x01e2 }
                double r4 = (double) r2     // Catch:{ IOException -> 0x01e2 }
                r11[r3] = r4     // Catch:{ IOException -> 0x01e2 }
                int r3 = r3 + 1
                goto L_0x003d
            L_0x004b:
                if (r1 == 0) goto L_0x0059
                r1.close()     // Catch:{ IOException -> 0x0051 }
                goto L_0x0059
            L_0x0051:
                r0 = move-exception
                java.lang.String r1 = "ExifInterface"
                java.lang.String r2 = "IOException occurred while closing InputStream"
                android.util.Log.e(r1, r2, r0)
            L_0x0059:
                return r11
            L_0x005a:
                int r11 = r10.numberOfComponents     // Catch:{ IOException -> 0x01e2 }
                android.support.media.ExifInterface$Rational[] r11 = new android.support.media.ExifInterface.Rational[r11]     // Catch:{ IOException -> 0x01e2 }
            L_0x005e:
                int r2 = r10.numberOfComponents     // Catch:{ IOException -> 0x01e2 }
                if (r3 >= r2) goto L_0x0078
                int r2 = r1.readInt()     // Catch:{ IOException -> 0x01e2 }
                long r5 = (long) r2     // Catch:{ IOException -> 0x01e2 }
                int r2 = r1.readInt()     // Catch:{ IOException -> 0x01e2 }
                long r7 = (long) r2     // Catch:{ IOException -> 0x01e2 }
                android.support.media.ExifInterface$Rational r2 = new android.support.media.ExifInterface$Rational     // Catch:{ IOException -> 0x01e2 }
                r9 = 0
                r4 = r2
                r4.<init>(r5, r7)     // Catch:{ IOException -> 0x01e2 }
                r11[r3] = r2     // Catch:{ IOException -> 0x01e2 }
                int r3 = r3 + 1
                goto L_0x005e
            L_0x0078:
                if (r1 == 0) goto L_0x0086
                r1.close()     // Catch:{ IOException -> 0x007e }
                goto L_0x0086
            L_0x007e:
                r0 = move-exception
                java.lang.String r1 = "ExifInterface"
                java.lang.String r2 = "IOException occurred while closing InputStream"
                android.util.Log.e(r1, r2, r0)
            L_0x0086:
                return r11
            L_0x0087:
                int r11 = r10.numberOfComponents     // Catch:{ IOException -> 0x01e2 }
                int[] r11 = new int[r11]     // Catch:{ IOException -> 0x01e2 }
            L_0x008b:
                int r2 = r10.numberOfComponents     // Catch:{ IOException -> 0x01e2 }
                if (r3 >= r2) goto L_0x0098
                int r2 = r1.readInt()     // Catch:{ IOException -> 0x01e2 }
                r11[r3] = r2     // Catch:{ IOException -> 0x01e2 }
                int r3 = r3 + 1
                goto L_0x008b
            L_0x0098:
                if (r1 == 0) goto L_0x00a6
                r1.close()     // Catch:{ IOException -> 0x009e }
                goto L_0x00a6
            L_0x009e:
                r0 = move-exception
                java.lang.String r1 = "ExifInterface"
                java.lang.String r2 = "IOException occurred while closing InputStream"
                android.util.Log.e(r1, r2, r0)
            L_0x00a6:
                return r11
            L_0x00a7:
                int r11 = r10.numberOfComponents     // Catch:{ IOException -> 0x01e2 }
                int[] r11 = new int[r11]     // Catch:{ IOException -> 0x01e2 }
            L_0x00ab:
                int r2 = r10.numberOfComponents     // Catch:{ IOException -> 0x01e2 }
                if (r3 >= r2) goto L_0x00b8
                short r2 = r1.readShort()     // Catch:{ IOException -> 0x01e2 }
                r11[r3] = r2     // Catch:{ IOException -> 0x01e2 }
                int r3 = r3 + 1
                goto L_0x00ab
            L_0x00b8:
                if (r1 == 0) goto L_0x00c6
                r1.close()     // Catch:{ IOException -> 0x00be }
                goto L_0x00c6
            L_0x00be:
                r0 = move-exception
                java.lang.String r1 = "ExifInterface"
                java.lang.String r2 = "IOException occurred while closing InputStream"
                android.util.Log.e(r1, r2, r0)
            L_0x00c6:
                return r11
            L_0x00c7:
                int r11 = r10.numberOfComponents     // Catch:{ IOException -> 0x01e2 }
                android.support.media.ExifInterface$Rational[] r11 = new android.support.media.ExifInterface.Rational[r11]     // Catch:{ IOException -> 0x01e2 }
            L_0x00cb:
                int r2 = r10.numberOfComponents     // Catch:{ IOException -> 0x01e2 }
                if (r3 >= r2) goto L_0x00e3
                long r5 = r1.readUnsignedInt()     // Catch:{ IOException -> 0x01e2 }
                long r7 = r1.readUnsignedInt()     // Catch:{ IOException -> 0x01e2 }
                android.support.media.ExifInterface$Rational r2 = new android.support.media.ExifInterface$Rational     // Catch:{ IOException -> 0x01e2 }
                r9 = 0
                r4 = r2
                r4.<init>(r5, r7)     // Catch:{ IOException -> 0x01e2 }
                r11[r3] = r2     // Catch:{ IOException -> 0x01e2 }
                int r3 = r3 + 1
                goto L_0x00cb
            L_0x00e3:
                if (r1 == 0) goto L_0x00f1
                r1.close()     // Catch:{ IOException -> 0x00e9 }
                goto L_0x00f1
            L_0x00e9:
                r0 = move-exception
                java.lang.String r1 = "ExifInterface"
                java.lang.String r2 = "IOException occurred while closing InputStream"
                android.util.Log.e(r1, r2, r0)
            L_0x00f1:
                return r11
            L_0x00f2:
                int r11 = r10.numberOfComponents     // Catch:{ IOException -> 0x01e2 }
                long[] r11 = new long[r11]     // Catch:{ IOException -> 0x01e2 }
            L_0x00f6:
                int r2 = r10.numberOfComponents     // Catch:{ IOException -> 0x01e2 }
                if (r3 >= r2) goto L_0x0103
                long r4 = r1.readUnsignedInt()     // Catch:{ IOException -> 0x01e2 }
                r11[r3] = r4     // Catch:{ IOException -> 0x01e2 }
                int r3 = r3 + 1
                goto L_0x00f6
            L_0x0103:
                if (r1 == 0) goto L_0x0111
                r1.close()     // Catch:{ IOException -> 0x0109 }
                goto L_0x0111
            L_0x0109:
                r0 = move-exception
                java.lang.String r1 = "ExifInterface"
                java.lang.String r2 = "IOException occurred while closing InputStream"
                android.util.Log.e(r1, r2, r0)
            L_0x0111:
                return r11
            L_0x0112:
                int r11 = r10.numberOfComponents     // Catch:{ IOException -> 0x01e2 }
                int[] r11 = new int[r11]     // Catch:{ IOException -> 0x01e2 }
            L_0x0116:
                int r2 = r10.numberOfComponents     // Catch:{ IOException -> 0x01e2 }
                if (r3 >= r2) goto L_0x0123
                int r2 = r1.readUnsignedShort()     // Catch:{ IOException -> 0x01e2 }
                r11[r3] = r2     // Catch:{ IOException -> 0x01e2 }
                int r3 = r3 + 1
                goto L_0x0116
            L_0x0123:
                if (r1 == 0) goto L_0x0131
                r1.close()     // Catch:{ IOException -> 0x0129 }
                goto L_0x0131
            L_0x0129:
                r0 = move-exception
                java.lang.String r1 = "ExifInterface"
                java.lang.String r2 = "IOException occurred while closing InputStream"
                android.util.Log.e(r1, r2, r0)
            L_0x0131:
                return r11
            L_0x0132:
                int r11 = r10.numberOfComponents     // Catch:{ IOException -> 0x01e2 }
                byte[] r4 = android.support.media.ExifInterface.EXIF_ASCII_PREFIX     // Catch:{ IOException -> 0x01e2 }
                int r4 = r4.length     // Catch:{ IOException -> 0x01e2 }
                if (r11 < r4) goto L_0x015b
                r11 = 0
            L_0x013c:
                byte[] r4 = android.support.media.ExifInterface.EXIF_ASCII_PREFIX     // Catch:{ IOException -> 0x01e2 }
                int r4 = r4.length     // Catch:{ IOException -> 0x01e2 }
                if (r11 >= r4) goto L_0x0154
                byte[] r4 = r10.bytes     // Catch:{ IOException -> 0x01e2 }
                byte r4 = r4[r11]     // Catch:{ IOException -> 0x01e2 }
                byte[] r5 = android.support.media.ExifInterface.EXIF_ASCII_PREFIX     // Catch:{ IOException -> 0x01e2 }
                byte r5 = r5[r11]     // Catch:{ IOException -> 0x01e2 }
                if (r4 == r5) goto L_0x0151
                r2 = 0
                goto L_0x0154
            L_0x0151:
                int r11 = r11 + 1
                goto L_0x013c
            L_0x0154:
                if (r2 == 0) goto L_0x015b
                byte[] r11 = android.support.media.ExifInterface.EXIF_ASCII_PREFIX     // Catch:{ IOException -> 0x01e2 }
                int r3 = r11.length     // Catch:{ IOException -> 0x01e2 }
            L_0x015b:
                java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01e2 }
                r11.<init>()     // Catch:{ IOException -> 0x01e2 }
            L_0x0160:
                int r2 = r10.numberOfComponents     // Catch:{ IOException -> 0x01e2 }
                if (r3 >= r2) goto L_0x017c
                byte[] r2 = r10.bytes     // Catch:{ IOException -> 0x01e2 }
                byte r2 = r2[r3]     // Catch:{ IOException -> 0x01e2 }
                if (r2 != 0) goto L_0x016b
                goto L_0x017c
            L_0x016b:
                r4 = 32
                if (r2 < r4) goto L_0x0174
                char r2 = (char) r2     // Catch:{ IOException -> 0x01e2 }
                r11.append(r2)     // Catch:{ IOException -> 0x01e2 }
                goto L_0x0179
            L_0x0174:
                r2 = 63
                r11.append(r2)     // Catch:{ IOException -> 0x01e2 }
            L_0x0179:
                int r3 = r3 + 1
                goto L_0x0160
            L_0x017c:
                java.lang.String r11 = r11.toString()     // Catch:{ IOException -> 0x01e2 }
                if (r1 == 0) goto L_0x018e
                r1.close()     // Catch:{ IOException -> 0x0186 }
                goto L_0x018e
            L_0x0186:
                r0 = move-exception
                java.lang.String r1 = "ExifInterface"
                java.lang.String r2 = "IOException occurred while closing InputStream"
                android.util.Log.e(r1, r2, r0)
            L_0x018e:
                return r11
            L_0x018f:
                byte[] r11 = r10.bytes     // Catch:{ IOException -> 0x01e2 }
                int r11 = r11.length     // Catch:{ IOException -> 0x01e2 }
                if (r11 != r2) goto L_0x01bf
                byte[] r11 = r10.bytes     // Catch:{ IOException -> 0x01e2 }
                byte r11 = r11[r3]     // Catch:{ IOException -> 0x01e2 }
                if (r11 < 0) goto L_0x01bf
                byte[] r11 = r10.bytes     // Catch:{ IOException -> 0x01e2 }
                byte r11 = r11[r3]     // Catch:{ IOException -> 0x01e2 }
                if (r11 > r2) goto L_0x01bf
                java.lang.String r11 = new java.lang.String     // Catch:{ IOException -> 0x01e2 }
                char[] r2 = new char[r2]     // Catch:{ IOException -> 0x01e2 }
                byte[] r4 = r10.bytes     // Catch:{ IOException -> 0x01e2 }
                byte r4 = r4[r3]     // Catch:{ IOException -> 0x01e2 }
                int r4 = r4 + 48
                char r4 = (char) r4     // Catch:{ IOException -> 0x01e2 }
                r2[r3] = r4     // Catch:{ IOException -> 0x01e2 }
                r11.<init>(r2)     // Catch:{ IOException -> 0x01e2 }
                if (r1 == 0) goto L_0x01be
                r1.close()     // Catch:{ IOException -> 0x01b6 }
                goto L_0x01be
            L_0x01b6:
                r0 = move-exception
                java.lang.String r1 = "ExifInterface"
                java.lang.String r2 = "IOException occurred while closing InputStream"
                android.util.Log.e(r1, r2, r0)
            L_0x01be:
                return r11
            L_0x01bf:
                java.lang.String r11 = new java.lang.String     // Catch:{ IOException -> 0x01e2 }
                byte[] r2 = r10.bytes     // Catch:{ IOException -> 0x01e2 }
                java.nio.charset.Charset r3 = android.support.media.ExifInterface.ASCII     // Catch:{ IOException -> 0x01e2 }
                r11.<init>(r2, r3)     // Catch:{ IOException -> 0x01e2 }
                if (r1 == 0) goto L_0x01d8
                r1.close()     // Catch:{ IOException -> 0x01d0 }
                goto L_0x01d8
            L_0x01d0:
                r0 = move-exception
                java.lang.String r1 = "ExifInterface"
                java.lang.String r2 = "IOException occurred while closing InputStream"
                android.util.Log.e(r1, r2, r0)
            L_0x01d8:
                return r11
            L_0x01d9:
                r11 = move-exception
                java.lang.String r1 = "ExifInterface"
                java.lang.String r2 = "IOException occurred while closing InputStream"
                android.util.Log.e(r1, r2, r11)
            L_0x01e1:
                return r0
            L_0x01e2:
                r11 = move-exception
                goto L_0x01e9
            L_0x01e4:
                r11 = move-exception
                r1 = r0
                goto L_0x0200
            L_0x01e7:
                r11 = move-exception
                r1 = r0
            L_0x01e9:
                java.lang.String r2 = "ExifInterface"
                java.lang.String r3 = "IOException occurred during reading a value"
                android.util.Log.w(r2, r3, r11)     // Catch:{ all -> 0x01ff }
                if (r1 == 0) goto L_0x01fe
                r1.close()     // Catch:{ IOException -> 0x01f6 }
                goto L_0x01fe
            L_0x01f6:
                r11 = move-exception
                java.lang.String r1 = "ExifInterface"
                java.lang.String r2 = "IOException occurred while closing InputStream"
                android.util.Log.e(r1, r2, r11)
            L_0x01fe:
                return r0
            L_0x01ff:
                r11 = move-exception
            L_0x0200:
                if (r1 == 0) goto L_0x020e
                r1.close()     // Catch:{ IOException -> 0x0206 }
                goto L_0x020e
            L_0x0206:
                r0 = move-exception
                java.lang.String r1 = "ExifInterface"
                java.lang.String r2 = "IOException occurred while closing InputStream"
                android.util.Log.e(r1, r2, r0)
            L_0x020e:
                throw r11
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.media.ExifInterface.ExifAttribute.getValue(java.nio.ByteOrder):java.lang.Object");
        }

        public double getDoubleValue(ByteOrder byteOrder) {
            Object value = getValue(byteOrder);
            if (value == null) {
                throw new NumberFormatException("NULL can't be converted to a double value");
            } else if (value instanceof String) {
                return Double.parseDouble((String) value);
            } else {
                if (value instanceof long[]) {
                    long[] jArr = (long[]) value;
                    if (jArr.length == 1) {
                        return (double) jArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else if (value instanceof int[]) {
                    int[] iArr = (int[]) value;
                    if (iArr.length == 1) {
                        return (double) iArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else if (value instanceof double[]) {
                    double[] dArr = (double[]) value;
                    if (dArr.length == 1) {
                        return dArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else if (value instanceof Rational[]) {
                    Rational[] rationalArr = (Rational[]) value;
                    if (rationalArr.length == 1) {
                        return rationalArr[0].calculate();
                    }
                    throw new NumberFormatException("There are more than one component");
                } else {
                    throw new NumberFormatException("Couldn't find a double value");
                }
            }
        }

        public int getIntValue(ByteOrder byteOrder) {
            Object value = getValue(byteOrder);
            if (value == null) {
                throw new NumberFormatException("NULL can't be converted to a integer value");
            } else if (value instanceof String) {
                return Integer.parseInt((String) value);
            } else {
                if (value instanceof long[]) {
                    long[] jArr = (long[]) value;
                    if (jArr.length == 1) {
                        return (int) jArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else if (value instanceof int[]) {
                    int[] iArr = (int[]) value;
                    if (iArr.length == 1) {
                        return iArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else {
                    throw new NumberFormatException("Couldn't find a integer value");
                }
            }
        }

        public String getStringValue(ByteOrder byteOrder) {
            Object value = getValue(byteOrder);
            if (value == null) {
                return null;
            }
            if (value instanceof String) {
                return (String) value;
            }
            StringBuilder sb = new StringBuilder();
            int i = 0;
            if (value instanceof long[]) {
                long[] jArr = (long[]) value;
                while (i < jArr.length) {
                    sb.append(jArr[i]);
                    i++;
                    if (i != jArr.length) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            } else if (value instanceof int[]) {
                int[] iArr = (int[]) value;
                while (i < iArr.length) {
                    sb.append(iArr[i]);
                    i++;
                    if (i != iArr.length) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            } else if (value instanceof double[]) {
                double[] dArr = (double[]) value;
                while (i < dArr.length) {
                    sb.append(dArr[i]);
                    i++;
                    if (i != dArr.length) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            } else if (!(value instanceof Rational[])) {
                return null;
            } else {
                Rational[] rationalArr = (Rational[]) value;
                while (i < rationalArr.length) {
                    sb.append(rationalArr[i].numerator);
                    sb.append('/');
                    sb.append(rationalArr[i].denominator);
                    i++;
                    if (i != rationalArr.length) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            }
        }
    }

    static class ExifTag {
        public final String name;
        public final int number;
        public final int primaryFormat;
        public final int secondaryFormat;

        private ExifTag(String str, int i, int i2) {
            this.name = str;
            this.number = i;
            this.primaryFormat = i2;
            this.secondaryFormat = -1;
        }

        private ExifTag(String str, int i, int i2, int i3) {
            this.name = str;
            this.number = i;
            this.primaryFormat = i2;
            this.secondaryFormat = i3;
        }

        /* access modifiers changed from: private */
        public boolean isFormatCompatible(int i) {
            if (this.primaryFormat == 7 || i == 7 || this.primaryFormat == i || this.secondaryFormat == i) {
                return true;
            }
            if ((this.primaryFormat == 4 || this.secondaryFormat == 4) && i == 3) {
                return true;
            }
            if ((this.primaryFormat == 9 || this.secondaryFormat == 9) && i == 8) {
                return true;
            }
            if ((this.primaryFormat == 12 || this.secondaryFormat == 12) && i == 11) {
                return true;
            }
            return false;
        }
    }

    private static class Rational {
        public final long denominator;
        public final long numerator;

        private Rational(long j, long j2) {
            if (j2 == 0) {
                this.numerator = 0;
                this.denominator = 1;
                return;
            }
            this.numerator = j;
            this.denominator = j2;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.numerator);
            sb.append("/");
            sb.append(this.denominator);
            return sb.toString();
        }

        public double calculate() {
            return ((double) this.numerator) / ((double) this.denominator);
        }
    }

    static {
        ExifTag[] exifTagArr;
        ExifTag exifTag = new ExifTag("ImageWidth", 256, 3, 4);
        ExifTag exifTag2 = new ExifTag("ImageLength", 257, 3, 4);
        ExifTag exifTag3 = new ExifTag("StripOffsets", 273, 3, 4);
        ExifTag exifTag4 = new ExifTag("RowsPerStrip", 278, 3, 4);
        ExifTag exifTag5 = new ExifTag("StripByteCounts", 279, 3, 4);
        IFD_TIFF_TAGS = new ExifTag[]{new ExifTag("NewSubfileType", 254, 4), new ExifTag("SubfileType", 255, 4), exifTag, exifTag2, new ExifTag("BitsPerSample", 258, 3), new ExifTag("Compression", 259, 3), new ExifTag("PhotometricInterpretation", 262, 3), new ExifTag("ImageDescription", 270, 2), new ExifTag("Make", 271, 2), new ExifTag("Model", 272, 2), exifTag3, new ExifTag("Orientation", 274, 3), new ExifTag("SamplesPerPixel", 277, 3), exifTag4, exifTag5, new ExifTag("XResolution", 282, 5), new ExifTag("YResolution", 283, 5), new ExifTag("PlanarConfiguration", 284, 3), new ExifTag("ResolutionUnit", 296, 3), new ExifTag("TransferFunction", 301, 3), new ExifTag("Software", 305, 2), new ExifTag("DateTime", 306, 2), new ExifTag("Artist", 315, 2), new ExifTag("WhitePoint", 318, 5), new ExifTag("PrimaryChromaticities", 319, 5), new ExifTag("SubIFDPointer", 330, 4), new ExifTag("JPEGInterchangeFormat", 513, 4), new ExifTag("JPEGInterchangeFormatLength", 514, 4), new ExifTag("YCbCrCoefficients", 529, 5), new ExifTag("YCbCrSubSampling", 530, 3), new ExifTag("YCbCrPositioning", 531, 3), new ExifTag("ReferenceBlackWhite", 532, 5), new ExifTag("Copyright", 33432, 2), new ExifTag("ExifIFDPointer", 34665, 4), new ExifTag("GPSInfoIFDPointer", 34853, 4), new ExifTag("SensorTopBorder", 4, 4), new ExifTag("SensorLeftBorder", 5, 4), new ExifTag("SensorBottomBorder", 6, 4), new ExifTag("SensorRightBorder", 7, 4), new ExifTag("ISO", 23, 3), new ExifTag("JpgFromRaw", 46, 7)};
        ExifTag exifTag6 = new ExifTag("PixelXDimension", 40962, 3, 4);
        ExifTag exifTag7 = new ExifTag("PixelYDimension", 40963, 3, 4);
        ExifTag exifTag8 = new ExifTag("DefaultCropSize", 50720, 3, 4);
        IFD_EXIF_TAGS = new ExifTag[]{new ExifTag("ExposureTime", 33434, 5), new ExifTag("FNumber", 33437, 5), new ExifTag("ExposureProgram", 34850, 3), new ExifTag("SpectralSensitivity", 34852, 2), new ExifTag("ISOSpeedRatings", 34855, 3), new ExifTag("OECF", 34856, 7), new ExifTag("ExifVersion", 36864, 2), new ExifTag("DateTimeOriginal", 36867, 2), new ExifTag("DateTimeDigitized", 36868, 2), new ExifTag("ComponentsConfiguration", 37121, 7), new ExifTag("CompressedBitsPerPixel", 37122, 5), new ExifTag("ShutterSpeedValue", 37377, 10), new ExifTag("ApertureValue", 37378, 5), new ExifTag("BrightnessValue", 37379, 10), new ExifTag("ExposureBiasValue", 37380, 10), new ExifTag("MaxApertureValue", 37381, 5), new ExifTag("SubjectDistance", 37382, 5), new ExifTag("MeteringMode", 37383, 3), new ExifTag("LightSource", 37384, 3), new ExifTag("Flash", 37385, 3), new ExifTag("FocalLength", 37386, 5), new ExifTag("SubjectArea", 37396, 3), new ExifTag("MakerNote", 37500, 7), new ExifTag("UserComment", 37510, 7), new ExifTag("SubSecTime", 37520, 2), new ExifTag("SubSecTimeOriginal", 37521, 2), new ExifTag("SubSecTimeDigitized", 37522, 2), new ExifTag("FlashpixVersion", 40960, 7), new ExifTag("ColorSpace", 40961, 3), exifTag6, exifTag7, new ExifTag("RelatedSoundFile", 40964, 2), new ExifTag("InteroperabilityIFDPointer", 40965, 4), new ExifTag("FlashEnergy", 41483, 5), new ExifTag("SpatialFrequencyResponse", 41484, 7), new ExifTag("FocalPlaneXResolution", 41486, 5), new ExifTag("FocalPlaneYResolution", 41487, 5), new ExifTag("FocalPlaneResolutionUnit", 41488, 3), new ExifTag("SubjectLocation", 41492, 3), new ExifTag("ExposureIndex", 41493, 5), new ExifTag("SensingMethod", 41495, 3), new ExifTag("FileSource", 41728, 7), new ExifTag("SceneType", 41729, 7), new ExifTag("CFAPattern", 41730, 7), new ExifTag("CustomRendered", 41985, 3), new ExifTag("ExposureMode", 41986, 3), new ExifTag("WhiteBalance", 41987, 3), new ExifTag("DigitalZoomRatio", 41988, 5), new ExifTag("FocalLengthIn35mmFilm", 41989, 3), new ExifTag("SceneCaptureType", 41990, 3), new ExifTag("GainControl", 41991, 3), new ExifTag("Contrast", 41992, 3), new ExifTag("Saturation", 41993, 3), new ExifTag("Sharpness", 41994, 3), new ExifTag("DeviceSettingDescription", 41995, 7), new ExifTag("SubjectDistanceRange", 41996, 3), new ExifTag("ImageUniqueID", 42016, 2), new ExifTag("DNGVersion", 50706, 1), exifTag8};
        ExifTag exifTag9 = new ExifTag("ThumbnailImageWidth", 256, 3, 4);
        ExifTag exifTag10 = new ExifTag("ThumbnailImageLength", 257, 3, 4);
        ExifTag exifTag11 = new ExifTag("StripOffsets", 273, 3, 4);
        ExifTag exifTag12 = new ExifTag("RowsPerStrip", 278, 3, 4);
        ExifTag exifTag13 = new ExifTag("StripByteCounts", 279, 3, 4);
        ExifTag exifTag14 = new ExifTag("DefaultCropSize", 50720, 3, 4);
        IFD_THUMBNAIL_TAGS = new ExifTag[]{new ExifTag("NewSubfileType", 254, 4), new ExifTag("SubfileType", 255, 4), exifTag9, exifTag10, new ExifTag("BitsPerSample", 258, 3), new ExifTag("Compression", 259, 3), new ExifTag("PhotometricInterpretation", 262, 3), new ExifTag("ImageDescription", 270, 2), new ExifTag("Make", 271, 2), new ExifTag("Model", 272, 2), exifTag11, new ExifTag("Orientation", 274, 3), new ExifTag("SamplesPerPixel", 277, 3), exifTag12, exifTag13, new ExifTag("XResolution", 282, 5), new ExifTag("YResolution", 283, 5), new ExifTag("PlanarConfiguration", 284, 3), new ExifTag("ResolutionUnit", 296, 3), new ExifTag("TransferFunction", 301, 3), new ExifTag("Software", 305, 2), new ExifTag("DateTime", 306, 2), new ExifTag("Artist", 315, 2), new ExifTag("WhitePoint", 318, 5), new ExifTag("PrimaryChromaticities", 319, 5), new ExifTag("SubIFDPointer", 330, 4), new ExifTag("JPEGInterchangeFormat", 513, 4), new ExifTag("JPEGInterchangeFormatLength", 514, 4), new ExifTag("YCbCrCoefficients", 529, 5), new ExifTag("YCbCrSubSampling", 530, 3), new ExifTag("YCbCrPositioning", 531, 3), new ExifTag("ReferenceBlackWhite", 532, 5), new ExifTag("Copyright", 33432, 2), new ExifTag("ExifIFDPointer", 34665, 4), new ExifTag("GPSInfoIFDPointer", 34853, 4), new ExifTag("DNGVersion", 50706, 1), exifTag14};
        sFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        for (int i = 0; i < EXIF_TAGS.length; i++) {
            sExifTagMapsForReading[i] = new HashMap<>();
            sExifTagMapsForWriting[i] = new HashMap<>();
            for (ExifTag exifTag15 : EXIF_TAGS[i]) {
                sExifTagMapsForReading[i].put(Integer.valueOf(exifTag15.number), exifTag15);
                sExifTagMapsForWriting[i].put(exifTag15.name, exifTag15);
            }
        }
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[0].number), Integer.valueOf(5));
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[1].number), Integer.valueOf(1));
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[2].number), Integer.valueOf(2));
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[3].number), Integer.valueOf(3));
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[4].number), Integer.valueOf(7));
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[5].number), Integer.valueOf(8));
    }

    public ExifInterface(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("inputStream cannot be null");
        }
        this.mFilename = null;
        if (inputStream instanceof AssetInputStream) {
            this.mAssetInputStream = (AssetInputStream) inputStream;
        } else {
            this.mAssetInputStream = null;
        }
        loadAttributes(inputStream);
    }

    private ExifAttribute getExifAttribute(String str) {
        for (int i = 0; i < EXIF_TAGS.length; i++) {
            ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[i].get(str);
            if (exifAttribute != null) {
                return exifAttribute;
            }
        }
        return null;
    }

    public String getAttribute(String str) {
        ExifAttribute exifAttribute = getExifAttribute(str);
        if (exifAttribute == null) {
            return null;
        }
        if (!sTagSetForCompatibility.contains(str)) {
            return exifAttribute.getStringValue(this.mExifByteOrder);
        }
        if (!str.equals("GPSTimeStamp")) {
            try {
                return Double.toString(exifAttribute.getDoubleValue(this.mExifByteOrder));
            } catch (NumberFormatException unused) {
                return null;
            }
        } else if (exifAttribute.format == 5 || exifAttribute.format == 10) {
            Rational[] rationalArr = (Rational[]) exifAttribute.getValue(this.mExifByteOrder);
            if (rationalArr == null || rationalArr.length != 3) {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid GPS Timestamp array. array=");
                sb.append(Arrays.toString(rationalArr));
                Log.w("ExifInterface", sb.toString());
                return null;
            }
            return String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf((int) (((float) rationalArr[0].numerator) / ((float) rationalArr[0].denominator))), Integer.valueOf((int) (((float) rationalArr[1].numerator) / ((float) rationalArr[1].denominator))), Integer.valueOf((int) (((float) rationalArr[2].numerator) / ((float) rationalArr[2].denominator)))});
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("GPS Timestamp format is not rational. format=");
            sb2.append(exifAttribute.format);
            Log.w("ExifInterface", sb2.toString());
            return null;
        }
    }

    public int getAttributeInt(String str, int i) {
        ExifAttribute exifAttribute = getExifAttribute(str);
        if (exifAttribute == null) {
            return i;
        }
        try {
            return exifAttribute.getIntValue(this.mExifByteOrder);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0048, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r4.mIsSupportedFile = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0050, code lost:
        addDefaultValuesForCompatibility();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0053, code lost:
        throw r5;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x004a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void loadAttributes(java.io.InputStream r5) throws java.io.IOException {
        /*
            r4 = this;
            r0 = 0
            r1 = 0
        L_0x0002:
            android.support.media.ExifInterface$ExifTag[][] r2 = EXIF_TAGS     // Catch:{ IOException -> 0x004a }
            int r2 = r2.length     // Catch:{ IOException -> 0x004a }
            if (r1 >= r2) goto L_0x0013
            java.util.HashMap<java.lang.String, android.support.media.ExifInterface$ExifAttribute>[] r2 = r4.mAttributes     // Catch:{ IOException -> 0x004a }
            java.util.HashMap r3 = new java.util.HashMap     // Catch:{ IOException -> 0x004a }
            r3.<init>()     // Catch:{ IOException -> 0x004a }
            r2[r1] = r3     // Catch:{ IOException -> 0x004a }
            int r1 = r1 + 1
            goto L_0x0002
        L_0x0013:
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x004a }
            r2 = 5000(0x1388, float:7.006E-42)
            r1.<init>(r5, r2)     // Catch:{ IOException -> 0x004a }
            r5 = r1
            java.io.BufferedInputStream r5 = (java.io.BufferedInputStream) r5     // Catch:{ IOException -> 0x004a }
            int r5 = r4.getMimeType(r5)     // Catch:{ IOException -> 0x004a }
            r4.mMimeType = r5     // Catch:{ IOException -> 0x004a }
            android.support.media.ExifInterface$ByteOrderedDataInputStream r5 = new android.support.media.ExifInterface$ByteOrderedDataInputStream     // Catch:{ IOException -> 0x004a }
            r5.<init>(r1)     // Catch:{ IOException -> 0x004a }
            int r1 = r4.mMimeType     // Catch:{ IOException -> 0x004a }
            switch(r1) {
                case 0: goto L_0x003e;
                case 1: goto L_0x003e;
                case 2: goto L_0x003e;
                case 3: goto L_0x003e;
                case 4: goto L_0x003a;
                case 5: goto L_0x003e;
                case 6: goto L_0x003e;
                case 7: goto L_0x0036;
                case 8: goto L_0x003e;
                case 9: goto L_0x0032;
                case 10: goto L_0x002e;
                case 11: goto L_0x003e;
                default: goto L_0x002d;
            }     // Catch:{ IOException -> 0x004a }
        L_0x002d:
            goto L_0x0041
        L_0x002e:
            r4.getRw2Attributes(r5)     // Catch:{ IOException -> 0x004a }
            goto L_0x0041
        L_0x0032:
            r4.getRafAttributes(r5)     // Catch:{ IOException -> 0x004a }
            goto L_0x0041
        L_0x0036:
            r4.getOrfAttributes(r5)     // Catch:{ IOException -> 0x004a }
            goto L_0x0041
        L_0x003a:
            r4.getJpegAttributes(r5, r0, r0)     // Catch:{ IOException -> 0x004a }
            goto L_0x0041
        L_0x003e:
            r4.getRawAttributes(r5)     // Catch:{ IOException -> 0x004a }
        L_0x0041:
            r4.setThumbnailData(r5)     // Catch:{ IOException -> 0x004a }
            r5 = 1
            r4.mIsSupportedFile = r5     // Catch:{ IOException -> 0x004a }
            goto L_0x004c
        L_0x0048:
            r5 = move-exception
            goto L_0x0050
        L_0x004a:
            r4.mIsSupportedFile = r0     // Catch:{ all -> 0x0048 }
        L_0x004c:
            r4.addDefaultValuesForCompatibility()
            return
        L_0x0050:
            r4.addDefaultValuesForCompatibility()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.media.ExifInterface.loadAttributes(java.io.InputStream):void");
    }

    private int getMimeType(BufferedInputStream bufferedInputStream) throws IOException {
        bufferedInputStream.mark(5000);
        byte[] bArr = new byte[5000];
        if (bufferedInputStream.read(bArr) != 5000) {
            throw new EOFException();
        }
        bufferedInputStream.reset();
        if (isJpegFormat(bArr)) {
            return 4;
        }
        if (isRafFormat(bArr)) {
            return 9;
        }
        if (isOrfFormat(bArr)) {
            return 7;
        }
        return isRw2Format(bArr) ? 10 : 0;
    }

    private static boolean isJpegFormat(byte[] bArr) throws IOException {
        for (int i = 0; i < JPEG_SIGNATURE.length; i++) {
            if (bArr[i] != JPEG_SIGNATURE[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean isRafFormat(byte[] bArr) throws IOException {
        byte[] bytes = "FUJIFILMCCD-RAW".getBytes(Charset.defaultCharset());
        for (int i = 0; i < bytes.length; i++) {
            if (bArr[i] != bytes[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean isOrfFormat(byte[] bArr) throws IOException {
        ByteOrderedDataInputStream byteOrderedDataInputStream = new ByteOrderedDataInputStream(bArr);
        this.mExifByteOrder = readByteOrder(byteOrderedDataInputStream);
        byteOrderedDataInputStream.setByteOrder(this.mExifByteOrder);
        short readShort = byteOrderedDataInputStream.readShort();
        byteOrderedDataInputStream.close();
        return readShort == 20306 || readShort == 21330;
    }

    private boolean isRw2Format(byte[] bArr) throws IOException {
        ByteOrderedDataInputStream byteOrderedDataInputStream = new ByteOrderedDataInputStream(bArr);
        this.mExifByteOrder = readByteOrder(byteOrderedDataInputStream);
        byteOrderedDataInputStream.setByteOrder(this.mExifByteOrder);
        short readShort = byteOrderedDataInputStream.readShort();
        byteOrderedDataInputStream.close();
        return readShort == 85;
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0164  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x015c A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x00b6 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void getJpegAttributes(android.support.media.ExifInterface.ByteOrderedDataInputStream r9, int r10, int r11) throws java.io.IOException {
        /*
            r8 = this;
            java.nio.ByteOrder r0 = java.nio.ByteOrder.BIG_ENDIAN
            r9.setByteOrder(r0)
            long r0 = (long) r10
            r9.seek(r0)
            byte r0 = r9.readByte()
            r1 = -1
            if (r0 == r1) goto L_0x002d
            java.io.IOException r9 = new java.io.IOException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "Invalid marker: "
            r10.append(r11)
            r11 = r0 & 255(0xff, float:3.57E-43)
            java.lang.String r11 = java.lang.Integer.toHexString(r11)
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            throw r9
        L_0x002d:
            r2 = 1
            int r10 = r10 + r2
            byte r3 = r9.readByte()
            r4 = -40
            if (r3 == r4) goto L_0x0054
            java.io.IOException r9 = new java.io.IOException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "Invalid marker: "
            r10.append(r11)
            r11 = r0 & 255(0xff, float:3.57E-43)
            java.lang.String r11 = java.lang.Integer.toHexString(r11)
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            throw r9
        L_0x0054:
            int r10 = r10 + r2
        L_0x0055:
            byte r0 = r9.readByte()
            if (r0 == r1) goto L_0x0078
            java.io.IOException r9 = new java.io.IOException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "Invalid marker:"
            r10.append(r11)
            r11 = r0 & 255(0xff, float:3.57E-43)
            java.lang.String r11 = java.lang.Integer.toHexString(r11)
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            throw r9
        L_0x0078:
            int r10 = r10 + r2
            byte r0 = r9.readByte()
            int r10 = r10 + r2
            r3 = -39
            if (r0 == r3) goto L_0x0175
            r3 = -38
            if (r0 != r3) goto L_0x0088
            goto L_0x0175
        L_0x0088:
            int r3 = r9.readUnsignedShort()
            int r3 = r3 + -2
            int r10 = r10 + 2
            if (r3 >= 0) goto L_0x009a
            java.io.IOException r9 = new java.io.IOException
            java.lang.String r10 = "Invalid length"
            r9.<init>(r10)
            throw r9
        L_0x009a:
            r4 = -31
            r5 = 0
            if (r0 == r4) goto L_0x0118
            r4 = -2
            if (r0 == r4) goto L_0x00ea
            switch(r0) {
                case -64: goto L_0x00b0;
                case -63: goto L_0x00b0;
                case -62: goto L_0x00b0;
                case -61: goto L_0x00b0;
                default: goto L_0x00a5;
            }
        L_0x00a5:
            switch(r0) {
                case -59: goto L_0x00b0;
                case -58: goto L_0x00b0;
                case -57: goto L_0x00b0;
                default: goto L_0x00a8;
            }
        L_0x00a8:
            switch(r0) {
                case -55: goto L_0x00b0;
                case -54: goto L_0x00b0;
                case -53: goto L_0x00b0;
                default: goto L_0x00ab;
            }
        L_0x00ab:
            switch(r0) {
                case -51: goto L_0x00b0;
                case -50: goto L_0x00b0;
                case -49: goto L_0x00b0;
                default: goto L_0x00ae;
            }
        L_0x00ae:
            goto L_0x015a
        L_0x00b0:
            int r0 = r9.skipBytes(r2)
            if (r0 == r2) goto L_0x00be
            java.io.IOException r9 = new java.io.IOException
            java.lang.String r10 = "Invalid SOFx"
            r9.<init>(r10)
            throw r9
        L_0x00be:
            java.util.HashMap<java.lang.String, android.support.media.ExifInterface$ExifAttribute>[] r0 = r8.mAttributes
            r0 = r0[r11]
            java.lang.String r4 = "ImageLength"
            int r5 = r9.readUnsignedShort()
            long r5 = (long) r5
            java.nio.ByteOrder r7 = r8.mExifByteOrder
            android.support.media.ExifInterface$ExifAttribute r5 = android.support.media.ExifInterface.ExifAttribute.createULong(r5, r7)
            r0.put(r4, r5)
            java.util.HashMap<java.lang.String, android.support.media.ExifInterface$ExifAttribute>[] r0 = r8.mAttributes
            r0 = r0[r11]
            java.lang.String r4 = "ImageWidth"
            int r5 = r9.readUnsignedShort()
            long r5 = (long) r5
            java.nio.ByteOrder r7 = r8.mExifByteOrder
            android.support.media.ExifInterface$ExifAttribute r5 = android.support.media.ExifInterface.ExifAttribute.createULong(r5, r7)
            r0.put(r4, r5)
            int r3 = r3 + -5
            goto L_0x015a
        L_0x00ea:
            byte[] r0 = new byte[r3]
            int r4 = r9.read(r0)
            if (r4 == r3) goto L_0x00fa
            java.io.IOException r9 = new java.io.IOException
            java.lang.String r10 = "Invalid exif"
            r9.<init>(r10)
            throw r9
        L_0x00fa:
            java.lang.String r3 = "UserComment"
            java.lang.String r3 = r8.getAttribute(r3)
            if (r3 != 0) goto L_0x0116
            java.util.HashMap<java.lang.String, android.support.media.ExifInterface$ExifAttribute>[] r3 = r8.mAttributes
            r3 = r3[r2]
            java.lang.String r4 = "UserComment"
            java.lang.String r6 = new java.lang.String
            java.nio.charset.Charset r7 = ASCII
            r6.<init>(r0, r7)
            android.support.media.ExifInterface$ExifAttribute r0 = android.support.media.ExifInterface.ExifAttribute.createString(r6)
            r3.put(r4, r0)
        L_0x0116:
            r3 = 0
            goto L_0x015a
        L_0x0118:
            r0 = 6
            if (r3 >= r0) goto L_0x011c
            goto L_0x015a
        L_0x011c:
            byte[] r4 = new byte[r0]
            int r6 = r9.read(r4)
            if (r6 == r0) goto L_0x012c
            java.io.IOException r9 = new java.io.IOException
            java.lang.String r10 = "Invalid exif"
            r9.<init>(r10)
            throw r9
        L_0x012c:
            int r10 = r10 + 6
            int r3 = r3 + -6
            byte[] r0 = IDENTIFIER_EXIF_APP1
            boolean r0 = java.util.Arrays.equals(r4, r0)
            if (r0 != 0) goto L_0x0139
            goto L_0x015a
        L_0x0139:
            if (r3 > 0) goto L_0x0143
            java.io.IOException r9 = new java.io.IOException
            java.lang.String r10 = "Invalid exif"
            r9.<init>(r10)
            throw r9
        L_0x0143:
            r8.mExifOffset = r10
            byte[] r0 = new byte[r3]
            int r4 = r9.read(r0)
            if (r4 == r3) goto L_0x0155
            java.io.IOException r9 = new java.io.IOException
            java.lang.String r10 = "Invalid exif"
            r9.<init>(r10)
            throw r9
        L_0x0155:
            int r10 = r10 + r3
            r8.readExifSegment(r0, r11)
            goto L_0x0116
        L_0x015a:
            if (r3 >= 0) goto L_0x0164
            java.io.IOException r9 = new java.io.IOException
            java.lang.String r10 = "Invalid length"
            r9.<init>(r10)
            throw r9
        L_0x0164:
            int r0 = r9.skipBytes(r3)
            if (r0 == r3) goto L_0x0172
            java.io.IOException r9 = new java.io.IOException
            java.lang.String r10 = "Invalid JPEG segment"
            r9.<init>(r10)
            throw r9
        L_0x0172:
            int r10 = r10 + r3
            goto L_0x0055
        L_0x0175:
            java.nio.ByteOrder r10 = r8.mExifByteOrder
            r9.setByteOrder(r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.media.ExifInterface.getJpegAttributes(android.support.media.ExifInterface$ByteOrderedDataInputStream, int, int):void");
    }

    private void getRawAttributes(ByteOrderedDataInputStream byteOrderedDataInputStream) throws IOException {
        parseTiffHeaders(byteOrderedDataInputStream, byteOrderedDataInputStream.available());
        readImageFileDirectory(byteOrderedDataInputStream, 0);
        updateImageSizeValues(byteOrderedDataInputStream, 0);
        updateImageSizeValues(byteOrderedDataInputStream, 5);
        updateImageSizeValues(byteOrderedDataInputStream, 4);
        validateImages(byteOrderedDataInputStream);
        if (this.mMimeType == 8) {
            ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[1].get("MakerNote");
            if (exifAttribute != null) {
                ByteOrderedDataInputStream byteOrderedDataInputStream2 = new ByteOrderedDataInputStream(exifAttribute.bytes);
                byteOrderedDataInputStream2.setByteOrder(this.mExifByteOrder);
                byteOrderedDataInputStream2.seek(6);
                readImageFileDirectory(byteOrderedDataInputStream2, 9);
                ExifAttribute exifAttribute2 = (ExifAttribute) this.mAttributes[9].get("ColorSpace");
                if (exifAttribute2 != null) {
                    this.mAttributes[1].put("ColorSpace", exifAttribute2);
                }
            }
        }
    }

    private void getRafAttributes(ByteOrderedDataInputStream byteOrderedDataInputStream) throws IOException {
        byteOrderedDataInputStream.skipBytes(84);
        byte[] bArr = new byte[4];
        byte[] bArr2 = new byte[4];
        byteOrderedDataInputStream.read(bArr);
        byteOrderedDataInputStream.skipBytes(4);
        byteOrderedDataInputStream.read(bArr2);
        int i = ByteBuffer.wrap(bArr).getInt();
        int i2 = ByteBuffer.wrap(bArr2).getInt();
        getJpegAttributes(byteOrderedDataInputStream, i, 5);
        byteOrderedDataInputStream.seek((long) i2);
        byteOrderedDataInputStream.setByteOrder(ByteOrder.BIG_ENDIAN);
        int readInt = byteOrderedDataInputStream.readInt();
        for (int i3 = 0; i3 < readInt; i3++) {
            int readUnsignedShort = byteOrderedDataInputStream.readUnsignedShort();
            int readUnsignedShort2 = byteOrderedDataInputStream.readUnsignedShort();
            if (readUnsignedShort == TAG_RAF_IMAGE_SIZE.number) {
                short readShort = byteOrderedDataInputStream.readShort();
                short readShort2 = byteOrderedDataInputStream.readShort();
                ExifAttribute createUShort = ExifAttribute.createUShort((int) readShort, this.mExifByteOrder);
                ExifAttribute createUShort2 = ExifAttribute.createUShort((int) readShort2, this.mExifByteOrder);
                this.mAttributes[0].put("ImageLength", createUShort);
                this.mAttributes[0].put("ImageWidth", createUShort2);
                return;
            }
            byteOrderedDataInputStream.skipBytes(readUnsignedShort2);
        }
    }

    private void getOrfAttributes(ByteOrderedDataInputStream byteOrderedDataInputStream) throws IOException {
        getRawAttributes(byteOrderedDataInputStream);
        ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[1].get("MakerNote");
        if (exifAttribute != null) {
            ByteOrderedDataInputStream byteOrderedDataInputStream2 = new ByteOrderedDataInputStream(exifAttribute.bytes);
            byteOrderedDataInputStream2.setByteOrder(this.mExifByteOrder);
            byte[] bArr = new byte[ORF_MAKER_NOTE_HEADER_1.length];
            byteOrderedDataInputStream2.readFully(bArr);
            byteOrderedDataInputStream2.seek(0);
            byte[] bArr2 = new byte[ORF_MAKER_NOTE_HEADER_2.length];
            byteOrderedDataInputStream2.readFully(bArr2);
            if (Arrays.equals(bArr, ORF_MAKER_NOTE_HEADER_1)) {
                byteOrderedDataInputStream2.seek(8);
            } else if (Arrays.equals(bArr2, ORF_MAKER_NOTE_HEADER_2)) {
                byteOrderedDataInputStream2.seek(12);
            }
            readImageFileDirectory(byteOrderedDataInputStream2, 6);
            ExifAttribute exifAttribute2 = (ExifAttribute) this.mAttributes[7].get("PreviewImageStart");
            ExifAttribute exifAttribute3 = (ExifAttribute) this.mAttributes[7].get("PreviewImageLength");
            if (!(exifAttribute2 == null || exifAttribute3 == null)) {
                this.mAttributes[5].put("JPEGInterchangeFormat", exifAttribute2);
                this.mAttributes[5].put("JPEGInterchangeFormatLength", exifAttribute3);
            }
            ExifAttribute exifAttribute4 = (ExifAttribute) this.mAttributes[8].get("AspectFrame");
            if (exifAttribute4 != null) {
                int[] iArr = (int[]) exifAttribute4.getValue(this.mExifByteOrder);
                if (iArr == null || iArr.length != 4) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Invalid aspect frame values. frame=");
                    sb.append(Arrays.toString(iArr));
                    Log.w("ExifInterface", sb.toString());
                } else if (iArr[2] > iArr[0] && iArr[3] > iArr[1]) {
                    int i = (iArr[2] - iArr[0]) + 1;
                    int i2 = (iArr[3] - iArr[1]) + 1;
                    if (i < i2) {
                        int i3 = i + i2;
                        i2 = i3 - i2;
                        i = i3 - i2;
                    }
                    ExifAttribute createUShort = ExifAttribute.createUShort(i, this.mExifByteOrder);
                    ExifAttribute createUShort2 = ExifAttribute.createUShort(i2, this.mExifByteOrder);
                    this.mAttributes[0].put("ImageWidth", createUShort);
                    this.mAttributes[0].put("ImageLength", createUShort2);
                }
            }
        }
    }

    private void getRw2Attributes(ByteOrderedDataInputStream byteOrderedDataInputStream) throws IOException {
        getRawAttributes(byteOrderedDataInputStream);
        if (((ExifAttribute) this.mAttributes[0].get("JpgFromRaw")) != null) {
            getJpegAttributes(byteOrderedDataInputStream, this.mRw2JpgFromRawOffset, 5);
        }
        ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[0].get("ISO");
        ExifAttribute exifAttribute2 = (ExifAttribute) this.mAttributes[1].get("ISOSpeedRatings");
        if (exifAttribute != null && exifAttribute2 == null) {
            this.mAttributes[1].put("ISOSpeedRatings", exifAttribute);
        }
    }

    private void readExifSegment(byte[] bArr, int i) throws IOException {
        ByteOrderedDataInputStream byteOrderedDataInputStream = new ByteOrderedDataInputStream(bArr);
        parseTiffHeaders(byteOrderedDataInputStream, bArr.length);
        readImageFileDirectory(byteOrderedDataInputStream, i);
    }

    private void addDefaultValuesForCompatibility() {
        String attribute = getAttribute("DateTimeOriginal");
        if (attribute != null && getAttribute("DateTime") == null) {
            this.mAttributes[0].put("DateTime", ExifAttribute.createString(attribute));
        }
        if (getAttribute("ImageWidth") == null) {
            this.mAttributes[0].put("ImageWidth", ExifAttribute.createULong(0, this.mExifByteOrder));
        }
        if (getAttribute("ImageLength") == null) {
            this.mAttributes[0].put("ImageLength", ExifAttribute.createULong(0, this.mExifByteOrder));
        }
        if (getAttribute("Orientation") == null) {
            this.mAttributes[0].put("Orientation", ExifAttribute.createULong(0, this.mExifByteOrder));
        }
        if (getAttribute("LightSource") == null) {
            this.mAttributes[1].put("LightSource", ExifAttribute.createULong(0, this.mExifByteOrder));
        }
    }

    private ByteOrder readByteOrder(ByteOrderedDataInputStream byteOrderedDataInputStream) throws IOException {
        short readShort = byteOrderedDataInputStream.readShort();
        if (readShort == 18761) {
            return ByteOrder.LITTLE_ENDIAN;
        }
        if (readShort == 19789) {
            return ByteOrder.BIG_ENDIAN;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid byte order: ");
        sb.append(Integer.toHexString(readShort));
        throw new IOException(sb.toString());
    }

    private void parseTiffHeaders(ByteOrderedDataInputStream byteOrderedDataInputStream, int i) throws IOException {
        this.mExifByteOrder = readByteOrder(byteOrderedDataInputStream);
        byteOrderedDataInputStream.setByteOrder(this.mExifByteOrder);
        int readUnsignedShort = byteOrderedDataInputStream.readUnsignedShort();
        if (this.mMimeType == 7 || this.mMimeType == 10 || readUnsignedShort == 42) {
            int readInt = byteOrderedDataInputStream.readInt();
            if (readInt < 8 || readInt >= i) {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid first Ifd offset: ");
                sb.append(readInt);
                throw new IOException(sb.toString());
            }
            int i2 = readInt - 8;
            if (i2 > 0 && byteOrderedDataInputStream.skipBytes(i2) != i2) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Couldn't jump to first Ifd: ");
                sb2.append(i2);
                throw new IOException(sb2.toString());
            }
            return;
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Invalid start code: ");
        sb3.append(Integer.toHexString(readUnsignedShort));
        throw new IOException(sb3.toString());
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x01ae  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01ff  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void readImageFileDirectory(android.support.media.ExifInterface.ByteOrderedDataInputStream r25, int r26) throws java.io.IOException {
        /*
            r24 = this;
            r0 = r24
            r1 = r25
            r2 = r26
            int r3 = r25.mPosition
            int r3 = r3 + 2
            int r4 = r25.mLength
            if (r3 <= r4) goto L_0x0013
            return
        L_0x0013:
            short r3 = r25.readShort()
            int r4 = r25.mPosition
            int r5 = r3 * 12
            int r4 = r4 + r5
            int r5 = r25.mLength
            if (r4 <= r5) goto L_0x0025
            return
        L_0x0025:
            r5 = 0
        L_0x0026:
            r7 = 4
            if (r5 >= r3) goto L_0x0274
            int r8 = r25.readUnsignedShort()
            int r9 = r25.readUnsignedShort()
            int r10 = r25.readInt()
            int r11 = r25.peek()
            int r11 = r11 + r7
            long r11 = (long) r11
            java.util.HashMap<java.lang.Integer, android.support.media.ExifInterface$ExifTag>[] r13 = sExifTagMapsForReading
            r13 = r13[r2]
            java.lang.Integer r14 = java.lang.Integer.valueOf(r8)
            java.lang.Object r13 = r13.get(r14)
            android.support.media.ExifInterface$ExifTag r13 = (android.support.media.ExifInterface.ExifTag) r13
            r14 = 7
            r15 = 0
            if (r13 != 0) goto L_0x0068
            java.lang.String r4 = "ExifInterface"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Skip the tag entry since tag number is not defined: "
            r6.append(r7)
            r6.append(r8)
            java.lang.String r6 = r6.toString()
            android.util.Log.w(r4, r6)
        L_0x0064:
            r17 = r3
            goto L_0x00e6
        L_0x0068:
            if (r9 <= 0) goto L_0x00ce
            int[] r4 = IFD_FORMAT_BYTES_PER_FORMAT
            int r4 = r4.length
            if (r9 < r4) goto L_0x0070
            goto L_0x00ce
        L_0x0070:
            boolean r4 = r13.isFormatCompatible(r9)
            if (r4 != 0) goto L_0x009b
            java.lang.String r4 = "ExifInterface"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Skip the tag entry since data format ("
            r6.append(r7)
            java.lang.String[] r7 = IFD_FORMAT_NAMES
            r7 = r7[r9]
            r6.append(r7)
            java.lang.String r7 = ") is unexpected for tag: "
            r6.append(r7)
            java.lang.String r7 = r13.name
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            android.util.Log.w(r4, r6)
            goto L_0x0064
        L_0x009b:
            if (r9 != r14) goto L_0x009f
            int r9 = r13.primaryFormat
        L_0x009f:
            long r6 = (long) r10
            int[] r4 = IFD_FORMAT_BYTES_PER_FORMAT
            r4 = r4[r9]
            r17 = r3
            long r3 = (long) r4
            long r3 = r3 * r6
            int r6 = (r3 > r15 ? 1 : (r3 == r15 ? 0 : -1))
            if (r6 < 0) goto L_0x00b7
            r6 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r18 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r18 <= 0) goto L_0x00b5
            goto L_0x00b7
        L_0x00b5:
            r6 = 1
            goto L_0x00e9
        L_0x00b7:
            java.lang.String r6 = "ExifInterface"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r15 = "Skip the tag entry since the number of components is invalid: "
            r7.append(r15)
            r7.append(r10)
            java.lang.String r7 = r7.toString()
            android.util.Log.w(r6, r7)
            goto L_0x00e8
        L_0x00ce:
            r17 = r3
            java.lang.String r3 = "ExifInterface"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "Skip the tag entry since data format is invalid: "
            r4.append(r6)
            r4.append(r9)
            java.lang.String r4 = r4.toString()
            android.util.Log.w(r3, r4)
        L_0x00e6:
            r3 = 0
        L_0x00e8:
            r6 = 0
        L_0x00e9:
            if (r6 != 0) goto L_0x00f3
            r1.seek(r11)
            r4 = r2
            r19 = r5
            goto L_0x026c
        L_0x00f3:
            r6 = 4
            int r15 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r15 <= 0) goto L_0x019c
            int r6 = r25.readInt()
            int r7 = r0.mMimeType
            if (r7 != r14) goto L_0x0159
            java.lang.String r7 = "MakerNote"
            java.lang.String r14 = r13.name
            boolean r7 = r7.equals(r14)
            if (r7 == 0) goto L_0x0110
            r0.mOrfMakerNoteOffset = r6
        L_0x010d:
            r19 = r5
            goto L_0x016d
        L_0x0110:
            r7 = 6
            if (r2 != r7) goto L_0x010d
            java.lang.String r14 = "ThumbnailImage"
            java.lang.String r15 = r13.name
            boolean r14 = r14.equals(r15)
            if (r14 == 0) goto L_0x010d
            r0.mOrfThumbnailOffset = r6
            r0.mOrfThumbnailLength = r10
            java.nio.ByteOrder r14 = r0.mExifByteOrder
            android.support.media.ExifInterface$ExifAttribute r7 = android.support.media.ExifInterface.ExifAttribute.createUShort(r7, r14)
            int r14 = r0.mOrfThumbnailOffset
            long r14 = (long) r14
            r19 = r5
            java.nio.ByteOrder r5 = r0.mExifByteOrder
            android.support.media.ExifInterface$ExifAttribute r5 = android.support.media.ExifInterface.ExifAttribute.createULong(r14, r5)
            int r14 = r0.mOrfThumbnailLength
            long r14 = (long) r14
            java.nio.ByteOrder r2 = r0.mExifByteOrder
            android.support.media.ExifInterface$ExifAttribute r2 = android.support.media.ExifInterface.ExifAttribute.createULong(r14, r2)
            java.util.HashMap<java.lang.String, android.support.media.ExifInterface$ExifAttribute>[] r14 = r0.mAttributes
            r15 = 4
            r14 = r14[r15]
            java.lang.String r15 = "Compression"
            r14.put(r15, r7)
            java.util.HashMap<java.lang.String, android.support.media.ExifInterface$ExifAttribute>[] r7 = r0.mAttributes
            r14 = 4
            r7 = r7[r14]
            java.lang.String r15 = "JPEGInterchangeFormat"
            r7.put(r15, r5)
            java.util.HashMap<java.lang.String, android.support.media.ExifInterface$ExifAttribute>[] r5 = r0.mAttributes
            r5 = r5[r14]
            java.lang.String r7 = "JPEGInterchangeFormatLength"
            r5.put(r7, r2)
            goto L_0x016d
        L_0x0159:
            r19 = r5
            int r2 = r0.mMimeType
            r5 = 10
            if (r2 != r5) goto L_0x016d
            java.lang.String r2 = "JpgFromRaw"
            java.lang.String r5 = r13.name
            boolean r2 = r2.equals(r5)
            if (r2 == 0) goto L_0x016d
            r0.mRw2JpgFromRawOffset = r6
        L_0x016d:
            long r14 = (long) r6
            long r20 = r14 + r3
            int r2 = r25.mLength
            r22 = r3
            long r2 = (long) r2
            int r4 = (r20 > r2 ? 1 : (r20 == r2 ? 0 : -1))
            if (r4 > 0) goto L_0x017f
            r1.seek(r14)
            goto L_0x01a0
        L_0x017f:
            java.lang.String r2 = "ExifInterface"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Skip the tag entry since data offset is invalid: "
            r3.append(r4)
            r3.append(r6)
            java.lang.String r3 = r3.toString()
            android.util.Log.w(r2, r3)
            r1.seek(r11)
        L_0x0198:
            r4 = r26
            goto L_0x026c
        L_0x019c:
            r22 = r3
            r19 = r5
        L_0x01a0:
            java.util.HashMap<java.lang.Integer, java.lang.Integer> r2 = sExifPointerTagMap
            java.lang.Integer r3 = java.lang.Integer.valueOf(r8)
            java.lang.Object r2 = r2.get(r3)
            java.lang.Integer r2 = (java.lang.Integer) r2
            if (r2 == 0) goto L_0x01ff
            r3 = -1
            switch(r9) {
                case 3: goto L_0x01c7;
                case 4: goto L_0x01c2;
                case 8: goto L_0x01bc;
                case 9: goto L_0x01b6;
                case 13: goto L_0x01b6;
                default: goto L_0x01b3;
            }
        L_0x01b3:
            r5 = 0
            goto L_0x01cd
        L_0x01b6:
            int r3 = r25.readInt()
            long r3 = (long) r3
            goto L_0x01b3
        L_0x01bc:
            short r3 = r25.readShort()
            long r3 = (long) r3
            goto L_0x01b3
        L_0x01c2:
            long r3 = r25.readUnsignedInt()
            goto L_0x01b3
        L_0x01c7:
            int r3 = r25.readUnsignedShort()
            long r3 = (long) r3
            goto L_0x01b3
        L_0x01cd:
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x01e5
            int r5 = r25.mLength
            long r5 = (long) r5
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 >= 0) goto L_0x01e5
            r1.seek(r3)
            int r2 = r2.intValue()
            r0.readImageFileDirectory(r1, r2)
            goto L_0x01fb
        L_0x01e5:
            java.lang.String r2 = "ExifInterface"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Skip jump into the IFD since its offset is invalid: "
            r5.append(r6)
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            android.util.Log.w(r2, r3)
        L_0x01fb:
            r1.seek(r11)
            goto L_0x0198
        L_0x01ff:
            r3 = r22
            int r2 = (int) r3
            byte[] r2 = new byte[r2]
            r1.readFully(r2)
            android.support.media.ExifInterface$ExifAttribute r3 = new android.support.media.ExifInterface$ExifAttribute
            r4 = 0
            r3.<init>(r9, r10, r2)
            java.util.HashMap<java.lang.String, android.support.media.ExifInterface$ExifAttribute>[] r2 = r0.mAttributes
            r4 = r26
            r2 = r2[r4]
            java.lang.String r5 = r13.name
            r2.put(r5, r3)
            java.lang.String r2 = "DNGVersion"
            java.lang.String r5 = r13.name
            boolean r2 = r2.equals(r5)
            if (r2 == 0) goto L_0x0225
            r2 = 3
            r0.mMimeType = r2
        L_0x0225:
            java.lang.String r2 = "Make"
            java.lang.String r5 = r13.name
            boolean r2 = r2.equals(r5)
            if (r2 != 0) goto L_0x0239
            java.lang.String r2 = "Model"
            java.lang.String r5 = r13.name
            boolean r2 = r2.equals(r5)
            if (r2 == 0) goto L_0x0247
        L_0x0239:
            java.nio.ByteOrder r2 = r0.mExifByteOrder
            java.lang.String r2 = r3.getStringValue(r2)
            java.lang.String r5 = "PENTAX"
            boolean r2 = r2.contains(r5)
            if (r2 != 0) goto L_0x025c
        L_0x0247:
            java.lang.String r2 = "Compression"
            java.lang.String r5 = r13.name
            boolean r2 = r2.equals(r5)
            if (r2 == 0) goto L_0x0260
            java.nio.ByteOrder r2 = r0.mExifByteOrder
            int r2 = r3.getIntValue(r2)
            r3 = 65535(0xffff, float:9.1834E-41)
            if (r2 != r3) goto L_0x0260
        L_0x025c:
            r2 = 8
            r0.mMimeType = r2
        L_0x0260:
            int r2 = r25.peek()
            long r2 = (long) r2
            int r5 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r5 == 0) goto L_0x026c
            r1.seek(r11)
        L_0x026c:
            int r5 = r19 + 1
            short r5 = (short) r5
            r2 = r4
            r3 = r17
            goto L_0x0026
        L_0x0274:
            int r2 = r25.peek()
            r3 = 4
            int r2 = r2 + r3
            int r4 = r25.mLength
            if (r2 > r4) goto L_0x02ae
            int r2 = r25.readInt()
            r4 = 8
            if (r2 <= r4) goto L_0x02ae
            int r4 = r25.mLength
            if (r2 >= r4) goto L_0x02ae
            long r4 = (long) r2
            r1.seek(r4)
            java.util.HashMap<java.lang.String, android.support.media.ExifInterface$ExifAttribute>[] r2 = r0.mAttributes
            r2 = r2[r3]
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x02a0
            r0.readImageFileDirectory(r1, r3)
            goto L_0x02ae
        L_0x02a0:
            java.util.HashMap<java.lang.String, android.support.media.ExifInterface$ExifAttribute>[] r2 = r0.mAttributes
            r3 = 5
            r2 = r2[r3]
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x02ae
            r0.readImageFileDirectory(r1, r3)
        L_0x02ae:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.media.ExifInterface.readImageFileDirectory(android.support.media.ExifInterface$ByteOrderedDataInputStream, int):void");
    }

    private void retrieveJpegImageSize(ByteOrderedDataInputStream byteOrderedDataInputStream, int i) throws IOException {
        ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[i].get("ImageWidth");
        if (((ExifAttribute) this.mAttributes[i].get("ImageLength")) == null || exifAttribute == null) {
            ExifAttribute exifAttribute2 = (ExifAttribute) this.mAttributes[i].get("JPEGInterchangeFormat");
            if (exifAttribute2 != null) {
                getJpegAttributes(byteOrderedDataInputStream, exifAttribute2.getIntValue(this.mExifByteOrder), i);
            }
        }
    }

    private void setThumbnailData(ByteOrderedDataInputStream byteOrderedDataInputStream) throws IOException {
        HashMap<String, ExifAttribute> hashMap = this.mAttributes[4];
        ExifAttribute exifAttribute = (ExifAttribute) hashMap.get("Compression");
        if (exifAttribute != null) {
            this.mThumbnailCompression = exifAttribute.getIntValue(this.mExifByteOrder);
            int i = this.mThumbnailCompression;
            if (i != 1) {
                switch (i) {
                    case 6:
                        handleThumbnailFromJfif(byteOrderedDataInputStream, hashMap);
                        return;
                    case 7:
                        break;
                    default:
                        return;
                }
            }
            if (isSupportedDataType(hashMap)) {
                handleThumbnailFromStrips(byteOrderedDataInputStream, hashMap);
                return;
            }
            return;
        }
        this.mThumbnailCompression = 6;
        handleThumbnailFromJfif(byteOrderedDataInputStream, hashMap);
    }

    private void handleThumbnailFromJfif(ByteOrderedDataInputStream byteOrderedDataInputStream, HashMap hashMap) throws IOException {
        ExifAttribute exifAttribute = (ExifAttribute) hashMap.get("JPEGInterchangeFormat");
        ExifAttribute exifAttribute2 = (ExifAttribute) hashMap.get("JPEGInterchangeFormatLength");
        if (exifAttribute != null && exifAttribute2 != null) {
            int intValue = exifAttribute.getIntValue(this.mExifByteOrder);
            int min = Math.min(exifAttribute2.getIntValue(this.mExifByteOrder), byteOrderedDataInputStream.available() - intValue);
            if (this.mMimeType == 4 || this.mMimeType == 9 || this.mMimeType == 10) {
                intValue += this.mExifOffset;
            } else if (this.mMimeType == 7) {
                intValue += this.mOrfMakerNoteOffset;
            }
            if (intValue > 0 && min > 0) {
                this.mHasThumbnail = true;
                this.mThumbnailOffset = intValue;
                this.mThumbnailLength = min;
                if (this.mFilename == null && this.mAssetInputStream == null) {
                    byte[] bArr = new byte[min];
                    byteOrderedDataInputStream.seek((long) intValue);
                    byteOrderedDataInputStream.readFully(bArr);
                    this.mThumbnailBytes = bArr;
                }
            }
        }
    }

    private void handleThumbnailFromStrips(ByteOrderedDataInputStream byteOrderedDataInputStream, HashMap hashMap) throws IOException {
        ExifAttribute exifAttribute = (ExifAttribute) hashMap.get("StripOffsets");
        ExifAttribute exifAttribute2 = (ExifAttribute) hashMap.get("StripByteCounts");
        if (!(exifAttribute == null || exifAttribute2 == null)) {
            long[] convertToLongArray = convertToLongArray(exifAttribute.getValue(this.mExifByteOrder));
            long[] convertToLongArray2 = convertToLongArray(exifAttribute2.getValue(this.mExifByteOrder));
            if (convertToLongArray == null) {
                Log.w("ExifInterface", "stripOffsets should not be null.");
            } else if (convertToLongArray2 == null) {
                Log.w("ExifInterface", "stripByteCounts should not be null.");
            } else {
                long j = 0;
                int i = 0;
                while (i < convertToLongArray2.length) {
                    i++;
                    j += convertToLongArray2[i];
                }
                byte[] bArr = new byte[((int) j)];
                int i2 = 0;
                int i3 = 0;
                for (int i4 = 0; i4 < convertToLongArray.length; i4++) {
                    int i5 = (int) convertToLongArray2[i4];
                    int i6 = ((int) convertToLongArray[i4]) - i2;
                    if (i6 < 0) {
                        Log.d("ExifInterface", "Invalid strip offset value");
                    }
                    byteOrderedDataInputStream.seek((long) i6);
                    int i7 = i2 + i6;
                    byte[] bArr2 = new byte[i5];
                    byteOrderedDataInputStream.read(bArr2);
                    i2 = i7 + i5;
                    System.arraycopy(bArr2, 0, bArr, i3, bArr2.length);
                    i3 += bArr2.length;
                }
                this.mHasThumbnail = true;
                this.mThumbnailBytes = bArr;
                this.mThumbnailLength = bArr.length;
            }
        }
    }

    private boolean isSupportedDataType(HashMap hashMap) throws IOException {
        ExifAttribute exifAttribute = (ExifAttribute) hashMap.get("BitsPerSample");
        if (exifAttribute != null) {
            int[] iArr = (int[]) exifAttribute.getValue(this.mExifByteOrder);
            if (Arrays.equals(BITS_PER_SAMPLE_RGB, iArr)) {
                return true;
            }
            if (this.mMimeType == 3) {
                ExifAttribute exifAttribute2 = (ExifAttribute) hashMap.get("PhotometricInterpretation");
                if (exifAttribute2 != null) {
                    int intValue = exifAttribute2.getIntValue(this.mExifByteOrder);
                    if ((intValue == 1 && Arrays.equals(iArr, BITS_PER_SAMPLE_GREYSCALE_2)) || (intValue == 6 && Arrays.equals(iArr, BITS_PER_SAMPLE_RGB))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isThumbnail(HashMap hashMap) throws IOException {
        ExifAttribute exifAttribute = (ExifAttribute) hashMap.get("ImageLength");
        ExifAttribute exifAttribute2 = (ExifAttribute) hashMap.get("ImageWidth");
        if (!(exifAttribute == null || exifAttribute2 == null)) {
            int intValue = exifAttribute.getIntValue(this.mExifByteOrder);
            int intValue2 = exifAttribute2.getIntValue(this.mExifByteOrder);
            if (intValue <= 512 && intValue2 <= 512) {
                return true;
            }
        }
        return false;
    }

    private void validateImages(InputStream inputStream) throws IOException {
        swapBasedOnImageSize(0, 5);
        swapBasedOnImageSize(0, 4);
        swapBasedOnImageSize(5, 4);
        ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[1].get("PixelXDimension");
        ExifAttribute exifAttribute2 = (ExifAttribute) this.mAttributes[1].get("PixelYDimension");
        if (!(exifAttribute == null || exifAttribute2 == null)) {
            this.mAttributes[0].put("ImageWidth", exifAttribute);
            this.mAttributes[0].put("ImageLength", exifAttribute2);
        }
        if (this.mAttributes[4].isEmpty() && isThumbnail(this.mAttributes[5])) {
            this.mAttributes[4] = this.mAttributes[5];
            this.mAttributes[5] = new HashMap<>();
        }
        if (!isThumbnail(this.mAttributes[4])) {
            Log.d("ExifInterface", "No image meets the size requirements of a thumbnail image.");
        }
    }

    private void updateImageSizeValues(ByteOrderedDataInputStream byteOrderedDataInputStream, int i) throws IOException {
        ExifAttribute exifAttribute;
        ExifAttribute exifAttribute2;
        ExifAttribute exifAttribute3 = (ExifAttribute) this.mAttributes[i].get("DefaultCropSize");
        ExifAttribute exifAttribute4 = (ExifAttribute) this.mAttributes[i].get("SensorTopBorder");
        ExifAttribute exifAttribute5 = (ExifAttribute) this.mAttributes[i].get("SensorLeftBorder");
        ExifAttribute exifAttribute6 = (ExifAttribute) this.mAttributes[i].get("SensorBottomBorder");
        ExifAttribute exifAttribute7 = (ExifAttribute) this.mAttributes[i].get("SensorRightBorder");
        if (exifAttribute3 != null) {
            if (exifAttribute3.format == 5) {
                Rational[] rationalArr = (Rational[]) exifAttribute3.getValue(this.mExifByteOrder);
                if (rationalArr == null || rationalArr.length != 2) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Invalid crop size values. cropSize=");
                    sb.append(Arrays.toString(rationalArr));
                    Log.w("ExifInterface", sb.toString());
                    return;
                }
                exifAttribute2 = ExifAttribute.createURational(rationalArr[0], this.mExifByteOrder);
                exifAttribute = ExifAttribute.createURational(rationalArr[1], this.mExifByteOrder);
            } else {
                int[] iArr = (int[]) exifAttribute3.getValue(this.mExifByteOrder);
                if (iArr == null || iArr.length != 2) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Invalid crop size values. cropSize=");
                    sb2.append(Arrays.toString(iArr));
                    Log.w("ExifInterface", sb2.toString());
                    return;
                }
                exifAttribute2 = ExifAttribute.createUShort(iArr[0], this.mExifByteOrder);
                exifAttribute = ExifAttribute.createUShort(iArr[1], this.mExifByteOrder);
            }
            this.mAttributes[i].put("ImageWidth", exifAttribute2);
            this.mAttributes[i].put("ImageLength", exifAttribute);
        } else if (exifAttribute4 == null || exifAttribute5 == null || exifAttribute6 == null || exifAttribute7 == null) {
            retrieveJpegImageSize(byteOrderedDataInputStream, i);
        } else {
            int intValue = exifAttribute4.getIntValue(this.mExifByteOrder);
            int intValue2 = exifAttribute6.getIntValue(this.mExifByteOrder);
            int intValue3 = exifAttribute7.getIntValue(this.mExifByteOrder);
            int intValue4 = exifAttribute5.getIntValue(this.mExifByteOrder);
            if (intValue2 > intValue && intValue3 > intValue4) {
                int i2 = intValue3 - intValue4;
                ExifAttribute createUShort = ExifAttribute.createUShort(intValue2 - intValue, this.mExifByteOrder);
                ExifAttribute createUShort2 = ExifAttribute.createUShort(i2, this.mExifByteOrder);
                this.mAttributes[i].put("ImageLength", createUShort);
                this.mAttributes[i].put("ImageWidth", createUShort2);
            }
        }
    }

    private void swapBasedOnImageSize(int i, int i2) throws IOException {
        if (!this.mAttributes[i].isEmpty() && !this.mAttributes[i2].isEmpty()) {
            ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[i].get("ImageLength");
            ExifAttribute exifAttribute2 = (ExifAttribute) this.mAttributes[i].get("ImageWidth");
            ExifAttribute exifAttribute3 = (ExifAttribute) this.mAttributes[i2].get("ImageLength");
            ExifAttribute exifAttribute4 = (ExifAttribute) this.mAttributes[i2].get("ImageWidth");
            if (!(exifAttribute == null || exifAttribute2 == null || exifAttribute3 == null || exifAttribute4 == null)) {
                int intValue = exifAttribute.getIntValue(this.mExifByteOrder);
                int intValue2 = exifAttribute2.getIntValue(this.mExifByteOrder);
                int intValue3 = exifAttribute3.getIntValue(this.mExifByteOrder);
                int intValue4 = exifAttribute4.getIntValue(this.mExifByteOrder);
                if (intValue < intValue3 && intValue2 < intValue4) {
                    HashMap<String, ExifAttribute> hashMap = this.mAttributes[i];
                    this.mAttributes[i] = this.mAttributes[i2];
                    this.mAttributes[i2] = hashMap;
                }
            }
        }
    }

    private static long[] convertToLongArray(Object obj) {
        if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            long[] jArr = new long[iArr.length];
            for (int i = 0; i < iArr.length; i++) {
                jArr[i] = (long) iArr[i];
            }
            return jArr;
        } else if (obj instanceof long[]) {
            return (long[]) obj;
        } else {
            return null;
        }
    }
}
