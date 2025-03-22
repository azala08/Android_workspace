package tops27

class calculation
{
    fun cal(a:Int,b:Int) : Int
    {
        return a+b
    }
    fun cal(a:Int,b:Int,c:Int) : Int
    {
        return a*b*c
    }
}
fun main()
{
    var c = calculation()
    println(c.cal(3,5))
    println(c.cal(5,6,7))
}