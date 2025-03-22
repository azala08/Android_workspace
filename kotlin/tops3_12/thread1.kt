package tops3_12


class A : Thread()
{
    override fun run()
    {
        for(i in 1..10)
        {
            println("Thread A: $i")
        }
    }
}
class B : Thread()
{
    override fun run()
    {
        for(i in 1..10)
        {
            println("Thread B: $i")
        }
    }
}
fun main()
{
    var a = A()
    var b = B()

    a.start()
    b.start()

    //a.stop()
    //b.stop()
}