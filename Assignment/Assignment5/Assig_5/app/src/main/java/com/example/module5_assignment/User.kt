package com.example.module5_assignment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class User
{
    @PrimaryKey(autoGenerate = true)
    var id:Int=0

    @ColumnInfo(name = "username")
    var name:String=""

    @ColumnInfo(name="useremail")
    var email:String=""


}