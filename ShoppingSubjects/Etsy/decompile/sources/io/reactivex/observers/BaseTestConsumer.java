package io.reactivex.observers;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.BaseTestConsumer;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public abstract class BaseTestConsumer<T, U extends BaseTestConsumer<T, U>> implements Disposable {
    protected final CountDownLatch a;
    protected final List<T> b;
    protected final List<Throwable> c;
    protected long d;
    protected Thread e;
    protected boolean f;
    protected int g;
    protected int h;

    public enum TestWaitStrategy implements Runnable {
        SPIN {
            public void run() {
            }
        },
        YIELD {
            public void run() {
                Thread.yield();
            }
        },
        SLEEP_1MS {
            public void run() {
                sleep(1);
            }
        },
        SLEEP_10MS {
            public void run() {
                sleep(10);
            }
        },
        SLEEP_100MS {
            public void run() {
                sleep(100);
            }
        },
        SLEEP_1000MS {
            public void run() {
                sleep(1000);
            }
        };

        public abstract void run();

        static void sleep(int i) {
            try {
                Thread.sleep((long) i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
