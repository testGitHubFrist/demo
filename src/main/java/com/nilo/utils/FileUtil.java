package com.nilo.utils;


import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2017/5/25.
 */
public class FileUtil {


    /**
     *
     * @return
     */
    public static String getFileUploadPath() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String path = "/" + String.valueOf(calendar.get(Calendar.YEAR))  + String.valueOf(calendar.get(Calendar.MONTH) + 1)
                 + String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + "/";
        return path;
    }

    public static String getFileUploadFileName() {

        UUID uid = UUID.randomUUID();

        return uid.toString();
    }

    public static String getFileSuffix(String fileName) throws FileNotFoundException {
        String[] strs = fileName.split("\\.");
        if (strs.length < 2) {
            throw new FileNotFoundException();
        } else {
            return "."+strs[1];
        }
    }


}
