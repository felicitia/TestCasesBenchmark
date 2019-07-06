package com.threatmetrix.TrustDefender;

import com.threatmetrix.TrustDefender.Profile.Result;

public interface EndNotifier {
    void complete(Result result);
}
