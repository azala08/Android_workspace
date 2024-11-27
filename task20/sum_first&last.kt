package task20

fun main ()
{
    var num=3572
    var rem=0
    var first=0
    var sum=0

    var last=num%10

    while(num>0)
    {
        rem=num%10
        first=num
        num/=10
    }

    sum=first+last

    print("Sum Of First Digit and Last Digit Is : $sum")
}