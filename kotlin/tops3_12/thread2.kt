package tops3_12

class A1 : Runnable
{
    override fun run()
    {
        for(i in 1..10)
        {
            println("Thread A1: $i")
        }
    }
}
class B1 : Runnable
{
    override fun run()
    {
        for (i in 1..10)
        {
            println("Thread B1: $i")
        }
    }
}
fun main()
{
    var a = A1()
    var b = B1()

    //a.start()
    //b.start()
    var t1 = Thread(A1())
    var t2 = Thread(B1())

    t1.start()
    t2.start()
    //a.stop()
    //b.stop()
}