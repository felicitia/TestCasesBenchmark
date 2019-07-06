package io.reactivex.schedulers;

import io.reactivex.Scheduler;
import io.reactivex.internal.schedulers.ComputationScheduler;
import io.reactivex.internal.schedulers.IoScheduler;
import io.reactivex.internal.schedulers.NewThreadScheduler;
import io.reactivex.internal.schedulers.SingleScheduler;
import io.reactivex.internal.schedulers.TrampolineScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;

public final class Schedulers {
    static final Scheduler COMPUTATION = RxJavaPlugins.initComputationScheduler(new Callable<Scheduler>() {
        public Scheduler call() throws Exception {
            return ComputationHolder.DEFAULT;
        }
    });
    static final Scheduler IO = RxJavaPlugins.initIoScheduler(new Callable<Scheduler>() {
        public Scheduler call() throws Exception {
            return IoHolder.DEFAULT;
        }
    });
    static final Scheduler NEW_THREAD = RxJavaPlugins.initNewThreadScheduler(new Callable<Scheduler>() {
        public Scheduler call() throws Exception {
            return NewThreadHolder.DEFAULT;
        }
    });
    static final Scheduler SINGLE = RxJavaPlugins.initSingleScheduler(new Callable<Scheduler>() {
        public Scheduler call() throws Exception {
            return SingleHolder.DEFAULT;
        }
    });
    static final Scheduler TRAMPOLINE = TrampolineScheduler.instance();

    static final class ComputationHolder {
        static final Scheduler DEFAULT = new ComputationScheduler();
    }

    static final class IoHolder {
        static final Scheduler DEFAULT = new IoScheduler();
    }

    static final class NewThreadHolder {
        static final Scheduler DEFAULT = NewThreadScheduler.instance();
    }

    static final class SingleHolder {
        static final Scheduler DEFAULT = new SingleScheduler();
    }

    public static Scheduler io() {
        return RxJavaPlugins.onIoScheduler(IO);
    }
}
