package com.example.administrator.quickwebview.sdk;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2017/8/14.
 */
public class QuickConnection {

    private String url;

    public QuickConnection(String url) {
        this.url = url;
    }

    public InputStream getResource() {
        String md5URL = MD5.md5(url);
        // read cache from local
        FileInputStream fileCache = FileUtil.readFile(GlobalConfig.PROGRAM_PATH + md5URL);
        if (fileCache != null) {
            return fileCache;
        }
        //read cache from service
        return createConnection();
    }

    private InputStream createConnection() {

        try {
            URL uri = new URL(url);
            URLConnection connection = uri.openConnection();
            InputStream inputStream = connection.getInputStream();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            inputStream.close();
            //new Tread write cache to file
            String md5Url = MD5.md5(url);
            WriteFileThread thread = new WriteFileThread(GlobalConfig.PROGRAM_PATH + md5Url, baos);
            thread.start();
            return new ByteArrayInputStream(baos.toByteArray());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
