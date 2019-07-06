package com.facebook.share.internal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import android.util.Pair;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.editable.EditableListing;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.ParcelableResourceWithMimeType;
import com.facebook.HttpMethod;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.d;
import com.facebook.e;
import com.facebook.f;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.internal.a;
import com.facebook.internal.u;
import com.facebook.internal.v;
import com.facebook.internal.z;
import com.facebook.internal.z.b;
import com.facebook.share.c;
import com.facebook.share.model.CameraEffectTextures;
import com.facebook.share.model.ShareCameraEffectContent;
import com.facebook.share.model.ShareMedia;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareStoryContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.widget.LikeView.ObjectType;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ShareInternalUtility */
public final class k {
    public static String a(Bundle bundle) {
        if (bundle.containsKey("completionGesture")) {
            return bundle.getString("completionGesture");
        }
        return bundle.getString("com.facebook.platform.extra.COMPLETION_GESTURE");
    }

    public static String b(Bundle bundle) {
        if (bundle.containsKey("postId")) {
            return bundle.getString("postId");
        }
        if (bundle.containsKey("com.facebook.platform.extra.POST_ID")) {
            return bundle.getString("com.facebook.platform.extra.POST_ID");
        }
        return bundle.getString("post_id");
    }

    public static boolean a(int i, int i2, Intent intent, i iVar) {
        a a = a(i, i2, intent);
        if (a == null) {
            return false;
        }
        u.a(a.c());
        if (iVar == null) {
            return true;
        }
        FacebookException a2 = v.a(v.g(intent));
        if (a2 == null) {
            iVar.a(a, v.e(intent));
        } else if (a2 instanceof FacebookOperationCanceledException) {
            iVar.a(a);
        } else {
            iVar.a(a, a2);
        }
        return true;
    }

    public static i a(final e<c.a> eVar) {
        return new i(eVar) {
            public void a(a aVar, Bundle bundle) {
                if (bundle != null) {
                    String a2 = k.a(bundle);
                    if (a2 == null || "post".equalsIgnoreCase(a2)) {
                        k.a(eVar, k.b(bundle));
                    } else if ("cancel".equalsIgnoreCase(a2)) {
                        k.b(eVar);
                    } else {
                        k.a(eVar, new FacebookException("UnknownError"));
                    }
                }
            }

            public void a(a aVar) {
                k.b(eVar);
            }

            public void a(a aVar, FacebookException facebookException) {
                k.a(eVar, facebookException);
            }
        };
    }

    private static a a(int i, int i2, Intent intent) {
        UUID b = v.b(intent);
        if (b == null) {
            return null;
        }
        return a.a(b, i);
    }

    public static void a(final int i) {
        CallbackManagerImpl.a(i, new CallbackManagerImpl.a() {
            public boolean a(int i, Intent intent) {
                return k.a(i, i, intent, k.a(null));
            }
        });
    }

    public static void a(final int i, d dVar, final e<c.a> eVar) {
        if (!(dVar instanceof CallbackManagerImpl)) {
            throw new FacebookException("Unexpected CallbackManager, please use the provided Factory.");
        }
        ((CallbackManagerImpl) dVar).b(i, new CallbackManagerImpl.a() {
            public boolean a(int i, Intent intent) {
                return k.a(i, i, intent, k.a(eVar));
            }
        });
    }

    public static List<String> a(SharePhotoContent sharePhotoContent, final UUID uuid) {
        if (sharePhotoContent != null) {
            List photos = sharePhotoContent.getPhotos();
            if (photos != null) {
                List a = z.a(photos, (b<T, K>) new b<SharePhoto, u.a>() {
                    public u.a a(SharePhoto sharePhoto) {
                        return k.b(uuid, (ShareMedia) sharePhoto);
                    }
                });
                List<String> a2 = z.a(a, (b<T, K>) new b<u.a, String>() {
                    public String a(u.a aVar) {
                        return aVar.a();
                    }
                });
                u.a((Collection<u.a>) a);
                return a2;
            }
        }
        return null;
    }

    public static String a(ShareVideoContent shareVideoContent, UUID uuid) {
        if (shareVideoContent == null || shareVideoContent.getVideo() == null) {
            return null;
        }
        u.a a = u.a(uuid, shareVideoContent.getVideo().getLocalUrl());
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(a);
        u.a((Collection<u.a>) arrayList);
        return a.a();
    }

    public static List<Bundle> a(ShareMediaContent shareMediaContent, final UUID uuid) {
        if (shareMediaContent != null) {
            List media = shareMediaContent.getMedia();
            if (media != null) {
                final ArrayList arrayList = new ArrayList();
                List<Bundle> a = z.a(media, (b<T, K>) new b<ShareMedia, Bundle>() {
                    public Bundle a(ShareMedia shareMedia) {
                        u.a a2 = k.b(uuid, shareMedia);
                        arrayList.add(a2);
                        Bundle bundle = new Bundle();
                        bundle.putString("type", shareMedia.getMediaType().name());
                        bundle.putString("uri", a2.a());
                        return bundle;
                    }
                });
                u.a((Collection<u.a>) arrayList);
                return a;
            }
        }
        return null;
    }

    public static Bundle a(ShareCameraEffectContent shareCameraEffectContent, UUID uuid) {
        if (shareCameraEffectContent != null) {
            CameraEffectTextures textures = shareCameraEffectContent.getTextures();
            if (textures != null) {
                Bundle bundle = new Bundle();
                ArrayList arrayList = new ArrayList();
                for (String str : textures.keySet()) {
                    u.a a = a(uuid, textures.getTextureUri(str), textures.getTextureBitmap(str));
                    arrayList.add(a);
                    bundle.putString(str, a.a());
                }
                u.a((Collection<u.a>) arrayList);
                return bundle;
            }
        }
        return null;
    }

    public static JSONObject a(final UUID uuid, ShareOpenGraphContent shareOpenGraphContent) throws JSONException {
        Set set;
        ShareOpenGraphAction action = shareOpenGraphContent.getAction();
        final ArrayList arrayList = new ArrayList();
        JSONObject a = h.a(action, (h.a) new h.a() {
            public JSONObject a(SharePhoto sharePhoto) {
                u.a a2 = k.b(uuid, (ShareMedia) sharePhoto);
                if (a2 == null) {
                    return null;
                }
                arrayList.add(a2);
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("url", a2.a());
                    if (sharePhoto.getUserGenerated()) {
                        jSONObject.put("user_generated", true);
                    }
                    return jSONObject;
                } catch (JSONException e) {
                    throw new FacebookException("Unable to attach images", (Throwable) e);
                }
            }
        });
        u.a((Collection<u.a>) arrayList);
        if (shareOpenGraphContent.getPlaceId() != null && z.a(a.optString("place"))) {
            a.put("place", shareOpenGraphContent.getPlaceId());
        }
        if (shareOpenGraphContent.getPeopleIds() != null) {
            JSONArray optJSONArray = a.optJSONArray(EditableListing.FIELD_TAGS);
            if (optJSONArray == null) {
                set = new HashSet();
            } else {
                set = z.b(optJSONArray);
            }
            for (String add : shareOpenGraphContent.getPeopleIds()) {
                set.add(add);
            }
            a.put(EditableListing.FIELD_TAGS, new JSONArray(set));
        }
        return a;
    }

    public static JSONObject a(ShareOpenGraphContent shareOpenGraphContent) throws JSONException {
        return h.a(shareOpenGraphContent.getAction(), (h.a) new h.a() {
            public JSONObject a(SharePhoto sharePhoto) {
                Uri imageUrl = sharePhoto.getImageUrl();
                if (!z.b(imageUrl)) {
                    throw new FacebookException("Only web images may be used in OG objects shared via the web dialog");
                }
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("url", imageUrl.toString());
                    return jSONObject;
                } catch (JSONException e) {
                    throw new FacebookException("Unable to attach images", (Throwable) e);
                }
            }
        });
    }

    public static JSONArray a(JSONArray jSONArray, boolean z) throws JSONException {
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (obj instanceof JSONArray) {
                obj = a((JSONArray) obj, z);
            } else if (obj instanceof JSONObject) {
                obj = a((JSONObject) obj, z);
            }
            jSONArray2.put(obj);
        }
        return jSONArray2;
    }

    public static JSONObject a(JSONObject jSONObject, boolean z) {
        if (jSONObject == null) {
            return null;
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            JSONArray names = jSONObject.names();
            for (int i = 0; i < names.length(); i++) {
                String string = names.getString(i);
                Object obj = jSONObject.get(string);
                if (obj instanceof JSONObject) {
                    obj = a((JSONObject) obj, true);
                } else if (obj instanceof JSONArray) {
                    obj = a((JSONArray) obj, true);
                }
                Pair a = a(string);
                String str = (String) a.first;
                String str2 = (String) a.second;
                if (z) {
                    if (str == null || !str.equals("fbsdk")) {
                        if (str != null) {
                            if (!str.equals("og")) {
                                jSONObject3.put(str2, obj);
                            }
                        }
                        jSONObject2.put(str2, obj);
                    } else {
                        jSONObject2.put(string, obj);
                    }
                } else if (str == null || !str.equals("fb")) {
                    jSONObject2.put(str2, obj);
                } else {
                    jSONObject2.put(string, obj);
                }
            }
            if (jSONObject3.length() > 0) {
                jSONObject2.put("data", jSONObject3);
            }
            return jSONObject2;
        } catch (JSONException unused) {
            throw new FacebookException("Failed to create json object from share content");
        }
    }

    public static Pair<String, String> a(String str) {
        String str2;
        int indexOf = str.indexOf(58);
        if (indexOf != -1) {
            int i = indexOf + 1;
            if (str.length() > i) {
                str2 = str.substring(0, indexOf);
                str = str.substring(i);
                return new Pair<>(str2, str);
            }
        }
        str2 = null;
        return new Pair<>(str2, str);
    }

    /* access modifiers changed from: private */
    public static u.a b(UUID uuid, ShareMedia shareMedia) {
        Uri uri;
        Bitmap bitmap = null;
        if (shareMedia instanceof SharePhoto) {
            SharePhoto sharePhoto = (SharePhoto) shareMedia;
            bitmap = sharePhoto.getBitmap();
            uri = sharePhoto.getImageUrl();
        } else {
            uri = shareMedia instanceof ShareVideo ? ((ShareVideo) shareMedia).getLocalUrl() : null;
        }
        return a(uuid, uri, bitmap);
    }

    private static u.a a(UUID uuid, Uri uri, Bitmap bitmap) {
        if (bitmap != null) {
            return u.a(uuid, bitmap);
        }
        if (uri != null) {
            return u.a(uuid, uri);
        }
        return null;
    }

    static void b(e<c.a> eVar) {
        a("cancelled", (String) null);
        if (eVar != null) {
            eVar.a();
        }
    }

    static void a(e<c.a> eVar, String str) {
        a("succeeded", (String) null);
        if (eVar != null) {
            eVar.a(new c.a(str));
        }
    }

    static void a(e<c.a> eVar, FacebookException facebookException) {
        a("error", facebookException.getMessage());
        if (eVar != null) {
            eVar.a(facebookException);
        }
    }

    private static void a(String str, String str2) {
        AppEventsLogger a = AppEventsLogger.a(f.f());
        Bundle bundle = new Bundle();
        bundle.putString("fb_share_dialog_outcome", str);
        if (str2 != null) {
            bundle.putString(ResponseConstants.ERROR_MESSAGE, str2);
        }
        a.a("fb_share_dialog_result", (Double) null, bundle);
    }

    public static GraphRequest a(AccessToken accessToken, File file, GraphRequest.b bVar) throws FileNotFoundException {
        ParcelableResourceWithMimeType parcelableResourceWithMimeType = new ParcelableResourceWithMimeType(ParcelFileDescriptor.open(file, ErrorDialogData.BINDER_CRASH), "image/png");
        Bundle bundle = new Bundle(1);
        bundle.putParcelable(ResponseConstants.FILE, parcelableResourceWithMimeType);
        GraphRequest graphRequest = new GraphRequest(accessToken, "me/staging_resources", bundle, HttpMethod.POST, bVar);
        return graphRequest;
    }

    public static GraphRequest a(AccessToken accessToken, Uri uri, GraphRequest.b bVar) throws FileNotFoundException {
        if (z.d(uri)) {
            return a(accessToken, new File(uri.getPath()), bVar);
        }
        if (!z.c(uri)) {
            throw new FacebookException("The image Uri must be either a file:// or content:// Uri");
        }
        ParcelableResourceWithMimeType parcelableResourceWithMimeType = new ParcelableResourceWithMimeType(uri, "image/png");
        Bundle bundle = new Bundle(1);
        bundle.putParcelable(ResponseConstants.FILE, parcelableResourceWithMimeType);
        GraphRequest graphRequest = new GraphRequest(accessToken, "me/staging_resources", bundle, HttpMethod.POST, bVar);
        return graphRequest;
    }

    @Nullable
    public static ObjectType a(ObjectType objectType, ObjectType objectType2) {
        if (objectType == objectType2) {
            return objectType;
        }
        if (objectType == ObjectType.UNKNOWN) {
            return objectType2;
        }
        if (objectType2 == ObjectType.UNKNOWN) {
            return objectType;
        }
        return null;
    }

    @Nullable
    public static Bundle a(ShareStoryContent shareStoryContent, final UUID uuid) {
        if (shareStoryContent == null || shareStoryContent.getStickerAsset() == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(shareStoryContent.getStickerAsset());
        List a = z.a((List<T>) arrayList, (b<T, K>) new b<SharePhoto, u.a>() {
            public u.a a(SharePhoto sharePhoto) {
                return k.b(uuid, (ShareMedia) sharePhoto);
            }
        });
        List a2 = z.a(a, (b<T, K>) new b<u.a, Bundle>() {
            public Bundle a(u.a aVar) {
                Bundle bundle = new Bundle();
                bundle.putString("uri", aVar.a());
                String a = k.a(aVar.b());
                if (a != null) {
                    z.a(bundle, "extension", a);
                }
                return bundle;
            }
        });
        u.a((Collection<u.a>) a);
        return (Bundle) a2.get(0);
    }

    @Nullable
    public static Bundle b(ShareStoryContent shareStoryContent, final UUID uuid) {
        if (shareStoryContent == null || shareStoryContent.getBackgroundAsset() == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(shareStoryContent.getBackgroundAsset());
        final ArrayList arrayList2 = new ArrayList();
        List a = z.a((List<T>) arrayList, (b<T, K>) new b<ShareMedia, Bundle>() {
            public Bundle a(ShareMedia shareMedia) {
                u.a a2 = k.b(uuid, shareMedia);
                arrayList2.add(a2);
                Bundle bundle = new Bundle();
                bundle.putString("type", shareMedia.getMediaType().name());
                bundle.putString("uri", a2.a());
                String a3 = k.a(a2.b());
                if (a3 != null) {
                    z.a(bundle, "extension", a3);
                }
                return bundle;
            }
        });
        u.a((Collection<u.a>) arrayList2);
        return (Bundle) a.get(0);
    }

    @Nullable
    public static String a(Uri uri) {
        if (uri == null) {
            return null;
        }
        String uri2 = uri.toString();
        int lastIndexOf = uri2.lastIndexOf(46);
        if (lastIndexOf == -1) {
            return null;
        }
        return uri2.substring(lastIndexOf);
    }
}
