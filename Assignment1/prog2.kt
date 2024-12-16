package Assignment1

open class Animal
{
    fun makesound()
    {
        println("Dog barks...")
    }
    fun makesoundd()
    {
        println("Cat sounds like meoww...")
    }
}

class Dog : Animal()
{
    fun dog()
    {
        println("I am Dog...")
    }
}

class Cat : Animal()
{
    fun cat()
    {
        println("I am Cat...")
    }
}
fun main()
{
    var d=Dog()
    var c=Cat()

    d.dog()
    d.makesound()

    println("========================")

    c.cat()
    c.makesoundd()
}