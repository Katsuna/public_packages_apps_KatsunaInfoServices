/**
* Copyright (C) 2020 Manos Saratsis
*
* This file is part of Katsuna.
*
* Katsuna is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Katsuna is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with Katsuna.  If not, see <https://www.gnu.org/licenses/>.
*/
package com.katsuna.infoservices.http;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
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
            else if (request instanceof StringRequest)
                Logger.Log("Requesting: " + request.getUrl()+ " " + "with headers: " + new String(String.valueOf(request.getHeaders())));
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
