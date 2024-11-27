package task20

fun main()
{
    var num1=0
    var num2=1
    var element=10

    print("$num1, $num2")

    var i=2
    while(i<element)
    {
        var num3=num1+num2
        print(", $num3")
        num1=num2
        num2=num3
        i++
    }
}