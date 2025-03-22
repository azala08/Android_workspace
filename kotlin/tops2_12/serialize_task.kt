package tops2_12

import java.io.FileOutputStream
import java.io.ObjectOutputStream

fun main()
{

    println("Enter your id:")
    var sid = readln().toInt()
    println("Enter your name:")
    var sname = readln().toString()

    var st=Stu(sid,sname)
    var fout = FileOutputStream("D://topstech1.txt")
    var out = ObjectOutputStream(fout)
    out.writeObject(st)
    println("success")

}