package tops26

open class Bank
{
    fun bank()
    {
        println("banking")
    }
}
class Current : Bank()
{
    fun current()
    {
        println("current")
    }
}
class Save : Bank()
{
    fun save()
    {
        println("saving")
    }
}



fun main()
{

}
