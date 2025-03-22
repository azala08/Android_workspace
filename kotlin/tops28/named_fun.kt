package tops28

class Shape
{
    fun area(length : Int , breadth: Int , height : Int=10)
    {
        println("length is " + length )
        println("breadth is " + breadth)
        println("height is " + height)
    }

}
fun main()
{
    var s = Shape()
    s.area(breadth = 10, height = 20, length = 30)
}