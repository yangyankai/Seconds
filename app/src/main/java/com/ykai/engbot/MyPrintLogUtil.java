package com.ykai.engbot;

import android.os.Environment;
import android.os.Message;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

            fw.write(content);

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

            fw.write(content);

            fw.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    public static void printTopic1(String content) {
        content += "\n";

        String tempPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "//top1_log_temp.txt";
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "//top1_log.txt";

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

            fw.write(content);

            fw.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    public static String readTopic1() {

        String tempPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "//top1_log_temp.txt";


        File tempFile = new File(tempPath);
        StringBuilder stringBuilder = new StringBuilder();



        if (tempFile.exists() && tempFile.isFile()) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(tempFile));
                String tempString = null;
//                        line = 1;
                while ((tempString = reader.readLine()) != null) {
                    stringBuilder.append(tempString).append("\n");
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                    }
                }
            }
        }

        return stringBuilder.toString();
    }



    public static String readTopic2( ) {

        String tempPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "//top2_log_temp.txt";


        File tempFile = new File(tempPath);
        StringBuilder stringBuilder = new StringBuilder();



        if (tempFile.exists() && tempFile.isFile()) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(tempFile));
                String tempString = null;
//                        line = 1;
                while ((tempString = reader.readLine()) != null) {
                    stringBuilder.append(tempString).append("\n");
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                    }
                }
            }
        }

        return stringBuilder.toString();
    }



    public static void printTopic2(String content) {
        content += "\n";

        String tempPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "//top2_log_temp.txt";
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "//top2_log.txt";

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

            fw.write(content);

            fw.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    public static void printTopic3(String content) {
        content += "\n";

        String tempPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "//top3_log_temp.txt";
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "//top3_log.txt";

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

            fw.write(content);

            fw.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    public static void printTopic4(String content) {
        content += "\n";

        String tempPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "//top4_log_temp.txt";
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "//top4_log.txt";

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

            fw.write(content);

            fw.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


}
