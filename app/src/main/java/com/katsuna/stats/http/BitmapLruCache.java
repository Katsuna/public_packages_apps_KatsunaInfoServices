package com.katsuna.stats.http;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;


public class BitmapLruCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache {
    public BitmapLruCache(int maxSize) {
        super(maxSize);
    }



    @Override
    public Bitmap getBitmap(String url) {
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        put(url, bitmap);
    }
}