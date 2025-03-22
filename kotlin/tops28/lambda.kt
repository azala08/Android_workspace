package tops28

class MyFirstClass1
{
    fun addTwoNum(a: Int , b : Int, action:(Int)->Unit)
    {
        val sum = a+b
        //println(sum)
        action(sum)
    }
}

fun main()
{
    var m1 = MyFirstClass1()

    val myLambda : (Int) -> Unit=
        {
                s : Int ->
            println(s)
        }
    m1.addTwoNum(6,5,myLambda)

}