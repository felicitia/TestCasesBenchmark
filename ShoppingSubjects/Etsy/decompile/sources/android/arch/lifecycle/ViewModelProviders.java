package android.arch.lifecycle;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider.AndroidViewModelFactory;
import android.support.annotation.NonNull;

public class ViewModelProviders {

    @Deprecated
    public static class DefaultFactory extends AndroidViewModelFactory {
        @Deprecated
        public DefaultFactory(@NonNull Application application) {
            super(application);
        }
    }
}
