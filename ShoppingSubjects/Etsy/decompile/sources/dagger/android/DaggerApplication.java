package dagger.android;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import com.google.errorprone.annotations.ForOverride;

public abstract class DaggerApplication extends Application implements d, e, f, g, h {
    DispatchingAndroidInjector<Activity> activityInjector;
    DispatchingAndroidInjector<BroadcastReceiver> broadcastReceiverInjector;
    DispatchingAndroidInjector<ContentProvider> contentProviderInjector;
    DispatchingAndroidInjector<Fragment> fragmentInjector;
    private volatile boolean needToInject = true;
    DispatchingAndroidInjector<Service> serviceInjector;

    /* access modifiers changed from: protected */
    @ForOverride
    public abstract b<? extends DaggerApplication> applicationInjector();

    public void onCreate() {
        super.onCreate();
        injectIfNecessary();
    }

    private void injectIfNecessary() {
        if (this.needToInject) {
            synchronized (this) {
                if (this.needToInject) {
                    applicationInjector().a(this);
                    if (this.needToInject) {
                        throw new IllegalStateException("The AndroidInjector returned from applicationInjector() did not inject the DaggerApplication");
                    }
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void setInjected() {
        this.needToInject = false;
    }

    public DispatchingAndroidInjector<Activity> activityInjector() {
        return this.activityInjector;
    }

    public DispatchingAndroidInjector<Fragment> fragmentInjector() {
        return this.fragmentInjector;
    }

    public DispatchingAndroidInjector<BroadcastReceiver> broadcastReceiverInjector() {
        return this.broadcastReceiverInjector;
    }

    public DispatchingAndroidInjector<Service> serviceInjector() {
        return this.serviceInjector;
    }

    public b<ContentProvider> contentProviderInjector() {
        injectIfNecessary();
        return this.contentProviderInjector;
    }
}
