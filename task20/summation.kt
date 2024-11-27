package task20

fun main()
{
    var num=1234
    var sum=0
    while(num!=0)
    {
        var rem=num%10
        sum+=rem
        num/=10
    }
    print(sum)
}