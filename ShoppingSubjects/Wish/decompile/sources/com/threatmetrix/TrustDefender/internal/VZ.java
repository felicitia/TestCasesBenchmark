package com.threatmetrix.TrustDefender.internal;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class VZ implements Y {

    /* renamed from: do reason: not valid java name */
    private static final String f559do = TL.m331if(VZ.class);

    /* renamed from: byte reason: not valid java name */
    private volatile boolean f560byte = false;

    /* renamed from: case reason: not valid java name */
    private volatile boolean f561case = false;

    /* renamed from: char reason: not valid java name */
    private volatile boolean f562char = false;

    /* renamed from: else reason: not valid java name */
    private CountDownLatch f563else = null;

    /* renamed from: for reason: not valid java name */
    private final ReentrantReadWriteLock f564for = new ReentrantReadWriteLock();

    /* renamed from: if reason: not valid java name */
    private volatile boolean f565if = false;

    /* renamed from: int reason: not valid java name */
    private volatile boolean f566int = false;

    /* renamed from: new reason: not valid java name */
    private volatile boolean f567new = false;

    /* renamed from: try reason: not valid java name */
    private CountDownLatch f568try = null;

    VZ() {
    }

    /* renamed from: if reason: not valid java name */
    public final boolean m357if() {
        this.f564for.readLock().lock();
        try {
            return this.f566int;
        } finally {
            this.f564for.readLock().unlock();
        }
    }

    /* renamed from: int reason: not valid java name */
    public final boolean m359int() {
        this.f564for.readLock().lock();
        try {
            CountDownLatch countDownLatch = this.f563else;
            return this.f566int && countDownLatch != null && countDownLatch.getCount() == 0;
        } finally {
            this.f564for.readLock().unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: do reason: not valid java name */
    public final boolean m352do() {
        this.f564for.writeLock().lock();
        try {
            if (!this.f566int) {
                this.f566int = true;
                this.f563else = new CountDownLatch(1);
                return true;
            }
            this.f564for.writeLock().unlock();
            return false;
        } finally {
            this.f564for.writeLock().unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: int reason: not valid java name */
    public final void m358int(boolean z) {
        this.f564for.readLock().lock();
        try {
            this.f566int = z;
            CountDownLatch countDownLatch = this.f563else;
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        } finally {
            this.f564for.readLock().unlock();
        }
    }

    /* renamed from: int reason: not valid java name */
    public final boolean m360int(int i) {
        boolean z;
        this.f564for.readLock().lock();
        try {
            boolean z2 = false;
            if (this.f566int) {
                if (this.f563else != null) {
                    CountDownLatch countDownLatch = this.f563else;
                    this.f564for.readLock().unlock();
                    TL.m338new(f559do, "Waiting for init to complete");
                    try {
                        z = countDownLatch.await((long) i, TimeUnit.MILLISECONDS);
                        if (!z) {
                            try {
                                TL.m332if(f559do, "Timed out waiting for init to complete");
                            } catch (InterruptedException e) {
                                e = e;
                            }
                        }
                    } catch (InterruptedException e2) {
                        e = e2;
                        z = false;
                        TL.m337int(f559do, "Waiting for init to complete interrupted", e);
                        this.f564for.readLock().lock();
                        z2 = true;
                        return z2;
                    }
                    this.f564for.readLock().lock();
                    try {
                        if (this.f566int && z) {
                            z2 = true;
                        }
                        return z2;
                    } finally {
                        this.f564for.readLock().unlock();
                    }
                }
            }
            TL.m338new(f559do, "init not in progress, nothing to wait for");
            return false;
        } finally {
            this.f564for.readLock().unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: new reason: not valid java name */
    public final boolean m361new() {
        this.f564for.readLock().lock();
        try {
            return this.f567new;
        } finally {
            this.f564for.readLock().unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: try reason: not valid java name */
    public final boolean m362try() {
        this.f564for.writeLock().lock();
        try {
            if (!this.f567new) {
                this.f567new = true;
                this.f565if = false;
                return true;
            }
            this.f564for.writeLock().unlock();
            return false;
        } finally {
            this.f564for.writeLock().unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: char reason: not valid java name */
    public final void m351char() {
        this.f564for.writeLock().lock();
        try {
            this.f567new = false;
        } finally {
            this.f564for.writeLock().unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: case reason: not valid java name */
    public final boolean m350case() {
        this.f564for.writeLock().lock();
        try {
            if (!this.f565if) {
                this.f565if = true;
                return true;
            }
            this.f564for.writeLock().unlock();
            return false;
        } finally {
            this.f564for.writeLock().unlock();
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: 0000 */
    /* renamed from: byte reason: not valid java name */
    public final boolean m349byte() {
        this.f564for.writeLock().lock();
        try {
            if (this.f565if) {
                this.f565if = false;
                this.f561case = false;
                this.f564for.writeLock().unlock();
                return true;
            }
            this.f564for.writeLock().unlock();
            return false;
        } catch (Throwable th) {
            this.f564for.writeLock().unlock();
            throw th;
        }
    }

    /* renamed from: for reason: not valid java name */
    public final boolean m355for() {
        this.f564for.readLock().lock();
        try {
            return this.f565if;
        } finally {
            this.f564for.readLock().unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: else reason: not valid java name */
    public final boolean m354else() {
        TL.m338new(f559do, "Attempting to cancel doPackageScan");
        this.f564for.writeLock().lock();
        try {
            if (!this.f561case) {
                this.f561case = true;
                return this.f560byte;
            }
            this.f564for.writeLock().unlock();
            return false;
        } finally {
            this.f564for.writeLock().unlock();
        }
    }

    /* renamed from: do reason: not valid java name */
    public final boolean m353do(boolean z) {
        this.f564for.writeLock().lock();
        try {
            if (this.f560byte) {
                this.f564for.writeLock().unlock();
                return false;
            } else if (this.f561case) {
                TL.m338new(f559do, "startScanning: aborted, marked as cancelled");
                this.f561case = false;
                return false;
            } else {
                this.f560byte = true;
                this.f568try = new CountDownLatch(1);
                this.f562char = z;
                this.f564for.writeLock().unlock();
                return true;
            }
        } finally {
            this.f564for.writeLock().unlock();
        }
    }

    /* renamed from: void reason: not valid java name */
    public final void m363void() {
        CountDownLatch countDownLatch;
        this.f564for.readLock().lock();
        try {
            if (this.f560byte) {
                this.f560byte = false;
                this.f561case = false;
                this.f562char = false;
                countDownLatch = this.f568try;
            } else {
                countDownLatch = null;
            }
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        } finally {
            this.f564for.readLock().unlock();
        }
    }

    /* renamed from: if reason: not valid java name */
    private CountDownLatch m348if(boolean z) {
        this.f564for.readLock().lock();
        try {
            if (this.f560byte && this.f568try != null && (!z || this.f562char)) {
                return this.f568try;
            }
            this.f564for.readLock().unlock();
            return null;
        } finally {
            this.f564for.readLock().unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: for reason: not valid java name */
    public final boolean m356for(boolean z, Long l) {
        boolean z2;
        if (l != null && l.longValue() <= 0) {
            return false;
        }
        CountDownLatch countDownLatch = m348if(z);
        if (countDownLatch == null) {
            return true;
        }
        TL.m338new(f559do, "waitForScan: Waiting for scan to complete");
        if (l == null) {
            try {
                countDownLatch.await();
                z2 = true;
            } catch (InterruptedException e) {
                if (!m355for()) {
                    TL.m337int(f559do, "waitForScan: Waiting for scan to complete interrupted", e);
                } else {
                    TL.m338new(f559do, "waitForScan: interrupted by cancel");
                }
                z2 = false;
            }
        } else {
            z2 = countDownLatch.await(l.longValue(), TimeUnit.MILLISECONDS);
        }
        return z2;
    }
}
