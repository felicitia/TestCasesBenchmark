package com.kount.api;

import android.util.Log;
import com.kount.api.DataCollector.Error;
import java.lang.reflect.Method;
import java.util.Hashtable;

abstract class CollectorTaskBase {
    private RequestHandler completionHandler = null;
    private final Hashtable<String, String> data = new Hashtable<>();
    private final Object debugHandler;
    protected boolean done = false;
    String sessionID = null;
    private final Hashtable<String, String> softErrors = new Hashtable<>();

    interface RequestHandler {
        void completed(Boolean bool, Error error, Hashtable<String, String> hashtable, Hashtable<String, String> hashtable2);
    }

    /* access modifiers changed from: 0000 */
    public abstract void collect();

    /* access modifiers changed from: 0000 */
    public abstract String getInternalName();

    /* access modifiers changed from: 0000 */
    public abstract String getName();

    CollectorTaskBase(Object obj) {
        this.debugHandler = obj;
    }

    /* access modifiers changed from: 0000 */
    public void collectForSession(String str, RequestHandler requestHandler) {
        this.sessionID = str;
        this.completionHandler = requestHandler;
        debugMessage("Starting");
        if (this.debugHandler != null) {
            try {
                Method declaredMethod = this.debugHandler.getClass().getDeclaredMethod("collectorStarted", new Class[]{String.class});
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(this.debugHandler, new Object[]{getName()});
            } catch (Exception e) {
                debugMessage(String.format("Exception: %s", new Object[]{e.getMessage()}));
            }
        }
        collect();
    }

    /* access modifiers changed from: 0000 */
    public void callCompletionHandler(Boolean bool, Error error) {
        String str = "Completed with %s";
        Object[] objArr = new Object[1];
        objArr[0] = bool.booleanValue() ? "Success" : "Failure";
        debugMessage(String.format(str, objArr));
        if (this.debugHandler != null) {
            try {
                Method declaredMethod = this.debugHandler.getClass().getDeclaredMethod("collectorDone", new Class[]{String.class, Boolean.class, Error.class});
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(this.debugHandler, new Object[]{getName(), bool, error});
            } catch (Exception e) {
                debugMessage(String.format("Exception: %s", new Object[]{e.getMessage()}));
            }
        }
        this.done = true;
        this.completionHandler.completed(bool, error, this.data, this.softErrors);
    }

    /* access modifiers changed from: 0000 */
    public void addDataPoint(String str, String str2) {
        if (str2 != null) {
            this.data.put(str, str2);
        }
    }

    /* access modifiers changed from: 0000 */
    public void addSoftError(String str) {
        this.softErrors.put(getInternalName(), str);
    }

    /* access modifiers changed from: 0000 */
    public void debugMessage(String str) {
        if (this.debugHandler != null) {
            String format = String.format("(%s) <%s> %s", new Object[]{this.sessionID, getName(), str});
            Log.d("DataCollector", format);
            try {
                Method declaredMethod = this.debugHandler.getClass().getDeclaredMethod("collectorDebugMessage", new Class[]{String.class});
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(this.debugHandler, new Object[]{format});
            } catch (Exception e) {
                debugMessage(String.format("Exception: %s", new Object[]{e.getMessage()}));
            }
        }
    }
}
