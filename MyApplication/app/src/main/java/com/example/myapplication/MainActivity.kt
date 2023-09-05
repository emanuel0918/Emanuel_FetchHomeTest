package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.data.model.Object1
import com.example.myapplication.data.model.Object1Adapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.TreeMap

class MainActivity : AppCompatActivity() {

    val list1:ArrayList<Object1> = ArrayList()

    val broadCastReceiver = object : BroadcastReceiver() {
        override fun onReceive(contxt: Context?, intent: Intent?) {
            val jsonString:String = intent?.getStringExtra("json")!!
            Log.i("",jsonString)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, FetchContentService::class.java)
        FetchContentService.enqueueWork(this, intent)
        val response:String=intent?.getStringExtra("json")!!


        val gson: Gson = Gson()

        // Define the type of the list using TypeToken
        val listType = object : TypeToken<List<Object1>>() {}.type

        // Deserialize the JSON array to a List<Object1>
        val list: List<Object1> = gson.fromJson<List<Object1>>(response, listType)

        // Create content
        // val content = ArrayList<Object1>()

        // group by id
        val objectsById = TreeMap<Int, ArrayList<Object1>>()
        for (o1 in list) {
            if (!objectsById.containsKey(o1.listId)) {
                objectsById[o1.listId] = ArrayList()
            }
            // Filter out any items where "name" is blank or null.
            if (!o1.name.isNullOrEmpty()) {
                if (o1.name != "") {
                    objectsById[o1.listId]!!.add(o1)
                }
            }
        }

        // sort by name
        for (key in objectsById.keys) {
            objectsById[key]!!.sortWith(Comparator { obj1: Object1, obj2: Object1 ->                                obj1.name.compareTo(obj2.name)})
        }

        for (key in objectsById.keys) {
            for (object1 in objectsById[key]!!) {
                list1.add(object1)
            }
        }

        val adapter = Object1Adapter(getApplicationContext(),R.layout.object1_layout,list1)

        val ldata: ListView = findViewById(R.id.jsonObjectView1)
        ldata.setAdapter(adapter)
    }


}
