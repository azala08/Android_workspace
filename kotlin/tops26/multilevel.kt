package tops26

open class A
{
    fun a()
    {
        println("a called")
    }
}
open class B : A()
{
    fun b()
    {
        println("b called")
    }
}
class C : B()
{
    fun c()
    {
        println("c called")
    }
}


fun main()
{
    var c = C()
    c.c()
    c.a()
    c.b()
}