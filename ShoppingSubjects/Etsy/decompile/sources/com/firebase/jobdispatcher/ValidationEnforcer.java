package com.firebase.jobdispatcher;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.List;

public class ValidationEnforcer implements r {
    private final r a;

    public static final class ValidationException extends RuntimeException {
        private final List<String> errors;

        public ValidationException(String str, @NonNull List<String> list) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(": ");
            sb.append(TextUtils.join("\n  - ", list));
            super(sb.toString());
            this.errors = list;
        }

        public List<String> getErrors() {
            return this.errors;
        }
    }

    public ValidationEnforcer(r rVar) {
        this.a = rVar;
    }

    @Nullable
    public List<String> a(o oVar) {
        return this.a.a(oVar);
    }

    public final void b(o oVar) {
        a(a(oVar));
    }

    private static void a(List<String> list) {
        if (list != null) {
            throw new ValidationException("JobParameters is invalid", list);
        }
    }
}
