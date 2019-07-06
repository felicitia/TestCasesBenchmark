package io.reactivex.disposables;

final class RunnableDisposable extends ReferenceDisposable<Runnable> {
    RunnableDisposable(Runnable runnable) {
        super(runnable);
    }

    /* access modifiers changed from: protected */
    public void onDisposed(Runnable runnable) {
        runnable.run();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RunnableDisposable(disposed=");
        sb.append(isDisposed());
        sb.append(", ");
        sb.append(get());
        sb.append(")");
        return sb.toString();
    }
}
