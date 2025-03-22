package tops6_12

import java.util.LinkedList

fun main()
{
    var list = LinkedList<String>()

    // var list = Doub

//    list.add("a")
//    list.add("b")
//    list.add("c")
//    list.addFirst("d")
//    list.addLast("e")

    //Top to Bottom Approach

    list.addLast("ios")
    list.add("android")
    list.add("java")
    list.addFirst("php")
    list.add("tyu")




    for(i in list)
    {
        println("$i")
    }

}