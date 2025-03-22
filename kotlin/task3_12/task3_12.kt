package task3_12

class Sender
{
    fun send(msg: String)
    {
        println("Sending $msg")
        println("$msg Sent")
    }
}
class ThreadSend(val sender: Sender,  val msg: String) : Runnable
{
    override fun run()
    {
        synchronized(sender)
        {
            sender.send(msg)
        }
    }
}
fun main() {
    val sender = Sender()

    val t1 = ThreadSend(sender, "Hii")
    val t2 = ThreadSend(sender, "Bye")

    val r1 = Thread(t1)
    val r2 = Thread(t2)

    r1.start()
    r2.start()
}
