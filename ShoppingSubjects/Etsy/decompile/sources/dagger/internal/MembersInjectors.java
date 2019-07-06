package dagger.internal;

import dagger.a;

public final class MembersInjectors {

    private enum NoOpMembersInjector implements a<Object> {
        INSTANCE;

        public void injectMembers(Object obj) {
            f.a(obj);
        }
    }
}
