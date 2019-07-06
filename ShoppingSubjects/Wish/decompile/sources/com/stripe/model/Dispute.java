package com.stripe.model;

import com.stripe.net.APIResource;

public class Dispute extends APIResource {
    String evidence;
    EvidenceSubObject evidenceSubObject;

    public void setEvidenceSubObject(EvidenceSubObject evidenceSubObject2) {
        this.evidenceSubObject = evidenceSubObject2;
    }

    public void setEvidence(String str) {
        this.evidence = str;
    }
}
