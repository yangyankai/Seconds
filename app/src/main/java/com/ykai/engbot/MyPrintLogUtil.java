package com.ykai.engbot;

import android.os.Environment;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.ykai.engbot.entity.RecordEntity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ykai on 17/9/23.
 */
public class MyPrintLogUtil {

    public static String APP_PATH="";

    public static void initAppPath(){

        String androidRootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String appPath = androidRootPath +File.separator+"Seconds";
        APP_PATH =appPath;
        //新建一个File，传入文件夹目录
        File file = new File(appPath);
//判断文件夹是否存在，如果不存在就创建，否则不创建
        if (!file.exists()) {
            //通过file的mkdirs()方法创建<span style="color:#FF0000;">目录中包含却不存在</span>的文件夹
            file.mkdirs();
        }
    }

    public static void createFileDirectory(String path){
        File file = new File(path);
//判断文件夹是否存在，如果不存在就创建，否则不创建
        if (!file.exists()) {
            //通过file的mkdirs()方法创建<span style="color:#FF0000;">目录中包含却不存在</span>的文件夹
            file.mkdirs();
        }
    }

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

//  save topic
    public static void printTopic(String content) {
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

    //  save topic
    public static void printTopic(String content,String path) {
        content += "\n";
        path += File.separator+"重点.txt";
        FileWriter fw = null;

        try {

            fw = new FileWriter(path, true);

            fw.write(content);

            fw.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public static String readTopic(String path) {

        path += File.separator+"重点.txt";

//        String tempPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "//top1_log_temp.txt";


        File tempFile = new File(path);
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


// save result
    public static void printResultContent(String content) {
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

    public static void printResultContent(String content,String path) {
        content += "\n";
path+=File.separator+"处理结果.txt";
        FileWriter fw = null;

        try {

            fw = new FileWriter(path, true);

            fw.write(content);

            fw.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public static String readResultContent( String path) {

        path+=File.separator+"处理结果.txt";
        File tempFile = new File(path);
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


    public static void moveMeetingContent(String currentMeetingPath) {

        String tempPath = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator + "chinese_log_temp.txt";
        FileOperator.moveFile(tempPath,currentMeetingPath);
        tempPath = Environment.getExternalStorageDirectory().getAbsolutePath() +File.separator+ "english_log_temp.txt";
        FileOperator.moveFile(tempPath,currentMeetingPath);
    }

    public static ArrayList<RecordEntity> GetFiles()  //搜索目录，扩展名，是否进入子文件夹
    {
        ArrayList<RecordEntity> list = new ArrayList<>();

        String path =Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"Seconds";

        File[] files = new File(path).listFiles();

        for (int i = 0; i < files.length; i++)
        {
            File f = files[i];

            if (f.isDirectory())  //忽略点文件（隐藏文件/文件夹）
            {
                String strPath = f.getName();
                if(null!=strPath&& strPath.contains("-")&& strPath.length()>20){
                    String [] times= strPath.split("-");
                    if(times.length == 2){
                        RecordEntity entity = new RecordEntity();
                        entity.beginTimeStamp = times[0];
                        entity.endTimeStamp = times[1];
                        list.add(entity);
                    }
                }


            }

        }
        return list;
    }



}
