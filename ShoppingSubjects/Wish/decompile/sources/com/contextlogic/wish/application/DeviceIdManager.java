package com.contextlogic.wish.application;

import android.os.Environment;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.GetAdvertisingIdService;
import com.contextlogic.wish.api.service.standalone.GetAdvertisingIdService.SuccessCallback;
import com.contextlogic.wish.util.FileUtil;
import com.contextlogic.wish.util.PreferenceUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

public class DeviceIdManager {
    private static DeviceIdManager sInstance = new DeviceIdManager();
    private String mDeviceId;
    private ArrayList<DeviceIdCallback> mDeviceIdCallbacks = new ArrayList<>();
    private boolean mDeviceIdWritten = false;

    public static abstract class DeviceIdCallback {
        private boolean mCancelled;

        public abstract void onDeviceIdReceived(String str);

        public void cancel() {
            this.mCancelled = true;
        }

        public boolean isCancelled() {
            return this.mCancelled;
        }
    }

    private DeviceIdManager() {
        initializeDeviceId();
    }

    public static DeviceIdManager getInstance() {
        return sInstance;
    }

    public void unregisterDeviceIdCallback(DeviceIdCallback deviceIdCallback) {
        synchronized (this.mDeviceIdCallbacks) {
            if (deviceIdCallback != null) {
                try {
                    this.mDeviceIdCallbacks.remove(deviceIdCallback);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public void registerDeviceIdCallback(DeviceIdCallback deviceIdCallback) {
        if (deviceIdCallback != null) {
            if (this.mDeviceId != null) {
                deviceIdCallback.onDeviceIdReceived(this.mDeviceId);
            } else {
                synchronized (this.mDeviceIdCallbacks) {
                    this.mDeviceIdCallbacks.add(deviceIdCallback);
                }
            }
        }
    }

    public void setDeviceId(String str) {
        this.mDeviceId = str;
        PreferenceUtil.setString("DeviceUUID", str);
        writeDeviceId(str);
        synchronized (this.mDeviceIdCallbacks) {
            Iterator it = this.mDeviceIdCallbacks.iterator();
            while (it.hasNext()) {
                ((DeviceIdCallback) it.next()).onDeviceIdReceived(this.mDeviceId);
            }
            this.mDeviceIdCallbacks.clear();
        }
    }

    private void initializeDeviceId() {
        String string = PreferenceUtil.getString("DeviceUUID");
        if (string != null) {
            setDeviceId(string);
            return;
        }
        if (string == null) {
            try {
                String readFile = FileUtil.readFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "/Wish/device_data"));
                if (readFile != null) {
                    try {
                        setDeviceId(readFile);
                    } catch (Throwable unused) {
                    }
                }
                string = readFile;
            } catch (Throwable unused2) {
            }
        }
        if (string == null) {
            generateDeviceId();
        }
    }

    private void generateDeviceId() {
        new GetAdvertisingIdService().requestService(new SuccessCallback() {
            public void onSuccess(String str) {
                DeviceIdManager.this.setDeviceId(UUID.nameUUIDFromBytes(str.getBytes()).toString());
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                DeviceIdManager.this.setDeviceId(UUID.randomUUID().toString());
            }
        });
    }

    public void writeDeviceId(final String str) {
        this.mDeviceIdWritten = true;
        new Thread(new Runnable() {
            /* JADX WARNING: type inference failed for: r1v3 */
            /* JADX WARNING: type inference failed for: r2v3, types: [java.io.FileOutputStream] */
            /* JADX WARNING: type inference failed for: r1v6, types: [java.io.OutputStreamWriter] */
            /* JADX WARNING: type inference failed for: r2v4 */
            /* JADX WARNING: type inference failed for: r2v5, types: [java.io.FileOutputStream] */
            /* JADX WARNING: type inference failed for: r1v7, types: [java.io.OutputStreamWriter] */
            /* JADX WARNING: type inference failed for: r2v6 */
            /* JADX WARNING: type inference failed for: r1v8, types: [java.io.FileOutputStream] */
            /* JADX WARNING: type inference failed for: r0v5, types: [java.io.OutputStreamWriter] */
            /* JADX WARNING: type inference failed for: r0v6 */
            /* JADX WARNING: type inference failed for: r2v8, types: [java.io.OutputStream, java.io.FileOutputStream] */
            /* JADX WARNING: type inference failed for: r1v9 */
            /* JADX WARNING: type inference failed for: r1v11 */
            /* JADX WARNING: type inference failed for: r1v13 */
            /* JADX WARNING: type inference failed for: r1v14 */
            /* JADX WARNING: type inference failed for: r1v15 */
            /* JADX WARNING: type inference failed for: r1v16 */
            /* JADX WARNING: type inference failed for: r1v17 */
            /* JADX WARNING: type inference failed for: r1v18 */
            /* JADX WARNING: type inference failed for: r2v9 */
            /* JADX WARNING: type inference failed for: r2v10 */
            /* JADX WARNING: type inference failed for: r2v11 */
            /* JADX WARNING: type inference failed for: r2v12 */
            /* JADX WARNING: type inference failed for: r0v10 */
            /* JADX WARNING: Can't wrap try/catch for region: R(5:19|(0)|(0)|37|38) */
            /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|(1:3)|4|5|(5:6|7|(6:9|10|11|12|13|14)(1:20)|(2:22|23)|(2:26|27))|49|50|(2:52|54)(1:56)) */
            /* JADX WARNING: Code restructure failed: missing block: B:55:?, code lost:
                return;
             */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x005f */
            /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x006b */
            /* JADX WARNING: Multi-variable type inference failed */
            /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
            /* JADX WARNING: Removed duplicated region for block: B:31:0x0057 A[SYNTHETIC, Splitter:B:31:0x0057] */
            /* JADX WARNING: Removed duplicated region for block: B:35:0x005c A[SYNTHETIC, Splitter:B:35:0x005c] */
            /* JADX WARNING: Removed duplicated region for block: B:43:0x0063 A[SYNTHETIC, Splitter:B:43:0x0063] */
            /* JADX WARNING: Removed duplicated region for block: B:47:0x0068 A[SYNTHETIC, Splitter:B:47:0x0068] */
            /* JADX WARNING: Removed duplicated region for block: B:52:0x007c A[Catch:{ Throwable -> 0x007f }] */
            /* JADX WARNING: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
            /* JADX WARNING: Unknown variable types count: 8 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r5 = this;
                    java.io.File r0 = new java.io.File     // Catch:{  }
                    java.io.File r1 = com.contextlogic.wish.util.FileUtil.getExternalDocumentsDirectory()     // Catch:{  }
                    java.lang.String r2 = "/Wish"
                    r0.<init>(r1, r2)     // Catch:{  }
                    boolean r1 = r0.exists()     // Catch:{  }
                    if (r1 != 0) goto L_0x0014
                    r0.mkdirs()     // Catch:{  }
                L_0x0014:
                    java.io.File r0 = new java.io.File     // Catch:{  }
                    java.io.File r1 = com.contextlogic.wish.util.FileUtil.getExternalDocumentsDirectory()     // Catch:{  }
                    java.lang.String r2 = "/Wish/device_data"
                    r0.<init>(r1, r2)     // Catch:{  }
                    r1 = 0
                    boolean r2 = r0.exists()     // Catch:{ Throwable -> 0x0060, all -> 0x0053 }
                    if (r2 != 0) goto L_0x0047
                    r0.createNewFile()     // Catch:{ Throwable -> 0x0060, all -> 0x0053 }
                    java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x0060, all -> 0x0053 }
                    r3 = 0
                    r2.<init>(r0, r3)     // Catch:{ Throwable -> 0x0060, all -> 0x0053 }
                    java.io.OutputStreamWriter r0 = new java.io.OutputStreamWriter     // Catch:{ Throwable -> 0x0061, all -> 0x0045 }
                    r0.<init>(r2)     // Catch:{ Throwable -> 0x0061, all -> 0x0045 }
                    java.lang.String r1 = r3     // Catch:{ Throwable -> 0x0043, all -> 0x003e }
                    r0.write(r1)     // Catch:{ Throwable -> 0x0043, all -> 0x003e }
                    r0.flush()     // Catch:{ Throwable -> 0x0043, all -> 0x003e }
                    r1 = r2
                    goto L_0x0048
                L_0x003e:
                    r1 = move-exception
                    r4 = r1
                    r1 = r0
                    r0 = r4
                    goto L_0x0055
                L_0x0043:
                    r1 = r0
                    goto L_0x0061
                L_0x0045:
                    r0 = move-exception
                    goto L_0x0055
                L_0x0047:
                    r0 = r1
                L_0x0048:
                    if (r0 == 0) goto L_0x004d
                    r0.close()     // Catch:{ IOException -> 0x004d }
                L_0x004d:
                    if (r1 == 0) goto L_0x006b
                    r1.close()     // Catch:{ Throwable -> 0x006b }
                    goto L_0x006b
                L_0x0053:
                    r0 = move-exception
                    r2 = r1
                L_0x0055:
                    if (r1 == 0) goto L_0x005a
                    r1.close()     // Catch:{ IOException -> 0x005a }
                L_0x005a:
                    if (r2 == 0) goto L_0x005f
                    r2.close()     // Catch:{ IOException -> 0x005f }
                L_0x005f:
                    throw r0     // Catch:{  }
                L_0x0060:
                    r2 = r1
                L_0x0061:
                    if (r1 == 0) goto L_0x0066
                    r1.close()     // Catch:{ IOException -> 0x0066 }
                L_0x0066:
                    if (r2 == 0) goto L_0x006b
                    r2.close()     // Catch:{ Throwable -> 0x006b }
                L_0x006b:
                    java.io.File r0 = new java.io.File     // Catch:{ Throwable -> 0x007f }
                    java.io.File r1 = com.contextlogic.wish.util.FileUtil.getExternalDocumentsDirectory()     // Catch:{ Throwable -> 0x007f }
                    java.lang.String r2 = "/Wish/.nomedia"
                    r0.<init>(r1, r2)     // Catch:{ Throwable -> 0x007f }
                    boolean r1 = r0.exists()     // Catch:{ Throwable -> 0x007f }
                    if (r1 != 0) goto L_0x007f
                    r0.createNewFile()     // Catch:{ Throwable -> 0x007f }
                L_0x007f:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.application.DeviceIdManager.AnonymousClass3.run():void");
            }
        }).start();
    }
}
