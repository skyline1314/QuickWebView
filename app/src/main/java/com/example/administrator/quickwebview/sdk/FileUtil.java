package com.example.administrator.quickwebview.sdk;

import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2017/8/15.
 */
public class FileUtil {

    public static FileInputStream readFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                System.out.println("loading the cache, " + path);
                return fileInputStream;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
        return null;
    }

    /**
     * @param url resource url
     * @return return mimeType by url
     */
    public static String getMime(String url) {
        String mime = "text/html";
        Uri currentUri = Uri.parse(url);
        String path = currentUri.getPath();
        if (path.contains(".css")) {
            mime = "text/css";
        } else if (path.contains(".js")) {
            mime = "application/x-javascript";
        } else if (path.contains(".jpg") || path.contains(".gif") ||
                path.contains(".png") || path.contains(".jpeg")) {
            mime = "image/*";
        }
        return mime;
    }

    public static void makeDir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public static boolean writeFile(ByteArrayOutputStream baos, String path) {
        try {
            File file = new File(path);
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(baos.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
