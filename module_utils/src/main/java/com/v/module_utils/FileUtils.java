package com.v.module_utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by 34556 on 2017/3/10.
 */
public class FileUtils {
    public static String getFromAssets(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(Utilities.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            //正常容错逻辑，不需要上报
            e.printStackTrace();
        }
        return "";
    }
}
