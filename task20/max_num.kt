package task20

fun main()
{
    var num=3752
    var max=0
    var rem=0
    while(num>0)
    {
        rem = num%10

        if(rem>max)
        {
            max=rem
        }
        num = num/10
    }
    println("max number is $max")

}