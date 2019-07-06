package com.contextlogic.wish.util;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.cache.LargeParcelableCache;
import com.contextlogic.wish.util.VideoUtil.CustomVideoFormat;
import com.contextlogic.wish.util.VideoUtil.VideoParseCallback;
import com.contextlogic.wish.util.VideoUtil.VideoSpecification;
import com.crashlytics.android.Crashlytics;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.m4m.AudioFormat;
import org.m4m.IProgressListener;
import org.m4m.MediaComposer;
import org.m4m.MediaFileInfo;
import org.m4m.VideoFormat;
import org.m4m.android.AndroidMediaObjectFactory;
import org.m4m.android.AudioFormatAndroid;

public class IntentUtil {
    public static String getVideoDirectoryPath() {
        StringBuilder sb = new StringBuilder();
        sb.append(WishApplication.getInstance().getFilesDir().getAbsolutePath());
        sb.append("/");
        sb.append("videos");
        return sb.toString();
    }

    public static Intent getShareIntent(String str, String str2) {
        return getShareIntent(str, str2, null, null);
    }

    public static Intent getShareIntentWithoutFB(String str, String str2) {
        if (str2 == null && str == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        List<ResolveInfo> queryIntentActivities = WishApplication.getInstance().getPackageManager().queryIntentActivities(intent, 0);
        if (queryIntentActivities == null || queryIntentActivities.size() == 0) {
            return null;
        }
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            String str3 = resolveInfo.activityInfo.packageName;
            if (!str3.contains(".facebook.")) {
                Intent intent2 = new Intent("android.intent.action.SEND");
                intent2.setType("text/plain");
                if (str != null) {
                    intent2.putExtra("android.intent.extra.SUBJECT", str);
                }
                if (str2 != null) {
                    intent2.putExtra("android.intent.extra.TEXT", str2);
                }
                intent2.setPackage(str3);
                intent2.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
                arrayList.add(intent2);
            }
        }
        Intent createChooser = Intent.createChooser((Intent) arrayList.remove(arrayList.size() - 1), WishApplication.getInstance().getString(R.string.share));
        createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[arrayList.size()]));
        return createChooser;
    }

    public static Intent getShareIntent(String str, String str2, Uri uri, String str3) {
        if (str3 == null) {
            str3 = "text/plain";
        }
        if (uri == null || (str2 == null && str == null)) {
            Intent intent = new Intent("android.intent.action.SEND");
            if (uri != null) {
                intent.putExtra("android.intent.extra.STREAM", uri);
                intent.addFlags(1);
            }
            intent.setType(str3);
            if (str != null) {
                intent.putExtra("android.intent.extra.SUBJECT", str);
            }
            if (str2 != null) {
                intent.putExtra("android.intent.extra.TEXT", str2);
            }
            Intent createChooser = Intent.createChooser(intent, WishApplication.getInstance().getString(R.string.share));
            if (createChooser.resolveActivity(WishApplication.getInstance().getPackageManager()) == null) {
                return null;
            }
            return createChooser;
        }
        ArrayList arrayList = new ArrayList();
        Intent intent2 = new Intent("android.intent.action.SEND");
        intent2.setType(str3);
        List<ResolveInfo> queryIntentActivities = WishApplication.getInstance().getPackageManager().queryIntentActivities(intent2, 0);
        if (queryIntentActivities == null || queryIntentActivities.size() == 0) {
            return null;
        }
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            String str4 = resolveInfo.activityInfo.packageName;
            Intent intent3 = new Intent("android.intent.action.SEND");
            if (uri != null && !str4.contains(".facebook.")) {
                intent3.putExtra("android.intent.extra.STREAM", uri);
                intent3.addFlags(1);
            }
            if (str4.contains(".facebook.")) {
                intent3.setType("text/plain");
            } else {
                intent3.setType(str3);
            }
            if (str != null) {
                intent3.putExtra("android.intent.extra.SUBJECT", str);
            }
            if (str2 != null) {
                intent3.putExtra("android.intent.extra.TEXT", str2);
            }
            intent3.setPackage(str4);
            intent3.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
            arrayList.add(intent3);
        }
        Intent createChooser2 = Intent.createChooser((Intent) arrayList.remove(arrayList.size() - 1), WishApplication.getInstance().getString(R.string.share));
        createChooser2.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[arrayList.size()]));
        return createChooser2;
    }

    public static Intent getImageChooserIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.PICK");
        Intent createChooser = Intent.createChooser(intent, WishApplication.getInstance().getString(R.string.choose_a_photo));
        if (ContextCompat.checkSelfPermission(WishApplication.getInstance(), "android.permission.CAMERA") == 0) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            StringBuilder sb = new StringBuilder();
            sb.append("tmp_image_");
            sb.append(String.valueOf(System.currentTimeMillis()));
            sb.append(".jpg");
            Uri fromFile = Uri.fromFile(new File(externalStorageDirectory, sb.toString()));
            ArrayList arrayList = new ArrayList();
            Intent intent2 = new Intent("android.media.action.IMAGE_CAPTURE");
            for (ResolveInfo resolveInfo : WishApplication.getInstance().getPackageManager().queryIntentActivities(intent2, 0)) {
                String str = resolveInfo.activityInfo.packageName;
                Intent intent3 = new Intent(intent2);
                intent3.putExtra("return-data", true);
                intent3.setComponent(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
                intent3.setPackage(str);
                intent3.putExtra("output", fromFile);
                arrayList.add(intent3);
            }
            createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[0]));
            putParcelableExtra(createChooser, "ExtraTemporaryFileUrl", fromFile);
        }
        return createChooser;
    }

    public static Bitmap parseImageChooserIntentResult(Intent intent, Intent intent2) {
        return parseImageChooserIntentResult(intent, intent2, 1024, 1024);
    }

    public static Bitmap parseImageChooserIntentResult(Intent intent, Intent intent2, int i, int i2) {
        boolean z;
        if (intent2 == null) {
            z = true;
        } else {
            String action = intent2.getAction();
            z = action == null ? false : action.equals("android.media.action.IMAGE_CAPTURE");
        }
        Uri uri = (Uri) getParcelableExtra(intent, "ExtraTemporaryFileUrl");
        Uri uri2 = z ? uri : intent2 == null ? null : intent2.getData();
        if (!z && uri2 == null) {
            uri2 = uri;
        }
        Bitmap openImageUri = BitmapUtil.openImageUri(uri2, i, i2);
        if (uri != null) {
            File file = new File(uri.getPath());
            if (file.exists()) {
                file.delete();
            }
        }
        return openImageUri;
    }

    public static Intent getVideoChooserIntent() {
        Uri uri;
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction("android.intent.action.PICK");
        Intent createChooser = Intent.createChooser(intent, WishApplication.getInstance().getString(R.string.choose_a_video));
        if (ContextCompat.checkSelfPermission(WishApplication.getInstance(), "android.permission.CAMERA") == 0) {
            File file = new File(getVideoDirectoryPath());
            file.mkdirs();
            if (VERSION.SDK_INT < 23) {
                File externalStorageDirectory = Environment.getExternalStorageDirectory();
                StringBuilder sb = new StringBuilder();
                sb.append("tmp_video_");
                sb.append(String.valueOf(System.currentTimeMillis()));
                sb.append(".mp4");
                uri = Uri.fromFile(new File(externalStorageDirectory, sb.toString()));
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(file.getAbsolutePath());
                sb2.append("/");
                sb2.append(String.format("video_%d.mp4", new Object[]{Long.valueOf(System.currentTimeMillis())}));
                uri = FileProvider.getUriForFile(WishApplication.getInstance(), getFileProviderAuthority(), new File(sb2.toString()));
            }
            ArrayList arrayList = new ArrayList();
            Intent intent2 = new Intent("android.media.action.VIDEO_CAPTURE");
            for (ResolveInfo resolveInfo : WishApplication.getInstance().getPackageManager().queryIntentActivities(intent2, 0)) {
                String str = resolveInfo.activityInfo.packageName;
                Intent intent3 = new Intent(intent2);
                intent3.putExtra("return-data", true);
                intent3.setComponent(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
                intent3.setPackage(str);
                intent3.putExtra("output", uri);
                arrayList.add(intent3);
            }
            createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[0]));
            putParcelableExtra(createChooser, "ExtraTemporaryFileUrl", uri);
        }
        return createChooser;
    }

    public static String getFileProviderAuthority() {
        return String.format("%s.fileprovider", new Object[]{WishApplication.getInstance().getPackageName()});
    }

    public static void parseVideoChooserIntentResult(Intent intent, Intent intent2, VideoSpecification videoSpecification, VideoParseCallback videoParseCallback) {
        boolean z;
        if (intent2 == null) {
            z = true;
        } else {
            String action = intent2.getAction();
            z = action == null ? false : action.equals("android.media.action.VIDEO_CAPTURE");
        }
        Uri uri = (Uri) getParcelableExtra(intent, "ExtraTemporaryFileUrl");
        Uri uri2 = z ? uri : intent2 == null ? null : intent2.getData();
        if (!z && uri2 == null) {
            uri2 = uri;
        }
        String path = FileUtil.getPath(uri2);
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(path);
        if (fileExtensionFromUrl == null || fileExtensionFromUrl.equals("")) {
            fileExtensionFromUrl = "mp4";
        }
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        StringBuilder sb = new StringBuilder();
        sb.append("video_");
        sb.append(System.currentTimeMillis());
        String absolutePath = new File(externalStorageDirectory, String.format("Wish/%s.%s", new Object[]{sb.toString(), fileExtensionFromUrl})).getAbsolutePath();
        new File(absolutePath).getParentFile().mkdirs();
        if (path == null) {
            return;
        }
        if (VERSION.SDK_INT < 18) {
            transferVideo(path, absolutePath, uri, videoParseCallback);
        } else {
            encodeVideo(path, absolutePath, uri, videoSpecification, videoParseCallback);
        }
    }

    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v1 */
    /* JADX WARNING: type inference failed for: r5v2, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r5v3 */
    /* JADX WARNING: type inference failed for: r0v2, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r5v4 */
    /* JADX WARNING: type inference failed for: r5v5, types: [java.io.OutputStream, java.io.FileOutputStream] */
    /* JADX WARNING: type inference failed for: r0v3 */
    /* JADX WARNING: type inference failed for: r0v4 */
    /* JADX WARNING: type inference failed for: r5v6 */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0041 A[SYNTHETIC, Splitter:B:29:0x0041] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0046 A[SYNTHETIC, Splitter:B:33:0x0046] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x004e A[SYNTHETIC, Splitter:B:41:0x004e] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0053 A[SYNTHETIC, Splitter:B:45:0x0053] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0070  */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void transferVideo(java.lang.String r5, java.lang.String r6, android.net.Uri r7, com.contextlogic.wish.util.VideoUtil.VideoParseCallback r8) {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ Throwable -> 0x004a, all -> 0x003d }
            r1.<init>(r6)     // Catch:{ Throwable -> 0x004a, all -> 0x003d }
            java.io.File r1 = r1.getParentFile()     // Catch:{ Throwable -> 0x004a, all -> 0x003d }
            r1.mkdirs()     // Catch:{ Throwable -> 0x004a, all -> 0x003d }
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x004a, all -> 0x003d }
            r1.<init>(r5)     // Catch:{ Throwable -> 0x004a, all -> 0x003d }
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x003b, all -> 0x0039 }
            r5.<init>(r6)     // Catch:{ Throwable -> 0x003b, all -> 0x0039 }
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch:{ Throwable -> 0x004c, all -> 0x0036 }
        L_0x001b:
            int r3 = r1.read(r2)     // Catch:{ Throwable -> 0x004c, all -> 0x0036 }
            r4 = -1
            if (r3 == r4) goto L_0x0027
            r4 = 0
            r5.write(r2, r4, r3)     // Catch:{ Throwable -> 0x004c, all -> 0x0036 }
            goto L_0x001b
        L_0x0027:
            r5.flush()     // Catch:{ Throwable -> 0x004c, all -> 0x0036 }
            if (r5 == 0) goto L_0x002f
            r5.close()     // Catch:{ Throwable -> 0x002f }
        L_0x002f:
            if (r1 == 0) goto L_0x0034
            r1.close()     // Catch:{ Throwable -> 0x0034 }
        L_0x0034:
            r0 = r6
            goto L_0x0056
        L_0x0036:
            r6 = move-exception
            r0 = r5
            goto L_0x003f
        L_0x0039:
            r6 = move-exception
            goto L_0x003f
        L_0x003b:
            r5 = r0
            goto L_0x004c
        L_0x003d:
            r6 = move-exception
            r1 = r0
        L_0x003f:
            if (r0 == 0) goto L_0x0044
            r0.close()     // Catch:{ Throwable -> 0x0044 }
        L_0x0044:
            if (r1 == 0) goto L_0x0049
            r1.close()     // Catch:{ Throwable -> 0x0049 }
        L_0x0049:
            throw r6
        L_0x004a:
            r5 = r0
            r1 = r5
        L_0x004c:
            if (r5 == 0) goto L_0x0051
            r5.close()     // Catch:{ Throwable -> 0x0051 }
        L_0x0051:
            if (r1 == 0) goto L_0x0056
            r1.close()     // Catch:{ Throwable -> 0x0056 }
        L_0x0056:
            if (r0 == 0) goto L_0x0070
            if (r7 == 0) goto L_0x006c
            java.io.File r5 = new java.io.File
            java.lang.String r7 = r7.getPath()
            r5.<init>(r7)
            boolean r7 = r5.exists()
            if (r7 == 0) goto L_0x006c
            r5.delete()
        L_0x006c:
            r8.onVideoParsed(r6)
            goto L_0x0073
        L_0x0070:
            r8.onVideoParseFailed()
        L_0x0073:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.util.IntentUtil.transferVideo(java.lang.String, java.lang.String, android.net.Uri, com.contextlogic.wish.util.VideoUtil$VideoParseCallback):void");
    }

    private static void encodeVideo(String str, String str2, Uri uri, VideoSpecification videoSpecification, VideoParseCallback videoParseCallback) {
        String str3;
        Throwable th;
        String str4 = str;
        String str5 = str2;
        VideoSpecification videoSpecification2 = videoSpecification;
        final long currentTimeMillis = System.currentTimeMillis();
        AndroidMediaObjectFactory androidMediaObjectFactory = new AndroidMediaObjectFactory(WishApplication.getInstance());
        MediaComposer[] mediaComposerArr = new MediaComposer[1];
        long[] jArr = new long[1];
        final long[] jArr2 = jArr;
        final MediaComposer[] mediaComposerArr2 = mediaComposerArr;
        final String str6 = str4;
        final String str7 = str5;
        final Uri uri2 = uri;
        AnonymousClass1 r11 = r1;
        final VideoParseCallback videoParseCallback2 = videoParseCallback;
        AnonymousClass1 r1 = new IProgressListener() {
            public void onMediaPause() {
            }

            public void onMediaStart() {
            }

            public void onMediaStop() {
            }

            public void onMediaProgress(float f) {
                long currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
                if (((double) f) > 0.05d || currentTimeMillis > 10000) {
                    double d = 100.0d / ((double) ((jArr2[0] * 6) + 5000));
                    double d2 = (double) ((f * 100.0f) / ((float) currentTimeMillis));
                    if (d2 < d && mediaComposerArr2[0] != null) {
                        mediaComposerArr2[0].stop();
                        mediaComposerArr2[0] = null;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Video encoder too slow: ");
                        sb.append(currentTimeMillis);
                        sb.append(" ");
                        sb.append(d);
                        sb.append(" ");
                        sb.append(d2);
                        Crashlytics.logException(new Exception(sb.toString()));
                        IntentUtil.transferVideo(str6, str7, uri2, videoParseCallback2);
                    }
                }
            }

            public void onMediaDone() {
                if (uri2 != null) {
                    File file = new File(uri2.getPath());
                    if (file.exists()) {
                        file.delete();
                    }
                }
                videoParseCallback2.onVideoParsed(str7);
            }

            public void onError(Exception exc) {
                StringBuilder sb = new StringBuilder();
                sb.append("Encode exception: ");
                sb.append(exc.toString());
                Crashlytics.logException(new Exception(sb.toString()));
                IntentUtil.transferVideo(str6, str7, uri2, videoParseCallback2);
            }
        };
        MediaComposer mediaComposer = new MediaComposer(androidMediaObjectFactory, r11);
        mediaComposerArr[0] = mediaComposer;
        try {
            MediaFileInfo mediaFileInfo = new MediaFileInfo(new AndroidMediaObjectFactory(WishApplication.getInstance()));
            org.m4m.Uri uri3 = new org.m4m.Uri(str4);
            mediaFileInfo.setUri(uri3);
            long durationInMicroSec = mediaFileInfo.getDurationInMicroSec() / 1000;
            jArr[0] = durationInMicroSec;
            if (videoSpecification2.maxDuration > 0 && durationInMicroSec > 0) {
                try {
                    if (durationInMicroSec > videoSpecification2.maxDuration) {
                        videoParseCallback.onVideoTooLong();
                        return;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    str3 = str2;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Encode handleLoadingSuccess exception: ");
                    sb.append(th.toString());
                    Crashlytics.logException(new Exception(sb.toString()));
                    transferVideo(str4, str3, uri, videoParseCallback);
                }
            }
            mediaComposer.addSourceFile(uri3);
            str3 = str2;
            try {
                mediaComposer.setTargetFile(str3, mediaFileInfo.getRotation());
                VideoFormat videoFormat = (VideoFormat) mediaFileInfo.getVideoFormat();
                int width = videoFormat.getVideoFrameSize().width();
                int height = videoFormat.getVideoFrameSize().height();
                if (videoSpecification2.maxWidth > 0 && videoSpecification2.maxHeight > 0) {
                    float f = (float) width;
                    float f2 = (float) height;
                    float min = Math.min(((float) videoSpecification2.maxWidth) / f, ((float) videoSpecification2.maxHeight) / f2);
                    if (min < 1.0f) {
                        width = (int) (f * min);
                        height = (int) (f2 * min);
                    }
                } else if (videoSpecification2.maxWidth > 0) {
                    float f3 = (float) width;
                    float f4 = ((float) videoSpecification2.maxWidth) / f3;
                    if (f4 < 1.0f) {
                        width = (int) (f3 * f4);
                        height = (int) (((float) height) * f4);
                    }
                } else if (videoSpecification2.maxHeight > 0) {
                    float f5 = (float) height;
                    float f6 = ((float) videoSpecification2.maxHeight) / f5;
                    if (f6 < 1.0f) {
                        width = (int) (((float) width) * f6);
                        height = (int) (f5 * f6);
                    }
                }
                if (height % 2 != 0) {
                    height--;
                }
                if (width % 2 != 0) {
                    width--;
                }
                CustomVideoFormat customVideoFormat = new CustomVideoFormat(videoFormat.getMimeType(), width, height);
                customVideoFormat.setVideoBitRateInKBytes(videoSpecification2.bitRate);
                customVideoFormat.setVideoFrameRate(videoSpecification2.frameRate);
                customVideoFormat.setVideoIFrameInterval(videoSpecification2.iFrameInterval);
                mediaComposer.setTargetVideoFormat(customVideoFormat);
                AudioFormat audioFormat = (AudioFormat) mediaFileInfo.getAudioFormat();
                AudioFormatAndroid audioFormatAndroid = new AudioFormatAndroid(audioFormat.getMimeType(), audioFormat.getAudioSampleRateInHz(), audioFormat.getAudioChannelCount());
                audioFormatAndroid.setAudioBitrateInBytes(98304);
                audioFormatAndroid.setAudioProfile(2);
                mediaComposer.setTargetAudioFormat(audioFormatAndroid);
                mediaComposer.start();
            } catch (Throwable th3) {
                th = th3;
                th = th;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Encode handleLoadingSuccess exception: ");
                sb2.append(th.toString());
                Crashlytics.logException(new Exception(sb2.toString()));
                transferVideo(str4, str3, uri, videoParseCallback);
            }
        } catch (Throwable th4) {
            th = th4;
            str3 = str2;
            th = th;
            StringBuilder sb22 = new StringBuilder();
            sb22.append("Encode handleLoadingSuccess exception: ");
            sb22.append(th.toString());
            Crashlytics.logException(new Exception(sb22.toString()));
            transferVideo(str4, str3, uri, videoParseCallback);
        }
    }

    public static <T extends Parcelable> T getParcelableExtra(Intent intent, String str) {
        try {
            T parcelableExtra = intent.getParcelableExtra(str);
            if (parcelableExtra == null) {
                Bundle bundleExtra = intent.getBundleExtra("ExtraBundledParcelables");
                if (bundleExtra != null) {
                    parcelableExtra = bundleExtra.getParcelable(str);
                }
            }
            return parcelableExtra;
        } catch (BadParcelableException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Catch FB BadParcelableException: ");
            sb.append(e.getMessage());
            Crashlytics.logException(new Exception(sb.toString()));
            return null;
        }
    }

    public static <T extends Parcelable> void putParcelableExtra(Intent intent, String str, T t) {
        Bundle bundleExtra = intent.getBundleExtra("ExtraBundledParcelables");
        if (bundleExtra == null) {
            bundleExtra = new Bundle();
        }
        bundleExtra.putParcelable(str, t);
        intent.putExtra("ExtraBundledParcelables", bundleExtra);
    }

    public static <T extends Parcelable> ArrayList<T> getParcelableArrayListExtra(Intent intent, String str) {
        ArrayList<T> parcelableArrayListExtra = intent.getParcelableArrayListExtra(str);
        if (parcelableArrayListExtra != null) {
            return parcelableArrayListExtra;
        }
        Bundle bundleExtra = intent.getBundleExtra("ExtraBundledParcelables");
        return bundleExtra != null ? bundleExtra.getParcelableArrayList(str) : parcelableArrayListExtra;
    }

    public static <T extends Parcelable> void putParcelableArrayListExtra(Intent intent, String str, ArrayList<T> arrayList) {
        Bundle bundleExtra = intent.getBundleExtra("ExtraBundledParcelables");
        if (bundleExtra == null) {
            bundleExtra = new Bundle();
        }
        bundleExtra.putParcelableArrayList(str, arrayList);
        intent.putExtra("ExtraBundledParcelables", bundleExtra);
    }

    public static <T extends Parcelable> void putLargeParcelableExtra(Intent intent, String str, T t) {
        intent.putExtra(str, LargeParcelableCache.getInstance().storeParcelable(t));
    }

    public static <T extends Parcelable> void putLargeParcelableExtra(Bundle bundle, String str, T t) {
        bundle.putString(str, LargeParcelableCache.getInstance().storeParcelable(t));
    }

    public static <T extends Parcelable> T getLargeParcelableExtra(Bundle bundle, String str, Class<T> cls) {
        String string = bundle == null ? null : bundle.getString(str);
        if (!TextUtils.isEmpty(string)) {
            return LargeParcelableCache.getInstance().getParcelable(string, cls);
        }
        return null;
    }

    public static <T extends Parcelable> T getLargeParcelableExtra(Intent intent, String str, Class<T> cls) {
        String stringExtra = intent == null ? null : intent.getStringExtra(str);
        if (!TextUtils.isEmpty(stringExtra)) {
            return LargeParcelableCache.getInstance().getParcelable(stringExtra, cls);
        }
        return null;
    }

    public static boolean safeToUnparcel(Intent intent) {
        if (intent == null) {
            return false;
        }
        try {
            intent.hasExtra("");
            return true;
        } catch (Throwable th) {
            Crashlytics.logException(th);
            return false;
        }
    }
}
