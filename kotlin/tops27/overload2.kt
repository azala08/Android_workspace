package tops27

class calculationn
{
    fun cal(a:Int,b:Int) : Int
    {
        return a+b
    }
    fun cal(a:Double,b:Double) : Double
    {
        return a*b
    }
}
fun main()
{
    var c = calculationn()
    println(c.cal(3,5))
    println(c.cal(5,6))

}