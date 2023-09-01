package com.main.app.adapter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.main.app.R;
import com.main.app.model.Object1;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private ArrayList<Object1> objects;
    private Context context;

    public ListAdapter(ArrayList<Object1> itemList, Context context){
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.objects = itemList;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        TextView idTextView, listIdTextView, nameTextView;
        ViewHolder(View itemView){
            super(itemView);
            idTextView = itemView.findViewById(R.id.textViewId);
            listIdTextView = itemView.findViewById(R.id.textViewListId);
            nameTextView = itemView.findViewById(R.id.textViewName);
        }

        void bindData(final Object1 item){
            try {
                idTextView.setText(Integer.toString(item.getId()));

            } catch (NumberFormatException nfe) {
                Log.i(ListAdapter.class.getName(),"Exception in Id");
            }
            try {
                listIdTextView.setText(Integer.toString(item.getListId()));

            } catch (NumberFormatException nfe) {
                Log.i(ListAdapter.class.getName(),"Exception in Id of List");

            }
            try {
                nameTextView.setText(item.getName());
            } catch (NullPointerException npe) {
                Log.i(ListAdapter.class.getName(),"Exception in Name");

            }

        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {


        }
    }
    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount(){return objects.size();}



    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
