package com.example.todo

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment


class DesignFragment : Fragment()
{
    lateinit var listView: ListView
    lateinit var list: MutableList<Model>
    lateinit var dbhelper: DbHelper
    lateinit var adapter: SimpleAdapter
    var arrayList = ArrayList<HashMap<String, String>>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_design, container, false)

        dbhelper = DbHelper(requireActivity())
        listView = view.findViewById(R.id.list)

        loadTasks()

        registerForContextMenu(listView)

        return view
    }

    private fun loadTasks() {
        list = dbhelper.viewdata()
        arrayList.clear()

        for (item in list) {
            val hm = HashMap<String, String>()
            hm["n1"] = item.Tname
            hm["n2"] = item.Tdisc
            arrayList.add(hm)
        }

        val fromArray = arrayOf("n1", "n2")
        val toArray = intArrayOf(R.id.txtname, R.id.txtdisc)

        adapter = SimpleAdapter(requireActivity(), arrayList, R.layout.design, fromArray, toArray)
        listView.adapter = adapter
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.add(0, 1, 0, "Update")
        menu.add(0, 2, 0, "Delete")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val acm = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val position = acm.position
        val selectedItemId = list[position].id

        when (item.itemId) {
            1 -> {
                val intent = Intent(requireActivity(), UpdateActivity::class.java).apply {
                    putExtra("id", selectedItemId)
                    putExtra("title", list[position].Tname)
                    putExtra("desc", list[position].Tdisc)
                }
                startActivity(intent)
            }
            2 -> {
                val alertDialog = AlertDialog.Builder(requireContext())
                    .setTitle("Are You Sure You Want to Delete?")
                    .setPositiveButton("Yes") { _, _ ->
                        dbhelper.deletedata(selectedItemId)
                        loadTasks() // Refresh list after deletion
                    }
                    .setNegativeButton("No") { dialogInterface, _ ->
                        dialogInterface.cancel()
                    }
                    .create()
                alertDialog.show()
            }
        }
        return super.onContextItemSelected(item)
    }
}