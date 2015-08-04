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

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by xieyinghua
 */
public class DBHandle extends SQLiteOpenHelper {
    /*
    * add class object @xieyinghua
    */
    private SQLiteDatabase sqlitedb;
    private static final String LOCAL_DBSID="private.db";
    private static final int LOCAL_DBVERSION=1;


    /*
        *constructor, load DBSID and DBVERSION too @xieyinghua
        */
    public DBHandle(Context context) {
        super(context,LOCAL_DBSID, null, LOCAL_DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }


    /*
    * open DB @xieyinghua
    */
    public boolean Open() throws SQLException {
        sqlitedb=this.getWritableDatabase();

        return true;
    }


    /*
    * close DB @xieyinghua
    */
    public boolean Close(){
        sqlitedb.close();
        this.close();

        return true;
    }


    /*
    * execute SQL statment @xieyinghua
    */
    public boolean Execute(String sql){
        sqlitedb.execSQL(sql);

        return true;
    }


    /*
    * query SQL statment. @xieyinghua
    */
    public String Query(String sql){
        int i;
        String res="";
        Cursor cursor = sqlitedb.rawQuery(sql, null);

        if (cursor.getCount()>0 && cursor.moveToFirst()) {
            do{
                res += cursor.getString(0);
                for(i=1;i<cursor.getColumnCount();i++) {
                    res += (","+cursor.getString(i));
                }
                res+="\n";
            }while(cursor.moveToNext());

            return res;
        }else{
            return null;
        }
    }


    /*
    * create table @xieyinghua
    */
    public boolean CreateTable(String tableName, String fieldData, String fieldType, String fieldIsNull, int fieldCount){
        int i;
        String sql;
        String[] fieldDataArr = fieldData.split(",");
        String[] fieldTypeArr = fieldType.split(",");
        String[] fieldIsNullArr = fieldIsNull.split(",");

        sql = "create table "+tableName+"(";
        sql+=(fieldDataArr[0]+" "+fieldTypeArr[0]+" "+fieldIsNullArr[0]);
        for(i=1;i<fieldCount;i++) {
            //example: "userid TEXT NOT NULL"
            sql+=(","+fieldDataArr[i]+" "+fieldTypeArr[1]+" "+fieldIsNullArr[i]);
        }
        sql += ")";

        //TIMESTAMP
        sqlitedb.execSQL(sql);

        return true;
    }


    /*
    * drop table
    */
    public boolean DropTable(String tableName){
        String sql;
        sql = "drop table if exists "+tableName+";";
        sqlitedb.execSQL(sql);

        return true;
    }


    /*
    * check if the table exist
    */
    public boolean isTableExists(String tableName, boolean openDb) {
        String sql;
        sql="select DISTINCT tbl_name from sqlite_master where tbl_name = '"+tableName+"'";

        /*if(openDb) {
                    if(sqlitedb == null || !sqlitedb.isOpen()) {
                        sqlitedb = dbhelper.getReadableDatabase();
                    }

                    if(!sqlitedb.isReadOnly()) {
                        sqlitedb.close();
                        sqlitedb = dbhelper.getReadableDatabase();
                    }
                }*/

        Cursor cursor = sqlitedb.rawQuery(sql, null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }

        return false;
    }
}
