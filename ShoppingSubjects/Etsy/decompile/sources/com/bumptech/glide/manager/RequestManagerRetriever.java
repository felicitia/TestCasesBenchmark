package com.bumptech.glide.manager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.bumptech.glide.h;
import java.util.HashMap;
import java.util.Map;

public class RequestManagerRetriever implements Callback {
    static final String FRAGMENT_TAG = "com.bumptech.glide.manager";
    private static final int ID_REMOVE_FRAGMENT_MANAGER = 1;
    private static final int ID_REMOVE_SUPPORT_FRAGMENT_MANAGER = 2;
    private static final RequestManagerRetriever INSTANCE = new RequestManagerRetriever();
    private static final String TAG = "RMRetriever";
    private volatile h applicationManager;
    private final Handler handler = new Handler(Looper.getMainLooper(), this);
    final Map<FragmentManager, RequestManagerFragment> pendingRequestManagerFragments = new HashMap();
    final Map<android.support.v4.app.FragmentManager, SupportRequestManagerFragment> pendingSupportRequestManagerFragments = new HashMap();

    public static RequestManagerRetriever get() {
        return INSTANCE;
    }

    RequestManagerRetriever() {
    }

    private h getApplicationManager(Context context) {
        if (this.applicationManager == null) {
            synchronized (this) {
                if (this.applicationManager == null) {
                    this.applicationManager = new h(context.getApplicationContext(), new b(), new f());
                }
            }
        }
        return this.applicationManager;
    }

    public h get(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("You cannot start a load on a null Context");
        }
        if (com.bumptech.glide.g.h.b() && !(context instanceof Application)) {
            if (context instanceof FragmentActivity) {
                return get((FragmentActivity) context);
            }
            if (context instanceof Activity) {
                return get((Activity) context);
            }
            if (context instanceof ContextWrapper) {
                return get(((ContextWrapper) context).getBaseContext());
            }
        }
        return getApplicationManager(context);
    }

    public h get(FragmentActivity fragmentActivity) {
        if (com.bumptech.glide.g.h.c()) {
            return get(fragmentActivity.getApplicationContext());
        }
        assertNotDestroyed(fragmentActivity);
        return supportFragmentGet(fragmentActivity, fragmentActivity.getSupportFragmentManager());
    }

    public h get(Fragment fragment) {
        if (fragment.getActivity() == null) {
            throw new IllegalArgumentException("You cannot start a load on a fragment before it is attached");
        } else if (com.bumptech.glide.g.h.c()) {
            return get(fragment.getActivity().getApplicationContext());
        } else {
            return supportFragmentGet(fragment.getActivity(), fragment.getChildFragmentManager());
        }
    }

    @TargetApi(11)
    public h get(Activity activity) {
        if (com.bumptech.glide.g.h.c() || VERSION.SDK_INT < 11) {
            return get(activity.getApplicationContext());
        }
        assertNotDestroyed(activity);
        return fragmentGet(activity, activity.getFragmentManager());
    }

    @TargetApi(17)
    private static void assertNotDestroyed(Activity activity) {
        if (VERSION.SDK_INT >= 17 && activity.isDestroyed()) {
            throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
        }
    }

    @TargetApi(17)
    public h get(android.app.Fragment fragment) {
        if (fragment.getActivity() == null) {
            throw new IllegalArgumentException("You cannot start a load on a fragment before it is attached");
        } else if (com.bumptech.glide.g.h.c() || VERSION.SDK_INT < 17) {
            return get(fragment.getActivity().getApplicationContext());
        } else {
            return fragmentGet(fragment.getActivity(), fragment.getChildFragmentManager());
        }
    }

    /* access modifiers changed from: 0000 */
    @TargetApi(17)
    public RequestManagerFragment getRequestManagerFragment(FragmentManager fragmentManager) {
        RequestManagerFragment requestManagerFragment = (RequestManagerFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (requestManagerFragment != null) {
            return requestManagerFragment;
        }
        RequestManagerFragment requestManagerFragment2 = (RequestManagerFragment) this.pendingRequestManagerFragments.get(fragmentManager);
        if (requestManagerFragment2 != null) {
            return requestManagerFragment2;
        }
        RequestManagerFragment requestManagerFragment3 = new RequestManagerFragment();
        this.pendingRequestManagerFragments.put(fragmentManager, requestManagerFragment3);
        fragmentManager.beginTransaction().add(requestManagerFragment3, FRAGMENT_TAG).commitAllowingStateLoss();
        this.handler.obtainMessage(1, fragmentManager).sendToTarget();
        return requestManagerFragment3;
    }

    /* access modifiers changed from: 0000 */
    @TargetApi(11)
    public h fragmentGet(Context context, FragmentManager fragmentManager) {
        RequestManagerFragment requestManagerFragment = getRequestManagerFragment(fragmentManager);
        h requestManager = requestManagerFragment.getRequestManager();
        if (requestManager != null) {
            return requestManager;
        }
        h hVar = new h(context, requestManagerFragment.getLifecycle(), requestManagerFragment.getRequestManagerTreeNode());
        requestManagerFragment.setRequestManager(hVar);
        return hVar;
    }

    /* access modifiers changed from: 0000 */
    public SupportRequestManagerFragment getSupportRequestManagerFragment(android.support.v4.app.FragmentManager fragmentManager) {
        SupportRequestManagerFragment supportRequestManagerFragment = (SupportRequestManagerFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (supportRequestManagerFragment != null) {
            return supportRequestManagerFragment;
        }
        SupportRequestManagerFragment supportRequestManagerFragment2 = (SupportRequestManagerFragment) this.pendingSupportRequestManagerFragments.get(fragmentManager);
        if (supportRequestManagerFragment2 != null) {
            return supportRequestManagerFragment2;
        }
        SupportRequestManagerFragment supportRequestManagerFragment3 = new SupportRequestManagerFragment();
        this.pendingSupportRequestManagerFragments.put(fragmentManager, supportRequestManagerFragment3);
        fragmentManager.beginTransaction().add((Fragment) supportRequestManagerFragment3, FRAGMENT_TAG).commitAllowingStateLoss();
        this.handler.obtainMessage(2, fragmentManager).sendToTarget();
        return supportRequestManagerFragment3;
    }

    /* access modifiers changed from: 0000 */
    public h supportFragmentGet(Context context, android.support.v4.app.FragmentManager fragmentManager) {
        SupportRequestManagerFragment supportRequestManagerFragment = getSupportRequestManagerFragment(fragmentManager);
        h requestManager = supportRequestManagerFragment.getRequestManager();
        if (requestManager != null) {
            return requestManager;
        }
        h hVar = new h(context, supportRequestManagerFragment.getLifecycle(), supportRequestManagerFragment.getRequestManagerTreeNode());
        supportRequestManagerFragment.setRequestManager(hVar);
        return hVar;
    }

    public boolean handleMessage(Message message) {
        Object obj;
        Object obj2 = null;
        boolean z = true;
        switch (message.what) {
            case 1:
                obj2 = (FragmentManager) message.obj;
                obj = this.pendingRequestManagerFragments.remove(obj2);
                break;
            case 2:
                obj2 = (android.support.v4.app.FragmentManager) message.obj;
                obj = this.pendingSupportRequestManagerFragments.remove(obj2);
                break;
            default:
                z = false;
                obj = null;
                break;
        }
        if (z && obj == null && Log.isLoggable(TAG, 5)) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to remove expected request manager fragment, manager: ");
            sb.append(obj2);
            Log.w(str, sb.toString());
        }
        return z;
    }
}
