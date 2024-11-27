package task21

fun main()
{
    println("Enter Any Character : ")
    var char = readln().toString()

    if(char.length==1)
    {
        if(char=="a" || char=="e" || char=="i" || char=="o" || char=="u" || char=="A" || char=="E" || char=="I" || char=="O" || char=="U")
        {
            println("Character is Vowel")
        }
        else
        {
            println("Character is Consonant")
        }
    }
    else
    {
        println("Enter any valid character")
    }
}




