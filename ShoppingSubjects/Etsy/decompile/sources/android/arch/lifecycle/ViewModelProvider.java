package android.arch.lifecycle;

import android.app.Application;
import android.support.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;

public class ViewModelProvider {

    public static class AndroidViewModelFactory extends a {
        private static AndroidViewModelFactory sInstance;
        private Application mApplication;

        public static AndroidViewModelFactory getInstance(@NonNull Application application) {
            if (sInstance == null) {
                sInstance = new AndroidViewModelFactory(application);
            }
            return sInstance;
        }

        public AndroidViewModelFactory(@NonNull Application application) {
            this.mApplication = application;
        }

        @NonNull
        public <T extends k> T create(@NonNull Class<T> cls) {
            if (!AndroidViewModel.class.isAssignableFrom(cls)) {
                return super.create(cls);
            }
            try {
                return (k) cls.getConstructor(new Class[]{Application.class}).newInstance(new Object[]{this.mApplication});
            } catch (NoSuchMethodException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Cannot create an instance of ");
                sb.append(cls);
                throw new RuntimeException(sb.toString(), e);
            } catch (IllegalAccessException e2) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Cannot create an instance of ");
                sb2.append(cls);
                throw new RuntimeException(sb2.toString(), e2);
            } catch (InstantiationException e3) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Cannot create an instance of ");
                sb3.append(cls);
                throw new RuntimeException(sb3.toString(), e3);
            } catch (InvocationTargetException e4) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Cannot create an instance of ");
                sb4.append(cls);
                throw new RuntimeException(sb4.toString(), e4);
            }
        }
    }

    public static class a {
        @NonNull
        public <T extends k> T create(@NonNull Class<T> cls) {
            try {
                return (k) cls.newInstance();
            } catch (InstantiationException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Cannot create an instance of ");
                sb.append(cls);
                throw new RuntimeException(sb.toString(), e);
            } catch (IllegalAccessException e2) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Cannot create an instance of ");
                sb2.append(cls);
                throw new RuntimeException(sb2.toString(), e2);
            }
        }
    }
}
