package tops11_12

import java.util.*
import kotlin.collections.ArrayList

//objects comapare with it's self

class Student :Comparable<Student>
{
    var sid:Int=0
    var sname:String=""

    constructor(sid:Int,sname:String)
    {
        this.sid=sid
        this.sname=sname
    }

    override fun compareTo(other: Student): Int
    {
        var value = 0

        if(this.sid>other.sid)
        {
            return 1
        }
        else if(this.sid<other.sid)
        {
            return -1
        }
        else if(this.sid==other.sid)
        {
            return 0
        }

        return value
    }

}
fun main()
{
    var s1 = Student(15, "Parth")
    var s2 = Student(10, "Jay")
    var s3 = Student(1, "Dhaval")
    var s4 = Student(12, "RAhul")
    var s5 = Student(7, "Tapan")

    var list = ArrayList<Student>()
    list.add(s1)
    list.add(s2)
    list.add(s3)
    list.add(s4)
    list.add(s5)

    Collections.sort(list)

    for(s:Student in list)
    {
        println("${s.sid} and ${s.sname}")
    }

}