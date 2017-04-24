package com.katsuna.stats.http;

import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class HTTPCookieStore implements CookieStore {

    private final static String PREFS_NAME = HTTPCookieStore.class.getName();
    private final static String PREF_DEFAULT_STRING = new JSONObject().toString();
    private final static String PREF_SESSION_COOKIE = "katsuna.session.cookie";

    private CookieStore mStore;
    private Context mContext;
    private Gson mGson;
    private Set<String> mCookieNames = new HashSet<>();

    public HTTPCookieStore(Context context, Set<String> cookieNames) {
        // prevent context leaking by getting the application context
        mContext = context.getApplicationContext();
        mGson = new Gson();
        mCookieNames = cookieNames;

        // get the default in memory store and if there is a cookie stored in shared preferences,
        // we added it to the cookie store
        mStore = new CookieManager().getCookieStore();
        JSONObject jsonSessionCookies = null;
        try {
            jsonSessionCookies = new JSONObject(mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).getString(PREF_SESSION_COOKIE, PREF_DEFAULT_STRING));
            Iterator<String> keys = jsonSessionCookies.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                HttpCookie cookie = mGson.fromJson(jsonSessionCookies.getString(key), HttpCookie.class);
                mStore.add(URI.create(cookie.getDomain()), cookie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public HTTPCookieStore(Context context) {
        this(context, new HashSet<String>());
    }

    @Override
    public void add(URI uri, HttpCookie cookie) {
        if (mCookieNames.contains(cookie.getName())) {
            // if the cookie that the cookie store attempt to add is a session cookie,
            // we remove the older cookie and save the new one in shared preferences
            remove(URI.create(cookie.getDomain()), cookie);

            try {
                JSONObject jsonSessionCookies = new JSONObject(mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).getString(PREF_SESSION_COOKIE, PREF_DEFAULT_STRING));
                jsonSessionCookies.put(cookie.getName(), mGson.toJson(cookie));
                mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit().putString(PREF_SESSION_COOKIE, jsonSessionCookies.toString()).commit();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        mStore.add(URI.create(cookie.getDomain()), cookie);
    }

    @Override
    public List<HttpCookie> get(URI uri) {
        return mStore.get(uri);
    }

    @Override
    public List<HttpCookie> getCookies() {
        return mStore.getCookies();
    }

    @Override
    public List<URI> getURIs() {
        return mStore.getURIs();
    }

    @Override
    public boolean remove(URI uri, HttpCookie cookie) {
        return mStore.remove(uri, cookie);
    }

    @Override
    public boolean removeAll() {
        return mStore.removeAll();
    }
}
