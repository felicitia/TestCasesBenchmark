package com.google.android.exoplayer2.drm;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.drm.DefaultDrmSession.ProvisioningManager;
import com.google.android.exoplayer2.drm.DrmInitData.SchemeData;
import com.google.android.exoplayer2.drm.DrmSession.DrmSessionException;
import com.google.android.exoplayer2.drm.ExoMediaCrypto;
import com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@TargetApi(18)
public class DefaultDrmSessionManager<T extends ExoMediaCrypto> implements ProvisioningManager<T>, DrmSessionManager<T> {
    private final MediaDrmCallback callback;
    private final Handler eventHandler;
    /* access modifiers changed from: private */
    public final EventListener eventListener;
    private final int initialDrmRequestRetryCount;
    private final ExoMediaDrm<T> mediaDrm;
    volatile MediaDrmHandler mediaDrmHandler;
    private int mode;
    private final boolean multiSession;
    private byte[] offlineLicenseKeySetId;
    private final HashMap<String, String> optionalKeyRequestParameters;
    private Looper playbackLooper;
    private final List<DefaultDrmSession<T>> provisioningSessions;
    /* access modifiers changed from: private */
    public final List<DefaultDrmSession<T>> sessions;
    private final UUID uuid;

    public interface EventListener {
        void onDrmKeysLoaded();

        void onDrmKeysRemoved();

        void onDrmKeysRestored();

        void onDrmSessionManagerError(Exception exc);
    }

    @SuppressLint({"HandlerLeak"})
    private class MediaDrmHandler extends Handler {
        public MediaDrmHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            byte[] bArr = (byte[]) message.obj;
            for (DefaultDrmSession defaultDrmSession : DefaultDrmSessionManager.this.sessions) {
                if (defaultDrmSession.hasSessionId(bArr)) {
                    defaultDrmSession.onMediaDrmEvent(message.what);
                    return;
                }
            }
        }
    }

    public static final class MissingSchemeDataException extends Exception {
        private MissingSchemeDataException(UUID uuid) {
            StringBuilder sb = new StringBuilder();
            sb.append("Media does not support uuid: ");
            sb.append(uuid);
            super(sb.toString());
        }
    }

    public boolean canAcquireSession(DrmInitData drmInitData) {
        boolean z = true;
        if (this.offlineLicenseKeySetId != null) {
            return true;
        }
        if (getSchemeData(drmInitData, this.uuid, true) == null) {
            if (drmInitData.schemeDataCount != 1 || !drmInitData.get(0).matches(C.COMMON_PSSH_UUID)) {
                return false;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("DrmInitData only contains common PSSH SchemeData. Assuming support for: ");
            sb.append(this.uuid);
            Log.w("DefaultDrmSessionMgr", sb.toString());
        }
        String str = drmInitData.schemeType;
        if (str == null || "cenc".equals(str)) {
            return true;
        }
        if (!"cbc1".equals(str) && !"cbcs".equals(str) && !"cens".equals(str)) {
            return true;
        }
        if (Util.SDK_INT < 24) {
            z = false;
        }
        return z;
    }

    public DrmSession<T> acquireSession(Looper looper, DrmInitData drmInitData) {
        String str;
        byte[] bArr;
        DefaultDrmSession defaultDrmSession;
        Looper looper2 = looper;
        Assertions.checkState(this.playbackLooper == null || this.playbackLooper == looper2);
        if (this.sessions.isEmpty()) {
            this.playbackLooper = looper2;
            if (this.mediaDrmHandler == null) {
                this.mediaDrmHandler = new MediaDrmHandler<>(looper2);
            }
        }
        DefaultDrmSession defaultDrmSession2 = null;
        if (this.offlineLicenseKeySetId == null) {
            SchemeData schemeData = getSchemeData(drmInitData, this.uuid, false);
            if (schemeData == null) {
                final MissingSchemeDataException missingSchemeDataException = new MissingSchemeDataException(this.uuid);
                if (!(this.eventHandler == null || this.eventListener == null)) {
                    this.eventHandler.post(new Runnable() {
                        public void run() {
                            DefaultDrmSessionManager.this.eventListener.onDrmSessionManagerError(missingSchemeDataException);
                        }
                    });
                }
                return new ErrorStateDrmSession(new DrmSessionException(missingSchemeDataException));
            }
            byte[] schemeInitData = getSchemeInitData(schemeData, this.uuid);
            str = getSchemeMimeType(schemeData, this.uuid);
            bArr = schemeInitData;
        } else {
            bArr = null;
            str = null;
        }
        if (this.multiSession) {
            Iterator it = this.sessions.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DefaultDrmSession defaultDrmSession3 = (DefaultDrmSession) it.next();
                if (defaultDrmSession3.hasInitData(bArr)) {
                    defaultDrmSession2 = defaultDrmSession3;
                    break;
                }
            }
        } else if (!this.sessions.isEmpty()) {
            defaultDrmSession2 = (DefaultDrmSession) this.sessions.get(0);
        }
        if (defaultDrmSession2 == null) {
            defaultDrmSession = new DefaultDrmSession(this.uuid, this.mediaDrm, this, bArr, str, this.mode, this.offlineLicenseKeySetId, this.optionalKeyRequestParameters, this.callback, looper2, this.eventHandler, this.eventListener, this.initialDrmRequestRetryCount);
            this.sessions.add(defaultDrmSession);
        } else {
            defaultDrmSession = defaultDrmSession2;
        }
        defaultDrmSession.acquire();
        return defaultDrmSession;
    }

    public void releaseSession(DrmSession<T> drmSession) {
        if (!(drmSession instanceof ErrorStateDrmSession)) {
            DefaultDrmSession defaultDrmSession = (DefaultDrmSession) drmSession;
            if (defaultDrmSession.release()) {
                this.sessions.remove(defaultDrmSession);
                if (this.provisioningSessions.size() > 1 && this.provisioningSessions.get(0) == defaultDrmSession) {
                    ((DefaultDrmSession) this.provisioningSessions.get(1)).provision();
                }
                this.provisioningSessions.remove(defaultDrmSession);
            }
        }
    }

    public void provisionRequired(DefaultDrmSession<T> defaultDrmSession) {
        this.provisioningSessions.add(defaultDrmSession);
        if (this.provisioningSessions.size() == 1) {
            defaultDrmSession.provision();
        }
    }

    public void onProvisionCompleted() {
        for (DefaultDrmSession onProvisionCompleted : this.provisioningSessions) {
            onProvisionCompleted.onProvisionCompleted();
        }
        this.provisioningSessions.clear();
    }

    public void onProvisionError(Exception exc) {
        for (DefaultDrmSession onProvisionError : this.provisioningSessions) {
            onProvisionError.onProvisionError(exc);
        }
        this.provisioningSessions.clear();
    }

    private static SchemeData getSchemeData(DrmInitData drmInitData, UUID uuid2, boolean z) {
        ArrayList arrayList = new ArrayList(drmInitData.schemeDataCount);
        int i = 0;
        while (true) {
            boolean z2 = true;
            if (i >= drmInitData.schemeDataCount) {
                break;
            }
            SchemeData schemeData = drmInitData.get(i);
            if (!schemeData.matches(uuid2) && (!C.CLEARKEY_UUID.equals(uuid2) || !schemeData.matches(C.COMMON_PSSH_UUID))) {
                z2 = false;
            }
            if (z2 && (schemeData.data != null || z)) {
                arrayList.add(schemeData);
            }
            i++;
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        if (C.WIDEVINE_UUID.equals(uuid2)) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                SchemeData schemeData2 = (SchemeData) arrayList.get(i2);
                int parseVersion = schemeData2.hasData() ? PsshAtomUtil.parseVersion(schemeData2.data) : -1;
                if (Util.SDK_INT < 23 && parseVersion == 0) {
                    return schemeData2;
                }
                if (Util.SDK_INT >= 23 && parseVersion == 1) {
                    return schemeData2;
                }
            }
        }
        return (SchemeData) arrayList.get(0);
    }

    private static byte[] getSchemeInitData(SchemeData schemeData, UUID uuid2) {
        byte[] bArr = schemeData.data;
        if (Util.SDK_INT >= 21) {
            return bArr;
        }
        byte[] parseSchemeSpecificData = PsshAtomUtil.parseSchemeSpecificData(bArr, uuid2);
        return parseSchemeSpecificData == null ? bArr : parseSchemeSpecificData;
    }

    private static String getSchemeMimeType(SchemeData schemeData, UUID uuid2) {
        String str = schemeData.mimeType;
        if (Util.SDK_INT >= 26 || !C.CLEARKEY_UUID.equals(uuid2)) {
            return str;
        }
        return ("video/mp4".equals(str) || "audio/mp4".equals(str)) ? "cenc" : str;
    }
}
