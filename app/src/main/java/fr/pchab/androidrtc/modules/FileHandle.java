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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Date;


public class FileHandle {
  /*
    * Move File Function. @xieyinghua
    */
  public static boolean MoveFile(String SourcePath, String TargetPath,int Speed) {
    int ch;
    byte fileBuffer[] = new byte[Speed];
    FileInputStream fins;
    FileOutputStream fouts;
    File SourceFileList;
    File TargetFileList;

    try {
      SourceFileList = new File(SourcePath);
      TargetFileList = new File(TargetPath);

      if (SourceFileList.exists()) {
        fins = new FileInputStream(SourceFileList);
        fouts = new FileOutputStream(TargetFileList);

        ch = 0;
        while ( (ch = fins.read(fileBuffer)) != -1) {
          fouts.write(fileBuffer,0,ch);
        }

        fins.close();
        fouts.close();

        SourceFileList.delete();

        return true;
      }

      return false;
    }catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  
  /*
  * Copy File Function. @xieyinghua
  */
  public static boolean CopyFile(String SourcePath, String TargetPath,int Speed) {
    int ch;
    byte fileBuffer[] = new byte[Speed];
    FileInputStream fins;
    FileOutputStream fouts;
    File SourceFileList;
    File TargetFileList;

    try {
      SourceFileList = new File(SourcePath);
      TargetFileList = new File(TargetPath);

      if (SourceFileList.exists()) {
        fins = new FileInputStream(SourceFileList);
        fouts = new FileOutputStream(TargetFileList);

        ch = 0;
        while ( (ch = fins.read(fileBuffer)) != -1) {
          fouts.write(fileBuffer,0,ch);
        }

        fins.close();
        fouts.close();

        return true;
      }

      return false;
    }catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  
  /*
  * Make Directory Function. @xieyinghua
   */
  public static boolean MakeDirectory(String TargetPath) {
    File TargetFileList;

    try {
      TargetFileList = new File(TargetPath);
      TargetFileList.mkdirs();

      if (TargetFileList.exists()) {
        return true;
      }

      return false;
    }catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  
  /*
    * Command Line Version Move File. @xieyinghua
    */
  public static boolean CommandMoveFile(String SourcePath, String TargetPath) {
    try {
      Runtime.getRuntime().exec("move " + SourcePath + " " + TargetPath);
      return true;
    }catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  
  /*
    *   Command Line Version Copy File. @xieyinghua
    */
  public static boolean CommandCopyFile(String SourcePath, String TargetPath) {
    try {
        Runtime.getRuntime().exec("copy " + SourcePath + " " + TargetPath);
        return true;
    }catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  
  /*
    * One Line Write File. @xieyinghua
    */
  public static boolean WriteTextFile(String FilePath, String FileBody, boolean Append) {
    try {	
      BufferedWriter bw = null;
      File f = new File(FilePath);
	  FileWriter fw=new FileWriter(f,Append);
	  bw = new BufferedWriter(fw);
      
	  bw.write(FileBody);   
	  
	  bw.flush();
	  bw.close();
	
	  return true;
    }catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }


  /*
    * Division File Path Function, Split Path, Name, Type.
    * ChoiceRerutn 0 Will Return Path, 1 Will Return Name, 2 Will Return Type. @xieyinghua
    */
  public static String DivisionFilePath(String InputPath, int ChoiceReturn) {
    String Output_File_Path = "";
    String Output_File_Name = "";
    String Output_File_Class = "";

    int i;

    for (i = InputPath.length(); i > 0; i--) {
      if (InputPath.substring(i - 1, i).equals(".")) {
        Output_File_Name = InputPath.substring(0, i - 1);
        Output_File_Class = InputPath.substring(i, InputPath.length());
        break;
      }
    }

    for (i = Output_File_Name.length(); i > 0; i--) {
      if (Output_File_Name.substring(i - 1, i).equals("\\")) {
        Output_File_Path = InputPath.substring(0, i - 1);
        Output_File_Name = Output_File_Name.substring(i,Output_File_Name.length());
        break;
      }
    }

    switch (ChoiceReturn) {
      case 0:
        return Output_File_Path;
      case 1:
        return Output_File_Name;
      case 2:
        return Output_File_Class;
      default:
        return "";
    }
  }


  //Write Log File. @xieyinghua
  public static boolean WriteLog(String FilePath, String FileBody) {
    Date now = new Date();
    WriteTextFile(FilePath,now.toString()+" "+FileBody+"\n",true);
    return true;
  }
}
