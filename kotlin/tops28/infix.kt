package tops28

fun main()
{
    val x: Int = 16
    val y : Int = 10

    val greater = x greater y
    println(greater)

}
infix fun Int.greater(other : Int) : Int
{
    if(this > other)
        return this
    else
        return other
}