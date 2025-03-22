package tops4_12

fun main()
{
    var mylist = mutableListOf<String>("a","b","c","d")
    var mylist2 = mutableListOf<String>("a","p","q","r")

    //mylist.add("e")
    //mylist.remove("a")
    //mylist.removeAt(2)

    //mylist.retainAll(mylist2)  // find commen data from 2 list
    mylist.addAll(mylist2)
    for(i in mylist)
    {
        println(i)
    }
}