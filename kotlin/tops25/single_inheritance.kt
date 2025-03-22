package tops25

//final class not be inherited
open class A1
{
    fun a1()
    {
        println("a1 called")
    }
}
class B1 : A1()
{
    fun b1()
    {
        println("b1 called")
    }
}
fun main()
{
    var b = B1()

    b.a1()
    b.b1()
}
