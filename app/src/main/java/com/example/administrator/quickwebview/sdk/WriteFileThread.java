package com.example.administrator.quickwebview.sdk;

import java.io.ByteArrayOutputStream;

/**
 * Created by Administrator on 2017/8/15.
 */
public class WriteFileThread extends Thread implements Runnable {

    private ByteArrayOutputStream baos;
    private String path;

    public WriteFileThread(String path, ByteArrayOutputStream baos) {
        this.baos = baos;
        this.path = path;
    }

    @Override
    public void run() {

        if (FileUtil.writeFile(baos, path)) {
            System.out.println("write file success! ");
        } else {
            System.out.println("write file fail! ");
        }
    }
}
