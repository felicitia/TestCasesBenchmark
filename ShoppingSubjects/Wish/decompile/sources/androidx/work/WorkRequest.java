package androidx.work;

import androidx.work.impl.model.WorkSpec;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public abstract class WorkRequest {
    private UUID mId;
    private Set<String> mTags;
    private WorkSpec mWorkSpec;

    public static abstract class Builder<B extends Builder, W extends WorkRequest> {
        boolean mBackoffCriteriaSet = false;
        UUID mId = UUID.randomUUID();
        Set<String> mTags = new HashSet();
        WorkSpec mWorkSpec;

        /* access modifiers changed from: 0000 */
        public abstract B getThis();

        public Builder(Class<? extends Worker> cls) {
            this.mWorkSpec = new WorkSpec(this.mId.toString(), cls.getName());
        }

        public B setInputData(Data data) {
            this.mWorkSpec.input = data;
            return getThis();
        }

        public B addTag(String str) {
            this.mTags.add(str);
            return getThis();
        }
    }

    protected WorkRequest(UUID uuid, WorkSpec workSpec, Set<String> set) {
        this.mId = uuid;
        this.mWorkSpec = workSpec;
        this.mTags = set;
    }

    public String getStringId() {
        return this.mId.toString();
    }

    public WorkSpec getWorkSpec() {
        return this.mWorkSpec;
    }

    public Set<String> getTags() {
        return this.mTags;
    }
}
