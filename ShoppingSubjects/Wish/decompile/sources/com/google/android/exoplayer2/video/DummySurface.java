package com.google.android.exoplayer2.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.Surface;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

@TargetApi(17)
public final class DummySurface extends Surface {
    private static int secureMode;
    private static boolean secureModeInitialized;
    public final boolean secure;
    private final DummySurfaceThread thread;
    private boolean threadReleased;

    private static class DummySurfaceThread extends HandlerThread implements OnFrameAvailableListener, Callback {
        private EGLContext context;
        private EGLDisplay display;
        private Handler handler;
        private Error initError;
        private RuntimeException initException;
        private EGLSurface pbuffer;
        private DummySurface surface;
        private SurfaceTexture surfaceTexture;
        private final int[] textureIdHolder = new int[1];

        public DummySurfaceThread() {
            super("dummySurface");
        }

        public DummySurface init(int i) {
            boolean z;
            start();
            this.handler = new Handler(getLooper(), this);
            synchronized (this) {
                z = false;
                this.handler.obtainMessage(1, i, 0).sendToTarget();
                while (this.surface == null && this.initException == null && this.initError == null) {
                    try {
                        wait();
                    } catch (InterruptedException unused) {
                        z = true;
                    }
                }
            }
            if (z) {
                Thread.currentThread().interrupt();
            }
            if (this.initException != null) {
                throw this.initException;
            } else if (this.initError == null) {
                return this.surface;
            } else {
                throw this.initError;
            }
        }

        public void release() {
            this.handler.sendEmptyMessage(3);
        }

        public void onFrameAvailable(SurfaceTexture surfaceTexture2) {
            this.handler.sendEmptyMessage(2);
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    try {
                        initInternal(message.arg1);
                        synchronized (this) {
                            notify();
                        }
                    } catch (RuntimeException e) {
                        Log.e("DummySurface", "Failed to initialize dummy surface", e);
                        this.initException = e;
                        synchronized (this) {
                            notify();
                        }
                    } catch (Error e2) {
                        try {
                            Log.e("DummySurface", "Failed to initialize dummy surface", e2);
                            this.initError = e2;
                            synchronized (this) {
                                notify();
                            }
                        } catch (Throwable th) {
                            synchronized (this) {
                                notify();
                                throw th;
                            }
                        }
                    }
                    return true;
                case 2:
                    this.surfaceTexture.updateTexImage();
                    return true;
                case 3:
                    try {
                        releaseInternal();
                    } catch (Throwable th2) {
                        quit();
                        throw th2;
                    }
                    quit();
                    return true;
                default:
                    return true;
            }
        }

        private void initInternal(int i) {
            int[] iArr;
            EGLSurface eGLSurface;
            int[] iArr2;
            boolean z = false;
            this.display = EGL14.eglGetDisplay(0);
            Assertions.checkState(this.display != null, "eglGetDisplay failed");
            int[] iArr3 = new int[2];
            Assertions.checkState(EGL14.eglInitialize(this.display, iArr3, 0, iArr3, 1), "eglInitialize failed");
            EGLConfig[] eGLConfigArr = new EGLConfig[1];
            int[] iArr4 = new int[1];
            Assertions.checkState(EGL14.eglChooseConfig(this.display, new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12327, 12344, 12339, 4, 12344}, 0, eGLConfigArr, 0, 1, iArr4, 0) && iArr4[0] > 0 && eGLConfigArr[0] != null, "eglChooseConfig failed");
            EGLConfig eGLConfig = eGLConfigArr[0];
            if (i == 0) {
                iArr = new int[]{12440, 2, 12344};
            } else {
                iArr = new int[]{12440, 2, 12992, 1, 12344};
            }
            this.context = EGL14.eglCreateContext(this.display, eGLConfig, EGL14.EGL_NO_CONTEXT, iArr, 0);
            Assertions.checkState(this.context != null, "eglCreateContext failed");
            if (i == 1) {
                eGLSurface = EGL14.EGL_NO_SURFACE;
            } else {
                if (i == 2) {
                    iArr2 = new int[]{12375, 1, 12374, 1, 12992, 1, 12344};
                } else {
                    iArr2 = new int[]{12375, 1, 12374, 1, 12344};
                }
                this.pbuffer = EGL14.eglCreatePbufferSurface(this.display, eGLConfig, iArr2, 0);
                Assertions.checkState(this.pbuffer != null, "eglCreatePbufferSurface failed");
                eGLSurface = this.pbuffer;
            }
            Assertions.checkState(EGL14.eglMakeCurrent(this.display, eGLSurface, eGLSurface, this.context), "eglMakeCurrent failed");
            GLES20.glGenTextures(1, this.textureIdHolder, 0);
            this.surfaceTexture = new SurfaceTexture(this.textureIdHolder[0]);
            this.surfaceTexture.setOnFrameAvailableListener(this);
            SurfaceTexture surfaceTexture2 = this.surfaceTexture;
            if (i != 0) {
                z = true;
            }
            this.surface = new DummySurface(this, surfaceTexture2, z);
        }

        private void releaseInternal() {
            try {
                if (this.surfaceTexture != null) {
                    this.surfaceTexture.release();
                    GLES20.glDeleteTextures(1, this.textureIdHolder, 0);
                }
            } finally {
                if (this.pbuffer != null) {
                    EGL14.eglDestroySurface(this.display, this.pbuffer);
                }
                if (this.context != null) {
                    EGL14.eglDestroyContext(this.display, this.context);
                }
                this.pbuffer = null;
                this.context = null;
                this.display = null;
                this.surface = null;
                this.surfaceTexture = null;
            }
        }
    }

    public static synchronized boolean isSecureSupported(Context context) {
        boolean z;
        synchronized (DummySurface.class) {
            z = true;
            if (!secureModeInitialized) {
                secureMode = Util.SDK_INT < 24 ? 0 : getSecureModeV24(context);
                secureModeInitialized = true;
            }
            if (secureMode == 0) {
                z = false;
            }
        }
        return z;
    }

    public static DummySurface newInstanceV17(Context context, boolean z) {
        assertApiLevel17OrHigher();
        int i = 0;
        Assertions.checkState(!z || isSecureSupported(context));
        DummySurfaceThread dummySurfaceThread = new DummySurfaceThread();
        if (z) {
            i = secureMode;
        }
        return dummySurfaceThread.init(i);
    }

    private DummySurface(DummySurfaceThread dummySurfaceThread, SurfaceTexture surfaceTexture, boolean z) {
        super(surfaceTexture);
        this.thread = dummySurfaceThread;
        this.secure = z;
    }

    public void release() {
        super.release();
        synchronized (this.thread) {
            if (!this.threadReleased) {
                this.thread.release();
                this.threadReleased = true;
            }
        }
    }

    private static void assertApiLevel17OrHigher() {
        if (Util.SDK_INT < 17) {
            throw new UnsupportedOperationException("Unsupported prior to API level 17");
        }
    }

    @TargetApi(24)
    private static int getSecureModeV24(Context context) {
        if (Util.SDK_INT < 26 && ("samsung".equals(Util.MANUFACTURER) || "XT1650".equals(Util.MODEL))) {
            return 0;
        }
        if (Util.SDK_INT < 26 && !context.getPackageManager().hasSystemFeature("android.hardware.vr.high_performance")) {
            return 0;
        }
        String eglQueryString = EGL14.eglQueryString(EGL14.eglGetDisplay(0), 12373);
        if (eglQueryString == null || !eglQueryString.contains("EGL_EXT_protected_content")) {
            return 0;
        }
        return eglQueryString.contains("EGL_KHR_surfaceless_context") ? 1 : 2;
    }
}
