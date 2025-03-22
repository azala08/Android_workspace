package tops2_12

import java.io.FileInputStream
import java.io.ObjectInputStream

fun main()
{
    var fin = FileInputStream("D://topstech1.txt")
    var oin = ObjectInputStream(fin)
    var s2: Stu = oin.readObject() as Stu
    println("Your id is ${s2.sid} and Your Name is ${s2.sname}")

}

