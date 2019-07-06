package rx_activity_result2;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import io.reactivex.functions.Consumer;
import io.reactivex.q;
import io.reactivex.subjects.PublishSubject;
import java.util.List;

/* compiled from: RxActivityResult */
public final class f {
    static a a;

    /* compiled from: RxActivityResult */
    public static class a<T> {
        final Class a;
        final PublishSubject<e<T>> b = PublishSubject.a();
        private final boolean c;

        public a(T t) {
            if (f.a == null) {
                throw new IllegalStateException("You must call RxActivityResult.register(application) before attempting to use startIntent");
            }
            this.a = t.getClass();
            this.c = t instanceof Activity;
        }

        public q<e<T>> a(Intent intent) {
            return a(intent, (b) null);
        }

        public q<e<T>> a(Intent intent, @Nullable b bVar) {
            return a(new c(intent), bVar);
        }

        private q<e<T>> a(c cVar, @Nullable b bVar) {
            cVar.a(this.c ? a() : b());
            cVar.a(bVar);
            HolderActivity.setRequest(cVar);
            f.a.b().a((Consumer<? super T>) new Consumer<Activity>() {
                /* renamed from: a */
                public void accept(Activity activity) throws Exception {
                    activity.startActivity(new Intent(activity, HolderActivity.class).addFlags(65536));
                }
            });
            return this.b;
        }

        private OnResult a() {
            return new RxActivityResult$Builder$2(this);
        }

        private OnResult b() {
            return new RxActivityResult$Builder$3(this);
        }

        /* access modifiers changed from: 0000 */
        @Nullable
        public Fragment a(List<Fragment> list) {
            if (list == null) {
                return null;
            }
            for (Fragment fragment : list) {
                if (fragment != null && fragment.isVisible() && fragment.getClass() == this.a) {
                    return fragment;
                }
                if (!(fragment == null || !fragment.isAdded() || fragment.getChildFragmentManager() == null)) {
                    Fragment a2 = a(fragment.getChildFragmentManager().getFragments());
                    if (a2 != null) {
                        return a2;
                    }
                }
            }
            return null;
        }
    }

    public static void a(Application application) {
        a = new a(application);
    }

    public static <T extends Activity> a<T> a(T t) {
        return new a<>(t);
    }
}
