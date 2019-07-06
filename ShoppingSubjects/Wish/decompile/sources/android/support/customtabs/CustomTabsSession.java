package android.support.customtabs;

import android.content.ComponentName;
import android.os.IBinder;

public final class CustomTabsSession {
    private final ICustomTabsCallback mCallback;
    private final ComponentName mComponentName;

    /* access modifiers changed from: 0000 */
    public IBinder getBinder() {
        return this.mCallback.asBinder();
    }

    /* access modifiers changed from: 0000 */
    public ComponentName getComponentName() {
        return this.mComponentName;
    }
}
