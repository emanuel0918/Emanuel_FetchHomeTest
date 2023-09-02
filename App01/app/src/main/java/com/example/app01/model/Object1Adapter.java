package com.example.app01.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.app01.R;

import java.util.LinkedList;

public class Object1Adapter extends ArrayAdapter<Object1> {
    private Context context;
    private LinkedList<Object1> objects;

    public Object1Adapter(Context context, int layout, LinkedList<Object1> itemList){
        super(context, layout, itemList);
        this.context = context;
        this.objects = itemList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView idTextView, listIdTextView, nameTextView;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(R.layout.object_layout1, null);
        }
        idTextView = convertView.findViewById(R.id.textViewId);
        listIdTextView = convertView.findViewById(R.id.textViewListId);
        nameTextView = convertView.findViewById(R.id.textViewName);

        Object1 o = objects.get(position);

        idTextView.setText(o.getId()+"");

        listIdTextView.setText(o.getListId()+"");

        nameTextView.setText(o.getName());

        return convertView;
    }

}
