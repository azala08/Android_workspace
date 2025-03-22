package tops6_12

fun main()
{
    var list = ArrayList<String>()
    // var list2 = ArrayList<String>()

    list.add("a")//0
    list.add("b")//1
    list.add("c")//2

    list.add(2,"d")


    //list.add(2,"b")//1,2//not possible
//    list.add("b")
//    list.add("c")
//    list.add("d")
//    list.add("a")

    // println(list.get(0))

//    list2.add("p")
//    list2.add("q")
//    list2.add("r")
//    list2.add("s")
//    list2.add("t")
//
//    list.addAll(list2)


    for(i in list)
    {
        println("$i")
    }

}