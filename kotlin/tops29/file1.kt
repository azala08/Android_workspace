package tops29

import java.io.FileOutputStream

fun main()
{

    var data = "Welcome to tops"
    var fout = FileOutputStream("D://tops2.txt")
    fout.write(data.toByteArray())
    println("success")

}