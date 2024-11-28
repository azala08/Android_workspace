package task27

// Parent class
open class Shape
{
    companion object
    {
        const val a = 5
        const val b = 7
    }
}

// 1st child class
class Rectangle : Shape()
{
    fun rec(): Int
    {
        return a * b
    }
}

// 2nd child class
class Triangle : Shape()
{
    fun tri(): Double
    {
        return 0.5 * a * b
    }
}
fun main()
{
    val rectangle = Rectangle()
    val rec = rectangle.rec()
    println("area of rectangle: $rec")

    val triangle = Triangle()
    val tri = triangle.tri()
    println("area of triangle: $tri")
}
