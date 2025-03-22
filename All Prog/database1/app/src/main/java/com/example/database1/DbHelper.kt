package com.example.database1


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context:Context) : SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION)
{
    companion object
    {
        var DB_NAME="tops.db"
        var TABLE_NAME="students"
        var ID="id"
        var NAME="name"
        var NUM="num"
        var DB_VERSION = 1
    }


    override fun onCreate(p0: SQLiteDatabase?)
    {
        var query = "CREATE TABLE "+ TABLE_NAME+"( "+ ID +" int primary key , "+ NAME + " text , "+ NUM + " text "+" ) "
        p0!!.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int)
    {
        var upquery = "DROP TABLE if exists "+ TABLE_NAME
        p0!!.execSQL(upquery)
        onCreate(p0)
    }

    fun insertdata(m:Model) : Long
    {
        var db = writableDatabase
        var values = ContentValues()//it use when you want to insert data
        values.put(NAME,m.name)
        values.put(NUM,m.num)
        var a = db.insert(TABLE_NAME,ID,values)

        return a
    }


    fun viewdata() : ArrayList<Model>
    {
        var db = readableDatabase
        var colarray = arrayOf(ID, NAME, NUM)
        var cursor = db.query(TABLE_NAME,colarray,null,null,null,null,null,null)//select * from tablename
        var arraylist = ArrayList<Model>()
        while(cursor.moveToNext())
        {
            var id = cursor.getInt(0)
            var name = cursor.getString(1)
            var num = cursor.getString(2)

            var m = Model()
            m.id=id
            m.name=name
            m.num=num

            arraylist.add(m)

        }
        return arraylist
    }

    fun deletedata(m:Model) : Long
    {
        var db = writableDatabase
        var values = ContentValues()//it use when you want to insert data
        values.put(NAME,m.name)
        values.put(NUM,m.num)
        var a = db.insert(TABLE_NAME,ID,values)

        return a
    }


    fun updata(m:Model) : Long
    {
        var db = writableDatabase
        var values = ContentValues()//it use when you want to insert data
        values.put(NAME,m.name)
        values.put(NUM,m.num)
        var a = db.insert(TABLE_NAME,ID,values)

        return a
    }


}