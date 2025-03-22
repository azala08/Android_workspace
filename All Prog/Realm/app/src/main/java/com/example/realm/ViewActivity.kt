package com.example.realm

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.realm.Realm

class ViewActivity : AppCompatActivity()
{
    lateinit var listView: ListView
    lateinit var list:MutableList<Model>
    lateinit var realm: Realm
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        listView = findViewById(R.id.list)
        list = ArrayList()

        realm = Realm.getInstance(Realm.getDefaultConfiguration())

        realm.beginTransaction()

        var realmresults = realm.where(Model::class.java).findAll()

        for(i in realmresults.indices)
        {
            list.add(realmresults[i]!!)
        }

        var adapter = MyAdapter(applicationContext,list)
        listView.adapter=adapter

        realm.commitTransaction()

        listView.setOnItemClickListener { parent, view, position, id ->


            var alert = AlertDialog.Builder(this)
            alert.setTitle("Select Operations?")
            alert.setPositiveButton("Update",{ dialogInterface: DialogInterface, i: Int ->

                var alert2 = AlertDialog.Builder(this)
                var layout = LayoutInflater.from(applicationContext)
                var view= layout.inflate(R.layout.activity_update,null)
                var edt1: EditText = view.findViewById(R.id.edtname)
                var edt2:EditText = view.findViewById(R.id.edtnum)
                edt1.setText(list.get(position).name)
                edt2.setText(list.get(position).num)
                alert2.setView(view)
                alert2.setPositiveButton("Update",{ dialogInterface: DialogInterface, i: Int ->

                    realm.beginTransaction()

                    var name = edt1.text.toString()
                    var num = edt2.text.toString()

                    list.get(position).name=name
                    list.get(position).num=num

                    realm.commitTransaction()
                    startActivity(Intent(applicationContext,ViewActivity::class.java))


                })
                alert2.show()

            })
            alert.setNegativeButton("Delete",{ dialogInterface: DialogInterface, i: Int ->

                realm.beginTransaction()
                var deleterealm = realm.where(Model::class.java).findAll()
                deleterealm.deleteFromRealm(position)
                realm.commitTransaction()
                startActivity(Intent(applicationContext,ViewActivity::class.java))

            })
            alert.show()


        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        finishAffinity()
    }
}