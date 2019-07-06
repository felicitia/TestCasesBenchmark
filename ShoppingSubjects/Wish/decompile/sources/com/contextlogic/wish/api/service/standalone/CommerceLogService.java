package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.service.SingleApiService;

public class CommerceLogService extends SingleApiService {
    /* JADX WARNING: Removed duplicated region for block: B:13:0x004d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void requestService(boolean r3, int r4, int r5, java.util.HashMap<java.lang.String, java.lang.String> r6, final com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback r7, final com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback r8) {
        /*
            r2 = this;
            com.contextlogic.wish.api.ApiRequest r0 = new com.contextlogic.wish.api.ApiRequest
            java.lang.String r1 = "commerce/log"
            r0.<init>(r1)
            java.lang.String r1 = "is_success"
            r0.addParameter(r1, r3)
            java.lang.String r3 = "action"
            r0.addParameter(r3, r4)
            java.lang.String r3 = "result"
            r0.addParameter(r3, r5)
            if (r6 == 0) goto L_0x004a
            int r3 = r6.size()
            if (r3 <= 0) goto L_0x004a
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ Throwable -> 0x004a }
            r3.<init>()     // Catch:{ Throwable -> 0x004a }
            java.util.Set r4 = r6.entrySet()     // Catch:{ Throwable -> 0x004a }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ Throwable -> 0x004a }
        L_0x002b:
            boolean r5 = r4.hasNext()     // Catch:{ Throwable -> 0x004a }
            if (r5 == 0) goto L_0x0045
            java.lang.Object r5 = r4.next()     // Catch:{ Throwable -> 0x004a }
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5     // Catch:{ Throwable -> 0x004a }
            java.lang.Object r6 = r5.getKey()     // Catch:{ Throwable -> 0x004a }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Throwable -> 0x004a }
            java.lang.Object r5 = r5.getValue()     // Catch:{ Throwable -> 0x004a }
            r3.put(r6, r5)     // Catch:{ Throwable -> 0x004a }
            goto L_0x002b
        L_0x0045:
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x004a }
            goto L_0x004b
        L_0x004a:
            r3 = 0
        L_0x004b:
            if (r3 == 0) goto L_0x0052
            java.lang.String r4 = "extra_info"
            r0.addParameter(r4, r3)
        L_0x0052:
            com.contextlogic.wish.api.service.standalone.CommerceLogService$1 r3 = new com.contextlogic.wish.api.service.standalone.CommerceLogService$1
            r3.<init>(r8, r7)
            r2.startService(r0, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.api.service.standalone.CommerceLogService.requestService(boolean, int, int, java.util.HashMap, com.contextlogic.wish.api.service.ApiService$DefaultSuccessCallback, com.contextlogic.wish.api.service.ApiService$DefaultFailureCallback):void");
    }
}
