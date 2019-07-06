package com.onfido.android.sdk.capture.ui.camera.face;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Area;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.media.MediaRecorder.OnInfoListener;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import com.onfido.android.sdk.capture.ui.Size;
import com.onfido.android.sdk.capture.ui.camera.FrameCallback;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CameraSource implements OnInfoListener {
    private SimpleDateFormat A;
    Parameters a;
    /* access modifiers changed from: private */
    public Context b;
    private final Object c;
    private Camera d;
    /* access modifiers changed from: private */
    public int e;
    private int f;
    private Size g;
    private float h;
    /* access modifiers changed from: private */
    public int i;
    /* access modifiers changed from: private */
    public int j;
    /* access modifiers changed from: private */
    public String k;
    /* access modifiers changed from: private */
    public String l;
    /* access modifiers changed from: private */
    public String m;
    public int mRequestedPreviewHeight;
    public int mRequestedPreviewWidth;
    private boolean n;
    /* access modifiers changed from: private */
    public int o;
    /* access modifiers changed from: private */
    public int p;
    /* access modifiers changed from: private */
    public int q;
    /* access modifiers changed from: private */
    public SurfaceHolder r;
    /* access modifiers changed from: private */
    public boolean s;
    /* access modifiers changed from: private */
    public FrameCallback v;
    private MediaRecorder w;
    private String x;
    private boolean y;
    private MediaCaptureCallback z;

    /* renamed from: com.onfido.android.sdk.capture.ui.camera.face.CameraSource$1 reason: invalid class name */
    class AnonymousClass1 implements AutoFocusCallback {
        final /* synthetic */ ShutterCallback a;
        final /* synthetic */ PictureCallback b;
        final /* synthetic */ boolean c;
        final /* synthetic */ CameraSource d;

        public void onAutoFocus(final boolean z) {
            String str = "OpenCameraSource";
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("onAutoFocus:");
                sb.append(z);
                Log.d(str, sb.toString());
                this.d.takePicture(this.a, new PictureCallback() {
                    public void onPictureTaken(byte[] bArr, int i, int i2) {
                        Log.d("OpenCameraSource", "onPictureTaken");
                        AnonymousClass1.this.b.onPictureTaken(bArr, i, i2);
                        if (z) {
                            AnonymousClass1.this.d.d();
                        }
                    }
                }, this.c);
            } catch (j unused) {
                Log.d("OpenCameraSource", "Error taking picture after autofocus");
            }
        }
    }

    public interface AutoFocusCallback {
        void onAutoFocus(boolean z);
    }

    public static class Builder {
        private CameraSource a = new CameraSource(null);

        public Builder(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("No context supplied.");
            }
            this.a.b = context;
        }

        private void a(int i, int i2) {
            if (i <= 0 || i > 1000000 || i2 <= 0 || i2 > 1000000) {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid picture size: ");
                sb.append(i);
                sb.append("x");
                sb.append(i2);
                throw new IllegalArgumentException(sb.toString());
            }
        }

        public CameraSource build() {
            return this.a;
        }

        public Builder setFacing(int i) {
            if (i == 0 || i == 1) {
                this.a.e = i;
                return this;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid camera: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }

        public Builder setFlashMode(String str) {
            this.a.m = str;
            return this;
        }

        public Builder setFocusMode(String str) {
            this.a.k = str;
            return this;
        }

        public Builder setRequestedPictureSize(int i, int i2) {
            a(i, i2);
            this.a.i = i;
            this.a.j = i2;
            return this;
        }

        public Builder setRequestedPreviewSize(int i, int i2) {
            a(i, i2);
            this.a.mRequestedPreviewWidth = i;
            this.a.mRequestedPreviewHeight = i2;
            return this;
        }

        public Builder setupRecording(int i, int i2, int i3, SurfaceHolder surfaceHolder) {
            this.a.o = i;
            this.a.p = i2;
            this.a.q = i3;
            this.a.r = surfaceHolder;
            this.a.s = true;
            return this;
        }
    }

    public interface PictureCallback {
        void onPictureTaken(byte[] bArr, int i, int i2);
    }

    public interface SetParametersCallback {
        Parameters call(Parameters parameters, Camera camera);
    }

    public interface ShutterCallback {
        void onShutter();
    }

    private class a implements android.hardware.Camera.AutoFocusCallback {
        /* access modifiers changed from: private */
        public AutoFocusCallback b;

        private a() {
        }

        /* synthetic */ a(CameraSource cameraSource, AnonymousClass1 r2) {
            this();
        }

        public void onAutoFocus(boolean z, Camera camera) {
            if (this.b != null) {
                this.b.onAutoFocus(z);
            }
        }
    }

    class c extends Exception {
        c(String str) {
            super(str);
        }
    }

    class d extends Exception {
        d(String str) {
            super(str);
        }
    }

    private class e implements PreviewCallback {
        private e() {
        }

        /* synthetic */ e(CameraSource cameraSource, AnonymousClass1 r2) {
            this();
        }

        public void onPreviewFrame(byte[] bArr, Camera camera) {
            if (CameraSource.this.v != null && bArr != null) {
                CameraSource.this.v.onNextFrame(bArr);
            }
        }
    }

    private interface f<T> {
        int a();

        Camera.Size a(int i);

        T c(int i);
    }

    private class g implements android.hardware.Camera.PictureCallback {
        /* access modifiers changed from: private */
        public PictureCallback b;

        private g() {
        }

        /* synthetic */ g(CameraSource cameraSource, AnonymousClass1 r2) {
            this();
        }

        public void onPictureTaken(byte[] bArr, Camera camera) {
            if (this.b != null) {
                this.b.onPictureTaken(bArr, camera.getParameters().getPictureSize().width, camera.getParameters().getPictureSize().height);
            }
        }
    }

    private class h implements android.hardware.Camera.ShutterCallback {
        /* access modifiers changed from: private */
        public ShutterCallback b;

        private h() {
        }

        /* synthetic */ h(CameraSource cameraSource, AnonymousClass1 r2) {
            this();
        }

        public void onShutter() {
            if (this.b != null) {
                this.b.onShutter();
            }
        }
    }

    private static class i {
        private Size a;
        private Size b;

        public i(Camera.Size size, Camera.Size size2) {
            this.a = new Size(size.width, size.height);
            if (size2 != null) {
                this.b = new Size(size2.width, size2.height);
            }
        }

        public Size a() {
            return this.a;
        }

        public Size b() {
            return this.b;
        }
    }

    class j extends Exception {
        j() {
            super("Error taking picture");
        }
    }

    private CameraSource() {
        this.c = new Object();
        this.e = 0;
        this.h = 30.0f;
        this.mRequestedPreviewWidth = 1024;
        this.mRequestedPreviewHeight = 768;
        this.i = -1;
        this.j = -1;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = false;
        this.s = false;
        this.z = null;
        this.A = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        this.a = null;
    }

    /* synthetic */ CameraSource(AnonymousClass1 r1) {
        this();
    }

    private static int a(int i2, boolean z2) {
        CameraInfo cameraInfo = new CameraInfo();
        for (int i3 = 0; i3 < Camera.getNumberOfCameras(); i3++) {
            Camera.getCameraInfo(i3, cameraInfo);
            if (cameraInfo.facing == i2) {
                return i3;
            }
        }
        if (!z2) {
            return -1;
        }
        return a(i2 == 0 ? 1 : 0, false);
    }

    private static Size a(Camera.Size size) {
        return new Size(size.width, size.height);
    }

    private static Size a(Camera camera, int i2, int i3) {
        return a(b(camera.getParameters().getSupportedPictureSizes(), i2, i3));
    }

    private static i a(Camera camera, int i2, int i3, final int i4, final int i5, List<Camera.Size> list) {
        final Map a2 = a(camera, list);
        final Camera.Size[] sizeArr = (Camera.Size[]) a2.keySet().toArray(new Camera.Size[a2.size()]);
        return (i) a((f<T>) new f<i>() {
            public int a() {
                return a2.size();
            }

            public Camera.Size a(int i) {
                return sizeArr[i];
            }

            /* renamed from: b */
            public i c(int i) {
                Camera.Size a2 = a(i);
                List list = (List) a2.get(a2);
                Camera.Size size = (i4 == -1 || i5 == -1) ? list.size() > 0 ? (Camera.Size) list.get(0) : null : CameraSource.b(list, i4, i5);
                return new i(a2, size);
            }
        }, i2, i3);
    }

    private static i a(Camera camera, int i2, int i3, List<Camera.Size> list) {
        return a(camera, i2, i3, -1, -1, list);
    }

    private File a(File file) {
        String format = this.A.format(new Date());
        StringBuilder sb = new StringBuilder();
        sb.append(file.getPath());
        sb.append(File.separator);
        sb.append("ONFIDO_VID_");
        sb.append(format);
        sb.append(".mp4");
        return new File(sb.toString());
    }

    private static <T> T a(f<T> fVar, int i2, int i3) {
        return a(fVar, i2, i3, false);
    }

    private static <T> T a(f<T> fVar, int i2, int i3, boolean z2) {
        int i4 = Integer.MAX_VALUE;
        int i5 = -1;
        int i6 = -1;
        int i7 = Integer.MAX_VALUE;
        for (int i8 = 0; i8 < fVar.a(); i8++) {
            Camera.Size a2 = fVar.a(i8);
            int i9 = a2.width;
            int i10 = i9 - i2;
            int i11 = a2.height - i3;
            int abs = Math.abs(i10) + Math.abs(i11);
            if (abs < i4) {
                i6 = i8;
                i4 = abs;
            }
            int i12 = i10 + i11;
            if (z2 && i10 > 0 && i11 > 0 && i12 < i7) {
                i5 = i8;
                i7 = i12;
            }
        }
        if (i5 <= -1) {
            i5 = i6;
        }
        if (i5 > -1) {
            return fVar.c(i5);
        }
        return null;
    }

    private static Map<Camera.Size, List<Camera.Size>> a(Camera camera, List<Camera.Size> list) {
        List<Camera.Size> supportedPreviewSizes = camera.getParameters().getSupportedPreviewSizes();
        HashMap hashMap = new HashMap();
        for (Camera.Size size : supportedPreviewSizes) {
            float f2 = ((float) size.width) / ((float) size.height);
            ArrayList arrayList = new ArrayList();
            hashMap.put(size, arrayList);
            for (Camera.Size size2 : list) {
                if (Math.abs(f2 - (((float) size2.width) / ((float) size2.height))) < 0.01f) {
                    arrayList.add(size2);
                }
            }
        }
        return hashMap;
    }

    private void a() {
        synchronized (this.c) {
            try {
                this.d.startPreview();
                this.n = true;
                e();
            } catch (RuntimeException e2) {
                throw new UnknownCameraException(e2.getMessage());
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void a(Camera camera, Parameters parameters, int i2) {
        int i3;
        int i4;
        int rotation = ((WindowManager) this.b.getSystemService("window")).getDefaultDisplay().getRotation();
        int i5 = 0;
        switch (rotation) {
            case 0:
                break;
            case 1:
                i5 = 90;
                break;
            case 2:
                i5 = 180;
                break;
            case 3:
                i5 = 270;
                break;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Bad rotation value: ");
                sb.append(rotation);
                Log.e("OpenCameraSource", sb.toString());
                break;
        }
        CameraInfo cameraInfo = new CameraInfo();
        Camera.getCameraInfo(i2, cameraInfo);
        if (cameraInfo.facing == 1) {
            i3 = (cameraInfo.orientation + i5) % 360;
            i4 = (360 - i3) % 360;
        } else {
            i3 = ((cameraInfo.orientation - i5) + 360) % 360;
            i4 = i3;
        }
        this.f = i3 / 90;
        camera.setDisplayOrientation(i4);
        parameters.setRotation(i3);
    }

    private void a(Camera camera, Size size) {
        this.w = new MediaRecorder();
        this.w.setCamera(camera);
        this.w.setAudioSource(5);
        this.w.setVideoSource(1);
        this.w.setMaxDuration(this.q);
        this.w.setOnInfoListener(this);
        CamcorderProfile camcorderProfile = CamcorderProfile.get(this.e, CamcorderProfile.hasProfile(this.e, this.o) ? this.o : 0);
        this.w.setOutputFormat(camcorderProfile.fileFormat);
        this.w.setVideoFrameRate(camcorderProfile.videoFrameRate);
        if (size != null) {
            int height = size.getHeight();
            int width = size.getWidth();
            if (height > width) {
                width = size.getHeight();
                height = size.getWidth();
            }
            this.w.setVideoSize(width, height);
        }
        this.w.setVideoEncodingBitRate(this.p);
        this.w.setVideoEncoder(camcorderProfile.videoCodec);
        this.w.setAudioEncodingBitRate(camcorderProfile.audioBitRate);
        this.w.setAudioChannels(camcorderProfile.audioChannels);
        this.w.setAudioSamplingRate(camcorderProfile.audioSampleRate);
        this.w.setAudioEncoder(camcorderProfile.audioCodec);
        this.w.setPreviewDisplay(this.r.getSurface());
        this.w.setOrientationHint(this.f * 90);
    }

    private void a(String str) {
        boolean z2 = str != null && !str.equals(this.k);
        StringBuilder sb = new StringBuilder();
        sb.append("focus mode updated:");
        sb.append(z2);
        sb.append(" newFocusMode:");
        sb.append(str);
        sb.append(" mFocusMode:");
        sb.append(this.k);
        Log.d("OpenCameraSource", sb.toString());
        this.l = z2 ? this.k : this.l;
        this.k = str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0028, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(final java.util.List<android.hardware.Camera.Area> r3, final com.onfido.android.sdk.capture.ui.camera.face.CameraSource.AutoFocusCallback r4, final boolean r5) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.c
            monitor-enter(r0)
            android.hardware.Camera r1 = r2.d     // Catch:{ all -> 0x0029 }
            if (r1 == 0) goto L_0x0027
            java.lang.String r1 = "auto"
            boolean r1 = r2.setFocusMode(r1)     // Catch:{ all -> 0x0029 }
            if (r1 != 0) goto L_0x0017
            if (r4 == 0) goto L_0x0015
            r3 = 0
            r4.onAutoFocus(r3)     // Catch:{ all -> 0x0029 }
        L_0x0015:
            monitor-exit(r0)     // Catch:{ all -> 0x0029 }
            return
        L_0x0017:
            com.onfido.android.sdk.capture.ui.camera.face.CameraSource$3 r1 = new com.onfido.android.sdk.capture.ui.camera.face.CameraSource$3     // Catch:{ all -> 0x0029 }
            r1.<init>(r3)     // Catch:{ all -> 0x0029 }
            r2.setParameters(r1)     // Catch:{ all -> 0x0029 }
            com.onfido.android.sdk.capture.ui.camera.face.CameraSource$4 r3 = new com.onfido.android.sdk.capture.ui.camera.face.CameraSource$4     // Catch:{ all -> 0x0029 }
            r3.<init>(r4, r5)     // Catch:{ all -> 0x0029 }
            r2.autoFocus(r3)     // Catch:{ all -> 0x0029 }
        L_0x0027:
            monitor-exit(r0)     // Catch:{ all -> 0x0029 }
            return
        L_0x0029:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0029 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.face.CameraSource.a(java.util.List, com.onfido.android.sdk.capture.ui.camera.face.CameraSource$AutoFocusCallback, boolean):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0035, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(android.hardware.Camera.Parameters r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.c
            monitor-enter(r0)
            android.hardware.Camera r1 = r4.d     // Catch:{ all -> 0x0036 }
            r2 = 0
            if (r1 == 0) goto L_0x0034
            if (r5 == 0) goto L_0x0034
            boolean r1 = r4.c()     // Catch:{ all -> 0x0036 }
            if (r1 == 0) goto L_0x002f
            android.hardware.Camera r1 = r4.d     // Catch:{ Exception -> 0x0025 }
            r1.setParameters(r5)     // Catch:{ Exception -> 0x0025 }
            java.lang.String r1 = r5.getFlashMode()     // Catch:{ Exception -> 0x0025 }
            r4.m = r1     // Catch:{ Exception -> 0x0025 }
            java.lang.String r5 = r5.getFocusMode()     // Catch:{ Exception -> 0x0025 }
            r4.a(r5)     // Catch:{ Exception -> 0x0025 }
            r5 = 1
            monitor-exit(r0)     // Catch:{ all -> 0x0036 }
            return r5
        L_0x0025:
            r5 = move-exception
            java.lang.String r1 = "OpenCameraSource"
            java.lang.String r3 = "Error: failed to set parameters"
            android.util.Log.e(r1, r3, r5)     // Catch:{ all -> 0x0036 }
            monitor-exit(r0)     // Catch:{ all -> 0x0036 }
            return r2
        L_0x002f:
            r4.b(r5)     // Catch:{ all -> 0x0036 }
            monitor-exit(r0)     // Catch:{ all -> 0x0036 }
            return r2
        L_0x0034:
            monitor-exit(r0)     // Catch:{ all -> 0x0036 }
            return r2
        L_0x0036:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0036 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.face.CameraSource.a(android.hardware.Camera$Parameters):boolean");
    }

    private int[] a(Camera camera, float f2) {
        int i2 = (int) (f2 * 1000.0f);
        int[] iArr = null;
        int i3 = Integer.MAX_VALUE;
        for (int[] iArr2 : camera.getParameters().getSupportedPreviewFpsRange()) {
            int abs = Math.abs(i2 - iArr2[0]) + Math.abs(i2 - iArr2[1]);
            if (abs < i3) {
                iArr = iArr2;
                i3 = abs;
            }
        }
        return iArr;
    }

    /* access modifiers changed from: private */
    public static Camera.Size b(final List<Camera.Size> list, int i2, int i3) {
        return (Camera.Size) a((f<T>) new f<Camera.Size>() {
            public int a() {
                return list.size();
            }

            public Camera.Size a(int i) {
                return (Camera.Size) list.get(i);
            }

            /* renamed from: b */
            public Camera.Size c(int i) {
                return a(i);
            }
        }, i2, i3);
    }

    private void b() {
        synchronized (this.c) {
            if (this.y) {
                stopVideo();
                notifyCancelRecording();
            }
            this.d.stopPreview();
            this.n = false;
        }
    }

    private void b(Parameters parameters) {
        if (this.a == null) {
            this.a = parameters;
            return;
        }
        Parameters parameters2 = this.a;
        StringBuilder sb = new StringBuilder();
        sb.append(this.a.flatten());
        sb.append(parameters.flatten());
        parameters2.unflatten(sb.toString());
    }

    /* access modifiers changed from: private */
    public static boolean b(Parameters parameters, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("setFocusMode:");
        sb.append(str);
        Log.d("OpenCameraSource", sb.toString());
        if (str != null) {
            if (parameters.getSupportedFocusModes().contains(str)) {
                parameters.setFocusMode(str);
                return true;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Camera focus mode: ");
            sb2.append(str);
            sb2.append(" is not supported on this device.");
            Log.i("OpenCameraSource", sb2.toString());
        }
        return false;
    }

    private boolean c() {
        boolean z2;
        synchronized (this.c) {
            z2 = this.d != null && this.n;
        }
        return z2;
    }

    private static boolean c(Parameters parameters, String str) {
        if (str != null) {
            List supportedFlashModes = parameters.getSupportedFlashModes();
            if (supportedFlashModes == null || !supportedFlashModes.contains(str)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Camera flash mode: ");
                sb.append(str);
                sb.append(" is not supported on this device.");
                Log.i("OpenCameraSource", sb.toString());
            } else {
                parameters.setFlashMode(str);
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void d() {
        synchronized (this.c) {
            if (this.l != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Reverting focus mode from:");
                sb.append(this.k);
                sb.append(" to:");
                sb.append(this.l);
                Log.d("OpenCameraSource", sb.toString());
                setParameters(new SetParametersCallback() {
                    public Parameters call(Parameters parameters, Camera camera) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Reverting focus mode from:");
                        sb.append(parameters.getFocusMode());
                        sb.append(" to:");
                        sb.append(CameraSource.this.l);
                        Log.d("OpenCameraSource", sb.toString());
                        if ("auto".equals(parameters.getFocusMode()) && !"auto".equals(CameraSource.this.l)) {
                            parameters.setFocusAreas(null);
                            CameraSource.this.cancelAutoFocus();
                            Log.d("OpenCameraSource", "canceledAutoFocus and setted focus areas to null");
                        }
                        CameraSource.b(parameters, CameraSource.this.l);
                        return parameters;
                    }
                });
                cancelAutoFocus();
                this.l = null;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("mFocusMode:");
                sb2.append(this.k);
                sb2.append(" mPreviousFocusMode:");
                sb2.append(this.l);
                Log.d("OpenCameraSource", sb2.toString());
            }
        }
    }

    private void e() {
        if (this.a != null) {
            a(this.a);
            this.a = null;
        }
    }

    private void f() {
        this.a = null;
    }

    @SuppressLint({"InlinedApi"})
    private Camera g() {
        i iVar;
        Size size;
        int i2;
        int i3;
        Log.d("OpenCameraSource", "createCamera");
        int a2 = a(this.e, true);
        if (a2 == -1) {
            throw new d("Could not find any camera.");
        }
        try {
            Camera open = Camera.open(a2);
            if (this.i == -1 || this.j == -1) {
                Log.d("OpenCameraSource", "Picture size not set");
                iVar = a(open, this.mRequestedPreviewWidth, this.mRequestedPreviewHeight, open.getParameters().getSupportedPictureSizes());
            } else {
                iVar = a(open, this.mRequestedPreviewWidth, this.mRequestedPreviewHeight, this.i, this.j, open.getParameters().getSupportedPictureSizes());
            }
            StringBuilder sb = new StringBuilder();
            sb.append("sizePair:");
            sb.append(iVar.b());
            sb.append(";");
            sb.append(iVar.a());
            Log.d("OpenCameraSource", sb.toString());
            if (iVar == null) {
                throw new RuntimeException("Could not find suitable preview/picture size pair.");
            }
            Size b2 = iVar.b();
            this.g = iVar.a();
            if (b2 == null) {
                if (this.i == -1 || this.j == -1) {
                    i3 = this.mRequestedPreviewWidth;
                    i2 = this.mRequestedPreviewHeight;
                } else {
                    i3 = this.i;
                    i2 = this.j;
                }
                b2 = a(open, i3, i2);
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("pictureSize:");
            sb2.append(b2);
            Log.d("OpenCameraSource", sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            sb3.append("mPreviewSize:");
            sb3.append(this.g);
            Log.d("OpenCameraSource", sb3.toString());
            int[] a3 = a(open, this.h);
            if (a3 == null) {
                throw new RuntimeException("Could not find suitable preview frames per second range.");
            }
            Parameters parameters = open.getParameters();
            f();
            if (b2 != null) {
                parameters.setPictureSize(b2.getWidth(), b2.getHeight());
            }
            parameters.setPreviewSize(this.g.getWidth(), this.g.getHeight());
            parameters.setPreviewFpsRange(a3[0], a3[1]);
            parameters.setPreviewFormat(17);
            a(open, parameters, a2);
            b(parameters, this.k);
            this.k = parameters.getFocusMode();
            c(parameters, this.m);
            this.m = parameters.getFlashMode();
            open.setParameters(parameters);
            if (this.s) {
                if (open.getParameters().getSupportedVideoSizes() != null) {
                    size = a(open, this.mRequestedPreviewWidth, this.mRequestedPreviewHeight, this.mRequestedPreviewWidth, this.mRequestedPreviewHeight, open.getParameters().getSupportedVideoSizes()).b();
                } else {
                    size = null;
                }
                WindowManager windowManager = (WindowManager) this.b.getSystemService("window");
                windowManager.getDefaultDisplay().getMetrics(new DisplayMetrics());
                a(open, size);
            }
            open.setPreviewCallback(new e(this, null));
            return open;
        } catch (RuntimeException unused) {
            throw new c("Could not open requested camera.");
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(FrameCallback frameCallback) {
        this.v = frameCallback;
    }

    public void autoFocus(AutoFocusCallback autoFocusCallback) {
        synchronized (this.c) {
            if (this.d != null) {
                a aVar = null;
                if (autoFocusCallback != null) {
                    a aVar2 = new a(this, null);
                    aVar2.b = autoFocusCallback;
                    aVar = aVar2;
                }
                try {
                    this.d.autoFocus(aVar);
                } catch (Exception e2) {
                    Log.e("OpenCameraSource", "Handled exception: Auto focus failed", e2);
                    autoFocusCallback.onAutoFocus(false);
                }
            }
        }
    }

    public void autoFocusOnce(List<Area> list, AutoFocusCallback autoFocusCallback) {
        a(list, autoFocusCallback, true);
    }

    public void cancelAutoFocus() {
        synchronized (this.c) {
            if (this.d != null) {
                this.d.cancelAutoFocus();
            }
        }
    }

    public Size getPreviewSize() {
        return this.g;
    }

    public boolean isRecording() {
        return this.y;
    }

    public void notifyCancelRecording() {
        synchronized (this.c) {
            this.z.onVideoCanceled();
        }
    }

    public void notifyFinishRecording(boolean z2) {
        synchronized (this.c) {
            this.z.onVideoCaptured(z2, this.x);
        }
    }

    public void onInfo(MediaRecorder mediaRecorder, int i2, int i3) {
        synchronized (this.c) {
            if (i2 == 800) {
                try {
                    if (this.z != null) {
                        stopVideo();
                        this.z.onVideoTimeoutExceeded();
                    }
                } finally {
                }
            }
        }
    }

    public void release() {
        synchronized (this.c) {
            stop();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0063, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setFocusMode(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.c
            monitor-enter(r0)
            android.hardware.Camera r1 = r6.d     // Catch:{ all -> 0x0064 }
            r2 = 0
            if (r1 == 0) goto L_0x0062
            if (r7 == 0) goto L_0x0062
            android.hardware.Camera r1 = r6.d     // Catch:{ RuntimeException -> 0x0060 }
            android.hardware.Camera$Parameters r1 = r1.getParameters()     // Catch:{ RuntimeException -> 0x0060 }
            java.lang.String r3 = "OpenCameraSource"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0064 }
            r4.<init>()     // Catch:{ all -> 0x0064 }
            java.lang.String r5 = "setFocusMode:"
            r4.append(r5)     // Catch:{ all -> 0x0064 }
            java.lang.String r5 = r6.k     // Catch:{ all -> 0x0064 }
            r4.append(r5)     // Catch:{ all -> 0x0064 }
            java.lang.String r5 = " mPreviousFocusMode:"
            r4.append(r5)     // Catch:{ all -> 0x0064 }
            java.lang.String r5 = r6.l     // Catch:{ all -> 0x0064 }
            r4.append(r5)     // Catch:{ all -> 0x0064 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0064 }
            android.util.Log.d(r3, r4)     // Catch:{ all -> 0x0064 }
            boolean r7 = b(r1, r7)     // Catch:{ all -> 0x0064 }
            if (r7 == 0) goto L_0x0062
            r6.a(r1)     // Catch:{ all -> 0x0064 }
            java.lang.String r7 = "OpenCameraSource"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0064 }
            r1.<init>()     // Catch:{ all -> 0x0064 }
            java.lang.String r2 = "after setting setFocusMode:"
            r1.append(r2)     // Catch:{ all -> 0x0064 }
            java.lang.String r2 = r6.k     // Catch:{ all -> 0x0064 }
            r1.append(r2)     // Catch:{ all -> 0x0064 }
            java.lang.String r2 = " mPreviousFocusMode:"
            r1.append(r2)     // Catch:{ all -> 0x0064 }
            java.lang.String r2 = r6.l     // Catch:{ all -> 0x0064 }
            r1.append(r2)     // Catch:{ all -> 0x0064 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0064 }
            android.util.Log.d(r7, r1)     // Catch:{ all -> 0x0064 }
            r7 = 1
            monitor-exit(r0)     // Catch:{ all -> 0x0064 }
            return r7
        L_0x0060:
            monitor-exit(r0)     // Catch:{ all -> 0x0064 }
            return r2
        L_0x0062:
            monitor-exit(r0)     // Catch:{ all -> 0x0064 }
            return r2
        L_0x0064:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0064 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.face.CameraSource.setFocusMode(java.lang.String):boolean");
    }

    public boolean setParameters(SetParametersCallback setParametersCallback) {
        synchronized (this.c) {
            if (this.d != null) {
                Parameters call = setParametersCallback.call(this.d.getParameters(), this.d);
                if (call != null) {
                    a(call);
                    return true;
                }
            }
            return false;
        }
    }

    public CameraSource start(SurfaceHolder surfaceHolder) {
        synchronized (this.c) {
            if (this.d != null) {
                this.d.setPreviewDisplay(surfaceHolder);
                a();
                return this;
            }
            this.d = g();
            this.d.setPreviewDisplay(surfaceHolder);
            a();
            return this;
        }
    }

    public void startVideo(MediaCaptureCallback mediaCaptureCallback) {
        try {
            synchronized (this.c) {
                if (this.d != null) {
                    this.d.unlock();
                    this.x = a(this.b.getFilesDir()).getAbsolutePath();
                    this.w.setOutputFile(this.x);
                    this.w.prepare();
                    this.w.start();
                    this.z = mediaCaptureCallback;
                    this.y = true;
                }
            }
        } catch (RuntimeException unused) {
            throw new j();
        } catch (IOException unused2) {
            throw new j();
        }
    }

    public void stop() {
        synchronized (this.c) {
            if (this.d != null) {
                b();
                this.d.setPreviewCallbackWithBuffer(null);
                try {
                    if (VERSION.SDK_INT >= 11) {
                        this.d.setPreviewTexture(null);
                    } else {
                        this.d.setPreviewDisplay(null);
                    }
                } catch (Exception e2) {
                    String str = "OpenCameraSource";
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to clear camera preview: ");
                    sb.append(e2);
                    Log.e(str, sb.toString());
                }
                this.d.release();
                this.d = null;
            }
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x000e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void stopVideo() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.c
            monitor-enter(r0)
            r1 = 0
            android.media.MediaRecorder r2 = r3.w     // Catch:{ RuntimeException -> 0x000e, all -> 0x0011 }
            r2.stop()     // Catch:{ RuntimeException -> 0x000e, all -> 0x0011 }
            android.media.MediaRecorder r2 = r3.w     // Catch:{ RuntimeException -> 0x000e, all -> 0x0011 }
            r2.release()     // Catch:{ RuntimeException -> 0x000e, all -> 0x0011 }
        L_0x000e:
            r3.y = r1     // Catch:{ all -> 0x0017 }
            goto L_0x0015
        L_0x0011:
            r2 = move-exception
            r3.y = r1     // Catch:{ all -> 0x0017 }
            throw r2     // Catch:{ all -> 0x0017 }
        L_0x0015:
            monitor-exit(r0)     // Catch:{ all -> 0x0017 }
            return
        L_0x0017:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0017 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.face.CameraSource.stopVideo():void");
    }

    public void takePicture(ShutterCallback shutterCallback, PictureCallback pictureCallback, boolean z2) {
        try {
            synchronized (this.c) {
                if (this.d != null) {
                    this.n = false;
                    h hVar = new h(this, null);
                    hVar.b = shutterCallback;
                    g gVar = new g(this, null);
                    gVar.b = pictureCallback;
                    this.d.takePicture(hVar, null, null, gVar);
                }
            }
        } catch (RuntimeException unused) {
            throw new j();
        }
    }
}
