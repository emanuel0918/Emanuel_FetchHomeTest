package com.example.myapplication.data.model


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.myapplication.R


class Object1Adapter(
    private val context: Context,
    layout: Int,
    private val objects: ArrayList<Object1>
) :
    ArrayAdapter<Object1?>(context, layout, objects as List<Object1?>) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val idTextView: TextView
        val listIdTextView: TextView
        val nameTextView: TextView
        if (convertView == null) {
            val layoutInflater = LayoutInflater.from(context)
            convertView = layoutInflater.inflate(R.layout.object1_layout, null)
        }
        idTextView = convertView!!.findViewById<TextView>(R.id.textViewId)
        listIdTextView = convertView.findViewById<TextView>(R.id.textViewListId)
        nameTextView = convertView.findViewById<TextView>(R.id.textViewName)
        val o = objects[position]
        idTextView.text = o.id.toString() + ""
        listIdTextView.text = o.listId.toString() + ""
        nameTextView.setText(o.name)
        return convertView
    }
}
