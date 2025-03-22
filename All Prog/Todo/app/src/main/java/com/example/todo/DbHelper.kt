package com.example.todo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION)
{

    companion object
    {
        var DB_VERSION = 1
        var DB_NAME = "todo.db"
        var TABLE_NAME = "info"
        var ID = "id"
        var TASK_NAME = "tname"
        var TASK_DISC = "tdisk"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        var query ="CREATE TABLE " + TABLE_NAME + "("+ ID + " INTEGER PRIMARY KEY," + TASK_NAME + " TEXT,"+ TASK_DISC + " TEXT " +" ) "
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        var upquery ="DROP TABLE IF EXISTS " + TABLE_NAME
        db!!.execSQL(upquery)
        onCreate(db)
    }

    fun insertdata(m: Model): Long {
        var db = writableDatabase
        var contentvalues = ContentValues()
        contentvalues.put(TASK_NAME, m.Tname)
        contentvalues.put(TASK_DISC, m.Tdisc)

        var query = db.insert(TABLE_NAME, ID, contentvalues)
        return query
    }

    fun viewdata(): ArrayList<Model>
    {
        var list = ArrayList<Model>()
        var db =readableDatabase
        var data = arrayOf(ID, TASK_NAME, TASK_DISC)
        var cursor = db.query(TABLE_NAME, data, null, null, null, null, null)

        while (cursor.moveToNext())
        {
            var id = cursor.getInt(0)
            var tname = cursor.getString(1)
            var tdisc = cursor.getString(2)

            var m = Model()
            m.id = id
            m.Tname = tname
            m.Tdisc = tdisc
            list.add(m)
        }

        return list
    }

    fun deletedata(id:Int)
    {
        var db = writableDatabase
        var deletequery = ID+"="+id
        db.delete(TABLE_NAME, deletequery, null)
    }

    fun updatedata(m:Model)
    {
        var db = writableDatabase
        var contentvalues = ContentValues()
        contentvalues.put(ID,m.id)
        contentvalues.put(TASK_NAME,m.Tname)
        contentvalues.put(TASK_DISC,m.Tdisc)
        var updatequery = ID+"="+m.id
        db.update(TABLE_NAME,contentvalues,updatequery,null)

    }

}