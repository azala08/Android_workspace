package Pattern21

fun main()
{
    val rows = 5

    for (i in rows downTo 1)
    {
        for (j in 1..i)
        {
            print("$j ")
        }
        println()
    }
}