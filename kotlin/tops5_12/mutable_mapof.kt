package tops5_12

fun main()
{
    var data = mutableMapOf<String,String>("aditi" to "1","vivek" to "2","utsav" to "3","aditi" to "4")

    data.put("utsav","4")
    print(data.get("utsav"))
//    for(i in data)
//    {
//        println(i)
//    }

}