package com.ykai.engbot;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Created by ykai on 17/9/23.
 */
public class ReadUtils {

//    printLog(fileName + "english.txt", english + "\n");
//    printLog(fileName + "chinese.txt", chinese);


    public static String readEnglish() {


//        String tempPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "//overheat_log_temp.txt";
//        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "//overheat_log.txt";
        String tempPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "//english_log_temp.txt";

//        String tempPath = "/local/newlog/aplog/overheat_log_temp.txt";

//        String path = "/local/newlog/aplog/overheat_log.txt";


        File tempFile = new File(tempPath);


        if (tempFile.exists() && tempFile.isFile()) {

            BufferedReader reader = null;
            StringBuilder stringBuilder = new StringBuilder();

            try {
//                System.out.println("以行为单位读取文件内容，一次读一整行：");
                reader = new BufferedReader(new FileReader(tempFile));
                String tempString = null;
                int line = 1;
                // 一次读入一行，直到读入null为文件结束
                while ((tempString = reader.readLine()) != null) {
                    // 显示行号
                    stringBuilder.append(tempString).append("\n");

//                    System.out.println("line " + line + ": " + tempString);
                    line++;
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

            return stringBuilder.toString();
        }
        return "文件为空,请先录音";
    }

    public static String readChinese() {


//        String tempPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "//overheat_log_temp.txt";
//        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "//overheat_log.txt";
        String tempPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "//chinese_log_temp.txt";

//        String tempPath = "/local/newlog/aplog/overheat_log_temp.txt";

//        String path = "/local/newlog/aplog/overheat_log.txt";


        File tempFile = new File(tempPath);


        if (tempFile.exists() && tempFile.isFile()) {

            BufferedReader reader = null;
            StringBuilder stringBuilder = new StringBuilder();

            try {
//                System.out.println("以行为单位读取文件内容，一次读一整行：");
                reader = new BufferedReader(new FileReader(tempFile));
                String tempString = null;
                int line = 1;
                // 一次读入一行，直到读入null为文件结束
                while ((tempString = reader.readLine()) != null) {
                    // 显示行号
                    stringBuilder.append(tempString).append("\n");


//                    System.out.println("line " + line + ": " + tempString);
                    line++;
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

            return stringBuilder.toString();
        }
        return "文件为空,请先录音";
    }


}
