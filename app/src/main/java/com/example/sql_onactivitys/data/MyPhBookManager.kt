package com.example.sql_onactivitys.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class MyPhBookManager(context: Context) {
    val dbHelper = MyPhBookHelper(context)
    var db: SQLiteDatabase? = null

    fun openBD() {
        db = dbHelper.writableDatabase
    }

    fun closeDB() {
        dbHelper.close()
    }

    fun insertDB(name: String, phone: String, uri: String) {
        val values = ContentValues().apply {
            put(MyDataBase.COLUMN_NAME_NAME, name)
            put(MyDataBase.COLUMN_NAME_PHONE, phone)
            put(MyDataBase.COLUMN_NAME_IMAGE_URI, uri)
        }
        db?.insert(MyDataBase.TABLE_NAME, null, values)
    }

    fun readDB(): ArrayList<String> {

        val dataList = ArrayList<String>()

        val cursor = db?.query(MyDataBase.TABLE_NAME, null, null,null, null, null, null)

        while (cursor?.moveToNext()!!) {
//            val dataText = cursor.getString(cursor.getColumnIndexOrThrow(MyDataBase.COLUMN_NAME_NAME)) +
//                    cursor.getString(cursor.getColumnIndexOrThrow(MyDataBase.COLUMN_NAME_PHONE)) +
//                    cursor.getString(cursor.getColumnIndexOrThrow(MyDataBase.COLUMN_NAME_IMAGE_URI))
            val dataText = cursor.getString(cursor.getColumnIndexOrThrow(MyDataBase.COLUMN_NAME_NAME))

            dataList.add(dataText.toString())
        }
        cursor.close()
        return dataList
    }


}