package task29

import java.io.FileInputStream
import java.io.FileOutputStream

fun main()
{
    println("Enter your name:")
    val name = readln().toString()

    println("Enter your E-mail:")
    val email = readln().toString()

    println("Enter password:")
    val pass = readln().toString()

    println("Enter confirm password:")
    val cpass = readln().toString()

    if(pass==cpass)
    {
        //println("Your Name : $name")
        //println("Your E-mail : $email")

        var fout = FileOutputStream("D://tops1.txt")

        fout.write("Your Name:  $name\n".toByteArray())
        fout.write("Your E-mail: $email".toByteArray())

        println("success")

        var fin = FileInputStream("D://tops1.txt")
        var data = ByteArray(fin.available())
        fin.read(data)
        var str: String = String(data)
        println(str)


    }
    else
    {
        println("Your password is incorrect...!")
    }
}








