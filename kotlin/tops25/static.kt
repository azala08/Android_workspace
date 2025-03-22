package tops25

class Account
{
    companion object
    {
        var counter = 0
    }
    fun display()
    {
        counter++
        println(counter)
    }
}
fun main()
{
    var a1 = Account()
    var a2 = Account()
    var a3 = Account()

    a1.display()
    a2.display()
    a3.display()
}