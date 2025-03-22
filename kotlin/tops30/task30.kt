package tops30

open class Flower
{
    open fun rose()
    {
        println("rose..!")
    }
}
class Name : Flower()
{
    override fun rose()
    {
        println("rose..!")
      //  super.rose()
    }

    fun display()
    {
        rose()
        super.rose()
    }
}
fun main()
{
    val color = Name()
    color.display()

}
