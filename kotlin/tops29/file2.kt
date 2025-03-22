package tops29

import java.io.FileInputStream

fun main()
{
    var fin = FileInputStream("E://tops1.txt")
    var data = ByteArray(fin.available())
    fin.read(data)
    var str: String = String(data)
    println(str)


}