package dagger.android;

import android.content.ContentProvider;
import android.support.annotation.CallSuper;

public abstract class DaggerContentProvider extends ContentProvider {
    @CallSuper
    public boolean onCreate() {
        a.a((ContentProvider) this);
        return true;
    }
}
