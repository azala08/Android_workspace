package tops2_12

import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

fun main()
{
    var fin = FileInputStream("D://topstech.txt")
    var oin = ObjectInputStream(fin)
    var s2: Student = oin.readObject() as Student
    println("Your id is ${s2.id} and Your Name is ${s2.name}")
//    println(s2.id)
//    println(s2.name)

}