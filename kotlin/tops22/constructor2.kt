package tops22

class stu
{
    constructor(name:String,surname:String,email:String,mob:String)
    {
        print("--------------------------------")
        println("your name is $name")
        println("your surname is $surname")
        println("your email is $email")
        println("your mob is $mob")
    }
    constructor(name:String,surname:String)
    {
        print("--------------------------------")
        println("your name is $name")
        println("your surname is $surname")
    }
    constructor(name:String,surname: String,email: String)
    {
        print("--------------------------------")
        println("your name is $name")
        println("your surname is $surname")
        println("your email is $email")
    }
    constructor(name:String,surname: String,mob:Double)
    {
        print("--------------------------------")
        println("your name is $name")
        println("your surname is $surname")
        println("your mob is $mob")
    }
}
fun main()
{
    var s1 = stu("a","b","abc@gmail.com","1234")
    var s2 = stu("a","b")
    var s3 = stu("a","b","abc@gmail.com")
    var s4 = stu("a","b","1234")
}



