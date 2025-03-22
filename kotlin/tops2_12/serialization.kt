package tops2_12

import java.io.FileOutputStream
import java.io.ObjectOutputStream

fun main()
{

    var s1 = Student(101,"aditi")
    var fout = FileOutputStream("D://topstech.txt")
    var out = ObjectOutputStream(fout)
    out.writeObject(s1)
    println("success")

}