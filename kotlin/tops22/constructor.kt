package tops22

class emp(var name:String,var surname:String)
{
    fun display()
    {
        println("your name is ${name}")
        println("Your surname is ${surname}")
    }
}
fun main()
{
    var e1 = emp("Aditi","Zala")
    var e2 = emp("Janvi","Pandya")

    e1.display()
    e2.display()
}