package com.ykai.engbot;

import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ykai on 17/9/23.
 */
public class MyPrintLogUtil {

    public static void printEnglishLog(String content) {
        content += "\n";

        String tempPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "//english_log_temp.txt";
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "//english_log.txt";

//        String tempPath = "/local/newlog/aplog/overheat_log_temp.txt";

//        String path = "/local/newlog/aplog/overheat_log.txt";


        File tempFile = new File(tempPath);

        File file = new File(path);


        if (tempFile.exists() && tempFile.isFile()) {

            if (tempFile.length() >= 1024 * 1024) {

                if (file.isFile() && file.exists()) {

                    file.delete();

                }

                tempFile.renameTo(file);

            }

        }


        FileWriter fw = null;

        long beginTime = System.currentTimeMillis();




        try {

            fw = new FileWriter(tempPath, true);

            fw.write( content);

            fw.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public static void printChineseLog(String content) {

        String tempPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "//chinese_log_temp.txt";

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "//chinese_log.txt";

//        String tempPath = "/local/newlog/aplog/overheat_log_temp.txt";

//        String path = "/local/newlog/aplog/overheat_log.txt";


        File tempFile = new File(tempPath);

        File file = new File(path);


        if (tempFile.exists() && tempFile.isFile()) {

            if (tempFile.length() >= 1024 * 1024) {

                if (file.isFile() && file.exists()) {

                    file.delete();

                }

                tempFile.renameTo(file);

            }

        }


        FileWriter fw = null;

        long beginTime = System.currentTimeMillis();




        try {

            fw = new FileWriter(tempPath, true);

            fw.write( content);

            fw.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


}
