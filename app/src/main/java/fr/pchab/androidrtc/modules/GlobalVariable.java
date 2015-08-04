/*
 * Copyright 2015 Xie Yinghua
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.pchab.androidrtc.modules;

import android.app.Activity;
import android.app.Application;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xieyinghua
 */
public class GlobalVariable extends Application {
    /*
        * add activity object @xieyinghua
        */
    public static String ID = "";
    public static String PW = "";
    public static String PatientID = "";

    public static String sdPath = null;
    public static String appPath = null;
    public static String videoPath = null;
    public static String logPath = null;
    public static String logFile = null;

    /*
        * pubic function, kill activicy or other etc... @xieyinghua
        */
    public static List<Activity> activityList=new ArrayList<Activity>();
    public static boolean KillAllActivity(){
        boolean result=false;
        int i;
        int len=activityList.size();
        for(i=0;i<len;i++){
            if(activityList.get(i)!=null){
                activityList.get(i).finish();
                result=true;
            }
        }

        return result;
    }


    /*
        * pubic function, CreateAppPath... @xieyinghua
        */
    public static boolean CreateAppPath(){
        //make sure directory is exist. @xieyinghua
        if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            sdPath = Environment.getExternalStorageDirectory().toString();
        }
        appPath = sdPath + File.separator + "SG_SurgeryTeacher" + File.separator;
        FileHandle.MakeDirectory(appPath);
        videoPath = appPath + "Video" + File.separator;
        FileHandle.MakeDirectory(videoPath);
        logPath = appPath + "Log" + File.separator;
        FileHandle.MakeDirectory(logPath);

        //made file name by user name and date time. @xieyinghua
        Date now = new Date();
        logFile=logPath+(new java.text.SimpleDateFormat("yyyyMMdd").format(now)).toString()+".log";

        return true;
    }

}