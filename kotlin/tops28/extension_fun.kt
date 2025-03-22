package tops28

class MyFirstClass
{
    fun hasPassed(marks : Int) : Boolean
    {
        return marks > 40
    }
}
fun main()
{
    var m = MyFirstClass()
    println("Pass Status: "+m.hasPassed(91))
    println("Scholarship Status: "+m.isscholar(91))



}

fun MyFirstClass.isscholar(marks : Int):Boolean
{
    return marks > 90
}