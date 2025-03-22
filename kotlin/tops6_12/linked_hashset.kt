package tops6_12

fun main()
{
    var set = LinkedHashSet<String>()
    set.add("utsav")
    set.add("rajan")
    set.add("bhumi")
    set.add("aditi")
    set.add("papu")
    set.add("smit")
    set.add("utsav")

    for(i in set)
    {
        println(i)
    }
}