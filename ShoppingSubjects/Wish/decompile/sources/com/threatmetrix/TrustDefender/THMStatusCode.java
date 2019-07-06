package com.threatmetrix.TrustDefender;

public enum THMStatusCode {
    THM_NotYet("Not Yet", "Profile request has started successfully but has not completed"),
    THM_OK("Okay", "Completed, No errors"),
    THM_Connection_Error("Connection Error", "There was a connection issue, profiling aborted"),
    THM_HostNotFound_Error("Host Not Found", "Unable to resolve the host name"),
    THM_NetworkTimeout_Error("Network Timeout", "Communications layer timed out"),
    THM_Internal_Error("Internal Error", "Internal Error, profiling incomplete or interrupted"),
    THM_HostVerification_Error("Host Verification Error", "Certificate verification failure! Potential MITM attack"),
    THM_Interrupted_Error("Interrupted", "Request was cancelled"),
    THM_ConfigurationError("Configuration Error", "Failed to retrieve configuration from server"),
    THM_PartialProfile("Partial Profile", "Connection error, only partial profile completed"),
    THM_Blocked("Profiling is blocked", "Profiling is blocked.(Screen is off)"),
    THM_EndNotifier_NotFound("EndNotifier not found", "EndNotifier is mandatory in profile request"),
    THM_In_Quiet_Period("In Quiet Period", "Profiling is blocked (In Quiet Period)"),
    THM_Certificate_Mismatch("Certificate Mismatch", "The pinned certificate does not match the server's certificate"),
    THM_StrongAuth_Failed("Registration failed", "System has rejected Registration attempt"),
    THM_StrongAuth_Cancelled("Registration was cancelled", "User has chosen not to proceed with Registration"),
    THM_StrongAuth_Unsupported("Strong Auth Method Unsupported", "Hardware/OS does not support this functionality.");
    
    private final String desc;
    private final String label;

    private THMStatusCode(String str, String str2) {
        this.label = str;
        this.desc = str2;
    }

    public final String toString() {
        return this.label;
    }

    public final String getDesc() {
        return this.desc;
    }
}
