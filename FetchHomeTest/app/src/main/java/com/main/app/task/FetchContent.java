package com.main.app.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.main.app.adapter.ListAdapter;
import com.main.app.model.Object1;
import com.main.app.utils.NetworkUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class FetchContent extends AsyncTask<Void,Void,Void> {


    private static final String TAG="fetchData";
    private String jsonString;
    private ArrayList<Object1> linkedList;
    private Context context;

    public FetchContent(Context context){
        this.context=context;
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
        //Log.i(TAG, jsonString);
        //Log.i(TAG,this.linkedList.size()+"");
        ListAdapter adapter =new ListAdapter(linkedList, context);
    }
}
