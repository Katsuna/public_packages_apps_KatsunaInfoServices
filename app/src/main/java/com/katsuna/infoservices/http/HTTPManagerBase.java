package com.katsuna.infoservices.http;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.katsuna.infoservices.logging.Logger;


public class HTTPManagerBase {

    private static final int MAX_IMAGE_CACHE_ENTRIES = 100;
    private static RequestQueue mQueue;
    private static ImageLoader mImageLoader;
    private static Context mContext;
    private static RetryPolicy retryPolicy;


    public static void init(Context context, RetryPolicy mretryPolicy) {
        mContext = context;
        mQueue = Volley.newRequestQueue(mContext);
        mImageLoader = new ImageLoader(mQueue, new BitmapLruCache(MAX_IMAGE_CACHE_ENTRIES));
        retryPolicy = mretryPolicy;
    }

    protected static void execute(Request<?> request) {
        if (mQueue == null)
            throw new RuntimeException("HTTPManager not Initialized!!");

        try {
            if (request instanceof JSONRequest)
                Logger.Log("Requesting: " + request.getUrl() + " " + "with params: " + new String(request.getBody()));
            else if (request instanceof MultipartRequest)
                Logger.Log("Uploading to: " + request.getUrl());
        } catch (Exception error) {
            error.printStackTrace();
        }

//        int socketTimeout = 20000; //30 seconds
//        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(retryPolicy);

        mQueue.add(request);
    }

    private static ImageLoader getImageLoader() {
        if (mImageLoader != null) {
            return mImageLoader;
        } else {
            throw new IllegalStateException("ImageLoader not initialized");
        }
    }

    protected static void downloadImage(String imageUrl, ImageView imageView) {
        getImageLoader().get(imageUrl, ImageLoader.getImageListener(imageView, 0, 0));
    }
}
