package com.example.jsoncrudvolly

import android.app.VoiceInteractor
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputBinding
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.privacysandbox.tools.core.model.Method
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.jsoncrudvolly.databinding.ActivityMainBinding
import org.json.JSONArray

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    lateinit var list: MutableList<Model>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        list = ArrayList()
        registerForContextMenu(binding.recycler)

        var stringRequest = StringRequest(Request.Method.GET,"https://prakrutitech.buzz/CRUD/view.php",object:Response.Listener<String>{
            override fun onResponse(response: String?)
            {
                var jsonArray = JSONArray(response)
                for(i in 0 until jsonArray.length())
                {
                    var jsonObject = jsonArray.getJSONObject(i)

                    var name = jsonObject.getString("name")
                    var surname = jsonObject.getString("surname")
                    var email = jsonObject.getString("email")
                    var pass = jsonObject.getString("password")

                    var m = Model()
                    m.name=name
                    m.surname=surname
                    m.email=email
                    m.pass=pass

                    list.add(m)
                }

                var adapter = MyAdapter(applicationContext,list)
                binding.recycler.adapter=adapter

            }
        },object:Response.ErrorListener{
            override fun onErrorResponse(error: VolleyError?)
            {
                Toast.makeText(applicationContext, "No Internet", Toast.LENGTH_SHORT).show()

            }
        })
        var queue: RequestQueue = Volley.newRequestQueue(this)
        queue.add(stringRequest)


        binding.f1.setOnClickListener {

            startActivity(Intent(applicationContext,AddActivity::class.java))

        }

    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?)
    {
        menuInflater.inflate(R.menu.ud,menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean
    {

        var acm: AdapterView.AdapterContextMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
        var id =acm.position
        var m = list[id]
        //Toast.makeText(applicationContext, ""+data, Toast.LENGTH_SHORT).show()

        when(item.itemId)
        {
            R.id.update->
            {
                var i = Intent(applicationContext,UpdateActivity::class.java)
                i.putExtra("id",m.id)
                i.putExtra("name",m.name)
                i.putExtra("surname",m.surname)
                i.putExtra("email",m.email)
                i.putExtra("password",m.pass)
                startActivity(i)


                //Toast.makeText(applicationContext,"UPDATE",Toast.LENGTH_SHORT).show()
            }
        }

        when(item.itemId)
        {
            R.id.delete->
            {
                var alert = AlertDialog.Builder(this)
                alert.setTitle("Are you sure you want to delete?")
                alert.setPositiveButton("YES",
                    { dialogInterface: DialogInterface, i: Int ->

                    var stringrequest:StringRequest = object :StringRequest(Request.Method.POST,"https://prakrutitech.buzz/CRUD/delete.php",
                        Response.Listener {

                            Toast.makeText(applicationContext,"Deleted",Toast.LENGTH_LONG).show()
                            startActivity(Intent(applicationContext,MainActivity::class.java))

                        },Response.ErrorListener {

                            Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()

                        })


                    {
                        override fun getParams(): MutableMap<String, String>?
                        {
                            var map = HashMap<String,String>()
                            map["id"]= m.id.toString()//get
                            return map
                        }

                    }
                        var queue:RequestQueue = Volley.newRequestQueue(this)
                        queue.add(stringrequest)


                    })
                alert.setNegativeButton("NO",{ dialogInterface: DialogInterface, i: Int ->

                })
                alert.show()

                //Toast.makeText(applicationContext,"DELETE",Toast.LENGTH_SHORT).show()
            }
        }
        return super.onContextItemSelected(item)
    }
}






