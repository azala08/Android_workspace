package tops29

import java.lang.Integer.parseInt

fun main()
{
    // var data = intArrayOf(10,20)

    var data = IntArray(5)
    //var a = Array
    for(i in 0..data.size - 1)
    {
        println("Enter Marks for student "+i+" : ")
        var d1= readln().toString()
        data[i] = parseInt(d1)
    }

    for(i in 0..data.size - 1)
    {
        println("Your Marks "+data[i])
    }




}