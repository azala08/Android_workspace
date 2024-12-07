package task7_12


import task7_12.Student
import task7_12.Teacher

open class Person(var name:String, var age: Int)
{
    fun display()
    {
        println("Student Name is $name ")
        println("Student age is $age")
    }
}
class Student(name: String, age: Int,var grade:String ) : Person(name,age )
{
    fun stud()
    {
        println("Student grade: $grade")
    }
}
class Teacher(name: String,age:Int,var Subject:String) : Person(name,age)
{
    fun teacher()
    {
        println("Teacher name: $name")
        println("Teacher age $age")
        println("Subject: $Subject")
    }
}
class School
{
    companion object
    {
        private var TotalPeople = 0

    }

    fun addPerson(st:Student)
    {
        TotalPeople++
    }
    fun addPerson(t2:Teacher)
    {
        TotalPeople++
    }

    fun getTotalPeople(): Int
    {
        return TotalPeople
    }

}
fun main()
{
    var stu1 = Student("aditi", 20, "A")
    var stu2 = Student("Bhumi", 21, "B")
    var stu3 = Student("Janvi",22,"A+")

    var t1 = Teacher("Mrs. Dave", 45, "Java")
    var t2 = Teacher("Mr. Chauhan",36,"PHP")
    var t3 = Teacher("Mrs.Pandya",31,"IOS")

    var s1 = School()
    s1.addPerson(stu1)
    s1.addPerson(stu2)
    s1.addPerson(stu3)
    s1.addPerson(t1)
    s1.addPerson(t2)
    s1.addPerson(t3)

    println("Total num: "+s1.getTotalPeople())
    println("\nStudents:")
    stu1.display()
    stu2.display()
    stu3.display()

    println("\nTeachers:")
    t1.teacher()
    t2.teacher()
    t3.teacher()



}