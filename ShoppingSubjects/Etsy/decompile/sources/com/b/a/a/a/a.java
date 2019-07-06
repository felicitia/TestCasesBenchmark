package com.b.a.a.a;

import android.util.Log;
import com.crashlytics.android.answers.Answers;

/* compiled from: AnswersKitEventLogger */
class a implements d {
    private final Answers a;

    private a(Answers answers) {
        this.a = answers;
    }

    public static a a() throws NoClassDefFoundError, IllegalStateException {
        return a(Answers.getInstance());
    }

    static a a(Answers answers) throws IllegalStateException {
        if (answers != null) {
            return new a(answers);
        }
        throw new IllegalStateException("Answers must be initialized before logging kit events");
    }

    public void a(c cVar) {
        try {
            this.a.logCustom(cVar.a());
        } catch (Throwable th) {
            Log.w("AnswersKitEventLogger", "Unexpected error sending Answers event", th);
        }
    }
}
