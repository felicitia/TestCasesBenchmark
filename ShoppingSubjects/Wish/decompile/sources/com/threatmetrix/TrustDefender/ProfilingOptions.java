package com.threatmetrix.TrustDefender;

import android.location.Location;
import com.threatmetrix.TrustDefender.internal.K7.O;
import com.threatmetrix.TrustDefender.internal.N.K;
import com.threatmetrix.TrustDefender.internal.P;
import java.util.List;

public class ProfilingOptions {
    public List<String> m_customAttributes;
    public O m_location;
    public String m_sessionID;

    public ProfilingOptions setSessionID(String str) {
        this.m_sessionID = str;
        return this;
    }

    public ProfilingOptions setCustomAttributes(List<String> list) {
        this.m_customAttributes = list;
        return this;
    }

    public ProfilingOptions setLocation(Location location) {
        if (K.m162do()) {
            this.m_location = P.m244do(location, true);
        }
        return this;
    }
}
