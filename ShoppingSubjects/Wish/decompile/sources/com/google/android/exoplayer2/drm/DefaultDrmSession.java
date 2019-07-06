package com.google.android.exoplayer2.drm;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.NotProvisionedException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.drm.DefaultDrmSessionManager.EventListener;
import com.google.android.exoplayer2.drm.DrmSession.DrmSessionException;
import com.google.android.exoplayer2.drm.ExoMediaCrypto;
import com.google.android.exoplayer2.drm.ExoMediaDrm.DefaultKeyRequest;
import com.google.android.exoplayer2.drm.ExoMediaDrm.KeyRequest;
import com.google.android.exoplayer2.drm.ExoMediaDrm.ProvisionRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@TargetApi(18)
class DefaultDrmSession<T extends ExoMediaCrypto> implements DrmSession<T> {
    final MediaDrmCallback callback;
    private final Handler eventHandler;
    /* access modifiers changed from: private */
    public final EventListener eventListener;
    private final byte[] initData;
    /* access modifiers changed from: private */
    public final int initialDrmRequestRetryCount;
    private DrmSessionException lastException;
    private T mediaCrypto;
    private final ExoMediaDrm<T> mediaDrm;
    private final String mimeType;
    private final int mode;
    private byte[] offlineLicenseKeySetId;
    private int openCount;
    private final HashMap<String, String> optionalKeyRequestParameters;
    private PostRequestHandler postRequestHandler;
    final PostResponseHandler postResponseHandler;
    private final ProvisioningManager<T> provisioningManager;
    private HandlerThread requestHandlerThread;
    private byte[] sessionId;
    private int state = 2;
    final UUID uuid;

    @SuppressLint({"HandlerLeak"})
    private class PostRequestHandler extends Handler {
        public PostRequestHandler(Looper looper) {
            super(looper);
        }

        /* access modifiers changed from: 0000 */
        public Message obtainMessage(int i, Object obj, boolean z) {
            return obtainMessage(i, z ? 1 : 0, 0, obj);
        }

        public void handleMessage(Message message) {
            try {
                switch (message.what) {
                    case 0:
                        e = DefaultDrmSession.this.callback.executeProvisionRequest(DefaultDrmSession.this.uuid, (ProvisionRequest) message.obj);
                        break;
                    case 1:
                        e = DefaultDrmSession.this.callback.executeKeyRequest(DefaultDrmSession.this.uuid, (KeyRequest) message.obj);
                        break;
                    default:
                        throw new RuntimeException();
                }
            } catch (Exception e) {
                e = e;
                if (maybeRetryRequest(message)) {
                    return;
                }
            }
            DefaultDrmSession.this.postResponseHandler.obtainMessage(message.what, e).sendToTarget();
        }

        private boolean maybeRetryRequest(Message message) {
            if (!(message.arg1 == 1)) {
                return false;
            }
            int i = message.arg2 + 1;
            if (i > DefaultDrmSession.this.initialDrmRequestRetryCount) {
                return false;
            }
            Message obtain = Message.obtain(message);
            obtain.arg2 = i;
            sendMessageDelayed(obtain, getRetryDelayMillis(i));
            return true;
        }

        private long getRetryDelayMillis(int i) {
            return (long) Math.min((i - 1) * 1000, 5000);
        }
    }

    @SuppressLint({"HandlerLeak"})
    private class PostResponseHandler extends Handler {
        public PostResponseHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    DefaultDrmSession.this.onProvisionResponse(message.obj);
                    return;
                case 1:
                    DefaultDrmSession.this.onKeyResponse(message.obj);
                    return;
                default:
                    return;
            }
        }
    }

    public interface ProvisioningManager<T extends ExoMediaCrypto> {
        void onProvisionCompleted();

        void onProvisionError(Exception exc);

        void provisionRequired(DefaultDrmSession<T> defaultDrmSession);
    }

    public DefaultDrmSession(UUID uuid2, ExoMediaDrm<T> exoMediaDrm, ProvisioningManager<T> provisioningManager2, byte[] bArr, String str, int i, byte[] bArr2, HashMap<String, String> hashMap, MediaDrmCallback mediaDrmCallback, Looper looper, Handler handler, EventListener eventListener2, int i2) {
        this.uuid = uuid2;
        this.provisioningManager = provisioningManager2;
        this.mediaDrm = exoMediaDrm;
        this.mode = i;
        this.offlineLicenseKeySetId = bArr2;
        this.optionalKeyRequestParameters = hashMap;
        this.callback = mediaDrmCallback;
        this.initialDrmRequestRetryCount = i2;
        this.eventHandler = handler;
        this.eventListener = eventListener2;
        this.postResponseHandler = new PostResponseHandler<>(looper);
        this.requestHandlerThread = new HandlerThread("DrmRequestHandler");
        this.requestHandlerThread.start();
        this.postRequestHandler = new PostRequestHandler<>(this.requestHandlerThread.getLooper());
        if (bArr2 == null) {
            this.initData = bArr;
            this.mimeType = str;
            return;
        }
        this.initData = null;
        this.mimeType = null;
    }

    public void acquire() {
        int i = this.openCount + 1;
        this.openCount = i;
        if (i == 1 && this.state != 1 && openInternal(true)) {
            doLicense(true);
        }
    }

    public boolean release() {
        int i = this.openCount - 1;
        this.openCount = i;
        if (i != 0) {
            return false;
        }
        this.state = 0;
        this.postResponseHandler.removeCallbacksAndMessages(null);
        this.postRequestHandler.removeCallbacksAndMessages(null);
        this.postRequestHandler = null;
        this.requestHandlerThread.quit();
        this.requestHandlerThread = null;
        this.mediaCrypto = null;
        this.lastException = null;
        if (this.sessionId != null) {
            this.mediaDrm.closeSession(this.sessionId);
            this.sessionId = null;
        }
        return true;
    }

    public boolean hasInitData(byte[] bArr) {
        return Arrays.equals(this.initData, bArr);
    }

    public boolean hasSessionId(byte[] bArr) {
        return Arrays.equals(this.sessionId, bArr);
    }

    public void provision() {
        this.postRequestHandler.obtainMessage(0, this.mediaDrm.getProvisionRequest(), true).sendToTarget();
    }

    public void onProvisionCompleted() {
        if (openInternal(false)) {
            doLicense(true);
        }
    }

    public void onProvisionError(Exception exc) {
        onError(exc);
    }

    public final int getState() {
        return this.state;
    }

    public final DrmSessionException getError() {
        if (this.state == 1) {
            return this.lastException;
        }
        return null;
    }

    public final T getMediaCrypto() {
        return this.mediaCrypto;
    }

    public Map<String, String> queryKeyStatus() {
        if (this.sessionId == null) {
            return null;
        }
        return this.mediaDrm.queryKeyStatus(this.sessionId);
    }

    private boolean openInternal(boolean z) {
        if (isOpen()) {
            return true;
        }
        try {
            this.sessionId = this.mediaDrm.openSession();
            this.mediaCrypto = this.mediaDrm.createMediaCrypto(this.sessionId);
            this.state = 3;
            return true;
        } catch (NotProvisionedException e) {
            if (z) {
                this.provisioningManager.provisionRequired(this);
            } else {
                onError(e);
            }
            return false;
        } catch (Exception e2) {
            onError(e2);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void onProvisionResponse(Object obj) {
        if (this.state != 2 && !isOpen()) {
            return;
        }
        if (obj instanceof Exception) {
            this.provisioningManager.onProvisionError((Exception) obj);
            return;
        }
        try {
            this.mediaDrm.provideProvisionResponse((byte[]) obj);
            this.provisioningManager.onProvisionCompleted();
        } catch (Exception e) {
            this.provisioningManager.onProvisionError(e);
        }
    }

    private void doLicense(boolean z) {
        switch (this.mode) {
            case 0:
            case 1:
                if (this.offlineLicenseKeySetId == null) {
                    postKeyRequest(1, z);
                    return;
                } else if (this.state == 4 || restoreKeys()) {
                    long licenseDurationRemainingSec = getLicenseDurationRemainingSec();
                    if (this.mode == 0 && licenseDurationRemainingSec <= 60) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Offline license has expired or will expire soon. Remaining seconds: ");
                        sb.append(licenseDurationRemainingSec);
                        Log.d("DefaultDrmSession", sb.toString());
                        postKeyRequest(2, z);
                        return;
                    } else if (licenseDurationRemainingSec <= 0) {
                        onError(new KeysExpiredException());
                        return;
                    } else {
                        this.state = 4;
                        if (this.eventHandler != null && this.eventListener != null) {
                            this.eventHandler.post(new Runnable() {
                                public void run() {
                                    DefaultDrmSession.this.eventListener.onDrmKeysRestored();
                                }
                            });
                            return;
                        }
                        return;
                    }
                } else {
                    return;
                }
            case 2:
                if (this.offlineLicenseKeySetId == null) {
                    postKeyRequest(2, z);
                    return;
                } else if (restoreKeys()) {
                    postKeyRequest(2, z);
                    return;
                } else {
                    return;
                }
            case 3:
                if (restoreKeys()) {
                    postKeyRequest(3, z);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private boolean restoreKeys() {
        try {
            this.mediaDrm.restoreKeys(this.sessionId, this.offlineLicenseKeySetId);
            return true;
        } catch (Exception e) {
            Log.e("DefaultDrmSession", "Error trying to restore Widevine keys.", e);
            onError(e);
            return false;
        }
    }

    private long getLicenseDurationRemainingSec() {
        if (!C.WIDEVINE_UUID.equals(this.uuid)) {
            return Long.MAX_VALUE;
        }
        Pair licenseDurationRemainingSec = WidevineUtil.getLicenseDurationRemainingSec(this);
        return Math.min(((Long) licenseDurationRemainingSec.first).longValue(), ((Long) licenseDurationRemainingSec.second).longValue());
    }

    private void postKeyRequest(int i, boolean z) {
        try {
            KeyRequest keyRequest = this.mediaDrm.getKeyRequest(i == 3 ? this.offlineLicenseKeySetId : this.sessionId, this.initData, this.mimeType, i, this.optionalKeyRequestParameters);
            if (C.CLEARKEY_UUID.equals(this.uuid)) {
                keyRequest = new DefaultKeyRequest(ClearKeyUtil.adjustRequestData(keyRequest.getData()), keyRequest.getDefaultUrl());
            }
            this.postRequestHandler.obtainMessage(1, keyRequest, z).sendToTarget();
        } catch (Exception e) {
            onKeysError(e);
        }
    }

    /* access modifiers changed from: private */
    public void onKeyResponse(Object obj) {
        if (isOpen()) {
            if (obj instanceof Exception) {
                onKeysError((Exception) obj);
                return;
            }
            try {
                byte[] bArr = (byte[]) obj;
                if (C.CLEARKEY_UUID.equals(this.uuid)) {
                    bArr = ClearKeyUtil.adjustResponseData(bArr);
                }
                if (this.mode == 3) {
                    this.mediaDrm.provideKeyResponse(this.offlineLicenseKeySetId, bArr);
                    if (!(this.eventHandler == null || this.eventListener == null)) {
                        this.eventHandler.post(new Runnable() {
                            public void run() {
                                DefaultDrmSession.this.eventListener.onDrmKeysRemoved();
                            }
                        });
                    }
                } else {
                    byte[] provideKeyResponse = this.mediaDrm.provideKeyResponse(this.sessionId, bArr);
                    if (!((this.mode != 2 && (this.mode != 0 || this.offlineLicenseKeySetId == null)) || provideKeyResponse == null || provideKeyResponse.length == 0)) {
                        this.offlineLicenseKeySetId = provideKeyResponse;
                    }
                    this.state = 4;
                    if (!(this.eventHandler == null || this.eventListener == null)) {
                        this.eventHandler.post(new Runnable() {
                            public void run() {
                                DefaultDrmSession.this.eventListener.onDrmKeysLoaded();
                            }
                        });
                    }
                }
            } catch (Exception e) {
                onKeysError(e);
            }
        }
    }

    private void onKeysExpired() {
        if (this.state == 4) {
            this.state = 3;
            onError(new KeysExpiredException());
        }
    }

    private void onKeysError(Exception exc) {
        if (exc instanceof NotProvisionedException) {
            this.provisioningManager.provisionRequired(this);
        } else {
            onError(exc);
        }
    }

    private void onError(final Exception exc) {
        this.lastException = new DrmSessionException(exc);
        if (!(this.eventHandler == null || this.eventListener == null)) {
            this.eventHandler.post(new Runnable() {
                public void run() {
                    DefaultDrmSession.this.eventListener.onDrmSessionManagerError(exc);
                }
            });
        }
        if (this.state != 4) {
            this.state = 1;
        }
    }

    private boolean isOpen() {
        return this.state == 3 || this.state == 4;
    }

    public void onMediaDrmEvent(int i) {
        if (isOpen()) {
            switch (i) {
                case 1:
                    this.state = 3;
                    this.provisioningManager.provisionRequired(this);
                    break;
                case 2:
                    doLicense(false);
                    break;
                case 3:
                    onKeysExpired();
                    break;
            }
        }
    }
}
