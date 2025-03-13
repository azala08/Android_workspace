package Assignment1

fun main()
{
    var a=1
    var sum=0
    while (a <= 100)
    {
        if(a%2==0)
        {
            sum+=a
        }
        a++
    }
    print("Sum Of All Even Numbers Between 1 to 100 : ")
    print(sum)

}