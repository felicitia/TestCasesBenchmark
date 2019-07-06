package io.branch.referral;

/* compiled from: BranchError */
public class c {
    String a = "";
    int b = -113;

    public String a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public String toString() {
        return a();
    }

    public c(String str, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(a(i));
        this.a = sb.toString();
    }

    private String a(int i) {
        if (i == -113) {
            this.b = -113;
            return " Branch API Error: poor network connectivity. Please try again later.";
        } else if (i == -114) {
            this.b = -114;
            return " Branch API Error: Please enter your branch_key in your project's manifest file first.";
        } else if (i == -104) {
            this.b = -104;
            return " Did you forget to call init? Make sure you init the session before making Branch calls.";
        } else if (i == -101) {
            this.b = -101;
            return " Unable to initialize Branch. Check network connectivity or that your branch key is valid.";
        } else if (i == -102) {
            this.b = -102;
            return " Please add 'android.permission.INTERNET' in your applications manifest file.";
        } else if (i == -105) {
            this.b = -105;
            return " Unable to create a URL with that alias. If you want to reuse the alias, make sure to submit the same properties for all arguments and that the user is the same owner.";
        } else if (i == -106) {
            this.b = -106;
            return " That Branch referral code is already in use.";
        } else if (i == -107) {
            this.b = -107;
            return " Unable to redeem rewards. Please make sure you have credits available to redeem.";
        } else if (i == -108) {
            this.b = -108;
            return "BranchApp class can be used only with API level 14 or above. Please make sure your minimum API level supported is 14. If you wish to use API level below 14 consider calling getInstance(Context) instead.";
        } else if (i == -109) {
            this.b = -109;
            return "Branch instance is not created. Make  sure your Application class is an instance of BranchLikedApp.";
        } else if (i == -110) {
            this.b = -110;
            return " Unable create share options. Couldn't find applications on device to share the link.";
        } else if (i == -111) {
            this.b = -111;
            return " Request to Branch server timed out. Please check your internet connectivity";
        } else if (i == -117) {
            this.b = -117;
            return " Tracking is disabled. Requested operation cannot be completed when tracking is disabled";
        } else if (i >= 500 || i == -112) {
            this.b = -112;
            return " Unable to reach the Branch servers, please try again shortly.";
        } else if (i == 409 || i == -115) {
            this.b = -115;
            return " A resource with this identifier already exists.";
        } else if (i >= 400 || i == -116) {
            this.b = -116;
            return " The request was invalid.";
        } else {
            this.b = -113;
            return " Check network connectivity and that you properly initialized.";
        }
    }
}
