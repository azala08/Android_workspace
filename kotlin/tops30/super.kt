package tops30

open class A
{
    open var color = "black\n"
}
class B : A()
{
    override var color = "white\n"

    fun display()
    {
        print(color)
        print(super.color)
    }
}
fun main()
{
    var b = B()
    b.display()
}