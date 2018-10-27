package com.example.admin.myapplication.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.admin.myapplication.Pojo.Street_Pojo;
import com.example.admin.myapplication.R;

import java.util.ArrayList;

/**
 * Created by admin on 15-09-2017.
 */

public class StreetAdapter extends RecyclerView.Adapter<StreetAdapter.ViewHolder> {

    private ArrayList<Street_Pojo> beanList;

    public StreetAdapter(ArrayList<Street_Pojo> beanList) {
        this.beanList = beanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_spinner_layout, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvPlace.setText(beanList.get(position).getmStreetName());
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public  void filterList(ArrayList<Street_Pojo> filterdNames) {
        this.beanList = filterdNames;
        notifyDataSetChanged();
    }

    public ArrayList<Street_Pojo> getList() {
        return beanList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPlace;

        public ViewHolder(View itemView) {
            super(itemView);
            tvPlace = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
