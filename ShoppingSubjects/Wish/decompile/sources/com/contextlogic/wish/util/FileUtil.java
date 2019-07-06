package com.contextlogic.wish.util;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Audio;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video;
import android.webkit.MimeTypeMap;
import com.contextlogic.wish.application.WishApplication;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {
    public static byte[] getBytes(InputStream inputStream, byte[] bArr, long j) throws IOException {
        if (j == 0) {
            j = 32768;
        }
        ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer((int) j);
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayBuffer.append(bArr, 0, read);
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused) {
                    }
                }
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused2) {
            }
        }
        if (byteArrayBuffer.capacity() == byteArrayBuffer.length()) {
            return byteArrayBuffer.buffer();
        }
        return byteArrayBuffer.toByteArray();
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x003d A[SYNTHETIC, Splitter:B:25:0x003d] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0042 A[SYNTHETIC, Splitter:B:29:0x0042] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x004a A[SYNTHETIC, Splitter:B:37:0x004a] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x004f A[SYNTHETIC, Splitter:B:41:0x004f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String readFile(java.io.File r5) {
        /*
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]
            r1 = 0
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ Throwable -> 0x0046, all -> 0x0038 }
            r2.<init>()     // Catch:{ Throwable -> 0x0046, all -> 0x0038 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0036, all -> 0x0033 }
            r3.<init>(r5)     // Catch:{ Throwable -> 0x0036, all -> 0x0033 }
        L_0x000f:
            int r5 = r3.read(r0)     // Catch:{ Throwable -> 0x0048, all -> 0x0031 }
            r4 = -1
            if (r5 == r4) goto L_0x001b
            r4 = 0
            r2.write(r0, r4, r5)     // Catch:{ Throwable -> 0x0048, all -> 0x0031 }
            goto L_0x000f
        L_0x001b:
            java.lang.String r5 = new java.lang.String     // Catch:{ Throwable -> 0x0048, all -> 0x0031 }
            byte[] r0 = r2.toByteArray()     // Catch:{ Throwable -> 0x0048, all -> 0x0031 }
            java.lang.String r4 = "UTF-8"
            r5.<init>(r0, r4)     // Catch:{ Throwable -> 0x0048, all -> 0x0031 }
            if (r2 == 0) goto L_0x002b
            r2.close()     // Catch:{ IOException -> 0x002b }
        L_0x002b:
            if (r3 == 0) goto L_0x0053
            r3.close()     // Catch:{ IOException -> 0x0053 }
            goto L_0x0053
        L_0x0031:
            r5 = move-exception
            goto L_0x003b
        L_0x0033:
            r5 = move-exception
            r3 = r1
            goto L_0x003b
        L_0x0036:
            r3 = r1
            goto L_0x0048
        L_0x0038:
            r5 = move-exception
            r2 = r1
            r3 = r2
        L_0x003b:
            if (r2 == 0) goto L_0x0040
            r2.close()     // Catch:{ IOException -> 0x0040 }
        L_0x0040:
            if (r3 == 0) goto L_0x0045
            r3.close()     // Catch:{ IOException -> 0x0045 }
        L_0x0045:
            throw r5
        L_0x0046:
            r2 = r1
            r3 = r2
        L_0x0048:
            if (r2 == 0) goto L_0x004d
            r2.close()     // Catch:{ IOException -> 0x004d }
        L_0x004d:
            if (r3 == 0) goto L_0x0052
            r3.close()     // Catch:{ IOException -> 0x0052 }
        L_0x0052:
            r5 = r1
        L_0x0053:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.util.FileUtil.readFile(java.io.File):java.lang.String");
    }

    @SuppressLint({"NewApi"})
    public static String getPath(Uri uri) {
        WishApplication instance = WishApplication.getInstance();
        Uri uri2 = null;
        if (!(VERSION.SDK_INT >= 19) || !DocumentsContract.isDocumentUri(instance, uri)) {
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                if (isGooglePhotosUri(uri)) {
                    return uri.getLastPathSegment();
                }
                if (!isNewGooglePhotosUri(uri) && isWishContentUri(uri)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(WishApplication.getInstance().getFilesDir().getAbsolutePath());
                    sb.append(uri.getPath());
                    return sb.toString();
                }
                String dataColumn = getDataColumn(instance, uri, null, null);
                if (dataColumn == null) {
                    dataColumn = uri.toString();
                }
                return dataColumn;
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        } else if (isExternalStorageDocument(uri)) {
            String[] split = DocumentsContract.getDocumentId(uri).split(":");
            if ("primary".equalsIgnoreCase(split[0])) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(Environment.getExternalStorageDirectory());
                sb2.append("/");
                sb2.append(split[1]);
                return sb2.toString();
            }
        } else if (isDownloadsDocument(uri)) {
            return getDataColumn(instance, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
        } else if (isMediaDocument(uri)) {
            String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
            String str = split2[0];
            if ("image".equals(str)) {
                uri2 = Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(str)) {
                uri2 = Video.Media.EXTERNAL_CONTENT_URI;
            } else if ("audio".equals(str)) {
                uri2 = Audio.Media.EXTERNAL_CONTENT_URI;
            }
            return getDataColumn(instance, uri2, "_id=?", new String[]{split2[1]});
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0037  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getDataColumn(android.content.Context r8, android.net.Uri r9, java.lang.String r10, java.lang.String[] r11) {
        /*
            java.lang.String r0 = "_data"
            r1 = 1
            java.lang.String[] r4 = new java.lang.String[r1]
            r1 = 0
            r4[r1] = r0
            r1 = 0
            android.content.ContentResolver r2 = r8.getContentResolver()     // Catch:{ all -> 0x0033 }
            r7 = 0
            r3 = r9
            r5 = r10
            r6 = r11
            android.database.Cursor r8 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0033 }
            if (r8 == 0) goto L_0x002d
            boolean r9 = r8.moveToFirst()     // Catch:{ all -> 0x002b }
            if (r9 == 0) goto L_0x002d
            int r9 = r8.getColumnIndexOrThrow(r0)     // Catch:{ all -> 0x002b }
            java.lang.String r9 = r8.getString(r9)     // Catch:{ all -> 0x002b }
            if (r8 == 0) goto L_0x002a
            r8.close()
        L_0x002a:
            return r9
        L_0x002b:
            r9 = move-exception
            goto L_0x0035
        L_0x002d:
            if (r8 == 0) goto L_0x0032
            r8.close()
        L_0x0032:
            return r1
        L_0x0033:
            r9 = move-exception
            r8 = r1
        L_0x0035:
            if (r8 == 0) goto L_0x003a
            r8.close()
        L_0x003a:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.util.FileUtil.getDataColumn(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    private static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    private static boolean isWishContentUri(Uri uri) {
        return "com.contextlogic.wish.fileprovider".equals(uri.getAuthority());
    }

    public static boolean isNewGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.contentprovider".equals(uri.getAuthority());
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:2|3|8) */
    /* JADX WARNING: Code restructure failed: missing block: B:3:?, code lost:
        r1 = new java.lang.StringBuilder();
        r1.append(android.os.Environment.getExternalStorageDirectory());
        r1.append("/Documents");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:?, code lost:
        return android.os.Environment.getExternalStorageDirectory();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        return new java.io.File(r1.toString());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0007 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.io.File getExternalDocumentsDirectory() {
        /*
            java.lang.String r0 = android.os.Environment.DIRECTORY_DOCUMENTS     // Catch:{ Throwable -> 0x0007 }
            java.io.File r0 = android.os.Environment.getExternalStoragePublicDirectory(r0)     // Catch:{ Throwable -> 0x0007 }
            goto L_0x0026
        L_0x0007:
            java.io.File r0 = new java.io.File     // Catch:{ Throwable -> 0x0022 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0022 }
            r1.<init>()     // Catch:{ Throwable -> 0x0022 }
            java.io.File r2 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ Throwable -> 0x0022 }
            r1.append(r2)     // Catch:{ Throwable -> 0x0022 }
            java.lang.String r2 = "/Documents"
            r1.append(r2)     // Catch:{ Throwable -> 0x0022 }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x0022 }
            r0.<init>(r1)     // Catch:{ Throwable -> 0x0022 }
            goto L_0x0026
        L_0x0022:
            java.io.File r0 = android.os.Environment.getExternalStorageDirectory()
        L_0x0026:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.util.FileUtil.getExternalDocumentsDirectory():java.io.File");
    }

    public static String getMimeType(String str) {
        Uri parse = Uri.parse(str);
        if (parse.getScheme() != null && parse.getScheme().equals("content")) {
            return WishApplication.getInstance().getContentResolver().getType(parse);
        }
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(parse.toString()).toLowerCase());
    }
}
