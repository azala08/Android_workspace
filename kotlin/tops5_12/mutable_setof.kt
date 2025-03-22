package tops5_12

fun main()
{
    var data = mutableSetOf<String>("aditi","vivek","utsav","aditi")
    var data2 = mutableSetOf<String>("a","b","c","d","aditi")

    //data.add("xyz")
    data.addAll(data2)
    data.remove("a")
    data.removeAll(data2)
    //data.removeIf()
    data.retainAll(data2)
    println(data.size)

    for(i in data)
    {
        println(i)
    }

}