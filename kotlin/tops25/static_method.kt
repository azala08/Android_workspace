package tops25

class stu
{
    var id:Int=0
    var name:String=""
    var college:String="NMC"

    constructor(id:Int,name:String)
    {
        this.id=id
        this.name=name
    }
    fun display()
    {
        print("\nYour ID is $id , name is $name , college is $college")
    }
}
fun main()
{
    var s1 = stu(101,"aditi")
    var s2 = stu(102,"janvi")

    s1.display()
    s2.display()
}