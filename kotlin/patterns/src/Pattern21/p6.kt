package Pattern21

fun main()
{
    val last = 'E'
    var alphabet = 'A'

    for (i in 1..last - 'A' + 1)
    {
        for (j in 1..i)
        {
            print("$alphabet ")
        }
        alphabet++

        println()
    }
}