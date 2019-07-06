package com.threatmetrix.TrustDefender;

public class Profile {

    public interface Handle {
        void cancel();

        String getSessionID();
    }

    public class Result {

        /* renamed from: new reason: not valid java name */
        private final THMStatusCode f12new;
        private final String sessionID;

        public Result(String str, THMStatusCode tHMStatusCode) {
            this.sessionID = str;
            this.f12new = tHMStatusCode;
        }

        public String getSessionID() {
            return this.sessionID;
        }

        public THMStatusCode getStatus() {
            return this.f12new;
        }
    }
}
