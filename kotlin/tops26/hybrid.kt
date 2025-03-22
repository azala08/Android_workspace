package tops26

open class A1
{
    fun a1()
    {
        println("a1 called")
    }
}
open class B1 : A1()
{
    fun b1()
    {
        println("b1 called")
    }
}
interface C1
{
    fun c1()
    {
        println("c1 called")
    }
}
class D1 : B1(),C1
{
    fun d1()
    {
        println("d1 called")
    }
}

fun main()
{
    var d = D1()

    d.a1()
    d.b1()
    d.c1()
    d.d1()
}
