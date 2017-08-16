package com.example.administrator.quickwebview.sdk;

import android.webkit.WebResourceResponse;

import java.io.InputStream;

/**
 * Created by Administrator on 2017/8/14.
 */
public class QuickWeb {

    private static QuickWeb mInstance = null;

    private QuickWeb() {

    }

    public static QuickWeb getInstance() {

        if (mInstance == null) {
            mInstance = new QuickWeb();
        }
        return mInstance;
    }


    /**
     * @param url request resource url
     */
    public WebResourceResponse requestResource(String url) {
//      only cache js  css  png gif
//      if you want to  cache ,can add resource
        if (!url.contains(".js") && !url.contains(".css") && !url.contains(".png") && !url.contains(".gif")) {
            return null;
        }
        QuickConnection quickConnection = new QuickConnection(url);
        InputStream resource = quickConnection.getResource();
        String mineType = FileUtil.getMime(url);
        if (resource != null) {
            return new WebResourceResponse(mineType, "utf-8", resource);
        }
        return null;
    }

}
