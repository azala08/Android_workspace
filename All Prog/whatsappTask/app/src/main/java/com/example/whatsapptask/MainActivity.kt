package com.example.whatsapptask
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.contactsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val contacts = listOf(
            Contact("Craig", R.drawable.avatar1, "Supp", "9:00 am"),
            Contact("Sergio", R.drawable.avatar2, "Let's Catchup", "7:34 pm"),
            Contact("Mubariz", R.drawable.avatar3, "Dinner tonight?", "6:32 am"),
            Contact("Mike", R.drawable.avatar4, "Gotta go", "5:76 am"),
            Contact("Michael", R.drawable.avatar5, "I'm in meeting", "5:00 am"),
            Contact("Toa", R.drawable.avatar6, "Gotcha", "7:34 pm"),
            Contact("Ivana", R.drawable.avatar7, "", "2:32 am")
        )

        recyclerView.adapter = ContactAdapter(contacts)
    }
}