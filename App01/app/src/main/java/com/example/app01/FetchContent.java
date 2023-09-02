package com.example.app01;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.AsyncTask;
import android.widget.ListView;


import com.example.app01.model.Object1;
import com.example.app01.model.Object1Adapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;


public class FetchContent extends AsyncTask<Void,Void,Void> {


    private static final String TAG="fetchData";
    private String jsonString;
    private Context context;
    private LinkedList content;
    private ArrayList<Object1> linkedList;
    private int layout;
    private ListView listView;

    public FetchContent(Context ctx, ListView listView, int layout){
        this.context=ctx;
        this.layout = layout;
        this.listView = listView;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {

            jsonString = NetworkUtils.getURLText("https://fetch-hiring.s3.amazonaws.com/hiring.json");

            Gson gson = new Gson();

            // Define the type of the list using TypeToken
            Type listType = new TypeToken<List<Object1>>(){}.getType();

            // Deserialize the JSON array to a List<Object1>
            linkedList = gson.fromJson(jsonString, listType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        // Create content
        content = new LinkedList();
        // group by id
        TreeMap<Integer, ArrayList<Object1>> objectsById = new TreeMap<>();
        for (Object1 o1 : linkedList) {
            if (!objectsById.containsKey(o1.getListId())) {
                objectsById.put(o1.getListId(), new ArrayList<Object1>());
            }
            // Filter out any items where "name" is blank or null.
            if (o1.getName() != null) {
                if (!o1.getName().equals("")) {
                    objectsById.get(o1.getListId()).add(o1);
                }
            }
        }


        // sort by name
        for (Integer key : objectsById.keySet()) {
            Collections.sort(objectsById.get(key), new Comparator<Object1>() {
                @Override
                public int compare(Object1 o1, Object1 o2) {
                    return o1.getName().compareToIgnoreCase(o2.getName());
                }
            });
        }

        for (Integer key : objectsById.keySet()) {
            content.addAll(objectsById.get(key));
        }
        Object1Adapter adapter = new Object1Adapter(context, layout, content);
        listView.setAdapter(adapter);
    }
}
